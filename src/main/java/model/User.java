package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class User {
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

        Gson gson = new Gson();
        String filePath = new File("").getAbsolutePath().concat("/src/main/java/" +
                "model/data/users.json");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ArrayList<User> users = gson.fromJson(fileReader, new TypeToken<ArrayList<User>>() {}.getType());
        if (users == null) users = new ArrayList<>();
        users.add(this);
        try {
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gson.toJson(users, fileWriter);
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    public boolean isPasswordValid(String password) {
        return password.equals(this.getPassword());
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
    private static void saveChange(User changedUser) {
        ArrayList<User> users = Game.getUsers();
        for (User user : users) {
            if(user.getUsername().equals(changedUser.getUsername())){
                users.set(users.indexOf(user), user);
                break;
            }
        }
        Gson gson = new Gson();
        String filePath = new File("").getAbsolutePath().concat("/src/main/java/" +
                "model/data/users.json");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gson.toJson(users, fileWriter);
        try {
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
