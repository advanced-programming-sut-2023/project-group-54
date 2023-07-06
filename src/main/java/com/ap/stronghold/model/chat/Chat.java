package com.ap.stronghold.model.chat;

import com.ap.stronghold.model.User;

import java.util.ArrayList;

public class Chat {
    private ArrayList<User> member;
    private ArrayList<Message> messages;
    private User owner;
    private String id;
    private String name;
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
