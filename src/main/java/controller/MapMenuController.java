package controller;

import model.*;
import model.Buildings.DefenseBuilding;
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
        int numberOfHousesToShow = 20,xFirstHome = xCoordinate - 20,
                xEndHome = xCoordinate + 20,
                yFirstHome = yCoordinate - 20,
                yEndHome = yCoordinate + 20;
        if (xCoordinate < 20) xFirstHome = 0;
        else if (xCoordinate > 480) xEndHome = 500;
        if (yCoordinate < 20) yFirstHome = 0;
        else if (yCoordinate > 480) yEndHome = 500;
        Map[][] gameMap = new Map[500][500];
        StringBuilder map = new StringBuilder();
        for (int i = xFirstHome; i < xEndHome; i++) {
            map.append(String.join("",Collections.nCopies((numberOfHousesToShow * 3) + 1,"-")) + "\n");
            for (int i1 = yFirstHome; i1 < yEndHome; i1++) {
                map.append("|" + ((gameMap[i][i1].getBuilding() != null ) ? ((gameMap[i][i1].getBuilding() instanceof DefenseBuilding) ? "W" : "B") : "#")
                        + ((gameMap[i][i1].getTree() != null) ? "T" : "#"));
            }
            map.append("|\n");
            for (int j = yFirstHome; j < yEndHome; j++) {
                int numberOfSoldiers = 0;
                if ( gameMap[i][j].getUnit().size() > 99) numberOfSoldiers = 99;
                map.append("|"+((!gameMap[i][j].getUnit().isEmpty()) ? ((numberOfSoldiers > 9) ? numberOfSoldiers : numberOfSoldiers + "#") : "##"));
            }
            map.append("|\n");
        }
        return map.toString();
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
