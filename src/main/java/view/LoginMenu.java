package view;

import controller.*;
import view.commands.CommonEnums;
import view.commands.inputs.LoginMenuCommands;
import view.commands.inputs.SignupMenuCommands;
import view.commands.outputs.LoginMenuOutput;
import view.commands.outputs.SignupMenuOutput;

import java.util.regex.Matcher;

public class LoginMenu {
    private LoginController loginController;

    public LoginMenu() {
        this.loginController = new LoginController();
    }

    public String run() {
        String command;
        Matcher matcher;
        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("\\s*back\\s*")) return "menu";
            else if (command.matches(CommonEnums.SHOW_MENU.getRegex())) System.out.println("Login menu");
            else if ((matcher = Menu.getMatcher(command, LoginMenuCommands.Login.getRegex())) != null)
                loginUser(matcher);
            else if ((matcher = Menu.getMatcher(command, LoginMenuCommands.FORGET_PASSWORD.getRegex())) != null)
                System.out.println(forgetPassword(matcher));
            else if (command.matches("\s*logout\s*")) logout();
            else System.out.println("Invalid command in sign up menu");
        }
    }

    private void loginUser(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String stayLoggedIn = matcher.group("stayLoggedIn");
        LoginMenuOutput result = loginController.login(username, password, stayLoggedIn);
        switch (result) {
            case LOGGED_IN:
                System.out.println(result.getRegex() + loginController.getUsername());
                break;
            case WRONG_PASSWORD:
                wrongPassword(stayLoggedIn);
                break;
            default:
                System.out.println(result.getRegex());
                break;
        }
    }

    public void wrongPassword(String stayLoggedIn) {
        System.out.println("please enter your password again");
        String command;
        for (int i = 0; i < 3; i++) {
            System.out.println(LoginMenuOutput.WRONG_PASSWORD.getRegex() + " please wait for " + ((i + 1) * 5) + " seconds");
            Controller.timer(i);
            command = Controller.buildParameter(Menu.getScanner().nextLine());
            if (loginController.passwordChecker(command).equals(LoginMenuOutput.LOGGED_IN)) {
                if(!Menu.captchaChecker()) {
                    System.out.println(LoginMenuOutput.FAILED_DURING_CAPTCHA.getRegex());
                    return;
                }
                loginController.setLoggedInUser(stayLoggedIn);
                System.out.println(LoginMenuOutput.LOGGED_IN.getRegex() + loginController.getUsername());
                return;
            }
        }
        System.out.println(LoginMenuOutput.LOGIN_FAILED.getRegex());
    }

    private String forgetPassword(Matcher matcher) {
        String username = matcher.group("username");
        String result = loginController.passwordRecovery(username);
        if (result.equals(LoginMenuOutput.USER_NOT_FOUND.getRegex())) return result;
        System.out.println(result);
        for (int i = 0; i < 3; i++) {
            if (loginController.checkAnswer(Controller.buildParameter(Menu.getScanner().nextLine()))) {
                for (int j = 0; j < 3; j++) {
                    String password = Controller.buildParameter(Menu.getScanner().nextLine());
                    SignupMenuOutput signupMenuOutput = Controller.checkPasswordValidity(password);
                    if (signupMenuOutput.equals(SignupMenuOutput.PASSWORD_OK)) {
                        if (!Menu.captchaChecker()) return LoginMenuOutput.FAILED_DURING_CAPTCHA.getRegex();
                        return loginController.setNewPassword(password).getRegex();
                    }
                    System.out.println(signupMenuOutput.getRegex());
                }
                return LoginMenuOutput.FAILED_DURING_SETTING_NEW_PASSWORD.getRegex();
            }
            System.out.println(LoginMenuOutput.WRONG_ANSWER.getRegex() + (5 * (i+1)) +" seconds");
            Controller.timer(i);
        }
        return LoginMenuOutput.FAILED_DURING_ANSWERING_QUESTION.getRegex();
    }

    private void logout() {
        if(!Menu.captchaChecker()) System.out.println(LoginMenuOutput.FAILED_DURING_CAPTCHA.getRegex());
        else System.out.println(loginController.logout());
    }
}
