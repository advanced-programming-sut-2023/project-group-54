package controller;

import model.Buildings.*;
import model.Game;
import model.Resource;
import model.units.Engineer;
import model.units.Unit;
import model.units.UnitType;
import view.enums.messages.BuildingMenuMessage;

import java.util.ArrayList;

public class BuildingMenuController {
    private static Building selectedBuilding;

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
        ArrayList<Unit> units = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            if (!unitType.equals(UnitType.ENGINEER)) {
                units.add(new Unit(unitType, selectedBuilding.getX1Position(), selectedBuilding.getY1Position()));
            } else {
                units.add(new Engineer(selectedBuilding.getX1Position(), selectedBuilding.getY1Position()));
            }
        }
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage taxRateSet(int rateNumber) {
        if (selectedBuilding.getBuildingType().equals(BuildingType.SMALL_STONE_GATE) || selectedBuilding.getBuildingType().equals(BuildingType.LARGE_STONE_GATE))
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
        if(selectedBuilding.getBuildingType().equals(BuildingType.GRANARY)){
            DefenseBuilding defenseBuilding = (DefenseBuilding) selectedBuilding;
            defenseBuilding.setIsOpen(true);
        }
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage closeGate() {
        if(selectedBuilding.getBuildingType().equals(BuildingType.GRANARY)){
            DefenseBuilding defenseBuilding = (DefenseBuilding) selectedBuilding;
            defenseBuilding.setIsOpen(false);
        }
        return BuildingMenuMessage.SUCCESS;
    }

    public static BuildingMenuMessage changeOutput(Resource resource) {
        if(!(selectedBuilding instanceof ProducerBuilding))
            return BuildingMenuMessage.INVALID_BUILDING;
        ProducerBuilding producerBuilding = (ProducerBuilding) selectedBuilding;
        if(producerBuilding.changeOutput(resource))
            return BuildingMenuMessage.SUCCESS;
        else return BuildingMenuMessage.INVALID_OUTPUT;
    }
}
