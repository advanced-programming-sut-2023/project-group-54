package model.units;

import model.Game;
import model.Government;
import model.Map;

import java.util.ArrayList;

public class Unit {
    private static ArrayList<Unit> units;
    private final Government owner;
    public int xPosition;
    public int yPosition;
    private State state;
    private UnitType unitType;
    private int hp;
    private Boolean isPatrol;
    public Unit(UnitType unitType, int xPosition, int yPosition) {
        this.owner = Game.getCurrentUser().getGovernment();
        this.unitType = unitType;
        this.state = State.STATIC;
        this.hp = unitType.getMaxHp();
        this.isPatrol = false;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        Map map = Game.getGameMapXY(xPosition, yPosition);
        map.addUnit(this);
        units.add(this);
    }

    public static ArrayList<Unit> getUnits() {
        return units;
    }

    public Government getOwner() {
        return owner;
    }

    public State getState() {
        return state;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public int getHp() {
        return hp;
    }

    public Boolean getPatrol() {
        return isPatrol;
    }

    public Government getGovernment() {
        return owner;
    }

    enum State {
        STATIC,
        DEFENSIVE,
        AGGRESSIVE;
    }
}
