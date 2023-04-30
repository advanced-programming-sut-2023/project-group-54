package controller;

import model.User;
import view.enums.messages.ProfileMenuMessage;

public class ProfileMenuController {
    private static User user;

    public static ProfileMenuMessage changeUsername(String newUsername) {
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeNickname(String newNickname) {
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changePassword(String oldPassword, String newPassword) {
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeEmail(String newEmail) {
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage changeSlogan(String newSlogan) {
        return ProfileMenuMessage.SUCCESS;
    }

    public static ProfileMenuMessage removeSlogan() {
        return ProfileMenuMessage.SUCCESS;
    }

    public static String showHighScore() {
        return "";
    }

    public static String showRank() {
        return "";
    }

    public static String showSlogan() {
        return "";
    }

    public static String showProfile() {
        return "";
    }
}
