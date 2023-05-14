package view;

import controller.Controller;
import controller.GameMenuController;
import controller.MapMenuController;
import model.Direction;
import model.Game;
import model.MapType;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.GameMenuMessage;
import view.enums.messages.MapMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameMenu {
    private static int xOfMap;
    private static int yOfMap;
    private static MapType typeForTreeAndTexture = MapType.DEFAULT;
    private static Direction direction = Direction.F;

    public static int getXOfMap() {
        return xOfMap;
    }

    public static int getYOfMap() {
        return yOfMap;
    }

    public static void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        Matcher matcher;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.EXIT, command) != null)
                return;
            else if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("login menu");
            else if ((options = CommandHandler.parsCommand(Command.SHOW_POPULARITY_FACTORS, command)) != null)
                popularityFactorsShow(options);
            else if ((options = CommandHandler.parsCommand(Command.SHOW_POPULARITY, command)) != null)
                popularityShow(options);
            else if ((options = CommandHandler.parsCommand(Command.SHOW_FOOD_LIST, command)) != null)
                foodListShow(options);
            else if ((options = CommandHandler.parsCommand(Command.FOOD_RATE_SHOW, command)) != null)
                foodRateShow(options);
            else if ((options = CommandHandler.parsCommand(Command.TAX_RATE_SHOW, command)) != null)
                taxRateShow(options);
            else if ((options = CommandHandler.parsCommand(Command.FEAR_RATE, command)) != null)
                fearRateSet(options);
            else if ((options = CommandHandler.parsCommand(Command.SHOW_MAP, command)) != null)
                setXYOfMapCommonErrors(options, "show map");
            else if ((options = CommandHandler.parsCommand(Command.MOVE_MAP, command)) != null)
                showMapMove(options);
            else if ((options = CommandHandler.parsCommand(Command.SHOW_DETAILS, command)) != null)
                setXYOfMapCommonErrors(options, "details");
