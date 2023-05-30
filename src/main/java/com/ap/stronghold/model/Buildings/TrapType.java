package com.ap.stronghold.model.Buildings;

public enum TrapType {
    KILLING_PIT(BuildingType.KILLING_PIT,100000),
    PITCH_DITCH(BuildingType.PITCH_DITCH,1000);
    private BuildingType buildingType;
    private int damage;

    TrapType(BuildingType buildingType,int damage) {
        this.buildingType = buildingType;
        this.damage = damage;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getDamage() {
        return damage;
    }
}
