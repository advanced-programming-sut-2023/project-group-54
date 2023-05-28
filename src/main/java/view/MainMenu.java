package view;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu extends Application {
    private static final Scanner scanner = new Scanner(System.in);
    public static Stage stage;

    public static boolean captchaChecker() {
        for (int i = 0; i < 10; i++) {
            System.out.println("please enter captcha (only uppercase letters): ");
            ArrayList<String> captcha = Controller.captcha();
            for (String s : captcha) {
                if (!s.equals(captcha.get(0))) {
                    System.out.println(s);
                }
            }
            String correctCaptcha = captcha.get(0);
            String userInput = scanner.nextLine();
            if (userInput.equals(correctCaptcha)) return true;
            System.out.println("you entered captcha wrong you have " + (9 - i) + " attempts left");
        }
        return false;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public void run(String[] args) {
        launch(args);
//        SignupMenu signupMenu = new SignupMenu();
//        LoginMenu loginMenu = new LoginMenu();
        if (Controller.checkIfStayLoggedIn()) {
            System.out.println("you are stay logged in");
            Menu.run();
        }

        String command;
        while (true) {
            if(Controller.isExit()){
                User.saveUser();
            }
            System.out.println("please enter the menu you want");
            command = MainMenu.getScanner().nextLine();
            if (command.matches("\\s*sign\\s+up\\s+menu\\s*"))
                SignupMenu.run();
            else if (command.matches("\\s*login\\s+menu\\s*"))
                LoginMenu.run();
            else if (CommandHandler.parsCommand(Command.EXIT, command) != null){
                User.saveUser();
                return;
            }
            else
                System.out.println("Invalid command");
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        URL url = MainMenu.class.getResource("/FXML/MainMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setTitle("Stronghold");
        stage.setScene(scene);
        stage.show();
    }
}