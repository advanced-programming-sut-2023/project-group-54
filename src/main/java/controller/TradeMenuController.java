package controller;

import model.Game;
import model.Government;
import model.Resource;
import model.*;
import view.enums.messages.ShopMenuMessage;
import view.enums.messages.TradeMenuMessage;

public class TradeMenuController {
    private static User user=Controller.getLoggedInUser();
    public String tradeListShow() {
        return "";
    }

    public String historyShow() {
        return "";
    }

    public static TradeMenuMessage trade(String username, Resource resource, int amount, int price, String message) {
        return TradeMenuMessage.SUCCESS;
    }

    public static TradeMenuMessage acceptTrade(String id, String message) {
        Trade trade=user.getGovernment().getAllTrades().get(Integer.parseInt(id));
        Resource resource=trade.getResource();
        int amount=trade.getAmount();
        User senderUser=trade.getSenderUser();
        if(user.getGovernment().getGold() < trade.getPrice()*amount)
            return TradeMenuMessage.NOT_ENOUGH_GOLD;
        if(!user.getGovernment().hasStorageForItem(resource,amount))
            return TradeMenuMessage.NOT_ENOUGH_CAPACITY;
        user.getGovernment().addToStorage(resource,amount);
        senderUser.getGovernment().removeFromStorage(resource,amount);
        user.getGovernment().getReceivedTrades().add(trade);
        return TradeMenuMessage.SUCCESS;
    }
    public static TradeMenuMessage trade(String resourceType,String amount ,String price,String message){
        Resource resource=Resource.getResourceByName(resourceType);
        int intAmount=Integer.parseInt(amount);
        int intPrice=Integer.parseInt(price);
        if(resource == null)
            return TradeMenuMessage.INVALID_ITEM;
       if(intAmount <= 0) return TradeMenuMessage.INVALID_AMOUNT;
       if(intPrice < 0) return TradeMenuMessage.INVALID_PRICE;
        if(user.getGovernment().getAllResources().get(resource) < intAmount) return TradeMenuMessage.NOT_ENOUGH_ITEM;
        Trade trade=new Trade(user,resource,intAmount,intPrice,message);
        user.getGovernment().getSentTrades().add(trade);
        for (int i = 0; i <Game.getUsers().size() ; i++) {
            if(!Game.getUsers().get(i).getUsername().equals(user.getUsername())) {
                Government government = Game.getUsers().get(i).getGovernment();
                government.getNewTrades().add(trade);
                government.getAllTrades().add(trade);
            }
        }
        return TradeMenuMessage.SUCCESS;
    }
}
