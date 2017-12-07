package jhipster.org.service;

import jhipster.org.service.dto.ConsultingDivisionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing ConsultingDivision.
 */
public interface ConsultingDivisionService {

    /**
     * Save a consultingDivision.
     *
     * @param consultingDivisionDTO the entity to save
     * @return the persisted entity
     */
    ConsultingDivisionDTO save(ConsultingDivisionDTO consultingDivisionDTO);

    /**
     * Get all the consultingDivisions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<ConsultingDivisionDTO> findAll(Pageable pageable);

    /**
     * Get the "id" consultingDivision.
     *
     * @param id the id of the entity
     * @return the entity
     */
    ConsultingDivisionDTO findOne(Long id);

    /**
     * Delete the "id" consultingDivision.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
