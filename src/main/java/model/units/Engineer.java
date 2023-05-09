package model.units;

import model.Buildings.SiegeType;
import model.User;

public class Engineer extends Unit{
    private Boolean hasOil;

    public Engineer(User owner) {
        super(owner,UnitType.ENGINEER, UnitType.ENGINEER.getMaxHp());
        this.hasOil = false;
    }

    public void pourOil(){

    }

    public void makeSiege(SiegeType siegeType){

    }
}
