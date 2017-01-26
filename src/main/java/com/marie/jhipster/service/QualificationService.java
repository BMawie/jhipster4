package com.marie.jhipster.service;

import com.marie.jhipster.domain.Qualification;
import com.marie.jhipster.repository.QualificationRepository;
import com.marie.jhipster.service.dto.QualificationDTO;
import com.marie.jhipster.service.mapper.QualificationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Qualification.
 */
@Service
@Transactional
public class QualificationService {

    private final Logger log = LoggerFactory.getLogger(QualificationService.class);
    
    @Inject
    private QualificationRepository qualificationRepository;

    @Inject
    private QualificationMapper qualificationMapper;

    /**
     * Save a qualification.
     *
     * @param qualificationDTO the entity to save
     * @return the persisted entity
     */
    public QualificationDTO save(QualificationDTO qualificationDTO) {
        log.debug("Request to save Qualification : {}", qualificationDTO);
        Qualification qualification = qualificationMapper.qualificationDTOToQualification(qualificationDTO);
        qualification = qualificationRepository.save(qualification);
        QualificationDTO result = qualificationMapper.qualificationToQualificationDTO(qualification);
        return result;
    }

    /**
     *  Get all the qualifications.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<QualificationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Qualifications");
        Page<Qualification> result = qualificationRepository.findAll(pageable);
        return result.map(qualification -> qualificationMapper.qualificationToQualificationDTO(qualification));
    }

    /**
     *  Get one qualification by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public QualificationDTO findOne(Long id) {
        log.debug("Request to get Qualification : {}", id);
        Qualification qualification = qualificationRepository.findOne(id);
        QualificationDTO qualificationDTO = qualificationMapper.qualificationToQualificationDTO(qualification);
        return qualificationDTO;
    }

    /**
     *  Delete the  qualification by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Qualification : {}", id);
        qualificationRepository.delete(id);
    }
}
