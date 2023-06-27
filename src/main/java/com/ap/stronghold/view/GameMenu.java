package com.ap.stronghold.view;

import com.ap.stronghold.controller.*;
import com.ap.stronghold.model.Buildings.Building;
import com.ap.stronghold.model.Buildings.ProducerBuilding;
import com.ap.stronghold.model.Direction;
import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.MapType;
import com.ap.stronghold.model.units.State;
import com.ap.stronghold.model.units.Unit;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.GameMenuMessage;
import com.ap.stronghold.view.enums.messages.MapMenuMessage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.regex.Matcher;

public class GameMenu extends Application {
    public static GridPane gridePane;
    public static Pane pane;
    private static int tilesLength = 50;
    private static int xOfMap;
    private static int yOfMap;
    private static MapType typeForTreeAndTexture = MapType.DEFAULT;
    private static Direction direction = Direction.F;
    private static HashMap<String, Image> images;
    private static ImageView[][] imageViews;
    private static int x;
    private static int y;
    private static Tooltip tooltip;
    private static Rectangle rectangle = new Rectangle();
    private static VBox rectangleVBox;
    private static VBox popularityVBox;
    private static int rectangleYIn = -1;
    private static int rectangleXIn = -1;
    private static int rectangleYOut = -1;
    private static int rectangleXOut = -1;
    private static ArrayList<Unit> unitsInRectangle = new ArrayList<>();
    private static HashSet<Building> buildingsInRectangle = new HashSet<>();

