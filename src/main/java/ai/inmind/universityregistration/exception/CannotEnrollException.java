package ai.inmind.universityregistration.exception;

import ai.inmind.universityregistration.model.Class;
import ai.inmind.universityregistration.model.Student;
import lombok.Getter;

@Getter
public class CannotEnrollException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private final String message;

    public CannotEnrollException(Student student, Class aClass, String cause) {
        super(String.format("Student of id=%s cannot enroll in Class of id=%s.", student.getId(), aClass.getId()), new Throwable(cause));
        this.message = String.format("Student of id=%s cannot enroll in Class of id=%s, because %s.", student.getId(), aClass.getId(), cause);
    }
}
