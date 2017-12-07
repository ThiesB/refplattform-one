package jhipster.org.web.rest;

import jhipster.org.RefplattformOneApp;

import jhipster.org.domain.ServiceComponents;
import jhipster.org.repository.ServiceComponentsRepository;
import jhipster.org.service.ServiceComponentsService;
import jhipster.org.service.dto.ServiceComponentsDTO;
import jhipster.org.service.mapper.ServiceComponentsMapper;
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
 * Test class for the ServiceComponentsResource REST controller.
 *
 * @see ServiceComponentsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefplattformOneApp.class)
public class ServiceComponentsResourceIntTest {

    private static final String DEFAULT_SERVICE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_NAME = "BBBBBBBBBB";

    @Autowired
    private ServiceComponentsRepository serviceComponentsRepository;

    @Autowired
    private ServiceComponentsMapper serviceComponentsMapper;

    @Autowired
    private ServiceComponentsService serviceComponentsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restServiceComponentsMockMvc;

    private ServiceComponents serviceComponents;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ServiceComponentsResource serviceComponentsResource = new ServiceComponentsResource(serviceComponentsService);
        this.restServiceComponentsMockMvc = MockMvcBuilders.standaloneSetup(serviceComponentsResource)
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
    public static ServiceComponents createEntity(EntityManager em) {
        ServiceComponents serviceComponents = new ServiceComponents()
            .serviceName(DEFAULT_SERVICE_NAME);
        return serviceComponents;
    }

