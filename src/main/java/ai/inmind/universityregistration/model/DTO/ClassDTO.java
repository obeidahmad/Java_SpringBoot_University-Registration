package ai.inmind.universityregistration.model.DTO;

import ai.inmind.universityregistration.model.Class;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ClassDTO {
    private Long id;
    private String semester;
    private String day;
    private Integer session;
    private InstructorDTO instructor = null;
    private CourseDTO course = null;
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

    public ClassDTO(Long id, String semester, String day, Integer session) {
        this.id = id;
        this.semester = semester;
        this.day = day;
        this.session = session;
    }
}
