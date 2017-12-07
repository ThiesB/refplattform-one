package jhipster.org.web.rest;

import jhipster.org.RefplattformOneApp;

import jhipster.org.domain.ConsultingDivision;
import jhipster.org.repository.ConsultingDivisionRepository;
import jhipster.org.service.ConsultingDivisionService;
import jhipster.org.service.dto.ConsultingDivisionDTO;
import jhipster.org.service.mapper.ConsultingDivisionMapper;
import jhipster.org.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static jhipster.org.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ConsultingDivisionResource REST controller.
 *
 * @see ConsultingDivisionResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefplattformOneApp.class)
public class ConsultingDivisionResourceIntTest {

    private static final String DEFAULT_DIVISION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DIVISION_NAME = "BBBBBBBBBB";

    @Autowired
    private ConsultingDivisionRepository consultingDivisionRepository;

    @Autowired
    private ConsultingDivisionMapper consultingDivisionMapper;

    @Autowired
    private ConsultingDivisionService consultingDivisionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restConsultingDivisionMockMvc;

    private ConsultingDivision consultingDivision;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ConsultingDivisionResource consultingDivisionResource = new ConsultingDivisionResource(consultingDivisionService);
        this.restConsultingDivisionMockMvc = MockMvcBuilders.standaloneSetup(consultingDivisionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ConsultingDivision createEntity(EntityManager em) {
        ConsultingDivision consultingDivision = new ConsultingDivision()
            .divisionName(DEFAULT_DIVISION_NAME);
        return consultingDivision;
    }

    @Before
    public void initTest() {
        consultingDivision = createEntity(em);
    }

    @Test
    @Transactional
    public void createConsultingDivision() throws Exception {
        int databaseSizeBeforeCreate = consultingDivisionRepository.findAll().size();

        // Create the ConsultingDivision
        ConsultingDivisionDTO consultingDivisionDTO = consultingDivisionMapper.toDto(consultingDivision);
        restConsultingDivisionMockMvc.perform(post("/api/consulting-divisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultingDivisionDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsultingDivision in the database
        List<ConsultingDivision> consultingDivisionList = consultingDivisionRepository.findAll();
        assertThat(consultingDivisionList).hasSize(databaseSizeBeforeCreate + 1);
        ConsultingDivision testConsultingDivision = consultingDivisionList.get(consultingDivisionList.size() - 1);
        assertThat(testConsultingDivision.getDivisionName()).isEqualTo(DEFAULT_DIVISION_NAME);
    }

    @Test
    @Transactional
    public void createConsultingDivisionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = consultingDivisionRepository.findAll().size();

        // Create the ConsultingDivision with an existing ID
        consultingDivision.setId(1L);
        ConsultingDivisionDTO consultingDivisionDTO = consultingDivisionMapper.toDto(consultingDivision);

        // An entity with an existing ID cannot be created, so this API call must fail
        restConsultingDivisionMockMvc.perform(post("/api/consulting-divisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultingDivisionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ConsultingDivision in the database
        List<ConsultingDivision> consultingDivisionList = consultingDivisionRepository.findAll();
        assertThat(consultingDivisionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkDivisionNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = consultingDivisionRepository.findAll().size();
        // set the field null
        consultingDivision.setDivisionName(null);

        // Create the ConsultingDivision, which fails.
        ConsultingDivisionDTO consultingDivisionDTO = consultingDivisionMapper.toDto(consultingDivision);

        restConsultingDivisionMockMvc.perform(post("/api/consulting-divisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultingDivisionDTO)))
            .andExpect(status().isBadRequest());

        List<ConsultingDivision> consultingDivisionList = consultingDivisionRepository.findAll();
        assertThat(consultingDivisionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllConsultingDivisions() throws Exception {
        // Initialize the database
        consultingDivisionRepository.saveAndFlush(consultingDivision);

        // Get all the consultingDivisionList
        restConsultingDivisionMockMvc.perform(get("/api/consulting-divisions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(consultingDivision.getId().intValue())))
            .andExpect(jsonPath("$.[*].divisionName").value(hasItem(DEFAULT_DIVISION_NAME.toString())));
    }

    @Test
    @Transactional
    public void getConsultingDivision() throws Exception {
        // Initialize the database
        consultingDivisionRepository.saveAndFlush(consultingDivision);

        // Get the consultingDivision
        restConsultingDivisionMockMvc.perform(get("/api/consulting-divisions/{id}", consultingDivision.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(consultingDivision.getId().intValue()))
            .andExpect(jsonPath("$.divisionName").value(DEFAULT_DIVISION_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingConsultingDivision() throws Exception {
        // Get the consultingDivision
        restConsultingDivisionMockMvc.perform(get("/api/consulting-divisions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateConsultingDivision() throws Exception {
        // Initialize the database
        consultingDivisionRepository.saveAndFlush(consultingDivision);
        int databaseSizeBeforeUpdate = consultingDivisionRepository.findAll().size();

        // Update the consultingDivision
        ConsultingDivision updatedConsultingDivision = consultingDivisionRepository.findOne(consultingDivision.getId());
        // Disconnect from session so that the updates on updatedConsultingDivision are not directly saved in db
        em.detach(updatedConsultingDivision);
        updatedConsultingDivision
            .divisionName(UPDATED_DIVISION_NAME);
        ConsultingDivisionDTO consultingDivisionDTO = consultingDivisionMapper.toDto(updatedConsultingDivision);

        restConsultingDivisionMockMvc.perform(put("/api/consulting-divisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultingDivisionDTO)))
            .andExpect(status().isOk());

        // Validate the ConsultingDivision in the database
        List<ConsultingDivision> consultingDivisionList = consultingDivisionRepository.findAll();
        assertThat(consultingDivisionList).hasSize(databaseSizeBeforeUpdate);
        ConsultingDivision testConsultingDivision = consultingDivisionList.get(consultingDivisionList.size() - 1);
        assertThat(testConsultingDivision.getDivisionName()).isEqualTo(UPDATED_DIVISION_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingConsultingDivision() throws Exception {
        int databaseSizeBeforeUpdate = consultingDivisionRepository.findAll().size();

        // Create the ConsultingDivision
        ConsultingDivisionDTO consultingDivisionDTO = consultingDivisionMapper.toDto(consultingDivision);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restConsultingDivisionMockMvc.perform(put("/api/consulting-divisions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(consultingDivisionDTO)))
            .andExpect(status().isCreated());

        // Validate the ConsultingDivision in the database
        List<ConsultingDivision> consultingDivisionList = consultingDivisionRepository.findAll();
        assertThat(consultingDivisionList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteConsultingDivision() throws Exception {
        // Initialize the database
        consultingDivisionRepository.saveAndFlush(consultingDivision);
        int databaseSizeBeforeDelete = consultingDivisionRepository.findAll().size();

        // Get the consultingDivision
        restConsultingDivisionMockMvc.perform(delete("/api/consulting-divisions/{id}", consultingDivision.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ConsultingDivision> consultingDivisionList = consultingDivisionRepository.findAll();
        assertThat(consultingDivisionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsultingDivision.class);
        ConsultingDivision consultingDivision1 = new ConsultingDivision();
        consultingDivision1.setId(1L);
        ConsultingDivision consultingDivision2 = new ConsultingDivision();
        consultingDivision2.setId(consultingDivision1.getId());
        assertThat(consultingDivision1).isEqualTo(consultingDivision2);
        consultingDivision2.setId(2L);
        assertThat(consultingDivision1).isNotEqualTo(consultingDivision2);
        consultingDivision1.setId(null);
        assertThat(consultingDivision1).isNotEqualTo(consultingDivision2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ConsultingDivisionDTO.class);
        ConsultingDivisionDTO consultingDivisionDTO1 = new ConsultingDivisionDTO();
        consultingDivisionDTO1.setId(1L);
        ConsultingDivisionDTO consultingDivisionDTO2 = new ConsultingDivisionDTO();
        assertThat(consultingDivisionDTO1).isNotEqualTo(consultingDivisionDTO2);
        consultingDivisionDTO2.setId(consultingDivisionDTO1.getId());
        assertThat(consultingDivisionDTO1).isEqualTo(consultingDivisionDTO2);
        consultingDivisionDTO2.setId(2L);
        assertThat(consultingDivisionDTO1).isNotEqualTo(consultingDivisionDTO2);
        consultingDivisionDTO1.setId(null);
        assertThat(consultingDivisionDTO1).isNotEqualTo(consultingDivisionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(consultingDivisionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(consultingDivisionMapper.fromId(null)).isNull();
    }
}
