package model.units;

import model.Buildings.SiegeType;

public class Engineer extends Unit{
    private Boolean hasOil;

    public Engineer() {
        super(UnitType.ENGINEER, UnitType.ENGINEER.getMaxHp());
        this.hasOil = false;
    }

    public void pourOil(){

    }

    public void makeSiege(SiegeType siegeType){

    }
}
