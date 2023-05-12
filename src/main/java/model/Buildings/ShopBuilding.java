package model.Buildings;

import model.Game;
import model.Resource;

public class ShopBuilding extends Building{
    public ShopBuilding() {
        super(BuildingType.MARKET, BuildingType.MARKET.getMaxHp(), Game.getCurrentUser().getGovernment());
    }
    public void buy(Resource resource) {

    }

    public void sell(Resource resource) {

    }
}
