package jhipster.org.web.rest;

import jhipster.org.RefplattformOneApp;

import jhipster.org.domain.Languages;
import jhipster.org.repository.LanguagesRepository;
import jhipster.org.service.LanguagesService;
import jhipster.org.service.dto.LanguagesDTO;
import jhipster.org.service.mapper.LanguagesMapper;
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
 * Test class for the LanguagesResource REST controller.
 *
 * @see LanguagesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefplattformOneApp.class)
public class LanguagesResourceIntTest {

    private static final String DEFAULT_LANGUAGE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_NAME = "BBBBBBBBBB";

    @Autowired
    private LanguagesRepository languagesRepository;

    @Autowired
    private LanguagesMapper languagesMapper;

    @Autowired
    private LanguagesService languagesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLanguagesMockMvc;

    private Languages languages;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LanguagesResource languagesResource = new LanguagesResource(languagesService);
        this.restLanguagesMockMvc = MockMvcBuilders.standaloneSetup(languagesResource)
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
    public static Languages createEntity(EntityManager em) {
        Languages languages = new Languages()
            .languageName(DEFAULT_LANGUAGE_NAME);
        return languages;
    }

    @Before
    public void initTest() {
        languages = createEntity(em);
    }

    @Test
    @Transactional
    public void createLanguages() throws Exception {
        int databaseSizeBeforeCreate = languagesRepository.findAll().size();

        // Create the Languages
        LanguagesDTO languagesDTO = languagesMapper.toDto(languages);
        restLanguagesMockMvc.perform(post("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(languagesDTO)))
            .andExpect(status().isCreated());

        // Validate the Languages in the database
        List<Languages> languagesList = languagesRepository.findAll();
        assertThat(languagesList).hasSize(databaseSizeBeforeCreate + 1);
        Languages testLanguages = languagesList.get(languagesList.size() - 1);
        assertThat(testLanguages.getLanguageName()).isEqualTo(DEFAULT_LANGUAGE_NAME);
    }

    @Test
    @Transactional
    public void createLanguagesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = languagesRepository.findAll().size();

        // Create the Languages with an existing ID
        languages.setId(1L);
        LanguagesDTO languagesDTO = languagesMapper.toDto(languages);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLanguagesMockMvc.perform(post("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(languagesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Languages in the database
        List<Languages> languagesList = languagesRepository.findAll();
        assertThat(languagesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkLanguageNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = languagesRepository.findAll().size();
        // set the field null
        languages.setLanguageName(null);

        // Create the Languages, which fails.
        LanguagesDTO languagesDTO = languagesMapper.toDto(languages);

        restLanguagesMockMvc.perform(post("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(languagesDTO)))
            .andExpect(status().isBadRequest());

        List<Languages> languagesList = languagesRepository.findAll();
        assertThat(languagesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllLanguages() throws Exception {
        // Initialize the database
        languagesRepository.saveAndFlush(languages);

        // Get all the languagesList
        restLanguagesMockMvc.perform(get("/api/languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(languages.getId().intValue())))
            .andExpect(jsonPath("$.[*].languageName").value(hasItem(DEFAULT_LANGUAGE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getLanguages() throws Exception {
        // Initialize the database
        languagesRepository.saveAndFlush(languages);

        // Get the languages
        restLanguagesMockMvc.perform(get("/api/languages/{id}", languages.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(languages.getId().intValue()))
            .andExpect(jsonPath("$.languageName").value(DEFAULT_LANGUAGE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingLanguages() throws Exception {
        // Get the languages
        restLanguagesMockMvc.perform(get("/api/languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLanguages() throws Exception {
        // Initialize the database
        languagesRepository.saveAndFlush(languages);
        int databaseSizeBeforeUpdate = languagesRepository.findAll().size();

        // Update the languages
        Languages updatedLanguages = languagesRepository.findOne(languages.getId());
        // Disconnect from session so that the updates on updatedLanguages are not directly saved in db
        em.detach(updatedLanguages);
        updatedLanguages
            .languageName(UPDATED_LANGUAGE_NAME);
        LanguagesDTO languagesDTO = languagesMapper.toDto(updatedLanguages);

        restLanguagesMockMvc.perform(put("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(languagesDTO)))
            .andExpect(status().isOk());

        // Validate the Languages in the database
        List<Languages> languagesList = languagesRepository.findAll();
        assertThat(languagesList).hasSize(databaseSizeBeforeUpdate);
        Languages testLanguages = languagesList.get(languagesList.size() - 1);
        assertThat(testLanguages.getLanguageName()).isEqualTo(UPDATED_LANGUAGE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingLanguages() throws Exception {
        int databaseSizeBeforeUpdate = languagesRepository.findAll().size();

        // Create the Languages
        LanguagesDTO languagesDTO = languagesMapper.toDto(languages);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLanguagesMockMvc.perform(put("/api/languages")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(languagesDTO)))
            .andExpect(status().isCreated());

        // Validate the Languages in the database
        List<Languages> languagesList = languagesRepository.findAll();
        assertThat(languagesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLanguages() throws Exception {
        // Initialize the database
        languagesRepository.saveAndFlush(languages);
        int databaseSizeBeforeDelete = languagesRepository.findAll().size();

        // Get the languages
        restLanguagesMockMvc.perform(delete("/api/languages/{id}", languages.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Languages> languagesList = languagesRepository.findAll();
        assertThat(languagesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Languages.class);
        Languages languages1 = new Languages();
        languages1.setId(1L);
        Languages languages2 = new Languages();
        languages2.setId(languages1.getId());
        assertThat(languages1).isEqualTo(languages2);
        languages2.setId(2L);
        assertThat(languages1).isNotEqualTo(languages2);
        languages1.setId(null);
        assertThat(languages1).isNotEqualTo(languages2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LanguagesDTO.class);
        LanguagesDTO languagesDTO1 = new LanguagesDTO();
        languagesDTO1.setId(1L);
        LanguagesDTO languagesDTO2 = new LanguagesDTO();
        assertThat(languagesDTO1).isNotEqualTo(languagesDTO2);
        languagesDTO2.setId(languagesDTO1.getId());
        assertThat(languagesDTO1).isEqualTo(languagesDTO2);
        languagesDTO2.setId(2L);
        assertThat(languagesDTO1).isNotEqualTo(languagesDTO2);
        languagesDTO1.setId(null);
        assertThat(languagesDTO1).isNotEqualTo(languagesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(languagesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(languagesMapper.fromId(null)).isNull();
    }
}
