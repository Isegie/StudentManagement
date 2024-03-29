package hr.tvz.segota.studapp.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.VerificationCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureDataJdbc
@ExtendWith(SpringExtension.class)
class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @Rule
    public VerificationCollector verificationCollector = MockitoJUnit.collector();

    @Mock
    ObjectMapper mapper;


    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllStudents() throws Exception {

        this.mockMvc.perform(get("/student/").
                with(user("admin")
                        .password("test")
                        .roles("ADMIN"))).andExpect(status().isOk());

        this.mockMvc.perform(get("/student/").
                with(user("admin")
                        .password("test")
                        .roles("ADMIN")).with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json(
                                "[{'firstName':'Mario','lastName':'Lebic','jmbag':'0213399738','numberOfECTS':24}," +
                                        "{'firstName':'Karla','lastName':'Pilic','jmbag':'0113092837','numberOfECTS':97}," +
                                        "{'firstName':'Lana','lastName':'Kutes','jmbag':'0249938473','numberOfECTS':101}]")).andDo(print());
    }


    @Test
    void getStudentByJMBAG() throws Exception {

        this.mockMvc.perform(get("/student/{jmbag}", "0213399738").
                with(user("admin")
                        .password("test")
                        .roles("ADMIN")).with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.firstName").value("Mario"))
                .andExpect(jsonPath("$.lastName").value("Lebic"))
                .andExpect(jsonPath("$.jmbag").value("0213399738"))
                .andExpect(jsonPath("$.numberOfECTS").value(24)).andDo(print());
    }


    @Test
    void save() throws Exception {

        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());

        String TEST_FIRST_NAME = "Janko";
        String TEST_LAST_NAME = "Robic";
        String TEST_JMBAG = "0369900928";
        LocalDate DATE_OF_BIRTH = LocalDate.of(2000, 11, 11);
        Integer TEST_NUMBER_OF_ECTS = 156;

        StudentCommand student = new StudentCommand();
        student.setFirstName(TEST_FIRST_NAME);
        student.setLastName(TEST_LAST_NAME);
        student.setJmbag(TEST_JMBAG);
        student.setDateOfBirth(DATE_OF_BIRTH);
        student.setNumberOfECTS(TEST_NUMBER_OF_ECTS);


        this.mockMvc.perform(post("/student")
                .with(user("admin").password("test")
                        .roles("ADMIN")).
                        with(csrf())
                .contentType(MediaType.APPLICATION_JSON).
                        content(mapper.writeValueAsString(student)).
                        accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.jmbag").value(TEST_JMBAG))
                .andExpect(jsonPath("$.firstName").value(TEST_FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(TEST_LAST_NAME))
                .andExpect(jsonPath("$.numberOfECTS").value(TEST_NUMBER_OF_ECTS))
                .andExpect(jsonPath("$.tuitionShouldBePaid").value(false))
                .andDo(print());
    }

    @Test
    void testStudentByJmbagNotFound() {
        Throwable throwable = Assertions.assertThrows(NestedServletException.class,
                () -> mockMvc.perform(get("/student/23").with(user("admin").password("test")
                        .roles("ADMIN")).
                        with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound()));

        Assertions.assertEquals(EmptyResultDataAccessException.class, throwable.getCause().getClass());
    }

    @Test
    void delete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/student/").param("jmbag", "0113092837")
                .with(user("admin").password("test")
                        .roles("ADMIN")).with(csrf()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
        //verify(this.studentService, atLeastOnce()).deleteByJMBAG("01S13092837");
    }


}
