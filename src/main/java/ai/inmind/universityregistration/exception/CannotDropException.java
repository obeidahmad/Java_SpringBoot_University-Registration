package ai.inmind.universityregistration.exception;

import ai.inmind.universityregistration.helper.LocaleParam;
import lombok.Getter;

@Getter
public class CannotDropException extends RuntimeException {
    private static final long serialVersionUID = 3L;
    private final String message;

    public CannotDropException(Long studentId, Long classId, String cause) {
        super(String.format(LocaleParam.getMessage("cannotDrop"), studentId, classId, LocaleParam.getMessage(cause)));
        this.message = String.format(LocaleParam.getMessage("cannotDrop"), studentId, classId, LocaleParam.getMessage(cause));
    }
}
