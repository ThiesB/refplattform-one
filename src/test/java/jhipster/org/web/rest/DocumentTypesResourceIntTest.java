package jhipster.org.web.rest;

import jhipster.org.RefplattformOneApp;

import jhipster.org.domain.DocumentTypes;
import jhipster.org.repository.DocumentTypesRepository;
import jhipster.org.service.DocumentTypesService;
import jhipster.org.service.dto.DocumentTypesDTO;
import jhipster.org.service.mapper.DocumentTypesMapper;
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
 * Test class for the DocumentTypesResource REST controller.
 *
 * @see DocumentTypesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefplattformOneApp.class)
public class DocumentTypesResourceIntTest {

    private static final String DEFAULT_TITEL = "AAAAAAAAAA";
    private static final String UPDATED_TITEL = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    @Autowired
    private DocumentTypesRepository documentTypesRepository;

    @Autowired
    private DocumentTypesMapper documentTypesMapper;

    @Autowired
    private DocumentTypesService documentTypesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDocumentTypesMockMvc;

    private DocumentTypes documentTypes;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DocumentTypesResource documentTypesResource = new DocumentTypesResource(documentTypesService);
        this.restDocumentTypesMockMvc = MockMvcBuilders.standaloneSetup(documentTypesResource)
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
    public static DocumentTypes createEntity(EntityManager em) {
        DocumentTypes documentTypes = new DocumentTypes()
            .titel(DEFAULT_TITEL)
            .type(DEFAULT_TYPE);
        return documentTypes;
    }

    @Before
    public void initTest() {
        documentTypes = createEntity(em);
    }

