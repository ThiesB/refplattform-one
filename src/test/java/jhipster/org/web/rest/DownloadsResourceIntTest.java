package jhipster.org.web.rest;

import jhipster.org.RefplattformOneApp;

import jhipster.org.domain.Downloads;
import jhipster.org.repository.DownloadsRepository;
import jhipster.org.service.DownloadsService;
import jhipster.org.service.dto.DownloadsDTO;
import jhipster.org.service.mapper.DownloadsMapper;
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
 * Test class for the DownloadsResource REST controller.
 *
 * @see DownloadsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefplattformOneApp.class)
public class DownloadsResourceIntTest {

    private static final String DEFAULT_TITEL = "AAAAAAAAAA";
    private static final String UPDATED_TITEL = "BBBBBBBBBB";

    private static final String DEFAULT_BEREICH = "AAAAAAAAAA";
    private static final String UPDATED_BEREICH = "BBBBBBBBBB";

    private static final String DEFAULT_ANLAGE_URL = "AAAAAAAAAA";
    private static final String UPDATED_ANLAGE_URL = "BBBBBBBBBB";

    @Autowired
    private DownloadsRepository downloadsRepository;

    @Autowired
    private DownloadsMapper downloadsMapper;

    @Autowired
    private DownloadsService downloadsService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDownloadsMockMvc;

    private Downloads downloads;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DownloadsResource downloadsResource = new DownloadsResource(downloadsService);
        this.restDownloadsMockMvc = MockMvcBuilders.standaloneSetup(downloadsResource)
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
    public static Downloads createEntity(EntityManager em) {
        Downloads downloads = new Downloads()
            .titel(DEFAULT_TITEL)
            .bereich(DEFAULT_BEREICH)
            .anlageURL(DEFAULT_ANLAGE_URL);
        return downloads;
    }

    @Before
    public void initTest() {
        downloads = createEntity(em);
    }

