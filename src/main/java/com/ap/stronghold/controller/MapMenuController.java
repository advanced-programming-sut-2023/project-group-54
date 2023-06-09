package com.ap.stronghold.controller;

import com.ap.stronghold.model.*;
import com.ap.stronghold.model.Buildings.*;
import com.ap.stronghold.model.units.Engineer;
import com.ap.stronghold.model.units.Unit;
import com.ap.stronghold.view.enums.messages.MapMenuMessage;
import javafx.scene.paint.Color;
import org.checkerframework.checker.units.qual.C;

import java.util.Collections;

import static com.ap.stronghold.model.Direction.*;
import static com.ap.stronghold.model.MapType.*;

public class MapMenuController {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[40m";
    private static final String ANSI_RED = "\u001B[41m";
    private static final String ANSI_GREEN = "\u001B[42m";
    private static final String ANSI_YELLOW = "\u001B[43m";
    private static final String ANSI_BLUE = "\u001B[44m";
    private static final String ANSI_PURPLE = "\u001B[45m";
    private static final String ANSI_LIGHT_BLUE = "\u001B[46m";
    private static final String ANSI_WHITE = "\u001B[47m";
    private static final String ANSI_TEXT_BLACK = "\u001B[30m";
    private static final String ANSI_TEXT_RED = "\u001b[31m";
    private static final String ANSI_TEXT_GREEN = "\u001b[32m";
    private static final String ANSI_TEXT_YELLOW = "\u001b[33m";
    private static final String ANSI_TEXT_BLUE = "\u001b[34m";
    private static final String ANSI_TEXT_PURPLE = "\u001b[35m";
    private static final String ANSI_TEXT_LIGHT_BLUE = "\u001b[36m";
    private static final String ANSI_TEXT_WHITE = "\u001b[37m";

//    public static void setMainHouse(int whichMap) {
//        for (int i = 0; i < Game.getUsers().size(); i++) {
//            if (i < 4) {
//                for (int j = whichMap + i * whichMap; j < whichMap + i * whichMap + 4; j++) {
//                    for (int k = whichMap + 25; k < whichMap + 25 + 4; k++) {
//                        Game.getGameMap()[whichMap + i * whichMap + j][whichMap + 25 + k].setBuilding(new Building(BuildingType.MAIN_HOUSE
//                                , BuildingType.MAIN_HOUSE.getMaxHp(), Game.getUsers().get(i).getGovernment()));
//                    }
//                }
//                dropBuilding(whichMap + i * whichMap + 4, whichMap + 25, "granary");
//                Building.addBuildings(Game.getGameMap()[whichMap + i * whichMap][whichMap + 25].getBuilding());
//            } else {
//                for (int j = whichMap + (i - 4) * whichMap; j < whichMap + (i - 4) * whichMap + 4; j++) {
//                    for (int k = whichMap * 4; k < whichMap * 4 + 4; k++) {
//                        Game.getGameMap()[whichMap + (i - 4) * whichMap + j][whichMap * 4 + k].setBuilding(new Building(BuildingType.MAIN_HOUSE
//                                , BuildingType.MAIN_HOUSE.getMaxHp(), Game.getUsers().get(i).getGovernment()));
//                    }
//                }
//                dropBuilding(whichMap + (i - 4) * whichMap + 4, whichMap * 4, "granary");
//                Building.addBuildings(Game.getGameMap()[whichMap + (i - 4) * whichMap][whichMap * 4].getBuilding());
//            }
//            Game.getUsers().get(i).getGovernment().setGold(4000);
//            Game.getUsers().get(i).getGovernment().addToStorage(Resource.WOOD, 100);
//            Game.getUsers().get(i).getGovernment().addToStorage(Resource.STONE, 100);
//            Game.getUsers().get(i).getGovernment().addToStorage(Resource.BREAD, 100);
//            Game.getUsers().get(i).getGovernment().changeResourceAmount(Resource.WOOD, 100);
//            Game.getUsers().get(i).getGovernment().changeResourceAmount(Resource.STONE, 100);
//            Game.getUsers().get(i).getGovernment().changeResourceAmount(Resource.BREAD, 100);
//        }
//    }

    public static void setMap(int mapNumber) {
        if (mapNumber == 1) {
            Game.setGameMap(400, 400);
            Game.setX(400);
            Game.setY(400);
        } else if (mapNumber == 2) {
            Game.setGameMap(200, 200);
            Game.setX(200);
            Game.setY(200);
        } else {
            Map[][] gameMap = new Map[400][400];
            for (int i = 0; i < 400; i++) {
                for (int j = 0; j < 400; j++) {
                    gameMap[i][j] = new Map(i, j);
                }
            }
            for (int i = 0; i < 30; i++) {
                for (int j = 0; j < 30; j++) {
                    gameMap[i][j].setMapType(IRON);
                }
            }
//            for (int i = 30; i < 55; i++) {
//                for (int j = 0; j < 25; j++) {
//                    gameMap[i][j].setMapType(PLAIN);
//                }
//            }
            for (int i = 50; i < 100; i++) {
                for (int j = 0; j < 50; j++) {
                    gameMap[i][j].setMapType(THICK_SCRUB);
                }
            }
            for (int i = 30; i < 60; i++) {
                for (int j = 30; j < 70; j++) {
                    gameMap[i][j].setMapType(BOULDERS);
                }
            }
            for (int i = 0; i < 100; i++) {
                for (int j = 60; j < 100; j++) {
                    gameMap[i][j].setMapType(SEA);
                }
            }
            for (int i = 50; i < 100; i++) {
                for (int j = 120; j < 150; j++) {
                    gameMap[i][j].setMapType(SMALL_POND);
//                    gameMap[i][j].setTree(OLIVE_TREE);
                }
            }
            for (int i = 200; i < 250; i++) {
                for (int j = 0; j < 50; j++) {
                    gameMap[i][j].setMapType(EARTH_AND_STONE);
                    gameMap[i][j].setTree(COCONUT_PALM);
                }
            }
            for (int i = 150; i < 200; i++) {
                for (int j = 120; j < 160; j++) {
                    gameMap[i][j].setMapType(IRON);
                }
            }
            for (int i = 0; i < 50; i++) {
                for (int j = 200; j < 280; j++) {
                    gameMap[i][j].setMapType(OASIS_GRASS);
                }
            }
            for (int i = 220; i < 260; i++) {
                for (int j = 150; j < 160; j++) {
                    gameMap[i][j].setMapType(RIVER);
                }
            }
            for (int i = 200; i < 260; i++) {
                for (int j = 250; j < 300; j++) {
                    gameMap[i][j].setMapType(OASIS_GRASS);
                }
                for (int j = 250; j < 270; j++) {
                    gameMap[i][j].setTree(CHERRY_PALM);
                }
            }
            for (int i = 280; i < 320; i++) {
                for (int j = 0; j < 70; j++) {
                    gameMap[i][j].setMapType(OIL);
                }
            }
            for (int i = 60; i < 100; i++) {
                for (int j = 150; j < 200; j++) {
                    gameMap[i][j].setMapType(IRON);
                }
            }

            Game.setX(400);
            Game.setY(400);
            Game.setGameMap(gameMap);
        }
    }

    public static String showMap(int xCoordinate, int yCoordinate) {
//        int numberOfHousesToShow = 20, xFirstHome = xCoordinate - numberOfHousesToShow,
//                xEndHome = xCoordinate + numberOfHousesToShow,
//                yFirstHome = yCoordinate - numberOfHousesToShow,
//                yEndHome = yCoordinate + numberOfHousesToShow;
//        if (xCoordinate < numberOfHousesToShow) xFirstHome = 0;
//        else if (xCoordinate > Game.getX() - numberOfHousesToShow) xEndHome = Game.getX();
//        if (yCoordinate < numberOfHousesToShow) yFirstHome = 0;
//        else if (yCoordinate > Game.getY() - numberOfHousesToShow) yEndHome = Game.getY();
////        System.out.println("xs: " + xFirstHome + " xe: " + xEndHome + "ys: " + yFirstHome + " ye: " + yEndHome);
//        Map[][] gameMap = Game.getGameMap();
//        StringBuilder map = new StringBuilder();
//        for (int i = xFirstHome; i < xEndHome; i++) {
//            map.append(String.join("", Collections.nCopies((xEndHome - xFirstHome) * 3 + 1, "-")) + "\n");
//            for (int i1 = yFirstHome; i1 < yEndHome; i1++) {
////                System.out.println(gameMap[i][i1].getMapType());
//                String[] color = backgroundColor(gameMap[i][i1].getMapType());
//                map.append("|" + color[0] + color[1] + ((gameMap[i][i1].getBuilding() != null) ? ((gameMap[i][i1].getBuilding() instanceof DefenseBuilding) ? "W" : "B") : "#")
//                        + ((gameMap[i][i1].getTree() != null) ? "T" : "#") + ANSI_RESET);
//            }
//            map.append("|\n");
//            for (int j = yFirstHome; j < yEndHome; j++) {
//                String[] color = backgroundColor(gameMap[i][j].getMapType());
//                int numberOfSoldiers = gameMap[i][j].getUnit().size();
//                if (gameMap[i][j].getUnit().size() > 99) numberOfSoldiers = 99;
//                map.append("|" + color[0] + color[1] + ((!gameMap[i][j].getUnit().isEmpty()) ? ((numberOfSoldiers > 9) ? numberOfSoldiers : numberOfSoldiers + "#") : "##") + ANSI_RESET);
//            }
//            map.append("|\n");
//        }
//        map.append("**earth has yellow background\t**earth and stone has yellow background and white text\t**boulder has yellow background and black text\n" +
//                "**Rock has yellow background and red text\t**Iron has yellow background and purple text\t**oasis grass has green background\n" +
//                "**scrub has green background and light blue text\t**thick scrub has green background and blue text\n");
//        map.append("**oil has black background\t**plain has green background and black text\t**shallow water has blue background\n" +
//                "**river has light blue background\t**small pond has blue background and white text\t**big pond has blue background and black text\n" +
//                "**beach has yellow background and blue text\t**sea has blue background and rex text\n");
//        map.append("first character of each house shows if there are buildings there or no if there is B that means there are a building " +
//                "and if its a W that means we have wall there\nsecond characters in each house shows the number of soldiers in that house");
//        return map.toString();
        return "";
    }

    private static Color[] backgroundColor(MapType mapType) {
        Color[] colors = new Color[2];
        colors[1] = null;
        switch (mapType) {
            case EARTH:
                colors[0] = Color.YELLOW;
                break;
            case EARTH_AND_STONE:
                colors[0] = Color.YELLOW;
                colors[1] = Color.WHITE;
                break;
            case BOULDERS:
                colors[0] = Color.YELLOW;
                colors[1] = Color.BLACK;
                break;
            case ROCK_N, ROCK_E, ROCK_S, ROCK_W:
                colors[0] = Color.YELLOW;
                colors[1] = Color.RED;
                break;
            case IRON:
                colors[0] = Color.YELLOW;
                colors[1] = Color.PURPLE;
                break;
            case OASIS_GRASS:
                colors[0] = Color.GREEN;
                break;
            case SCRUB:
                colors[0] = Color.GREEN;
                colors[1] = Color.LIGHTBLUE;
                break;
            case THICK_SCRUB:
                colors[0] = Color.GREEN;
                colors[1] = Color.BLUE;
                break;
            default:
                return setBackgroundColorWater(mapType);
        }
        return colors;
    }

    private static Color[] setBackgroundColorWater(MapType mapType) {
        Color[] colors = new Color[2];
        colors[1] = null;
        switch (mapType) {
            case OIL:
                colors[0] = Color.BLACK;
                break;
//            case PLAIN:
//                colors[0] = Color.GREEN;
//                colors[1] = Color.BLACK;
//                break;
            case SHALLOW_WATER:
                colors[0] = Color.BLUE;
                break;
            case RIVER:
                colors[0] = Color.LIGHTBLUE;
                break;
            case SMALL_POND:
                colors[0] = Color.BLUE;
                colors[1] = Color.WHITE;
                break;
            case BIG_POUND:
                colors[0] = Color.BLUE;
                colors[1] = Color.BLACK;
                break;
            case BEACH:
                colors[0] = Color.YELLOW;
                colors[1] = Color.BLUE;
                break;
            case SEA:
                colors[0] = Color.BLUE;
                colors[1] = Color.RED;
                break;
        }
        return colors;
    }

    public static String showMapDetails(int xCoordinate, int yCoordinate) {
        Map house = Game.getGameMap()[xCoordinate][yCoordinate];
        StringBuilder details = new StringBuilder();
        int i = 1;
        details.append("Map type is :" + house.getMapType().toString() + "\nUnits in this house are : \n");
        details.append(((house.getBuilding() != null) ? "we have a " + house.getBuilding().getBuildingType().getName() +
                " building " + ((house.getBuilding().getBuildingType().getWorkers() != 0) ? "which has " +
                house.getBuilding().getBuildingType().getWorkers() + " workers" : "") : "there is no building here") + "\n");
        details.append(((house.getTree() != null) ? "we have tree with type " + house.getTree().toString() : "no tree in this house") + "\n");
        for (Unit unit : house.getUnit()) {
            if (unit instanceof Engineer)
                details.append(i + ") engineer owner : " + unit.getOwner().getUser().getUsername());
            else details.append(i + ") troop name : " + unit.getUnitType().getType() + " - troop hp : " + unit.getHp() +
                    "- unit " + ((unit.isPatrol()) ? "is" : "is not") + " patrol\n\t- troop state : " + unit.getState() +
                    " - unit owner is : " + unit.getGovernment().getUser().getUsername() + "\n");
            i++;
        }
        return details.toString().trim();
    }

    public static MapMenuMessage setTextureFinalTest(int xCoordinate, int yCoordinate) {
        if (Game.getGameMap()[xCoordinate][yCoordinate].getBuilding() != null)
            return MapMenuMessage.HOUSE_IS_FILED_WITH_BUILDING;
        return MapMenuMessage.SUCCESS;
    }

    public static void setTexture(int xCoordinate, int yCoordinate, MapType mapType) {
        Game.getGameMap()[xCoordinate][yCoordinate].setMapType(mapType);
        Game.getGameMap()[xCoordinate][yCoordinate].setBuilding(null);
        Game.getGameMap()[xCoordinate][yCoordinate].setTree(null);
    }

    public static MapMenuMessage clearBlock(int xCoordinate, int yCoordinate, boolean change) {
        if (Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getBuildingType().equals(BuildingType.MAIN_HOUSE))
            return MapMenuMessage.MAIN_HOUSE;
        if (change) {
            Building building = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding();
            int x1 = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getX1Position();
            int y1 = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getY1Position();
            int x2 = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getX2Position();
            int y2 = Game.getGameMap()[xCoordinate][yCoordinate].getBuilding().getY2Position();
            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    Game.getGameMap()[i][j].setBuilding(null);
                }
            }
            if (building instanceof StorageBuilding) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    storageBuilding.getOwner().getAllResources().put(resource, storageBuilding.getOwner().getAllResources().get(resource) - storageBuilding.getStorage().get(resource));
                }
                storageBuilding.getOwner().getBuildings().remove(building);
            }
            for (Unit unit : Game.getGameMap()[xCoordinate][yCoordinate].getUnit()) {
                Unit.getUnits().remove(unit);
            }
            Game.getGameMap()[xCoordinate][yCoordinate].setUnit();
            Building.getBuildings().remove(building);
        }

        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage dropRockFinalTest(int xCoordinate, int yCoordinate) {
        if (Game.getGameMap()[xCoordinate][yCoordinate].getBuilding() != null)
            return MapMenuMessage.HOUSE_IS_FILED_WITH_BUILDING;
        return MapMenuMessage.SUCCESS;
    }

    public static void dropRock(int x, int y, Direction direction) {
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
                        dropRock(x, y, N);
                        break;
                    case 1:
                        dropRock(x, y, S);
                        break;
                    case 2:
                        dropRock(x, y, E);
                        break;
                    case 3:
                        dropRock(x, y, W);
                        break;
                }
        }
    }

    public static MapMenuMessage dropTreeFinalTest(int xCoordinate, int yCoordinate) {
        if (Game.getGameMap()[xCoordinate][yCoordinate].getBuilding() != null)
            return MapMenuMessage.HOUSE_IS_FILED_WITH_BUILDING;
        return MapMenuMessage.SUCCESS;
    }

    public static void dropTree(int xCoordinate, int yCoordinate, MapType tree) {
        Game.getGameMap()[xCoordinate][yCoordinate].setTree(tree);
    }

    public static MapMenuMessage dropBuilding(int x, int y, String type, Government government) {
        BuildingType buildingType = null;
        for (BuildingType allBuildingType : BuildingType.values()) {
            if (allBuildingType.getName().equals(type)) {
                buildingType = allBuildingType;
            }
        }
        if (buildingType == null) return MapMenuMessage.NOT_VALID_TYPE_FOR_DROP_BUILDING;
        int length = buildingType.getLength();
        int width = buildingType.getWidth();
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + width; j++) {
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
                if (map.getBuilding() != null && (!type.equals("mangonel") && !type.equals("ballistae")) &&
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
//                boolean sign = false;
//                if (type.equals("granary")) {
//                    if (!Game.getGameMap()[x - 1][y].getBuilding().getBuildingType().equals(BuildingType.MAIN_HOUSE)) {
//                        outer2:
//                        for (int k = x; k < x + buildingType.getLength(); k++) {
//                            for (int l = y; l < buildingType.getWidth(); l++) {
//                                if (Game.getGameMap()[k + 1][l].getBuilding().getBuildingType().equals(BuildingType.GRANARY))
//                                    sign = true;
//                                else if (Game.getGameMap()[k - 1][l].getBuilding().getBuildingType().equals(BuildingType.GRANARY))
//                                    sign = true;
//                                else if (Game.getGameMap()[k][l + 1].getBuilding().getBuildingType().equals(BuildingType.GRANARY))
//                                    sign = true;
//                                else if (Game.getGameMap()[k][l - 1].getBuilding().getBuildingType().equals(BuildingType.GRANARY))
//                                    sign = true;
//                                if (sign) break outer2;
//                            }
//                        }
//                        if (!sign) return MapMenuMessage.PUT_STORAGE_NEXT_TO_EACH_OTHER;
//                    }
//                }
            }
        }
        return checkCost(x, y, buildingType, length, width, government);
    }

    private static MapMenuMessage checkCost(int x, int y, BuildingType buildingType, int length, int width, Government government) {
        if (buildingType.getCostType() != null) {
            if (Game.getCurrentUser().getGovernment().getAllResources().get(buildingType.getCostType()) < buildingType.getCostAmount())
                return MapMenuMessage.NOT_ENOUGH_RESOURCE;
        }
        if (buildingType.getCost() > Game.getCurrentUser().getGovernment().getGold())
            return MapMenuMessage.NOT_ENOUGH_MONEY;
        if (Game.getCurrentUser().getGovernment().getUnemployedWorker() < buildingType.getWorkers())
            return MapMenuMessage.NOT_ENOUGH_WORKERS;
        Engineer engineer = null;
        for (Unit unit : Unit.getUnits()) {
            if (unit instanceof Engineer &&
                    unit.getOwner().equals(Game.getCurrentUser()) &&
                    !((Engineer) unit).isWorking()) engineer = ((Engineer) unit);
        }
        if (buildingType.isNeedEngineer() && engineer == null) return MapMenuMessage.NOT_ENOUGH_ENGINEER;
        return findClassDropBuilding(x, y, buildingType, length, width, government);
    }

    private static MapMenuMessage findClassDropBuilding(int x, int y, BuildingType buildingType, int length, int width, Government government) {
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
        DefenseBuilding defenseBuilding = null;
        ProducerBuilding producerBuilding = null;
        SiegeBuilding siegeBuilding = null;
        StorageBuilding storageBuilding = null;
        TrapBuilding trapBuilding = null;
        ShopBuilding shopBuilding = null;
        Building building = null;
        if (defenseType != null){
            defenseBuilding = new DefenseBuilding(defenseType, x, x+length, y, y+width, government);
            government.addBuilding(defenseBuilding);
        }
        else if (producerType != null){
            producerBuilding = new ProducerBuilding(producerType, x, x+length, y, y+width, government);
            government.addBuilding(producerBuilding);
        }
        else if (siegeType != null){
            siegeBuilding = new SiegeBuilding(siegeType, x, x+length, y, y+width, government);
            government.addBuilding(siegeBuilding);
        }
        else if (storageType != null){
            storageBuilding = new StorageBuilding(storageType, x, x+length, y, y+width, government);
            government.addBuilding(storageBuilding);
        }
        else if (trapType != null){
            trapBuilding = new TrapBuilding(trapType, x, x+length, y, y+width, government);
            government.addBuilding(trapBuilding);
        }
        else{
            if(buildingGroup.equals(BuildingGroup.SHOP_BUILDING)){
                shopBuilding = new ShopBuilding( x, x+length, y, y+width, government);
                government.addBuilding(shopBuilding);
            }else{
                building = new Building(buildingType, buildingType.getMaxHp(), government, x, x+length, y, y+width);
                government.addBuilding(building);
            }
        }
        for (int i = x; i < x + length; i++) {
            for (int j = y; j < y + width; j++) {
                if (defenseType != null){
//                    System.out.println("defense");
                    Game.getGameMap()[i][j].setBuilding(defenseBuilding);
                    Game.getCurrentUser().getGovernment().getBuildings().add(defenseBuilding);
                }
                else if (producerType != null){
//                    System.out.println("producer");
                    Game.getGameMap()[i][j].setBuilding(producerBuilding);
                    Game.getCurrentUser().getGovernment().getBuildings().add(producerBuilding);
                }
                else if (siegeType != null){
//                    System.out.println("siege");
                    Game.getGameMap()[i][j].setBuilding(siegeBuilding);
                    Game.getCurrentUser().getGovernment().getBuildings().add(siegeBuilding);
                }
                else if (storageType != null){
//                    System.out.println("storage");
                    Game.getGameMap()[i][j].setBuilding(storageBuilding);
                    Game.getCurrentUser().getGovernment().getBuildings().add(storageBuilding);
                }
                else if (trapType != null){
//                    System.out.println("trap");
                    Game.getGameMap()[i][j].setBuilding(trapBuilding);
                    Game.getCurrentUser().getGovernment().getBuildings().add(trapBuilding);
                }
                else{
                    if(buildingGroup.equals(BuildingGroup.SHOP_BUILDING)){
                        Game.getGameMap()[i][j].setBuilding(shopBuilding);
                        Game.getCurrentUser().getGovernment().getBuildings().add(shopBuilding);
                    }else{
                        Game.getGameMap()[i][j].setBuilding(building);
                        Game.getCurrentUser().getGovernment().getBuildings().add(building);
                    }
                }
            }
        }
        Game.getCurrentUser().getGovernment().setUnemployedWorker(buildingType.getWorkers());
        if (buildingType.isNeedEngineer()) {
            for (Unit unit : Unit.getUnits()) {
                if (unit instanceof Engineer &&
                        unit.getOwner().equals(Game.getCurrentUser()) &&
                        !((Engineer) unit).isWorking()) {
                    ((Engineer) unit).setWorking(true);
                    ((Engineer) unit).setBuildingWhichWorks(Game.getGameMap()[x][y].getBuilding());
                }
            }
        }
        int finalGold = Game.getCurrentUser().getGovernment().getGold() - buildingType.getCost();
        Game.getCurrentUser().getGovernment().setGold(finalGold);
        if (buildingType.getCostAmount() != 0) {
            Game.getCurrentUser().getGovernment().changeResourceAmount(buildingType.getCostType(), -buildingType.getCostAmount());
            Game.getCurrentUser().getGovernment().removeFromStorage(buildingType.getCostType(), -buildingType.getCostAmount());
        }
        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage selectBuilding(int x, int y) {
        if (Game.getGameMap()[x][y].getBuilding() == null) return MapMenuMessage.HOUSE_IS_EMPTY;
        if (!Game.getGameMap()[x][y].getBuilding().getOwner().equals(Game.getCurrentUser().getGovernment()))
            return MapMenuMessage.BUILDING_OR_SOLDIER_DOESNT_BELONG_TO_YOU;
        else if(Game.getGameMap()[x][y].getBuilding().getBuildingType().equals(BuildingType.MARKET))
            return MapMenuMessage.SHOP_MENU_BUILDING;
        return MapMenuMessage.SUCCESS;
    }

    public static MapMenuMessage selectUnit(int x, int y) {
//        boolean sign = true;
        int numberOfSoldiers = 0;
        for (Unit unit : Game.getGameMap()[x][y].getUnit()) {
            if (unit.getGovernment().equals(Game.getCurrentUser().getGovernment())) {
                numberOfSoldiers++;
//                sign = false;
//                break;
            }
        }
        if (numberOfSoldiers == 0) return MapMenuMessage.HOUSE_IS_EMPTY;
//        if (sign) return MapMenuMessage.BUILDING_OR_SOLDIER_DOESNT_BELONG_TO_YOU;
        return MapMenuMessage.SUCCESS;
    }

    public static void setDefaultBuilding(int count) {
        Game.setMainHouses();
        dropBuilding(20, 130, "main house", Game.getCurrentUser().getGovernment());
        Game.addMainHouses(Game.getGameMap()[20][130].getBuilding());
        dropBuilding(20, 137, "stock pile", Game.getCurrentUser().getGovernment());
        dropBuilding(30, 130, "granary", Game.getCurrentUser().getGovernment());
        Game.getUsers().get(0).getGovernment().addToStorage(Resource.WOOD, 100);
        Game.getUsers().get(0).getGovernment().addToStorage(Resource.STONE, 50);
        Game.getUsers().get(0).getGovernment().addToStorage(Resource.BREAD, 100);

        dropBuilding(120, 15, "main house", Game.getUsers().get(1).getGovernment());
        Game.addMainHouses(Game.getGameMap()[120][15].getBuilding());
        dropBuilding(120, 22, "stock pile", Game.getUsers().get(1).getGovernment());
        dropBuilding(130, 15, "granary", Game.getUsers().get(1).getGovernment());
        Game.getUsers().get(1).getGovernment().addToStorage(Resource.WOOD, 100);
        Game.getUsers().get(1).getGovernment().addToStorage(Resource.STONE, 50);
        Game.getUsers().get(1).getGovernment().addToStorage(Resource.BREAD, 100);
        switch (count){
            case 8:
                dropBuilding(300, 250, "main house", Game.getUsers().get(7).getGovernment());
                Game.addMainHouses(Game.getGameMap()[300][250].getBuilding());
                dropBuilding(300, 257, "stock pile", Game.getUsers().get(7).getGovernment());
                dropBuilding(310, 250, "granary", Game.getUsers().get(7).getGovernment());
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.WOOD, 100);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.STONE, 50);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.BREAD, 100);
            case 7:
                dropBuilding(300, 160, "main house", Game.getUsers().get(6).getGovernment());
                Game.addMainHouses(Game.getGameMap()[300][160].getBuilding());
                dropBuilding(300, 167, "stock pile", Game.getUsers().get(6).getGovernment());
                dropBuilding(310, 160, "granary", Game.getUsers().get(6).getGovernment());
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.WOOD, 100);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.STONE, 50);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.BREAD, 100);
            case 6:
                dropBuilding(300, 90, "main house", Game.getUsers().get(5).getGovernment());
                Game.addMainHouses(Game.getGameMap()[300][90].getBuilding());
                dropBuilding(300, 97, "stock pile", Game.getUsers().get(5).getGovernment());
                dropBuilding(310, 90, "granary", Game.getUsers().get(5).getGovernment());
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.WOOD, 100);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.STONE, 50);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.BREAD, 100);
            case 5:
                dropBuilding(220, 180, "main house", Game.getUsers().get(4).getGovernment());
                Game.addMainHouses(Game.getGameMap()[220][180].getBuilding());
                dropBuilding(220, 187, "stock pile", Game.getUsers().get(4).getGovernment());
                dropBuilding(230, 180, "granary", Game.getUsers().get(4).getGovernment());
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.WOOD, 100);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.STONE, 50);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.BREAD, 100);
            case 4:
                dropBuilding(220, 90, "main house", Game.getUsers().get(3).getGovernment());
                Game.addMainHouses(Game.getGameMap()[220][90].getBuilding());
                dropBuilding(220, 97, "stock pile", Game.getUsers().get(3).getGovernment());
                dropBuilding(230, 90, "granary", Game.getUsers().get(3).getGovernment());
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.WOOD, 100);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.STONE, 50);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.BREAD, 100);
            case 3:
                dropBuilding(150, 250, "main house", Game.getUsers().get(2).getGovernment());
                Game.addMainHouses(Game.getGameMap()[150][250].getBuilding());
                dropBuilding(150, 257, "stock pile", Game.getUsers().get(2).getGovernment());
                dropBuilding(160, 250, "granary", Game.getUsers().get(2).getGovernment());
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.WOOD, 100);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.STONE, 50);
                Game.getUsers().get(count - 1).getGovernment().addToStorage(Resource.BREAD, 100);
        }
    }
}
