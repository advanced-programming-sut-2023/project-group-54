package controller;

import model.*;
import model.Buildings.DefenseBuilding;
import model.units.Engineer;
import model.units.Unit;
import view.enums.messages.GameMenuMessage;
import view.enums.messages.MapMenuMessage;

import java.util.Collections;

import static model.Direction.*;
import static model.MapType.*;

public class MapMenuController {
    private static User currentUser;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\\u001B[40m";
    private static final String ANSI_RED = "\u001B[41m";
    private static final String ANSI_GREEN = "\u001B[42m";
    private static final String ANSI_YELLOW = "\u001B[43m";
    private static final String ANSI_BLUE = "\u001B[44m\n";
    private static final String ANSI_PURPLE = "\u001B[45m";
    private static final String ANSI_LIGHT_BLUE = "\u001B[46m\n";
    private static final String ANSI_WHITE = "\u001B[47m";
    private static final String ANSI_TEXT_BLACK = "\u001B[30m";
    private static final String ANSI_TEXT_RED ="\u001b[31m";
    private static final String ANSI_TEXT_GREEN = "\u001b[32m";
    private static final String ANSI_TEXT_YELLOW = "\u001b[33m";
    private static final String ANSI_TEXT_BLUE = "\u001b[34m";
    private static final String ANSI_TEXT_PURPLE = "\u001b[35m";
    private static final String ANSI_TEXT_LIGHT_BLUE = "\u001b[36m";
    private static final String ANSI_TEXT_WHITE = "\u001b[37m";


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
        int numberOfHousesToShow = 20,xFirstHome = xCoordinate - numberOfHousesToShow,
                xEndHome = xCoordinate + numberOfHousesToShow,
                yFirstHome = yCoordinate - numberOfHousesToShow,
                yEndHome = yCoordinate + numberOfHousesToShow;
        if (xCoordinate < numberOfHousesToShow) xFirstHome = 0;
        else if (xCoordinate > 480) xEndHome = 500;
        if (yCoordinate < numberOfHousesToShow) yFirstHome = 0;
        else if (yCoordinate > 480) yEndHome = 500;
        Map[][] gameMap = new Map[500][500];
        StringBuilder map = new StringBuilder();
        for (int i = xFirstHome; i < xEndHome; i++) {
            map.append(String.join("",Collections.nCopies((numberOfHousesToShow * 3) + 1,"-")) + "\n");
            for (int i1 = yFirstHome; i1 < yEndHome; i1++) {
                String[] color = backgroundColor(gameMap[i][i1].getMapType());
                map.append("|" + color[0] + color[1] + ((gameMap[i][i1].getBuilding() != null ) ? ((gameMap[i][i1].getBuilding() instanceof DefenseBuilding) ? "W" : "B") : "#")
                        + ((gameMap[i][i1].getTree() != null) ? "T" : "#") + ANSI_RESET);
            }
            map.append("|\n");
            for (int j = yFirstHome; j < yEndHome; j++) {
                String[] color = backgroundColor(gameMap[i][j].getMapType());
                int numberOfSoldiers = 0;
                if ( gameMap[i][j].getUnit().size() > 99) numberOfSoldiers = 99;
                map.append("|"+ color[0] + color[1] +((!gameMap[i][j].getUnit().isEmpty()) ? ((numberOfSoldiers > 9) ? numberOfSoldiers : numberOfSoldiers + "#") : "##") + ANSI_RESET);
            }
            map.append("|\n");
        }
        map.append("**earth has yellow background\t**earth and stone has yellow background and white text\t**boulder has yellow background and black text\n" +
                "**Rock has yellow background and red text\t**Iron has yellow background and purple text\t**oasis grass has green background\n" +
                "**scrub has green background and light blue text\t**thick scrub has green background and blue text\n");
        map.append("**oil has black background\t**plain has green background and black text\t**shallow water has blue background\n" +
                "**river has light blue background\t**small pond has blue background and white text\t**big pond has blue background and black text\n" +
                "**beach has yellow background and blue text\t**sea has blue background and rex text\n");
        map.append("first character of each house shows if there are buildings there or no if there is B that means there are a building " +
                "and if its a W that means we have wall there\nsecond characters in each house shows the number of soldiers in that house");
        return map.toString();
    }

    private static String[] backgroundColor(MapType mapType) {
        String[] colors = new String[2];
        colors[1] = "";
        switch (mapType) {
            case EARTH:
                colors[0] = ANSI_YELLOW;
                break;
            case EARTH_AND_STONE:
                colors[0] = ANSI_YELLOW;
                colors[1] = ANSI_TEXT_WHITE;
                break;
            case BOULDERS:
                colors[0] = ANSI_YELLOW;
                colors[1]= ANSI_TEXT_BLACK;
                break;
            case ROCK_N,ROCK_E,ROCK_S,ROCK_W:
                colors[0] = ANSI_YELLOW;
                colors[1] = ANSI_TEXT_RED;
                break;
            case IRON:
                colors[0] = ANSI_YELLOW;
                colors[1] = ANSI_TEXT_PURPLE;
                break;
            case OASIS_GRASS:
                colors[0] = ANSI_GREEN;
                break;
            case SCRUB:
                colors[0] = ANSI_GREEN;
                colors[1] = ANSI_TEXT_LIGHT_BLUE;
                break;
            case THICK_SCRUB:
                colors[0] = ANSI_GREEN;
                colors[1] = ANSI_TEXT_BLUE;
                break;
            default:
                return setBackgroundColorWater(mapType);
        }
        return colors;
    }

