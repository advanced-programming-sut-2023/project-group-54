package model.Buildings;

import model.Game;
import model.Government;
import model.Resource;

public class ShopBuilding extends Building{
    public ShopBuilding(int x1Position, int x2Position, int y1Position, int y2Position, Government government) {
        super(BuildingType.MARKET, BuildingType.MARKET.getMaxHp(), government, x1Position, x2Position, y1Position, y2Position);
    }
    public void buy(Resource resource) {

    }

    public void sell(Resource resource) {

    }
}
