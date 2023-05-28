package view;

import controller.Controller;
import controller.MapMenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Game;
import model.User;
import org.checkerframework.checker.units.qual.C;
import view.enums.commands.Command;
import view.enums.commands.CommandHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu {

    public static void run() {
        String command;
        HashMap<String, ArrayList<String>> options;
        System.out.println("you are in menu");
        while (true) {
            command = MainMenu.getScanner().nextLine();
            if (CommandHandler.parsCommand(Command.USER_LOGOUT, command) != null) {
                LoginMenu.logout();
                return;
            } else if ((options = CommandHandler.parsCommand(Command.START_GAME, command)) != null) {
                startGame(options);
                System.out.println("you are in menu");
            }
            else if (CommandHandler.parsCommand(Command.PROFILE_MENU, command) != null){
                ProfileMenu.run();
                System.out.println("you are in menu");
            }else if(CommandHandler.parsCommand(Command.EXIT, command) != null){
                Controller.setExit(true);
                return;
            }
            else System.out.println("invalid command in menu");
        }
    }

    private static void startGame(HashMap<String, ArrayList<String>> options) {
        int count;
        try {
            count = Integer.parseInt(options.get("count").get(0));
        } catch (NumberFormatException e) {
            System.out.println("format is wrong for count you have to enter a number");
            return;
        }
        if (count < 2 || count > 8) {
            System.out.println("count must greater than 1 and lower than 9");
            return;
        }
        ArrayList<User> users = new ArrayList<>();
        User a = null;
        User b = null;
        User c = null;
        User d = null;
        User e = null;
        User f = null;
        User g = null;
        for (String s : options.keySet()) {
            switch (s) {
                case "a":
                    a = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username a is invalid");
                        return;
                    }
                    break;
                case "b":
                    b = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username b is invalid");
                        return;
                    }
                    break;
                case "c":
                    c = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username c is invalid");
                        return;
                    }
                    break;
                case "d":
                    d = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username d is invalid");
                        return;
                    }
                    break;
                case "e":
                    e = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username e is invalid");
                        return;
                    }
                    break;
                case "f":
                    f = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username f is invalid");
                        return;
                    }
                    break;
                case "g":
                    g = User.findUserByUsername(options.get(s).get(0));
                    if(a == null){
                        System.out.println("username g is invalid");
                        return;
                    }
                    break;
            }
        }
        users.add(Controller.getLoggedInUser());
        users.add(a);
        if (b != null)
            users.add(b);
        if (c != null)
            users.add(c);
        if (d != null)
            users.add(d);
        if (e != null)
            users.add(e);
        if (f != null)
            users.add(f);
        if (g != null)
            users.add(g);

        if(setMap()){
            Game.setCurrentUser(Controller.getLoggedInUser());
            Game.setUsers(users);
            Game.setUserRemoved();
            MapMenuController.setDefaultBuilding(count);
//            if(Game.getX() == 400)
//                MapMenuController.setMainHouse(75);
//            else if(Game.getX() == 200)
//                MapMenuController.setMainHouse(30);
            System.out.println("game started successfully");
            GameMenu.run();
        }else{
            System.out.println("game not started due to failure in choosing map");
        }
    }

    private static boolean setMap() {
        System.out.println("choose your map :\n1- if you want a 400 by 400 map enter 1\n2- if you want a 200 by 200 map enter 2\n3- if ypu want to use default map enter 3");
        String number = MainMenu.getScanner().nextLine();
        int mapNumber = 0;
        try {
            mapNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            System.out.println("format is wrong for map number you have to enter a number");
            return false;
        }
        if (mapNumber < 1 || mapNumber > 3) {
            System.out.println("you have enter number 1 or 2 or 3 action failed");
            return false;
        }
        MapMenuController.setMap(mapNumber);
        System.out.println("successfully done");
        return true;
    }
}

