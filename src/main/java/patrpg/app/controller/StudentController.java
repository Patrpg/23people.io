package patrpg.app.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patrpg.app.model.Student;
import patrpg.app.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class StudentController {

    StudentService SudentService;

    /*
    GET /students -> It returns a paginated list of existing students
     */
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
    }

    /*
    GET /students/:id -> It returns the student identified by the given id. A Not Found 404
    response status must be returned if there is no student with the given :id
     */
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Integer id) {
        return new ResponseEntity<>(new Student(), HttpStatus.OK);
    }

    /*
    POST /Students -> Create a student with the data provided in the json body request.
    A Bad Request 400 response status must be returned if the body request json is
    invalid.
     */
    @PostMapping("/students")
    public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
        return new ResponseEntity<>(new Student(), HttpStatus.CREATED);
    }

    /*
    PUT /students/:id -> Update a student with the given id and the body request json. A
    Not Found 404 response status must be returned if there is no student with the
    given :id. A Bad Request 400 response status must be returned if the body request
    json is invalid.
     */
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer id,
                                                 @RequestBody Student student) {
        return new ResponseEntity<>(new Student(), HttpStatus.OK);
    }

    /*
    DELETE /students/:id -> Delete a student with the given id. A Not Found 404
    response status must be returned if there is no student with the given :id
     */
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Integer id) {
        return new ResponseEntity<>(new Student(), HttpStatus.NO_CONTENT);
    }

}
