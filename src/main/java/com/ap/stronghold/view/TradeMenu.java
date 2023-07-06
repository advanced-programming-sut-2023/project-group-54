package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.ShopMenuController;
import com.ap.stronghold.controller.TradeMenuController;
import com.ap.stronghold.model.*;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.TradeMenuMessage;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

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
                return;
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


        ObservableList<String> items = FXCollections.observableArrayList();


        for (int i = 0; i < government.getSentTrades().size(); i++) {
            String result = "";
            Trade trade = government.getSentTrades().get(i);
            result = (i + 1) + " resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            result += " receiver: " + trade.getReceiverUser().getUsername()+" status: "+trade.getAcceptedStatus();
            items.add(result);
        }


        listView.setItems(items);
        return listView;
    }
    public  static ListView receivedTradeListShow( Stage stage){

        Government government = Game.getCurrentUser().getGovernment();


        ObservableList<String> items = FXCollections.observableArrayList();

        for (int i = 0; i < government.getReceivedTrades().size(); i++) {
            String result = "";
            Trade trade = government.getReceivedTrades().get(i);
            result = (i + 1) + " resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            result += " sender: " + trade.getSenderUser().getUsername() + " status: " + trade.getAcceptedStatus();
            items.add(result);
        }

        ListView<String> tradeListView = new ListView<>(items);
        tradeListView.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    setBackground(null);
                } else {
                    setText(item);
                    Trade trade = government.getReceivedTrades().get(getIndex());


                     if (trade.getAcceptedStatus().equals("pending") && !trade.getIsNew()) {
                        setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
                         setOnMouseClicked(event -> {
                             Popup popup1 = new Popup();
                             Text message = new Text("");
                             message.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                             message.setFill(Color.WHITE);
                             message.setTextAlignment(TextAlignment.CENTER);
                            TradeMenuMessage result= TradeMenu.acceptTrade(getIndex());
                             switch (result) {
                                 case INVALID_INDEX -> message.setText("index is invalid");
                                 case NOT_ENOUGH_GOLD -> message.setText("you don't have enough gold to accept this trade");
                                 case NOT_ENOUGH_CAPACITY -> message.setText("you don't have enough capacity to accept this trade");
                                 case SUCCESS -> message.setText("your acceptation has been successfully done");
                             }
                             Button closeButton = new Button("Close");
                             closeButton.setOnAction(event1 -> {
                                 popup1.hide();
                                 trade.setAcceptedStatus(true);
                                 try {
                                     ( new PreviousTradeMenu()).start(stage);
                                 } catch (Exception e) {
                                     throw new RuntimeException(e);
                                 }
                                 tradeListView.setVisible(false);
                             });

                             VBox layout = new VBox(10, message, closeButton);
                             layout.setAlignment(Pos.CENTER);
                             layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px;");


                             popup1.getContent().add(layout);
                             popup1.setAutoHide(true);
                             popup1.show(tradeListView.getScene().getWindow());

                         });
                    }else if(trade.getIsNew() && !trade.getAcceptedStatus().equals("accepted")){
                         setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
                         setOnMouseClicked(event -> {
                             Popup popup1 = new Popup();
                             Text message = new Text("");
                             message.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                             message.setFill(Color.WHITE);
                             message.setTextAlignment(TextAlignment.CENTER);
                             TradeMenuMessage result= TradeMenu.acceptTrade(getIndex());
                             switch (result) {
                                 case INVALID_INDEX -> message.setText("index is invalid");
                                 case NOT_ENOUGH_GOLD -> message.setText("you don't have enough gold to accept this trade");
                                 case NOT_ENOUGH_CAPACITY -> message.setText("you don't have enough capacity to accept this trade");
                                 case SUCCESS -> message.setText("your acceptation has been successfully done");
                             }
                             Button closeButton = new Button("Close");
                             closeButton.setOnAction(event1 -> {
                                 popup1.hide();
                                 trade.setAcceptedStatus(true);
                                 try {
                                     ( new PreviousTradeMenu()).start(stage);
                                 } catch (Exception e) {
                                     throw new RuntimeException(e);
                                 }
                                tradeListView.setVisible(false);


                             });

                             VBox layout = new VBox(10, message, closeButton);
                             layout.setAlignment(Pos.CENTER);
                             layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px;");


                             popup1.getContent().add(layout);
                             popup1.setAutoHide(true);
                             popup1.show(tradeListView.getScene().getWindow());

                         });
                     }
                     else {
                        setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
                    }




                }
            }
        });


        return tradeListView;
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


    public static TradeMenuMessage acceptTrade(int id) {


        TradeMenuMessage result = TradeMenuController.acceptTrade(id+1,null);
        return result;



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
        Button backButton=new Button("Back");
        backButton.setLayoutX(0);
        backButton.setLayoutY(650);
        backButton.setOnAction(actionEvent -> {
            try {
                (new ShopMenu()).start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        userComboBox = new ComboBox<>();
        selectedUserLabel = new Label();
        userComboBox.getItems().addAll(User.usersToString());
        pane.getChildren().add(imageView);
        pane.getChildren().add(backButton);
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

