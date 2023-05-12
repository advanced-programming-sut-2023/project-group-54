package model.Buildings;

import model.Direction;
import model.Game;

public class SiegeBuilding extends Building{
    private SiegeType siegeType;

    public SiegeBuilding(SiegeType siegeType) {
        super(siegeType.getBuildingType(), siegeType.getBuildingType().getMaxHp(), Game.getCurrentUser().getGovernment());
        this.siegeType = siegeType;
    }
}
