package model.Buildings;

public class TrapBuilding extends Building {
    private TrapType trapType;

    public TrapBuilding(TrapType trapType) {
        super(trapType.getBuildingType(), trapType.getBuildingType().getMaxHp());
        this.trapType = trapType;
    }

    public void firePitchDitch() {

    }
}
