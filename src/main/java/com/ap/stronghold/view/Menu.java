package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.LoginMenuController;
import com.ap.stronghold.controller.MapMenuController;
import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.User;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.LoginMenuMessage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Menu extends Application {

//    public static void run() {
//        String command;
//        HashMap<String, ArrayList<String>> options;
//        System.out.println("you are in menu");
//        while (true) {
//            command = MainMenu.getScanner().nextLine();
//           if ((options = CommandHandler.parsCommand(Command.START_GAME, command)) != null) {
//                startGame(options);
//                System.out.println("you are in menu");
//            }
//            else System.out.println("invalid command in menu");
//        }
//    }

    private static void startGame(HashMap<String, ArrayList<String>> options) {
        int count;
        try {
            count = Integer.parseInt(options.get("count").get(0));
        } catch (NumberFormatException e) {
            System.out.println("format is wrong for count you have to enter a number");
            return;
        }
        if (count < 2 || count > 8) {
            System.out.println("count must greater than 1 and lower than 9");
            return;
        }
        ArrayList<User> users = new ArrayList<>();
        User a = null;
        User b = null;
        User c = null;
        User d = null;
        User e = null;
        User f = null;
        User g = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "a":
                    a = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username a is invalid");
                        return;
                    }
                    break;
                case "b":
                    b = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username b is invalid");
                        return;
                    }
                    break;
                case "c":
                    c = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username c is invalid");
                        return;
                    }
                    break;
                case "d":
                    d = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username d is invalid");
                        return;
                    }
                    break;
                case "e":
                    e = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username e is invalid");
                        return;
                    }
                    break;
                case "f":
                    f = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username f is invalid");
                        return;
                    }
                    break;
                case "g":
                    g = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username g is invalid");
                        return;
                    }
                    break;
            }
        }
        users.add(Controller.getLoggedInUser());
        users.add(a);
        if (b != null)
            users.add(b);
        if (c != null)
            users.add(c);
        if (d != null)
            users.add(d);
        if (e != null)
            users.add(e);
        if (f != null)
            users.add(f);
        if (g != null)
            users.add(g);

        if(setMap()){
            Game.setCurrentUser(Controller.getLoggedInUser());
            Game.setUsers(users);
            Game.setUserRemoved();
            MapMenuController.setDefaultBuilding(count);
//            if(Game.getX() == 400)
//                MapMenuController.setMainHouse(75);
//            else if(Game.getX() == 200)
//                MapMenuController.setMainHouse(30);
            System.out.println("game started successfully");
            GameMenu.run();
        }else{
            System.out.println("game not started due to failure in choosing map");
        }
    }

    private static boolean setMap() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("choose map");
        dialog.setHeaderText("your map");

        ChoiceBox<String> order = new ChoiceBox<String>();
        order.getItems().add("400 by 400 map");
        order.getItems().add("200 by 200 map");
        order.getItems().add("default 400 by 400 map");
        order.setValue("default 400 by 400 map");
        HBox content = new HBox();
        content.setSpacing(10);
        content.getChildren().addAll(new Label("select your map"), order);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return order.getValue();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()){
            int mapNumber = 0;
            switch (order.getValue()){
                case "400 by 400 map" -> mapNumber = 1;
                case "200 by 200 map" -> mapNumber = 2;
                case "default 400 by 400 map" -> mapNumber = 3;
            }
            MapMenuController.setMap(mapNumber);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(Menu.class.getResource("/com/ap/stronghold/FXML/menu.fxml"));

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    public void logout() throws IOException {
        LoginMenuController.logout();
        (new LoginMenu()).start(MainMenu.stage);
    }

    public void enterProfileMenu() throws Exception {
        (new ProfileMenu()).start(MainMenu.stage);
    }
    public void enterShopMenu() throws Exception{
        (new ShopMenu()).start(MainMenu.stage);
    }

    public void exit() {
        User.saveUser();
        System.exit(0);
        MainMenu.stage.close();
    }

    public void startGame() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("game count");
        dialog.setHeaderText("game user count");
        dialog.setContentText("How many players are there?");
        Optional<String> result = dialog.showAndWait();

        while (result.isPresent()){
            try {
                int count = Integer.parseInt(result.get());
                if(count < 2 || count > 8){
                    dialog.setHeaderText("count must be between 2 and 8");
                    result = dialog.showAndWait();
                }else{
                    getUsers(count);
                    return;
                }
            } catch (NumberFormatException e){
                dialog.setHeaderText("count must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void getUsers(int count) throws Exception {
        ArrayList<User>users = new ArrayList<>();
        users.add(Controller.getLoggedInUser());
        boolean presented = true;
        for (int i = 1; i < count; i++){
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("user " + i+1);
            dialog.setHeaderText("game users");
            dialog.setContentText("what is the username of user " + (i+1) + "?");
            Optional<String> result = dialog.showAndWait();

            while (result.isPresent()){
                String username = result.get();
                if(User.findUserByUsername(username) != null){
                    users.add(User.findUserByUsername(username));
                    break;
                }else{
                    dialog.setHeaderText("user not found");
                    result = dialog.showAndWait();
                }
            }

            if(!result.isPresent()){
                presented = false;
                break;
            }
        }
        if(presented && setMap()){
            Game.setCurrentUser(Controller.getLoggedInUser());
            Game.setUsers(users);
            Game.setUserRemoved();
            MapMenuController.setDefaultBuilding(count);
            (new GameMenu()).start(MainMenu.stage);
        }
    }
}

