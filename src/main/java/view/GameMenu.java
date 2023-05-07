package view;
import controller.*;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;

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
        HashMap<String, ArrayList<String>> options;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("you are in game menu");
            else if (CommandHandler.parsCommand(Command.BACK, command) != null) return;
            else if ((options = CommandHandler.parsCommand(Command.SHOW_MAP,command)) != null)
                showMap(options);
            //if command is drop building
            //if command is showMap
            //if command is showMapDetails
            //if command is moveMap
            //if command is setTexture
            //if command is clearBlock
            //if command is dropRock
            //if command is dropTree
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
    private void showMap(HashMap<String, ArrayList<String>> options) {
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
        if (x < 0 || x >= 500 ) {
            System.out.println("you have entered wrong number for x it has to be bigger than 0 and less than 500");
            return;
        } else if (y < 0 ||  y >= 500 ) {
            System.out.println("you have entered wrong number for x it has to be bigger than 0 and less than 500");
            return;
        }


    }

    private void showMapDetails(Matcher matcher) {

    }

    private void moveMap(Matcher matcher) {

    }

    private void setTexture(Matcher matcher) {

    }

    private void clearBlock(Matcher matcher) {

    }

    private void dropRock(Matcher matcher) {

    }

    private void dropTree(Matcher matcher) {

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
