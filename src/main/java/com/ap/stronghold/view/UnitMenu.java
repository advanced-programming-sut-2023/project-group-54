package com.ap.stronghold.view;

import com.ap.stronghold.controller.BuildingMenuController;
import com.ap.stronghold.controller.UnitMenuController;
import com.ap.stronghold.model.Buildings.SiegeType;
import com.ap.stronghold.model.Direction;
import com.ap.stronghold.model.units.State;
import com.ap.stronghold.view.enums.commands.Command;
import com.ap.stronghold.view.enums.commands.CommandHandler;
import com.ap.stronghold.view.enums.messages.UnitMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class UnitMenu {
    private static boolean isDisband = false;
    public static void run(int x,int y) {
        UnitMenuController.setSelectedUnit(x,y);
        String command;
        HashMap<String, ArrayList<String>> options;
        System.out.println("you are in unit menu");
        while (true) {
            if(isDisband)
                return;
            command = MainMenu.getScanner().nextLine();
//            if (CommandHandler.parsCommand(Command.BACK, command) != null)
//                return;
//            else if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
//                System.out.println("unit menu");
//            else if ((options = CommandHandler.parsCommand(Command.MOVE_UNIT_TO, command)) != null)
//                moveUnit(options);
            if ((options = CommandHandler.parsCommand(Command.PATROL_UNIT, command)) != null)
                patrolUnit(options);
            else if ((options = CommandHandler.parsCommand(Command.STOP_PATROL_UNIT, command)) != null)
                stopPatrolUnit(options);
//            else if ((options = CommandHandler.parsCommand(Command.SET, command)) != null)
//                setState(options);
//            else if ((options = CommandHandler.parsCommand(Command.ATTACK, command)) != null)
//                attack(options);
//            else if ((options = CommandHandler.parsCommand(Command.DISBAND_UNIT, command)) != null)
//                disbandUnit();
            else if ((options = CommandHandler.parsCommand(Command.POUR_OIL, command)) != null)
                pourOil(options);
            else if ((options = CommandHandler.parsCommand(Command.BUILD, command)) != null)
                buildEquipment(options);
            else
                System.out.println("invalid command in unit menu");
        }
    }

    private static void moveUnit(HashMap<String, ArrayList<String>> options) {
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

    private static void patrolUnit(HashMap<String, ArrayList<String>> options) {
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
        switch (result) {
            case THATS_TOO_FAR_FROM_COORDINATE:
                System.out.println("selected unit is too far from that coordinate action failed");
                break;
            case CAN_NOT_PASS_THAT_MAP_TYPE:
                System.out.println("there is a type of map in the way which can not be passed");
                break;
            case THERE_ARE__BUILDING_ON_THAT_WAY:
                System.out.println("there are buildings on the way action failed");
                break;
            default:
                System.out.println("patrol set for unit(s) successfully");
                break;
        }
    }

    private static void stopPatrolUnit(HashMap<String, ArrayList<String>> options) {
        UnitMenuMessage result = UnitMenuController.stopPatrolUnit();
        if (Objects.requireNonNull(result) == UnitMenuMessage.SUCCESS) {
            System.out.println("patrol unit stopped successfully");
        }
    }

    private static void setState(HashMap<String, ArrayList<String>> options) {
        String state = options.get("s").get(0);
        State stateType = null;
        for (State value : State.values()) {
            if(value.getName().equals(state)){
                stateType = value;
            }
        }
        if(stateType == null){
            System.out.println("invalid state");
            return;
        }
        UnitMenuMessage result = UnitMenuController.setState(stateType);
        switch (result){
            case SUCCESS -> System.out.println("state changed successfully");
        }
    }

    private static void attack(HashMap<String, ArrayList<String>> options) {
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

    private static void pourOil(HashMap<String, ArrayList<String>> options) {
        String direction = options.get("d").get(0);
        Direction direction1 = null;
        switch (direction){
            case "up":
                direction1 = Direction.UP;
                break;
            case "down":
                direction1 = Direction.DOWN;
                break;
            case "right":
                direction1 = Direction.RIGHT;
                break;
            case "left":
                direction1 = Direction.LEFT;
                break;
        }
        if(direction1 == null){
            System.out.println("invalid direction");
            return;
        }
        UnitMenuMessage result = UnitMenuController.pourOil(direction1);
        switch (result){
            case SUCCESS -> System.out.println("this unit(s) pour oil after turn");
            case INVALID_UNIT -> System.out.println("this command can't use for this unit(s)");
        }
    }

    private static void buildEquipment(HashMap<String, ArrayList<String>> options) {
        String equipment = options.get("q").get(0);
        SiegeType siegeType = null;
        for (SiegeType value : SiegeType.values()) {
            if(value.getName().equals(equipment)){
                siegeType = value;
                break;
            }
        }
        if(siegeType == null){
            System.out.println("invalid equipment");
        }
        UnitMenuMessage result = UnitMenuController.buildEquipment(siegeType);
        switch (result){
            case SUCCESS -> System.out.println("this unit(s) make equipment after turn");
            case INVALID_UNIT -> System.out.println("this command can't use for this unit(s)");
        }
    }

    private static void disbandUnit() {
        UnitMenuMessage result = UnitMenuController.disbandUnit();
        if (Objects.requireNonNull(result) == UnitMenuMessage.SUCCESS) {
            System.out.println("unit(s) disbanded successfully");
            isDisband = true;
        }
    }
}
