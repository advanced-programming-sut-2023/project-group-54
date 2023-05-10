package view;
import controller.*;
import model.Direction;
import model.MapType;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.commands.Regexes;
import view.enums.messages.MapMenuMessage;

import java.lang.foreign.MemorySegment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

public class GameMenu {
    private static int xOfMap;
    private static int yOfMap;

    public static int getXOfMap() {
        return xOfMap;
    }

    public static int getYOfMap() {
        return yOfMap;
    }

    public void run() {
        String command;
        Matcher matcher;
        HashMap<String, ArrayList<String>> options;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("you are in game menu");
            else if (CommandHandler.parsCommand(Command.BACK, command) != null) return;
            else if ((options = CommandHandler.parsCommand(Command.SHOW_MAP,command)) != null)
                setXYOfMapCommonErrors(options,"show map");
            else if ((matcher = MainMenu.getMatcher(command, Regexes.MAP_SHOW_MOVE.getRegex())) != null)
                showMapMove(matcher);
            else if ((options = CommandHandler.parsCommand(Command.SHOW_DETAILS,command)) != null)
                setXYOfMapCommonErrors(options,"details");
           else if ((matcher = MainMenu.getMatcher(command,Regexes.MAP_DETAILS_MOVE.getRegex())) != null)
               showMapDetailsMove(matcher);
            else if ((options = CommandHandler.parsCommand(Command.SET_TEXTURE_FOR_ONE_HOUSE,command)) != null)
                setXYOfMapCommonErrors(options,"texture for one house");
            else if ((options = CommandHandler.parsCommand(Command.SET_TEXTURE_FOR_RECTANGLE,command)) != null)
                setTextureForRectangle(options);
            else if ((options = CommandHandler.parsCommand(Command.CLEAR,command)) != null)
                setXYOfMapCommonErrors(options,"clear block for one house");
            // multiple
            else if ((options = CommandHandler.parsCommand(Command.DROP_ROCK,command)) != null)
                setXYOfMapCommonErrors(options,"drop rock for one house");
            //multiple
            else if ((options = CommandHandler.parsCommand(Command.DROP_TREE,command)) != null)
                setXYOfMapCommonErrors(options,"drop tree for one house");
            //multiple
            //if command is drop building
            //if command is popularityFactorsShow
            //if command is popularityShow
            //if command is foodListShow
            //if command is foodRateShow
            //if command is taxRateShow
            //if command is fearRateSet
            //if command is selectBuilding
            //if command is selectUnit
            //if command is nextTurn
        }
    }

    private void setXYOfMapCommonErrors(HashMap<String, ArrayList<String>> options,String whichFunction) {
        int x = 0,y=0;
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
                    }
                    break;
            }
        }
        setXYOfMap(x,y,whichFunction,options);
    }

    private void setXYOfMap(int x,int y,String whichFunction,HashMap<String, ArrayList<String>> options) {
        if (x < 0 || x >= 500 ) {
            System.out.println("you have entered wrong number for x it has to be bigger than 0 and less than 500");
            return;
        } else if (y < 0 ||  y >= 500 ) {
            System.out.println("you have entered wrong number for x it has to be bigger than 0 and less than 500");
            return;
        }
        switch (whichFunction) {
            case "show map":
                xOfMap = x;
                yOfMap = y;
                showMap(x,y);
                break;
            case "details":
                xOfMap = x;
                yOfMap = y;
                showMapDetails(x,y);
                break;
            case "texture for one house":
                if (setTextureForOneHouse(x,y,options))
                    System.out.println("successfully changed the texture in house -x " + x + " -y " + y );
                break;
            case "clear block for one house":
                clearOneBlock(x,y);
                break;
            case "drop rock for one house":
                if (dropOneRock(x,y,options))
                    System.out.println("successfully dropped a rock in house -x " + x + " -y " + y );
                break;
            case "drop tree for one house":
               if (dropOneTree(x,y,options))
                   System.out.println("successfully dropped a tree in house -x " + x + " -y " + y );
                break;
        }
    }

    private void showMap(int x,int y) {
        System.out.println(MapMenuController.showMap(x,y));
    }

    private void showMapDetails(int x,int y) {
        System.out.println(MapMenuController.showMapDetails(x,y));
    }

    private void moveMapShow(Matcher matcher) {
        String upOrDown = matcher.group("upOrDown");
        String leftOrRight = matcher.group("leftOrRight");
        if (upOrDown != null) {
            if(upOrDown.equals("up")) xOfMap++;
            else xOfMap--;
        }
        if (leftOrRight != null) {
            if (leftOrRight.equals("right")) yOfMap++;
            else yOfMap--;
        }
    }

    private void showMapMove(Matcher matcher) {
        moveMapShow(matcher);
        showMap(xOfMap,yOfMap);
    }

    private void showMapDetailsMove(Matcher matcher) {
        moveMapShow(matcher);
        showMapDetails(xOfMap,yOfMap);
    }

    private MapType typeChecker(HashMap<String, ArrayList<String>> options) {
        String type = "";
        for (String s : options.keySet()) {
            if (s.equals("t")) {
                type = Controller.buildParameter(options.get(s).get(0));
                break;
            }
        }
        boolean sign = true;
        MapType mapToSend = null;
        for (MapType allMapType : MapType.DEFAULT.getAllMapTypes()) {
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

    private boolean setTextureForOneHouse(int x, int y,HashMap<String, ArrayList<String>> options) {
        MapType type= typeChecker(options);
        MapMenuMessage mapMenuMessage;
        if (type != null) {
            mapMenuMessage = MapMenuController.setTextureFinalTest(x,y,type);
            if (!mapMenuMessage.equals(MapMenuMessage.SUCCESS)) {
                System.out.println("a building exists int house -x "+ x + " -y "+ y + " you can not do this action");
                return false;
            }
            MapMenuController.setTexture(x,y,type);
            return true;

        }
        else return false;
    }

    private void clearOneBlock(int x,int y) {
        //to complete
    }

    private Direction directionChecker(HashMap<String, ArrayList<String>> options) {
        String direction = "";
        for (String s : options.keySet()) {
            if (s.equals("d")) {
                direction = Controller.buildParameter(options.get(s).get(0));
                break;
            }
        }
        boolean sign = true;
        Direction directionToSend = null;
        for (Direction allDirections : Direction.R.getAllDirections()) {
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

    private boolean dropOneRock(int x,int y,HashMap<String, ArrayList<String>> options) {
        Direction direction = directionChecker(options);
        if (direction != null ){
            MapMenuMessage message = MapMenuController.dropRockFinalTest(x,y,direction);
            if (!message.equals(MapMenuMessage.SUCCESS)) {
                System.out.println("a building exists int house -x "+ x + " -y "+ y + " you can not do this action");
                return false;
            }
            MapMenuController.dropRock(x,y,direction);
            return true;
        }
        return false;
    }

    private MapType treeChecker(HashMap<String, ArrayList<String>> options) {
        String treeType = "";
        for (String s : options.keySet()) {
            if (s.equals("t")) {
                treeType = Controller.buildParameter(options.get(s).get(0));
                break;
            }
        }
        boolean sign = true;
        MapType treeToSend = null;
        for (MapType allMapType : MapType.DEFAULT.getAllMapTypes()) {
            if (allMapType.toString().equals(treeType.toUpperCase())) {
                sign = false;
                if (!allMapType.isInTextureCommand()) {
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
    private boolean dropOneTree(int x, int y, HashMap<String, ArrayList<String>> options) {

    }

    private void checkRectangleIsValid() {

    }

    private void setTextureForRectangle(HashMap<String, ArrayList<String>> options) {

    }

    private void clearBlockRectangle(Matcher matcher) {

    }

    private void dropRockRectangle(Matcher matcher) {

    }

    private void dropTreeRectangle(Matcher matcher) {

    }
    private void popularityFactorsShow(Matcher matcher){

    }
    private void popularityShow(Matcher matcher){

    }
    private void foodListShow(Matcher matcher){

    }
    private void foodRateShow(Matcher matcher){

    }
    private void taxRateShow(Matcher matcher){

    }
    private void fearRateSet(Matcher matcher){

    }
    private void dropBuilding(Matcher matcher){

    }
    private void selectBuilding(Matcher matcher){

    }
    private void selectUnit(Matcher matcher){

    }
    private void nextTurn(Matcher matcher){

    }
}
