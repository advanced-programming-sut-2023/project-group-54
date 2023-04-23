package model.Buildings;

import model.Direction;

public class SiegeBuilding extends Building{
    private SiegeType siegeType;

    public SiegeBuilding(SiegeType siegeType) {
        super(siegeType.getBuildingType(), siegeType.getBuildingType().getMaxHp());
        this.siegeType = siegeType;
    }
}
