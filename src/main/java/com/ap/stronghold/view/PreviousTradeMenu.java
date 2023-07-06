package com.ap.stronghold.view;

import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.Government;
import com.ap.stronghold.model.Trade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class PreviousTradeMenu extends Application {



    @Override
    public void start(Stage stage) throws Exception {
        Pane pane= FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/previousTradeMenu.fxml"));
        Button backButton=new Button("Back");
        backButton.setLayoutX(0);
        backButton.setLayoutY(650);
        backButton.setOnAction(actionEvent -> {
            try {
                Government government= Game.getCurrentUser().getGovernment();
                ArrayList<Trade> lastTrades=government.getReceivedTrades();
                for(int i=0;i<lastTrades.size();i++){
                    if(lastTrades.get(i).getIsNew()){
                        lastTrades.get(i).setIsNew();
                    }
                }
                (new TradeMenu()).start(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ListView<String> sentTradesList=TradeMenu.tradeListShow();
        ListView<String> receivedTradesList=TradeMenu.receivedTradeListShow(stage);
        sentTradesList.setVisible(false);
        sentTradesList.setLayoutY(200);
        sentTradesList.setLayoutX(400);
        sentTradesList.setPrefSize(500, 300);
        sentTradesList.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14px; -fx-text-fill: #000000;");
        receivedTradesList.setVisible(false);
        receivedTradesList.setLayoutY(200);
        receivedTradesList.setLayoutX(400);
        receivedTradesList.setPrefSize(500, 300);
        receivedTradesList.setStyle("-fx-background-color: #FFFFFF; -fx-font-size: 14px; -fx-text-fill: #000000;");
        Label sentTradesLabel = new Label("Sent Trades");
        sentTradesLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-background-color: #336699; -fx-padding: 10px;");
        sentTradesLabel.setLayoutY(0);
        sentTradesLabel.setLayoutX(350);
        sentTradesLabel.setOnMouseClicked(mouseEvent -> {
            receivedTradesList.setVisible(false);
            sentTradesList.setVisible(true);
        });
        Label receivedTradesLabel = new Label("Received Trades");
        receivedTradesLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #FFFFFF; -fx-background-color: #336699; -fx-padding: 10px;");
        receivedTradesLabel.setLayoutY(0);
        receivedTradesLabel.setLayoutX(1100-350);
        receivedTradesLabel.setOnMouseClicked(mouseEvent -> {

            sentTradesList.setVisible(false);
            receivedTradesList.setVisible(true);

        });

        pane.getChildren().add(sentTradesLabel);
        pane.getChildren().add(sentTradesList);
        pane.getChildren().add(receivedTradesLabel);
        pane.getChildren().add(receivedTradesList);
        pane.getChildren().add(backButton);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
    }
}
