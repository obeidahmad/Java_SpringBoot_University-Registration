package ai.inmind.universityregistration.helper;

import java.util.Locale;

public class LocaleParam {
    private static Locale locale;

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(String localeString) {
        locale = new Locale(localeString);
    }
}
