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
import patrpg.app.controller.CourseController;
import patrpg.app.model.Course;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CoursesTests {

    @Autowired
    CourseController courseController;

    @Test
    @Order(1)
    void getCourses() {
        ResponseEntity<Page<Course>> response = courseController.getCourses(Pageable.unpaged());

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(2)
    void insertCourse() {
        Course course = new Course();
        course.setCode("0000");
        ResponseEntity<Course> response = courseController.insertCourse(course);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        course.setId(null);
        courseController.insertCourse(course);
    }

    @Test
    @Order(3)
    void getCourseById() {
        ResponseEntity<Course> response = courseController.getCourseById(1);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(4)
    void updateCourse() {
        Course course = new Course();
        course.setId(1);
        course.setCode("1111");

        ResponseEntity<Course> response = courseController.updateCourse(1, course);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @Order(5)
    void deleteCourse() {
        ResponseEntity<Void> response = courseController.deleteCourse(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
