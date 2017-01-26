package com.marie.jhipster.web.rest;

import com.marie.jhipster.JhipsterApp;
import com.marie.jhipster.domain.User;
import com.marie.jhipster.domain.enumeration.CivilityEnum;
import com.marie.jhipster.repository.UserRepository;
import com.marie.jhipster.service.UserService;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class UserResourceIntTest {

    private static final CivilityEnum DEFAULT_CIVILITY = CivilityEnum.MADAME;
    private static final CivilityEnum UPDATED_CIVILITY = CivilityEnum.MONSIEUR;

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_BIRTH_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_BIRTH_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_BIRTH_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_BIRTH_CITY = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_BIRTH_DEPT = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_DEPT = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_NAMES = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_NAMES = "BBBBBBBBBB";

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserService userService;

    private MockMvc restUserMockMvc;

    /**
     * Create a User.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which has a required relationship to the User entity.
     */
    public static User createEntity(EntityManager em) {
        User user = new User();
        user.setLogin("test");
        user.setPassword(RandomStringUtils.random(60));
        user.setActivated(true);
        user.setEmail("test@test.com");
        user.setFirstName("test");
        user.setLastName("test");
        user.setLangKey("en");
        user.setCivility(DEFAULT_CIVILITY);
        user.setUsername(DEFAULT_USERNAME);
        user.setBirthDate(DEFAULT_BIRTH_DATE);
        user.setBirthCountry(DEFAULT_BIRTH_COUNTRY);
        user.setBirthCity(DEFAULT_BIRTH_CITY);
        user.setBirthDept(DEFAULT_BIRTH_DEPT);
        user.setNationality(DEFAULT_NATIONALITY);
        user.setOtherNames(DEFAULT_OTHER_NAMES);
        em.persist(user);
        em.flush();
        return user;
    }

    @Before
    public void setup() {
        UserResource userResource = new UserResource();
        ReflectionTestUtils.setField(userResource, "userRepository", userRepository);
        ReflectionTestUtils.setField(userResource, "userService", userService);
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(userResource).build();
    }

    @Test
    public void testGetExistingUser() throws Exception {
        restUserMockMvc.perform(get("/api/users/admin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.lastName").value("Administrator"));
    }

    @Test
    public void testGetUnknownUser() throws Exception {
        restUserMockMvc.perform(get("/api/users/unknown")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
