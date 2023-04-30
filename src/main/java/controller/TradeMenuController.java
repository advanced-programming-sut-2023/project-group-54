package controller;

import model.Resource;
import view.enums.messages.TradeMenuMessage;

public class TradeMenuController {
    public String tradeListShow() {
        return "";
    }

    public String historyShow() {
        return "";
    }

    public TradeMenuMessage trade(String username, Resource resource, int amount, int price, String message) {
        return TradeMenuMessage.SUCCESS;
    }

    public TradeMenuMessage acceptTrade(String id, String message) {
        return TradeMenuMessage.SUCCESS;
    }
}
