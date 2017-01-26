package com.marie.jhipster.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marie.jhipster.service.QualificationService;
import com.marie.jhipster.web.rest.util.HeaderUtil;
import com.marie.jhipster.web.rest.util.PaginationUtil;
import com.marie.jhipster.service.dto.QualificationDTO;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Qualification.
 */
@RestController
@RequestMapping("/api")
public class QualificationResource {

    private final Logger log = LoggerFactory.getLogger(QualificationResource.class);
        
    @Inject
    private QualificationService qualificationService;

    /**
     * POST  /qualifications : Create a new qualification.
     *
     * @param qualificationDTO the qualificationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new qualificationDTO, or with status 400 (Bad Request) if the qualification has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/qualifications")
    @Timed
    public ResponseEntity<QualificationDTO> createQualification(@RequestBody QualificationDTO qualificationDTO) throws URISyntaxException {
        log.debug("REST request to save Qualification : {}", qualificationDTO);
        if (qualificationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("qualification", "idexists", "A new qualification cannot already have an ID")).body(null);
        }
        QualificationDTO result = qualificationService.save(qualificationDTO);
        return ResponseEntity.created(new URI("/api/qualifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("qualification", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /qualifications : Updates an existing qualification.
     *
     * @param qualificationDTO the qualificationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated qualificationDTO,
     * or with status 400 (Bad Request) if the qualificationDTO is not valid,
     * or with status 500 (Internal Server Error) if the qualificationDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/qualifications")
    @Timed
    public ResponseEntity<QualificationDTO> updateQualification(@RequestBody QualificationDTO qualificationDTO) throws URISyntaxException {
        log.debug("REST request to update Qualification : {}", qualificationDTO);
        if (qualificationDTO.getId() == null) {
            return createQualification(qualificationDTO);
        }
        QualificationDTO result = qualificationService.save(qualificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("qualification", qualificationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /qualifications : get all the qualifications.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of qualifications in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/qualifications")
    @Timed
    public ResponseEntity<List<QualificationDTO>> getAllQualifications(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Qualifications");
        Page<QualificationDTO> page = qualificationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/qualifications");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /qualifications/:id : get the "id" qualification.
     *
     * @param id the id of the qualificationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the qualificationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/qualifications/{id}")
    @Timed
    public ResponseEntity<QualificationDTO> getQualification(@PathVariable Long id) {
        log.debug("REST request to get Qualification : {}", id);
        QualificationDTO qualificationDTO = qualificationService.findOne(id);
        return Optional.ofNullable(qualificationDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /qualifications/:id : delete the "id" qualification.
     *
     * @param id the id of the qualificationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/qualifications/{id}")
    @Timed
    public ResponseEntity<Void> deleteQualification(@PathVariable Long id) {
        log.debug("REST request to delete Qualification : {}", id);
        qualificationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("qualification", id.toString())).build();
    }

}
