package ai.inmind.universityregistration.helper;

import ai.inmind.universityregistration.configuration.LocalizationConfig;

import java.util.Locale;

public class LocaleParam {
    private static Locale locale;

    public static void setLocale(String localeString) {
        locale = new Locale(localeString);
    }

    public static String getMessage(String messageId) {
        return LocalizationConfig.messageSource().getMessage(messageId, null, locale);
    }
}
