package ai.inmind.universityregistration.model.DTO;

import ai.inmind.universityregistration.model.Student;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<ClassDTO> enrolledIn = new ArrayList<>();

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        student.getEnrolledIn().forEach(aClass -> this.enrolledIn.add(new ClassDTO(aClass.getId(), aClass.getSemester(), aClass.getDay(), aClass.getSession())));
    }

    public StudentDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
