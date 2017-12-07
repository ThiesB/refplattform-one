package jhipster.org.service.impl;

import jhipster.org.service.IndustriesService;
import jhipster.org.domain.Industries;
import jhipster.org.repository.IndustriesRepository;
import jhipster.org.service.dto.IndustriesDTO;
import jhipster.org.service.mapper.IndustriesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Industries.
 */
@Service
@Transactional
public class IndustriesServiceImpl implements IndustriesService{

    private final Logger log = LoggerFactory.getLogger(IndustriesServiceImpl.class);

    private final IndustriesRepository industriesRepository;

    private final IndustriesMapper industriesMapper;

    public IndustriesServiceImpl(IndustriesRepository industriesRepository, IndustriesMapper industriesMapper) {
        this.industriesRepository = industriesRepository;
        this.industriesMapper = industriesMapper;
    }

    /**
     * Save a industries.
     *
     * @param industriesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IndustriesDTO save(IndustriesDTO industriesDTO) {
        log.debug("Request to save Industries : {}", industriesDTO);
        Industries industries = industriesMapper.toEntity(industriesDTO);
        industries = industriesRepository.save(industries);
        return industriesMapper.toDto(industries);
    }

    /**
     * Get all the industries.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<IndustriesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Industries");
        return industriesRepository.findAll(pageable)
            .map(industriesMapper::toDto);
    }

    /**
     * Get one industries by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public IndustriesDTO findOne(Long id) {
        log.debug("Request to get Industries : {}", id);
        Industries industries = industriesRepository.findOne(id);
        return industriesMapper.toDto(industries);
    }

    /**
     * Delete the industries by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Industries : {}", id);
        industriesRepository.delete(id);
    }
}
