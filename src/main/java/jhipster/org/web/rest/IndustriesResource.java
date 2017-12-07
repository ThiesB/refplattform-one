package jhipster.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.org.service.IndustriesService;
import jhipster.org.web.rest.errors.BadRequestAlertException;
import jhipster.org.web.rest.util.HeaderUtil;
import jhipster.org.web.rest.util.PaginationUtil;
import jhipster.org.service.dto.IndustriesDTO;
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
 * REST controller for managing Industries.
 */
@RestController
@RequestMapping("/api")
public class IndustriesResource {

    private final Logger log = LoggerFactory.getLogger(IndustriesResource.class);

    private static final String ENTITY_NAME = "industries";

    private final IndustriesService industriesService;

    public IndustriesResource(IndustriesService industriesService) {
        this.industriesService = industriesService;
    }

    /**
     * POST  /industries : Create a new industries.
     *
     * @param industriesDTO the industriesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new industriesDTO, or with status 400 (Bad Request) if the industries has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/industries")
    @Timed
    public ResponseEntity<IndustriesDTO> createIndustries(@Valid @RequestBody IndustriesDTO industriesDTO) throws URISyntaxException {
        log.debug("REST request to save Industries : {}", industriesDTO);
        if (industriesDTO.getId() != null) {
            throw new BadRequestAlertException("A new industries cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IndustriesDTO result = industriesService.save(industriesDTO);
        return ResponseEntity.created(new URI("/api/industries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /industries : Updates an existing industries.
     *
     * @param industriesDTO the industriesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated industriesDTO,
     * or with status 400 (Bad Request) if the industriesDTO is not valid,
     * or with status 500 (Internal Server Error) if the industriesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/industries")
    @Timed
    public ResponseEntity<IndustriesDTO> updateIndustries(@Valid @RequestBody IndustriesDTO industriesDTO) throws URISyntaxException {
        log.debug("REST request to update Industries : {}", industriesDTO);
        if (industriesDTO.getId() == null) {
            return createIndustries(industriesDTO);
        }
        IndustriesDTO result = industriesService.save(industriesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, industriesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /industries : get all the industries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of industries in body
     */
    @GetMapping("/industries")
    @Timed
    public ResponseEntity<List<IndustriesDTO>> getAllIndustries(Pageable pageable) {
        log.debug("REST request to get a page of Industries");
        Page<IndustriesDTO> page = industriesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/industries");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /industries/:id : get the "id" industries.
     *
     * @param id the id of the industriesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the industriesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/industries/{id}")
    @Timed
    public ResponseEntity<IndustriesDTO> getIndustries(@PathVariable Long id) {
        log.debug("REST request to get Industries : {}", id);
        IndustriesDTO industriesDTO = industriesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(industriesDTO));
    }

    /**
     * DELETE  /industries/:id : delete the "id" industries.
     *
     * @param id the id of the industriesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/industries/{id}")
    @Timed
    public ResponseEntity<Void> deleteIndustries(@PathVariable Long id) {
        log.debug("REST request to delete Industries : {}", id);
        industriesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
