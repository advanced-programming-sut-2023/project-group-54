package model.Buildings;

import model.Resource;

public enum SiegeType {
    CATAPULT(BuildingType.SIEGE_TENT, "catapult", 150, 40, 2, 20, 2, Resource.STONE, 2, true, true, false),
    TREBUCHET(BuildingType.SIEGE_TENT, "trebuchet", 150, 50, 0, 25, 3, Resource.STONE, 2, true, true, false),
    SIEGE_TOWER(BuildingType.SIEGE_TENT, "siege tower", 150, 0, 1, 0, 4, null, 0, false, false, true),
    BATTERING_RAM(BuildingType.SIEGE_TENT, "battering ram", 150, 0, 1, 0, 4, null, 0, false, true, true),
    PORTABLE_SHIELD(BuildingType.SIEGE_TENT, "portable shield", 5, 0, 4, 0, 1, null, 0, false, true, false),
    FIRE_BALLISTA(BuildingType.SIEGE_TENT, "fire ballista", 150, 50, 2, 25, 2, null, 0, false, true, false);

    private BuildingType buildingType;
    private String name;
    private int cost;
    private int damage;
    private int speed;
    private int range;
    private int engineerNeeded;
    private Resource costType;
    private int costAmount;
    private boolean needCost;
    private boolean isAttack;
    private boolean justStructures;

    SiegeType(BuildingType buildingType, String name, int cost, int damage, int speed, int range, int engineerNeeded, Resource costType, int costAmount, boolean needCost, boolean isAttack, boolean justStructures) {
        this.buildingType = buildingType;
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        this.speed = speed;
        this.range = range;
        this.engineerNeeded = engineerNeeded;
        this.costType = costType;
        this.costAmount = costAmount;
        this.needCost = needCost;
        this.isAttack = isAttack;
        this.justStructures = justStructures;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getCost() {
        return cost;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRange() {
        return range;
    }

    public int getEngineerNeeded() {
        return engineerNeeded;
    }

    public Resource getCostType() {
        return costType;
    }

    public int getCostAmount() {
        return costAmount;
    }

    public String getName() {
        return name;
    }

    public boolean isNeedCost() {
        return needCost;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public boolean isJustStructures() {
        return justStructures;
    }
}
