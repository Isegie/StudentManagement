package hr.tvz.segota.studapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"hr.tvz.segota.studapp.security",
        "hr.tvz.segota.studapp.security.error", "hr.tvz.segota.studapp.security.jwt"
        , "hr.tvz.segota.studapp.security.web", "hr.tvz.segota.studapp.user"
        , "hr.tvz.segota.studapp.configurations",
        "hr.tvz.segota.studapp.student", "hr.tvz.segota.studapp.course"})
public class StudappApplication {

    public static void main(String[] args) {

        SpringApplication.run(StudappApplication.class, args);
    }

}
