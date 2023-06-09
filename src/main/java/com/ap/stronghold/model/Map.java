package com.ap.stronghold.model;

import com.ap.stronghold.model.Buildings.Building;
import com.ap.stronghold.model.units.Unit;

import java.util.ArrayList;

public class Map {
    int x;
    int y;
    private Building building;
    private MapType tree;
    private ArrayList<Unit> unit;
    private MapType mapType;

    public Map(MapType mapType, int x, int y) {
        this.mapType = mapType;
        this.unit = new ArrayList<>();
        this.tree = null;
        this.building = null;
        this.x = x;
        this.y = y;
    }

    public Map(int x, int y) {
        this.mapType = MapType.EARTH;
        this.unit = new ArrayList<>();
        this.tree = null;
        this.building = null;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addUnit(Unit unit) {
        this.unit.add(unit);
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public MapType getTree() {
        return tree;
    }

    public void setTree(MapType tree) {
        this.tree = tree;
    }

    public ArrayList<Unit> getUnit() {
        return unit;
    }

    public MapType getMapType() {
        return mapType;
    }

    public void setMapType(MapType mapType) {
        this.mapType = mapType;
    }

    public void setUnit() {
        this.unit = new ArrayList<>();
    }
}
