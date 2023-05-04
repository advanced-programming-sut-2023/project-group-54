package model.Buildings;

import model.Government;

import java.util.ArrayList;

public class Building {
    private static ArrayList<Building> buildings;
    private BuildingType buildingType;
    private int hp;
    private Government owner;

    public Building(BuildingType buildingType, int maxHp,Government owner) {
        this.buildingType=buildingType;
        this.hp=maxHp;
        this.owner=owner;
        buildings.add(this);
    }

    public static ArrayList<Building> getBuildings() {
        return buildings;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
