package jhipster.org.web.rest;

import com.codahale.metrics.annotation.Timed;
// import com.sun.el.stream.Optional;
import jhipster.org.domain.CustomerReferences;
import jhipster.org.repository.CustomerReferencesRepository;
import jhipster.org.service.CustomerReferencesService;
import jhipster.org.web.rest.errors.BadRequestAlertException;
import jhipster.org.web.rest.util.HeaderUtil;
import jhipster.org.web.rest.util.PaginationUtil;
import jhipster.org.service.dto.CustomerReferencesDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * REST controller for managing CustomerReferences.
 */
@RestController
@RequestMapping("/api")
public class CustomerReferencesResource {

    private final Logger log = LoggerFactory.getLogger(CustomerReferencesResource.class);

    private static final String ENTITY_NAME = "customerReferences";

    private final CustomerReferencesService customerReferencesService;

    @Autowired
    private final CustomerReferencesRepository customerReferencesRepository;

    public CustomerReferencesResource(CustomerReferencesService customerReferencesService, CustomerReferencesRepository customerReferencesRepository) {
        this.customerReferencesService = customerReferencesService;
        this.customerReferencesRepository = customerReferencesRepository;
    }

    /**
     * POST  /customer-references : Create a new customerReferences.
     *
     * @param customerReferencesDTO the customerReferencesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new customerReferencesDTO, or with status 400 (Bad Request) if the customerReferences has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/customer-references")
    @Timed
    public ResponseEntity<CustomerReferencesDTO> createCustomerReferences(@Valid @RequestBody CustomerReferencesDTO customerReferencesDTO) throws URISyntaxException {
        log.debug("REST request to save CustomerReferences : {}", customerReferencesDTO);
        if (customerReferencesDTO.getId() != null) {
            throw new BadRequestAlertException("A new customerReferences cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CustomerReferencesDTO result = customerReferencesService.save(customerReferencesDTO);
        return ResponseEntity.created(new URI("/api/customer-references/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /customer-references : Updates an existing customerReferences.
     *
     * @param customerReferencesDTO the customerReferencesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated customerReferencesDTO,
     * or with status 400 (Bad Request) if the customerReferencesDTO is not valid,
     * or with status 500 (Internal Server Error) if the customerReferencesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/customer-references")
    @Timed
    public ResponseEntity<CustomerReferencesDTO> updateCustomerReferences(@Valid @RequestBody CustomerReferencesDTO customerReferencesDTO) throws URISyntaxException {
        log.debug("REST request to update CustomerReferences : {}", customerReferencesDTO);
        if (customerReferencesDTO.getId() == null) {
            return createCustomerReferences(customerReferencesDTO);
        }
        CustomerReferencesDTO result = customerReferencesService.save(customerReferencesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, customerReferencesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /customer-references : get all the customerReferences.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of customerReferences in body
     */
    @GetMapping("/customer-references")
    @Timed
    public ResponseEntity<List<CustomerReferencesDTO>> getAllCustomerReferences(Pageable pageable) {
        log.debug("REST request to get a page of CustomerReferences");
        Page<CustomerReferencesDTO> page = customerReferencesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/customer-references");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /customer-references/:id : get the "id" customerReferences.
     *
     * @param id the id of the customerReferencesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the customerReferencesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/customer-references/{id}")
    @Timed
    public ResponseEntity<CustomerReferencesDTO> getCustomerReferences(@PathVariable Long id) {
        log.debug("REST request to get CustomerReferences : {}", id);
        CustomerReferencesDTO customerReferencesDTO = customerReferencesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerReferencesDTO));
    }

    /**
     * DELETE  /customer-references/:id : delete the "id" customerReferences.
     *
     * @param id the id of the customerReferencesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/customer-references/{id}")
    @Timed
    public ResponseEntity<Void> deleteCustomerReferences(@PathVariable Long id) {
        log.debug("REST request to delete CustomerReferences : {}", id);
        customerReferencesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

/*    @RequestMapping(value ="/customer-references/{titel}", method = RequestMethod.GET )
    public ResponseEntity getCustomerReferenceByTitel(@PathVariable String titel){
        return new ResponseEntity<List<CustomerReferences>>(this.customerReferencesRepository.findByTitelContaining(titel), HttpStatus.OK);
    }*/

    @GetMapping("/customer-references/search/{titel}")
    @Timed
    public ResponseEntity<CustomerReferences> getCustomerReferencesById(@PathVariable String titel) {
        log.debug("REST request to get CustomerReferences : {}", titel);
        CustomerReferences customerReferences = customerReferencesRepository.findByTitelContaining(titel);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(customerReferences));
    }
}
