package model;

import java.util.ArrayList;

public class Game {
    private static final ArrayList<User> users = new ArrayList<>();
    private static User stayLoggedInUser = null;
    private static ArrayList<ArrayList<Map>> map;

    private static User currentUser;
    private static ArrayList<Integer> lastTurn;
    private static int currentTurn;

    public static ArrayList<ArrayList<Map>> getMap() {
        return map;
    }



    public static User getCurrentUser() {
        return currentUser;
    }

    public static ArrayList<Integer> getLastTurn() {
        return lastTurn;
    }

    public static int getCurrentTurn() {
        return currentTurn;
    }

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
