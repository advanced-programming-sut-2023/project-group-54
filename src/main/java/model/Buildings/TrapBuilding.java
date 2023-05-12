package model.Buildings;

import model.Game;

public class TrapBuilding extends Building {
    private TrapType trapType;

    public TrapBuilding(TrapType trapType) {
        super(trapType.getBuildingType(), trapType.getBuildingType().getMaxHp(), Game.getCurrentUser().getGovernment());
        this.trapType = trapType;
    }

    public void firePitchDitch() {

    }
}
