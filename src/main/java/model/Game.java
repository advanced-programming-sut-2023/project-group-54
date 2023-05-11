package model;

import java.util.ArrayList;

public class Game {
    private static final ArrayList<ArrayList<Map>> map = new ArrayList<>();
    private static final ArrayList<User> users = new ArrayList<>();
    private static final ArrayList<Integer> lastTurn = new ArrayList<>();
    private static User currentUser;
    private static int currentTurn;
    private static int x;
    private static int y;
    private static Map[][] gameMap;

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static void setX(int x) {
        Game.x = x;
    }

    public static void setY(int y) {
        Game.y = y;
    }

    public static void setGameMap(int x,int y) {
        Game.gameMap = new Map[x][y];
    }

    public static Map[][] getGameMap() {
        return gameMap;
    }

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
