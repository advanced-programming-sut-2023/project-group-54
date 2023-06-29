package com.ap.stronghold.model;

import java.util.ArrayList;

public class Trade {
    private static ArrayList<Trade> trades=new ArrayList<>();
    private User senderUser;
    private User receiverUser;
    private Resource resource;
    private int amount;
    private int price;
    private String senderMessage;
    private String receiverMessage;
    private boolean acceptedStatus;

    public Trade(User senderUser,User receiverUser, Resource resource, int amount, int price, String senderMessage, String receiverMessage) {
        this.senderUser = senderUser;
        this.resource = resource;
        this.amount = amount;
        this.price = price;
        this.senderMessage = senderMessage;
        this.receiverMessage = receiverMessage;
        this.receiverUser=receiverUser;
        this.acceptedStatus=false;
        trades.add(this);
    }

    public static ArrayList<Trade> getTrades() {
        return trades;
    }

    public static void setTrades(ArrayList<Trade> trades) {
        Trade.trades = trades;
    }

    public User getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(User senderUser) {
        this.senderUser = senderUser;
    }

    public User getReceiverUser() {
        return receiverUser;
    }

    public void setReceiverUser(User receiverUser) {
        this.receiverUser = receiverUser;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public void setSenderMessage(String senderMessage) {
        this.senderMessage = senderMessage;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void setReceiverMessage(String receiverMessage) {
        this.receiverMessage = receiverMessage;
    }

}