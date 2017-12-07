package jhipster.org.web.rest;

import jhipster.org.RefplattformOneApp;

import jhipster.org.domain.Industries;
import jhipster.org.repository.IndustriesRepository;
import jhipster.org.service.IndustriesService;
import jhipster.org.service.dto.IndustriesDTO;
import jhipster.org.service.mapper.IndustriesMapper;
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
 * Test class for the IndustriesResource REST controller.
 *
 * @see IndustriesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefplattformOneApp.class)
public class IndustriesResourceIntTest {

    private static final String DEFAULT_INDUSTRY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY_NAME = "BBBBBBBBBB";

    @Autowired
    private IndustriesRepository industriesRepository;

    @Autowired
    private IndustriesMapper industriesMapper;

    @Autowired
    private IndustriesService industriesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restIndustriesMockMvc;

    private Industries industries;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final IndustriesResource industriesResource = new IndustriesResource(industriesService);
        this.restIndustriesMockMvc = MockMvcBuilders.standaloneSetup(industriesResource)
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
    public static Industries createEntity(EntityManager em) {
        Industries industries = new Industries()
            .industryName(DEFAULT_INDUSTRY_NAME);
        return industries;
    }

    @Before
    public void initTest() {
        industries = createEntity(em);
    }

    @Test
    @Transactional
    public void createIndustries() throws Exception {
        int databaseSizeBeforeCreate = industriesRepository.findAll().size();

        // Create the Industries
        IndustriesDTO industriesDTO = industriesMapper.toDto(industries);
        restIndustriesMockMvc.perform(post("/api/industries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(industriesDTO)))
            .andExpect(status().isCreated());

        // Validate the Industries in the database
        List<Industries> industriesList = industriesRepository.findAll();
        assertThat(industriesList).hasSize(databaseSizeBeforeCreate + 1);
        Industries testIndustries = industriesList.get(industriesList.size() - 1);
        assertThat(testIndustries.getIndustryName()).isEqualTo(DEFAULT_INDUSTRY_NAME);
    }

    @Test
    @Transactional
    public void createIndustriesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = industriesRepository.findAll().size();

        // Create the Industries with an existing ID
        industries.setId(1L);
        IndustriesDTO industriesDTO = industriesMapper.toDto(industries);

        // An entity with an existing ID cannot be created, so this API call must fail
        restIndustriesMockMvc.perform(post("/api/industries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(industriesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Industries in the database
        List<Industries> industriesList = industriesRepository.findAll();
        assertThat(industriesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkIndustryNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = industriesRepository.findAll().size();
        // set the field null
        industries.setIndustryName(null);

        // Create the Industries, which fails.
        IndustriesDTO industriesDTO = industriesMapper.toDto(industries);

        restIndustriesMockMvc.perform(post("/api/industries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(industriesDTO)))
            .andExpect(status().isBadRequest());

        List<Industries> industriesList = industriesRepository.findAll();
        assertThat(industriesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllIndustries() throws Exception {
        // Initialize the database
        industriesRepository.saveAndFlush(industries);

        // Get all the industriesList
        restIndustriesMockMvc.perform(get("/api/industries?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(industries.getId().intValue())))
            .andExpect(jsonPath("$.[*].industryName").value(hasItem(DEFAULT_INDUSTRY_NAME.toString())));
    }

    @Test
    @Transactional
    public void getIndustries() throws Exception {
        // Initialize the database
        industriesRepository.saveAndFlush(industries);

        // Get the industries
        restIndustriesMockMvc.perform(get("/api/industries/{id}", industries.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(industries.getId().intValue()))
            .andExpect(jsonPath("$.industryName").value(DEFAULT_INDUSTRY_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingIndustries() throws Exception {
        // Get the industries
        restIndustriesMockMvc.perform(get("/api/industries/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateIndustries() throws Exception {
        // Initialize the database
        industriesRepository.saveAndFlush(industries);
        int databaseSizeBeforeUpdate = industriesRepository.findAll().size();

        // Update the industries
        Industries updatedIndustries = industriesRepository.findOne(industries.getId());
        // Disconnect from session so that the updates on updatedIndustries are not directly saved in db
        em.detach(updatedIndustries);
        updatedIndustries
            .industryName(UPDATED_INDUSTRY_NAME);
        IndustriesDTO industriesDTO = industriesMapper.toDto(updatedIndustries);

        restIndustriesMockMvc.perform(put("/api/industries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(industriesDTO)))
            .andExpect(status().isOk());

        // Validate the Industries in the database
        List<Industries> industriesList = industriesRepository.findAll();
        assertThat(industriesList).hasSize(databaseSizeBeforeUpdate);
        Industries testIndustries = industriesList.get(industriesList.size() - 1);
        assertThat(testIndustries.getIndustryName()).isEqualTo(UPDATED_INDUSTRY_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingIndustries() throws Exception {
        int databaseSizeBeforeUpdate = industriesRepository.findAll().size();

        // Create the Industries
        IndustriesDTO industriesDTO = industriesMapper.toDto(industries);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restIndustriesMockMvc.perform(put("/api/industries")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(industriesDTO)))
            .andExpect(status().isCreated());

        // Validate the Industries in the database
        List<Industries> industriesList = industriesRepository.findAll();
        assertThat(industriesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteIndustries() throws Exception {
        // Initialize the database
        industriesRepository.saveAndFlush(industries);
        int databaseSizeBeforeDelete = industriesRepository.findAll().size();

        // Get the industries
        restIndustriesMockMvc.perform(delete("/api/industries/{id}", industries.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Industries> industriesList = industriesRepository.findAll();
        assertThat(industriesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Industries.class);
        Industries industries1 = new Industries();
        industries1.setId(1L);
        Industries industries2 = new Industries();
        industries2.setId(industries1.getId());
        assertThat(industries1).isEqualTo(industries2);
        industries2.setId(2L);
        assertThat(industries1).isNotEqualTo(industries2);
        industries1.setId(null);
        assertThat(industries1).isNotEqualTo(industries2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IndustriesDTO.class);
        IndustriesDTO industriesDTO1 = new IndustriesDTO();
        industriesDTO1.setId(1L);
        IndustriesDTO industriesDTO2 = new IndustriesDTO();
        assertThat(industriesDTO1).isNotEqualTo(industriesDTO2);
        industriesDTO2.setId(industriesDTO1.getId());
        assertThat(industriesDTO1).isEqualTo(industriesDTO2);
        industriesDTO2.setId(2L);
        assertThat(industriesDTO1).isNotEqualTo(industriesDTO2);
        industriesDTO1.setId(null);
        assertThat(industriesDTO1).isNotEqualTo(industriesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(industriesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(industriesMapper.fromId(null)).isNull();
    }
}
