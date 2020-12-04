package hr.tvz.segota.studapp.student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    List<StudentDTO> findAll();

    Optional<StudentDTO> findStudentByJmbag(String JMBAG);

    Optional<StudentDTO> save(StudentCommand command);

    void deleteByJMBAG(String jmbag);

    Optional<StudentDTO> update(String jmbag, StudentCommand updateStudentCommand);
}