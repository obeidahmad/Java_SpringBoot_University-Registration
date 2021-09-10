package ai.inmind.universityregistration.model.DTO;

import ai.inmind.universityregistration.model.Student;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class StudentDTO {
    @NonNull
    @ApiModelProperty(notes = "The database generated ID", required = true)
    private Long id;
    @NonNull
    @ApiModelProperty(notes = "The student's first name", required = true)
    private String firstName;
    @NonNull
    @ApiModelProperty(notes = "The student's last name", required = true)
    private String lastName;
    @ApiModelProperty(notes = "A list of classes in which the student is enrolled")
    private List<ClassDTO> enrolledIn = new ArrayList<>();

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        student.getEnrolledIn().forEach(aClass -> this.enrolledIn.add(new ClassDTO(aClass.getId(), aClass.getSemester(), aClass.getDay(), aClass.getSession())));
    }
}
