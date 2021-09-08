package ai.inmind.universityregistration.repository;

import ai.inmind.universityregistration.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
