package model.Buildings;

import model.Game;
import model.units.Unit;

public class TrapBuilding extends Building {
    private TrapType trapType;

    public TrapBuilding(TrapType trapType) {
        super(trapType.getBuildingType(), trapType.getBuildingType().getMaxHp(), Game.getCurrentUser().getGovernment());
        this.trapType = trapType;
    }

    public TrapType getTrapType() {
        return trapType;
    }

    public void firePitchDitch() {
        for (int i = this.x1Position; i < this.x2Position; i++) {
            for (int j = this.y1Position; j < this.y2Position; j++) {
                for (Unit unit : Game.getGameMap()[i][j].getUnit()) {
                    if (!unit.getGovernment().equals(this.getOwner()))
                        unit.setHp(-this.getTrapType().getDamage());
                }
            }
        }
    }
}
