package ai.inmind.universityregistration.model.DTO;

import ai.inmind.universityregistration.model.Instructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class InstructorDTO {
    @NonNull
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private List<ClassDTO> classes = new ArrayList<>();

    public InstructorDTO(Instructor instructor) {
        this.id = instructor.getId();
        this.firstName = instructor.getFirstName();
        this.lastName = instructor.getLastName();
        instructor.getClasses().forEach(aClass -> this.classes.add(new ClassDTO(aClass.getId(), aClass.getSemester(), aClass.getDay(), aClass.getSession())));
    }
}
