package view;

import controller.BuildingMenuController;
import model.Resource;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.BuildingMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildingMenu {
    public void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.BACK, command) != null)
                return;
            else if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("building menu");
            else if (CommandHandler.parsCommand(Command.REPAIR, command) != null)
                repair();
            else if ((options = CommandHandler.parsCommand(Command.CREATE_UNIT, command)) != null)
                createUnit(options);
            else if ((options = CommandHandler.parsCommand(Command.TAX_RATE, command)) != null)
                taxRateSet(options);
            else if ((options = CommandHandler.parsCommand(Command.FOOD_RATE, command)) != null)
                foodRateSet(options);
            else if ((options = CommandHandler.parsCommand(Command.OPEN_GATE, command)) != null)
                openGate(options);
            else if ((options = CommandHandler.parsCommand(Command.CLOSE_GATE, command)) != null)
                closeGate(options);
            else if ((options = CommandHandler.parsCommand(Command.CHANGE_OUTPUT, command)) != null)
                changeOutput(options);
            else
                System.out.println("Invalid command in building menu");
        }
    }

    private void repair() {
        BuildingMenuMessage result = BuildingMenuController.repair();
        switch (result){
            case SUCCESS -> System.out.println("repaired successfully");
            case INVALID_BUILDING -> System.out.println("you have selected invalid building to use this command");
        }
    }

    private void createUnit(HashMap<String, ArrayList<String>> options) {
        String unit = null;
        int count = -100;
        for (String q : options.keySet()) {
            switch (q){
                case "t":
                    unit = options.get(q).get(0);
                    break;
                case "c":
                    try {
                        count = Integer.parseInt(options.get(q).get(0));
                    } catch (NumberFormatException e){
                        System.out.println("invalid count entered");
                        return;
                    }
                    break;
            }
        }
        if(unit == null){
            System.out.println("type not entered");
            return;
        }
        if(count == -100){
            System.out.println("count not entered");
            return;
        }
        if(count < 1){
            System.out.println("invalid count");
            return;
        }
        BuildingMenuMessage result = BuildingMenuController.createUnit(unit, count);
        switch (result){
            case SUCCESS -> System.out.println("units created successfully");
            case INVALID_BUILDING -> System.out.println("you have selected invalid building to use this command");
        }
    }
    private void taxRateSet(HashMap<String, ArrayList<String>> options) {
        int taxRate = -100;
        for (String s : options.keySet()) {
            if (s.equals("r")) {
                try {
                    taxRate = Integer.parseInt(options.get(s).get(0));
                } catch (NumberFormatException e) {
                    System.out.println("invalid rate entered");
                    return;
                }
            }
        }
        if(taxRate < -3 || taxRate > 8){
            System.out.println("tax rate must grater than -4 and lower than 9");
            return;
        }
        BuildingMenuMessage result = BuildingMenuController.taxRateSet(taxRate);
        switch (result){
            case SUCCESS -> System.out.println("tax rate set successfully");
            case INVALID_BUILDING -> System.out.println("you have selected invalid building to use this command");
        }
    }

    private void foodRateSet(HashMap<String, ArrayList<String>> options) {
        int foodRate = -100;
        for (String s : options.keySet()) {
            if (s.equals("r")) {
                try {
                    foodRate = Integer.parseInt(options.get(s).get(0));
                } catch (NumberFormatException e) {
                    System.out.println("invalid rate entered");
                    return;
                }
            }
        }
        if(foodRate < -2 || foodRate > 2){
            System.out.println("tax rate must grater than -3 and lower than 3");
            return;
        }
        BuildingMenuMessage result = BuildingMenuController.foodRateSet(foodRate);
        switch (result){
            case SUCCESS -> System.out.println("food rate set successfully");
            case INVALID_BUILDING -> System.out.println("you have selected invalid building to use this command");
        }
    }

    private static void openGate(HashMap<String, ArrayList<String>> options) {
        BuildingMenuMessage result = BuildingMenuController.openGate();
        switch (result){
            case SUCCESS -> System.out.println("Gate opened successfully");
            case INVALID_BUILDING -> System.out.println("you have selected invalid building to use this command");
        }
    }

    private static void closeGate(HashMap<String, ArrayList<String>> options) {
        BuildingMenuMessage result = BuildingMenuController.closeGate();
        switch (result){
            case SUCCESS -> System.out.println("Gate closed successfully");
            case INVALID_BUILDING -> System.out.println("you have selected invalid building to use this command");
        }
    }

    private void changeOutput(HashMap<String, ArrayList<String>> options) {
        Resource resource = null;
        for (String s : options.keySet()) {
            if(s.equals("r")){
                for (Resource value : Resource.values()) {
                    if(value.getName().equals(options.get(s))){
                        resource = value;
                    }
                }
            }
        }
        if(resource == null){
            System.out.println("invalid resource name");
        }
        BuildingMenuMessage result = BuildingMenuController.changeOutput(resource);
        switch (result){
            case SUCCESS -> System.out.println("output changed successfully");
            case INVALID_BUILDING -> System.out.println("you have selected invalid building to use this command");
            case INVALID_OUTPUT -> System.out.println("this building can't produce this resource");
        }
    }
}

