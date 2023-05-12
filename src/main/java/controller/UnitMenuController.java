package controller;

import model.Direction;
import model.Game;
import model.units.Unit;
import view.enums.messages.UnitMenuMessage;

import java.util.ArrayList;

public class UnitMenuController {
    private static ArrayList<Unit> selectedUnit;

    public static void setSelectedUnit(int x,int y) {
        for (Unit unit : Game.getGameMap()[x][y].getUnit()) {
            if (unit.getGovernment().equals(Game.getCurrentUser().getGovernment()))
                selectedUnit.add(unit);
        }
    }

    public UnitMenuMessage moveUnit(int xCoordinate, int yCoordinate) {
        return UnitMenuMessage.SUCCESS;
    }

    public UnitMenuMessage patrolUnit(int xCoordinate1, int yCoordinate1, int xCoordinate2, int yCoordinate2) {
        return UnitMenuMessage.SUCCESS;
    }

    public UnitMenuMessage stopPatrolUnit() {
        return UnitMenuMessage.SUCCESS;
    }

    public UnitMenuMessage setState(int xCoordinate, int yCoordinate, String state) {
        return UnitMenuMessage.SUCCESS;
    }

    public UnitMenuMessage attack(int enemyXCoordinate, int enemyYCoordinate) {
        return UnitMenuMessage.SUCCESS;
    }

    public UnitMenuMessage pourOil(Direction direction) {
        return UnitMenuMessage.SUCCESS;
    }

    public UnitMenuMessage digTunnel(int xCoordinate, int yCoordinate) {
        return UnitMenuMessage.SUCCESS;
    }

    public UnitMenuMessage buildEquipment(String equipmentName) {
        return UnitMenuMessage.SUCCESS;
    }

    public UnitMenuMessage disbandUnit() {
        return UnitMenuMessage.SUCCESS;
    }
}
