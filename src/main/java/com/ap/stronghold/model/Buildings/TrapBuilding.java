package com.ap.stronghold.model.Buildings;

import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.Government;
import com.ap.stronghold.model.units.Unit;

public class TrapBuilding extends Building {
    private TrapType trapType;

    public TrapBuilding(TrapType trapType, int x1Position, int x2Position, int y1Position, int y2Position, Government government) {
        super(trapType.getBuildingType(), trapType.getBuildingType().getMaxHp(), government, x1Position, x2Position, y1Position, y2Position);
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
