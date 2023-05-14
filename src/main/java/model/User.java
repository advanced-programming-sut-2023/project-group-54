package model;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import controller.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class User implements Comparable{
    private static final ArrayList<User> users;
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

    private Government government;

    static {
        Gson gson = new Gson();
        String filePath = new File("").getAbsolutePath().concat("/src/main/java/model/data/users.json");
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<User> allUsers = gson.fromJson(fileReader, new TypeToken<ArrayList<User>>() {}.getType());
        if (allUsers == null) allUsers = new ArrayList<>();
        users = allUsers;
        for (User user : users) {
            user.setGovernment(new Government());
        }
    }

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
    public static void addUser(User user){
        users.add(user);
    }

    public int getHighScore() {
        return highScore;
    }
    public int getUserRank() {
        ArrayList<User> sortUsers = new ArrayList<>(users);
        Collections.sort(sortUsers);
        return sortUsers.indexOf(this);
    }

    @Override
    public int compareTo(Object a) {
        if (this.highScore < ((User) a).highScore) return 1;
        else if (this.highScore > ((User) a).highScore) return -1;
        else return this.username.compareTo(((User) a).username);
    }
}
