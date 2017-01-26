package com.marie.jhipster.web.rest;

import com.marie.jhipster.JhipsterApp;

import com.marie.jhipster.domain.Qualification;
import com.marie.jhipster.repository.QualificationRepository;
import com.marie.jhipster.service.QualificationService;
import com.marie.jhipster.service.dto.QualificationDTO;
import com.marie.jhipster.service.mapper.QualificationMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QualificationResource REST controller.
 *
 * @see QualificationResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class QualificationResourceIntTest {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final String DEFAULT_RESULTS = "AAAAAAAAAA";
    private static final String UPDATED_RESULTS = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_SCHOOL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SCHOOL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NOTES = "AAAAAAAAAA";
    private static final String UPDATED_NOTES = "BBBBBBBBBB";

    private static final String DEFAULT_SPECIALITY = "AAAAAAAAAA";
    private static final String UPDATED_SPECIALITY = "BBBBBBBBBB";

    @Inject
    private QualificationRepository qualificationRepository;

    @Inject
    private QualificationMapper qualificationMapper;

    @Inject
    private QualificationService qualificationService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restQualificationMockMvc;

    private Qualification qualification;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        QualificationResource qualificationResource = new QualificationResource();
        ReflectionTestUtils.setField(qualificationResource, "qualificationService", qualificationService);
        this.restQualificationMockMvc = MockMvcBuilders.standaloneSetup(qualificationResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualification createEntity(EntityManager em) {
        Qualification qualification = new Qualification()
                .type(DEFAULT_TYPE)
                .year(DEFAULT_YEAR)
                .results(DEFAULT_RESULTS)
                .name(DEFAULT_NAME)
                .country(DEFAULT_COUNTRY)
                .city(DEFAULT_CITY)
                .schoolName(DEFAULT_SCHOOL_NAME)
                .notes(DEFAULT_NOTES)
                .speciality(DEFAULT_SPECIALITY);
        return qualification;
    }

    @Before
    public void initTest() {
        qualification = createEntity(em);
    }

    @Test
    @Transactional
    public void createQualification() throws Exception {
        int databaseSizeBeforeCreate = qualificationRepository.findAll().size();

        // Create the Qualification
        QualificationDTO qualificationDTO = qualificationMapper.qualificationToQualificationDTO(qualification);

        restQualificationMockMvc.perform(post("/api/qualifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qualificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Qualification in the database
        List<Qualification> qualificationList = qualificationRepository.findAll();
        assertThat(qualificationList).hasSize(databaseSizeBeforeCreate + 1);
        Qualification testQualification = qualificationList.get(qualificationList.size() - 1);
        assertThat(testQualification.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testQualification.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testQualification.getResults()).isEqualTo(DEFAULT_RESULTS);
        assertThat(testQualification.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testQualification.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testQualification.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testQualification.getSchoolName()).isEqualTo(DEFAULT_SCHOOL_NAME);
        assertThat(testQualification.getNotes()).isEqualTo(DEFAULT_NOTES);
        assertThat(testQualification.getSpeciality()).isEqualTo(DEFAULT_SPECIALITY);
    }

    @Test
    @Transactional
    public void createQualificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = qualificationRepository.findAll().size();

        // Create the Qualification with an existing ID
        Qualification existingQualification = new Qualification();
        existingQualification.setId(1L);
        QualificationDTO existingQualificationDTO = qualificationMapper.qualificationToQualificationDTO(existingQualification);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualificationMockMvc.perform(post("/api/qualifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingQualificationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Qualification> qualificationList = qualificationRepository.findAll();
        assertThat(qualificationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllQualifications() throws Exception {
        // Initialize the database
        qualificationRepository.saveAndFlush(qualification);

        // Get all the qualificationList
        restQualificationMockMvc.perform(get("/api/qualifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualification.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].results").value(hasItem(DEFAULT_RESULTS.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].schoolName").value(hasItem(DEFAULT_SCHOOL_NAME.toString())))
            .andExpect(jsonPath("$.[*].notes").value(hasItem(DEFAULT_NOTES.toString())))
            .andExpect(jsonPath("$.[*].speciality").value(hasItem(DEFAULT_SPECIALITY.toString())));
    }

    @Test
    @Transactional
    public void getQualification() throws Exception {
        // Initialize the database
        qualificationRepository.saveAndFlush(qualification);

        // Get the qualification
        restQualificationMockMvc.perform(get("/api/qualifications/{id}", qualification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(qualification.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.results").value(DEFAULT_RESULTS.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.schoolName").value(DEFAULT_SCHOOL_NAME.toString()))
            .andExpect(jsonPath("$.notes").value(DEFAULT_NOTES.toString()))
            .andExpect(jsonPath("$.speciality").value(DEFAULT_SPECIALITY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQualification() throws Exception {
        // Get the qualification
        restQualificationMockMvc.perform(get("/api/qualifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQualification() throws Exception {
        // Initialize the database
        qualificationRepository.saveAndFlush(qualification);
        int databaseSizeBeforeUpdate = qualificationRepository.findAll().size();

        // Update the qualification
        Qualification updatedQualification = qualificationRepository.findOne(qualification.getId());
        updatedQualification
                .type(UPDATED_TYPE)
                .year(UPDATED_YEAR)
                .results(UPDATED_RESULTS)
                .name(UPDATED_NAME)
                .country(UPDATED_COUNTRY)
                .city(UPDATED_CITY)
                .schoolName(UPDATED_SCHOOL_NAME)
                .notes(UPDATED_NOTES)
                .speciality(UPDATED_SPECIALITY);
        QualificationDTO qualificationDTO = qualificationMapper.qualificationToQualificationDTO(updatedQualification);

        restQualificationMockMvc.perform(put("/api/qualifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qualificationDTO)))
            .andExpect(status().isOk());

        // Validate the Qualification in the database
        List<Qualification> qualificationList = qualificationRepository.findAll();
        assertThat(qualificationList).hasSize(databaseSizeBeforeUpdate);
        Qualification testQualification = qualificationList.get(qualificationList.size() - 1);
        assertThat(testQualification.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testQualification.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testQualification.getResults()).isEqualTo(UPDATED_RESULTS);
        assertThat(testQualification.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testQualification.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testQualification.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testQualification.getSchoolName()).isEqualTo(UPDATED_SCHOOL_NAME);
        assertThat(testQualification.getNotes()).isEqualTo(UPDATED_NOTES);
        assertThat(testQualification.getSpeciality()).isEqualTo(UPDATED_SPECIALITY);
    }

    @Test
    @Transactional
    public void updateNonExistingQualification() throws Exception {
        int databaseSizeBeforeUpdate = qualificationRepository.findAll().size();

        // Create the Qualification
        QualificationDTO qualificationDTO = qualificationMapper.qualificationToQualificationDTO(qualification);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restQualificationMockMvc.perform(put("/api/qualifications")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(qualificationDTO)))
            .andExpect(status().isCreated());

        // Validate the Qualification in the database
        List<Qualification> qualificationList = qualificationRepository.findAll();
        assertThat(qualificationList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteQualification() throws Exception {
        // Initialize the database
        qualificationRepository.saveAndFlush(qualification);
        int databaseSizeBeforeDelete = qualificationRepository.findAll().size();

        // Get the qualification
        restQualificationMockMvc.perform(delete("/api/qualifications/{id}", qualification.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Qualification> qualificationList = qualificationRepository.findAll();
        assertThat(qualificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
