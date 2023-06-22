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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.print.attribute.standard.Media;
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
                showPriceList();
            else if ((options = CommandHandler.parsCommand(Command.SHOP_BUY, command)) != null)
                buyItem(options);
            else if ((options = CommandHandler.parsCommand(Command.SHOP_SELL, command)) != null)
                sellItem(options);
            else System.out.println("invalid command in shop menu");
        }
    }


    public static void showPriceList() {
        String result = ShopMenuController.showItemList();
        System.out.println(result);
    }

    public static void buyItem(HashMap<String, ArrayList<String>> options) {
        String name = null;
        String amount = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "i" -> name = Controller.buildParameter(options.get(s).get(0));
                case "a" -> amount = Controller.buildParameter(options.get(s).get(0));
            }
        }
        if (name == null) {
            System.out.println("itemName not entered");
            return;
        }
        if (amount == null) {
            System.out.println("amount not entered");
            return;
        }
        if (!Controller.isNumeric(amount)) {
            System.out.println("amount format is invalid");
            return;
        }
        ShopMenuMessage result = ShopMenuController.buyItemChecker(name, Integer.parseInt(amount));

        switch (result) {
            case INVALID_ITEM -> System.out.println("item is invalid");
            case INVALID_AMOUNT -> System.out.println("amount is invalid it should be at least 1");
            case NOT_ENOUGH_CAPACITY -> System.out.println("you don't have enough capacity to buy this item");
            case NOT_ENOUGH_GOLD -> System.out.println("you don't have enough gold to buy this item");
            case SUCCESS -> {
                String confirmMessage = "are you sure you want to buy " + amount + " of " + name + "? if you are sure enter Y";
                System.out.println(confirmMessage);
                String confirmAnswer = MainMenu.getScanner().nextLine();
                ShopMenuMessage buyResult = ShopMenuController.buyItemConfirm(confirmAnswer, name, Integer.parseInt(amount));
                if (buyResult.equals(ShopMenuMessage.SUCCESS)) System.out.println("item bought");
                else System.out.println("canceled");
            }
        }
    }

    public static void sellItem(HashMap<String, ArrayList<String>> options) {
        String name = null;
        String amount = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "i" -> name = Controller.buildParameter(options.get(s).get(0));
                case "a" -> amount = Controller.buildParameter(options.get(s).get(0));
            }
        }
        if (name == null) {
            System.out.println("itemName not entered");
            return;
        }
        if (amount == null) {
            System.out.println("amount not entered");
            return;
        }
        if (!Controller.isNumeric(amount)) {
            System.out.println("amount format is invalid");
            return;
        }
        ShopMenuMessage result = ShopMenuController.sellItemChecker(name, Integer.parseInt(amount));
        switch (result) {
            case NOT_ENOUGH_ITEM -> System.out.println("you don't have enough amount of this item to sell it");
            case INVALID_ITEM -> System.out.println("item is invalid");
            case INVALID_AMOUNT -> System.out.println("amount is invalid it should be at least 1");
            case SUCCESS -> {
                String confirmMessage = "are you sure you want to sell " + amount + " of " + name + "? if you are sure enter Y";
                System.out.println(confirmMessage);
                String confirmAnswer = MainMenu.getScanner().nextLine();
                ShopMenuMessage sellResult = ShopMenuController.sellItemConfirm(confirmAnswer, name, Integer.parseInt(amount));
                if (sellResult.equals(ShopMenuMessage.SUCCESS)) System.out.println("item sold");
                else System.out.println("canceled");
            }
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
        pane.getChildren().add(imageView);
        HBox hbox1 = createHBox1();
        HBox hbox2 = createHBox2();
        HBox hbox3 = createHBox3();
        HBox hbox4 = createHBox4();
        VBox vbox = new VBox(hbox1, hbox2, hbox3, hbox4);
        vbox.setLayoutX(550);
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
                stackPane.getChildren().add(createGroup(imageView));
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
                stackPane.getChildren().add(createGroup(imageView));
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
                stackPane.getChildren().add(createGroup(imageView));
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
                stackPane.getChildren().add(createGroup(imageView));
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
    private Group createGroup(ImageView imageView) {

        Rectangle border = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        border.setStroke(Color.BLACK);
        border.setFill(null);
        border.setStrokeWidth(2);
        Group group = new Group(imageView, border);
        group.setOnMouseEntered(e -> border.setStroke(Color.RED));
        group.setOnMouseExited(e -> border.setStroke(Color.BLACK));
        group.setOnMouseClicked(e -> {
            ImageView selectedImageView = new ImageView(imageView.getImage());
            selectedImageView.setFitWidth(imageView.getFitWidth());
            selectedImageView.setFitHeight(imageView.getFitHeight());


            Button buyButton = new Button("Buy");
            buyButton.setOnAction(event -> {

                System.out.println("Buy button clicked");
            });

            Button sellButton = new Button("Sell");
            sellButton.setOnAction(event -> {
                Stage stage = (Stage) sellButton.getScene().getWindow();
                stage.close();
            });

            HBox buttonBox = new HBox(buyButton, sellButton);
            buttonBox.setSpacing(10);
            buttonBox.setAlignment(Pos.CENTER);

            VBox pageBox = new VBox(selectedImageView, buttonBox);

            pageBox.setSpacing(10);
            pageBox.setPadding(new Insets(10));



            Pane pane = new Pane( pageBox);
            Scene pageScene = new Scene(pane, 400, 300);
            URL url = getClass().getResource("/com/ap/stronghold/CSS/style1.css");
            pageScene.getStylesheets().add(url.toExternalForm());
            Stage pageStage = new Stage();
            pageStage.setScene(pageScene);
            pageStage.show();
        });
    return group;
    }


}


