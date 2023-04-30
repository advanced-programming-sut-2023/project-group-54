package model;

import java.util.ArrayList;

public class Game {
    private static ArrayList<ArrayList<Map>> map;
    private static ArrayList<User> users;
    private static User currentUser;
    private static ArrayList<Integer> lastTurn;
    private static int currentTurn;

    public static ArrayList<ArrayList<Map>> getMap() {
        return map;
    }

    public static ArrayList<User> getUsers() {
        return users;
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
}
