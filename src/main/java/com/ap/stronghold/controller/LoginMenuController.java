package com.ap.stronghold.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ap.stronghold.model.User;
import com.ap.stronghold.view.MainMenu;
import com.ap.stronghold.view.enums.messages.LoginMenuMessage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class LoginMenuController {
    private static User user;

    public static LoginMenuMessage findUsername(String username){
        user = User.findUserByUsername(username);
        if (user == null) return LoginMenuMessage.USER_NOT_FOUND;
        return LoginMenuMessage.SUCCESS;
    }
    public static LoginMenuMessage loginUser(String username, String password, boolean stayLoggedIn) {
        LoginMenuMessage result = findUsername(username);
         if(result != LoginMenuMessage.SUCCESS)
             return result;

        result = passwordChecker(password);
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
            String filePath = new File("").getAbsolutePath().concat("/src/main/java/com/ap/stronghold/model/data/stayLoggedInUser.json");
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

    public static LoginMenuMessage passwordChecker(String password) {
        if (user.isPasswordValid(password)) return LoginMenuMessage.SUCCESS;
        return LoginMenuMessage.WRONG_PASSWORD;
    }

    public static LoginMenuMessage forgetPasswordUsernameCheck(String username) {
        return findUsername(username);
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
        User.saveUser();
    }

    public static LoginMenuMessage logout() {
        Controller.setLoggedInUser(null);
        String filePath = new File("").getAbsolutePath().concat("/src/main/java/com/ap/stronghold/model/data/stayLoggedInUser.json");
        (new File(filePath)).delete();
        try {
            new FileWriter(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return LoginMenuMessage.SUCCESS;
    }

}
