package model.Buildings;

import model.Game;
import model.Government;
import model.units.Unit;

import java.util.ArrayList;

public class DefenseBuilding extends Building{
    private DefenseType defenseType;
    private boolean isOpen;
    private ArrayList<Unit> climbedUnit;
    public DefenseBuilding(DefenseType defenseType, int x1Position, int x2Position, int y1Position, int y2Position, Government government) {
        super(defenseType.getBuildingType(), defenseType.getBuildingType().getMaxHp(), government, x1Position, x2Position, y1Position, y2Position);
        this.defenseType = defenseType;
        this.isOpen = true;
        this.climbedUnit = new ArrayList<>();
    }
    public void setIsOpen(boolean isOpen){
        this.isOpen = isOpen;
    }

    public DefenseType getDefenseType() {
        return defenseType;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public ArrayList<Unit> getClimbedUnit() {
        return climbedUnit;
    }
}
