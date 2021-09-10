package ai.inmind.universityregistration.helper;

import ai.inmind.universityregistration.configuration.LocalizationConfig;

public class MessageBuilder {
    public static String messageBuilder(String model, String mainMessage) {
        model = LocalizationConfig.messageSource().getMessage(model,null, LocaleParam.getLocale());
        mainMessage = LocalizationConfig.messageSource().getMessage(mainMessage,null, LocaleParam.getLocale());
        return model + " " + mainMessage;
    }
}
