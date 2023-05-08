package controller;

import model.*;
import model.Buildings.DefenseBuilding;
import view.enums.messages.GameMenuMessage;
import view.enums.messages.MapMenuMessage;

import java.util.Collections;

public class MapMenuController {
    private static User currentUser;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\\u001B[40m";
    private static final String ANSI_RED = "\u001B[41m";
    private static final String ANSI_GREEN = "\\u001B[42m";
    private static final String ANSI_YELLOW = "\\u001B[43m";
    private static final String ANSI_BLUED = "\\u001B[44m\n";
    private static final String ANSI_PURPLE = "\\u001B[45m";
    private static final String ANSI_LIGHT_BLUE = "\\u001B[46m\n";
    private static final String ANSI_WHITE = "\\u001B[47m";
    private static final String ANSI_BRIGHT_RED ="\\u001b[41;1m";
    private static final String ANSI_BRIGHT_GREEN = "\\u001b[42;1m";
    private static final String ANSI_BRIGHT_YELLOW = "\\u001b[43;1m";
    private static final String ANSI_BRIGHT_BLUE = "\\u001b[44;1m";
    private static final String ANSI_BRIGHT_PURPLE = "\\u001b[45;1m";
    private static final String ANSI_BRIGHT_LIGHT_BLUE = "\\u001b[46;1m";
    private static final String ANSI_BRIGHT_WHITE = "\\u001b[47;1m";


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
                String color = backgroundColor(gameMap[i][i1].getMapType());
                map.append("|" + ((gameMap[i][i1].getBuilding() != null ) ? ((gameMap[i][i1].getBuilding() instanceof DefenseBuilding) ? "W" : "B") : "#")
                        + ((gameMap[i][i1].getTree() != null) ? "T" : "#") + ANSI_RESET);
            }
            map.append("|\n");
            for (int j = yFirstHome; j < yEndHome; j++) {
                String color = backgroundColor(gameMap[i][j].getMapType());
                int numberOfSoldiers = 0;
                if ( gameMap[i][j].getUnit().size() > 99) numberOfSoldiers = 99;
                map.append("|"+((!gameMap[i][j].getUnit().isEmpty()) ? ((numberOfSoldiers > 9) ? numberOfSoldiers : numberOfSoldiers + "#") : "##") + ANSI_RESET);
            }
            map.append("|\n");
        }
        return map.toString();
    }

    private static String backgroundColor(MapType mapType) {
        switch (mapType) {
            case EARTH:
                return ANSI_WHITE_BACKGROUND;
            case EARTH_AND_STONE:
                return ;
            case BOULDERS:
                return ;
            case ROCK:
                return ;
            case IRON:
                return ;
            case OASIS_GRASS:
                return ANSI_GREEN_BACKGROUND;
            case SCRUB:
                return ;
            default://THICK_SCRUB
                return :
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
