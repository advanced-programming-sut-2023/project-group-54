package controller;

import model.User;
import view.MainMenu;
import view.enums.messages.LoginMenuMessage;

public class LoginMenuController {
    private static User user;

    public static LoginMenuMessage loginUser(String username, String password, boolean stayLoggedIn) {
        user = User.findUserByUsername(username);
        if (user == null) return LoginMenuMessage.USER_NOT_FOUND;
        LoginMenuMessage result = passwordChecker(password);
        if (result.equals(LoginMenuMessage.SUCCESS)) {
            if (MainMenu.captchaChecker()) {
                setLoggedInUser(stayLoggedIn);
            }
            else {
                return LoginMenuMessage.FAILED_DURING_CAPTCHA;
            }
        }
        return result;
    }

    public static void setLoggedInUser(boolean stayLoggedIn) {
        if(stayLoggedIn){
            //store data
        }
        Controller.setLoggedInUser(user);
    }

    public static LoginMenuMessage passwordChecker(String password) {
        if (user.isPasswordValid(password)) return LoginMenuMessage.SUCCESS;
        return LoginMenuMessage.WRONG_PASSWORD;
    }

    public static LoginMenuMessage forgetPasswordUsernameCheck(String username) {
        user = User.findUserByUsername(username);
        if (user == null) return LoginMenuMessage.USER_NOT_FOUND;
        return LoginMenuMessage.SUCCESS;
    }
    public static int getQuestion(String username){
        user = User.findUserByUsername(username);
        return user.getQuestionNumber();
    }
    public static boolean checkAnswer(String answer) {
        return answer.equals(user.getQuestionAnswer());
    }

    public static void setNewPassword(String password) {
        user.setPassword(Controller.hashString(password));
    }

    public static LoginMenuMessage logout() {
        Controller.setLoggedInUser(null);
        return LoginMenuMessage.SUCCESS;
    }
}
