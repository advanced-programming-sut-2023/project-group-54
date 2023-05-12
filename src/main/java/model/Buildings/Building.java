package model.Buildings;

import model.Game;
import model.Government;
import model.Map;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Building {
    private static ArrayList<Building> buildings;
    private BuildingType buildingType;
    private int hp;
    private Government owner;
    int x1Position;
    int x2Position;
    int y1Position;
    int y2Position;

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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX1Position() {
        return x1Position;
    }

    public int getX2Position() {
        return x2Position;
    }

    public int getY1Position() {
        return y1Position;
    }

    public int getY2Position() {
        return y2Position;
    }
}
