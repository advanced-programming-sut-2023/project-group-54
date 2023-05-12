package model.units;

import model.User;

import java.util.ArrayList;

public class Unit {
    private static ArrayList<Unit> units;
    private final User owner;
    enum State{
        STATIC,
        DEFENSIVE,
        AGGRESSIVE;
    }
    private State state;
    private UnitType unitType;
    private int hp;
    private Boolean isPatrol;

    public static ArrayList<Unit> getUnits() {
        return units;
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

    public User getOwner() {
        return owner;
    }

    public Unit(User owner, UnitType unitType, int hp) {
        this.owner = owner;
        this.unitType = unitType;
        this.state = State.STATIC;
        this.hp = hp;
        this.isPatrol = false;
    }
}
