package jhipster.org.service.impl;

import jhipster.org.service.CustomerReferencesService;
import jhipster.org.domain.CustomerReferences;
import jhipster.org.repository.CustomerReferencesRepository;
import jhipster.org.service.dto.CustomerReferencesDTO;
import jhipster.org.service.mapper.CustomerReferencesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing CustomerReferences.
 */
@Service
@Transactional
public class CustomerReferencesServiceImpl implements CustomerReferencesService{

    private final Logger log = LoggerFactory.getLogger(CustomerReferencesServiceImpl.class);

    private final CustomerReferencesRepository customerReferencesRepository;

    private final CustomerReferencesMapper customerReferencesMapper;

    public CustomerReferencesServiceImpl(CustomerReferencesRepository customerReferencesRepository, CustomerReferencesMapper customerReferencesMapper) {
        this.customerReferencesRepository = customerReferencesRepository;
        this.customerReferencesMapper = customerReferencesMapper;
    }

    /**
     * Save a customerReferences.
     *
     * @param customerReferencesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CustomerReferencesDTO save(CustomerReferencesDTO customerReferencesDTO) {
        log.debug("Request to save CustomerReferences : {}", customerReferencesDTO);
        CustomerReferences customerReferences = customerReferencesMapper.toEntity(customerReferencesDTO);
        customerReferences = customerReferencesRepository.save(customerReferences);
        return customerReferencesMapper.toDto(customerReferences);
    }

    /**
     * Get all the customerReferences.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerReferencesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomerReferences");
        return customerReferencesRepository.findAll(pageable)
            .map(customerReferencesMapper::toDto);
    }

    /**
     * Get one customerReferences by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CustomerReferencesDTO findOne(Long id) {
        log.debug("Request to get CustomerReferences : {}", id);
        CustomerReferences customerReferences = customerReferencesRepository.findOneWithEagerRelationships(id);
        return customerReferencesMapper.toDto(customerReferences);
    }

    /**
     * Delete the customerReferences by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomerReferences : {}", id);
        customerReferencesRepository.delete(id);
    }
}
