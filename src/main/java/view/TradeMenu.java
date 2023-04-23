package view;
import controller.*;

import java.util.regex.Matcher;

public class TradeMenu {
    private TradeMenuController tradeMenuController;

    public TradeMenu() {
        this.tradeMenuController = new TradeMenuController();
    }
     private String run() {
        String command;
        Matcher matcher;
        //show all users;
         // show all requests;
         while (true) {
             command = Menu.getScanner().nextLine();
             //if show current menu
             //if back
             //if trade
             //if trade list
             //if trade accept
             //if trade history
         }
     }

     public void trade(Matcher matcher) {

     }

     public void tradeAccept(Matcher matcher) {

     }
}
