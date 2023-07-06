package com.ap.stronghold.model;

import com.ap.stronghold.model.chat.AllChat;
import com.ap.stronghold.model.chat.Group;
import com.ap.stronghold.model.chat.PublicChat;
import com.ap.stronghold.view.ProfileMenu;
import com.ap.stronghold.view.ScoreBoard;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.ap.stronghold.controller.Controller;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class User implements Comparable<User>{
    private static ArrayList<User> users = new ArrayList<>();
    private static PublicChat publicChat = new PublicChat();
    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String nickname;
    @Expose
    private String email;
    @Expose
    private String slogan;
    @Expose
    private int questionNumber;
    @Expose
    private String questionAnswer;
    @Expose
    private int highScore;
    @Expose
    private String avatarPath;
    private AllChat allChat;
    private ArrayList<User> friends;
        private Government government;

    public User(String username, String password, String nickname, String email, String slogan, int questionNumber, String questionAnswer, Government government) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.slogan = slogan;
        this.questionNumber = questionNumber;
        this.questionAnswer = questionAnswer;
        this.government = government;
        this.highScore = 0;
        this.avatarPath ="C:\\Users\\Amirhosein\\IdeaProjects\\project-group-54-after-prof\\target\\classes\\com\\ap\\stronghold\\Media\\Avatars\\5.png";
        users.add(this);
        this.allChat = new AllChat();
        this.friends = new ArrayList<>();
    }


    public static void loadUser(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String filePath = new File("").getAbsolutePath().concat("/src/main/java/com/ap/stronghold/model/data/users.json");
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<User> allUsers = gson.fromJson(fileReader, new TypeToken<ArrayList<User>>() {}.getType());
        if (allUsers == null) allUsers = new ArrayList<>();
        users = allUsers;
        AllChat.allChatsInTheGame.add(publicChat);
        for (User user : users) {
            Government government = new Government();
            user.setGovernment(government);
            government.setUser(user);
            user.allChat = new AllChat();
            user.allChat.addChat(publicChat);
            user.friends = new ArrayList<>();
        }
    }

    public static void saveUser(){
        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String filePath = new File("").getAbsolutePath().concat("/src/main/java/com/ap/stronghold/model/data/users.json");
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gson.toJson(User.getUsers(), fileWriter);
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setUsers(ArrayList<User> users) {
        User.users = users;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getSlogan() {
        return slogan;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public Government getGovernment() {
        return government;
    }
    public static User findUserByUsername(String username){
        for (User user : User.getUsers()) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }
    public static User findUserByEmail(String email) {
        for (User user : User.getUsers()) {
            if (user.getEmail().equalsIgnoreCase(email)) return user;
        }
        return null;
    }
    public boolean isPasswordValid(String password){
        return this.getPassword().equals(Controller.hashString(password));
    }


    public void setHighScore(int highScore) {
        this.highScore += highScore;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public void setGovernment(Government government) {
        this.government = government;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public int getHighScore() {
        return highScore;
    }

    public static ArrayList<User> getUsersSorted() {
        ArrayList<User> sortUsers = new ArrayList<>(users);
        Collections.sort(sortUsers);
        return sortUsers;
    }

    public int getUserRank() {
        ArrayList<User> sortUsers = new ArrayList<>(users);
        Collections.sort(sortUsers);
        for (User sortUser : sortUsers) {
            if(sortUser.getUsername().equals(this.getUsername())){
                return sortUsers.indexOf(sortUser) + 1;
            }
        }
        return -1;
    }

    public static PublicChat getPublicChat() {
        return publicChat;
    }

    public AllChat getAllChat() {
        return allChat;
    }

    @Override
    public int compareTo(User a) {
        if (this.highScore < a.highScore) return 1;
        else if (this.highScore > a.highScore) return -1;
        else return this.username.compareTo(a.username);
    }

    @Override
    public String toString() {
        return this.username;
    }
    public static ArrayList<String> usersToString(){
        ArrayList<String> result=new ArrayList<>();
        for (int i=0;i< users.size();i++){
            if(!users.get(i).equals(Game.getCurrentUser()))
            result.add(users.get(i).getUsername());
        }
        return result;
    }
}
