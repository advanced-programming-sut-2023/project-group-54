package controller;

import model.*;
import view.Menu;
import view.commands.inputs.SignupMenuCommands;
import view.commands.outputs.LoginMenuOutput;
import view.commands.outputs.SignupMenuOutput;


import java.util.regex.Matcher;

public class LoginController {

    private User user;


    public String getUsername() {
        return user.getUsername();
    }

    public LoginMenuOutput login(String username, String password, String stayLoggedIn) {
        user = Controller.findUserByUsername(username);
        if (user == null) return LoginMenuOutput.USER_NOT_FOUND;
        LoginMenuOutput result = passwordChecker(password);
        if (result.equals(LoginMenuOutput.LOGGED_IN)) {
            if (Menu.captchaChecker()) setLoggedInUser(stayLoggedIn);
            else return LoginMenuOutput.FAILED_DURING_CAPTCHA;
        }
        return result;
    }

    public void setLoggedInUser(String stayLoggedIn) {
        if (stayLoggedIn != null) Game.setStayLoggedInUser(user);
        Controller.setLoggedInUser(user);
    }

    public LoginMenuOutput passwordChecker(String password) {
        if (user.isPasswordValid(password)) return LoginMenuOutput.LOGGED_IN;
        return LoginMenuOutput.WRONG_PASSWORD;
    }

    public String passwordRecovery(String username) {
        user = Controller.findUserByUsername(username);
        if (user == null) return LoginMenuOutput.USER_NOT_FOUND.getRegex();
        switch (user.getQuestionNumber()) {
            case 1:
                return "What is your father’s name?";
            case 2:
                return "What was your first pet’s name?";
            default:
                return "What is your mother’s last name?";
        }
    }

    public boolean checkAnswer(String answer) {
        return answer.equals(user.getQuestionAnswer());
    }


    public LoginMenuOutput setNewPassword(String password) {
        user.setPassword(password);
        return LoginMenuOutput.NEW_PASSWORD_SET;
    }

    public String logout() {
        Controller.setLoggedInUser(null);
        return LoginMenuOutput.LOGGED_OUT_FIRST_PART.getRegex() + user.getUsername() + LoginMenuOutput.LOGGED_OUT.getRegex();
    }
}
