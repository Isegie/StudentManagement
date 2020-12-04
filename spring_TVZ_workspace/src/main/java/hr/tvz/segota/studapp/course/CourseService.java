package hr.tvz.segota.studapp.course;

import java.util.List;

public interface CourseService {
    List<CourseDTO> findAll();

    List<CourseDTO> findByStudents_Jmbag(String jmbag);

}
