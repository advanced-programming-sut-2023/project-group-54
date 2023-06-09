package com.ap.stronghold.controller;

import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.Government;
import com.ap.stronghold.model.Resource;
import com.ap.stronghold.model.User;
import com.ap.stronghold.view.enums.messages.ShopMenuMessage;

public class ShopMenuController {
    private static User user = Game.getCurrentUser();


    public static double getAmount(String itemName) {
        Resource resource=Resource.getResourceByName(itemName);
        return user.getGovernment().getResourceCount(resource);
    }

    public static ShopMenuMessage buyItemChecker(String itemName, int amount) {
        Resource item = Resource.getResourceByName(itemName);
        if (item == null) return ShopMenuMessage.INVALID_ITEM;
        if (amount <= 0) return ShopMenuMessage.INVALID_AMOUNT;
        if (user.getGovernment().getGold() < item.getBuyPrice() * amount) return ShopMenuMessage.NOT_ENOUGH_GOLD;
        if (!(user.getGovernment().hasStorageForItem(item, amount))) return ShopMenuMessage.NOT_ENOUGH_CAPACITY;
        return ShopMenuMessage.SUCCESS;

    }

    public static void buyItemConfirm( String itemName, int amount) {
        Resource item = Resource.getResourceByName(itemName);
            int finalGold = user.getGovernment().getGold() - item.getBuyPrice() * amount;
            user.getGovernment().setGold(finalGold);
            user.getGovernment().changeResourceAmount(item, amount);
            user.getGovernment().addToStorage(item, amount);

    }

    public static ShopMenuMessage sellItemChecker(String itemName, int amount) {
        Resource item = Resource.getResourceByName(itemName);
        if (item == null) return ShopMenuMessage.INVALID_ITEM;
        if (amount <= 0) return ShopMenuMessage.INVALID_AMOUNT;
        if (user.getGovernment().getAllResources().get(item) < amount) return ShopMenuMessage.NOT_ENOUGH_ITEM;
        return ShopMenuMessage.SUCCESS;

    }

    public static ShopMenuMessage sellItemConfirm( String itemName, int amount) {
        Resource item = Resource.getResourceByName(itemName);
            int finalGold = user.getGovernment().getGold() + item.getSellPrice()*amount;
            user.getGovernment().setGold(finalGold);
            user.getGovernment().changeResourceAmount(item, -amount);
            user.getGovernment().removeFromStorage(item, amount);
            return ShopMenuMessage.SUCCESS;

    }
}
