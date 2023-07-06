package com.ap.stronghold.model.chat;

import com.ap.stronghold.controller.Controller;
import com.ap.stronghold.model.User;
import com.google.gson.annotations.Expose;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

public class AllChat {
    @Expose
    public static HashSet<Chat> allChatsInTheGame = new HashSet<>();
    @Expose
    private ArrayList<Chat> allChatsOfUser;
    @Expose
    private int numberOfChat;
//    private final ArrayList<PrivateChat> privateChats;
//    private final ArrayList<Group> groups;
//    private final PublicChat publicChat;

    public AllChat() {
//        this.privateChats = new ArrayList<>();
//        this.groups = new ArrayList<>();
//        this.publicChat = User.getPublicChat();
        this.numberOfChat = 1;
        allChatsOfUser = new ArrayList<>();
        allChatsOfUser.add(User.getPublicChat());
    }

//    public ArrayList<PrivateChat> getPrivateChats() {
//        return privateChats;
//    }
//
//    public void addPrivateChats(PrivateChat privateChats) {
//        this.privateChats.add(privateChats);
//    }
//
//    public ArrayList<Group> getGroups() {
//        return groups;
//    }
//
//    public void addGroups(Group group) {
//        this.groups.add(group);
//    }
//
//    public PublicChat getPublicChat() {
//        return publicChat;
//    }

    public int getNumberOfChat() {
        return numberOfChat;
    }

    public void setNumberOfChat(int numberOfChat) {
        this.numberOfChat = numberOfChat;
    }

    public ArrayList<Chat> getAllChatsOfUser() {
        return allChatsOfUser;
    }

    public void addChat(Chat chat) {
        allChatsOfUser.add(chat);
        allChatsInTheGame.add(chat);
    }

    public void sortChats() {
        Comparator<Chat> compare1 = Comparator.comparing(Chat::getYear).reversed();
        Comparator<Chat> compare2 = Comparator.comparing(Chat::getMonth).reversed();
        Comparator<Chat> compare3 = Comparator.comparing(Chat::getDay).reversed();
        Comparator<Chat> compare4 = Comparator.comparing(Chat::getHour).reversed();
        Comparator<Chat> compare5 = Comparator.comparing(Chat::getMinute).reversed();
        Comparator<Chat> compare6 = Comparator.comparing(Chat::getSecond).reversed();
        Comparator<Chat> multipleFieldComparator = compare1
                .thenComparing(compare2).thenComparing(compare3).thenComparing(compare4)
                .thenComparing(compare5).thenComparing(compare6);
        Collections.sort(Controller.getLoggedInUser().getAllChat().getAllChatsOfUser(),multipleFieldComparator);
    }

    public Chat findChatsOfUser(String id) {
        for (Chat chat : allChatsOfUser) {
            if (chat.getId().equals(id))
                return chat;
        }
        return null;
    }


    public static Chat findChatInAll(String id) {
        for (Chat chat : allChatsInTheGame) {
            if (chat.getId().equals(id))
                return chat;
        }
        return null;
    }
}
