package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.TradeMenuController;
import com.ap.stronghold.model.*;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.TradeMenuMessage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TradeMenu extends Application {
    public static void run() {
        String command;
        Matcher matcher;
        HashMap<String, ArrayList<String>> options;
        showAllPlayer();
        showNotifications();
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("trade menu");
            else if (CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if ((options = CommandHandler.parsCommand(Command.TRADE, command)) != null)
                return;
            else if (CommandHandler.parsCommand(Command.TRADE_LIST, command) != null)
                tradeListShow();
            else if ((options = CommandHandler.parsCommand(Command.TRADE_ACCEPT, command)) != null)
                acceptTrade(options);
            else if (CommandHandler.parsCommand(Command.TRADE_HISTORY, command) != null)
                historyShow();
            else System.out.println("invalid command in trade menu");
        }
    }

    private static void showAllPlayer() {
        ArrayList<User> allUsers = User.getUsers();
        String result = "";
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            result += i + 1 + ". " + user.getUsername() + "\n";
        }
        System.out.println(result.trim());
    }

    private static void showNotifications() {
        Government government = Game.getCurrentUser().getGovernment();
        ArrayList<Trade> newTrades = government.getNewTrades();
        String notifications = "";
        for (Trade trade : newTrades) {
            notifications += "resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            notifications += " sender: " + trade.getSenderUser().getUsername() + "\n";
        }
        if (notifications.equals("")) {
            System.out.println("you don't have any new notification");
            return;
        }
        System.out.println(notifications.trim());
        government.getNewTrades().clear();
    }

    public static ListView tradeListShow() {
        Government government = Game.getCurrentUser().getGovernment();
        ListView<String> listView = new ListView<>();
        String result = "";

        ObservableList<String> items = FXCollections.observableArrayList();


        for (int i = 0; i < government.getAllTrades().size(); i++) {
            Trade trade = government.getAllTrades().get(i);
             result = (i + 1) + " resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            result += " sender: " + trade.getSenderUser().getUsername();
            items.add(result);
        }


        listView.setItems(items);
        return listView;
    }

    public static void historyShow() {
        Government government = Game.getCurrentUser().getGovernment();
        String result = "";
        for (int i = 0; i < government.getSentTrades().size(); i++) {
            Trade trade = government.getSentTrades().get(i);
            result += i + 1 + " resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            result += " sender: " + trade.getSenderUser().getUsername() + "\n";
        }
        for (int i = 0; i < government.getReceivedTrades().size(); i++) {
            Trade trade = government.getReceivedTrades().get(i);
            result += i + 1 + " resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            result += " acceptMessage: " + trade.getReceiverMessage() + " sender: " + trade.getSenderUser().getUsername() + "\n";
        }
        if (result.equals("")) {
            System.out.println("your history is empty");
            return;
        }
        System.out.println(result.trim());


    }


    public static void acceptTrade(HashMap<String, ArrayList<String>> options) {
        String id = null;
        String message = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "i" -> id = Controller.buildParameter(options.get(s).get(0));
                case "m" -> message = Controller.buildParameter(options.get(s).get(0));
            }
        }
        if (id == null) {
            System.out.println("id not entered");
            return;
        }
        if (!Controller.isNumeric(id)) {
            System.out.println("id should be number please choose one of the indexes");
            return;
        }
        if (message == null) {
            System.out.println("message not entered");
            return;
        }

        TradeMenuMessage result = TradeMenuController.acceptTrade(id, message);
        switch (result) {
            case INVALID_INDEX -> System.out.println("index is invalid");
            case NOT_ENOUGH_GOLD -> System.out.println("you don't have enough gold to accept this trade");
            case NOT_ENOUGH_CAPACITY -> System.out.println("you don't have enough capacity to accept this trade");
            case SUCCESS -> System.out.println("your acceptation has been successfully done");
        }


    }
    private ComboBox<String> userComboBox;
    private Label selectedUserLabel;
    public void start(Stage stage) throws IOException {
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/tradeMenu.fxml"));
        Image logo = new Image(ShopMenu.class.getResource("/com/ap/stronghold/Media/tradeLogo.jpg").toExternalForm());
        ImageView imageView = new ImageView(logo);
        imageView.setLayoutX(200);
        imageView.setLayoutY(100);
        imageView.setFitWidth(200);
        imageView.setFitHeight(100);
        Button newTradeButton=new Button("New Trade");
        Button previousTradeButton=new Button("previous trades");

        newTradeButton.setStyle("-fx-background-color: red;");
        newTradeButton.setOnMouseEntered(e -> newTradeButton.setStyle("-fx-background-color: green;"));
        newTradeButton.setOnMouseExited(e -> newTradeButton.setStyle("-fx-background-color: red;"));
        previousTradeButton.setLayoutX(300);
        previousTradeButton.setLayoutY(350);
        previousTradeButton.setScaleX(1);
        previousTradeButton.setScaleY(1);
        previousTradeButton.setStyle("-fx-background-color: red;");
        previousTradeButton.setOnMouseEntered(e -> previousTradeButton.setStyle("-fx-background-color: green;"));
        previousTradeButton.setOnMouseExited(e -> previousTradeButton.setStyle("-fx-background-color: red;"));
        userComboBox = new ComboBox<>();
        selectedUserLabel = new Label();
        userComboBox.getItems().addAll(User.usersToString());
        pane.getChildren().add(imageView);
        userComboBox.setDisable(true);
        pane.getChildren().add(previousTradeButton);
        newTradeButton.setOnAction(event -> showUserList(stage));
        newTradeButton.setLayoutX(100);
        newTradeButton.setLayoutY(350);
        newTradeButton.setScaleX(1);
        newTradeButton.setScaleY(1);
        userComboBox.setLayoutX(100);
        userComboBox.setLayoutY(400);
        previousTradeButton.setOnAction(actionEvent -> {
            try {
                (new PreviousTradeMenu()).start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        selectedUserLabel.setLayoutX(240);
        selectedUserLabel.setLayoutY(10);
        pane.getChildren().addAll(newTradeButton,userComboBox,selectedUserLabel);
        Scene scene=new Scene(pane);
        stage.setScene(scene);

    }
    private void showUserList(Stage stage) {
        userComboBox.setDisable(false);
        // Show user list
        userComboBox.show();
        userComboBox.setOnAction(event -> {
            try {
                selectUser(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        userComboBox.setDisable(true);
    }

    private void selectUser(Stage stage) throws Exception {
        // Get selected user
        String selectedUser = userComboBox.getSelectionModel().getSelectedItem();
        User user=User.findUserByUsername(selectedUser);
        (new NewTradeMenu(user)).start(stage);

    }
}

