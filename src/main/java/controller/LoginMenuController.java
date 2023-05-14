package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.User;
import view.MainMenu;
import view.enums.messages.LoginMenuMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            String filePath = new File("").getAbsolutePath().concat("/src/main/java/model/data/stayLoggedInUser.json");
            FileWriter fileWriter;
            try {
                fileWriter = new FileWriter(filePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            gson.toJson(user, fileWriter);
            try {
                fileWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Controller.setLoggedInUser(user);
    }

    public static void setLoggedInUser(User user) {
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
