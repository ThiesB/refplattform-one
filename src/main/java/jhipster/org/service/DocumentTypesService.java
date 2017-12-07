package jhipster.org.service;

import jhipster.org.service.dto.DocumentTypesDTO;
import java.util.List;

/**
 * Service Interface for managing DocumentTypes.
 */
public interface DocumentTypesService {

    /**
     * Save a documentTypes.
     *
     * @param documentTypesDTO the entity to save
     * @return the persisted entity
     */
    DocumentTypesDTO save(DocumentTypesDTO documentTypesDTO);

    /**
     * Get all the documentTypes.
     *
     * @return the list of entities
     */
    List<DocumentTypesDTO> findAll();

    /**
     * Get the "id" documentTypes.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DocumentTypesDTO findOne(Long id);

    /**
     * Delete the "id" documentTypes.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
