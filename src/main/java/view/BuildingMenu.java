package view;
import controller.*;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class BuildingMenu {
    public void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if(CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if(CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("building menu");
            else if((options = CommandHandler.parsCommand(Command.REPAIR, command)) != null)
                repair(options);
            else if((options = CommandHandler.parsCommand(Command.CREATE_UNIT, command)) != null)
                createUnit(options);
            else if((options = CommandHandler.parsCommand(Command.TAX_RATE, command)) != null)
                taxRateSet(options);
            //if command is repair
            //if command is createUnit
            //if command is taxRateSet
            //if command is foodRateSet
        }
    }

    private void repair(HashMap<String, ArrayList<String>> options) {

    }

    private void createUnit(HashMap<String, ArrayList<String>> options) {

    }

    private void taxRateSet(HashMap<String, ArrayList<String>> options) {

    }

    private void foodRateSet(HashMap<String, ArrayList<String>> options) {

    }

    private void openGate(HashMap<String, ArrayList<String>> options) {

    }

    private void changeOutput(HashMap<String, ArrayList<String>> options) {

    }
}