    private static String[] setBackgroundColorWater(MapType mapType) {
        String[] colors = new String[2];
        colors[1] = "";
        switch(mapType) {
            case OIL:
                colors[0] = ANSI_BLACK;
                break;
            case PLAIN:
                colors[0] = ANSI_GREEN;
                colors[1] = ANSI_TEXT_BLACK;
                break;
            case SHALLOW_WATER:
                colors[0] = ANSI_BLUE;
                break;
            case RIVER:
                colors[0] = ANSI_LIGHT_BLUE;
                break;
            case SMALL_POND:
                colors[0] = ANSI_BLUE;
                colors[1] = ANSI_TEXT_WHITE;
                break;
            case BIG_POUND:
                colors[0] = ANSI_BLUE;
                colors[1] = ANSI_TEXT_BLACK;
                break;
            case BEACH:
                colors[0] = ANSI_YELLOW;
                colors[1] = ANSI_BLUE;
                break;
            case SEA:
                colors[0] = ANSI_BLUE;
                colors[1] = ANSI_TEXT_RED;
                break;
        }
        return colors;
    }

    public static String showMapDetails(int xCoordinate, int yCoordinate) {
        Map house = Game.getGameMap()[xCoordinate][yCoordinate];
        StringBuilder details = new StringBuilder();
        int i = 1;
        details.append("Map type is :" + house.getMapType().toString() + "\nUnits in this house are : \n");
        details.append(((house.getUnit() != null ) ? "we have a " + house.getBuilding().getBuildingType().getName() +
                " building " + ((house.getBuilding().getBuildingType().getWorkers() != 0) ? "which has " +
                house.getBuilding().getBuildingType().getWorkers() + " workers" : "") : "there is no building here") + "\n");
        details.append(((house.getTree() != null) ? "we have tree with type " + house.getTree().toString() : "no tree in this house") + "\n");
        for (Unit unit : house.getUnit()) {
            if (unit instanceof Engineer) details.append(i + ") engineer owner : " + unit.getOwner().getUsername());
            else details.append(i + ") troop name : " + unit.getUnitType().getType() + " - troop hp : " + unit.getHp() +
                    "- unit " +((unit.getPatrol()) ? "is" : "is not") + " patrol\n\t- troop state : " + unit.getState() +
                    " - unit owner is : " + unit.getOwner().getUsername() + "\n");
            i++;
        }
        return details.toString();
    }

    public static String moveMap(int x, int y) {
        return "";
    }

    public static MapMenuMessage setTextureFinalTest(int xCoordinate, int yCoordinate) {
        if (Game.getGameMap()[xCoordinate][yCoordinate].getBuilding() != null) return MapMenuMessage.HOUSE_IS_FILED_WITH_BUILDING;
        return MapMenuMessage.SUCCESS;
    }

    public static void setTexture(int xCoordinate, int yCoordinate, MapType mapType) {
        Game.getGameMap()[xCoordinate][yCoordinate].setMapType(mapType);
        Game.getGameMap()[xCoordinate][yCoordinate].setBuilding(null);
        Game.getGameMap()[xCoordinate][yCoordinate].setTree(null);
    }

    public static MapMenuMessage clearBlock(int xCoordinate, int yCoordinate) {
        //to complete
        //checking for not destroy the main house
        //Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().
        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage dropRockFinalTest(int xCoordinate, int yCoordinate) {
        if (Game.getGameMap()[xCoordinate][yCoordinate].getBuilding() != null) return MapMenuMessage.HOUSE_IS_FILED_WITH_BUILDING;
        return MapMenuMessage.SUCCESS;
    }
    public static void dropRock(int x,int y,Direction direction) {
        Game.getGameMap()[x][y].setUnit();
        switch (direction) {
            case N:
                Game.getGameMap()[x][y].setMapType(MapType.ROCK_N);
                break;
            case S:
                Game.getGameMap()[x][y].setMapType(MapType.ROCK_S);
                break;
            case E:
                Game.getGameMap()[x][y].setMapType(MapType.ROCK_E);
                break;
            case W:
                Game.getGameMap()[x][y].setMapType(MapType.ROCK_W);
                break;
            case R:
                int random = (int) Math.floor(Math.random() * (3 + 1));
                switch (random) {
                    case 0:
                        dropRock(x,y,N);
                        break;
                    case 1:
                        dropRock(x,y,S);
                        break;
                    case 2:
                        dropRock(x,y,E);
                        break;
                    case 3:
                        dropRock(x,y,W);
                        break;
                }
        }
    }

    public static MapMenuMessage dropTreeFinalTest(int xCoordinate, int yCoordinate) {
        if (Game.getGameMap()[xCoordinate][yCoordinate].getBuilding() != null) return MapMenuMessage.HOUSE_IS_FILED_WITH_BUILDING;
        return MapMenuMessage.SUCCESS;
    }

    public static void dropTree(int xCoordinate, int yCoordinate, MapType tree) {
        Game.getGameMap()[xCoordinate][yCoordinate].setTree(tree);
    }
}
