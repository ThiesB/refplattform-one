package jhipster.org.service;

import jhipster.org.service.dto.ProjectRolesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ProjectRoles.
 */
public interface ProjectRolesService {

    /**
     * Save a projectRoles.
     *
     * @param projectRolesDTO the entity to save
     * @return the persisted entity
     */
    ProjectRolesDTO save(ProjectRolesDTO projectRolesDTO);

    /**
     * Get all the projectRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ProjectRolesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" projectRoles.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ProjectRolesDTO findOne(Long id);

    /**
     * Delete the "id" projectRoles.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
