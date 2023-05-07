package controller;

import model.*;
import view.enums.messages.GameMenuMessage;
import view.enums.messages.MapMenuMessage;

public class MapMenuController {
    private static User currentUser;
    public static void setMap(int mapNumber) {
        sw
    }

    public static String showMap(int xCoordinate, int yCoordinate) {
        return "";
    }

    public static String showMapDetails(int xCoordinate, int yCoordinate) {
        return "";
    }

    public static String moveMap(int x, int y) {
        return "";
    }

    public static MapMenuMessage setTexture(int x1Coordinate, int y1Coordinate, int x2Coordinate, int y2Coordinate, MapType mapType) {
        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage clearBlock(int xCoordinate, int yCoordinate) {
        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage dropRock(int xCoordinate, int yCoordinate, Direction direction) {
        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage dropTree(int xCoordinate, int yCoordinate, TreeType tree) {
        return MapMenuMessage.SUCCESS;
    }
}
