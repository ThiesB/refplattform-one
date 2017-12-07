package jhipster.org.web.rest;

import jhipster.org.RefplattformOneApp;

import jhipster.org.domain.ProjectRoles;
import jhipster.org.repository.ProjectRolesRepository;
import jhipster.org.service.ProjectRolesService;
import jhipster.org.service.dto.ProjectRolesDTO;
import jhipster.org.service.mapper.ProjectRolesMapper;
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
 * Test class for the ProjectRolesResource REST controller.
 *
 * @see ProjectRolesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RefplattformOneApp.class)
public class ProjectRolesResourceIntTest {

    private static final String DEFAULT_ROLE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_ROLE_NAME = "BBBBBBBBBB";

    @Autowired
    private ProjectRolesRepository projectRolesRepository;

    @Autowired
    private ProjectRolesMapper projectRolesMapper;

    @Autowired
    private ProjectRolesService projectRolesService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProjectRolesMockMvc;

    private ProjectRoles projectRoles;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjectRolesResource projectRolesResource = new ProjectRolesResource(projectRolesService);
        this.restProjectRolesMockMvc = MockMvcBuilders.standaloneSetup(projectRolesResource)
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
    public static ProjectRoles createEntity(EntityManager em) {
        ProjectRoles projectRoles = new ProjectRoles()
            .roleName(DEFAULT_ROLE_NAME);
        return projectRoles;
    }

    @Before
    public void initTest() {
        projectRoles = createEntity(em);
    }

    @Test
    @Transactional
    public void createProjectRoles() throws Exception {
        int databaseSizeBeforeCreate = projectRolesRepository.findAll().size();

        // Create the ProjectRoles
        ProjectRolesDTO projectRolesDTO = projectRolesMapper.toDto(projectRoles);
        restProjectRolesMockMvc.perform(post("/api/project-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectRolesDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectRoles in the database
        List<ProjectRoles> projectRolesList = projectRolesRepository.findAll();
        assertThat(projectRolesList).hasSize(databaseSizeBeforeCreate + 1);
        ProjectRoles testProjectRoles = projectRolesList.get(projectRolesList.size() - 1);
        assertThat(testProjectRoles.getRoleName()).isEqualTo(DEFAULT_ROLE_NAME);
    }

    @Test
    @Transactional
    public void createProjectRolesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectRolesRepository.findAll().size();

        // Create the ProjectRoles with an existing ID
        projectRoles.setId(1L);
        ProjectRolesDTO projectRolesDTO = projectRolesMapper.toDto(projectRoles);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectRolesMockMvc.perform(post("/api/project-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectRolesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectRoles in the database
        List<ProjectRoles> projectRolesList = projectRolesRepository.findAll();
        assertThat(projectRolesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkRoleNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = projectRolesRepository.findAll().size();
        // set the field null
        projectRoles.setRoleName(null);

        // Create the ProjectRoles, which fails.
        ProjectRolesDTO projectRolesDTO = projectRolesMapper.toDto(projectRoles);

        restProjectRolesMockMvc.perform(post("/api/project-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectRolesDTO)))
            .andExpect(status().isBadRequest());

        List<ProjectRoles> projectRolesList = projectRolesRepository.findAll();
        assertThat(projectRolesList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProjectRoles() throws Exception {
        // Initialize the database
        projectRolesRepository.saveAndFlush(projectRoles);

        // Get all the projectRolesList
        restProjectRolesMockMvc.perform(get("/api/project-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectRoles.getId().intValue())))
            .andExpect(jsonPath("$.[*].roleName").value(hasItem(DEFAULT_ROLE_NAME.toString())));
    }

    @Test
    @Transactional
    public void getProjectRoles() throws Exception {
        // Initialize the database
        projectRolesRepository.saveAndFlush(projectRoles);

        // Get the projectRoles
        restProjectRolesMockMvc.perform(get("/api/project-roles/{id}", projectRoles.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(projectRoles.getId().intValue()))
            .andExpect(jsonPath("$.roleName").value(DEFAULT_ROLE_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProjectRoles() throws Exception {
        // Get the projectRoles
        restProjectRolesMockMvc.perform(get("/api/project-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProjectRoles() throws Exception {
        // Initialize the database
        projectRolesRepository.saveAndFlush(projectRoles);
        int databaseSizeBeforeUpdate = projectRolesRepository.findAll().size();

        // Update the projectRoles
        ProjectRoles updatedProjectRoles = projectRolesRepository.findOne(projectRoles.getId());
        // Disconnect from session so that the updates on updatedProjectRoles are not directly saved in db
        em.detach(updatedProjectRoles);
        updatedProjectRoles
            .roleName(UPDATED_ROLE_NAME);
        ProjectRolesDTO projectRolesDTO = projectRolesMapper.toDto(updatedProjectRoles);

        restProjectRolesMockMvc.perform(put("/api/project-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectRolesDTO)))
            .andExpect(status().isOk());

        // Validate the ProjectRoles in the database
        List<ProjectRoles> projectRolesList = projectRolesRepository.findAll();
        assertThat(projectRolesList).hasSize(databaseSizeBeforeUpdate);
        ProjectRoles testProjectRoles = projectRolesList.get(projectRolesList.size() - 1);
        assertThat(testProjectRoles.getRoleName()).isEqualTo(UPDATED_ROLE_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingProjectRoles() throws Exception {
        int databaseSizeBeforeUpdate = projectRolesRepository.findAll().size();

        // Create the ProjectRoles
        ProjectRolesDTO projectRolesDTO = projectRolesMapper.toDto(projectRoles);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectRolesMockMvc.perform(put("/api/project-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectRolesDTO)))
            .andExpect(status().isCreated());

        // Validate the ProjectRoles in the database
        List<ProjectRoles> projectRolesList = projectRolesRepository.findAll();
        assertThat(projectRolesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProjectRoles() throws Exception {
        // Initialize the database
        projectRolesRepository.saveAndFlush(projectRoles);
        int databaseSizeBeforeDelete = projectRolesRepository.findAll().size();

        // Get the projectRoles
        restProjectRolesMockMvc.perform(delete("/api/project-roles/{id}", projectRoles.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ProjectRoles> projectRolesList = projectRolesRepository.findAll();
        assertThat(projectRolesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectRoles.class);
        ProjectRoles projectRoles1 = new ProjectRoles();
        projectRoles1.setId(1L);
        ProjectRoles projectRoles2 = new ProjectRoles();
        projectRoles2.setId(projectRoles1.getId());
        assertThat(projectRoles1).isEqualTo(projectRoles2);
        projectRoles2.setId(2L);
        assertThat(projectRoles1).isNotEqualTo(projectRoles2);
        projectRoles1.setId(null);
        assertThat(projectRoles1).isNotEqualTo(projectRoles2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectRolesDTO.class);
        ProjectRolesDTO projectRolesDTO1 = new ProjectRolesDTO();
        projectRolesDTO1.setId(1L);
        ProjectRolesDTO projectRolesDTO2 = new ProjectRolesDTO();
        assertThat(projectRolesDTO1).isNotEqualTo(projectRolesDTO2);
        projectRolesDTO2.setId(projectRolesDTO1.getId());
        assertThat(projectRolesDTO1).isEqualTo(projectRolesDTO2);
        projectRolesDTO2.setId(2L);
        assertThat(projectRolesDTO1).isNotEqualTo(projectRolesDTO2);
        projectRolesDTO1.setId(null);
        assertThat(projectRolesDTO1).isNotEqualTo(projectRolesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(projectRolesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(projectRolesMapper.fromId(null)).isNull();
    }
}
