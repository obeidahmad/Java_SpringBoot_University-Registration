package ai.inmind.universityregistration.model.DTO;

import ai.inmind.universityregistration.model.Instructor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class InstructorDTO {
    @NonNull
    @ApiModelProperty(notes = "The database generated ID", required = true)
    private Long id;
    @NonNull
    @ApiModelProperty(notes = "The instructor's first name", required = true)
    private String firstName;
    @NonNull
    @ApiModelProperty(notes = "The instructor's last name", required = true)
    private String lastName;
    @ApiModelProperty(notes = "A list of classes given by the instructor")
    private List<ClassDTO> classes = new ArrayList<>();

    public InstructorDTO(Instructor instructor) {
        this.id = instructor.getId();
        this.firstName = instructor.getFirstName();
        this.lastName = instructor.getLastName();
        instructor.getClasses().forEach(aClass -> this.classes.add(new ClassDTO(aClass.getId(), aClass.getSemester(), aClass.getDay(), aClass.getSession())));
    }
}
