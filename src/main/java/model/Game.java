package model;

import java.util.ArrayList;

public class Game {
    private static final ArrayList<ArrayList<Map>> map = new ArrayList<>();
    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Integer> lastTurn = new ArrayList<>();
    private static User currentUser;
    private static int currentTurn;
    private static Map[][] gameMap = new Map[500][500];

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
