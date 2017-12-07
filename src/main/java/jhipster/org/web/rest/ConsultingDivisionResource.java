package jhipster.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.org.service.ConsultingDivisionService;
import jhipster.org.web.rest.errors.BadRequestAlertException;
import jhipster.org.web.rest.util.HeaderUtil;
import jhipster.org.web.rest.util.PaginationUtil;
import jhipster.org.service.dto.ConsultingDivisionDTO;
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
 * REST controller for managing ConsultingDivision.
 */
@RestController
@RequestMapping("/api")
public class ConsultingDivisionResource {

    private final Logger log = LoggerFactory.getLogger(ConsultingDivisionResource.class);

    private static final String ENTITY_NAME = "consultingDivision";

    private final ConsultingDivisionService consultingDivisionService;

    public ConsultingDivisionResource(ConsultingDivisionService consultingDivisionService) {
        this.consultingDivisionService = consultingDivisionService;
    }

    /**
     * POST  /consulting-divisions : Create a new consultingDivision.
     *
     * @param consultingDivisionDTO the consultingDivisionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new consultingDivisionDTO, or with status 400 (Bad Request) if the consultingDivision has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/consulting-divisions")
    @Timed
    public ResponseEntity<ConsultingDivisionDTO> createConsultingDivision(@Valid @RequestBody ConsultingDivisionDTO consultingDivisionDTO) throws URISyntaxException {
        log.debug("REST request to save ConsultingDivision : {}", consultingDivisionDTO);
        if (consultingDivisionDTO.getId() != null) {
            throw new BadRequestAlertException("A new consultingDivision cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ConsultingDivisionDTO result = consultingDivisionService.save(consultingDivisionDTO);
        return ResponseEntity.created(new URI("/api/consulting-divisions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /consulting-divisions : Updates an existing consultingDivision.
     *
     * @param consultingDivisionDTO the consultingDivisionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated consultingDivisionDTO,
     * or with status 400 (Bad Request) if the consultingDivisionDTO is not valid,
     * or with status 500 (Internal Server Error) if the consultingDivisionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/consulting-divisions")
    @Timed
    public ResponseEntity<ConsultingDivisionDTO> updateConsultingDivision(@Valid @RequestBody ConsultingDivisionDTO consultingDivisionDTO) throws URISyntaxException {
        log.debug("REST request to update ConsultingDivision : {}", consultingDivisionDTO);
        if (consultingDivisionDTO.getId() == null) {
            return createConsultingDivision(consultingDivisionDTO);
        }
        ConsultingDivisionDTO result = consultingDivisionService.save(consultingDivisionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, consultingDivisionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /consulting-divisions : get all the consultingDivisions.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of consultingDivisions in body
     */
    @GetMapping("/consulting-divisions")
    @Timed
    public ResponseEntity<List<ConsultingDivisionDTO>> getAllConsultingDivisions(Pageable pageable) {
        log.debug("REST request to get a page of ConsultingDivisions");
        Page<ConsultingDivisionDTO> page = consultingDivisionService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/consulting-divisions");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /consulting-divisions/:id : get the "id" consultingDivision.
     *
     * @param id the id of the consultingDivisionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the consultingDivisionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/consulting-divisions/{id}")
    @Timed
    public ResponseEntity<ConsultingDivisionDTO> getConsultingDivision(@PathVariable Long id) {
        log.debug("REST request to get ConsultingDivision : {}", id);
        ConsultingDivisionDTO consultingDivisionDTO = consultingDivisionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(consultingDivisionDTO));
    }

    /**
     * DELETE  /consulting-divisions/:id : delete the "id" consultingDivision.
     *
     * @param id the id of the consultingDivisionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/consulting-divisions/{id}")
    @Timed
    public ResponseEntity<Void> deleteConsultingDivision(@PathVariable Long id) {
        log.debug("REST request to delete ConsultingDivision : {}", id);
        consultingDivisionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