//            else if ((matcher = MainMenu.getMatcher(command, Regexes.MAP_DETAILS_MOVE.getRegex())) != null)
//                showMapDetailsMove(matcher);
            else if ((options = CommandHandler.parsCommand(Command.SET_TEXTURE_FOR_ONE_HOUSE, command)) != null)
                setXYOfMapCommonErrors(options, "texture");
            else if ((options = CommandHandler.parsCommand(Command.SET_TEXTURE_FOR_RECTANGLE, command)) != null)
                checkRectangleIsValid(options, "texture");
            else if ((options = CommandHandler.parsCommand(Command.CLEAR, command)) != null)
                setXYOfMapCommonErrors(options, "clear block");
            else if ((options = CommandHandler.parsCommand(Command.CLEAR_FOR_RECTANGLE, command)) != null)
                checkRectangleIsValid(options, "clear block");
            else if ((options = CommandHandler.parsCommand(Command.DROP_ROCK, command)) != null)
                setXYOfMapCommonErrors(options, "drop rock");
            else if ((options = CommandHandler.parsCommand(Command.DROP_ROCK_FOR_RECTANGLE, command)) != null)
                checkRectangleIsValid(options, "drop rock");
            else if ((options = CommandHandler.parsCommand(Command.DROP_TREE, command)) != null)
                setXYOfMapCommonErrors(options, "drop tree");
            else if ((options = CommandHandler.parsCommand(Command.DROP_TREE_FOR_RECTANGLE, command)) != null)
                checkRectangleIsValid(options, "drop tree");
            else if ((options = CommandHandler.parsCommand(Command.DROP_BUILDING, command)) != null)
                setXYOfMapCommonErrors(options, "drop building");
            else if ((options = CommandHandler.parsCommand(Command.SELECT_UNIT, command)) != null)
                setXYOfMapCommonErrors(options, "select unit");
            else if (CommandHandler.parsCommand(Command.NEXT_TURN, command) != null)
                nextTurn();
            else if ((options = CommandHandler.parsCommand(Command.SELECT_BUILDING, command)) != null)
                setXYOfMapCommonErrors(options, "select building");
            else System.out.println("invalid command in game menu");
        }
    }

    private static void popularityFactorsShow(HashMap<String, ArrayList<String>> options) {
        String s = "";
        int foodPop = GameMenuController.getPopularityFromFood();
        int taxPop = GameMenuController.getPopularityFromTax();
        int fearPop = GameMenuController.getPopularityFromFear();
        int religionPop = GameMenuController.getPopularityFromReligion();
        int popularity = GameMenuController.getPopularity();

        s += "Food: " + foodPop;
        s += "\ntax: " + taxPop;
        s += "\nFear: " + fearPop;
        s += "\nReligion: " + religionPop;
        s += "\nPopularity: " + popularity;
        System.out.println(s);
    }

    private static void popularityShow(HashMap<String, ArrayList<String>> options) {
        System.out.println(GameMenuController.getPopularity());
    }

    private static void foodListShow(HashMap<String, ArrayList<String>> options) {
        String s = "";
        double appleCount = GameMenuController.getAppleCount();
        double meetCount = GameMenuController.getMeetCount();
        double cheeseCount = GameMenuController.getCheeseCount();
        double breadCount = GameMenuController.getBreadCount();

        s += "Apple: " + appleCount;
        s += "\nMeet: " + meetCount;
        s += "\nCheese: " + cheeseCount;
        s += "\nBread: " + breadCount;

        System.out.println(s);
    }

    private static void foodRateShow(HashMap<String, ArrayList<String>> options) {
        System.out.println(GameMenuController.foodRateShow());
    }

    private static void taxRateShow(HashMap<String, ArrayList<String>> options) {
        System.out.println(GameMenuController.taxRateShow());
    }

    private static void fearRateSet(HashMap<String, ArrayList<String>> options) {
        int fearRate = 0;
        try {
            fearRate = Integer.parseInt(options.get("r").get(0));
        } catch (NumberFormatException e) {
            System.out.println("invalid rate entered");
            return;
        }
        if (fearRate < -5 || fearRate > 5) {
            System.out.println("fear rate must be lower than 5 and grater than -5");
            return;
        }
        GameMenuMessage result = GameMenuController.fearRateSet(fearRate);
        if (result.equals(GameMenuMessage.SUCCESS))
            System.out.println("fear rate changed successfully");
    }

    private static void setXYOfMapCommonErrors(HashMap<String, ArrayList<String>> options, String whichFunction) {
        int x = 0, y = 0;
        for (String s : options.keySet()) {
            switch (s) {
                case "x":
                    try {
                        x = Integer.parseInt(Controller.buildParameter(options.get(s).get(0)));
                    } catch (NumberFormatException e) {
                        System.out.println("format is wrong for x you have to enter a number");
                        return;
                    }
                    break;
                case "y":
                    try {
                        y = Integer.parseInt(Controller.buildParameter(options.get(s).get(0)));
                    } catch (NumberFormatException e) {
                        System.out.println("format is wrong for y you have to enter a number");
                        return;
                    }
                    break;
            }
        }
        setXYOfMap(x, y, whichFunction, options);
    }

    private static boolean checkXY(int x, int y) {
        if (x < 0 || x >= Game.getX()) {
            System.out.println("you have entered wrong number for x it has to be bigger than 0 and less than game x length");
            return false;
        } else if (y < 0 || y >= Game.getY()) {
            System.out.println("you have entered wrong number for y it has to be bigger than 0 and less than game y length");
            return false;
        }
        return true;
    }

    private static void setXYOfMap(int x, int y, String whichFunction, HashMap<String, ArrayList<String>> options) {
        if (!checkXY(x, y)) return;
        switch (whichFunction) {
            case "show map":
                xOfMap = x;
                yOfMap = y;
                showMap(x, y);
                break;
            case "details":
                xOfMap = x;
                yOfMap = y;
                showMapDetails(x, y);
                break;
            case "texture":
                if (setTextureForOneHouse(x, y, options, true))
                    System.out.println("successfully changed the texture in house -x " + x + " -y " + y);
                break;
            case "clear block":
                if (clearOneBlock(x, y, true))
                    System.out.println("successfully cleared the block");
                break;
            case "drop rock":
                if (dropOneRock(x, y, options, true))
                    System.out.println("successfully dropped a rock in house -x " + x + " -y " + y);
                break;
            case "drop tree":
                if (dropOneTree(x, y, options, true))
                    System.out.println("successfully dropped a tree in house -x " + x + " -y " + y);
                break;
            case "drop building":
                dropBuilding(x, y, options);
                break;
            case "select building":
                selectBuilding(x, y);
                break;
            case "select unit":
                selectUnit(x, y);
                break;
        }
    }

    private static void showMap(int x, int y) {
        System.out.println(MapMenuController.showMap(x, y));
    }

    private static void showMapDetails(int x, int y) {
        System.out.println(MapMenuController.showMapDetails(x, y));
    }

    private static void moveMap(int up, int down, int left, int right) {
        yOfMap += right - left;
        xOfMap += down - up;
    }

    private static void showMapMove(HashMap<String, ArrayList<String>> options) {
        int up = 0;
        int down = 0;
        int left = 0;
        int right = 0;
        for (String s : options.keySet()) {
            switch (s) {
                case "u":
                    up = Integer.parseInt(options.get(s).get(0));
                    break;
                case "d":
                    down = Integer.parseInt(options.get(s).get(0));
                    break;
                case "l":
                    left = Integer.parseInt(options.get(s).get(0));
                    break;
                case "r":
                    right = Integer.parseInt(options.get(s).get(0));
                    break;
            }
        }

        moveMap(up, down, left, right);
        if (!checkXY(xOfMap, yOfMap)) return;
        System.out.println("x: " + xOfMap + " y: " + yOfMap);
        showMap(xOfMap, yOfMap);
    }

    private static void showMapDetailsMove(Matcher matcher) {
//        moveMap(1, 1, 1, 1);
        if (!checkXY(xOfMap, yOfMap)) return;
        showMapDetails(xOfMap, yOfMap);
    }

    private static String setTypeForTreeAndTextureAndBuilding(HashMap<String, ArrayList<String>> options) {
        String type = "";
        for (String s : options.keySet()) {
            if (s.equals("t")) {
                type = Controller.buildParameter(options.get(s).get(0));
                break;
            }
        }
        return type;
    }

    private static MapType typeChecker(HashMap<String, ArrayList<String>> options) {
        String type = setTypeForTreeAndTextureAndBuilding(options);
        boolean sign = true;
        MapType mapToSend = null;
        for (MapType allMapType : MapType.values()) {
            if (allMapType.toString().equals(type.toUpperCase())) {
                sign = false;
                if (!allMapType.isInTextureCommand()) {
                    System.out.println("you have to enter something except than trees and rock");
                    return null;
                }
                mapToSend = allMapType;
                break;
            }
        }
        if (sign) {
            System.out.println("wrong type were entered");
            return null;
        }
        return mapToSend;
    }

    private static boolean setTextureForOneHouse(int x, int y, HashMap<String, ArrayList<String>> options, boolean change) {
        if (typeForTreeAndTexture.equals(MapType.DEFAULT)) typeForTreeAndTexture = typeChecker(options);
        MapMenuMessage mapMenuMessage;
        if (typeForTreeAndTexture != null) {
            mapMenuMessage = MapMenuController.setTextureFinalTest(x, y);
            if (!mapMenuMessage.equals(MapMenuMessage.SUCCESS)) {
                System.out.println("a building exists int house -x " + x + " -y " + y + " you can not do this action");
                return false;
            }
            if (change) {
                MapMenuController.setTexture(x, y, typeForTreeAndTexture);
                typeForTreeAndTexture = MapType.DEFAULT;
            }
            return true;

        } else return false;
    }

    private static boolean clearOneBlock(int x, int y, boolean change) {
        if (MapMenuController.clearBlock(x, y, change).equals(MapMenuMessage.MAIN_HOUSE)) {
            System.out.println("you can not clear the house which main house is placed on");
            return false;
        }
        return true;
    }

    private static Direction directionChecker(HashMap<String, ArrayList<String>> options) {
        String direction = "";
        for (String s : options.keySet()) {
            if (s.equals("d")) {
                direction = Controller.buildParameter(options.get(s).get(0));
                break;
            }
        }
        boolean sign = true;
        Direction directionToSend = null;
        for (Direction allDirections : Direction.values()) {
            if (allDirections.toString().equals(direction.toUpperCase()) &&
                    allDirections.isForRock()) {
                sign = false;
                directionToSend = allDirections;
                break;
            }
        }
        if (sign) {
            System.out.println("wrong direction were entered");
            return null;
        }
        return directionToSend;
    }

    private static boolean dropOneRock(int x, int y, HashMap<String, ArrayList<String>> options, boolean change) {
        if (direction.equals(Direction.F)) direction = directionChecker(options);
        if (direction != null) {
            MapMenuMessage message = MapMenuController.dropRockFinalTest(x, y);
            if (!message.equals(MapMenuMessage.SUCCESS)) {
                System.out.println("a building exists int house -x " + x + " -y " + y + " you can not do this action");
                return false;
            }
            if (change) {
                MapMenuController.dropRock(x, y, direction);
                direction = Direction.F;
            }
            return true;
        }
        return false;
    }

    private static MapType treeChecker(HashMap<String, ArrayList<String>> options) {
        String treeType = setTypeForTreeAndTextureAndBuilding(options);
        boolean sign = true;
        MapType treeToSend = null;
        for (MapType allMapType : MapType.values()) {
            if (allMapType.toString().equals(treeType.toUpperCase())) {
                sign = false;
                if (!allMapType.isTree()) {
                    System.out.println("you have to enter something except than those in playground types of game");
                    return null;
                }
                treeToSend = allMapType;
                break;
            }
        }
        if (sign) {
            System.out.println("wrong type were entered for tree type");
            return null;
        }
        return treeToSend;
    }

    private static boolean dropOneTree(int x, int y, HashMap<String, ArrayList<String>> options, boolean change) {
        if (typeForTreeAndTexture.equals(MapType.DEFAULT)) typeForTreeAndTexture = treeChecker(options);
        if (typeForTreeAndTexture != null) {
            MapMenuMessage message = MapMenuController.dropTreeFinalTest(x, y);
            if (!message.equals(MapMenuMessage.SUCCESS)) {
                System.out.println("a building exists in house -x " + x + " -y " + y + " you can not do this action");
                return false;
            }
            if (change) {
                MapMenuController.dropTree(x, y, typeForTreeAndTexture);
                typeForTreeAndTexture = MapType.DEFAULT;
            }
            return true;
        }
        return false;
    }

    private static void checkRectangleIsValid(HashMap<String, ArrayList<String>> options, String whichFunction) {
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        for (String s : options.keySet()) {
            switch (s) {
                case "x1":
                    try {
                        x1 = Integer.parseInt(Controller.buildParameter(options.get(s).get(0)));
                    } catch (NumberFormatException e) {
                        System.out.println("format is wrong for x1 you have to enter a number");
                        return;
                    }
                    break;
                case "y1":
                    try {
                        y1 = Integer.parseInt(Controller.buildParameter(options.get(s).get(0)));
                    } catch (NumberFormatException e) {
                        System.out.println("format is wrong for y1 you have to enter a number");
                        return;
                    }
                    break;
                case "x2":
                    try {
                        x2 = Integer.parseInt(Controller.buildParameter(options.get(s).get(0)));
                    } catch (NumberFormatException e) {
                        System.out.println("format is wrong for x2 you have to enter a number");
                        return;
                    }
                    break;
                case "y2":
                    try {
                        y2 = Integer.parseInt(Controller.buildParameter(options.get(s).get(0)));
                    } catch (NumberFormatException e) {
                        System.out.println("format is wrong for y2 you have to enter a number");
                        return;
                    }
                    break;
            }
        }
        checkXYForRectangle(x1, y1, x2, y2, options, whichFunction);
    }

    private static void checkXYForRectangle(int x1, int y1, int x2, int y2, HashMap<String, ArrayList<String>> options, String whichFunction) {
        if (x1 < 0 || x1 >= Game.getX() || x2 < 0 || x2 > Game.getX()) {
            System.out.println("you have entered wrong number for x1 or x2 it has to be bigger than 0 and less than game map x length");
            return;
        } else if (y1 < 0 || y1 >= Game.getY() || y2 < 0 || y2 > Game.getY()) {
            System.out.println("you have entered wrong number for y1 or y2 it has to be bigger than 0 and less than game map y length");
            return;
        } else if (x2 < x1) {
            System.out.println("x2 is less than x1 action failed");
            return;
        } else if (y2 < y1) {
            System.out.println("y2 is less than y1 action failed");
            return;
        }
        whichFunctionForRectangle(x1, y1, x2, y2, options, whichFunction);
    }

    private static void whichFunctionForRectangle(int x1, int y1, int x2, int y2, HashMap<String, ArrayList<String>> options, String whichFunction) {
        switch (whichFunction) {
            case "texture":
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        if (!setTextureForOneHouse(i, j, options, false)) return;
                    }
                }
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        setTextureForOneHouse(i, j, options, true);
                    }
                }
                System.out.println("successfully changed the texture from house x1 " + x1 + " y1 " + y1 + " to x2 " + x2 + " y2 " + y2);
                break;
            case "clear block":
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        if (!clearOneBlock(i, j, false)) return;
                    }
                }
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        clearOneBlock(i, j, true);
                    }
                }
                System.out.println("successfully cleared the block from house x1 " + x1 + " y1 " + y1 + " to x2 " + x2 + " y2 " + y2);
                break;
            case "drop rock":
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        if (!dropOneRock(i, j, options, false)) return;
                    }
                }
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        dropOneRock(i, j, options, true);
                    }
                }
                System.out.println("successfully dropped a rock from house x1 " + x1 + " y1 " + y1 + " to x2 " + x2 + " y2 " + y2);
                break;
            case "drop tree":
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        if (!dropOneTree(i, j, options, false)) return;
                    }
                }
                for (int i = x1; i < x2; i++) {
                    for (int j = y1; j < y2; j++) {
                        dropOneTree(i, j, options, true);
                    }
                }
                System.out.println("successfully dropped tree from house x1 " + x1 + " y1 " + y1 + " to x2 " + x2 + " y2 " + y2);
                break;
        }
    }

    private static void dropBuilding(int x, int y, HashMap<String, ArrayList<String>> options) {
        String type = setTypeForTreeAndTextureAndBuilding(options);
        MapMenuMessage message = MapMenuController.dropBuilding(x, y, type);
        switch (message) {
            case NOT_VALID_TYPE_FOR_DROP_BUILDING:
                System.out.println("enter a valid type for building");
                return;
            case HOUSE_IS_FILED_WITH_BUILDING:
                System.out.println("house or houses around that that this building needs them are filled with buildings");
                return;
            case FORBIDDEN_DROP_BUILDING_ON_ROCK:
                System.out.println("you can not drop building on a rock");
                return;
            case CAN_NOT_PLACE_THAT_THING_ON_IT:
                System.out.println("you can place anything on oi,sea,shallow water,river,big pound and sea");
                return;
            case MANGONEL_AND_BALLIASTE_MUST_ON_SQUARE_OR_ROUND_TOWER:
                System.out.println("mangonel and balistae must be place on square or round tower");
                return;
            case FARMS_NEED_TO_BE_ON_THICK_SCRUB_OR_OASIS_GRASS:
                System.out.println("apple orchard,diary farmer,hops farmer,wheat farmer should be place on thick scrub or oasis grass");
                return;
            case IRON_MINE_MUST_BE_ON_IRON:
                System.out.println("iron mine must be placed on iron");
                return;
            case PITCH_RIG_ON_SMALL_POUND:
                System.out.println("pitch rig must be place on small pound");
                return;
            case QUARRY_ON_BOULDERS:
                System.out.println("quarry must be placed on boulders");
                return;
            case ONLY_MANGONEL_BALLIASTE_ON_SQUARE_AND_ROUND:
                System.out.println("you can only drop mangonel and balistae in square or round tower");
                return;
            case ONLY_IRON_MINE_ON_IRON:
                System.out.println("only iron mine can be placed on iron");
                return;
            case ONLY_PITCH_RIG_ON_SMALL_POUND:
                System.out.println("only pitch rig can be placed on small pound");
                return;
            case ONLY_QUARRY_ON_BOULDERS:
                System.out.println("only quarry can be placed on boulders");
                return;
            case PUT_STORAGE_NEXT_TO_EACH_OTHER:
                System.out.println("you have to put granary storages next to each other");
                break;
            case NOT_ENOUGH_RESOURCE:
                System.out.println("you dont have enough resources to build that building");
                return;
            case NOT_ENOUGH_WORKERS:
                System.out.println("you dont have enough workers to work on that building");
                return;
            case NOT_ENOUGH_ENGINEER:
                System.out.println("you dont have enough engineer to work on that building");
                return;
            case NOT_ENOUGH_MONEY:
                System.out.println("you dont have enough gold to build that building");
                return;
            case SUCCESS:
                System.out.println("you successfully dropped your building on position x " + x + " y " + y);
                return;
        }
    }

    private static void selectBuilding(int x, int y) {
        switch (MapMenuController.selectBuilding(x, y)) {
            case HOUSE_IS_EMPTY:
                System.out.println("the house you have entered is empty");
                break;
            case BUILDING_OR_SOLDIER_DOESNT_BELONG_TO_YOU:
                System.out.println("building in that house doesnt belong to you");
                break;
            case SUCCESS:
                System.out.println("successfully entered the building menu");
                BuildingMenu.run(x, y);
                break;
        }
    }

    private static void selectUnit(int x, int y) {
        switch (MapMenuController.selectUnit(x, y)) {
            case HOUSE_IS_EMPTY:
                System.out.println("there is no soldier in that house");
                break;
            case BUILDING_OR_SOLDIER_DOESNT_BELONG_TO_YOU:
                System.out.println("there is no soldier belonged to you in that house");
                break;
            case SUCCESS:
                System.out.println("successfully entered the unit menu");
                UnitMenu.run(x, y);
                break;
        }
    }

    private static void nextTurn() {
        if (Game.getUsers().indexOf(Game.getCurrentUser()) == Game.getUsers().size() - 1) {
            GameMenuController.doGameInEachTurn();
        }
        GameMenuController.setNextUser();
    }
}
