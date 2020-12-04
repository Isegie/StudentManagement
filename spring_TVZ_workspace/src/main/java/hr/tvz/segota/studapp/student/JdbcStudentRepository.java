package hr.tvz.segota.studapp.student;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Primary
@Repository
public class JdbcStudentRepository implements StudentRepository {
    private JdbcTemplate jdbc;
    private SimpleJdbcInsert studentInserter;

    public JdbcStudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.studentInserter = new SimpleJdbcInsert(jdbc)
                .withTableName("student")
                .usingGeneratedKeyColumns("id_s");
    }

    @Override
    public List<Student> findAll() {
        return jdbc.query("select first_name, last_name, jmbag, date_of_birth, ects_points from student", this::mapRowToStudent);
    }

    @Override
    public Optional<Student> findStudentByJmbag(String jmbag) {
        return Optional.ofNullable(jdbc.queryForObject("select first_name, last_name, jmbag, date_of_birth, ects_points from student where jmbag = ?",
                this::mapRowToStudent, jmbag));
    }

    @Override
    public Optional<Student> save(Student student) {
        if (student != null) {
            saveStudentDetails(student);
        }
        return Optional.ofNullable(student);
    }

    public long saveStudentDetails(Student student) {
        Map<String, Object> values = new HashMap<>();
        values.put("first_name", student.getName());
        values.put("last_name", student.getSurname());
        values.put("jmbag", student.getJmbag());
        values.put("date_of_birth", student.getDateOfBirth());
        values.put("ects_points", student.getNumberOfECTS());
        return studentInserter.executeAndReturnKey(values).longValue();
    }

    public Optional<Student> update(String JMBAG, Student student) {
        int status = jdbc.update("UPDATE student SET first_name = ?,last_name = ?,date_of_birth = ?,ects_points = ? WHERE jmbag = ?", student.getName(),
                student.getSurname(),
                student.getDateOfBirth(),
                student.getNumberOfECTS(),
                student.getJmbag());

        if (status != 0) {
            return Optional.ofNullable(student);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByJmbag(String jmbag) {
        jdbc.update("DELETE FROM studentsandcourses WHERE id_stud IN (SELECT id_stud FROM student WHERE jmbag = ?); DELETE FROM student WHERE jmbag = ?;", jmbag, jmbag);
    }

    private Student mapRowToStudent(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setName(rs.getString("first_name"));
        student.setSurname(rs.getString("last_name"));
        student.setJmbag(rs.getString("jmbag"));
        student.setDateOfBirth(rs.getTimestamp("date_of_birth").toLocalDateTime().toLocalDate());
        student.setNumberOfECTS(rs.getInt("ects_points"));

        return student;
    }
}