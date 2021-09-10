package ai.inmind.universityregistration.exception;

import ai.inmind.universityregistration.configuration.LocalizationConfig;
import ai.inmind.universityregistration.helper.LocaleParam;
import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private final String message;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format(LocalizationConfig.messageSource().getMessage("resourceNotFound",null, LocaleParam.getLocale()),
                LocalizationConfig.messageSource().getMessage(resourceName,null, LocaleParam.getLocale()),
                LocalizationConfig.messageSource().getMessage(fieldName,null, LocaleParam.getLocale()),
                fieldValue));

        this.message = String.format(LocalizationConfig.messageSource().getMessage("resourceNotFound",null, LocaleParam.getLocale()),
                LocalizationConfig.messageSource().getMessage(resourceName,null, LocaleParam.getLocale()),
                LocalizationConfig.messageSource().getMessage(fieldName,null, LocaleParam.getLocale()),
                fieldValue);
    }
}
