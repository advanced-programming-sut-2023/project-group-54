package com.ap.stronghold.model.Buildings;

import com.ap.stronghold.model.Direction;
import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.Government;

public class SiegeBuilding extends Building{
    private SiegeType siegeType;

    public SiegeBuilding(SiegeType siegeType, int x1Position, int x2Position, int y1Position, int y2Position, Government government) {
        super(siegeType.getBuildingType(), siegeType.getBuildingType().getMaxHp(), government, x1Position, x2Position, y1Position, y2Position);
        this.siegeType = siegeType;
    }
}
