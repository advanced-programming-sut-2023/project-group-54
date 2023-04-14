package model.Buildings;

import model.Resources;

import java.util.HashMap;

public class Building {
    private HashMap<Integer, Resources> costs;
    private int workers;
    private int maxHp;
    private int hp;
    private BuildingGroup buildingGroup;
}
