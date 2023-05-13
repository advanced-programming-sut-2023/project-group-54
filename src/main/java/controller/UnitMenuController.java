package controller;

import model.Buildings.SiegeType;
import model.Direction;
import model.Game;
import model.units.Engineer;
import model.units.State;
import model.units.Unit;
import view.enums.messages.UnitMenuMessage;

import java.util.ArrayList;

public class UnitMenuController {
    private static ArrayList<Unit> selectedUnit;

    public static void setSelectedUnit(int x, int y) {
        for (Unit unit : Game.getGameMap()[x][y].getUnit()) {
            if (unit.getGovernment().equals(Game.getCurrentUser().getGovernment()))
                selectedUnit.add(unit);
        }
    }

    public static UnitMenuMessage moveUnit(int xCoordinate, int yCoordinate) {
        for (Unit unit : selectedUnit) {
            unit.setXMoveTarget(xCoordinate);
            unit.setYMoveTarget(yCoordinate);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage patrolUnit(int xCoordinate1, int yCoordinate1, int xCoordinate2, int yCoordinate2) {
        for (Unit unit : selectedUnit) {
            unit.setPatrol(true);
            unit.setPatrolTF(0);
            unit.setPatrolXFrom(xCoordinate1);
            unit.setPatrolXTarget(xCoordinate2);
            unit.setPatrolYFrom(yCoordinate1);
            unit.setPatrolYTarget(yCoordinate2);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage stopPatrolUnit() {
        for (Unit unit : selectedUnit) {
            unit.setPatrol(false);
            unit.setPatrolTF(0);
            unit.setPatrolXFrom(0);
            unit.setPatrolXTarget(0);
            unit.setPatrolYFrom(0);
            unit.setPatrolYTarget(0);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage setState(State state) {
        for (Unit unit : selectedUnit) {
            unit.setState(state);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage attack(int XCoordinate, int YCoordinate) {
        for (Unit unit : selectedUnit) {
            unit.setXTarget(XCoordinate);
            unit.setYTarget(YCoordinate);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage pourOil(Direction direction) {
        boolean isEngineer = true;
        for (Unit unit : selectedUnit) {
            if(!(unit instanceof Engineer))
                isEngineer = false;
        }
        if(!isEngineer)
            return UnitMenuMessage.INVALID_UNIT;
        for (Unit unit : selectedUnit) {
            Engineer engineer = (Engineer) unit;
            engineer.setPour(direction);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage buildEquipment(SiegeType siegeType) {
        boolean isEngineer = true;
        for (Unit unit : selectedUnit) {
            if(!(unit instanceof Engineer))
                isEngineer = false;
        }
        if(!isEngineer)
            return UnitMenuMessage.INVALID_UNIT;

        for (Unit unit : selectedUnit) {
            Engineer engineer = (Engineer) unit;
            engineer.setSiegeTypeToBuild(siegeType);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage disbandUnit() {
        for (Unit unit : selectedUnit) {
            Unit.getUnits().remove(unit);
            Game.getCurrentUser().getGovernment().setPopulation(Game.getCurrentUser().getGovernment().getPopulation() + 1);
        }
        return UnitMenuMessage.SUCCESS;
    }
}
