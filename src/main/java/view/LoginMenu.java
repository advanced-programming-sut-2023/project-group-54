package view;

import controller.Controller;
import controller.LoginMenuController;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.LoginMenuMessage;
import view.enums.messages.SignupMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginMenu {
    private boolean inMenu = false;

    public static void logout() {
        LoginMenuMessage result = LoginMenuController.logout();
        if (result.equals(LoginMenuMessage.SUCCESS)) {
            System.out.println("logged out");
        }
    }

    public void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        System.out.println("you are in login menu");
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("login menu");
            else if ((options = CommandHandler.parsCommand(Command.LOGIN, command)) != null) {
                loginUser(options);
                if (inMenu)
                    return;
            } else if ((options = CommandHandler.parsCommand(Command.FORGET_PASSWORD, command)) != null)
                forgetPassword(options);
            else
                System.out.println("Invalid command in login menu");
        }
    }

    private void loginUser(HashMap<String, ArrayList<String>> options) {
        String username = null;
        String password = null;
        boolean stayLoggedIn = false;
        for (String s : options.keySet()) {
            switch (s) {
                case "u":
                    username = Controller.buildParameter(options.get(s).get(0));
                    break;

                case "p":
                    password = Controller.buildParameter(options.get(s).get(0));
                    break;

                case "-stay-logged-in":
                    stayLoggedIn = true;
                    break;
            }
        }
        if (username == null) {
            System.out.println("username not entered");
            return;
        } else if (password == null) {
            System.out.println("password not entered");
            return;
        }
        LoginMenuMessage result = LoginMenuController.loginUser(username, password, stayLoggedIn);
        switch (result) {
            case SUCCESS:
                System.out.println("logged in with user with username: " + username);
                Menu.run();
                inMenu = true;
                return;
            case WRONG_PASSWORD:
                if (wrongPassword(stayLoggedIn)) {
                    Menu.run();
                    inMenu = true;
                }
                return;
            case USER_NOT_FOUND:
                System.out.println("no user with this id exists");
                return;
            case FAILED_DURING_CAPTCHA:
                System.out.println("failed because of multiple wrong answers for captcha");
                return;
        }
    }

    public boolean wrongPassword(boolean stayLoggedIn) {
        System.out.println("please enter your password again");
        String command;
        for (int i = 0; i < 3; i++) {
            System.out.println("please wait for " + (i + 1) * 5 + "seconds");
            Controller.timer(i);
            command = MainMenu.getScanner().nextLine();
            if (LoginMenuController.passwordChecker(command).equals(LoginMenuMessage.SUCCESS)) {
                if (!MainMenu.captchaChecker()) {
                    System.out.println("failed because of multiple wrong answers for captcha");
                    return false;
                }
                LoginMenuController.setLoggedInUser(stayLoggedIn);
                System.out.println("logged in with user with username: " + Controller.getLoggedInUser().getUsername());
                return true;
            }
        }
        System.out.println("you have entered wrong password for 3 times : login failed");
        return false;
    }

    private void forgetPassword(HashMap<String, ArrayList<String>> options) {
        String username = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "u":
                    username = Controller.buildParameter(options.get(s).get(0));
                    break;
            }
        }
        LoginMenuMessage result = LoginMenuController.forgetPasswordUsernameCheck(username);
        int questionNumber = 0;
        switch (result) {
            case SUCCESS:
                questionNumber = LoginMenuController.getQuestion(username);
                break;
            case USER_NOT_FOUND:
                System.out.println("no user with this id exists");
                return;
        }
        switch (questionNumber) {
            case 1:
                System.out.println("What is your father’s name?");
            case 2:
                System.out.println("What was your first pet’s name?");
            default:
                System.out.println("What is your mother’s last name?");
        }
        checkAnswer();
    }

    private void checkAnswer() {
        for (int i = 0; i < 3; i++) {
            if (LoginMenuController.checkAnswer(Controller.buildParameter(MainMenu.getScanner().nextLine()))) {
                for (int j = 0; j < 3; j++) {
                    String password = Controller.buildParameter(MainMenu.getScanner().nextLine());
                    SignupMenuMessage result = Controller.checkPasswordValidity(password);
                    if (result.equals(SignupMenuMessage.SUCCESS)) {
                        if (!MainMenu.captchaChecker()) {
                            System.out.println("failed because of multiple wrong answers for captcha");
                            return;
                        }
                        LoginMenuController.setNewPassword(password);
                        System.out.println("new password has been set");
                        return;
                    } else {
                        passwordErrorsPrint(result);
                    }
                }
                System.out.println("setting new password failed you have entered 3 wrong formats for password");
                return;
            }
            System.out.println("you have entered wrong answer for security question please wait for " + (5 * (i + 1)) + " seconds");
            Controller.timer(i);
        }
        System.out.println("failed because of multiple wrong answers for captcha");
    }

    private void passwordErrorsPrint(SignupMenuMessage signupMenuMessage) {
        switch (signupMenuMessage) {
            case WRONG_FORMAT_PASSWORD_LENGTH:
                System.out.println("password length is too low at least 6 is needed");
                break;
            case WRONG_FORMAT_PASSWORD_LETTERS:
                System.out.println("your password should contain uppercase and lowercase letters and numbers");
                break;
            case WRONG_FORMAT_PASSWORD_SPECIAL:
                System.out.println("WRONG_FORMAT_PASSWORD_SPECIAL");
                break;
        }
    }
}
