package model.Buildings;

import model.Map;

import java.util.HashMap;

public class Building {
    private static HashMap<Building, Integer> buildings;
    private BuildingType buildingType;
    private int hp;
    private Map[][] mapPositions = new Map[buildingType.getWidth()][buildingType.getLength()];
    public Building(BuildingType buildingType, int maxHp) {

    }

    public static HashMap<Building, Integer> getBuildings() {
        return buildings;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getHp() {
        return hp;
    }

    public Map[][] getMapPositions() {
        return mapPositions;
    }
}
