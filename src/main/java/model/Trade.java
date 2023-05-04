package model;

import java.util.ArrayList;

public class Trade {
    private static ArrayList<Trade> trades = new ArrayList<>();
    private User senderUser;
    private User receiverUser;
    private Resource resource;
    private int amount;
    private int price;
    private String senderMessage;
    private String receiverMessage;
    private int id;

    public Trade(User senderUser, User receiverUser, Resource resource, int amount, int price, String senderMessage, String receiverMessage) {
        this.senderUser = senderUser;
        this.receiverUser = receiverUser;
        this.resource = resource;
        this.amount = amount;
        this.price = price;
        this.senderMessage = senderMessage;
        this.receiverMessage = receiverMessage;
        id = 1;
        trades.add(this);
    }
}