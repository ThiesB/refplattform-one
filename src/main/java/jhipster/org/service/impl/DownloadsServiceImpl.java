package jhipster.org.service.impl;

import jhipster.org.service.DownloadsService;
import jhipster.org.domain.Downloads;
import jhipster.org.repository.DownloadsRepository;
import jhipster.org.service.dto.DownloadsDTO;
import jhipster.org.service.mapper.DownloadsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Downloads.
 */
@Service
@Transactional
public class DownloadsServiceImpl implements DownloadsService{

    private final Logger log = LoggerFactory.getLogger(DownloadsServiceImpl.class);

    private final DownloadsRepository downloadsRepository;

    private final DownloadsMapper downloadsMapper;

    public DownloadsServiceImpl(DownloadsRepository downloadsRepository, DownloadsMapper downloadsMapper) {
        this.downloadsRepository = downloadsRepository;
        this.downloadsMapper = downloadsMapper;
    }

    /**
     * Save a downloads.
     *
     * @param downloadsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DownloadsDTO save(DownloadsDTO downloadsDTO) {
        log.debug("Request to save Downloads : {}", downloadsDTO);
        Downloads downloads = downloadsMapper.toEntity(downloadsDTO);
        downloads = downloadsRepository.save(downloads);
        return downloadsMapper.toDto(downloads);
    }

    /**
     * Get all the downloads.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DownloadsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Downloads");
        return downloadsRepository.findAll(pageable)
            .map(downloadsMapper::toDto);
    }

    /**
     * Get one downloads by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DownloadsDTO findOne(Long id) {
        log.debug("Request to get Downloads : {}", id);
        Downloads downloads = downloadsRepository.findOne(id);
        return downloadsMapper.toDto(downloads);
    }

    /**
     * Delete the downloads by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Downloads : {}", id);
        downloadsRepository.delete(id);
    }
}
