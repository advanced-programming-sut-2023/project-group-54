package model;

import java.util.ArrayList;

public class Game {
    private static final ArrayList<User> users = new ArrayList<>();
    private static User stayLoggedInUser = null;

    public static User getStayLoggedInUser() {
        return stayLoggedInUser;
    }

    public static void setStayLoggedInUser(User stayLoggedInUser) {
        Game.stayLoggedInUser = stayLoggedInUser;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUsers(User user) {
        Game.users.add(user);
    }
}
