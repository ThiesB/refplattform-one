package jhipster.org.service;

import jhipster.org.service.dto.DownloadsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Downloads.
 */
public interface DownloadsService {

    /**
     * Save a downloads.
     *
     * @param downloadsDTO the entity to save
     * @return the persisted entity
     */
    DownloadsDTO save(DownloadsDTO downloadsDTO);

    /**
     * Get all the downloads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DownloadsDTO> findAll(Pageable pageable);

    /**
     * Get the "id" downloads.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DownloadsDTO findOne(Long id);

    /**
     * Delete the "id" downloads.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
