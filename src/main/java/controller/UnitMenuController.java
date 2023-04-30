package controller;

import model.Direction;
import model.units.Unit;
import view.enums.messages.UnitMenuMessage;

import java.util.ArrayList;

public class UnitMenuController {
    private ArrayList<Unit> selectedUnit;

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
