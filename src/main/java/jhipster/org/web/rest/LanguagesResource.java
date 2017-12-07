package jhipster.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.org.service.LanguagesService;
import jhipster.org.web.rest.errors.BadRequestAlertException;
import jhipster.org.web.rest.util.HeaderUtil;
import jhipster.org.web.rest.util.PaginationUtil;
import jhipster.org.service.dto.LanguagesDTO;
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
 * REST controller for managing Languages.
 */
@RestController
@RequestMapping("/api")
public class LanguagesResource {

    private final Logger log = LoggerFactory.getLogger(LanguagesResource.class);

    private static final String ENTITY_NAME = "languages";

    private final LanguagesService languagesService;

    public LanguagesResource(LanguagesService languagesService) {
        this.languagesService = languagesService;
    }

    /**
     * POST  /languages : Create a new languages.
     *
     * @param languagesDTO the languagesDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new languagesDTO, or with status 400 (Bad Request) if the languages has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/languages")
    @Timed
    public ResponseEntity<LanguagesDTO> createLanguages(@Valid @RequestBody LanguagesDTO languagesDTO) throws URISyntaxException {
        log.debug("REST request to save Languages : {}", languagesDTO);
        if (languagesDTO.getId() != null) {
            throw new BadRequestAlertException("A new languages cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LanguagesDTO result = languagesService.save(languagesDTO);
        return ResponseEntity.created(new URI("/api/languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /languages : Updates an existing languages.
     *
     * @param languagesDTO the languagesDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated languagesDTO,
     * or with status 400 (Bad Request) if the languagesDTO is not valid,
     * or with status 500 (Internal Server Error) if the languagesDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/languages")
    @Timed
    public ResponseEntity<LanguagesDTO> updateLanguages(@Valid @RequestBody LanguagesDTO languagesDTO) throws URISyntaxException {
        log.debug("REST request to update Languages : {}", languagesDTO);
        if (languagesDTO.getId() == null) {
            return createLanguages(languagesDTO);
        }
        LanguagesDTO result = languagesService.save(languagesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, languagesDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /languages : get all the languages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of languages in body
     */
    @GetMapping("/languages")
    @Timed
    public ResponseEntity<List<LanguagesDTO>> getAllLanguages(Pageable pageable) {
        log.debug("REST request to get a page of Languages");
        Page<LanguagesDTO> page = languagesService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/languages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /languages/:id : get the "id" languages.
     *
     * @param id the id of the languagesDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the languagesDTO, or with status 404 (Not Found)
     */
    @GetMapping("/languages/{id}")
    @Timed
    public ResponseEntity<LanguagesDTO> getLanguages(@PathVariable Long id) {
        log.debug("REST request to get Languages : {}", id);
        LanguagesDTO languagesDTO = languagesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(languagesDTO));
    }

    /**
     * DELETE  /languages/:id : delete the "id" languages.
     *
     * @param id the id of the languagesDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/languages/{id}")
    @Timed
    public ResponseEntity<Void> deleteLanguages(@PathVariable Long id) {
        log.debug("REST request to delete Languages : {}", id);
        languagesService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
