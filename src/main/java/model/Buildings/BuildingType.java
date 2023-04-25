package model.Buildings;

import model.MapType;
import model.Resource;
import model.TreeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BuildingType {
    WALL_STAIRS(250, 1, 1, 0, "wall stairs", null, 0, Resource.STONE,
            0.25, false, BuildingGroup.CASTLE),
    LOW_WALL(250, 1, 1, 0, "low wall", null, 0, Resource.STONE,
            0.25, false, BuildingGroup.CASTLE),
    STONE_WALL(800, 1, 1, 0, "stone wall", null, 0, Resource.STONE,
            0.5, false, BuildingGroup.CASTLE),
    CRENELATED_WALL(1000, 1, 1, 0, "wall stairs", null, 0, Resource.STONE,
            0.5, false, BuildingGroup.CASTLE),
    SMALL_STONE_GATE(1000, 5, 5, 0, "small stone gate", null, 0, Resource.STONE,
            10, false, BuildingGroup.CASTLE),
    LARGE_STONE_GATE(2000, 7, 7, 0, "large stone gate", null, 0, Resource.STONE,
            20, false, BuildingGroup.CASTLE),
    DRAW_BRIDGE(-1, 5, 5, 0, "draw bridge", null, 0, Resource.WOOD,
            10, false, BuildingGroup.CASTLE),
    LOOK_OUT_TOWER(250, 3, 3, 0, "look out tower", null, 0, Resource.STONE,
            10, false, BuildingGroup.CASTLE),
    PERIMETER_TOWER(1000, 4, 4, 0, "perimeter tower", null, 0, Resource.STONE,
            10, false, BuildingGroup.CASTLE),
    DEFENCE_TOWER(1200, 5, 5, 0, "defence tower", null, 0, Resource.STONE,
            15, false, BuildingGroup.CASTLE),
    SQUARE_TOWER(1600, 6, 6, 0, "square tower", null, 0, Resource.STONE,
            35, false, BuildingGroup.CASTLE),
    ROUND_TOWER(2000, 6, 6, 0, "round tower", null, 0, Resource.STONE,
            40, false, BuildingGroup.CASTLE),
    ARMOURY(500, 4, 4, 0, "armoury", null, 0, Resource.WOOD,
            5, false, BuildingGroup.CASTLE),
    BARRACKS(500, 10, 10, 0, "barracks", null, 0, Resource.STONE,
            15, false, BuildingGroup.CASTLE),
    MERCENARY_POST(400, 10, 10, 0, "mercenary post", null, 0, Resource.WOOD,
            10, false, BuildingGroup.CASTLE),
    ENGINEERS_GUILD(400, 5, 10, 100, "engineer's guild", null, 0, Resource.WOOD,
            10, false, BuildingGroup.CASTLE),
    KILLING_PIT(-1, 1, 1, 0, "killing pit", null, 0, Resource.WOOD,
            6, false, BuildingGroup.CASTLE),
    OIL_SMELTER(300, 4, 8, 100, "oil smelter", null, 1, Resource.IRON,
            10, true, BuildingGroup.CASTLE),
    PITCH_DITCH(-1, 1, 1, 0, "pitch ditch", null, 0, Resource.PITCH,
            0.4, false, BuildingGroup.CASTLE),
    CAGED_WAR_DOGS(-1, 2, 2, 100, "caged war dogs", null, 0, Resource.WOOD,
            10, false, BuildingGroup.CASTLE),
    SIEGE_TENT(100, 3, 3, 0, "siege tent", null, 1, null,
            0, true, BuildingGroup.CASTLE),
    MANGONEL(100, 2, 3, 50, "mangonel", new ArrayList<>(Arrays.asList(DefenseType.SQUARE_TOWER, DefenseType.ROUND_TOWER)), 2, null,
            0, true, BuildingGroup.CASTLE),
    BALLISTAE(100, 2, 3, 50, "ballistae",  new ArrayList<>(Arrays.asList(DefenseType.SQUARE_TOWER, DefenseType.ROUND_TOWER)), 2, null,
                0, true, BuildingGroup.CASTLE),
    STABLE(300, 6, 6, 400, "stable", null, 0, Resource.WOOD,
            20, false, BuildingGroup.CASTLE),
    APPLE_ORCHARD(200, 11, 11, 0, "apple orchard", new ArrayList<>(Arrays.asList(MapType.THICK_SCRUB,
            MapType.OASIS_GRASS)), 1, Resource.WOOD, 5, false, BuildingGroup.FARM),
    DIARY_FARMER(200, 10, 10, 0, "diary farmer", new ArrayList<>(Arrays.asList(MapType.THICK_SCRUB,
            MapType.OASIS_GRASS)), 1, Resource.WOOD, 10, false, BuildingGroup.FARM),
    HOPS_FARMER(200, 9, 9, 0, "hops farmer", new ArrayList<>(Arrays.asList(MapType.THICK_SCRUB,
            MapType.OASIS_GRASS)), 1, Resource.WOOD, 15, false, BuildingGroup.FARM),
    WHEAT_FARMER(200, 9, 9, 0, "wheat farmer", new ArrayList<>(Arrays.asList(MapType.THICK_SCRUB,
            MapType.OASIS_GRASS)), 1, Resource.WOOD, 15, false, BuildingGroup.FARM),
    HUNTER_POST(200, 11, 11, 0, "hunter post", null, 1, Resource.WOOD,
            5, false, BuildingGroup.FARM),
    BAKERY(300, 4, 4, 0, "bakery", null, 1, Resource.WOOD,
            10, false, BuildingGroup.FOOD_PROCESSING),
    BREWER(300, 4, 4, 0, "brewer", null, 1, Resource.WOOD,
            10, false, BuildingGroup.FOOD_PROCESSING),
    GRANARY(250, 4, 4, 0, "granary", null, 0, Resource.WOOD,
            5, false, BuildingGroup.FOOD_PROCESSING),
    INN(400, 6, 6, 100, "inn", null, 1, Resource.WOOD,
            20, false, BuildingGroup.FOOD_PROCESSING),
    MILL(200, 3, 3, 0, "mill", null, 3, Resource.WOOD,
            20, false, BuildingGroup.FOOD_PROCESSING),
    IRON_MINE(300, 4, 4, 0, "iron mine", new ArrayList<>(List.of(MapType.IRON)), 2, Resource.WOOD,
            20, false, BuildingGroup.INDUSTRY),
    MARKET(200, 5, 5, 0, "market", null, 1, Resource.WOOD,
            5, false, BuildingGroup.INDUSTRY),
    OX_TETHER(100, 2, 2, 0, "ox tether", null, 1, Resource.WOOD,
            5, false, BuildingGroup.INDUSTRY),
    PITCH_RIG(200, 4, 4, 0, "pitch rig", new ArrayList<>(List.of(TreeType.MARSH)), 1, Resource.WOOD,
            20, false, BuildingGroup.INDUSTRY),
    QUARRY(350, 6, 6, 0, "quarry", new ArrayList<>(List.of(MapType.ROCK)), 3, Resource.WOOD,
            20, false, BuildingGroup.INDUSTRY),
    STOCK_PILE(-1, 5, 5, 0, "stock pile", null, 0, null,
            0, false, BuildingGroup.INDUSTRY),
    WOODCUTTER(150, 3, 3, 0, "wood cutter", null, 1, Resource.WOOD,
            3, false, BuildingGroup.INDUSTRY),
    HOVEL(200, 4, 4, 0, "hovel", null, 0, Resource.WOOD,
            6, false, BuildingGroup.TOWN),
    CHURCH(600, 9, 9, 500, "church", null, 0, null,
            0, false, BuildingGroup.TOWN),
    CATHEDRAL(1200, 13, 13, 1000, "cathedral", null, 0, null,
            0, false, BuildingGroup.TOWN),
    ARMOURER(500, 4, 4, 100, "armourer", null, 1, Resource.WOOD,
            20, false, BuildingGroup.WEAPONS),
    BLACKSMITH(500, 4, 4, 200, "blacksmith", null, 1, Resource.WOOD,
            20, false, BuildingGroup.WEAPONS),
    FLETCHER(500, 4, 4, 100, "fletcher", null, 1, Resource.WOOD,
            20, false, BuildingGroup.WEAPONS),
    POLETURNER(500, 4, 4, 100, "poleturner", null, 1, Resource.WOOD,
            10, false, BuildingGroup.WEAPONS);

    private int maxHp;
    private int width;
    private int length;
    private int cost;
    private String name;
    private ArrayList<?> mapTypes;
    private int workers;
    private Resource costType;
    private double costAmount;
    private boolean needEngineer;
    private BuildingGroup buildingGroup;

    BuildingType(int maxHp, int width, int length, int cost, String name, ArrayList<?> mapTypes,
                 int workers, Resource costType, double costAmount, boolean needEngineer, BuildingGroup buildingGroup) {
        this.maxHp = maxHp;
        this.width = width;
        this.length = length;
        this.cost = cost;
        this.name = name;
        this.mapTypes = mapTypes;
        this.workers = workers;
        this.costType = costType;
        this.costAmount = costAmount;
        this.needEngineer = needEngineer;
        this.buildingGroup = buildingGroup;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public ArrayList<?> getMapTypes() {
        return mapTypes;
    }

    public int getWorkers() {
        return workers;
    }

    public Resource getCostType() {
        return costType;
    }

    public double getCostAmount() {
        return costAmount;
    }

    public boolean isNeedEngineer() {
        return needEngineer;
    }

    public BuildingGroup getBuildingGroup() {
        return buildingGroup;
    }
}
