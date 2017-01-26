package com.marie.jhipster.service.mapper;

import com.marie.jhipster.domain.*;
import com.marie.jhipster.service.dto.QualificationDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Qualification and its DTO QualificationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QualificationMapper {

    QualificationDTO qualificationToQualificationDTO(Qualification qualification);

    List<QualificationDTO> qualificationsToQualificationDTOs(List<Qualification> qualifications);

    Qualification qualificationDTOToQualification(QualificationDTO qualificationDTO);

    List<Qualification> qualificationDTOsToQualifications(List<QualificationDTO> qualificationDTOs);
}
