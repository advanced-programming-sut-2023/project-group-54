package model;

import model.Buildings.Building;

import java.util.ArrayList;

public class Game {
    private static ArrayList<User> users;
    private static ArrayList<Building> mainHouses;
    private static User currentUser;
    private static int x;
    private static int y;
    private static Map[][] gameMap;

    public static ArrayList<Building> getMainHouses() {
        return mainHouses;
    }

    public static void setMainHouses() {
        Game.mainHouses = new ArrayList<>();
    }

    public static void addMainHouses(Building mainHouses) {
        Game.mainHouses.add(mainHouses);
    }

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        Game.x = x;
    }

    public static int getY() {
        return y;
    }

    public static void setY(int y) {
        Game.y = y;
    }

    public static void setGameMap(int x, int y) {
        Game.gameMap = new Map[x][y];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                Game.gameMap[i][j] = new Map(i, j);
            }
        }
    }

    public static Map[][] getGameMap() {
        return gameMap;
    }

    public static void setGameMap(Map[][] gameMap) {
        Game.gameMap = gameMap;
    }

    public static Map getGameMapXY(int x, int y) {
        return gameMap[x][y];
    }

    public static void setGameMapXY(int x, int y, Map map) {
        gameMap[x][y] = map;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        Game.users = users;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Game.currentUser = currentUser;
    }
}
