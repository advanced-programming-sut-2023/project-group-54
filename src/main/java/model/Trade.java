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
    private int id;
}