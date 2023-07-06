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

    public void enterChatList(ActionEvent actionEvent) throws Exception {
        new ChatList();
    }
}

