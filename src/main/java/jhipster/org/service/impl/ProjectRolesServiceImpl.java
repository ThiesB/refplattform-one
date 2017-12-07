package jhipster.org.service.impl;

import jhipster.org.service.ProjectRolesService;
import jhipster.org.domain.ProjectRoles;
import jhipster.org.repository.ProjectRolesRepository;
import jhipster.org.service.dto.ProjectRolesDTO;
import jhipster.org.service.mapper.ProjectRolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ProjectRoles.
 */
@Service
@Transactional
public class ProjectRolesServiceImpl implements ProjectRolesService{

    private final Logger log = LoggerFactory.getLogger(ProjectRolesServiceImpl.class);

    private final ProjectRolesRepository projectRolesRepository;

    private final ProjectRolesMapper projectRolesMapper;

    public ProjectRolesServiceImpl(ProjectRolesRepository projectRolesRepository, ProjectRolesMapper projectRolesMapper) {
        this.projectRolesRepository = projectRolesRepository;
        this.projectRolesMapper = projectRolesMapper;
    }

    /**
     * Save a projectRoles.
     *
     * @param projectRolesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProjectRolesDTO save(ProjectRolesDTO projectRolesDTO) {
        log.debug("Request to save ProjectRoles : {}", projectRolesDTO);
        ProjectRoles projectRoles = projectRolesMapper.toEntity(projectRolesDTO);
        projectRoles = projectRolesRepository.save(projectRoles);
        return projectRolesMapper.toDto(projectRoles);
    }

    /**
     * Get all the projectRoles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProjectRolesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ProjectRoles");
        return projectRolesRepository.findAll(pageable)
            .map(projectRolesMapper::toDto);
    }

    /**
     * Get one projectRoles by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProjectRolesDTO findOne(Long id) {
        log.debug("Request to get ProjectRoles : {}", id);
        ProjectRoles projectRoles = projectRolesRepository.findOne(id);
        return projectRolesMapper.toDto(projectRoles);
    }

    /**
     * Delete the projectRoles by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProjectRoles : {}", id);
        projectRolesRepository.delete(id);
    }
}
