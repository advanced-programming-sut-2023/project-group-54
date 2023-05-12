package model.Buildings;

import model.Game;
import model.units.Unit;

import java.util.ArrayList;

public class DefenseBuilding extends Building{
    private DefenseType defenseType;
    private boolean isOpen;
    private ArrayList<Unit> climbedUnit;
    public DefenseBuilding(DefenseType defenseType) {
        super(defenseType.getBuildingType(), defenseType.getBuildingType().getMaxHp(), Game.getCurrentUser().getGovernment());
        this.defenseType = defenseType;
        this.isOpen = true;
        this.climbedUnit = new ArrayList<>();
    }
}
