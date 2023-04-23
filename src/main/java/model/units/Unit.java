package model.units;

import java.util.ArrayList;

public class Unit {
    private static ArrayList<Unit> units;
    enum State{
        STATIC,
        DEFENSIVE,
        AGGRESSIVE;
    }
    private State state;
    private UnitType unitType;
    private int hp;
    private Boolean isPatrol;

    public Unit(UnitType unitType, int hp) {
        this.unitType = unitType;
        this.state = State.STATIC;
        this.hp = hp;
        this.isPatrol = false;
    }
}
