package ai.inmind.universityregistration.repository;

import ai.inmind.universityregistration.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
