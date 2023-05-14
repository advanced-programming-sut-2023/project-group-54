package model.units;

import model.Game;
import model.Government;
import model.Map;

import java.util.ArrayList;

public class Unit {
    private static ArrayList<Unit> units = new ArrayList<>();
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
        this.xTarget = -1;
        this.yTarget = -1;
        Map map = Game.getGameMapXY(xPosition, yPosition);
        map.addUnit(this);
        units.add(this);
    }

    public static ArrayList<Unit> getUnits() {
        return units;
    }

    public static void setUnits(ArrayList<Unit> units) {
        Unit.units = units;
    }

    public void setXTarget(int xTarget) {
        this.xTarget = xTarget;
    }

    public void setYTarget(int yTarget) {
        this.yTarget = yTarget;
    }

    public void setXMoveTarget(int xMoveTarget) {
        this.xMoveTarget = xMoveTarget;
    }

    public void setYMoveTarget(int yMoveTarget) {
        this.yMoveTarget = yMoveTarget;
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

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
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

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getxMoveTarget() {
        return xMoveTarget;
    }

    public void setxMoveTarget(int xMoveTarget) {
        this.xMoveTarget = xMoveTarget;
    }

    public int getyMoveTarget() {
        return yMoveTarget;
    }

    public void setyMoveTarget(int yMoveTarget) {
        this.yMoveTarget = yMoveTarget;
    }

    public int getPatrolTF() {
        return patrolTF;
    }

    public void setPatrolTF(int patrolTF) {
        this.patrolTF = patrolTF;
    }

    public int getPatrolXTarget() {
        return patrolXTarget;
    }

    public void setPatrolXTarget(int patrolXTarget) {
        this.patrolXTarget = patrolXTarget;
    }

    public int getPatrolYTarget() {
        return patrolYTarget;
    }

    public void setPatrolYTarget(int patrolYTarget) {
        this.patrolYTarget = patrolYTarget;
    }

    public int getPatrolXFrom() {
        return patrolXFrom;
    }

    public void setPatrolXFrom(int patrolXFrom) {
        this.patrolXFrom = patrolXFrom;
    }

    public int getPatrolYFrom() {
        return patrolYFrom;
    }

    public void setPatrolYFrom(int patrolYFrom) {
        this.patrolYFrom = patrolYFrom;
    }

    public int getxTarget() {
        return xTarget;
    }

    public int getyTarget() {
        return yTarget;
    }

    public void setHp2(int hp) {
        this.hp += hp;
    }

}
