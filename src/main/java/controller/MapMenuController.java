package controller;

import model.*;
import view.enums.messages.GameMenuMessage;
import view.enums.messages.MapMenuMessage;

import java.util.Collections;

public class MapMenuController {
    private static User currentUser;
    private final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    private static final String ANSI_BLACK_BACKGROUND = "\\u001B[40m";
    private static final String ANSI_GREEN_BACKGROUND = "\\u001B[42m";
    private static final String ANSI_YELLOW_BACKGROUND = "\\u001B[43m";
    private static final String ANSI_BLUE_BACKGROUND = "\\u001B[44m\n";
    private static final String ANSI_LIGHT_BLUE_BACKGROUND = "\\u001B[46m\n";

    public static void setMap(int mapNumber) {
        Map[][] gameMap = new Map[500][500];
        gameMap = Game.getGameMap();
        if (mapNumber == 1) {
            for (int i = 0; i < 50; i++) {
                for (int i1 = 0; i1 < gameMap[i].length; i1++) {

                }
            }
        }
    }

    public static String showMap(int xCoordinate, int yCoordinate) {
        int numberOfHousesToShow = 40,xFirstHome = xCoordinate - 40,
                xEndHome = xCoordinate + 40,
                yFirstHome = yCoordinate - 40,
                yEndHome = yCoordinate + 40;
        if (xCoordinate < 40) xFirstHome = 0;
        else if (xCoordinate > 460) xEndHome = 500;
        if (yCoordinate < 40) yFirstHome = 0;
        else if (yCoordinate > 460) yEndHome = 500;
        Map[][] gameMap = new Map[500][500];
        StringBuilder map = new StringBuilder();
        for (int i = xFirstHome; i < xEndHome; i++) {
            map.append(String.join("",Collections.nCopies((numberOfHousesToShow * 3) + 1,"-")));
            for (int i1 = yFirstHome; i1 < yEndHome; i1++) {
                map.append("|" + (gameMap[i][i1] == null) ? );
            }
        }
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
