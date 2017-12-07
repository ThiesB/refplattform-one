package jhipster.org.service.impl;

import jhipster.org.service.ServiceComponentsService;
import jhipster.org.domain.ServiceComponents;
import jhipster.org.repository.ServiceComponentsRepository;
import jhipster.org.service.dto.ServiceComponentsDTO;
import jhipster.org.service.mapper.ServiceComponentsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing ServiceComponents.
 */
@Service
@Transactional
public class ServiceComponentsServiceImpl implements ServiceComponentsService{

    private final Logger log = LoggerFactory.getLogger(ServiceComponentsServiceImpl.class);

    private final ServiceComponentsRepository serviceComponentsRepository;

    private final ServiceComponentsMapper serviceComponentsMapper;

    public ServiceComponentsServiceImpl(ServiceComponentsRepository serviceComponentsRepository, ServiceComponentsMapper serviceComponentsMapper) {
        this.serviceComponentsRepository = serviceComponentsRepository;
        this.serviceComponentsMapper = serviceComponentsMapper;
    }

    /**
     * Save a serviceComponents.
     *
     * @param serviceComponentsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ServiceComponentsDTO save(ServiceComponentsDTO serviceComponentsDTO) {
        log.debug("Request to save ServiceComponents : {}", serviceComponentsDTO);
        ServiceComponents serviceComponents = serviceComponentsMapper.toEntity(serviceComponentsDTO);
        serviceComponents = serviceComponentsRepository.save(serviceComponents);
        return serviceComponentsMapper.toDto(serviceComponents);
    }

    /**
     * Get all the serviceComponents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ServiceComponentsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceComponents");
        return serviceComponentsRepository.findAll(pageable)
            .map(serviceComponentsMapper::toDto);
    }

    /**
     * Get one serviceComponents by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ServiceComponentsDTO findOne(Long id) {
        log.debug("Request to get ServiceComponents : {}", id);
        ServiceComponents serviceComponents = serviceComponentsRepository.findOne(id);
        return serviceComponentsMapper.toDto(serviceComponents);
    }

    /**
     * Delete the serviceComponents by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceComponents : {}", id);
        serviceComponentsRepository.delete(id);
    }
}
