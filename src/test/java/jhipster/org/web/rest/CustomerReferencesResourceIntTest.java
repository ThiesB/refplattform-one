package jhipster.org.web.rest;

import jhipster.org.RefplattformOneApp;

import jhipster.org.domain.CustomerReferences;
import jhipster.org.repository.CustomerReferencesRepository;
import jhipster.org.service.CustomerReferencesService;
import jhipster.org.service.dto.CustomerReferencesDTO;
import jhipster.org.service.mapper.CustomerReferencesMapper;
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
 * Test class for the CustomerReferencesResource REST controller.
 *
 * @see CustomerReferencesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefplattformOneApp.class)
public class CustomerReferencesResourceIntTest {

    private static final String DEFAULT_TITEL = "AAAAAAAAAA";
    private static final String UPDATED_TITEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROJECT_TIME_SPAN = 1;
    private static final Integer UPDATED_PROJECT_TIME_SPAN = 2;

    private static final Integer DEFAULT_PROJECT_VOLUME = 1;
    private static final Integer UPDATED_PROJECT_VOLUME = 2;

    private static final String DEFAULT_PROJECT_TEAM = "AAAAAAAAAA";
    private static final String UPDATED_PROJECT_TEAM = "BBBBBBBBBB";

    private static final Integer DEFAULT_EXXETA_CONSULTING_AMOUNT = 1;
    private static final Integer UPDATED_EXXETA_CONSULTING_AMOUNT = 2;

    private static final String DEFAULT_REFERENCE_OWNER = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE_OWNER = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EXXETA_PROJECT = false;
    private static final Boolean UPDATED_EXXETA_PROJECT = true;

    private static final Boolean DEFAULT_FINISHED = false;
    private static final Boolean UPDATED_FINISHED = true;

    private static final String DEFAULT_SCHLAGWORTE = "AAAAAAAAAA";
    private static final String UPDATED_SCHLAGWORTE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_KRITISCHE_ERFOLGSFAKTOREN = "AAAAAAAAAA";
    private static final String UPDATED_KRITISCHE_ERFOLGSFAKTOREN = "BBBBBBBBBB";

    private static final String DEFAULT_ANMERKUNGEN = "AAAAAAAAAA";
    private static final String UPDATED_ANMERKUNGEN = "BBBBBBBBBB";

    @Autowired
    private CustomerReferencesRepository customerReferencesRepository;

    @Autowired
    private CustomerReferencesMapper customerReferencesMapper;

    @Autowired
    private CustomerReferencesService customerReferencesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCustomerReferencesMockMvc;

    private CustomerReferences customerReferences;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CustomerReferencesResource customerReferencesResource = new CustomerReferencesResource(customerReferencesService, customerReferencesRepository);
        this.restCustomerReferencesMockMvc = MockMvcBuilders.standaloneSetup(customerReferencesResource)
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
    public static CustomerReferences createEntity(EntityManager em) {
        CustomerReferences customerReferences = new CustomerReferences()
            .titel(DEFAULT_TITEL)
            .projectTimeSpan(DEFAULT_PROJECT_TIME_SPAN)
            .projectVolume(DEFAULT_PROJECT_VOLUME)
            .projectTeam(DEFAULT_PROJECT_TEAM)
            .exxetaConsultingAmount(DEFAULT_EXXETA_CONSULTING_AMOUNT)
            .referenceOwner(DEFAULT_REFERENCE_OWNER)
            .exxetaProject(DEFAULT_EXXETA_PROJECT)
            .finished(DEFAULT_FINISHED)
            .schlagworte(DEFAULT_SCHLAGWORTE)
            .contact(DEFAULT_CONTACT)
            .kritischeErfolgsfaktoren(DEFAULT_KRITISCHE_ERFOLGSFAKTOREN)
            .anmerkungen(DEFAULT_ANMERKUNGEN);
        return customerReferences;
    }

    @Before
    public void initTest() {
        customerReferences = createEntity(em);
    }

