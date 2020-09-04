package patrpg.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import patrpg.app.entity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
}
