package hr.tvz.segota.studapp.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseImplementation implements CourseService {

    private CourseJpaRepository jpaRepository;

    @Autowired
    public void setJpaRepository(CourseJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<CourseDTO> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CourseDTO> findByStudents_Jmbag(String jmbag) {
        return jpaRepository.findByStudents_Jmbag(jmbag).stream()
                .map(this::mapCourseToDTO).collect(Collectors.toList());
    }

    private CourseDTO mapCourseToDTO(Course course) {
        return new CourseDTO(course.getName(), course.getEctsPoints());
    }

}
