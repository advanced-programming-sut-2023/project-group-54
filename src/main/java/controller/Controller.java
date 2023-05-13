package controller;

import com.google.common.hash.Hashing;
import model.Buildings.Building;
import model.Buildings.StorageBuilding;
import model.Buildings.StorageType;
import model.Game;
import model.Resource;
import model.User;
import view.enums.messages.MapMenuMessage;
import view.enums.messages.SignupMenuMessage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private static User loggedInUser;

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

    public static ArrayList<String> captcha() {
        char[] randomCaptcha = new char[4];
        randomCaptcha[0] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        randomCaptcha[1] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        randomCaptcha[2] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        randomCaptcha[3] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        String captcha = new String(randomCaptcha);
        BufferedImage image = new BufferedImage(144, 32, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setFont(new Font("Dialog", Font.PLAIN, 11));
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.drawString(captcha, 6, 24);
        ArrayList<String> captchaArt = new ArrayList<>();
        captchaArt.add(captcha);
        for (int y = 0; y < 32; y++) {
            StringBuilder asciiArt = new StringBuilder();
            for (int x = 0; x < 144; x++)
                asciiArt.append(image.getRGB(x, y) == -16777216 ? " " : image.getRGB(x, y) == -1 ? "#" : "*");
            if (asciiArt.toString().trim().isEmpty()) continue;
            captchaArt.add(String.valueOf(asciiArt));
        }
        return captchaArt;
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
        return false;
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
}
