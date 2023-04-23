package controller;
import model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    private ArrayList<User> users;
    private static Scanner scanner;

    public static void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static boolean checkIfStayLoggedIn() {

    }

    public static Matcher checkParameter(String toBeChecked, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toBeChecked);
        return matcher.matches() ? matcher : null;
    }

    public static User findUserByUsername(String username) {

    }

    public static void run(){

    }

}
