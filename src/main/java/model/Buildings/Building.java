package model.Buildings;

import model.Map;

import java.util.ArrayList;
import java.util.HashMap;

public class Building {
    private static ArrayList<Building> buildings;
    private BuildingType buildingType;
    private int hp;
    public Building(BuildingType buildingType, int maxHp) {

    }

    public static ArrayList<Building> getBuildings() {
        return buildings;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
