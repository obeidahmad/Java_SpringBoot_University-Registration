package ai.inmind.universityregistration.repository;

import ai.inmind.universityregistration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
