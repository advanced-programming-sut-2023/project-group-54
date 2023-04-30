package controller;

import view.enums.messages.ShopMenuMessage;

public class ShopMenuController {
    public String showItemList() {
        return "";
    }

    public ShopMenuMessage buyItem(String itemName, int amount) {
        return ShopMenuMessage.SUCCESS;
    }

    public ShopMenuMessage sellItem(String itemName, int amount) {
        return ShopMenuMessage.SUCCESS;
    }
}
