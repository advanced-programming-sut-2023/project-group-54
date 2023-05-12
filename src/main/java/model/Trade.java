package model;

import java.util.ArrayList;

public class Trade {
    private static ArrayList<Trade> trades;
    private User senderUser;
    private User receiverUser;
    private Resource resource;
    private int amount;
    private int price;
    private String message;

    public Trade(User senderUser,Resource resource,int amount,int price,String message){
     this.senderUser=senderUser;
     this.resource=resource;
     this.amount=amount;
     this.price=price;
     this.message=message;
    }
    public Resource getResource(){return this.resource;}

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}