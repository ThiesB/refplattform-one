package jhipster.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import jhipster.org.service.DownloadsService;
import jhipster.org.web.rest.errors.BadRequestAlertException;
import jhipster.org.web.rest.util.HeaderUtil;
import jhipster.org.web.rest.util.PaginationUtil;
import jhipster.org.service.dto.DownloadsDTO;
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
 * REST controller for managing Downloads.
 */
@RestController
@RequestMapping("/api")
public class DownloadsResource {

    private final Logger log = LoggerFactory.getLogger(DownloadsResource.class);

    private static final String ENTITY_NAME = "downloads";

    private final DownloadsService downloadsService;

    public DownloadsResource(DownloadsService downloadsService) {
        this.downloadsService = downloadsService;
    }

    /**
     * POST  /downloads : Create a new downloads.
     *
     * @param downloadsDTO the downloadsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new downloadsDTO, or with status 400 (Bad Request) if the downloads has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/downloads")
    @Timed
    public ResponseEntity<DownloadsDTO> createDownloads(@Valid @RequestBody DownloadsDTO downloadsDTO) throws URISyntaxException {
        log.debug("REST request to save Downloads : {}", downloadsDTO);
        if (downloadsDTO.getId() != null) {
            throw new BadRequestAlertException("A new downloads cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DownloadsDTO result = downloadsService.save(downloadsDTO);
        return ResponseEntity.created(new URI("/api/downloads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /downloads : Updates an existing downloads.
     *
     * @param downloadsDTO the downloadsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated downloadsDTO,
     * or with status 400 (Bad Request) if the downloadsDTO is not valid,
     * or with status 500 (Internal Server Error) if the downloadsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/downloads")
    @Timed
    public ResponseEntity<DownloadsDTO> updateDownloads(@Valid @RequestBody DownloadsDTO downloadsDTO) throws URISyntaxException {
        log.debug("REST request to update Downloads : {}", downloadsDTO);
        if (downloadsDTO.getId() == null) {
            return createDownloads(downloadsDTO);
        }
        DownloadsDTO result = downloadsService.save(downloadsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, downloadsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /downloads : get all the downloads.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of downloads in body
     */
    @GetMapping("/downloads")
    @Timed
    public ResponseEntity<List<DownloadsDTO>> getAllDownloads(Pageable pageable) {
        log.debug("REST request to get a page of Downloads");
        Page<DownloadsDTO> page = downloadsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/downloads");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /downloads/:id : get the "id" downloads.
     *
     * @param id the id of the downloadsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the downloadsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/downloads/{id}")
    @Timed
    public ResponseEntity<DownloadsDTO> getDownloads(@PathVariable Long id) {
        log.debug("REST request to get Downloads : {}", id);
        DownloadsDTO downloadsDTO = downloadsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(downloadsDTO));
    }

    /**
     * DELETE  /downloads/:id : delete the "id" downloads.
     *
     * @param id the id of the downloadsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/downloads/{id}")
    @Timed
    public ResponseEntity<Void> deleteDownloads(@PathVariable Long id) {
        log.debug("REST request to delete Downloads : {}", id);
        downloadsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
