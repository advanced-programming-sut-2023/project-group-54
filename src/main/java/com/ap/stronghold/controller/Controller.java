package com.ap.stronghold.controller;

import com.google.common.hash.Hashing;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.Resource;
import com.ap.stronghold.model.User;
import com.ap.stronghold.view.enums.messages.SignupMenuMessage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private static User loggedInUser;
    private static boolean exit;

    public static void setLoggedInUser(User loggedInUser) {
        Controller.loggedInUser = loggedInUser;
    }
    public static SignupMenuMessage checkPasswordValidity(String password) {
        if (!password.matches("(?=^\\S{6,}$).*"))
            return SignupMenuMessage.WRONG_FORMAT_PASSWORD_LENGTH;
        if (!password.matches("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).*"))
            return SignupMenuMessage.WRONG_FORMAT_PASSWORD_LETTERS;
        if (!password.matches("(?=.*[^A-Za-z0-9]).*"))
            return SignupMenuMessage.WRONG_FORMAT_PASSWORD_SPECIAL;
        return SignupMenuMessage.SUCCESS;
    }

    public static SignupMenuMessage checkUsernameValidity(String username) {
        if (!username.matches("^[A-Za-z_]+$"))
            return SignupMenuMessage.WRONG_FORMAT_USERNAME;
        if (User.findUserByUsername(username) != null)
            return SignupMenuMessage.USERNAME_EXIST;
        return SignupMenuMessage.SUCCESS;
    }

    public static SignupMenuMessage checkUsernameNew(String username) {

        return SignupMenuMessage.SUCCESS;
    }

    public static SignupMenuMessage checkEmailValidity(String email) {
        if (!email.matches("^[A-Za-z_.0-9]+@(?<u>[A-Za-z_0-9]+)\\.(?<a>[A-Za-z_0-9]+)$"))
            return SignupMenuMessage.WRONG_FORMAT_EMAIL;
        if (User.findUserByEmail(email) != null)
            return SignupMenuMessage.EMAIL_EXIST;
        return SignupMenuMessage.SUCCESS;
    }

    public static String buildParameter(String parameter) {
        return parameter.replaceAll("\"", "");
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static int captcha() {
        File file = new File(Controller.class.getResource("/com/ap/stronghold/Media/Captcha").getPath());
        int ran = new Random().nextInt(1, 50);
        int i = 1;
        if(file.listFiles() != null){
            for (File listFile : file.listFiles()) {
                if(listFile.getName().split("\\.")[1].equals("png")){
                    if(i == ran){
                        return Integer.parseInt(listFile.getName().split("\\.")[0]);
                    }
                    i++;
                }
            }
        }
        return 0;
    }

    public static void timer(int seconds) {
        try {
            Thread.sleep(1000 * (5L * (seconds + 1)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static String hashString(String str){
        return Hashing.sha256().hashString(str, StandardCharsets.UTF_8).toString();
    }
    public static boolean checkIfStayLoggedIn() {
        Gson gson = new Gson();
        String filePath = new File("").getAbsolutePath().concat("/src/main/java/com/ap/stronghold/model/data/stayLoggedInUser.json");
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        User user = gson.fromJson(fileReader, new TypeToken<User>() {}.getType());
        if(user == null)
            return false;
        if(User.findUserByUsername(user.getUsername()) != null){
            setLoggedInUser(User.findUserByUsername(user.getUsername()));
            return true;
        }else return false;
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static void setExit(boolean exit) {
        Controller.exit = exit;
    }

    public static boolean isExit() {
        return exit;
    }
}