    static {
        images = new HashMap<>();
        images.put("EARTH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/earth.png").toExternalForm()));
        images.put("EARTH_AND_STONE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/earth_and_stone.png").toExternalForm()));
        images.put("BOULDERS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/boulders.png").toExternalForm()));
        images.put("ROCK_N", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/rock_n.png").toExternalForm()));
        images.put("ROCK_S", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/rock_s.png").toExternalForm()));
        images.put("ROCK_E", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/rock_e.png").toExternalForm()));
        images.put("ROCK_W", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/rock_w.png").toExternalForm()));
        images.put("IRON", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/iron.png").toExternalForm()));
        images.put("OASIS_GRASS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/oasis_grass.png").toExternalForm()));
        images.put("SCRUB", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/scrub.png").toExternalForm()));
        images.put("THICK_SCRUB", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/thick_scrub.png").toExternalForm()));
        images.put("OIL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/oil.png").toExternalForm()));
        images.put("SHALLOW_WATER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/shallow_water.png").toExternalForm()));
        images.put("RIVER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/river.png").toExternalForm()));
        images.put("SMALL_POND", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/small_pond.png").toExternalForm()));
        images.put("BIG_POUND", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/big_pound.png").toExternalForm()));
        images.put("BEACH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/beach.png").toExternalForm()));
        images.put("SEA", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Tiles/sea.png").toExternalForm()));

        images.put("WALL_STAIRS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/WALL_STAIRS.png").toExternalForm()));
        images.put("LOW_WALL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/LOW_WALL.png").toExternalForm()));
        images.put("STONE_WALL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/STONE_WALL.png").toExternalForm()));
        images.put("SMALL_STONE_GATE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/SMALL_STONE_GATE.png").toExternalForm()));
        images.put("LARGE_STONE_GATE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/LARGE_STONE_GATE.png").toExternalForm()));
        images.put("DRAW_BRIDGE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/DRAW_BRIDGE.png").toExternalForm()));
        images.put("LOOK_OUT_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/LOOK_OUT_TOWER.png").toExternalForm()));
        images.put("PERIMETER_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/PERIMETER_TOWER.png").toExternalForm()));
        images.put("DEFENCE_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/DEFENCE_TOWER.png").toExternalForm()));
        images.put("SQUARE_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/SQUARE_TOWER.png").toExternalForm()));
        images.put("ROUND_TOWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/ROUND_TOWER.png").toExternalForm()));
        images.put("ARMOURY", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/ARMOURY.png").toExternalForm()));
        images.put("BARRACKS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/BARRACKS.png").toExternalForm()));
        images.put("MERCENARY_POST", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/MERCENARY_POST.png").toExternalForm()));
        images.put("ENGINEERS_GUILD", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/ENGINEERS_GUILD.png").toExternalForm()));
        images.put("KILLING_PIT", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/KILLING_PIT.png").toExternalForm()));
        images.put("OIL_SMELTER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/OIL_SMELTER.png").toExternalForm()));
        images.put("PITCH_DITCH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/PITCH_DITCH.png").toExternalForm()));
        images.put("CAGED_WAR_DOGS", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/CAGED_WAR_DOGS.png").toExternalForm()));
        images.put("SIEGE_TENT", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/SIEGE_TENT.png").toExternalForm()));
        images.put("APPLE_ORCHARD", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/APPLE_ORCHARD.png").toExternalForm()));
        images.put("DIARY_FARMER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/DIARY_FARMER.png").toExternalForm()));
        images.put("HOPS_FARMER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/HOPS_FARMER.png").toExternalForm()));
        images.put("WHEAT_FARMER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/WHEAT_FARMER.png").toExternalForm()));
        images.put("HUNTER_POST", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/HUNTER_POST.png").toExternalForm()));
        images.put("BAKERY", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/BAKERY.png").toExternalForm()));
        images.put("BREWER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/BREWER.png").toExternalForm()));
        images.put("GRANARY", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/GRANARY.png").toExternalForm()));
        images.put("INN", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/INN.png").toExternalForm()));
        images.put("MILL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/MILL.png").toExternalForm()));
        images.put("IRON_MINE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/IRON_MINE.png").toExternalForm()));
        images.put("MARKET", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/MARKET.png").toExternalForm()));
        images.put("OX_TETHER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/OX_TETHER.png").toExternalForm()));
        images.put("PITCH_RIG", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/PITCH_RIG.png").toExternalForm()));
        images.put("QUARRY", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/QUARRY.png").toExternalForm()));
        images.put("STOCK_PILE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/STOCK_PILE.png").toExternalForm()));
        images.put("WOODCUTTER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/WOODCUTTER.png").toExternalForm()));
        images.put("HOVEL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/HOVEL.png").toExternalForm()));
        images.put("CHURCH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/CHURCH.png").toExternalForm()));
        images.put("CATHEDRAL", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/CATHEDRAL.png").toExternalForm()));
        images.put("ARMOURER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/ARMOURER.png").toExternalForm()));
        images.put("BLACKSMITH", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/BLACKSMITH.png").toExternalForm()));
        images.put("FLETCHER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/FLETCHER.png").toExternalForm()));
        images.put("POLETURNER", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/POLETURNER.png").toExternalForm()));
        images.put("MAIN_HOUSE", new Image(GameMenu.class.getResource("/com/ap/stronghold/Media/Buildings/MAIN_HOUSE.png").toExternalForm()));

        imageViews = new ImageView[180][70];
        for (int i = 0; i < 180; i++) {
            for (int j = 0; j < 70; j++) {
                imageViews[i][j] = new ImageView();
            }
        }
    }

    private Scene scene;
    private double startDragX;
    private double startDragY;

    public static void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.EXIT, command) != null)
                return;
            else if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("game menu");
            else if (CommandHandler.parsCommand(Command.TRADE_MENU, command) != null)
                TradeMenu.run();
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
            else if ((options = CommandHandler.parsCommand(Command.DROP_UNIT, command)) != null)
                dropUnit(options);
            else if (CommandHandler.parsCommand(Command.NEXT_TURN, command) != null)
                nextTurn();
            else if ((options = CommandHandler.parsCommand(Command.SELECT_BUILDING, command)) != null)
                setXYOfMapCommonErrors(options, "select building");
            else System.out.println("invalid command in game menu");
        }
    }

    private static void dropUnit(HashMap<String, ArrayList<String>> options) {
        String x = options.get("x").get(0);
        String y = options.get("y").get(0);
        String t = Controller.buildParameter(options.get("t").get(0));
        String c = options.get("c").get(0);

        if (GameMenuController.dropUnit(Integer.parseInt(x), Integer.parseInt(y), t, Integer.parseInt(c)).equals(GameMenuMessage.SUCCESS)) {
            System.out.println("drop unit successfully");
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
        Direction directionToSend = Direction.F;
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
        MapMenuMessage message = MapMenuController.dropBuilding(x, y, type, Game.getCurrentUser().getGovernment());
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
            case SHOP_MENU_BUILDING:
                System.out.println("successfully entered the shop menu");
                ShopMenu.run();
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

    private static void reload() {
        x = ((900 / tilesLength) - (200 / tilesLength));
        y = (1800 / tilesLength);

        xOfMap = Math.max(xOfMap, x / 2);
        xOfMap = Math.min(xOfMap, (Game.getX() - x / 2));
        yOfMap = Math.max(yOfMap, y / 2);
        yOfMap = Math.min(yOfMap, (Game.getY() - y / 2));

        gridePane.getChildren().clear();
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                imageViews[i][j].setFitWidth(tilesLength);
                imageViews[i][j].setFitHeight(tilesLength);
                gridePane.add(imageViews[i][j], i, j);
            }
        }

        int numberOfUnit = unitsInRectangle.size();
        double avRate = 0;
        double rateSize = 0;
        double rateMax = 0;
        double rateMin = 0;
        for (Building building : buildingsInRectangle) {
            if (building instanceof ProducerBuilding) {
                avRate += ((ProducerBuilding) building).getProductionRate();
                rateSize++;
                if (rateMax < ((ProducerBuilding) building).getProductionRate()) {
                    rateMax = ((ProducerBuilding) building).getProductionRate();
                }
                if (rateMin > ((ProducerBuilding) building).getProductionRate() || rateMin == 0) {
                    rateMin = ((ProducerBuilding) building).getProductionRate();
                }
            }
        }
        avRate = avRate == 0 ? 0 : avRate / rateSize;

        rectangleVBox.getChildren().clear();
        rectangleVBox.getChildren().add(new Text("Rectangle Info"));
        rectangleVBox.getChildren().add(new Text("units: " + numberOfUnit));
        rectangleVBox.getChildren().add(new Text("avRate: " + avRate));
        rectangleVBox.getChildren().add(new Text("rateMax: " + rateMax));
        rectangleVBox.getChildren().add(new Text("rateMin: " + rateMin));

        gridePane.add(rectangleVBox, 0, x, (200 / tilesLength), (200 / tilesLength));


        int foodPop = GameMenuController.getPopularityFromFood();
        int taxPop = GameMenuController.getPopularityFromTax();
        int fearPop = GameMenuController.getPopularityFromFear();
        int religionPop = GameMenuController.getPopularityFromReligion();
        int popularity = GameMenuController.getPopularity();

        Text foodText = new Text("Food: " + foodPop);
        if (foodPop > 0)
            foodText.setFill(Color.GREEN);
        else if (foodPop < 0)
            foodText.setFill(Color.RED);

        Text taxText = new Text("Tax: " + taxPop);
        if (taxPop > 0)
            taxText.setFill(Color.GREEN);
        else if (taxPop < 0)
            taxText.setFill(Color.RED);

        Text fearText = new Text("Fear: " + fearPop);
        if (fearPop > 0)
            fearText.setFill(Color.GREEN);
        else if (fearPop < 0)
            fearText.setFill(Color.RED);

        Text religionText = new Text("Religion: " + religionPop);
        if (religionPop > 0)
            religionText.setFill(Color.GREEN);
        else if (religionPop < 0)
            religionText.setFill(Color.RED);

        Text rateText = new Text("Rate: " + (foodPop + taxPop + fearPop + religionPop));
        if ((foodPop + taxPop + fearPop + religionPop) > 0)
            rateText.setFill(Color.GREEN);
        else if ((foodPop + taxPop + fearPop + religionPop) < 0)
            rateText.setFill(Color.RED);

        Text popularityText = new Text("Popularity: " + popularity + "/100");
        if (popularity > 0)
            popularityText.setFill(Color.GREEN);
        else if (popularity < 0)
            popularityText.setFill(Color.RED);

        popularityVBox.getChildren().clear();
        popularityVBox.getChildren().add(new Text("Popularity Info"));
        popularityVBox.getChildren().add(foodText);
        popularityVBox.getChildren().add(taxText);
        popularityVBox.getChildren().add(fearText);
        popularityVBox.getChildren().add(religionText);
        popularityVBox.getChildren().add(rateText);
        popularityVBox.getChildren().add(popularityText);

        gridePane.add(popularityVBox, (200 / tilesLength), x, (200 / tilesLength), (200 / tilesLength));
    }

    @Override
    public void start(Stage stage) throws Exception {
        pane = FXMLLoader.load(Menu.class.getResource("/com/ap/stronghold/FXML/gameMenu.fxml"));
        gridePane = (GridPane) pane.getChildren().get(0);

        rectangleVBox = new VBox();
        rectangleVBox.setAlignment(Pos.CENTER);
        rectangleVBox.setSpacing(10);

        popularityVBox = new VBox();
        popularityVBox.setAlignment(Pos.CENTER);
        popularityVBox.setSpacing(10);


        showRectAngle();

        reload();

        xOfMap = x / 2;
        yOfMap = y / 2;

        pane.getChildren().add(rectangle);

        showMap();

        scene = new Scene(pane);

        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            switch (keyCode) {
                case M -> moveSelectedUnit();
                case S -> setStateSelectedUnit();
                case A -> attackSelectedUnit();
                case D -> disbandSelectedUnit();
                case R -> repairSelectedBuilding();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    private void repairSelectedBuilding() {
        if (buildingsInRectangle.size() < 1) {
            return;
        }
        for (Building building : buildingsInRectangle) {
            BuildingMenuController.setSelectedBuilding(building);
            BuildingMenuController.repair();
        }
    }

    private void disbandSelectedUnit() {
        if (unitsInRectangle.size() < 1) {
            return;
        }
        UnitMenuController.setSelectedUnit(unitsInRectangle);
        UnitMenuController.disbandUnit();
    }

    private void attackSelectedUnit() {
        if (unitsInRectangle.size() < 1) {
            return;
        }
        int destinationX = 0;
        int destinationY = 0;

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("attack to");
        dialog.setHeaderText("attack coordinates");
        dialog.setContentText("what is X coordinates of attack?");
        Optional<String> result = dialog.showAndWait();
        while (result.isPresent()) {
            try {
                int x = Integer.parseInt(result.get());
                if (x < 0 || x > Game.getX()) {
                    dialog.setHeaderText("X must lower than " + Game.getX());
                    result = dialog.showAndWait();
                } else {
                    destinationX = x;
                    break;
                }
            } catch (NumberFormatException e) {
                dialog.setHeaderText("X must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!result.isPresent()) {
            return;
        }

        dialog.setHeaderText("attack coordinates");
        dialog.setContentText("what is Y coordinates of attack?");
        result = dialog.showAndWait();
        while (result.isPresent()) {
            try {
                int y = Integer.parseInt(result.get());
                if (y < 0 || y > Game.getY()) {
                    dialog.setHeaderText("Y must lower than " + Game.getY());
                    result = dialog.showAndWait();
                } else {
                    destinationY = y;
                    break;
                }
            } catch (NumberFormatException e) {
                dialog.setHeaderText("Y must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!result.isPresent()) {
            return;
        }

        UnitMenuController.setSelectedUnit(unitsInRectangle);
        UnitMenuController.attack(destinationX, destinationY);
    }

    private void setStateSelectedUnit() {
        if (unitsInRectangle.size() < 1) {
            return;
        }
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("state");
        dialog.setHeaderText("select unit state");

        ChoiceBox<String> order = new ChoiceBox<String>();
        order.getItems().add("standing");
        order.getItems().add("defensive");
        order.getItems().add("offensive");
        order.setValue("standing");
        HBox content = new HBox();
        content.setSpacing(10);
        content.getChildren().addAll(new Label("select units state: "), order);
        dialog.getDialogPane().setContent(content);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return order.getValue();
            }
            return null;
        });

        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            State state = null;
            switch (result.get()) {
                case "standing":
                    state = State.STATIC;
                    break;
                case "defensive":
                    state = State.DEFENSIVE;
                    break;
                case "offensive":
                    state = State.AGGRESSIVE;
                    break;
            }
            UnitMenuController.setSelectedUnit(unitsInRectangle);
            UnitMenuController.setState(state);
        }
    }

    private void moveSelectedUnit() {
        if (unitsInRectangle.size() < 1) {
            return;
        }
        int destinationX = 0;
        int destinationY = 0;

        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("move to");
        dialog.setHeaderText("destination coordinates");
        dialog.setContentText("what is X coordinates of destination?");
        Optional<String> result = dialog.showAndWait();
        while (result.isPresent()) {
            try {
                int x = Integer.parseInt(result.get());
                if (x < 0 || x > Game.getX()) {
                    dialog.setHeaderText("X must lower than " + Game.getX());
                    result = dialog.showAndWait();
                } else {
                    destinationX = x;
                    break;
                }
            } catch (NumberFormatException e) {
                dialog.setHeaderText("X must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!result.isPresent()) {
            return;
        }

        dialog.setHeaderText("destination coordinates");
        dialog.setContentText("what is Y coordinates of destination?");
        result = dialog.showAndWait();
        while (result.isPresent()) {
            try {
                int y = Integer.parseInt(result.get());
                if (y < 0 || y > Game.getY()) {
                    dialog.setHeaderText("Y must lower than " + Game.getY());
                    result = dialog.showAndWait();
                } else {
                    destinationY = y;
                    break;
                }
            } catch (NumberFormatException e) {
                dialog.setHeaderText("Y must be a number");
                result = dialog.showAndWait();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        if (!result.isPresent()) {
            return;
        }

        UnitMenuController.setSelectedUnit(unitsInRectangle);
        UnitMenuController.moveUnit(destinationX, destinationY);
    }

    public void press(MouseEvent mouseEvent) {
        startDragX = mouseEvent.getSceneX();
        startDragY = mouseEvent.getSceneY();
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            rectangleXOut = rectangleXIn = -1;
            rectangleYOut = rectangleYIn = -1;
            rectangle.setWidth(0);
            rectangle.setHeight(0);
            showRectAngle();
        }
    }

    public void drag(MouseEvent event) {
        if (event.getButton().equals(MouseButton.SECONDARY)) {
            if (startDragY <= 700) {
                boolean moved = false;
                if ((int) (startDragX - event.getSceneX()) / tilesLength > 0) {
                    yOfMap += (startDragX - event.getSceneX()) / tilesLength;
                    moved = true;
                } else if ((int) (event.getSceneX() - startDragX) / tilesLength > 0) {
                    yOfMap -= (event.getSceneX() - startDragX) / tilesLength;
                    moved = true;
                }
                if ((int) (startDragY - event.getSceneY()) / tilesLength > 0) {
                    xOfMap += (startDragY - event.getSceneY()) / tilesLength;
                    moved = true;
                } else if ((int) (event.getSceneY() - startDragY) / tilesLength > 0) {
                    xOfMap -= (event.getSceneY() - startDragY) / tilesLength;
                    moved = true;
                }
                if (moved) {
                    startDragX = event.getSceneX();
                    startDragY = event.getSceneY();
                    reload();
                    showRectAngle();
                    showMap();
                }
            }
        } else if (event.getButton().equals(MouseButton.PRIMARY)) {
            if (startDragY <= 700) {
                rectangleYIn = (int) ((startDragX / 1800) * y) + yOfMap - y / 2;
                rectangleXIn = (int) ((startDragY / (900 - ((200 / tilesLength) * tilesLength))) * x) + xOfMap - x / 2;
                rectangleYOut = (int) ((event.getX() / 1800) * y) + yOfMap - y / 2;
                rectangleXOut = (int) ((event.getY() / (900 - ((200 / tilesLength) * tilesLength))) * x) + xOfMap - x / 2;
                if (rectangleYIn > rectangleYOut) {
                    int tmp = rectangleYIn;
                    rectangleYIn = rectangleYOut;
                    rectangleYOut = tmp;
                }
                if (rectangleXIn > rectangleXOut) {
                    int tmp = rectangleXIn;
                    rectangleXIn = rectangleXOut;
                    rectangleXOut = tmp;
                }
                reload();
                showRectAngle();
            }
        }
    }

    private void showRectAngle() {
        if (rectangleYIn != -1 && rectangleXIn != -1) {
            int xMin = xOfMap < x / 2 ? 0 : xOfMap - x / 2;
            int yMin = yOfMap < y / 2 ? 0 : yOfMap - y / 2;
            rectangle.setX((rectangleYIn - yMin) * tilesLength);
            rectangle.setY((rectangleXIn - xMin) * tilesLength);
            rectangle.setWidth((rectangleYOut - rectangleYIn + 1) * tilesLength);
            rectangle.setHeight((rectangleXOut - rectangleXIn + 1) * tilesLength);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke(Color.RED);
            for (int i = rectangleXIn; i < rectangleXOut; i++) {
                for (int j = rectangleYIn; j < rectangleYOut; j++) {
                    unitsInRectangle.addAll(Game.getGameMap()[i][j].getUnit());
                    if (Game.getGameMap()[i][j].getBuilding() != null)
                        buildingsInRectangle.add(Game.getGameMap()[i][j].getBuilding());
                }
            }
        }
    }

    public void scroll(ScrollEvent scrollEvent) {
        double deltaY = scrollEvent.getDeltaY();
        if (deltaY < 0) {
            tilesLength -= 1;
            tilesLength = Math.max(tilesLength, 10);
        } else {
            tilesLength += 1;
            tilesLength = Math.min(tilesLength, 60);
        }
        reload();
        showRectAngle();
        showMap();
    }

    public void showMap() {
        int xMin = xOfMap < x / 2 ? 0 : xOfMap - x / 2;
        int xMax = xOfMap < x / 2 ? x : xOfMap + x / 2;
        int yMin = yOfMap < y / 2 ? 0 : yOfMap - y / 2;
        int yMax = yOfMap < y / 2 ? y : yOfMap + y / 2;
        if (x % 2 == 1) {
            xMax++;
        }
        if (y % 2 == 1) {
            yMax++;
        }
        yMax = Math.min(yMax, Game.getY());
        xMax = Math.min(xMax, Game.getX());
        for (int i = yMin; i < yMax; i++) {
            for (int j = xMin; j < xMax; j++) {
                imageViews[i - yMin][j - xMin].setImage(images.get(Game.getGameMap()[j][i].getMapType().name()));
            }
        }
        Node node1 = pane.getChildren().get(0);
        Node node2 = pane.getChildren().get(1);
        pane.getChildren().clear();
        pane.getChildren().add(node1);
        pane.getChildren().add(node2);
        reload();
        for (Building building : Building.getBuildings()) {
            if (building.getX1Position() >= xMin && building.getX2Position() <= xMax) {
                if (building.getY1Position() >= yMin && building.getY2Position() <= yMax) {
                    ImageView imageView = new ImageView(images.get(building.getBuildingType().name()));
                    imageView.setFitWidth((building.getY2Position() - building.getY1Position()) * tilesLength);
                    imageView.setFitHeight((building.getX2Position() - building.getX1Position()) * tilesLength);
                    imageView.setLayoutX((building.getY1Position() - yMin) * tilesLength);
                    imageView.setLayoutY((building.getX1Position() - xMin) * tilesLength);
                    pane.getChildren().add(imageView);
                }
            }
        }
    }

    public void hover(MouseEvent mouseEvent) {
        if (mouseEvent.getY() < 700) {
            int yIn = (int) ((mouseEvent.getX() / 1800) * y) + yOfMap - y / 2;
            int xIn = (int) ((mouseEvent.getY() / (900 - ((200 / tilesLength) * tilesLength))) * x) + xOfMap - x / 2;
            String unitData = "x: " + xIn + " y: " + yIn + "\n" + MapMenuController.showMapDetails(xIn, yIn);
            for (Unit unit : Game.getGameMap()[xIn][yIn].getUnit()) {
                unitData += "\n" + (unit.getxTarget() == -1 ? "" : "attacking...") + "type: " + unit.getUnitType().name() + " health: " + unit.getHp() + " damage: " + unit.getUnitType().getDamage();
            }
            tooltip = new Tooltip(unitData);
            tooltip.setShowDelay(Duration.seconds(1));
            tooltip.setShowDuration(Duration.seconds(4));
            tooltip.setWrapText(true);
            Tooltip.install(gridePane, tooltip);
        }
    }
}