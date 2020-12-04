package hr.tvz.segota.studapp.course;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@AutoConfigureDataJdbc
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private CourseService courseService;

    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getAllCourses() throws Exception {

        this.mockMvc.perform(get("/course/").
                with(user("admin")
                        .password("test")
                        .roles("ADMIN"))).andExpect(status().isOk());
        /*
        this.mockMvc.perform(get("/course/").
                with(user("admin")
                        .password("test")
                        .roles("ADMIN")).with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print())
                .andExpect(content()
                        .json("[{'name':'Programiranje Web Aplikacija','numberOfECTS':5}," +
                                "{'name':'Programiranje u jeziku Java','numberOfECTS':5}," +
                                "{'name':'Operacijski sustavi','numberOfECTS':6}," +
                                "{'name':'Osnove Elektrotehnike i Elektronike','numberOfECTS':7}]"));

         */
    }

    @Test
    void getCoursesByJmbag() throws Exception {

        this.mockMvc.perform(get("/course/{$.jmbag}", "0213399738").
                with(user("admin")
                        .password("test")
                        .roles("ADMIN")).with(csrf()).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andDo(print());

    }

}