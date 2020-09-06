package patrpg.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patrpg.app.model.Course;
import patrpg.app.service.CourseService;

@RestController
@AllArgsConstructor
public class CourseController {

    CourseService courseService;

    /*
    GET /courses -> It returns a paginated list of existing courses
    */
    @GetMapping("/courses")
    public ResponseEntity<Page<Course>> getCourses(Pageable pageable) {
        return new ResponseEntity<>(courseService.getCourses(pageable), HttpStatus.OK);
    }


    /*
    GET /courses/:id -> It returns the course identified by the given id. A Not Found 404
    response status must be returned if there is no course with the given :id
     */
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    /*
    POST /courses -> Create a course with the data provided in the json body request.
    A Bad Request 400 response status must be returned if the body request json is
    invalid.
     */
    @PostMapping("/courses")
    public ResponseEntity<Course> insertCourse(@RequestBody Course course) {
        return new ResponseEntity<>(courseService.insertCourse(course), HttpStatus.CREATED);
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
        return new ResponseEntity<>(courseService.updateCourse(id, course), HttpStatus.OK);
    }

    /*
    DELETE /courses/:id -> Delete a course with the given id. A Not Found 404
    response status must be returned if there is no course with the given :id
     */
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
