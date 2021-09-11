package ai.inmind.universityregistration.service.impl;

import ai.inmind.universityregistration.exception.CannotEnrollException;
import ai.inmind.universityregistration.exception.CannotDropException;
import ai.inmind.universityregistration.model.Class;
import ai.inmind.universityregistration.model.Course;
import ai.inmind.universityregistration.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Class aClass = classService.getElementById(classId);

        Course course = aClass.getCourse();

        List<Class> classes = student.getEnrolledIn();
        List<Course> courses = new ArrayList<>();
        classes.forEach(c -> courses.add(c.getCourse()));

        checkEnrollment(student, aClass, course, classes, courses);

        classes.add(aClass);
        student.setEnrolledIn(classes);
        return studentService.updateElement(studentId, student);
    }

    private void checkEnrollment(Student student, Class aClass, Course course, List<Class> classes, List<Course> courses) {
        if (classes.contains(aClass) && courses.contains(course)) throw new CannotEnrollException(student.getId(), aClass.getId(), "cannotEnrollCause1");
        if (!courses.containsAll(course.getPrerequisites())) throw new CannotEnrollException(student.getId(), aClass.getId(), "cannotEnrollCause2");
        if (classes.stream().anyMatch(c -> Objects.equals(c.getSemester(), aClass.getSemester()) && Objects.equals(c.getDay(), aClass.getDay()) && Objects.equals(c.getSession(), aClass.getSession())))
            throw new CannotEnrollException(student.getId(), aClass.getId(), "cannotEnrollCause3");
    }

    public Student dropClass(Long studentId, Long classId) {
        Student student = studentService.getElementById(studentId);
        Class aClass = classService.getElementById(classId);

        List<Class> enrolledIn = student.getEnrolledIn();

        enrolledIn.forEach(c -> {
            if (c.getCourse().getPrerequisites().stream().anyMatch(course -> course.equals(aClass.getCourse()))) throw new CannotDropException(studentId, classId, "cannotDropCause2");
        });

        if(!enrolledIn.remove(aClass)) throw new CannotDropException(studentId, classId, "cannotDropCause1");
        student.setEnrolledIn(enrolledIn);
        return studentService.updateElement(studentId, student);
    }
}
