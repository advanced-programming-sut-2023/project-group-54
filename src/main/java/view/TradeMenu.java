package view;

import controller.Controller;
import controller.TradeMenuController;
import model.Game;
import model.Government;
import model.Trade;
import model.User;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.TradeMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class TradeMenu {
    public void run() {
        String command;
        Matcher matcher;
        HashMap<String, ArrayList<String>> options;
        while (true) {
            showAllPlayer();
            showNotifications();
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("trade menu");
            else if (CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if ((options = CommandHandler.parsCommand(Command.TRADE, command)) != null)
                trade(options);
            else if ((options = CommandHandler.parsCommand(Command.TRADE_LIST, command)) != null)
                tradeListShow();
            else if ((options = CommandHandler.parsCommand(Command.TRADE_ACCEPT, command)) != null)
                acceptTrade(options);
            else if ((options = CommandHandler.parsCommand(Command.TRADE_HISTORY, command)) != null)
                historyShow();
        }
    }

    private void showAllPlayer() {
        ArrayList<User> allUsers = User.getUsers();
        String result = null;
        for (int i = 0; i < allUsers.size(); i++) {
            User user = allUsers.get(i);
            result += i + 1 + ". " + user.getUsername() + "\n";
        }
        System.out.println(result.trim());
    }

    private void showNotifications() {
        Government government = Game.getCurrentUser().getGovernment();
        ArrayList<Trade> newTrades = government.getNewTrades();
        String notifications = null;
        for (Trade trade : newTrades) {
            notifications += "resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            notifications += " sender: " + trade.getSenderUser().getUsername() + "\n";
        }
        if (notifications == null) {
            System.out.println("you don't have any new notification");
            return;
        }
        System.out.println(notifications.trim());
        government.getNewTrades().clear();
    }

    public void tradeListShow() {
        Government government = Game.getCurrentUser().getGovernment();
        String result = null;

        for (int i = 0; i < government.getAllTrades().size(); i++) {
            Trade trade = government.getAllTrades().get(i);
            result += i + 1 + " resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            result += " sender: " + trade.getSenderUser().getUsername() + "\n";
        }
        if (result == null)
            System.out.println("you don't have any request or donation");
        else {
            System.out.println(result.trim());
        }
    }

    public void historyShow() {
        Government government = Game.getCurrentUser().getGovernment();
        String result = null;
        for (int i = 0; i < government.getSentTrades().size(); i++) {
            Trade trade = government.getSentTrades().get(i);
            result += i + 1 + " resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            result += " sender: " + trade.getSenderUser().getUsername() + "\n";
        }
        for (int i = 0; i < government.getReceivedTrades().size(); i++) {
            Trade trade = government.getReceivedTrades().get(i);
            result += i + 1 + " resourceType: " + trade.getResource() + " amount: " + trade.getAmount() + " price: " + trade.getPrice() + " message: " + trade.getSenderMessage();
            result += " acceptMessage: "+trade.getReceiverMessage()+" sender: " + trade.getSenderUser().getUsername() + "\n";
        }
        if (result == null) {
            System.out.println("your history is empty");
            return;
        }
        System.out.println(result.trim());


    }

    public void trade(HashMap<String, ArrayList<String>> options) {
        String resourceType = null;
        String amount = null;
        String price = null;
        String message = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "t" -> resourceType = Controller.buildParameter(options.get(s).get(0));
                case "a" -> amount = Controller.buildParameter(options.get(s).get(0));
                case "p" -> price = Controller.buildParameter(options.get(s).get(0));
                case "message" -> message = Controller.buildParameter(options.get(s).get(0));
            }
        }
        if (resourceType == null) {
            System.out.println("resource not entered");
            return;
        }
        if (amount == null) {
            System.out.println("amount not entered");
            return;
        }
        if (!Controller.isNumeric(amount)) {
            System.out.println("amount format is invalid please enter a amount");
            return;
        }
        if (price == null) {
            System.out.println("price not entered");
            return;
        }
        if (!Controller.isNumeric(price)) {
            System.out.println("price format is invalid please enter a number");
            return;
        }
        if (message == null) {
            System.out.println("message not entered");
            return;
        }
        TradeMenuMessage result = TradeMenuController.trade(resourceType, amount, price, message);
        switch (result) {
            case INVALID_ITEM -> System.out.println("item is invalid we don't have this item in our game");
            case INVALID_AMOUNT -> System.out.println("amount is invalid it should be at least 1");
            case INVALID_PRICE -> System.out.println("price is invalid price should be grater than 0");
            case SUCCESS -> System.out.println("your trade request has been sent successfully");
        }


    }

    public void acceptTrade(HashMap<String, ArrayList<String>> options) {
        String id = null;
        String message = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "i" -> id = Controller.buildParameter(options.get(s).get(0));
                case "message" -> message = Controller.buildParameter(options.get(s).get(0));
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
}
