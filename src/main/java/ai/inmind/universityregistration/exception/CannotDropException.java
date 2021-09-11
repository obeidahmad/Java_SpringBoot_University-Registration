package ai.inmind.universityregistration.exception;

import ai.inmind.universityregistration.configuration.LocalizationConfig;
import ai.inmind.universityregistration.helper.LocaleParam;
import lombok.Getter;

@Getter
public class CannotDropException extends RuntimeException{
    private static final long serialVersionUID = 3L;
    private final String message;

    public CannotDropException(Long studentId, Long classId, String cause) {
        super(String.format(LocalizationConfig.messageSource().getMessage("cannotDrop", null, LocaleParam.getLocale()),
                studentId, classId, LocalizationConfig.messageSource().getMessage(cause, null, LocaleParam.getLocale())));
        this.message = String.format(LocalizationConfig.messageSource().getMessage("cannotDrop", null, LocaleParam.getLocale()),
                studentId, classId, LocalizationConfig.messageSource().getMessage(cause, null, LocaleParam.getLocale()));
    }
}
