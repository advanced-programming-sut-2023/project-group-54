package model;

import java.util.ArrayList;

public class User {
    private static final ArrayList<User> users = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int questionNumber;
    private String questionAnswer;
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
        return this.getPassword().equals(password);
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
}
