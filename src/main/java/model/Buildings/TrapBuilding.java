package model.Buildings;

import model.*;

public class TrapBuilding extends Building {
    private TrapType trapType;

    public TrapBuilding(TrapType trapType, User owner) {
        super(trapType.getBuildingType(), trapType.getBuildingType().getMaxHp(),owner);
        this.trapType = trapType;
    }

    public void firePitchDitch() {

    }
}