    @Before
    public void initTest() {
        serviceComponents = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceComponents() throws Exception {
        int databaseSizeBeforeCreate = serviceComponentsRepository.findAll().size();

        // Create the ServiceComponents
        ServiceComponentsDTO serviceComponentsDTO = serviceComponentsMapper.toDto(serviceComponents);
        restServiceComponentsMockMvc.perform(post("/api/service-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceComponentsDTO)))
            .andExpect(status().isCreated());

        // Validate the ServiceComponents in the database
        List<ServiceComponents> serviceComponentsList = serviceComponentsRepository.findAll();
        assertThat(serviceComponentsList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceComponents testServiceComponents = serviceComponentsList.get(serviceComponentsList.size() - 1);
        assertThat(testServiceComponents.getServiceName()).isEqualTo(DEFAULT_SERVICE_NAME);
    }

    @Test
    @Transactional
    public void createServiceComponentsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceComponentsRepository.findAll().size();

        // Create the ServiceComponents with an existing ID
        serviceComponents.setId(1L);
        ServiceComponentsDTO serviceComponentsDTO = serviceComponentsMapper.toDto(serviceComponents);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceComponentsMockMvc.perform(post("/api/service-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceComponentsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceComponents in the database
        List<ServiceComponents> serviceComponentsList = serviceComponentsRepository.findAll();
        assertThat(serviceComponentsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkServiceNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = serviceComponentsRepository.findAll().size();
        // set the field null
        serviceComponents.setServiceName(null);

        // Create the ServiceComponents, which fails.
        ServiceComponentsDTO serviceComponentsDTO = serviceComponentsMapper.toDto(serviceComponents);

        restServiceComponentsMockMvc.perform(post("/api/service-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceComponentsDTO)))
            .andExpect(status().isBadRequest());

        List<ServiceComponents> serviceComponentsList = serviceComponentsRepository.findAll();
        assertThat(serviceComponentsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllServiceComponents() throws Exception {
        // Initialize the database
        serviceComponentsRepository.saveAndFlush(serviceComponents);

        // Get all the serviceComponentsList
        restServiceComponentsMockMvc.perform(get("/api/service-components?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceComponents.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceName").value(hasItem(DEFAULT_SERVICE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getServiceComponents() throws Exception {
        // Initialize the database
        serviceComponentsRepository.saveAndFlush(serviceComponents);

        // Get the serviceComponents
        restServiceComponentsMockMvc.perform(get("/api/service-components/{id}", serviceComponents.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(serviceComponents.getId().intValue()))
            .andExpect(jsonPath("$.serviceName").value(DEFAULT_SERVICE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingServiceComponents() throws Exception {
        // Get the serviceComponents
        restServiceComponentsMockMvc.perform(get("/api/service-components/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceComponents() throws Exception {
        // Initialize the database
        serviceComponentsRepository.saveAndFlush(serviceComponents);
        int databaseSizeBeforeUpdate = serviceComponentsRepository.findAll().size();

        // Update the serviceComponents
        ServiceComponents updatedServiceComponents = serviceComponentsRepository.findOne(serviceComponents.getId());
        // Disconnect from session so that the updates on updatedServiceComponents are not directly saved in db
        em.detach(updatedServiceComponents);
        updatedServiceComponents
            .serviceName(UPDATED_SERVICE_NAME);
        ServiceComponentsDTO serviceComponentsDTO = serviceComponentsMapper.toDto(updatedServiceComponents);

        restServiceComponentsMockMvc.perform(put("/api/service-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceComponentsDTO)))
            .andExpect(status().isOk());

        // Validate the ServiceComponents in the database
        List<ServiceComponents> serviceComponentsList = serviceComponentsRepository.findAll();
        assertThat(serviceComponentsList).hasSize(databaseSizeBeforeUpdate);
        ServiceComponents testServiceComponents = serviceComponentsList.get(serviceComponentsList.size() - 1);
        assertThat(testServiceComponents.getServiceName()).isEqualTo(UPDATED_SERVICE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceComponents() throws Exception {
        int databaseSizeBeforeUpdate = serviceComponentsRepository.findAll().size();

        // Create the ServiceComponents
        ServiceComponentsDTO serviceComponentsDTO = serviceComponentsMapper.toDto(serviceComponents);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restServiceComponentsMockMvc.perform(put("/api/service-components")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(serviceComponentsDTO)))
            .andExpect(status().isCreated());

        // Validate the ServiceComponents in the database
        List<ServiceComponents> serviceComponentsList = serviceComponentsRepository.findAll();
        assertThat(serviceComponentsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteServiceComponents() throws Exception {
        // Initialize the database
        serviceComponentsRepository.saveAndFlush(serviceComponents);
        int databaseSizeBeforeDelete = serviceComponentsRepository.findAll().size();

        // Get the serviceComponents
        restServiceComponentsMockMvc.perform(delete("/api/service-components/{id}", serviceComponents.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ServiceComponents> serviceComponentsList = serviceComponentsRepository.findAll();
        assertThat(serviceComponentsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceComponents.class);
        ServiceComponents serviceComponents1 = new ServiceComponents();
        serviceComponents1.setId(1L);
        ServiceComponents serviceComponents2 = new ServiceComponents();
        serviceComponents2.setId(serviceComponents1.getId());
        assertThat(serviceComponents1).isEqualTo(serviceComponents2);
        serviceComponents2.setId(2L);
        assertThat(serviceComponents1).isNotEqualTo(serviceComponents2);
        serviceComponents1.setId(null);
        assertThat(serviceComponents1).isNotEqualTo(serviceComponents2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ServiceComponentsDTO.class);
        ServiceComponentsDTO serviceComponentsDTO1 = new ServiceComponentsDTO();
        serviceComponentsDTO1.setId(1L);
        ServiceComponentsDTO serviceComponentsDTO2 = new ServiceComponentsDTO();
        assertThat(serviceComponentsDTO1).isNotEqualTo(serviceComponentsDTO2);
        serviceComponentsDTO2.setId(serviceComponentsDTO1.getId());
        assertThat(serviceComponentsDTO1).isEqualTo(serviceComponentsDTO2);
        serviceComponentsDTO2.setId(2L);
        assertThat(serviceComponentsDTO1).isNotEqualTo(serviceComponentsDTO2);
        serviceComponentsDTO1.setId(null);
        assertThat(serviceComponentsDTO1).isNotEqualTo(serviceComponentsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(serviceComponentsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(serviceComponentsMapper.fromId(null)).isNull();
    }
}
