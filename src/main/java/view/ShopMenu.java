package view;

import controller.Controller;
import controller.ShopMenuController;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.ShopMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;


public class ShopMenu {
    public void run() {
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
        }
    }

    public void showPriceList() {
        String result = ShopMenuController.showItemList();
        System.out.println(result);
    }

    public void buyItem(HashMap<String, ArrayList<String>> options) {
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

    public void sellItem(HashMap<String, ArrayList<String>> options) {
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
}