    @Test
    @Transactional
    public void createDocumentTypes() throws Exception {
        int databaseSizeBeforeCreate = documentTypesRepository.findAll().size();

        // Create the DocumentTypes
        DocumentTypesDTO documentTypesDTO = documentTypesMapper.toDto(documentTypes);
        restDocumentTypesMockMvc.perform(post("/api/document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the DocumentTypes in the database
        List<DocumentTypes> documentTypesList = documentTypesRepository.findAll();
        assertThat(documentTypesList).hasSize(databaseSizeBeforeCreate + 1);
        DocumentTypes testDocumentTypes = documentTypesList.get(documentTypesList.size() - 1);
        assertThat(testDocumentTypes.getTitel()).isEqualTo(DEFAULT_TITEL);
        assertThat(testDocumentTypes.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createDocumentTypesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = documentTypesRepository.findAll().size();

        // Create the DocumentTypes with an existing ID
        documentTypes.setId(1L);
        DocumentTypesDTO documentTypesDTO = documentTypesMapper.toDto(documentTypes);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDocumentTypesMockMvc.perform(post("/api/document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentTypesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DocumentTypes in the database
        List<DocumentTypes> documentTypesList = documentTypesRepository.findAll();
        assertThat(documentTypesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitelIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentTypesRepository.findAll().size();
        // set the field null
        documentTypes.setTitel(null);

        // Create the DocumentTypes, which fails.
        DocumentTypesDTO documentTypesDTO = documentTypesMapper.toDto(documentTypes);

        restDocumentTypesMockMvc.perform(post("/api/document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentTypesDTO)))
            .andExpect(status().isBadRequest());

        List<DocumentTypes> documentTypesList = documentTypesRepository.findAll();
        assertThat(documentTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = documentTypesRepository.findAll().size();
        // set the field null
        documentTypes.setType(null);

        // Create the DocumentTypes, which fails.
        DocumentTypesDTO documentTypesDTO = documentTypesMapper.toDto(documentTypes);

        restDocumentTypesMockMvc.perform(post("/api/document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentTypesDTO)))
            .andExpect(status().isBadRequest());

        List<DocumentTypes> documentTypesList = documentTypesRepository.findAll();
        assertThat(documentTypesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDocumentTypes() throws Exception {
        // Initialize the database
        documentTypesRepository.saveAndFlush(documentTypes);

        // Get all the documentTypesList
        restDocumentTypesMockMvc.perform(get("/api/document-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(documentTypes.getId().intValue())))
            .andExpect(jsonPath("$.[*].titel").value(hasItem(DEFAULT_TITEL.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getDocumentTypes() throws Exception {
        // Initialize the database
        documentTypesRepository.saveAndFlush(documentTypes);

        // Get the documentTypes
        restDocumentTypesMockMvc.perform(get("/api/document-types/{id}", documentTypes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(documentTypes.getId().intValue()))
            .andExpect(jsonPath("$.titel").value(DEFAULT_TITEL.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDocumentTypes() throws Exception {
        // Get the documentTypes
        restDocumentTypesMockMvc.perform(get("/api/document-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDocumentTypes() throws Exception {
        // Initialize the database
        documentTypesRepository.saveAndFlush(documentTypes);
        int databaseSizeBeforeUpdate = documentTypesRepository.findAll().size();

        // Update the documentTypes
        DocumentTypes updatedDocumentTypes = documentTypesRepository.findOne(documentTypes.getId());
        // Disconnect from session so that the updates on updatedDocumentTypes are not directly saved in db
        em.detach(updatedDocumentTypes);
        updatedDocumentTypes
            .titel(UPDATED_TITEL)
            .type(UPDATED_TYPE);
        DocumentTypesDTO documentTypesDTO = documentTypesMapper.toDto(updatedDocumentTypes);

        restDocumentTypesMockMvc.perform(put("/api/document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentTypesDTO)))
            .andExpect(status().isOk());

        // Validate the DocumentTypes in the database
        List<DocumentTypes> documentTypesList = documentTypesRepository.findAll();
        assertThat(documentTypesList).hasSize(databaseSizeBeforeUpdate);
        DocumentTypes testDocumentTypes = documentTypesList.get(documentTypesList.size() - 1);
        assertThat(testDocumentTypes.getTitel()).isEqualTo(UPDATED_TITEL);
        assertThat(testDocumentTypes.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingDocumentTypes() throws Exception {
        int databaseSizeBeforeUpdate = documentTypesRepository.findAll().size();

        // Create the DocumentTypes
        DocumentTypesDTO documentTypesDTO = documentTypesMapper.toDto(documentTypes);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDocumentTypesMockMvc.perform(put("/api/document-types")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(documentTypesDTO)))
            .andExpect(status().isCreated());

        // Validate the DocumentTypes in the database
        List<DocumentTypes> documentTypesList = documentTypesRepository.findAll();
        assertThat(documentTypesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDocumentTypes() throws Exception {
        // Initialize the database
        documentTypesRepository.saveAndFlush(documentTypes);
        int databaseSizeBeforeDelete = documentTypesRepository.findAll().size();

        // Get the documentTypes
        restDocumentTypesMockMvc.perform(delete("/api/document-types/{id}", documentTypes.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<DocumentTypes> documentTypesList = documentTypesRepository.findAll();
        assertThat(documentTypesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentTypes.class);
        DocumentTypes documentTypes1 = new DocumentTypes();
        documentTypes1.setId(1L);
        DocumentTypes documentTypes2 = new DocumentTypes();
        documentTypes2.setId(documentTypes1.getId());
        assertThat(documentTypes1).isEqualTo(documentTypes2);
        documentTypes2.setId(2L);
        assertThat(documentTypes1).isNotEqualTo(documentTypes2);
        documentTypes1.setId(null);
        assertThat(documentTypes1).isNotEqualTo(documentTypes2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DocumentTypesDTO.class);
        DocumentTypesDTO documentTypesDTO1 = new DocumentTypesDTO();
        documentTypesDTO1.setId(1L);
        DocumentTypesDTO documentTypesDTO2 = new DocumentTypesDTO();
        assertThat(documentTypesDTO1).isNotEqualTo(documentTypesDTO2);
        documentTypesDTO2.setId(documentTypesDTO1.getId());
        assertThat(documentTypesDTO1).isEqualTo(documentTypesDTO2);
        documentTypesDTO2.setId(2L);
        assertThat(documentTypesDTO1).isNotEqualTo(documentTypesDTO2);
        documentTypesDTO1.setId(null);
        assertThat(documentTypesDTO1).isNotEqualTo(documentTypesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(documentTypesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(documentTypesMapper.fromId(null)).isNull();
    }
}
