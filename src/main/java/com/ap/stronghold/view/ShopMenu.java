package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.ShopMenuController;
import com.ap.stronghold.model.Resource;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.ShopMenuMessage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class ShopMenu extends Application {

    public static void run() {
        String command;

        HashMap<String, ArrayList<String>> options;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("shop menu");
            else if (CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if (CommandHandler.parsCommand(Command.SHOW_PRICE_LIST, command) != null)
                return;
            else if ((options = CommandHandler.parsCommand(Command.SHOP_BUY, command)) != null)
               return;
            else if ((options = CommandHandler.parsCommand(Command.SHOP_SELL, command)) != null)
               return;
            else System.out.println("invalid command in shop menu");
        }
    }







    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/shopMenu.fxml"));

        Image logo = new Image(ShopMenu.class.getResource("/com/ap/stronghold/Media/shopLogo.png").toExternalForm());
        ImageView imageView = new ImageView(logo);
        imageView.setLayoutX(750);
        imageView.setLayoutY(50);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        Button tradeButton=new Button();
        tradeButton.setLayoutX(900);
        tradeButton.setLayoutY(620);
        tradeButton.setScaleX(1);
        tradeButton.setScaleY(1);
        tradeButton.setText("Trade Menu");
        tradeButton.setStyle("-fx-background-color: red;");
        tradeButton.setOnMouseEntered(e -> tradeButton.setStyle("-fx-background-color: blue;"));
        tradeButton.setOnMouseExited(e -> tradeButton.setStyle("-fx-background-color: red;"));
        tradeButton.setOnAction(event -> {
            try {
                new TradeMenu().start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        pane.getChildren().add(imageView);
        pane.getChildren().add(tradeButton);
        HBox hbox1 = createHBox1();
        HBox hbox2 = createHBox2();
        HBox hbox3 = createHBox3();
        HBox hbox4 = createHBox4();
        VBox vbox = new VBox(hbox1, hbox2, hbox3, hbox4);
        vbox.setLayoutX(450);
        vbox.setLayoutY(150);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        pane.getChildren().add(vbox);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
    }
    public void initialize(){

    }


    private HBox createHBox1() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        for (Resource item : Resource.values()) {
            if(item.getName().equals("wood") || item.getName().equals("wheat") || item.getName().equals("swords") || item.getName().equals("stone")
            || item.getName().equals("spear")){
                ImageView imageView = createImageView("/Media/"+item.getName()+".png");
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(createGroup(imageView, item.getName()));
                hbox.getChildren().add(stackPane);;
            }
        }



        return hbox;
    }
    private HBox createHBox2() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        for (Resource item : Resource.values()) {
            if (item.getName().equals("pitch") || item.getName().equals("pike") || item.getName().equals("metal") || item.getName().equals("meat")
                    || item.getName().equals("mace")) {
                ImageView imageView = createImageView("/Media/" + item.getName() + ".png");
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(createGroup(imageView, item.getName()));
                hbox.getChildren().add(stackPane);
                ;
            }
        }
        return hbox;
    }
    private HBox createHBox3() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        for (Resource item : Resource.values()) {
            if (item.getName().equals("leather") || item.getName().equals("iron") || item.getName().equals("hops") || item.getName().equals("flour")
                    || item.getName().equals("crossbow")) {
                ImageView imageView = createImageView("/Media/" + item.getName() + ".png");
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(createGroup(imageView, item.getName()));
                hbox.getChildren().add(stackPane);


            }
        }
        return hbox;
    }
    private HBox createHBox4() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        for (Resource item : Resource.values()) {
            if (item.getName().equals("cheese") || item.getName().equals("bread") || item.getName().equals("bow") || item.getName().equals("apple")
                    || item.getName().equals("ale")) {
                ImageView imageView = createImageView("/Media/" + item.getName() + ".png");
                StackPane stackPane = new StackPane();
                stackPane.getChildren().add(createGroup(imageView, item.getName()));
                hbox.getChildren().add(stackPane);
                ;
            }
        }
        return hbox;
    }


    private ImageView createImageView(String imagePath) {
        Image image = new Image(ShopMenu.class.getResource("/com/ap/stronghold"+imagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);



        return imageView;
    }
    private Group createGroup(ImageView imageView,String itemName) {
        Resource resource=Resource.getResourceByName(itemName);
        Rectangle border = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        border.setStroke(Color.BLACK);
        border.setFill(null);
        border.setStrokeWidth(2);
        Group group = new Group(imageView, border);
        group.setOnMouseEntered(e -> border.setStroke(Color.RED));
        group.setOnMouseExited(e -> border.setStroke(Color.BLACK));
        group.setOnMouseClicked(e -> {
            ImageView selectedImageView = new ImageView(imageView.getImage());
            selectedImageView.setFitWidth(imageView.getFitWidth()*2);
            selectedImageView.setFitHeight(imageView.getFitHeight()*2);
            selectedImageView.setLayoutY(0);
            Label buyPriceLabel = new Label("Buy Price: $" +resource.getBuyPrice() );
            buyPriceLabel.setStyle("-fx-font-size: 22px;\n" +
                    "    -fx-font-family: \"Arial Black\";\n" +
                    "    -fx-fill: #818181;\n" +
                    "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
            buyPriceLabel.setLayoutX(10);
            buyPriceLabel.setLayoutY(selectedImageView.getFitHeight() + 10);

            Label sellPriceLabel = new Label("Sell Price: $" + resource.getSellPrice());
            sellPriceLabel.setLayoutX(10);
            sellPriceLabel.setLayoutY(selectedImageView.getFitHeight() + 30);
            sellPriceLabel.setStyle("-fx-font-size: 22px;\n" +
                    "    -fx-font-family: \"Arial Black\";\n" +
                    "    -fx-fill: #818181;\n" +
                    "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");


            Label amountLabel = new Label("Amount: " +ShopMenuController.getAmount(itemName) );
            amountLabel.setLayoutX(10);
            amountLabel.setLayoutY(selectedImageView.getFitHeight() + 30);
            amountLabel.setStyle("-fx-font-size: 22px;\n" +
                    "    -fx-font-family: \"Arial Black\";\n" +
                    "    -fx-fill: #818181;\n" +
                    "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
            TextField textField = new TextField();
            textField.setLayoutX(10);
            textField.setLayoutY(10);
            textField.setScaleX(1.5);
            textField.setStyle("-fx-prompt-text-fill: grey; -fx-font-size: 14px; -fx-font-family: Arial;-fx-background-color: black; -fx-text-fill: white;");
            textField.setPromptText("amount of "+itemName);

            Button buyButton = new Button("Buy");


            buyButton.setOnAction(event -> {
                String input = textField.getText();
                if (!input.isEmpty()) {
                    Popup popup1 = new Popup();
                    Text message = new Text("");
                    message.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    message.setFill(Color.WHITE);
                    message.setTextAlignment(TextAlignment.CENTER);
                    String amount=input;
                    if (!Controller.isNumeric(amount)) {
                        message.setText("please enter a number!");
                    }
                    ShopMenuMessage result = ShopMenuController.buyItemChecker(itemName, Integer.parseInt(amount));
                    switch (result) {
                        case INVALID_ITEM -> message.setText("item is invalid");
                        case INVALID_AMOUNT -> message.setText("amount is invalid it should be at least 1");
                        case NOT_ENOUGH_CAPACITY -> message.setText("you don't have enough capacity to buy this item");
                        case NOT_ENOUGH_GOLD -> message.setText("you don't have enough gold to buy this item");
                        case SUCCESS -> {

                            ShopMenuController.buyItemConfirm(itemName,Integer.parseInt(amount));

                            message.setText("item bought");
                        }
                    }
                    Button closeButton = new Button("Close");
                    closeButton.setOnAction(event1 -> {
                        popup1.hide();
                        amountLabel.setText("Amount: " + ShopMenuController.getAmount(itemName));

                    });

                    VBox layout = new VBox(10, message, closeButton);
                    layout.setAlignment(Pos.CENTER);
                    layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px;");


                    popup1.getContent().add(layout);
                    popup1.setAutoHide(true);
                    popup1.show(buyButton.getScene().getWindow());
                } else {
                    Popup popup1 = new Popup();
                    Text message = new Text("Please enter an amount.");
                    message.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    message.setFill(Color.WHITE);
                    message.setTextAlignment(TextAlignment.CENTER);

                    Button closeButton = new Button("Close");
                    closeButton.setOnAction(event1 -> popup1.hide());

                    VBox layout = new VBox(10, message, closeButton);
                    layout.setAlignment(Pos.CENTER);
                    layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px;");


                    popup1.getContent().add(layout);
                    popup1.setAutoHide(true);
                    popup1.show(buyButton.getScene().getWindow());
                }
            });

            Button sellButton = new Button("Sell");
            sellButton.setOnAction(event -> {
                String input = textField.getText();
                if (!input.isEmpty()) {
                    Text message = new Text("");
                    message.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    message.setFill(Color.WHITE);
                    message.setTextAlignment(TextAlignment.CENTER);
                    Popup popup1 = new Popup();
                   String amount=input;
                    if (!Controller.isNumeric(amount)) {
                        message.setText("please enter a number!");
                    }
                    ShopMenuMessage result = ShopMenuController.sellItemChecker(itemName, Integer.parseInt(amount));
                    switch (result) {
                        case NOT_ENOUGH_ITEM -> message.setText("you don't have enough amount of this item to sell it");
                        case INVALID_ITEM -> message.setText("item is invalid");
                        case INVALID_AMOUNT -> message.setText("amount is invalid it should be at least 1");
                        case SUCCESS -> {
                            ShopMenuController.sellItemConfirm(itemName,Integer.parseInt(amount));
                            message.setText("item sold");
                        }
                    }



                    Button closeButton = new Button("Close");
                    closeButton.setOnAction(event1 -> {
                        amountLabel.setText("Amount: " + ShopMenuController.getAmount(itemName));
                        popup1.hide();
                    });

                    VBox layout = new VBox(10, message, closeButton);
                    layout.setAlignment(Pos.CENTER);
                    layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px;");


                    popup1.getContent().add(layout);
                    popup1.setAutoHide(true);
                    popup1.show(buyButton.getScene().getWindow());
                } else {
                    Popup popup1 = new Popup();
                    // Handle empty input
                    Text message = new Text("Please enter an amount.");
                    message.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    message.setFill(Color.WHITE);
                    message.setTextAlignment(TextAlignment.CENTER);

                    Button closeButton = new Button("Close");
                    closeButton.setOnAction(event1 -> popup1.hide());

                    VBox layout = new VBox(10, message, closeButton);
                    layout.setAlignment(Pos.CENTER);
                    layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px;");


                    popup1.getContent().add(layout);
                    popup1.setAutoHide(true);
                    popup1.show(buyButton.getScene().getWindow());
                }
            });
            Button backButton=new Button("Back");
            backButton.setOnAction(event -> {
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            });



            HBox buttonBox = new HBox(buyButton, sellButton,backButton);
            buttonBox.setSpacing(20);
            buttonBox.setAlignment(Pos.CENTER);

            VBox pageBox = new VBox(selectedImageView, buyPriceLabel, sellPriceLabel,amountLabel, buttonBox);
            pageBox.setLayoutY(150);
            pageBox.setLayoutX(350);
            pageBox.setSpacing(20);
            pageBox.setPadding(new Insets(10));

            pageBox.getChildren().add(1, textField);



            Pane pane = new Pane( pageBox);
            Label itemNameLabel = new Label(itemName);
            itemNameLabel.setStyle("-fx-font-size: 30px;\n" +
                    "    -fx-font-family: \"Arial Black\";\n" +
                    "    -fx-fill: #818181;\n" +
                    "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
            itemNameLabel.setLayoutY(50);
            itemNameLabel.setLayoutX(450);
            pane.getChildren().add(itemNameLabel);
            Scene pageScene = new Scene(pane, 1100,700 );
            URL url = getClass().getResource("/com/ap/stronghold/CSS/style1.css");
            pageScene.getStylesheets().add(url.toExternalForm());
            Stage pageStage = new Stage();
            pageStage.setScene(pageScene);
            pageStage.show();
        });
    return group;
    }


}


