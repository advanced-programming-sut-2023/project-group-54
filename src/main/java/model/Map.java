package model;

import model.Buildings.Building;
import model.units.Unit;

import java.util.ArrayList;

public class Map {
    private Building building;
    private MapType tree;
    private ArrayList<Unit> unit;
    private MapType mapType;
    public Map(MapType mapType) {
        this.mapType = mapType;
        this.unit = new ArrayList<>();
        this.tree = null;
        this.building = null;
    }
    public Map() {
        this.mapType = MapType.EARTH;
        this.unit = new ArrayList<>();
        this.tree = null;
        this.building = null;
    }

    public Building getBuilding() {
        return building;
    }

    public MapType getTree() {
        return tree;
    }

    public ArrayList<Unit> getUnit() {
        return unit;
    }

    public MapType getMapType() {
        return mapType;
    }
}
