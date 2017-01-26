package com.marie.jhipster.service.dto;

import com.marie.jhipster.config.Constants;

import com.marie.jhipster.domain.Authority;
import com.marie.jhipster.domain.User;

import com.marie.jhipster.domain.enumeration.CivilityEnum;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO representing a user, with his authorities.
 */
public class UserDTO {

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private boolean activated = false;

    @Size(min = 2, max = 5)
    private String langKey;

    private Set<String> authorities;

    private CivilityEnum civility;

    private String username;

    private LocalDate birthDate;

    private String birthCountry;

    private String birthCity;

    private String birthDept;

    private String nationality;

    private String otherNames;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this(user.getLogin(), user.getFirstName(), user.getLastName(),
            user.getEmail(), user.getActivated(), user.getLangKey(),
            user.getAuthorities().stream().map(Authority::getName)
                .collect(Collectors.toSet()), user.getCivility(), user.getUsername(),
                user.getBirthDate(), user.getBirthCity(), user.getBirthCountry(),
                user.getBirthDept(), user.getNationality(), user.getOtherNames());
    }

    public UserDTO(String login, String firstName, String lastName,
        String email, boolean activated, String langKey, Set<String> authorities) {

        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activated = activated;
        this.langKey = langKey;
        this.authorities = authorities;
    }

    public UserDTO(String login, String firstName, String lastName,
        String email, boolean activated, String langKey, Set<String> authorities,
                   CivilityEnum civility, String username, LocalDate birthDate, String birthCity,
                   String birthCountry, String birthDept, String nationality, String otherNames) {

        this(login, firstName, lastName, email, activated, langKey, authorities);
        this.civility = civility;
        this.username = username;
        this.birthCity = birthCity;
        this.birthCountry = birthCountry;
        this.birthDate = birthDate;
        this.birthDept = birthDept;
        this.nationality = nationality;
        this.otherNames = otherNames;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public CivilityEnum getCivility() {
        return civility;
    }

    public void setCivility(CivilityEnum civility) {
        this.civility = civility;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
    public String getBirthCountry() {
        return birthCountry;
    }

    public void setBirthCountry(String birthCountry) {
        this.birthCountry = birthCountry;
    }
    public String getBirthCity() {
        return birthCity;
    }

    public void setBirthCity(String birthCity) {
        this.birthCity = birthCity;
    }
    public String getBirthDept() {
        return birthDept;
    }

    public void setBirthDept(String birthDept) {
        this.birthDept = birthDept;
    }
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "login='" + login + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", email='" + email + '\'' +
            ", activated=" + activated +
            ", langKey='" + langKey + '\'' +
            ", authorities=" + authorities +
            ", civility='" + civility + "'" +
            ", username='" + username + "'" +
            ", birthDate='" + birthDate + "'" +
            ", birthCountry='" + birthCountry + "'" +
            ", birthCity='" + birthCity + "'" +
            ", birthDept='" + birthDept + "'" +
            ", nationality='" + nationality + "'" +
            ", otherNames='" + otherNames + "'" +
            "}";
    }
}
