package view;
import controller.GameMenuController;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;
import view.enums.messages.GameMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMenu {
    public void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.EXIT, command) != null)
                return;
            else if (CommandHandler.parsCommand(Command.SHOW_MENU, command) != null)
                System.out.println("login menu");
            else if((options = CommandHandler.parsCommand(Command.SHOW_POPULARITY_FACTORS, command)) != null)
                popularityFactorsShow(options);
            else if((options = CommandHandler.parsCommand(Command.SHOW_POPULARITY, command)) != null)
                popularityShow(options);
            else if((options = CommandHandler.parsCommand(Command.SHOW_FOOD_LIST, command)) != null)
                foodListShow(options);
            else if((options = CommandHandler.parsCommand(Command.FOOD_RATE_SHOW, command)) != null)
                foodRateShow(options);
            else if((options = CommandHandler.parsCommand(Command.TAX_RATE_SHOW, command)) != null)
                taxRateShow(options);
            else if((options = CommandHandler.parsCommand(Command.FEAR_RATE, command)) != null)
                fearRateSet(options);
            else if((options = CommandHandler.parsCommand(Command.DROP_BUILDING, command)) != null)
                dropBuilding(options);
            else if((options = CommandHandler.parsCommand(Command.SELECT_BUILDING, command)) != null)
                selectBuilding(options);
            else if((options = CommandHandler.parsCommand(Command.SELECT_UNIT, command)) != null)
                selectUnit(options);
            else if((options = CommandHandler.parsCommand(Command.NEXT_TURN, command)) != null)
                nextTurn(options);
        }
    }
    private void popularityFactorsShow(HashMap<String, ArrayList<String>> options){
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
    private void popularityShow(HashMap<String, ArrayList<String>> options){
        System.out.println(GameMenuController.getPopularity());
    }
    private void foodListShow(HashMap<String, ArrayList<String>> options){
        String s = "";
        int appleCount = GameMenuController.getAppleCount();
        int meetCount = GameMenuController.getMeetCount();
        int cheeseCount = GameMenuController.getCheeseCount();
        int breadCount = GameMenuController.getBreadCount();

        s += "Apple: " + appleCount;
        s += "\nMeet: " + meetCount;
        s += "\nCheese: " + cheeseCount;
        s += "\nBread: " + breadCount;

        System.out.println(s);
    }
    private void foodRateShow(HashMap<String, ArrayList<String>> options){
        System.out.println(GameMenuController.foodRateShow());
    }
    private void taxRateShow(HashMap<String, ArrayList<String>> options){
        System.out.println(GameMenuController.taxRateShow());
    }
    private void fearRateSet(HashMap<String, ArrayList<String>> options){
        int fearRate = 0;
        try {
            fearRate = Integer.parseInt(options.get("r").get(0));
        } catch (NumberFormatException e){
            System.out.println("invalid rate entered");
            return;
        }
        if(fearRate < -5 || fearRate > 5){
            System.out.println("fear rate must be lower than 5 and grater than -5");
            return;
        }
        GameMenuMessage result = GameMenuController.fearRateSet(fearRate);
        if(result.equals(GameMenuMessage.SUCCESS))
            System.out.println("fear rate changed successfully");
    }
    private void dropBuilding(HashMap<String, ArrayList<String>> options){

    }
    private void selectBuilding(HashMap<String, ArrayList<String>> options){

    }
    private void selectUnit(HashMap<String, ArrayList<String>> options){

    }
    private void nextTurn(HashMap<String, ArrayList<String>> options){

    }
}
