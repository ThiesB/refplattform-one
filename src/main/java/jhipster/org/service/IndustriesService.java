package jhipster.org.service;

import jhipster.org.service.dto.IndustriesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Industries.
 */
public interface IndustriesService {

    /**
     * Save a industries.
     *
     * @param industriesDTO the entity to save
     * @return the persisted entity
     */
    IndustriesDTO save(IndustriesDTO industriesDTO);

    /**
     * Get all the industries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<IndustriesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" industries.
     *
     * @param id the id of the entity
     * @return the entity
     */
    IndustriesDTO findOne(Long id);

    /**
     * Delete the "id" industries.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
