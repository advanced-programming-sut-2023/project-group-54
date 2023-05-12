package view;

import controller.UnitMenuController;
import model.units.State;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.UnitMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class UnitMenu {
    public static void run(int x,int y) {
        UnitMenuController.setSelectedUnit(x,y);
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
            else if ((options = CommandHandler.parsCommand(Command.PATROL_UNIT, command)) != null)
                patrolUnit(options);
            else if ((options = CommandHandler.parsCommand(Command.STOP_PATROL_UNIT, command)) != null)
                stopPatrolUnit(options);
            //if command is setState
            //if command is attack
            //if command is pourOil
            //if command is digTunnel
            //if command is buildEquipment
            //if command is disbandUnit
        }
    }

    private void moveUnit(HashMap<String, ArrayList<String>> options) {
        int xTo = -100;
        int yTo = -100;
        for (String s : options.keySet()) {
            switch (s) {
                case "x":
                    try {
                        xTo = Integer.parseInt(options.get(s).get(0));
                    } catch (NumberFormatException e) {
                        System.out.println("invalid x entered");
                        return;
                    }
                    break;
                case "y":
                    try {
                        yTo = Integer.parseInt(options.get(s).get(0));
                    } catch (NumberFormatException e) {
                        System.out.println("invalid x entered");
                        return;
                    }
                    break;
            }
        }
        if(xTo == -100){
            System.out.println("x not entered");
            return;
        }
        if(yTo == -100){
            System.out.println("x not entered");
            return;
        }
        UnitMenuMessage result = UnitMenuController.moveUnit(xTo, yTo);
        if (Objects.requireNonNull(result) == UnitMenuMessage.SUCCESS) {
            System.out.println("unit will move successfully");
        }
    }

    private void patrolUnit(HashMap<String, ArrayList<String>> options) {
        int xTo = -100;
        int xFrom = -100;
        int yTo = -100;
        int yFrom = -100;
        for (String s : options.keySet()) {
            switch (s){
                case "x1":
                    try {
                        xFrom = Integer.parseInt(options.get(s).get(0));
                    } catch (NumberFormatException e){
                        System.out.println("invalid x1 entered");
                        return;
                    }
                    break;
                case "x2":
                    try {
                        xTo = Integer.parseInt(options.get(s).get(0));
                    } catch (NumberFormatException e){
                        System.out.println("invalid x2 entered");
                        return;
                    }
                    break;
                case "y1":
                    try {
                        yFrom = Integer.parseInt(options.get(s).get(0));
                    } catch (NumberFormatException e){
                        System.out.println("invalid y1 entered");
                        return;
                    }
                    break;
                case "y2":
                    try {
                        yTo = Integer.parseInt(options.get(s).get(0));
                    } catch (NumberFormatException e){
                        System.out.println("invalid y2 entered");
                        return;
                    }
                    break;
            }
        }
        if(xFrom == -100){
            System.out.println("x1 not entered");
            return;
        }
        if(xTo == -100){
            System.out.println("x2 not entered");
            return;
        }
        if(yFrom == -100){
            System.out.println("y1 not entered");
            return;
        }
        if(yTo == -100){
            System.out.println("y2 not entered");
            return;
        }
        UnitMenuMessage result = UnitMenuController.patrolUnit(xFrom, yFrom, xTo, yTo);
        if (Objects.requireNonNull(result) == UnitMenuMessage.SUCCESS) {
            System.out.println("patrol set for unit(s) successfully");
        }
    }

    private void stopPatrolUnit(HashMap<String, ArrayList<String>> options) {
        UnitMenuMessage result = UnitMenuController.stopPatrolUnit();
        if (Objects.requireNonNull(result) == UnitMenuMessage.SUCCESS) {
            System.out.println("patrol nuit stoped successfully");
        }
    }

    private void setState(HashMap<String, ArrayList<String>> options) {
        String state = options.get("s").get(0);
        State stateType = null;
        for (State value : State.values()) {
            if(value.getName().equals(state)){
                stateType = value;
            }
        }
        UnitMenuMessage result = UnitMenuController.setState(stateType);
        switch (result){
            case SUCCESS -> System.out.println("state changed successfully");
        }
    }

    private void attack(HashMap<String, ArrayList<String>> options) {
        boolean isE = false;
        int x = -100;
        int y = -100;
        for (String s : options.keySet()) {
            switch (s){
                case "e":
                    isE = true;
                    break;
                case "x":
                    try {
                        x = Integer.parseInt(options.get(s).get(0));
                    } catch (NumberFormatException e){
                        System.out.println("invalid x entered");
                        return;
                    }
                    break;
                case "y":
                    try {
                        y = Integer.parseInt(options.get(s).get(0));
                    } catch (NumberFormatException e){
                        System.out.println("invalid y entered");
                        return;
                    }
                    break;
            }
        }
        if(x == -100){
            System.out.println("x not entered");
            return;
        }
        if(y == -100){
            System.out.println("y not entered");
            return;
        }
        UnitMenuMessage result = UnitMenuController.attack(x, y);
        if (Objects.requireNonNull(result) == UnitMenuMessage.SUCCESS) {
            System.out.println("target identified successfully");
        }
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
