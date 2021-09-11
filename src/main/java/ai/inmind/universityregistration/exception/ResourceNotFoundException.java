package ai.inmind.universityregistration.exception;

import ai.inmind.universityregistration.helper.LocaleParam;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format(LocaleParam.getMessage("resourceNotFound"), LocaleParam.getMessage(resourceName), LocaleParam.getMessage(fieldName), fieldValue));
        this.message = String.format(LocaleParam.getMessage("resourceNotFound"), LocaleParam.getMessage(resourceName), LocaleParam.getMessage(fieldName), fieldValue);
    }
}
