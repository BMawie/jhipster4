package com.marie.jhipster.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Qualification.
 */
@Entity
@Table(name = "qualification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Qualification implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "year")
    private Integer year;

    @Column(name = "results")
    private String results;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "notes")
    private String notes;

    @Column(name = "speciality")
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

    public Qualification type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public Qualification year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getResults() {
        return results;
    }

    public Qualification results(String results) {
        this.results = results;
        return this;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getName() {
        return name;
    }

    public Qualification name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public Qualification country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public Qualification city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public Qualification schoolName(String schoolName) {
        this.schoolName = schoolName;
        return this;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getNotes() {
        return notes;
    }

    public Qualification notes(String notes) {
        this.notes = notes;
        return this;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSpeciality() {
        return speciality;
    }

    public Qualification speciality(String speciality) {
        this.speciality = speciality;
        return this;
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
        Qualification qualification = (Qualification) o;
        if (qualification.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, qualification.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Qualification{" +
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
