package model.Buildings;

import model.Resource;

public class ShopBuilding extends Building{
    public ShopBuilding() {
        super(BuildingType.MARKET, BuildingType.MARKET.getMaxHp());
    }
    public void buy(Resource resource) {

    }

    public void sell(Resource resource) {

    }
}
