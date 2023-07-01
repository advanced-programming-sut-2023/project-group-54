package com.ap.stronghold.view;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.controller.ShopMenuController;
import com.ap.stronghold.controller.TradeMenuController;
import com.ap.stronghold.model.Resource;
import com.ap.stronghold.model.User;
import com.ap.stronghold.view.enums.messages.ShopMenuMessage;
import com.ap.stronghold.view.enums.messages.TradeMenuMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;


public class NewTradeMenu extends Application {
    private User receiverUser;
    public NewTradeMenu() {
        // Default constructor
    }
    public NewTradeMenu(User user){
        this.receiverUser=user;
    }

    @Override
    public void start(Stage stage) throws Exception {

        Pane pane= FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/newTradeMenu.fxml"));
        Button backButton=new Button("Back");
        backButton.setLayoutX(0);
        backButton.setLayoutY(650);
        backButton.setOnAction(actionEvent -> {
            try {
                (new TradeMenu()).start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
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
        pane.getChildren().add(backButton);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
    }
    private HBox createHBox1() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        for (Resource item : Resource.values()) {
            if (!(ShopMenuController.getAmount(item.getName()) == 0)) {
                if (item.getName().equals("wood") || item.getName().equals("wheat") || item.getName().equals("swords") || item.getName().equals("stone")
                        || item.getName().equals("spear")) {
                    ImageView imageView = createImageView("/Media/" + item.getName() + ".png");
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().add(createGroup(imageView, item.getName()));
                    hbox.getChildren().add(stackPane);
                    ;
                }
            }
        }



        return hbox;
    }
    private HBox createHBox2() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        for (Resource item : Resource.values()) {
            if (!(ShopMenuController.getAmount(item.getName()) == 0)) {
                if (item.getName().equals("pitch") || item.getName().equals("pike") || item.getName().equals("metal") || item.getName().equals("meat")
                        || item.getName().equals("mace")) {
                    ImageView imageView = createImageView("/Media/" + item.getName() + ".png");
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().add(createGroup(imageView, item.getName()));
                    hbox.getChildren().add(stackPane);
                    ;
                }
            }
        }

        return hbox;
    }
    private HBox createHBox3() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        for (Resource item : Resource.values()) {
            if (!(ShopMenuController.getAmount(item.getName()) == 0)) {
                if (item.getName().equals("leather") || item.getName().equals("iron") || item.getName().equals("hops") || item.getName().equals("flour")
                        || item.getName().equals("crossbow")) {
                    ImageView imageView = createImageView("/Media/" + item.getName() + ".png");
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().add(createGroup(imageView, item.getName()));
                    hbox.getChildren().add(stackPane);


                }
            }
        }
        return hbox;
    }
    private HBox createHBox4() {
        HBox hbox = new HBox();
        hbox.setSpacing(10);
        for (Resource item : Resource.values()) {
            if (!(ShopMenuController.getAmount(item.getName()) == 0)) {
                if (item.getName().equals("cheese") || item.getName().equals("bread") || item.getName().equals("bow") || item.getName().equals("apple")
                        || item.getName().equals("ale")) {
                    ImageView imageView = createImageView("/Media/" + item.getName() + ".png");
                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().add(createGroup(imageView, item.getName()));
                    hbox.getChildren().add(stackPane);
                    ;
                }
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
    private Group createGroup(ImageView imageView, String itemName) {
        AtomicInteger quantity= new AtomicInteger();
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



            Label amountLabel = new Label("Amount: " + ShopMenuController.getAmount(itemName) );
            amountLabel.setLayoutX(10);
            amountLabel.setLayoutY(selectedImageView.getFitHeight() + 30);
            amountLabel.setStyle("-fx-font-size: 22px;\n" +
                    "    -fx-font-family: \"Arial Black\";\n" +
                    "    -fx-fill: #818181;\n" +
                    "    -fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );");
            Label amountField = new Label("amount of "+itemName);
            amountField.setLayoutX(10);
            amountField.setLayoutY(10);
            amountField.setScaleX(1.5);
            amountField.setStyle("-fx-prompt-text-fill: grey; -fx-font-size: 14px; -fx-font-family: Arial;-fx-background-color: black; -fx-text-fill: white;");
            TextField textField1=new TextField();
            textField1.setLayoutX(10);
            textField1.setLayoutY(20);
            textField1.setScaleX(1.5);
            textField1.setStyle("-fx-prompt-text-fill: grey; -fx-font-size: 14px; -fx-font-family: Arial;-fx-background-color: black; -fx-text-fill: white;");
            textField1.setPromptText("message");
            TextField priceTextField=new TextField();
            priceTextField.setLayoutX(10);
            priceTextField.setLayoutY(30);
            priceTextField.setScaleX(1.5);
            priceTextField.setStyle("-fx-prompt-text-fill: grey; -fx-font-size: 14px; -fx-font-family: Arial;-fx-background-color: black; -fx-text-fill: white;");
            priceTextField.setPromptText("price");

            Button donateButton = new Button("Donate");
            Button minusButton=new Button("-");
            Button plusButton=new Button("+");
            plusButton.setOnAction(actionEvent -> {
                quantity.getAndIncrement();
                amountField.setText(quantity.toString());
            });
            minusButton.setOnAction(actionEvent -> {
                if(quantity.get() > 0){
                    quantity.getAndDecrement();
                    amountField.setText(quantity.toString());
                }
            });

            donateButton.setOnAction(event -> {

                    String message=textField1.getText();
                    Popup popup1 = new Popup();
                    Text popMessage = new Text("");
                    popMessage.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    popMessage.setFill(Color.WHITE);
                    popMessage.setTextAlignment(TextAlignment.CENTER);
                        TradeMenuMessage result = TradeMenuController.trade(itemName, quantity.toString(), Integer.toString(0), message,receiverUser);

                switch (result) {
                    case INVALID_ITEM -> popMessage.setText("item is invalid we don't have this item in our game");
                    case INVALID_AMOUNT -> popMessage.setText("amount is invalid it should be at least 1");
                    case NOT_ENOUGH_ITEM -> popMessage.setText("you don't have enough item for this trade request");
                    case INVALID_PRICE -> popMessage.setText("price is invalid price should be grater than 0");
                    case SUCCESS -> popMessage.setText("your trade request has been sent successfully");
                }
                    Button closeButton = new Button("Close");
                    closeButton.setOnAction(event1 -> {
                        popup1.hide();
                        amountLabel.setText("Amount: " + ShopMenuController.getAmount(itemName));
                    });

                    VBox layout = new VBox(10, popMessage, closeButton);
                    layout.setAlignment(Pos.CENTER);
                    layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px;");


                    popup1.getContent().add(layout);
                    popup1.setAutoHide(true);
                    popup1.show(donateButton.getScene().getWindow());

            });

            Button requestButton = new Button("Request");
            requestButton.setOnAction(event -> {
                String price = priceTextField.getText();
                String inputMessage=textField1.getText();
                if (!price.isEmpty()) {
                    Text popMessage = new Text("");
                    popMessage.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                    popMessage.setFill(Color.WHITE);
                    popMessage.setTextAlignment(TextAlignment.CENTER);
                    Popup popup1 = new Popup();
                    String message=inputMessage;
                    TradeMenuMessage result = TradeMenuController.trade(itemName, quantity.toString(),price , message,receiverUser);

                    switch (result) {
                        case INVALID_ITEM -> popMessage.setText("item is invalid we don't have this item in our game");
                        case INVALID_AMOUNT -> popMessage.setText("amount is invalid it should be at least 1");
                        case NOT_ENOUGH_ITEM -> popMessage.setText("you don't have enough item for this trade request");
                        case INVALID_PRICE -> popMessage.setText("price is invalid price should be grater than 0");
                        case SUCCESS -> popMessage.setText("your trade request has been sent successfully");
                    }





                    Button closeButton = new Button("Close");
                    closeButton.setOnAction(event1 -> {
                        amountLabel.setText("Amount: " + ShopMenuController.getAmount(itemName));
                        popup1.hide();
                    });

                    VBox layout = new VBox(10, popMessage, closeButton);
                    layout.setAlignment(Pos.CENTER);
                    layout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.8); -fx-padding: 20px;");


                    popup1.getContent().add(layout);
                    popup1.setAutoHide(true);
                    popup1.show(donateButton.getScene().getWindow());
                } else {
                    Popup popup1 = new Popup();
                    // Handle empty input
                    Text message = new Text("Please enter a price.");
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
                    popup1.show(donateButton.getScene().getWindow());
                }
            });
            Button backButton=new Button("Back");
            backButton.setOnAction(event -> {
                Stage stage = (Stage) backButton.getScene().getWindow();
                stage.close();
            });



            HBox buttonBox = new HBox(donateButton, requestButton,backButton);
            buttonBox.setSpacing(20);
            buttonBox.setAlignment(Pos.CENTER);

            VBox pageBox = new VBox(selectedImageView,amountLabel, buttonBox);
            pageBox.setLayoutY(150);
            pageBox.setLayoutX(350);
            pageBox.setSpacing(20);
            pageBox.setPadding(new Insets(10));

            pageBox.getChildren().add(1, amountField);
            pageBox.getChildren().add(2,textField1);
            pageBox.getChildren().add(3,plusButton);
            pageBox.getChildren().add(4,minusButton);
            pageBox.getChildren().add(5,priceTextField);



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
