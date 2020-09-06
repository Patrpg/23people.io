package patrpg.app.service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import patrpg.app.entity.CourseEntity;
import patrpg.app.model.Course;
import patrpg.app.repository.CourseRepository;

import java.util.Optional;

@Log4j2
@Service
@AllArgsConstructor
public class CourseService {

    CourseRepository courseRepository;

    public Page<Course> getCourses(Pageable pageable) {
        Page<CourseEntity> courseEntityPage = courseRepository.findAll(pageable);
        return courseEntityPage.map(this::getCourseFromEntity);
    }

    public Course getCourseById(Integer id) {
        Optional<CourseEntity> oce = courseRepository.findById(id);
        if (oce.isEmpty()) {
            log.warn("[getCourseById] There is no course with id {}", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[getCourseById] There is no course with id " + id);
        }
        return getCourseFromEntity(oce.get());
    }

    public Course insertCourse(Course course) {
        if (course.getId() != null) {
            log.warn("[insertCourse] Id must be null");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[insertCourse] Id must be null");
        }
        course.setId(courseRepository.save(getEntityFromCourse(course)).getId());
        return course;
    }

    public Course updateCourse(Integer id, Course course) {
        if (!id.equals(course.getId())) {
            log.warn("[updateCourse] Id in JSON is different from path param id");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "[updateCourse] Id in JSON is different from path param id");
        }
        Optional<CourseEntity> oce = courseRepository.findById(id);
        if (oce.isPresent()) {
            courseRepository.save(getEntityFromCourse(course));
            return course;
        }
        log.warn("[updateCourse] There is no course with id {}", id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[updateCourse] There is no course with id " + id);
    }

    public void deleteCourse(Integer id) {
        Optional<CourseEntity> oce = courseRepository.findById(id);
        if (oce.isPresent()) {
            courseRepository.delete(oce.get());
            return;
        }
        log.warn("[deleteCourse] There is no course with id {}", id);
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "[deleteCourse] There is no course with id " + id);
    }


    private Course getCourseFromEntity(CourseEntity entity) {
        Course ret = new Course();
        ret.setId(entity.getId());
        ret.setCode(entity.getCode());
        return ret;
    }

    private CourseEntity getEntityFromCourse(Course course) {
        CourseEntity ret = new CourseEntity();
        ret.setId(course.getId());
        ret.setCode(course.getCode());
        return ret;
    }

}
