package view;

import controller.Controller;
import controller.MapMenuController;

import java.util.regex.Matcher;

public class Menu {
    public static void run() {
        String command;
        Matcher matcher;
        while (true) {
            command = MainMenu.getScanner().nextLine();
        }
    }

    private boolean setMap() {
        System.out.println("choose your map :\n1- if you want a 400 by 400 map enter 1\n2- if you want a 200 by 200 map enter 2");
        String command = MainMenu.getScanner().nextLine();
        int mapNumber = 0;
        try {
            mapNumber = Integer.parseInt(Controller.buildParameter(command));
        } catch (NumberFormatException e) {
            System.out.println("format is wrong for x you have to enter a number");
            return false;
        }
        if (mapNumber != 1 && mapNumber != 2) {
            System.out.println("you have enter number 1 or 2 action failed");
            return false;
        }
        MapMenuController.setMap(mapNumber);
        System.out.println("successfully done");
        return true;
    }
}

