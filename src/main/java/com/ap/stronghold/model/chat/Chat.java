package com.ap.stronghold.model.chat;

import com.ap.stronghold.model.User;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class Chat {
    @Expose
    private ArrayList<User> member;
    @Expose
    private ArrayList<Message> messages;
    @Expose
    private User owner;
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private int[] messageTimeForSort;

    public Chat(User owner, String id, String name) {
        this.owner = owner;
        this.id = id;
        this.name = name;
        this.member = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.messages.add(new Message(null,"public chat started"));
       // this.messageTimeForSort = messages.get(0).getMessageTimeForSort();
    }

    public String findMember(User user) {
        for (User user1 : member) {
            if (user1.equals(user))
                return "correct";
        }
        return null;
    }

    public void addMember(User user) {
        this.member.add(user);
    }

    public void addMessages(Message messages) {
        this.messages.add(messages);
    }

    public ArrayList<User> getMember() {
        return member;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public User getOwner() {
        return owner;
    }

    public String getId() {
        return id;
    }

    public int[] getMessageTimeForSort() {
        return messageTimeForSort;
    }

    public int getYear() {
        return messageTimeForSort[0];
    }

    public int getMonth() {
        return messageTimeForSort[1];
    }

    public int getDay() {
        return messageTimeForSort[2];
    }

    public int getHour() {
        return messageTimeForSort[3];
    }

    public int getMinute() {
        return messageTimeForSort[4];
    }
    public int getSecond() {
        return messageTimeForSort[5];
    }

    public void setMessageTimeForSort(int[] messageTimeForSort) {
        this.messageTimeForSort = messageTimeForSort;
    }

    public String getName() {
        return name;
    }
}
