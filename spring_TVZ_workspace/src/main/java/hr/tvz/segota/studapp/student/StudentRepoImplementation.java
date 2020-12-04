package hr.tvz.segota.studapp.student;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StudentRepoImplementation implements StudentRepository {
    public List<Student> STUDENTS_LIST = new ArrayList<>();

    public StudentRepoImplementation() {
        STUDENTS_LIST.add(new Student("Marin", "Drzic", "0243849097", LocalDate.now().minusYears(28), 100));
        STUDENTS_LIST.add(new Student("Leonardo", "DiCaprio", "0315849097", LocalDate.now().minusYears(30), 120));
        STUDENTS_LIST.add(new Student("Maks", "Kresovic", "0117849097", LocalDate.now().minusYears(21), 90));
        STUDENTS_LIST.add(new Student("Ivan", "Segota", "0877839597", LocalDate.now().minusYears(19), 60));
    }

    @Override
    public List<Student> findAll() {
        return STUDENTS_LIST;
    }

    @Override
    public Optional<Student> findStudentByJmbag(final String jmbag) {
        return STUDENTS_LIST.stream().filter(it -> Objects.equals(it.getJmbag(), jmbag)).findAny();
    }

    @Override
    public void deleteByJmbag(String jmbag) {
        STUDENTS_LIST.removeIf(it -> it.getJmbag().equals(jmbag));
    }

    @Override
    public Optional<Student> save(Student student) {
        STUDENTS_LIST.add(student);
        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> update(String jmbag, Student mapCommandToStudent) {
        return Optional.empty();
    }


}