    @Test
    @Transactional
    public void createDownloads() throws Exception {
        int databaseSizeBeforeCreate = downloadsRepository.findAll().size();

        // Create the Downloads
        DownloadsDTO downloadsDTO = downloadsMapper.toDto(downloads);
        restDownloadsMockMvc.perform(post("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadsDTO)))
            .andExpect(status().isCreated());

        // Validate the Downloads in the database
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeCreate + 1);
        Downloads testDownloads = downloadsList.get(downloadsList.size() - 1);
        assertThat(testDownloads.getTitel()).isEqualTo(DEFAULT_TITEL);
        assertThat(testDownloads.getBereich()).isEqualTo(DEFAULT_BEREICH);
        assertThat(testDownloads.getAnlageURL()).isEqualTo(DEFAULT_ANLAGE_URL);
    }

    @Test
    @Transactional
    public void createDownloadsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = downloadsRepository.findAll().size();

        // Create the Downloads with an existing ID
        downloads.setId(1L);
        DownloadsDTO downloadsDTO = downloadsMapper.toDto(downloads);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDownloadsMockMvc.perform(post("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Downloads in the database
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTitelIsRequired() throws Exception {
        int databaseSizeBeforeTest = downloadsRepository.findAll().size();
        // set the field null
        downloads.setTitel(null);

        // Create the Downloads, which fails.
        DownloadsDTO downloadsDTO = downloadsMapper.toDto(downloads);

        restDownloadsMockMvc.perform(post("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadsDTO)))
            .andExpect(status().isBadRequest());

        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAnlageURLIsRequired() throws Exception {
        int databaseSizeBeforeTest = downloadsRepository.findAll().size();
        // set the field null
        downloads.setAnlageURL(null);

        // Create the Downloads, which fails.
        DownloadsDTO downloadsDTO = downloadsMapper.toDto(downloads);

        restDownloadsMockMvc.perform(post("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadsDTO)))
            .andExpect(status().isBadRequest());

        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDownloads() throws Exception {
        // Initialize the database
        downloadsRepository.saveAndFlush(downloads);

        // Get all the downloadsList
        restDownloadsMockMvc.perform(get("/api/downloads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(downloads.getId().intValue())))
            .andExpect(jsonPath("$.[*].titel").value(hasItem(DEFAULT_TITEL.toString())))
            .andExpect(jsonPath("$.[*].bereich").value(hasItem(DEFAULT_BEREICH.toString())))
            .andExpect(jsonPath("$.[*].anlageURL").value(hasItem(DEFAULT_ANLAGE_URL.toString())));
    }

    @Test
    @Transactional
    public void getDownloads() throws Exception {
        // Initialize the database
        downloadsRepository.saveAndFlush(downloads);

        // Get the downloads
        restDownloadsMockMvc.perform(get("/api/downloads/{id}", downloads.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(downloads.getId().intValue()))
            .andExpect(jsonPath("$.titel").value(DEFAULT_TITEL.toString()))
            .andExpect(jsonPath("$.bereich").value(DEFAULT_BEREICH.toString()))
            .andExpect(jsonPath("$.anlageURL").value(DEFAULT_ANLAGE_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDownloads() throws Exception {
        // Get the downloads
        restDownloadsMockMvc.perform(get("/api/downloads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDownloads() throws Exception {
        // Initialize the database
        downloadsRepository.saveAndFlush(downloads);
        int databaseSizeBeforeUpdate = downloadsRepository.findAll().size();

        // Update the downloads
        Downloads updatedDownloads = downloadsRepository.findOne(downloads.getId());
        // Disconnect from session so that the updates on updatedDownloads are not directly saved in db
        em.detach(updatedDownloads);
        updatedDownloads
            .titel(UPDATED_TITEL)
            .bereich(UPDATED_BEREICH)
            .anlageURL(UPDATED_ANLAGE_URL);
        DownloadsDTO downloadsDTO = downloadsMapper.toDto(updatedDownloads);

        restDownloadsMockMvc.perform(put("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadsDTO)))
            .andExpect(status().isOk());

        // Validate the Downloads in the database
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeUpdate);
        Downloads testDownloads = downloadsList.get(downloadsList.size() - 1);
        assertThat(testDownloads.getTitel()).isEqualTo(UPDATED_TITEL);
        assertThat(testDownloads.getBereich()).isEqualTo(UPDATED_BEREICH);
        assertThat(testDownloads.getAnlageURL()).isEqualTo(UPDATED_ANLAGE_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingDownloads() throws Exception {
        int databaseSizeBeforeUpdate = downloadsRepository.findAll().size();

        // Create the Downloads
        DownloadsDTO downloadsDTO = downloadsMapper.toDto(downloads);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDownloadsMockMvc.perform(put("/api/downloads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(downloadsDTO)))
            .andExpect(status().isCreated());

        // Validate the Downloads in the database
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDownloads() throws Exception {
        // Initialize the database
        downloadsRepository.saveAndFlush(downloads);
        int databaseSizeBeforeDelete = downloadsRepository.findAll().size();

        // Get the downloads
        restDownloadsMockMvc.perform(delete("/api/downloads/{id}", downloads.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Downloads> downloadsList = downloadsRepository.findAll();
        assertThat(downloadsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Downloads.class);
        Downloads downloads1 = new Downloads();
        downloads1.setId(1L);
        Downloads downloads2 = new Downloads();
        downloads2.setId(downloads1.getId());
        assertThat(downloads1).isEqualTo(downloads2);
        downloads2.setId(2L);
        assertThat(downloads1).isNotEqualTo(downloads2);
        downloads1.setId(null);
        assertThat(downloads1).isNotEqualTo(downloads2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DownloadsDTO.class);
        DownloadsDTO downloadsDTO1 = new DownloadsDTO();
        downloadsDTO1.setId(1L);
        DownloadsDTO downloadsDTO2 = new DownloadsDTO();
        assertThat(downloadsDTO1).isNotEqualTo(downloadsDTO2);
        downloadsDTO2.setId(downloadsDTO1.getId());
        assertThat(downloadsDTO1).isEqualTo(downloadsDTO2);
        downloadsDTO2.setId(2L);
        assertThat(downloadsDTO1).isNotEqualTo(downloadsDTO2);
        downloadsDTO1.setId(null);
        assertThat(downloadsDTO1).isNotEqualTo(downloadsDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(downloadsMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(downloadsMapper.fromId(null)).isNull();
    }
}
