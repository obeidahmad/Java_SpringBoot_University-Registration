package ai.inmind.universityregistration.exception;

import ai.inmind.universityregistration.model.Class;
import ai.inmind.universityregistration.model.Student;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class CannotEnrollException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private final String message;

    public CannotEnrollException(Student student, Class enrollment, String cause) {
        super(String.format("%s cannot enroll in %s.", student, enrollment), new Throwable(cause));
        this.message = String.format("%s cannot enroll in %s, because %s", student, enrollment, cause);
    }
}
