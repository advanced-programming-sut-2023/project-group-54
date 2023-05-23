package controller;

import model.Game;
import model.Resource;
import model.User;
import view.enums.messages.ShopMenuMessage;

import java.util.HashMap;

public class ShopMenuController {
    private static User user = Game.getCurrentUser();

    public static String showItemList() {
        String result = "";
        int counter = 1;

        for (Resource item : Resource.values()) {
            result += (counter++) + "- itemName:" + item.getName() +
                    " Buy Price: " + item.getBuyPrice() + " Sell Price: " + item.getSellPrice() +
                    " Your Storage Amount: " + user.getGovernment().getResourceCount(item) + "\n";
        }
        return result.trim();

    }

    public static ShopMenuMessage buyItemChecker(String itemName, int amount) {
        Resource item = Resource.getResourceByName(itemName);
        if (item == null) return ShopMenuMessage.INVALID_ITEM;
        if (amount <= 0) return ShopMenuMessage.INVALID_AMOUNT;
        if (user.getGovernment().getGold() < item.getBuyPrice() * amount) return ShopMenuMessage.NOT_ENOUGH_GOLD;
        if (!(user.getGovernment().hasStorageForItem(item, amount))) return ShopMenuMessage.NOT_ENOUGH_CAPACITY;
        return ShopMenuMessage.SUCCESS;

    }

    public static ShopMenuMessage buyItemConfirm(String confirmAnswer, String itemName, int amount) {
        Resource item = Resource.getResourceByName(itemName);
        if (confirmAnswer.matches("\\s*[Yy]\\s*")) {
            int finalGold = user.getGovernment().getGold() - item.getBuyPrice() * amount;
            user.getGovernment().setGold(finalGold);
            user.getGovernment().changeResourceAmount(item, amount);
            user.getGovernment().addToStorage(item, amount);
            return ShopMenuMessage.SUCCESS;
        } else return ShopMenuMessage.CANCEL;
    }

    public static ShopMenuMessage sellItemChecker(String itemName, int amount) {
        Resource item = Resource.getResourceByName(itemName);
        if (item == null) return ShopMenuMessage.INVALID_ITEM;
        if (amount <= 0) return ShopMenuMessage.INVALID_AMOUNT;
        if (user.getGovernment().getAllResources().get(item) < amount) return ShopMenuMessage.NOT_ENOUGH_ITEM;
        return ShopMenuMessage.SUCCESS;

    }

    public static ShopMenuMessage sellItemConfirm(String confirmAnswer, String itemName, int amount) {
        Resource item = Resource.getResourceByName(itemName);
        if (confirmAnswer.matches("\\s*[Yy]\\s*")) {
            int finalGold = user.getGovernment().getGold() + item.getSellPrice()*amount;
            user.getGovernment().setGold(finalGold);
            user.getGovernment().changeResourceAmount(item, -amount);
            user.getGovernment().removeFromStorage(item, amount);
            return ShopMenuMessage.SUCCESS;
        } else return ShopMenuMessage.CANCEL;
    }
}
