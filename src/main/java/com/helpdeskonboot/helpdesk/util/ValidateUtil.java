package com.helpdeskonboot.helpdesk.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidateUtil {

    private final String regExp = "[0-9a-zA-Z\\~\\.\\s\\\"\\(\\)\\,\\:\\;\\<\\>\\@\\[\\]\\!\\#\\$\\%\\&\\'\\*\\+\\-\\/\\=\\?\\^\\_\\`\\{\\|\\}]";

    public boolean userEmailValidate(String userEmail) {
        if (userEmail.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            return true;
        }
        return false;
    }

    public boolean userPasswordValidate(String userPassword) {
        if ((userPassword.length() > 5)&&(userPassword.length() < 21)) {
            if (!userPassword.equals(userPassword.replaceAll("[A-Z]", ""))) {
                if (!userPassword.equals(userPassword.replaceAll("[a-z]", ""))) {
                    if (!userPassword.equals(userPassword.replaceAll("[\\~\\.\\\"\\(\\)\\,\\:\\;\\<\\>\\@\\[\\]\\!\\#\\$\\%\\&\\'\\*\\+-\\/\\=\\?\\^\\_\\`\\{\\|\\}]", ""))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean ticketDescriptionValidate(String ticketDescription) {
        if ((ticketDescription.replaceAll(regExp, "").equals(""))&&
                (ticketDescription.length() <= 500)) {
            return true;
        }
        return false;
    }

    public boolean ticketNameValidate(String ticketName) {
        if ((ticketName.replaceAll(regExp, "").equals(""))&&
                (ticketName.length() <= 100)) {
            return true;
        }
        return false;
    }

    public boolean ticketDesiredResolutionDateValidate(LocalDateTime desiredResolutionDate) {
       return true;
    }

    public boolean commentTextValidate(String commetText) {
        if ((commetText.replaceAll(regExp, "").equals(""))&&
                (commetText.length() <= 500)) {
            return true;
        }
        return false;
    }

    public boolean feedbackTextValidate(String feedbackText) {
        if ((feedbackText.replaceAll(regExp, "").equals(""))&&
                (feedbackText.length() <= 500)) {
            return true;
        }
        return false;
    }

    public boolean feedbackRateValidate(Long rate) {
        if ((rate < 6)&&(rate > 0)) {
            return true;
        }
        return false;
    }
}
