package ai.inmind.universityregistration.repository;

import ai.inmind.universityregistration.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
}
