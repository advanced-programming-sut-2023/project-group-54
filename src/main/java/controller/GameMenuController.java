package controller;

import model.*;
import model.Buildings.Building;
import view.enums.messages.GameMenuMessage;

public class GameMenuController {
    private static Game currentGame;

    public static String popularityFactorsShow() {
        return "";
    }

    public static String popularityShow() {
        return "";
    }

    public static String foodListShow() {
        return "";
    }

    public static String foodRateShow() {
        return "";
    }

    public static String taxRateShow() {
        return "";
    }

    public static GameMenuMessage fearRateSet(int rateNumber) {
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage dropBuilding(int xCoordinate, int yCoordinate, Building building) {
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage selectBuilding(int xCoordinate, int yCoordinate) {
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage selectUnit(int xCoordinate, int yCoordinate) {
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage nextTurn() {
        return GameMenuMessage.SUCCESS;
    }
}
