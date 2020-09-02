package patrpg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patrpg.app.entity.StudentEntity;

public interface CourseRepository extends JpaRepository<StudentEntity, Integer> {
}
