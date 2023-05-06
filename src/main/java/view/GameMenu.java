package view;
import controller.*;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;

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
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("you are in game menu");
            else if (CommandHandler.parsCommand(Command.BACK, command) != null) return;
            else if (CommandHandler.parsCommand());
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
