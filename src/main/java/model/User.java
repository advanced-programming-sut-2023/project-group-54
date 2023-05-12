package model;

import java.util.ArrayList;
import java.util.Collections;

public class User implements Comparable<User> {
    private static final ArrayList<User> users = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan;
    private int questionNumber;
    private String questionAnswer;
    private Government government;
    private int highScore;


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

    public int getHighScore() {
        return highScore;
    }
    public static int getUserRank(User user) {
        ArrayList<User> sortUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            sortUsers.add(users.get(i));
        }
        Collections.sort(sortUsers);
        for (int i = 0; i < sortUsers.size(); i++) {
            if (sortUsers.get(i).getUsername().equals(user.getUsername())) return i + 1;

        }
        return -1;
    }
    @Override
    public int compareTo(User a) {
        if (this.highScore < a.highScore) return 1;
        else if (this.highScore > a.highScore) return -1;
        else return this.username.compareTo(a.username);
    }
}
