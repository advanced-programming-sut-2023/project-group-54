package controller;

import model.Buildings.Building;
import model.Resource;
import model.units.Unit;
import view.enums.messages.BuildingMenuMessage;

public class BuildingMenuController {
    private Building selectedBuilding;

    public BuildingMenuMessage repair() {
        return BuildingMenuMessage.SUCCESS;
    }

    public BuildingMenuMessage createUnit(Unit unit, int count) {
        return BuildingMenuMessage.SUCCESS;
    }

    public BuildingMenuMessage taxRateSet(int rateNumber) {
        return BuildingMenuMessage.SUCCESS;
    }

    public BuildingMenuMessage foodRateSet(int rateNumber) {
        return BuildingMenuMessage.SUCCESS;
    }

    public BuildingMenuMessage openGate(){
        return BuildingMenuMessage.SUCCESS;
    }

    public BuildingMenuMessage changeOutput(Resource resource){
        return BuildingMenuMessage.SUCCESS;
    }
}
