package hr.tvz.segota.studapp.student;

import hr.tvz.segota.studapp.course.Course;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_s")
    private Long id;
    @Column(name = "first_name")
    private String name;
    @Column(name = "last_name")
    private String surname;
    @Column(name = "jmbag")
    private String jmbag;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Column(name = "ects_points")
    private int numberOfECTS;
    @ManyToMany(targetEntity = Course.class)
    @JoinTable(
            name = "studentsandcourses",
            joinColumns = {@JoinColumn(name = "id_stud")},
            inverseJoinColumns = {@JoinColumn(name = "id_course")}
    )
    private List<Course> courses;

    public Student() {
    }

    public Student(String name, String surname, String jmbag, LocalDate dateOfBirth, int numberOfECTS) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.jmbag = jmbag;
        this.numberOfECTS = numberOfECTS;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String _name) {
        name = _name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String _surname) {
        surname = _surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate _dateOfBirth) {
        dateOfBirth = _dateOfBirth;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String _jmbag) {
        jmbag = _jmbag;
    }

    public int getNumberOfECTS() {
        return numberOfECTS;
    }

    public void setNumberOfECTS(int _numberOfECTS) {
        numberOfECTS = _numberOfECTS;
    }
}