package patrpg.app;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import patrpg.app.controller.StudentController;
import patrpg.app.model.Course;
import patrpg.app.model.Student;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudentsTests {

    @Autowired
    StudentController studentController;

    @Test
    @Order(1)
    void getStudents() {
        ResponseEntity<Page<Student>> response = studentController.getStudents(Pageable.unpaged());

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    void insertStudent() {
        Student student = new Student();
        student.setCourse(new Course());
        student.getCourse().setId(2);
        student.setRut(33488993);
        student.setDv("9");
        student.setName(UUID.randomUUID().toString());
        student.setLastName(UUID.randomUUID().toString());
        student.setAge(19);
        ResponseEntity<Student> response = studentController.insertStudent(student);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @Order(3)
    void getStudentById() {
        ResponseEntity<Student> response = studentController.getStudentById(1);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(4)
    void updateStudent() {
        Student student = new Student();
        student.setId(1);
        student.setCourse(new Course());
        student.getCourse().setId(2);
        student.setRut(25552752);
        student.setDv("5");
        student.setName(UUID.randomUUID().toString());
        student.setLastName(UUID.randomUUID().toString());
        student.setAge(21);

        ResponseEntity<Student> response = studentController.updateStudent(1, student);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(5)
    void deleteStudent() {
        ResponseEntity<Void> response = studentController.deleteStudent(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
