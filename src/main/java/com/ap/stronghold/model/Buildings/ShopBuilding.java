package com.ap.stronghold.model.Buildings;

import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.Government;
import com.ap.stronghold.model.Resource;

public class ShopBuilding extends Building{
    public ShopBuilding(int x1Position, int x2Position, int y1Position, int y2Position, Government government) {
        super(BuildingType.MARKET, BuildingType.MARKET.getMaxHp(), government, x1Position, x2Position, y1Position, y2Position);
    }
    public void buy(Resource resource) {

    }

    public void sell(Resource resource) {

    }
}
