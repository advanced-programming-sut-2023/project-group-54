package view;

import controller.*;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class UnitMenu {
    public void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        System.out.println("you are in unit up menu");
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("unit menu");
            else if ((options = CommandHandler.parsCommand(Command.MOVE_UNIT_TO, command)) != null)
                moveUnit(options);
            //if command is moveUnit
            //if command is patrolUnit
            //if command is stopPatrolUnit
            //if command is setState
            //if command is attack
            //if command is pourOil
            //if command is digTunnel
            //if command is buildEquipment
            //if command is disbandUnit
        }
    }

    private void moveUnit(HashMap<String, ArrayList<String>> options) {

    }

    private void patrolUnit(HashMap<String, ArrayList<String>> options) {

    }

    private void stopPatrolUnit(HashMap<String, ArrayList<String>> options) {

    }

    private void setState(HashMap<String, ArrayList<String>> options) {

    }

    private void attack() {

    }

    private void pourOil() {

    }

    private void digTunnel() {

    }

    private void buildEquipment() {

    }

    private void disbandUnit() {

    }
}
