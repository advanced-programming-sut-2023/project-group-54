package model.units;

public class Soldier extends Unit{
    enum State{
        STATIC,
        DEFENSIVE,
        AGGRESSIVE;
    }
    enum SoldierType{
        ;
    }
    private State state;
    private SoldierType soldierType;
    private int attack;
    private int defence;
    private int speed;
    private int attackSpeed;
    private Boolean isAssassins;
    private Boolean hasLadder;
    private Boolean isPatrol;
}
