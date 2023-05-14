package controller;

import model.User;
import view.enums.messages.ProfileMenuMessage;
import view.enums.messages.SignupMenuMessage;

public class ProfileMenuController {
    private static User user = Controller.getLoggedInUser();


    public static SignupMenuMessage changeUsername(String username) {
        SignupMenuMessage result = Controller.checkUsernameValidity(username);
        if (result.equals(SignupMenuMessage.SUCCESS)) {
            user.setUsername(username);
        }
        return result;
    }

    public static ProfileMenuMessage changeNickname(String newNickname) {
        user.setNickname(newNickname);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changePassword(String oldPassword, String newPassword) {
        if (!(user.isPasswordValid(oldPassword))) {
            return ProfileMenuMessage.PASSWORD_IS_NOT_CORRECT;
        }
        if (oldPassword.equals(newPassword)) {
            return ProfileMenuMessage.PASSWORD_IS_NOT_NEW;
        }
        SignupMenuMessage checkPasswordFormat = Controller.checkPasswordValidity(newPassword);
        switch (checkPasswordFormat) {
            case WRONG_FORMAT_PASSWORD_SPECIAL -> {
                return ProfileMenuMessage.WRONG_FORMAT_PASSWORD_SPECIAL;
            }
            case WRONG_FORMAT_PASSWORD_LENGTH -> {
                return ProfileMenuMessage.WRONG_FORMAT_PASSWORD_LENGTH;
            }
            case WRONG_FORMAT_PASSWORD_LETTERS -> {
                return ProfileMenuMessage.WRONG_FORMAT_PASSWORD_LETTERS;
            }
            case SUCCESS -> {
                return ProfileMenuMessage.SUCCESS;
            }
        }
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changePasswordConfirmation(String newPassword, String newPasswordConfirmation) {
        if (newPassword.equals(newPasswordConfirmation)) {
            user.setPassword(newPassword);
            return ProfileMenuMessage.SUCCESS;
        } else return ProfileMenuMessage.PASSWORD_CONFIRMATION_FAILED;

    }

    public static SignupMenuMessage changeEmail(String newEmail) {
        SignupMenuMessage result = Controller.checkEmailValidity(newEmail);
        if (result.equals(SignupMenuMessage.SUCCESS)) {
            user.setEmail(newEmail);
        }
        return result;

    }

    public static ProfileMenuMessage changeSlogan(String newSlogan) {
        user.setSlogan(newSlogan);
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage removeSlogan() {
        user.setSlogan(null);
        return ProfileMenuMessage.SUCCESS;
    }

    public static String showHighScore() {
        return "";
    }


    public static String displaySlogan() {
        String slogan = user.getSlogan();
        if (slogan == null) return "Slogan is empty!";
        else return slogan;


    }

    public static String displayProfile() {
        String result = "your Profile:\n";
        result += "username : " + user.getUsername() + "\n";
        result += "highScore : "+ user.getHighScore()+"\n";
        result += "rank : "+ user.getUserRank()+"\n";
        result += "nickname : " + user.getNickname() + "\n";
        result += "email : " + user.getEmail() + "\n";
        if (user.getSlogan() != null) {
            result += "slogan : " + user.getSlogan() + "\n";
        }
        return result;
    }
}
