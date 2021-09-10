package ai.inmind.universityregistration.model.DTO;

import ai.inmind.universityregistration.model.Instructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InstructorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private List<ClassDTO> classes = new ArrayList<>();

    public InstructorDTO(Instructor instructor) {
        this.id = instructor.getId();
        this.firstName = instructor.getFirstName();
        this.lastName = instructor.getLastName();
        instructor.getClasses().forEach(aClass -> this.classes.add(new ClassDTO(aClass.getId(), aClass.getSemester(), aClass.getDay(), aClass.getSession())));
    }

    public InstructorDTO(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
