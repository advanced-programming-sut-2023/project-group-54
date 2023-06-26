package com.ap.stronghold.controller;

import com.ap.stronghold.model.Buildings.SiegeType;
import com.ap.stronghold.model.Direction;
import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.units.Engineer;
import com.ap.stronghold.model.units.State;
import com.ap.stronghold.model.units.Unit;
import com.ap.stronghold.view.enums.messages.UnitMenuMessage;

import java.util.ArrayList;

public class UnitMenuController {
    private static final ArrayList<Unit> selectedUnit = new ArrayList<>();

    public static ArrayList<Unit> getSelectedUnit() {
        return selectedUnit;
    }

    public static void setSelectedUnit(int x, int y) {
        for (Unit unit : Game.getGameMap()[x][y].getUnit()) {
            if (unit.getGovernment().equals(Game.getCurrentUser().getGovernment()))
                selectedUnit.add(unit);
        }
    }

    public static void setSelectedUnit(ArrayList<Unit> units) {
        selectedUnit.clear();
        selectedUnit.addAll(units);
    }

    public static UnitMenuMessage moveUnit(int xCoordinate, int yCoordinate) {
        //check if out of range
        for (Unit unit : selectedUnit) {
            unit.setXMoveTarget(xCoordinate);
            unit.setYMoveTarget(yCoordinate);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage patrolUnit(int xCoordinate1, int yCoordinate1, int xCoordinate2, int yCoordinate2) {
        for (int i = xCoordinate1; i < xCoordinate2; i++) {
            for (int j = yCoordinate1; j < yCoordinate2; j++) {
                if (!Game.getGameMap()[i][j].getMapType().isCanBePassed()) return UnitMenuMessage.CAN_NOT_PASS_THAT_MAP_TYPE;
                if (Game.getGameMap()[i][j].getBuilding() != null)
                    return UnitMenuMessage.THERE_ARE__BUILDING_ON_THAT_WAY;
            }
        }
        for (Unit unit : selectedUnit) {
            unit.setPatrol(true);
            unit.setPatrolTF(0);
            unit.setXMoveTarget(xCoordinate1);
            unit.setYMoveTarget(yCoordinate1);
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

    public static UnitMenuMessage attack(int xCoordinate, int yCoordinate) {
        for (Unit unit : selectedUnit) {
            unit.setXTarget(xCoordinate);
            unit.setYTarget(yCoordinate);
            if (unit.getUnitType().getRange() != 1) {
                unit.setxMoveTarget(xCoordinate - unit.getUnitType().getRange() + 1);
                unit.setyMoveTarget(yCoordinate - unit.getUnitType().getRange() + 1);
            } else {
                unit.setxMoveTarget(xCoordinate);
                unit.setyMoveTarget(yCoordinate);
            }
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage pourOil(Direction direction) {
        boolean isEngineer = true;
        for (Unit unit : selectedUnit) {
            if(!(unit instanceof Engineer)){
                isEngineer = false;
                break;
            }
        }
        if(!isEngineer)
            return UnitMenuMessage.INVALID_UNIT;
        for (Unit unit : selectedUnit) {
            Engineer engineer = (Engineer) unit;
            engineer.setPour(direction);
//            engineer.setHasOil(true);
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage buildEquipment(SiegeType siegeType) {
        boolean isEngineer = true;
        for (Unit unit : selectedUnit) {
            if(!(unit instanceof Engineer)){
                isEngineer = false;
                break;
            }
        }
        if(!isEngineer)
            return UnitMenuMessage.INVALID_UNIT;
        int count = 0;
        for (Unit unit : selectedUnit) {
            Engineer engineer = (Engineer) unit;
            engineer.setSiegeTypeToBuild(siegeType);
            count++;
            if(count == siegeType.getEngineerNeeded()){
                break;
            }
        }
        return UnitMenuMessage.SUCCESS;
    }

    public static UnitMenuMessage disbandUnit() {
        for (Unit unit : selectedUnit) {
            Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getUnit().remove(unit);
            Unit.getUnits().remove(unit);
            Game.getCurrentUser().getGovernment().setPopulation2(1);
            Game.getCurrentUser().getGovernment().addUnemployedWorker(1);
        }
        return UnitMenuMessage.SUCCESS;
    }
}
