package controller;

import model.Game;
import model.User;
import view.commands.inputs.SignupMenuCommands;
import view.commands.outputs.SignupMenuOutput;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private static User loggedInUser;

    public static void setLoggedInUser(User loggedInUser) {
        Controller.loggedInUser = loggedInUser;
    }

    public static boolean checkIfStayLoggedIn() {
        return true;
    }

    public static Matcher checkParameter(String toBeChecked, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toBeChecked);
        return matcher.matches() ? matcher : null;
    }

    public static User findUserByUsername(String username) {
        for (User user : Game.getUsers()) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public static User findEmail(String email) {
        for (User user : Game.getUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) return user;
        }
        return null;
    }

    public static String buildParameter(String parameter) {
        return parameter.replaceAll("\"", "");
    }

    public static SignupMenuOutput checkPasswordValidity(String password) {
        if (!password.matches(SignupMenuCommands.Password_LENGTH.getRegex()))
            return SignupMenuOutput.WRONG_FORMAT_PASSWORD_LENGTH;
        if (!password.matches(SignupMenuCommands.PASSWORD_LETTER.getRegex()))
            return SignupMenuOutput.WRONG_FORMAT_PASSWORD_LETTERS;
        if (!password.matches(SignupMenuCommands.PASSWORD_SPECIAL_LETTER.getRegex()))
            return SignupMenuOutput.WRONG_FORMAT_PASSWORD_SPECIAL;
        return SignupMenuOutput.PASSWORD_OK;
    }

    public static void timer(int seconds) {
        try {
            Thread.sleep(1000 * (5L * (seconds + 1)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> captcha() {
        char[] randomCaptcha = new char[4];
        randomCaptcha[0] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        randomCaptcha[1] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        randomCaptcha[2] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        randomCaptcha[3] = (char) Math.floor(Math.random() * (90 - 65 + 1) + 65);
        String captcha = Arrays.toString(randomCaptcha);
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
}
