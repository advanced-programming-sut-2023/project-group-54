package controller;

import model.*;
import model.Buildings.*;
import model.units.Engineer;
import model.units.Unit;
import view.BuildingMenu;
import view.enums.messages.MapMenuMessage;

import java.util.Collections;

import static model.Direction.*;
import static model.MapType.*;

public class MapMenuController {
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
        if (mapNumber == 1) {
            Game.setGameMap(400,400);
            Game.setX(400);
            Game.setY(400);
        }
        else if(mapNumber == 2) {
            Game.setGameMap(200,200);
            Game.setX(200);
            Game.setY(200);
        }
        else{
            Map[][] gameMap = new Map[400][400];
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    gameMap[i][j].setMapType(IRON);
                }
            }
            for (int i = 30; i < 55; i++) {
                for (int j = 0; j < 25; j++) {
                    gameMap[i][j].setMapType(PLAIN);
                }
            }
            for (int i = 50; i < 100; i++) {
                for (int j = 0; j < 50; j++) {
                    Game.getGameMap()[i][j].setMapType(THICK_SCRUB);
                }
            }
            for (int i = 30; i < 60; i++) {
                for (int j = 30; j < 70; j++) {
                    Game.getGameMap()[i][j].setMapType(BOULDERS);
                }
            }
            for (int i = 0; i < 100; i++) {
                for (int j = 60; j < 100; j++) {
                    Game.getGameMap()[i][j].setMapType(SEA);
                }
            }
            for (int i = 50; i < 100; i++) {
                for (int j =120; j < 150; j++) {
                    Game.getGameMap()[i][j].setMapType(SMALL_POND);
                    Game.getGameMap()[i][j].setTree(OLIVE_TREE);
                }
            }
            for (int i = 200; i < 250; i++) {
                for (int j = 0; j < 50; j++) {
                    Game.getGameMap()[i][j].setMapType(EARTH_AND_STONE);
                    Game.getGameMap()[i][j].setTree(COCONUT_PALM);
                }
            }
            for (int i = 150; i < 200; i++) {
                for (int j = 120; j < 160; j++) {
                    Game.getGameMap()[i][j].setMapType(IRON);
                }
            }
            for (int i = 0; i < 50; i++) {
                for (int j = 200; j < 280; j++) {
                    Game.getGameMap()[i][j].setMapType(OASIS_GRASS);
                }
            }
            for (int i = 220; i < 260; i++) {
                for (int j = 150; j < 160; j++) {
                    Game.getGameMap()[i][j].setMapType(RIVER);
                }
            }
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 80; j++) {
                    Game.getGameMap()[i][j].setMapType(THICK_SCRUB);
                }
            }
            for (int i = 200; i < 260; i++) {
                for (int j = 250; j < 300; j++) {
                    Game.getGameMap()[i][j].setMapType(OASIS_GRASS);
                }
                for (int j = 250; j < 270; j++) {
                    Game.getGameMap()[i][j].setTree(CHERRY_PALM);
                }
            }
            for (int i = 280; i < 320; i++) {
                for (int j = 0; j < 70; j++) {
                    Game.getGameMap()[i][j].setMapType(OIL);
                }
            }
            for (int i = 60; i < 100; i++) {
                for (int j = 150; j < 200; j++) {
                    Game.getGameMap()[i][j].setMapType(IRON);
                }
            }
            Game.setX(400);
            Game.setY(400);
            Game.setGameMap(gameMap);
        }
    }

    public static String showMap(int xCoordinate, int yCoordinate) {
        int numberOfHousesToShow = 20,xFirstHome = xCoordinate - numberOfHousesToShow,
                xEndHome = xCoordinate + numberOfHousesToShow,
                yFirstHome = yCoordinate - numberOfHousesToShow,
                yEndHome = yCoordinate + numberOfHousesToShow;
        if (xCoordinate < numberOfHousesToShow) xFirstHome = 0;
        else if (xCoordinate > Game.getX() - numberOfHousesToShow) xEndHome = Game.getX();
        if (yCoordinate < numberOfHousesToShow) yFirstHome = 0;
        else if (yCoordinate > Game.getY() - numberOfHousesToShow) yEndHome = Game.getY();
        Map[][] gameMap = new Map[Game.getX()][Game.getY()];
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
            if (unit instanceof Engineer) details.append(i + ") engineer owner : " + unit.getOwner().getUser().getUsername());
            else details.append(i + ") troop name : " + unit.getUnitType().getType() + " - troop hp : " + unit.getHp() +
                    "- unit " +((unit.getPatrol()) ? "is" : "is not") + " patrol\n\t- troop state : " + unit.getState() +
                    " - unit owner is : " + unit.getGovernment().getUser().getUsername() + "\n");
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
        //checking for not destroy the main house
        int x1 = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getX1Position();
        int y1 = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getY1Position();
        int x2 = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getX2Position();
        int y2 = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getY2Position();
        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                Game.getGameMap()[i][j].setBuilding(null);
            }
        }
        Game.getGameMap()[xCoordinate][yCoordinate].setUnit();
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

    public static MapMenuMessage dropBuilding(int x,int y,String type) {
        BuildingType buildingType = null;
        for (BuildingType allBuildingType : BuildingType.values()) {
            if (allBuildingType.getName().equals(type)) {
                buildingType = allBuildingType;
            }
        }
        if (buildingType == null) return MapMenuMessage.NOT_VALID_TYPE_FOR_DROP_BUILDING;
        int length = buildingType.getLength();
        int width = buildingType.getWidth();
        for (int i = x; i < length; i++) {
            for (int j = y; j < width; j++) {
                Map map = Game.getGameMap()[i][j];
                if (map.getBuilding() != null) return MapMenuMessage.HOUSE_IS_FILED_WITH_BUILDING;
                if (map.getMapType().isRock()) return MapMenuMessage.FORBIDDEN_DROP_BUILDING_ON_ROCK;
                if (!map.getMapType().isNormalThingPlacedOn()) return MapMenuMessage.CAN_NOT_PLACE_THAT_THING_ON_IT;
                if ((type.equals("mangonel") || type.equals("ballistae")) &&
                        (!map.getBuilding().getBuildingType().equals(BuildingType.SQUARE_TOWER) &&
                                !map.getBuilding().getBuildingType().equals(BuildingType.ROUND_TOWER)))
                    return MapMenuMessage.MANGONEL_AND_BALLIASTE_MUST_ON_SQUARE_OR_ROUND_TOWER;
                if ((type.equals("apple orchard") || type.equals("diary farmer")
                        || type.equals("hops farmer") || type.equals("wheat farmer")) &&
                        (!map.getMapType().equals(MapType.THICK_SCRUB) &&
                                !map.getMapType().equals(MapType.OASIS_GRASS)))
                    return MapMenuMessage.FARMS_NEED_TO_BE_ON_THICK_SCRUB_OR_OASIS_GRASS;
                if (type.equals("iron mine") &&
                        !map.getMapType().equals(MapType.IRON))
                    return MapMenuMessage.IRON_MINE_MUST_BE_ON_IRON;
                if (type.equals("pitch rig") &&
                        !map.getMapType().equals(MapType.SMALL_POND))
                    return MapMenuMessage.PITCH_RIG_ON_SMALL_POUND;
                if (type.equals("quarry") &&
                        !map.getMapType().equals(MapType.BOULDERS))
                    return MapMenuMessage.QUARRY_ON_BOULDERS;
                if ((!type.equals("mangonel") && !type.equals("ballistae")) &&
                        (map.getBuilding().getBuildingType().equals(BuildingType.SQUARE_TOWER) ||
                                map.getBuilding().getBuildingType().equals(BuildingType.ROUND_TOWER)))
                    return MapMenuMessage.ONLY_MANGONEL_BALLIASTE_ON_SQUARE_AND_ROUND;
                if (!type.equals("iron mine") &&
                        map.getMapType().equals(MapType.IRON))
                    return MapMenuMessage.ONLY_IRON_MINE_ON_IRON;
                if (!type.equals("pitch rig") &&
                        map.getMapType().equals(MapType.SMALL_POND))
                    return MapMenuMessage.ONLY_PITCH_RIG_ON_SMALL_POUND;
                if (!type.equals("quarry") &&
                        map.getMapType().equals(MapType.BOULDERS))
                    return MapMenuMessage.ONLY_QUARRY_ON_BOULDERS;
            }
        }
        return checkCost(x,y,buildingType,length,width);
    }

    private static MapMenuMessage checkCost(int x,int y,BuildingType buildingType,int length,int width) {
        if (buildingType.getCostType() != null ) {
            if(Game.getCurrentUser().getGovernment().getAllResources().get(buildingType.getCostType() ) < buildingType.getCostAmount())
                return MapMenuMessage.NOT_ENOUGH_RESOURCE;
        }
            if (buildingType.getCost() > Game.getCurrentUser().getGovernment().getGold()) return MapMenuMessage.NOT_ENOUGH_MONEY;
        if (Game.getCurrentUser().getGovernment().getUnemployedWorker() < buildingType.getWorkers())
            return MapMenuMessage.NOT_ENOUGH_WORKERS;
        Engineer engineer = null;
        for (Unit unit : Unit.getUnits()) {
            if (unit instanceof Engineer &&
                    unit.getOwner().equals(Game.getCurrentUser()) &&
                    !((Engineer)unit).isWorking()) engineer = ((Engineer) unit);
        }
        if (buildingType.isNeedEngineer() && engineer == null) return MapMenuMessage.NOT_ENOUGH_ENGINEER;
        return findClassDropBuilding(x,y,buildingType,length,width);
    }

    private static MapMenuMessage findClassDropBuilding(int x, int y, BuildingType buildingType, int length, int width) {
        BuildingGroup buildingGroup = buildingType.getBuildingGroup2();
        DefenseType defenseType = null;
        ProducerType producerType = null;
        SiegeType siegeType = null;
        StorageType storageType = null;
        TrapType trapType = null;
        if (buildingGroup.equals(BuildingGroup.DEFENSE_BUILDING)) {
            for (DefenseType allDefenceType : DefenseType.values()) {
                if (allDefenceType.getBuildingType().equals(buildingType))
                    defenseType = allDefenceType;
            }
        } else if (buildingGroup.equals(BuildingGroup.PRODUCER_BUILDING)) {
            for (ProducerType allProducerType : ProducerType.values()) {
                if (allProducerType.getBuildingType().equals(buildingType))
                    producerType = allProducerType;
            }
        } else if (buildingGroup.equals(BuildingGroup.SIEGE_BUILDING)) {
            for (SiegeType allSiegeType : SiegeType.values()) {
                if (allSiegeType.getBuildingType().equals(buildingType))
                    siegeType = allSiegeType;
            }
        } else if (buildingGroup.equals(BuildingGroup.STORAGE_BUILDING)) {
            for (StorageType allStorageType : StorageType.values()) {
                if (allStorageType.getBuildingType().equals(buildingType))
                    storageType = allStorageType;
            }
        } else if (buildingGroup.equals(BuildingGroup.TRAP_BUILDING)) {
            for (TrapType allTrapType : TrapType.values()) {
                if (allTrapType.getBuildingType().equals(buildingType))
                    trapType = allTrapType;
            }
        }
        for (int i = x; i < length; i++) {
            for (int j = y; j < width; j++) {
                if (defenseType != null) Game.getGameMap()[i][j].setBuilding(new DefenseBuilding(defenseType));
                else if (producerType != null) Game.getGameMap()[i][j].setBuilding(new ProducerBuilding(producerType));
                else if (siegeType != null) Game.getGameMap()[i][j].setBuilding(new SiegeBuilding(siegeType));
                else if (storageType != null ) Game.getGameMap()[i][j].setBuilding(new StorageBuilding(storageType));
                else if (trapType != null) Game.getGameMap()[i][j].setBuilding(new TrapBuilding(trapType));
                else Game.getGameMap()[i][j].setBuilding(new Building(buildingType,buildingType.getMaxHp(),
                            Game.getCurrentUser().getGovernment()));
            }
        }
        Game.getCurrentUser().getGovernment().setUnemployedWorker(buildingType.getWorkers());
        if (buildingType.isNeedEngineer()) {
            for (Unit unit : Unit.getUnits()) {
                if (unit instanceof Engineer &&
                        unit.getOwner().equals(Game.getCurrentUser()) &&
                        !((Engineer)unit).isWorking()) {
                    ((Engineer)unit).setWorking(true);
                    ((Engineer)unit).setBuildingWhichWorks(Game.getGameMap()[x][y].getBuilding());
                }
            }
        }
        int finalGold=Game.getCurrentUser().getGovernment().getGold()+ buildingType.getCost();
        Game.getCurrentUser().getGovernment().setGold(finalGold);
        Game.getCurrentUser().getGovernment().changeResourceAmount(buildingType.getCostType(),-buildingType.getCostAmount());
        Game.getCurrentUser().getGovernment().removeFromStorage(buildingType.getCostType(),-buildingType.getCostAmount());
        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage selectBuilding(int x,int y) {
        if (Game.getGameMap()[x][y].getBuilding() == null) return MapMenuMessage.HOUSE_IS_EMPTY;
        if (!Game.getGameMap()[x][y].getBuilding().getOwner().equals(Game.getCurrentUser().getGovernment()))
            return MapMenuMessage.BUILDING_OR_SOLDIER_DOESNT_BELONG_TO_YOU;
        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage selectUnit(int x,int y) {
        boolean sign = true;
        int numberOfSoldiers = 0;
        for (Unit unit : Game.getGameMap()[x][y].getUnit()) {
            numberOfSoldiers++;
            if (unit.getGovernment().equals(Game.getCurrentUser().getGovernment())) {
                sign = false;
                break;
            }
        }
        if (numberOfSoldiers == 0) return MapMenuMessage.HOUSE_IS_EMPTY;
        if (sign) return MapMenuMessage.BUILDING_OR_SOLDIER_DOESNT_BELONG_TO_YOU;
        return MapMenuMessage.SUCCESS;
    }

}
