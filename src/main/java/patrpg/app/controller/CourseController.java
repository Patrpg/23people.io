package patrpg.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patrpg.app.model.Course;
import patrpg.app.service.CourseService;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class CourseController {

    CourseService courseService;

    /*
    GET /courses -> It returns a paginated list of existing courses
     */
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /*
    GET /courses/:id -> It returns the course identified by the given id. A Not Found 404
    response status must be returned if there is no course with the given :id
     */
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(new Course(), HttpStatus.OK);
    }

    /*
    POST /courses -> Create a course with the data provided in the json body request.
    A Bad Request 400 response status must be returned if the body request json is
    invalid.
     */
    @PostMapping("/courses")
    public ResponseEntity<Course> insertCourse(@RequestBody Course course) {
        return new ResponseEntity<>(new Course(), HttpStatus.CREATED);
    }

    /*
    PUT /courses/:id -> Update a course with the given id and the body request json. A
    Not Found 404 response status must be returned if there is no course with the
    given :id. A Bad Request 400 response status must be returned if the body request
    json is invalid.
     */
    @PutMapping("/courses/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id,
                                               @RequestBody Course course) {
        return new ResponseEntity<>(new Course(), HttpStatus.OK);
    }

    /*
    DELETE /courses/:id -> Delete a course with the given id. A Not Found 404
    response status must be returned if there is no course with the given :id
     */
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> deleteCourse(@PathVariable Integer id) {
        return new ResponseEntity<>(new Course(), HttpStatus.NO_CONTENT);
    }
}
