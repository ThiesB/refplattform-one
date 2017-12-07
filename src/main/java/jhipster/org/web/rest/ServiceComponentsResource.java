package jhipster.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.org.service.ServiceComponentsService;
import jhipster.org.web.rest.errors.BadRequestAlertException;
import jhipster.org.web.rest.util.HeaderUtil;
import jhipster.org.web.rest.util.PaginationUtil;
import jhipster.org.service.dto.ServiceComponentsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ServiceComponents.
 */
@RestController
@RequestMapping("/api")
public class ServiceComponentsResource {

    private final Logger log = LoggerFactory.getLogger(ServiceComponentsResource.class);

    private static final String ENTITY_NAME = "serviceComponents";

    private final ServiceComponentsService serviceComponentsService;

    public ServiceComponentsResource(ServiceComponentsService serviceComponentsService) {
        this.serviceComponentsService = serviceComponentsService;
    }

    /**
     * POST  /service-components : Create a new serviceComponents.
     *
     * @param serviceComponentsDTO the serviceComponentsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new serviceComponentsDTO, or with status 400 (Bad Request) if the serviceComponents has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/service-components")
    @Timed
    public ResponseEntity<ServiceComponentsDTO> createServiceComponents(@Valid @RequestBody ServiceComponentsDTO serviceComponentsDTO) throws URISyntaxException {
        log.debug("REST request to save ServiceComponents : {}", serviceComponentsDTO);
        if (serviceComponentsDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceComponents cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceComponentsDTO result = serviceComponentsService.save(serviceComponentsDTO);
        return ResponseEntity.created(new URI("/api/service-components/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /service-components : Updates an existing serviceComponents.
     *
     * @param serviceComponentsDTO the serviceComponentsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated serviceComponentsDTO,
     * or with status 400 (Bad Request) if the serviceComponentsDTO is not valid,
     * or with status 500 (Internal Server Error) if the serviceComponentsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/service-components")
    @Timed
    public ResponseEntity<ServiceComponentsDTO> updateServiceComponents(@Valid @RequestBody ServiceComponentsDTO serviceComponentsDTO) throws URISyntaxException {
        log.debug("REST request to update ServiceComponents : {}", serviceComponentsDTO);
        if (serviceComponentsDTO.getId() == null) {
            return createServiceComponents(serviceComponentsDTO);
        }
        ServiceComponentsDTO result = serviceComponentsService.save(serviceComponentsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, serviceComponentsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /service-components : get all the serviceComponents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of serviceComponents in body
     */
    @GetMapping("/service-components")
    @Timed
    public ResponseEntity<List<ServiceComponentsDTO>> getAllServiceComponents(Pageable pageable) {
        log.debug("REST request to get a page of ServiceComponents");
        Page<ServiceComponentsDTO> page = serviceComponentsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/service-components");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /service-components/:id : get the "id" serviceComponents.
     *
     * @param id the id of the serviceComponentsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the serviceComponentsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/service-components/{id}")
    @Timed
    public ResponseEntity<ServiceComponentsDTO> getServiceComponents(@PathVariable Long id) {
        log.debug("REST request to get ServiceComponents : {}", id);
        ServiceComponentsDTO serviceComponentsDTO = serviceComponentsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(serviceComponentsDTO));
    }

    /**
     * DELETE  /service-components/:id : delete the "id" serviceComponents.
     *
     * @param id the id of the serviceComponentsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/service-components/{id}")
    @Timed
    public ResponseEntity<Void> deleteServiceComponents(@PathVariable Long id) {
        log.debug("REST request to delete ServiceComponents : {}", id);
        serviceComponentsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
