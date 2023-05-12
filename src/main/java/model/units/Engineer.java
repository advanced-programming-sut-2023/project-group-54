package model.units;

import model.Buildings.Building;
import model.Buildings.SiegeType;
import model.User;

public class Engineer extends Unit{
    private Boolean hasOil;
    private Building buildingWhichWorks;
    private boolean isWorking;

    public Engineer(int xPosition, int yPosition) {
        super(UnitType.ENGINEER, xPosition, yPosition);
        this.hasOil = false;
        this.isWorking = false;
    }

    public Building getBuildingWhichWorks() {
        return buildingWhichWorks;
    }

    public void setBuildingWhichWorks(Building buildingWhichWorks) {
        this.buildingWhichWorks = buildingWhichWorks;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public void pourOil(){

    }

    public void makeSiege(SiegeType siegeType){

    }
}
