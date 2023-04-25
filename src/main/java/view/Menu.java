package view;

import controller.*;
import view.commands.CommonEnums;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private GameMenu gameMenu;

    public static Matcher getMatcher(String command, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

    public static boolean captchaChecker() {

        for (int i = 0; i < 10; i++) {
            System.out.println(CommonEnums.ENTER_CAPTCHA.getRegex());
            ArrayList<String> captcha = Controller.captcha();
            for (String s : captcha) {
                System.out.println(s);
            }
            String correctCaptcha = captcha.get(0);
            String userInput = scanner.nextLine();
            if (userInput.equals(correctCaptcha)) return true;
            System.out.println(CommonEnums.WRONG_CAPTCHA.getRegex() + (9 - i) + " attempts left");
        }
        return false;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public void run() {
        SignupMenu signupMenu = new SignupMenu();
        LoginMenu loginMenu = new LoginMenu();
        if (Controller.checkIfStayLoggedIn()) {
            this.gameMenu = new GameMenu();
            gameMenu.run();
        }
        String command;
        while (true) {
            command = Menu.getScanner().nextLine();
            if (command.matches("\\s*sign\\s+up\\s+menu\\s*")) signupMenu.run();
            else if (command.matches("\\s*login\\s+menu\\s*")) loginMenu.run();
            else if (command.matches(CommonEnums.EXIT.getRegex())) return;
            else System.out.println("Invalid command");
        }
    }
}
