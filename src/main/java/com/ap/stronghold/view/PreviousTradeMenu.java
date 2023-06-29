package com.ap.stronghold.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PreviousTradeMenu extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        Pane pane= FXMLLoader.load(LoginMenu.class.getResource("/com/ap/stronghold/FXML/previousTradeMenu.fxml"));
        ListView tradesList=TradeMenu.tradeListShow();
        pane.getChildren().add(tradesList);
        Scene scene=new Scene(pane);
        stage.setScene(scene);
    }
}
