package jhipster.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.org.service.ProjectRolesService;
import jhipster.org.web.rest.errors.BadRequestAlertException;
import jhipster.org.web.rest.util.HeaderUtil;
import jhipster.org.web.rest.util.PaginationUtil;
import jhipster.org.service.dto.ProjectRolesDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ProjectRoles.
 */
@RestController
@RequestMapping("/api")
public class ProjectRolesResource {

    private final Logger log = LoggerFactory.getLogger(ProjectRolesResource.class);

    private static final String ENTITY_NAME = "projectRoles";

    private final ProjectRolesService projectRolesService;

    public ProjectRolesResource(ProjectRolesService projectRolesService) {
        this.projectRolesService = projectRolesService;
    }

    /**
     * POST  /project-roles : Create a new projectRoles.
     *
     * @param projectRolesDTO the projectRolesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new projectRolesDTO, or with status 400 (Bad Request) if the projectRoles has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/project-roles")
    @Timed
    public ResponseEntity<ProjectRolesDTO> createProjectRoles(@Valid @RequestBody ProjectRolesDTO projectRolesDTO) throws URISyntaxException {
        log.debug("REST request to save ProjectRoles : {}", projectRolesDTO);
        if (projectRolesDTO.getId() != null) {
            throw new BadRequestAlertException("A new projectRoles cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ProjectRolesDTO result = projectRolesService.save(projectRolesDTO);
        return ResponseEntity.created(new URI("/api/project-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /project-roles : Updates an existing projectRoles.
     *
     * @param projectRolesDTO the projectRolesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated projectRolesDTO,
     * or with status 400 (Bad Request) if the projectRolesDTO is not valid,
     * or with status 500 (Internal Server Error) if the projectRolesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/project-roles")
    @Timed
    public ResponseEntity<ProjectRolesDTO> updateProjectRoles(@Valid @RequestBody ProjectRolesDTO projectRolesDTO) throws URISyntaxException {
        log.debug("REST request to update ProjectRoles : {}", projectRolesDTO);
        if (projectRolesDTO.getId() == null) {
            return createProjectRoles(projectRolesDTO);
        }
        ProjectRolesDTO result = projectRolesService.save(projectRolesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, projectRolesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /project-roles : get all the projectRoles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of projectRoles in body
     */
    @GetMapping("/project-roles")
    @Timed
    public ResponseEntity<List<ProjectRolesDTO>> getAllProjectRoles(Pageable pageable) {
        log.debug("REST request to get a page of ProjectRoles");
        Page<ProjectRolesDTO> page = projectRolesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/project-roles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /project-roles/:id : get the "id" projectRoles.
     *
     * @param id the id of the projectRolesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the projectRolesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/project-roles/{id}")
    @Timed
    public ResponseEntity<ProjectRolesDTO> getProjectRoles(@PathVariable Long id) {
        log.debug("REST request to get ProjectRoles : {}", id);
        ProjectRolesDTO projectRolesDTO = projectRolesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(projectRolesDTO));
    }

    /**
     * DELETE  /project-roles/:id : delete the "id" projectRoles.
     *
     * @param id the id of the projectRolesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/project-roles/{id}")
    @Timed
    public ResponseEntity<Void> deleteProjectRoles(@PathVariable Long id) {
        log.debug("REST request to delete ProjectRoles : {}", id);
        projectRolesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
