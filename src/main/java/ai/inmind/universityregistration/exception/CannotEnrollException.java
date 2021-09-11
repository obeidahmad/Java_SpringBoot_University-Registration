package ai.inmind.universityregistration.exception;

import ai.inmind.universityregistration.helper.LocaleParam;
import lombok.Getter;

@Getter
public class CannotEnrollException extends RuntimeException {
    private static final long serialVersionUID = 2L;
    private final String message;

    public CannotEnrollException(Long studentId, Long classId, String cause) {
        super(String.format(LocaleParam.getMessage("cannotEnroll"), studentId, classId, LocaleParam.getMessage(cause)));
        this.message = String.format(LocaleParam.getMessage("cannotEnroll"), studentId, classId, LocaleParam.getMessage(cause));
    }
}
