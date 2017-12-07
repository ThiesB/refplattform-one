package jhipster.org.service.impl;

import jhipster.org.service.DocumentTypesService;
import jhipster.org.domain.DocumentTypes;
import jhipster.org.repository.DocumentTypesRepository;
import jhipster.org.service.dto.DocumentTypesDTO;
import jhipster.org.service.mapper.DocumentTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing DocumentTypes.
 */
@Service
@Transactional
public class DocumentTypesServiceImpl implements DocumentTypesService{

    private final Logger log = LoggerFactory.getLogger(DocumentTypesServiceImpl.class);

    private final DocumentTypesRepository documentTypesRepository;

    private final DocumentTypesMapper documentTypesMapper;

    public DocumentTypesServiceImpl(DocumentTypesRepository documentTypesRepository, DocumentTypesMapper documentTypesMapper) {
        this.documentTypesRepository = documentTypesRepository;
        this.documentTypesMapper = documentTypesMapper;
    }

    /**
     * Save a documentTypes.
     *
     * @param documentTypesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DocumentTypesDTO save(DocumentTypesDTO documentTypesDTO) {
        log.debug("Request to save DocumentTypes : {}", documentTypesDTO);
        DocumentTypes documentTypes = documentTypesMapper.toEntity(documentTypesDTO);
        documentTypes = documentTypesRepository.save(documentTypes);
        return documentTypesMapper.toDto(documentTypes);
    }

    /**
     * Get all the documentTypes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentTypesDTO> findAll() {
        log.debug("Request to get all DocumentTypes");
        return documentTypesRepository.findAll().stream()
            .map(documentTypesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one documentTypes by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DocumentTypesDTO findOne(Long id) {
        log.debug("Request to get DocumentTypes : {}", id);
        DocumentTypes documentTypes = documentTypesRepository.findOne(id);
        return documentTypesMapper.toDto(documentTypes);
    }

    /**
     * Delete the documentTypes by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DocumentTypes : {}", id);
        documentTypesRepository.delete(id);
    }
}
