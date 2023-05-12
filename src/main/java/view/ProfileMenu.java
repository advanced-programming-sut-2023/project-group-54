package view;
import controller.*;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.ProfileMenuMessage;
import view.enums.messages.SignupMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class ProfileMenu {
    private String run() {
        while (true) {
            String command;
            HashMap<String, ArrayList<String>> options;
            while (true) {
                command = MainMenu.getScanner().nextLine();
                 if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                    System.out.println("profile menu");
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
                 else if (CommandHandler.parsCommand(Command.DISPLAY_SLOGAN, command) != null)
                     displaySlogan();
                 else if (CommandHandler.parsCommand(Command.DISPLAY_PROFILE, command) != null)
                     displayProfile();
                else
                    System.out.println("Invalid command in profile up menu");
            }
        }
    }


    private void changeUsername(HashMap<String, ArrayList<String>> options){
        String username=null;
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
        SignupMenuMessage result=ProfileMenuController.changeUsername(username);
        switch (result){
            case WRONG_FORMAT_USERNAME -> System.out.println("invalid format for username");
            case USERNAME_EXIST -> System.out.println("username already exists");
            case SUCCESS -> System.out.println("username changed successfully");
        }

    }
    private void changeNickname(HashMap<String, ArrayList<String>> options) {
        String nickname=null;
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
            ProfileMenuMessage result=ProfileMenuController.changeNickname(nickname);
            if(result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("nickname changed successfully");

    }

    public void changePassword(HashMap<String, ArrayList<String>> options) {
        String oldPassword=null;
        String newPassword=null;
        for (String s : options.keySet()) {
            switch (s) {
                case "o":
                    oldPassword = Controller.buildParameter(options.get(s).get(0));
                    break;
                case "n":
                    newPassword=Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if(oldPassword == null){
            System.out.println("oldPassword not entered");
            return;
        }
        else if(newPassword == null){
            System.out.println("newPassword not entered");
            return;
        }
        ProfileMenuMessage result=ProfileMenuController.changePassword(oldPassword,newPassword);
        switch (result){
            case PASSWORD_IS_NOT_CORRECT -> System.out.println("Current password is incorrect");
            case PASSWORD_IS_NOT_NEW -> System.out.println("Please enter a new password");
            case WRONG_FORMAT_PASSWORD_LENGTH -> System.out.println("password length is too low at least 6 is needed");
            case WRONG_FORMAT_PASSWORD_LETTERS -> System.out.println("your password should contain uppercase and lowercase letters and numbers");
            case WRONG_FORMAT_PASSWORD_SPECIAL -> System.out.println("your password should contain special letters");
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
        }

    }

    public void changeEmail(HashMap<String, ArrayList<String>> options) {
        String email=null;
        for (String s : options.keySet()) {
            switch (s) {
                case "e":
                    email = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if(email == null){
            System.out.println("email not entered");
            return;
        }
        SignupMenuMessage result=ProfileMenuController.changeEmail(email);
        switch (result){
            case WRONG_FORMAT_EMAIL -> System.out.println("invalid format for email");
            case EMAIL_EXIST -> System.out.println("email already exists");
            case SUCCESS -> System.out.println("email changed successfully");
        }


    }

    public void changeSlogan(HashMap<String, ArrayList<String>> options) {
        String slogan=null;
        for (String s : options.keySet()) {
            switch (s) {
                case "s":
                    slogan = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        if(slogan == null){
            System.out.println("slogan not entered");
            return;
        }
        ProfileMenuMessage result=ProfileMenuController.changeSlogan(slogan);
        if(result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("slogan changed successfully");
    }

    public void removeSlogan() {
        ProfileMenuMessage result=ProfileMenuController.removeSlogan();
        if(result.equals(ProfileMenuMessage.SUCCESS)) System.out.println("slogan removed successfully");
    }

    public void showHighScore(Matcher matcher) {

    }

    public void showRank(Matcher matcher) {

    }

    public void displaySlogan() {
       String result=ProfileMenuController.displaySlogan();
        System.out.println(result);

    }

    public void displayProfile() {
        String result=ProfileMenuController.displayProfile();
        System.out.println(result);
    }
}
