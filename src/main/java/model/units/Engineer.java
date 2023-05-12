package model.units;

import model.Buildings.SiegeType;
import model.User;

public class Engineer extends Unit{
    private Boolean hasOil;

    public Engineer(int xPosition, int yPosition) {
        super(UnitType.ENGINEER, xPosition, yPosition);
        this.hasOil = false;
    }

    public void pourOil(){

    }

    public void makeSiege(SiegeType siegeType){

    }
}
