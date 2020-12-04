package hr.tvz.segota.studapp.student;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents() {
        return studentService.findAll();
    }

    @GetMapping("/{jmbag}")
    public ResponseEntity<StudentDTO> getStudentByJmbag(@PathVariable final String jmbag) {
        return studentService.findStudentByJmbag(jmbag).map(studentDTO -> ResponseEntity.status(HttpStatus.OK)
                .body(studentDTO)).orElseGet(() -> ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build());
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody final StudentCommand command) {
        return studentService.save(command).map(studentDTO -> ResponseEntity.status(HttpStatus.CREATED)
                .body(studentDTO)).orElseGet(() -> ResponseEntity
                .status(HttpStatus.CONFLICT)
                .build());
    }

    @PutMapping("/{jmbag}")
    public ResponseEntity<StudentDTO> update(@PathVariable String jmbag, @Valid @RequestBody final StudentCommand updateStudentCommand) {
        return studentService.update(jmbag, updateStudentCommand).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{jmbag}")
    public void delete(@PathVariable String jmbag) {
        studentService.deleteByJMBAG(jmbag);
    }
}