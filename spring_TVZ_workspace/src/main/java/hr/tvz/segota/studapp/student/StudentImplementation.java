package hr.tvz.segota.studapp.student;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentImplementation implements StudentService {
    private static final int YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED = 26;
    private final StudentRepository studentRepository;


    public StudentImplementation(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> findAll() {
        return studentRepository.findAll().stream().map(this::mapStudentToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> findStudentByJmbag(final String jmbag) {
        return Optional.ofNullable(studentRepository.findStudentByJmbag(jmbag).map(this::mapStudentToDTO).orElse(null));
    }

    @Override
    public Optional<StudentDTO> save(StudentCommand command) {
        Student saveStudent = new Student(command.getFirstName(), command.getLastName(), command.getJmbag(), command.getDateOfBirth(), command.getNumberOfECTS());

        return Optional.ofNullable(studentRepository.save(saveStudent).map(this::mapStudentToDTO).orElse(null));
    }

    @Override
    public Optional<StudentDTO> update(final String jmbag, final StudentCommand updatedStudentCommand) {
        return studentRepository.update(jmbag, mapCommandToStudent(updatedStudentCommand)).map(this::mapStudentToDTO);
    }

    @Override
    public void deleteByJMBAG(final String jmbag) {
        studentRepository.deleteByJmbag(jmbag);
    }

    private Student mapCommandToStudent(final StudentCommand studentCommand) {
        return new Student(studentCommand.getFirstName(), studentCommand.getLastName(), studentCommand.getJmbag(), studentCommand.getDateOfBirth(), studentCommand.getNumberOfECTS());
    }

    private StudentDTO mapStudentToDTO(final Student student) {
        return new StudentDTO(student.getName(), student.getSurname(), student.getJmbag(), student.getNumberOfECTS(), shouldTuitionBePayed(student.getDateOfBirth()));
    }

    private boolean shouldTuitionBePayed(LocalDate dateOfBirth) {
        return dateOfBirth.plusYears(YEARS_AFTER_WHICH_TUITION_SHOULD_BE_PAYED).isBefore(LocalDate.now());
    }
}
