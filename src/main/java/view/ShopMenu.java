package view;
import controller.*;

import java.util.regex.Matcher;

public class ShopMenu {
    private ShopMenuController shopMenuController;

    public ShopMenu() {
        this.shopMenuController = new ShopMenuController();
    }

    public String run() {
        String command;
        Matcher matcher;
        while (true) {
            command = Menu.getScanner().nextLine();
            //if show current menu
            //if back
            //if show price list
            //if buy item
            //if sell item
        }
    }

    public void sellItem(Matcher matcher) {

    }

    public void buyItem(Matcher matcher) {

    }
}
