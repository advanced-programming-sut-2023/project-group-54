package com.ap.stronghold.model.units;

import com.ap.stronghold.model.Buildings.Building;
import com.ap.stronghold.model.Buildings.SiegeType;
import com.ap.stronghold.model.Direction;

public class Engineer extends Unit {
    private Boolean hasOil;
    private Direction pour;
    private Building buildingWhichWorks;
    private boolean isWorking;
    private SiegeType siegeTypeToBuild;

    public Engineer(int xPosition, int yPosition) {
        super(UnitType.ENGINEER, xPosition, yPosition);
        this.hasOil = false;
        this.pour = null;
        this.isWorking = false;
        this.siegeTypeToBuild = null;
    }

    public SiegeType getSiegeTypeToBuild() {
        return siegeTypeToBuild;
    }

    public void setSiegeTypeToBuild(SiegeType siegeTypeToBuild) {
        this.siegeTypeToBuild = siegeTypeToBuild;
    }

    public Boolean getHasOil() {
        return hasOil;
    }

    public void setHasOil(Boolean hasOil) {
        this.hasOil = hasOil;
    }

    public Direction getPour() {
        return pour;
    }

    public void setPour(Direction pour) {
        this.pour = pour;
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
}
