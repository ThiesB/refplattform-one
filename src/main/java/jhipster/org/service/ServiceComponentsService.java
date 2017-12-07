package jhipster.org.service;

import jhipster.org.service.dto.ServiceComponentsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ServiceComponents.
 */
public interface ServiceComponentsService {

    /**
     * Save a serviceComponents.
     *
     * @param serviceComponentsDTO the entity to save
     * @return the persisted entity
     */
    ServiceComponentsDTO save(ServiceComponentsDTO serviceComponentsDTO);

    /**
     * Get all the serviceComponents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ServiceComponentsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" serviceComponents.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ServiceComponentsDTO findOne(Long id);

    /**
     * Delete the "id" serviceComponents.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
