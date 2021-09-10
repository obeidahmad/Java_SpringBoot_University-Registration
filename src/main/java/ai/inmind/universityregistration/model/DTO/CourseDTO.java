package ai.inmind.universityregistration.model.DTO;

import ai.inmind.universityregistration.model.Course;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private List<CourseDTO> prerequisites = new ArrayList<>();
    private List<ClassDTO> classes = new ArrayList<>();

    public CourseDTO(Course course) {
        this.id = course.getId();
        this.name = course.getName();
        course.getPrerequisites().forEach(prerequisite -> this.prerequisites.add(new CourseDTO(prerequisite.getId(), prerequisite.getName())));
        course.getClasses().forEach(aClass -> this.classes.add(new ClassDTO(aClass.getId(), aClass.getSemester(), aClass.getDay(), aClass.getSession())));
    }

    public CourseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
