package view;
import controller.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {
//    private static final Scanner scanner = new Scanner(System.in);
    private GameMenu gameMenu;
    public static Matcher getMatcher(String command, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(command);
        return matcher.matches() ? matcher : null;
    }

//    public static Scanner getScanner() {
//        return scanner;
//    }

    public String run() {
        if (Controller.checkIfStayLoggedIn()) {
            this.gameMenu = new GameMenu();
            gameMenu.run();
        }
        String command;
        Matcher matcher;
        while (true) {
//            command = Menu.getScanner().nextLine();
            //if signup
            //if login menu
            //if exit
        }
    }
}
