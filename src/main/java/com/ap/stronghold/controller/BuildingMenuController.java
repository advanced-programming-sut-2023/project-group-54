package com.ap.stronghold.controller;

import com.ap.stronghold.model.Buildings.*;
import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.Resource;
import com.ap.stronghold.model.units.Engineer;
import com.ap.stronghold.model.units.Unit;
import com.ap.stronghold.model.units.UnitType;
import com.ap.stronghold.view.enums.messages.BuildingMenuMessage;

public class BuildingMenuController {
    private static Building selectedBuilding;

    public static void setSelectedBuilding(int x, int y) {
        selectedBuilding = Game.getGameMap()[x][y].getBuilding();
    }
    public static void setSelectedBuilding(Building building) {
        selectedBuilding = building;
    }

    public static BuildingMenuMessage repair() {
        if (selectedBuilding.getBuildingType().getBuildingGroup().equals(BuildingGroup.CASTLE))
            selectedBuilding.setHp(selectedBuilding.getBuildingType().getMaxHp());
        else return BuildingMenuMessage.INVALID_BUILDING;
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage createUnit(String type, int count) {
        UnitType unitType = null;
        for (UnitType value : UnitType.values()) {
            if (value.getType().equals(type))
                unitType = value;
        }
        if (unitType == null)
            return BuildingMenuMessage.INVALID_TYPE;
        if (!unitType.getProducerBuilding().getBuildingType().equals(selectedBuilding.getBuildingType())) {
            return BuildingMenuMessage.INVALID_BUILDING;
        }
        for (int i = 0; i < count; i++) {
            if (Game.getCurrentUser().getGovernment().getGold() - unitType.getGoldNeeded() < 0)
                return BuildingMenuMessage.NOT_ENOUGH_GOLD;

            boolean enoughResource = true;
            for (Resource resource : unitType.getResourcesNeeded())
                if (!Game.getCurrentUser().getGovernment().hasEnoughItem(resource, 1)) enoughResource = false;

            if(!enoughResource)
                return BuildingMenuMessage.NOT_ENOUGH_RESOURCE;

            Game.getCurrentUser().getGovernment().setGold(Game.getCurrentUser().getGovernment().getGold() - unitType.getGoldNeeded());
            for (Resource resource : unitType.getResourcesNeeded()) {
                Game.getCurrentUser().getGovernment().removeFromStorage(resource, 1);
                Game.getCurrentUser().getGovernment().changeResourceAmount(resource, -1);
            }
            if (!unitType.equals(UnitType.ENGINEER)) {
                new Unit(unitType, selectedBuilding.getX1Position(), selectedBuilding.getY1Position());
            } else {
                new Engineer(selectedBuilding.getX1Position(), selectedBuilding.getY1Position());
            }
        }
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage taxRateSet(int rateNumber) {
        if (selectedBuilding.getBuildingType().equals(BuildingType.SMALL_STONE_GATE)
                || selectedBuilding.getBuildingType().equals(BuildingType.LARGE_STONE_GATE)
                || selectedBuilding.getBuildingType().equals(BuildingType.MAIN_HOUSE))
            Game.getCurrentUser().getGovernment().setTaxRate(rateNumber);
        else
            return BuildingMenuMessage.INVALID_BUILDING;
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage foodRateSet(int rateNumber) {
        if (selectedBuilding.getBuildingType().equals(BuildingType.GRANARY))
            Game.getCurrentUser().getGovernment().setFoodRate(rateNumber);
        else
            return BuildingMenuMessage.INVALID_BUILDING;
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage openGate() {
        if (selectedBuilding.getBuildingType().equals(BuildingType.GRANARY)) {
            DefenseBuilding defenseBuilding = (DefenseBuilding) selectedBuilding;
            defenseBuilding.setIsOpen(true);
        }
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage closeGate() {
        if (selectedBuilding.getBuildingType().equals(BuildingType.GRANARY)) {
            DefenseBuilding defenseBuilding = (DefenseBuilding) selectedBuilding;
            defenseBuilding.setIsOpen(false);
        }
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage changeOutput(Resource resource) {
        if (!(selectedBuilding instanceof ProducerBuilding))
            return BuildingMenuMessage.INVALID_BUILDING;
        ProducerBuilding producerBuilding = (ProducerBuilding) selectedBuilding;
        if (producerBuilding.changeOutput(resource))
            return BuildingMenuMessage.SUCCESS;
        else return BuildingMenuMessage.INVALID_OUTPUT;
    }
}
