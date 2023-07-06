package com.ap.stronghold.model.chat;

import com.ap.stronghold.model.User;
import com.google.gson.annotations.Expose;
import javafx.scene.input.InputMethodTextRun;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
    @Expose
    private User owner;
    @Expose
    private String content;
    @Expose
    private String messageTime;
    //private int[] messageTimeForSort;
    @Expose
    private boolean isMessageSent;
    @Expose
    private boolean isMessageSeen;
    @Expose
    private String reactions;


    public Message(User owner, String content) {
        this.owner = owner;
        this.content = content;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.messageTime = dtf.format(now);
//        String time = this.messageTime.replaceAll(" ","");
//        String[] time2 = time.split("/|:");
//        int[] timesToInt = new int[time2.length];
//        for (int i = 0; i < time2.length; i++)
//            timesToInt[i] = Integer.parseInt(time2[i]);
//        this.messageTimeForSort = timesToInt;
        this.isMessageSeen =false;
        this.isMessageSent = false;
        this.reactions = "reactions :\n";
    }

    public String getReactions() {
        return reactions;
    }

    public void setReactions(String reactions) {
        this.reactions += reactions;
    }

    public boolean isMessageSent() {
        return isMessageSent;
    }

    public void setMessageSent(boolean messageSent) {
        isMessageSent = messageSent;
    }

    public boolean isMessageSeen() {
        return isMessageSeen;
    }

    public void setMessageSeen(boolean messageSeen) {
        isMessageSeen = messageSeen;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMessageTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.messageTime = dtf.format(now);
        this.messageTime = messageTime;
    }

    public User getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public String getMessageTime() {
        return this.messageTime;
    }

//    public int[] getMessageTimeForSort() {
//        return messageTimeForSort;
//    }
}
