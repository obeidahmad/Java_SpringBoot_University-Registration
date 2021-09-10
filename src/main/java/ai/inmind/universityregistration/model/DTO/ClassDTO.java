package ai.inmind.universityregistration.model.DTO;

import ai.inmind.universityregistration.model.Class;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ClassDTO {
    @NonNull
    @ApiModelProperty(notes = "The database generated ID", required = true)
    private Long id;
    @NonNull
    @ApiModelProperty(notes = "The semester during which this class is given", required = true)
    private String semester;
    @NonNull
    @ApiModelProperty(notes = "The day during which this class is given", required = true)
    private String day;
    @NonNull
    @ApiModelProperty(notes = "The session during which this class is given", required = true)
    private Integer session;
    @ApiModelProperty(notes = "The instructor who teach this class")
    private InstructorDTO instructor = null;
    @ApiModelProperty(notes = "The course taught in this class")
    private CourseDTO course = null;
    @ApiModelProperty(notes = "The students enrolled in this class")
    private List<StudentDTO> enrolledStudents = new ArrayList<>();

    public ClassDTO(Class aClass) {
        this.id = aClass.getId();
        this.semester = aClass.getSemester();
        this.day = aClass.getDay();
        this.session = aClass.getSession();
        this.instructor = new InstructorDTO(aClass.getInstructor().getId(), aClass.getInstructor().getFirstName(), aClass.getInstructor().getLastName());
        this.course = new CourseDTO(aClass.getCourse().getId(), aClass.getCourse().getName());
        aClass.getEnrolledStudents().forEach(enrolledStudent -> this.enrolledStudents.add(new StudentDTO(enrolledStudent.getId(), enrolledStudent.getFirstName(), enrolledStudent.getLastName())));
    }
}
