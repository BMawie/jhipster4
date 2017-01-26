package com.marie.jhipster.service.dto;

import java.io.Serializable;
import java.util.Objects;


/**
 * A DTO for the Qualification entity.
 */
public class QualificationDTO implements Serializable {

    private Long id;

    private String type;

    private Integer year;

    private String results;

    private String name;

    private String country;

    private String city;

    private String schoolName;

    private String notes;

    private String speciality;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QualificationDTO qualificationDTO = (QualificationDTO) o;

        if ( ! Objects.equals(id, qualificationDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "QualificationDTO{" +
            "id=" + id +
            ", type='" + type + "'" +
            ", year='" + year + "'" +
            ", results='" + results + "'" +
            ", name='" + name + "'" +
            ", country='" + country + "'" +
            ", city='" + city + "'" +
            ", schoolName='" + schoolName + "'" +
            ", notes='" + notes + "'" +
            ", speciality='" + speciality + "'" +
            '}';
    }
}
