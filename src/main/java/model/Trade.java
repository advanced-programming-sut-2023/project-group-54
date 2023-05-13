package model;

import java.util.ArrayList;

public class Trade {
    private static ArrayList<Trade> trades;
    private User senderUser;
    private User receiverUser;
    private Resource resource;
    private int amount;
    private int price;
    private String senderMessage;
    private String receiverMessage;

    public Trade(User senderUser, User receiverUser, Resource resource, int amount, int price, String senderMessage, String receiverMessage) {
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.resource = resource;
        this.amount = amount;
        this.price = price;
        this.senderMessage = senderMessage;
        this.receiverMessage = receiverMessage;
        trades.add(this);
    }


}