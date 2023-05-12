package model.units;

import model.Game;
import model.Government;
import model.Map;

import java.util.ArrayList;

public class Unit {
    private static ArrayList<Unit> units;
    private final Government owner;
    private int xPosition;
    private int yPosition;
    private int xMoveTarget;
    private int yMoveTarget;
    private State state;
    private UnitType unitType;
    private int hp;
    private Boolean isPatrol;
    private int patrolTF;
    private int patrolXTarget;
    private int patrolYTarget;
    private int patrolXFrom;
    private int patrolYFrom;
    private int xTarget;
    private int yTarget;

    public Unit(UnitType unitType, int xPosition, int yPosition) {
        this.owner = Game.getCurrentUser().getGovernment();
        this.unitType = unitType;
        this.state = State.STATIC;
        this.hp = unitType.getMaxHp();
        this.isPatrol = false;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xMoveTarget = xPosition;
        this.yMoveTarget = yPosition;
        this.xMoveTarget = -1;
        this.yMoveTarget = -1;
        Map map = Game.getGameMapXY(xPosition, yPosition);
        map.addUnit(this);
        units.add(this);
    }

    public static ArrayList<Unit> getUnits() {
        return units;
    }

    public void setXTarget(int xTarget) {
        this.xTarget = xTarget;
    }

    public void setYTarget(int yTarget) {
        this.yTarget = yTarget;
    }

    public void setPatrolTF(int patrolTF) {
        this.patrolTF = patrolTF;
    }

    public void setPatrolXTarget(int patrolXTarget) {
        this.patrolXTarget = patrolXTarget;
    }

    public void setPatrolYTarget(int patrolYTarget) {
        this.patrolYTarget = patrolYTarget;
    }

    public void setPatrolXFrom(int patrolXFrom) {
        this.patrolXFrom = patrolXFrom;
    }

    public void setPatrolYFrom(int patrolYFrom) {
        this.patrolYFrom = patrolYFrom;
    }

    public void setXMoveTarget(int xTarget) {
        this.xMoveTarget = xTarget;
    }

    public void setYMoveTarget(int yTarget) {
        this.yMoveTarget = yTarget;
    }

    public Government getOwner() {
        return owner;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public void setPatrol(Boolean patrol) {
        isPatrol = patrol;
    }

    public Government getGovernment() {
        return owner;
    }
}
