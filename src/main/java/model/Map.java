package model;

import model.Buildings.Building;
import model.units.Unit;

import java.util.ArrayList;

public class Map {
    private Building building;
    private TreeType tree;
    private ArrayList<Unit> unit;
    private MapType mapType;
    public Map(MapType mapType) {
        this.mapType = mapType;
    }
    public Map() {
        this.mapType = MapType.EARTH;
    }


}
