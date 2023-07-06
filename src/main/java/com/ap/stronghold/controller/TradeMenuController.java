package com.ap.stronghold.controller;

import com.ap.stronghold.model.*;
import com.ap.stronghold.view.enums.messages.TradeMenuMessage;

public class TradeMenuController {

    public static TradeMenuMessage trade(String username, Resource resource, int amount, int price, String message) {
        return TradeMenuMessage.SUCCESS;
    }

    public static TradeMenuMessage acceptTrade(int id, String message) {
        if(id < 1 || id > Game.getCurrentUser().getGovernment().getReceivedTrades().size())
            return TradeMenuMessage.INVALID_INDEX;
        Trade trade = Game.getCurrentUser().getGovernment().getReceivedTrades().get(id - 1);
        trade.setReceiverMessage(message);
        Resource resource = trade.getResource();
        int amount = trade.getAmount();
        User senderUser = trade.getSenderUser();
        if (Game.getCurrentUser().getGovernment().getGold() < trade.getPrice() * amount)
            return TradeMenuMessage.NOT_ENOUGH_GOLD;
        if (!Game.getCurrentUser().getGovernment().hasStorageForItem(resource, amount))
            return TradeMenuMessage.NOT_ENOUGH_CAPACITY;
        Game.getCurrentUser().getGovernment().addToStorage(resource, amount);
        senderUser.getGovernment().removeFromStorage(resource, amount);
        Game.getCurrentUser().getGovernment().setGold2(-(amount*trade.getPrice()));

        return TradeMenuMessage.SUCCESS;
    }

    public static TradeMenuMessage trade(String resourceType, String amount, String price, String message,User receiverUser) {
        Resource resource = Resource.getResourceByName(resourceType);
        int intAmount = Integer.parseInt(amount);
        int intPrice = Integer.parseInt(price);
        if (resource == null)
            return TradeMenuMessage.INVALID_ITEM;
        if (intAmount <= 0) return TradeMenuMessage.INVALID_AMOUNT;
        if (intPrice < 0) return TradeMenuMessage.INVALID_PRICE;
        if (Game.getCurrentUser().getGovernment().getAllResources().get(resource) < intAmount) return TradeMenuMessage.NOT_ENOUGH_ITEM;
        Trade trade = new Trade(Game.getCurrentUser(),receiverUser, resource, intAmount, intPrice, message,null);
        Game.getCurrentUser().getGovernment().getSentTrades().add(0,trade);
        Game.getCurrentUser().getGovernment().getAllTrades().add(0,trade);
        Government government = receiverUser.getGovernment();
        government.getReceivedTrades().add(0,trade);
        government.getNewTrades().add(0,trade);
        government.getAllTrades().add(0,trade);
        return TradeMenuMessage.SUCCESS;
    }


}
