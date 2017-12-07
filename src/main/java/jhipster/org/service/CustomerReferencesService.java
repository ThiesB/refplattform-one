package jhipster.org.service;

import jhipster.org.service.dto.CustomerReferencesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CustomerReferences.
 */
public interface CustomerReferencesService {

    /**
     * Save a customerReferences.
     *
     * @param customerReferencesDTO the entity to save
     * @return the persisted entity
     */
    CustomerReferencesDTO save(CustomerReferencesDTO customerReferencesDTO);

    /**
     * Get all the customerReferences.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<CustomerReferencesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" customerReferences.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CustomerReferencesDTO findOne(Long id);

    /**
     * Delete the "id" customerReferences.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
