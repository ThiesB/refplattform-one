package jhipster.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.org.service.DocumentTypesService;
import jhipster.org.web.rest.errors.BadRequestAlertException;
import jhipster.org.web.rest.util.HeaderUtil;
import jhipster.org.service.dto.DocumentTypesDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing DocumentTypes.
 */
@RestController
@RequestMapping("/api")
public class DocumentTypesResource {

    private final Logger log = LoggerFactory.getLogger(DocumentTypesResource.class);

    private static final String ENTITY_NAME = "documentTypes";

    private final DocumentTypesService documentTypesService;

    public DocumentTypesResource(DocumentTypesService documentTypesService) {
        this.documentTypesService = documentTypesService;
    }

    /**
     * POST  /document-types : Create a new documentTypes.
     *
     * @param documentTypesDTO the documentTypesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new documentTypesDTO, or with status 400 (Bad Request) if the documentTypes has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/document-types")
    @Timed
    public ResponseEntity<DocumentTypesDTO> createDocumentTypes(@Valid @RequestBody DocumentTypesDTO documentTypesDTO) throws URISyntaxException {
        log.debug("REST request to save DocumentTypes : {}", documentTypesDTO);
        if (documentTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new documentTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DocumentTypesDTO result = documentTypesService.save(documentTypesDTO);
        return ResponseEntity.created(new URI("/api/document-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /document-types : Updates an existing documentTypes.
     *
     * @param documentTypesDTO the documentTypesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated documentTypesDTO,
     * or with status 400 (Bad Request) if the documentTypesDTO is not valid,
     * or with status 500 (Internal Server Error) if the documentTypesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/document-types")
    @Timed
    public ResponseEntity<DocumentTypesDTO> updateDocumentTypes(@Valid @RequestBody DocumentTypesDTO documentTypesDTO) throws URISyntaxException {
        log.debug("REST request to update DocumentTypes : {}", documentTypesDTO);
        if (documentTypesDTO.getId() == null) {
            return createDocumentTypes(documentTypesDTO);
        }
        DocumentTypesDTO result = documentTypesService.save(documentTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, documentTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /document-types : get all the documentTypes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of documentTypes in body
     */
    @GetMapping("/document-types")
    @Timed
    public List<DocumentTypesDTO> getAllDocumentTypes() {
        log.debug("REST request to get all DocumentTypes");
        return documentTypesService.findAll();
        }

    /**
     * GET  /document-types/:id : get the "id" documentTypes.
     *
     * @param id the id of the documentTypesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the documentTypesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/document-types/{id}")
    @Timed
    public ResponseEntity<DocumentTypesDTO> getDocumentTypes(@PathVariable Long id) {
        log.debug("REST request to get DocumentTypes : {}", id);
        DocumentTypesDTO documentTypesDTO = documentTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(documentTypesDTO));
    }

    /**
     * DELETE  /document-types/:id : delete the "id" documentTypes.
     *
     * @param id the id of the documentTypesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/document-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteDocumentTypes(@PathVariable Long id) {
        log.debug("REST request to delete DocumentTypes : {}", id);
        documentTypesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
