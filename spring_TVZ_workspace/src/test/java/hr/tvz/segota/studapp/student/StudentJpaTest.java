package hr.tvz.segota.studapp.student;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
@ExtendWith(SpringExtension.class)
public class StudentJpaTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private List<Student> listOfStudents = new ArrayList<>();

    @BeforeEach
    public void setUp() {

        entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitTest");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        StudentCommand firstStudent = new StudentCommand();
        firstStudent.setFirstName("Mario");
        firstStudent.setLastName("Lebic");
        firstStudent.setJmbag("0213399738");
        firstStudent.setDateOfBirth(Timestamp.valueOf("1992-02-12 00:00:05").toLocalDateTime().toLocalDate());
        firstStudent.setNumberOfECTS(24);


        StudentCommand secondStudent = new StudentCommand();
        secondStudent.setFirstName("Karla");
        secondStudent.setLastName("Pilic");
        secondStudent.setJmbag("0113092837");
        secondStudent.setDateOfBirth(Timestamp.valueOf("1992-01-11 00:00:04").toLocalDateTime().toLocalDate());
        secondStudent.setNumberOfECTS(97);


        StudentCommand thirdStudent = new StudentCommand();
        thirdStudent.setFirstName("Lana");
        thirdStudent.setLastName("Kutes");
        thirdStudent.setJmbag("0249938473");
        thirdStudent.setDateOfBirth(Timestamp.valueOf("1995-09-11 00:00:03").toLocalDateTime().toLocalDate());
        thirdStudent.setNumberOfECTS(101);
        Student fStudent = new Student(firstStudent.getFirstName(), firstStudent.getLastName(), firstStudent.getJmbag(), firstStudent.getDateOfBirth(), firstStudent.getNumberOfECTS());
        Student sStudent = new Student(secondStudent.getFirstName(), secondStudent.getLastName(), secondStudent.getJmbag(), secondStudent.getDateOfBirth(), secondStudent.getNumberOfECTS());
        Student tStudent = new Student(thirdStudent.getFirstName(), thirdStudent.getLastName(), thirdStudent.getJmbag(), thirdStudent.getDateOfBirth(), thirdStudent.getNumberOfECTS());

        listOfStudents.add(fStudent);
        listOfStudents.add(sStudent);
        listOfStudents.add(tStudent);

        entityManager.persist(fStudent);
        entityManager.persist(sStudent);
        entityManager.persist(tStudent);

        entityManager.getTransaction().commit();
    }

    @Test
    public void testStudents() {
        List<Student> fetchedEmStudents = entityManager.createQuery(
                "select s from Student s"
        ).getResultList();

        Assert.assertNotNull(fetchedEmStudents);
        Assert.assertEquals(listOfStudents.size(), fetchedEmStudents.size());
    }

}
