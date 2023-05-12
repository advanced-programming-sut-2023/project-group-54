package model.Buildings;

import model.Map;
import model.User;

import java.util.HashMap;

public class Building {
    private static HashMap<Building, Integer> buildings;
    private BuildingType buildingType;
    private int hp;
    private Map[][] mapPositions = new Map[buildingType.getWidth()][buildingType.getLength()];
    private User owner;
    public Building(BuildingType buildingType, int maxHp,User owner) {

    }

    public static HashMap<Building,Integer> getBuildings() {
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

    public User getOwner() {
        return owner;
    }
}
