package patrpg.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import patrpg.app.entity.StudentEntity;
import patrpg.app.model.Student;
import patrpg.app.repository.StudentRepository;

import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class StudentService {

    StudentRepository studentRepository;
    CourseService courseService;

    public Page<Student> getStudents(Pageable pageable) {
        Page<StudentEntity> studentEntityPage = studentRepository.findAll(pageable);
        return studentEntityPage.map(this::getStudentFromEntity);
    }

    public Student getStudentById(Integer id) {
        Optional<StudentEntity> ose = studentRepository.findById(id);
        if (ose.isEmpty()) {
            log.warn("[getStudentById] There is no student with id {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[getStudentById] There is no student with id " + id);
        }
        return getStudentFromEntity(ose.get());
    }

    public Student insertStudent(Student student) {
        if (student.getId() != null || student.getAge() == null || student.getRut() == null || student.getDv() == null ||
                student.getCourse().getId() == null || student.getName() == null || student.getLastName() == null) {
            log.warn("[insertStudent] Id must be null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[insertStudent] Id must be null");
        }

        if (!checkDv(student.getRut(), student.getDv())) {
            log.warn("[insertStudent] Invalid Rut");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[insertStudent] Invalid Rut");
        }

        if (student.getAge() <= 18) {
            log.warn("[insertStudent] Invalid Age (must be >18");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[insertStudent] Invalid Age (must be >18");
        }

        // This will throw a exception if the course does not exists
        courseService.getCourseById(student.getCourse().getId());

        student.setId(studentRepository.save(getEntityFromStudent(student)).getId());
        return student;
    }

    public Student updateStudent(Integer id, Student student) {
        if (!id.equals(student.getId())) {
            log.warn("[updateStudent] Id in JSON is different from path param id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[updateStudent] Id in JSON is different from path param id");
        }

        if (student.getId() == null || student.getAge() == null || student.getRut() == null || student.getDv() == null ||
                student.getCourse().getId() == null || student.getName() == null || student.getLastName() == null) {
            log.warn("[updateStudent] Invalid body");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[updateStudent] Invalid body");
        }

        if (student.getAge() <= 18) {
            log.warn("[updateStudent] Invalid Age (must be >18)");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[updateStudent] Invalid Age (must be >18)");
        }

        Optional<StudentEntity> ose = studentRepository.findById(id);
        if (ose.isPresent()) {

            // This will throw a exception if the course does not exists
            courseService.getCourseById(student.getCourse().getId());

            studentRepository.save(getEntityFromStudent(student));
            return student;
        }
        log.warn("[updateStudent] There is no student with id {}", id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[updateStudent] There is no student with id " + id);
    }

    public void deleteStudent(Integer id) {
        Optional<StudentEntity> ose = studentRepository.findById(id);
        if (ose.isPresent()) {
            studentRepository.delete(ose.get());
            return;
        }
        log.warn("[deleteStudent] There is no course with id {}", id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[deleteStudent] There is no course with id " + id);
    }

    private Student getStudentFromEntity(StudentEntity studentEntity) {
        Student ret = new Student();
        ret.setId(studentEntity.getId());
        ret.setCourse(courseService.getCourseById(studentEntity.getIdCourse()));
        ret.setRut(studentEntity.getRut());
        ret.setDv(studentEntity.getDv());
        ret.setName(studentEntity.getName());
        ret.setLastName(studentEntity.getLastName());
        ret.setAge(studentEntity.getAge());
        return ret;
    }

    private StudentEntity getEntityFromStudent(Student student) {
        StudentEntity ret = new StudentEntity();
        ret.setId(student.getId());
        ret.setIdCourse(student.getCourse().getId());
        ret.setRut(student.getRut());
        ret.setDv(student.getDv());
        ret.setName(student.getName());
        ret.setLastName(student.getLastName());
        ret.setAge(student.getAge());
        return ret;
    }

    private boolean checkDv(Integer rut, String dv) {
        int m = 0;
        int s = 1;
        dv = dv.toUpperCase();
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        if (dv.equals("K") && s == 0) {
            return true;
        } else return dv.equals(String.valueOf(s - 1));
    }
}
