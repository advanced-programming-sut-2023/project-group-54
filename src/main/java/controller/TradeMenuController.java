package controller;

import model.*;
import view.enums.messages.TradeMenuMessage;

public class TradeMenuController {
    public static TradeMenuMessage trade(String username, Resource resource, int amount, int price, String message) {
        return TradeMenuMessage.SUCCESS;
    }

    public static TradeMenuMessage acceptTrade(String id, String message) {
        if(Integer.parseInt(id) < 1 || Integer.parseInt(id) > Game.getCurrentUser().getGovernment().getAllTrades().size())
            return TradeMenuMessage.INVALID_INDEX;
        Trade trade = Game.getCurrentUser().getGovernment().getAllTrades().get(Integer.parseInt(id) - 1);
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
        Game.getCurrentUser().getGovernment().getReceivedTrades().add(trade);
        Game.getCurrentUser().getGovernment().setGold2(-(amount*trade.getPrice()));
        senderUser.getGovernment().setGold2((amount*trade.getPrice()));
        for (User user : Game.getUsers()) {
            user.getGovernment().getAllTrades().remove(trade);
            user.getGovernment().getNewTrades().remove(trade);
        }
        return TradeMenuMessage.SUCCESS;
    }

    public static TradeMenuMessage trade(String resourceType, String amount, String price, String message) {
        Resource resource = Resource.getResourceByName(resourceType);
        int intAmount = Integer.parseInt(amount);
        int intPrice = Integer.parseInt(price);
        if (resource == null)
            return TradeMenuMessage.INVALID_ITEM;
        if (intAmount <= 0) return TradeMenuMessage.INVALID_AMOUNT;
        if (intPrice < 0) return TradeMenuMessage.INVALID_PRICE;
        if (Game.getCurrentUser().getGovernment().getAllResources().get(resource) < intAmount) return TradeMenuMessage.NOT_ENOUGH_ITEM;
        Trade trade = new Trade(Game.getCurrentUser(), resource, intAmount, intPrice, message,null);
        Game.getCurrentUser().getGovernment().getSentTrades().add(trade);
        for (int i = 0; i < Game.getUsers().size(); i++) {
            if (!Game.getUsers().get(i).getUsername().equals(Game.getCurrentUser().getUsername())) {
                Government government = Game.getUsers().get(i).getGovernment();
                government.getNewTrades().add(trade);
                government.getAllTrades().add(trade);
            }
        }
        return TradeMenuMessage.SUCCESS;
    }


}
