package ai.inmind.universityregistration.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
        this.message = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);
    }
}
