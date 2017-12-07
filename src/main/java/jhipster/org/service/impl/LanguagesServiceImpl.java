package jhipster.org.service.impl;

import jhipster.org.service.LanguagesService;
import jhipster.org.domain.Languages;
import jhipster.org.repository.LanguagesRepository;
import jhipster.org.service.dto.LanguagesDTO;
import jhipster.org.service.mapper.LanguagesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Languages.
 */
@Service
@Transactional
public class LanguagesServiceImpl implements LanguagesService{

    private final Logger log = LoggerFactory.getLogger(LanguagesServiceImpl.class);

    private final LanguagesRepository languagesRepository;

    private final LanguagesMapper languagesMapper;

    public LanguagesServiceImpl(LanguagesRepository languagesRepository, LanguagesMapper languagesMapper) {
        this.languagesRepository = languagesRepository;
        this.languagesMapper = languagesMapper;
    }

    /**
     * Save a languages.
     *
     * @param languagesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LanguagesDTO save(LanguagesDTO languagesDTO) {
        log.debug("Request to save Languages : {}", languagesDTO);
        Languages languages = languagesMapper.toEntity(languagesDTO);
        languages = languagesRepository.save(languages);
        return languagesMapper.toDto(languages);
    }

    /**
     * Get all the languages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LanguagesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Languages");
        return languagesRepository.findAll(pageable)
            .map(languagesMapper::toDto);
    }

    /**
     * Get one languages by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public LanguagesDTO findOne(Long id) {
        log.debug("Request to get Languages : {}", id);
        Languages languages = languagesRepository.findOne(id);
        return languagesMapper.toDto(languages);
    }

    /**
     * Delete the languages by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Languages : {}", id);
        languagesRepository.delete(id);
    }
}
