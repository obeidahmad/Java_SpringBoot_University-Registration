package ai.inmind.universityregistration.service.impl;

import ai.inmind.universityregistration.exception.CannotEnrollException;
import ai.inmind.universityregistration.model.Class;
import ai.inmind.universityregistration.model.Course;
import ai.inmind.universityregistration.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentEnrollmentService {
    private final StudentServiceImpl studentService;
    private final ClassServiceImpl classService;

    public StudentEnrollmentService(StudentServiceImpl studentService, ClassServiceImpl classService) {
        this.studentService = studentService;
        this.classService = classService;
    }

    public Student enrollInClass(Long studentId, Long classId) {
        Student student = studentService.getElementById(studentId);
        Class enrollment = classService.getElementById(classId);

        Course course = enrollment.getCourse();

        List<Class> classes = student.getEnrolledIn();
        List<Course> courses = new ArrayList<>();
        classes.forEach((c) -> courses.add(c.getCourse()));

        checkEnrollment(student, enrollment, course, classes, courses);

        classes.add(enrollment);
        student.setEnrolledIn(classes);
        return studentService.updateElement(studentId, student);
    }

    private void checkEnrollment(Student student, Class enrollment, Course course, List<Class> classes, List<Course> courses) {
        if (classes.contains(enrollment) && courses.contains(course)) throw new CannotEnrollException(student, enrollment, "Already Enrolled.");
        if (!courses.containsAll(course.getPrerequisites())) throw new CannotEnrollException(student, enrollment, "Prerequisites not satisfied.");
        if (classes.stream().anyMatch((c) -> Objects.equals(c.getSemester(), enrollment.getSemester()) && Objects.equals(c.getDay(), enrollment.getDay()) && Objects.equals(c.getSession(), enrollment.getSession())))
            throw new CannotEnrollException(student, enrollment, "Conflicting classes.");
    }
}
