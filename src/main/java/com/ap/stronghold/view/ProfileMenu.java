package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.ProfileMenuController;
import com.ap.stronghold.model.User;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.ProfileMenuMessage;
import com.ap.stronghold.view.enums.messages.SignupMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class ProfileMenu {
    public static void run() {

        System.out.println("you are in profile menu");
        while (true) {
            String command;
            HashMap<String, ArrayList<String>> options;

            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("profile menu");
            else if (CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if ((options = CommandHandler.parsCommand(Command.CHANGE_USERNAME, command)) != null)
                changeUsername(options);
            else if ((options = CommandHandler.parsCommand(Command.CHANGE_NICKNAME, command)) != null)
                changeNickname(options);
            else if ((options = CommandHandler.parsCommand(Command.CHANGE_PASSWORD, command)) != null)
                changePassword(options);
            else if ((options = CommandHandler.parsCommand(Command.CHANGE_EMAIL, command)) != null)
                changeEmail(options);
            else if ((options = CommandHandler.parsCommand(Command.CHANGE_SLOGAN, command)) != null)
                changeSlogan(options);
            else if (CommandHandler.parsCommand(Command.REMOVE_SLOGAN, command) != null)
                removeSlogan();
            else if (CommandHandler.parsCommand(Command.PROFILE_DISPLAY_HIGH_SCORE, command) != null)
                showHighScore();
            else if (CommandHandler.parsCommand(Command.PROFILE_DISPLAY_RANK, command) != null)
                showRank();
            else if (CommandHandler.parsCommand(Command.DISPLAY_SLOGAN, command) != null)
                displaySlogan();
            else if (CommandHandler.parsCommand(Command.DISPLAY_PROFILE, command) != null)
                displayProfile();
            else
                System.out.println("Invalid command in profile menu");
        }
    }


    private static void changeUsername(HashMap<String, ArrayList<String>> options) {
        String username = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "u":
                    username = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (username == null) {
            System.out.println("username not entered");
            return;
        }
        SignupMenuMessage result = ProfileMenuController.changeUsername(username);
        switch (result) {
            case WRONG_FORMAT_USERNAME -> System.out.println("invalid format for username");
            case USERNAME_EXIST -> System.out.println("username already exists");
            case SUCCESS -> System.out.println("username changed successfully");
        }
        User.saveUser();
    }

    private static void changeNickname(HashMap<String, ArrayList<String>> options) {
        String nickname = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "n":
                    nickname = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (nickname == null) {
            System.out.println("nickname not entered");
            return;
        }
        ProfileMenuMessage result = ProfileMenuController.changeNickname(nickname);
        if (result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("nickname changed successfully");
        User.saveUser();
    }

    public static void changePassword(HashMap<String, ArrayList<String>> options) {
        String oldPassword = null;
        String newPassword = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "o":
                    oldPassword = Controller.buildParameter(options.get(s).get(0));
                    break;
                case "n":
                    newPassword = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (oldPassword == null) {
            System.out.println("oldPassword not entered");
            return;
        } else if (newPassword == null) {
            System.out.println("newPassword not entered");
            return;
        }
        ProfileMenuMessage result = ProfileMenuController.changePassword(oldPassword, newPassword);
        switch (result) {
            case PASSWORD_IS_NOT_CORRECT -> System.out.println("Current password is incorrect");
            case PASSWORD_IS_NOT_NEW -> System.out.println("Please enter a new password");
            case SUCCESS -> {
                if (!MainMenu.captchaChecker()) {
                    System.out.println("failed because of multiple wrong answers for captcha please try again from beginning");
                    return;
                } else {
                    System.out.println("please enter your new password again");
                    String newPasswordConfirmation = MainMenu.getScanner().nextLine();
                    ProfileMenuMessage confirmationResult = ProfileMenuController.changePasswordConfirmation(newPassword, newPasswordConfirmation);
                    if (confirmationResult.equals(ProfileMenuMessage.SUCCESS)) {
                        System.out.println("password changed successfully");
                    } else System.out.println("password confirmation failed please try again from beginning");

                }
            }
            default -> passwordErrorsPrint(result);
        }
        User.saveUser();
    }

    private static void passwordErrorsPrint(ProfileMenuMessage profileMenuMessage) {
        switch (profileMenuMessage) {
            case WRONG_FORMAT_PASSWORD_LENGTH:
                System.out.println("password length is too low at least 6 is needed");
                break;
            case WRONG_FORMAT_PASSWORD_LETTERS:
                System.out.println("your password should contain uppercase and lowercase letters and numbers");
                break;
            case WRONG_FORMAT_PASSWORD_SPECIAL:
                System.out.println("your password should have at least a special character");
                break;
        }
    }

    public static void changeEmail(HashMap<String, ArrayList<String>> options) {
        String email = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "e":
                    email = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (email == null) {
            System.out.println("email not entered");
            return;
        }
        SignupMenuMessage result = ProfileMenuController.changeEmail(email);
        switch (result) {
            case WRONG_FORMAT_EMAIL -> System.out.println("invalid format for email");
            case EMAIL_EXIST -> System.out.println("email already exists");
            case SUCCESS -> System.out.println("email changed successfully");
        }
        User.saveUser();
    }

    public static void changeSlogan(HashMap<String, ArrayList<String>> options) {
        String slogan = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "s":
                    slogan = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if (slogan == null) {
            System.out.println("slogan not entered");
            return;
        }
        ProfileMenuMessage result = ProfileMenuController.changeSlogan(slogan);
        if (result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("slogan changed successfully");
        User.saveUser();
    }

    public static void removeSlogan() {
        ProfileMenuMessage result = ProfileMenuController.removeSlogan();
        if (result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("slogan removed successfully");
        User.saveUser();
    }

    public static void showHighScore() {
        int highScore = Controller.getLoggedInUser().getHighScore();
        System.out.println(highScore);
    }

    public static void showRank() {
        int rank = Controller.getLoggedInUser().getUserRank();
        System.out.println(rank);
    }

    public static void displaySlogan() {
        String result = ProfileMenuController.displaySlogan();
        System.out.println(result);

    }

    public static void displayProfile() {
        String result = ProfileMenuController.displayProfile();
        System.out.println(result.trim());
    }
}
