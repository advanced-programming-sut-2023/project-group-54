package view;

import controller.*;

import java.util.Scanner;
import java.util.regex.Matcher;

public class UnitMenu {
    public static void run(int x,int y) {
        UnitMenuController.setSelectedUnit(x,y);
        String command;
        Matcher matcher;
        while (true) {
            command = MainMenu.getScanner().nextLine();
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

    private void moveUnit(Matcher matcher) {

    }

    private void patrolUnit(Matcher matcher) {

    }

    private void stopPatrolUnit(Matcher matcher) {

    }

    private void setState(Matcher matcher) {

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
