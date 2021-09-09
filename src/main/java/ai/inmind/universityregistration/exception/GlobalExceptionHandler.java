package ai.inmind.universityregistration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<String> blogNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        return new ResponseEntity<>(resourceNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