    @Test
    @Transactional
    public void createCustomerReferences() throws Exception {
        int databaseSizeBeforeCreate = customerReferencesRepository.findAll().size();

        // Create the CustomerReferences
        CustomerReferencesDTO customerReferencesDTO = customerReferencesMapper.toDto(customerReferences);
        restCustomerReferencesMockMvc.perform(post("/api/customer-references")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerReferencesDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerReferences in the database
        List<CustomerReferences> customerReferencesList = customerReferencesRepository.findAll();
        assertThat(customerReferencesList).hasSize(databaseSizeBeforeCreate + 1);
        CustomerReferences testCustomerReferences = customerReferencesList.get(customerReferencesList.size() - 1);
        assertThat(testCustomerReferences.getTitel()).isEqualTo(DEFAULT_TITEL);
        assertThat(testCustomerReferences.getProjectTimeSpan()).isEqualTo(DEFAULT_PROJECT_TIME_SPAN);
        assertThat(testCustomerReferences.getProjectVolume()).isEqualTo(DEFAULT_PROJECT_VOLUME);
        assertThat(testCustomerReferences.getProjectTeam()).isEqualTo(DEFAULT_PROJECT_TEAM);
        assertThat(testCustomerReferences.getExxetaConsultingAmount()).isEqualTo(DEFAULT_EXXETA_CONSULTING_AMOUNT);
        assertThat(testCustomerReferences.getReferenceOwner()).isEqualTo(DEFAULT_REFERENCE_OWNER);
        assertThat(testCustomerReferences.isExxetaProject()).isEqualTo(DEFAULT_EXXETA_PROJECT);
        assertThat(testCustomerReferences.isFinished()).isEqualTo(DEFAULT_FINISHED);
        assertThat(testCustomerReferences.getSchlagworte()).isEqualTo(DEFAULT_SCHLAGWORTE);
        assertThat(testCustomerReferences.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testCustomerReferences.getKritischeErfolgsfaktoren()).isEqualTo(DEFAULT_KRITISCHE_ERFOLGSFAKTOREN);
        assertThat(testCustomerReferences.getAnmerkungen()).isEqualTo(DEFAULT_ANMERKUNGEN);
    }

    @Test
    @Transactional
    public void createCustomerReferencesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = customerReferencesRepository.findAll().size();

        // Create the CustomerReferences with an existing ID
        customerReferences.setId(1L);
        CustomerReferencesDTO customerReferencesDTO = customerReferencesMapper.toDto(customerReferences);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCustomerReferencesMockMvc.perform(post("/api/customer-references")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerReferencesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CustomerReferences in the database
        List<CustomerReferences> customerReferencesList = customerReferencesRepository.findAll();
        assertThat(customerReferencesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitelIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerReferencesRepository.findAll().size();
        // set the field null
        customerReferences.setTitel(null);

        // Create the CustomerReferences, which fails.
        CustomerReferencesDTO customerReferencesDTO = customerReferencesMapper.toDto(customerReferences);

        restCustomerReferencesMockMvc.perform(post("/api/customer-references")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerReferencesDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerReferences> customerReferencesList = customerReferencesRepository.findAll();
        assertThat(customerReferencesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReferenceOwnerIsRequired() throws Exception {
        int databaseSizeBeforeTest = customerReferencesRepository.findAll().size();
        // set the field null
        customerReferences.setReferenceOwner(null);

        // Create the CustomerReferences, which fails.
        CustomerReferencesDTO customerReferencesDTO = customerReferencesMapper.toDto(customerReferences);

        restCustomerReferencesMockMvc.perform(post("/api/customer-references")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerReferencesDTO)))
            .andExpect(status().isBadRequest());

        List<CustomerReferences> customerReferencesList = customerReferencesRepository.findAll();
        assertThat(customerReferencesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCustomerReferences() throws Exception {
        // Initialize the database
        customerReferencesRepository.saveAndFlush(customerReferences);

        // Get all the customerReferencesList
        restCustomerReferencesMockMvc.perform(get("/api/customer-references?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(customerReferences.getId().intValue())))
            .andExpect(jsonPath("$.[*].titel").value(hasItem(DEFAULT_TITEL.toString())))
            .andExpect(jsonPath("$.[*].projectTimeSpan").value(hasItem(DEFAULT_PROJECT_TIME_SPAN)))
            .andExpect(jsonPath("$.[*].projectVolume").value(hasItem(DEFAULT_PROJECT_VOLUME)))
            .andExpect(jsonPath("$.[*].projectTeam").value(hasItem(DEFAULT_PROJECT_TEAM.toString())))
            .andExpect(jsonPath("$.[*].exxetaConsultingAmount").value(hasItem(DEFAULT_EXXETA_CONSULTING_AMOUNT)))
            .andExpect(jsonPath("$.[*].referenceOwner").value(hasItem(DEFAULT_REFERENCE_OWNER.toString())))
            .andExpect(jsonPath("$.[*].exxetaProject").value(hasItem(DEFAULT_EXXETA_PROJECT.booleanValue())))
            .andExpect(jsonPath("$.[*].finished").value(hasItem(DEFAULT_FINISHED.booleanValue())))
            .andExpect(jsonPath("$.[*].schlagworte").value(hasItem(DEFAULT_SCHLAGWORTE.toString())))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].kritischeErfolgsfaktoren").value(hasItem(DEFAULT_KRITISCHE_ERFOLGSFAKTOREN.toString())))
            .andExpect(jsonPath("$.[*].anmerkungen").value(hasItem(DEFAULT_ANMERKUNGEN.toString())));
    }

    @Test
    @Transactional
    public void getCustomerReferences() throws Exception {
        // Initialize the database
        customerReferencesRepository.saveAndFlush(customerReferences);

        // Get the customerReferences
        restCustomerReferencesMockMvc.perform(get("/api/customer-references/{id}", customerReferences.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(customerReferences.getId().intValue()))
            .andExpect(jsonPath("$.titel").value(DEFAULT_TITEL.toString()))
            .andExpect(jsonPath("$.projectTimeSpan").value(DEFAULT_PROJECT_TIME_SPAN))
            .andExpect(jsonPath("$.projectVolume").value(DEFAULT_PROJECT_VOLUME))
            .andExpect(jsonPath("$.projectTeam").value(DEFAULT_PROJECT_TEAM.toString()))
            .andExpect(jsonPath("$.exxetaConsultingAmount").value(DEFAULT_EXXETA_CONSULTING_AMOUNT))
            .andExpect(jsonPath("$.referenceOwner").value(DEFAULT_REFERENCE_OWNER.toString()))
            .andExpect(jsonPath("$.exxetaProject").value(DEFAULT_EXXETA_PROJECT.booleanValue()))
            .andExpect(jsonPath("$.finished").value(DEFAULT_FINISHED.booleanValue()))
            .andExpect(jsonPath("$.schlagworte").value(DEFAULT_SCHLAGWORTE.toString()))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT.toString()))
            .andExpect(jsonPath("$.kritischeErfolgsfaktoren").value(DEFAULT_KRITISCHE_ERFOLGSFAKTOREN.toString()))
            .andExpect(jsonPath("$.anmerkungen").value(DEFAULT_ANMERKUNGEN.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCustomerReferences() throws Exception {
        // Get the customerReferences
        restCustomerReferencesMockMvc.perform(get("/api/customer-references/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCustomerReferences() throws Exception {
        // Initialize the database
        customerReferencesRepository.saveAndFlush(customerReferences);
        int databaseSizeBeforeUpdate = customerReferencesRepository.findAll().size();

        // Update the customerReferences
        CustomerReferences updatedCustomerReferences = customerReferencesRepository.findOne(customerReferences.getId());
        // Disconnect from session so that the updates on updatedCustomerReferences are not directly saved in db
        em.detach(updatedCustomerReferences);
        updatedCustomerReferences
            .titel(UPDATED_TITEL)
            .projectTimeSpan(UPDATED_PROJECT_TIME_SPAN)
            .projectVolume(UPDATED_PROJECT_VOLUME)
            .projectTeam(UPDATED_PROJECT_TEAM)
            .exxetaConsultingAmount(UPDATED_EXXETA_CONSULTING_AMOUNT)
            .referenceOwner(UPDATED_REFERENCE_OWNER)
            .exxetaProject(UPDATED_EXXETA_PROJECT)
            .finished(UPDATED_FINISHED)
            .schlagworte(UPDATED_SCHLAGWORTE)
            .contact(UPDATED_CONTACT)
            .kritischeErfolgsfaktoren(UPDATED_KRITISCHE_ERFOLGSFAKTOREN)
            .anmerkungen(UPDATED_ANMERKUNGEN);
        CustomerReferencesDTO customerReferencesDTO = customerReferencesMapper.toDto(updatedCustomerReferences);

        restCustomerReferencesMockMvc.perform(put("/api/customer-references")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerReferencesDTO)))
            .andExpect(status().isOk());

        // Validate the CustomerReferences in the database
        List<CustomerReferences> customerReferencesList = customerReferencesRepository.findAll();
        assertThat(customerReferencesList).hasSize(databaseSizeBeforeUpdate);
        CustomerReferences testCustomerReferences = customerReferencesList.get(customerReferencesList.size() - 1);
        assertThat(testCustomerReferences.getTitel()).isEqualTo(UPDATED_TITEL);
        assertThat(testCustomerReferences.getProjectTimeSpan()).isEqualTo(UPDATED_PROJECT_TIME_SPAN);
        assertThat(testCustomerReferences.getProjectVolume()).isEqualTo(UPDATED_PROJECT_VOLUME);
        assertThat(testCustomerReferences.getProjectTeam()).isEqualTo(UPDATED_PROJECT_TEAM);
        assertThat(testCustomerReferences.getExxetaConsultingAmount()).isEqualTo(UPDATED_EXXETA_CONSULTING_AMOUNT);
        assertThat(testCustomerReferences.getReferenceOwner()).isEqualTo(UPDATED_REFERENCE_OWNER);
        assertThat(testCustomerReferences.isExxetaProject()).isEqualTo(UPDATED_EXXETA_PROJECT);
        assertThat(testCustomerReferences.isFinished()).isEqualTo(UPDATED_FINISHED);
        assertThat(testCustomerReferences.getSchlagworte()).isEqualTo(UPDATED_SCHLAGWORTE);
        assertThat(testCustomerReferences.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testCustomerReferences.getKritischeErfolgsfaktoren()).isEqualTo(UPDATED_KRITISCHE_ERFOLGSFAKTOREN);
        assertThat(testCustomerReferences.getAnmerkungen()).isEqualTo(UPDATED_ANMERKUNGEN);
    }

    @Test
    @Transactional
    public void updateNonExistingCustomerReferences() throws Exception {
        int databaseSizeBeforeUpdate = customerReferencesRepository.findAll().size();

        // Create the CustomerReferences
        CustomerReferencesDTO customerReferencesDTO = customerReferencesMapper.toDto(customerReferences);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCustomerReferencesMockMvc.perform(put("/api/customer-references")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(customerReferencesDTO)))
            .andExpect(status().isCreated());

        // Validate the CustomerReferences in the database
        List<CustomerReferences> customerReferencesList = customerReferencesRepository.findAll();
        assertThat(customerReferencesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCustomerReferences() throws Exception {
        // Initialize the database
        customerReferencesRepository.saveAndFlush(customerReferences);
        int databaseSizeBeforeDelete = customerReferencesRepository.findAll().size();

        // Get the customerReferences
        restCustomerReferencesMockMvc.perform(delete("/api/customer-references/{id}", customerReferences.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CustomerReferences> customerReferencesList = customerReferencesRepository.findAll();
        assertThat(customerReferencesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerReferences.class);
        CustomerReferences customerReferences1 = new CustomerReferences();
        customerReferences1.setId(1L);
        CustomerReferences customerReferences2 = new CustomerReferences();
        customerReferences2.setId(customerReferences1.getId());
        assertThat(customerReferences1).isEqualTo(customerReferences2);
        customerReferences2.setId(2L);
        assertThat(customerReferences1).isNotEqualTo(customerReferences2);
        customerReferences1.setId(null);
        assertThat(customerReferences1).isNotEqualTo(customerReferences2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CustomerReferencesDTO.class);
        CustomerReferencesDTO customerReferencesDTO1 = new CustomerReferencesDTO();
        customerReferencesDTO1.setId(1L);
        CustomerReferencesDTO customerReferencesDTO2 = new CustomerReferencesDTO();
        assertThat(customerReferencesDTO1).isNotEqualTo(customerReferencesDTO2);
        customerReferencesDTO2.setId(customerReferencesDTO1.getId());
        assertThat(customerReferencesDTO1).isEqualTo(customerReferencesDTO2);
        customerReferencesDTO2.setId(2L);
        assertThat(customerReferencesDTO1).isNotEqualTo(customerReferencesDTO2);
        customerReferencesDTO1.setId(null);
        assertThat(customerReferencesDTO1).isNotEqualTo(customerReferencesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(customerReferencesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(customerReferencesMapper.fromId(null)).isNull();
    }
}
