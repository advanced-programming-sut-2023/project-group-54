package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class MainMenu extends Application {
    private static final Scanner scanner = new Scanner(System.in);
    public static Stage stage;

    public static boolean captchaChecker() {
        TextInputDialog dialog = new TextInputDialog("");
        int number = Controller.captcha();
        dialog.setTitle("Enter Captcha");
        dialog.setHeaderText("Enter The Number Inside Picture");
        dialog.setGraphic(new ImageView(new Image(MainMenu.class.getResource("/com/ap/stronghold/Media/Captcha/" + number + ".png").toExternalForm())));

        Optional<String> result = dialog.showAndWait();

        while (result.isPresent()){
            if(result.get().equals(String.valueOf(number))){
                return true;
            }else{
                number = Controller.captcha();
                dialog.setGraphic(new ImageView(new Image(MainMenu.class.getResource("/com/ap/stronghold/Media/Captcha/" + number + ".png").toExternalForm())));
                result = dialog.showAndWait();
            }
        }
        return false;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public void run() throws Exception {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        if (Controller.checkIfStayLoggedIn()){
            (new Menu()).start(stage);
            return;
        }
        URL url = MainMenu.class.getResource("/com/ap/stronghold/FXML/mainMenu.fxml");
        Pane pane = FXMLLoader.load(url);
        Scene scene = new Scene(pane);
        stage.setTitle("Stronghold");
        stage.setScene(scene);
        stage.show();
    }

    public void enterLoginMenu() throws IOException {
        (new LoginMenu()).start(MainMenu.stage);
    }

    public void enterSignUpMenu() throws IOException {
        (new SignupMenu()).start(MainMenu.stage);
    }
}