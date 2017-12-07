package jhipster.org.service;

import jhipster.org.service.dto.LanguagesDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Languages.
 */
public interface LanguagesService {

    /**
     * Save a languages.
     *
     * @param languagesDTO the entity to save
     * @return the persisted entity
     */
    LanguagesDTO save(LanguagesDTO languagesDTO);

    /**
     * Get all the languages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<LanguagesDTO> findAll(Pageable pageable);

    /**
     * Get the "id" languages.
     *
     * @param id the id of the entity
     * @return the entity
     */
    LanguagesDTO findOne(Long id);

    /**
     * Delete the "id" languages.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
