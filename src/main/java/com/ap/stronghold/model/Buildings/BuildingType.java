package com.ap.stronghold.model.Buildings;

import com.ap.stronghold.model.MapType;
import com.ap.stronghold.model.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum BuildingType {
    WALL_STAIRS(250, 1, 1, 0, "wall stairs", null, 0, Resource.STONE,
            0.25, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, false),
    LOW_WALL(250, 1, 1, 0, "low wall", null, 0, Resource.STONE,
            0.25, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, false),
    STONE_WALL(800, 1, 1, 0, "stone wall", null, 0, Resource.STONE,
            0.5, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, false),
    CRENELATED_WALL(1000, 1, 1, 0, "wall stairs", null, 0, Resource.STONE,
            0.5, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, false),
    SMALL_STONE_GATE(1000, 5, 5, 0, "small stone gate", null, 0, Resource.STONE,
            10, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, true),
    LARGE_STONE_GATE(2000, 7, 7, 0, "large stone gate", null, 0, Resource.STONE,
            20, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, true),
    DRAW_BRIDGE(-1, 5, 5, 0, "draw bridge", null, 0, Resource.WOOD,
            10, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, true),
    LOOK_OUT_TOWER(250, 3, 3, 0, "look out tower", null, 0, Resource.STONE,
            10, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, true),
    PERIMETER_TOWER(1000, 4, 4, 0, "perimeter tower", null, 0, Resource.STONE,
            10, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, true),
    DEFENCE_TOWER(1200, 5, 5, 0, "defence tower", null, 0, Resource.STONE,
            15, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, true),
    SQUARE_TOWER(1600, 6, 6, 0, "square tower", null, 0, Resource.STONE,
            35, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, true),
    ROUND_TOWER(2000, 6, 6, 0, "round tower", null, 0, Resource.STONE,
            40, false, BuildingGroup.CASTLE, BuildingGroup.DEFENSE_BUILDING, true),
    ARMOURY(500, 4, 4, 0, "armoury", null, 0, Resource.WOOD,
            5, false, BuildingGroup.CASTLE, BuildingGroup.STORAGE_BUILDING, true),
    BARRACKS(500, 10, 10, 0, "barracks", null, 0, Resource.STONE,
            15, false, BuildingGroup.CASTLE, BuildingGroup.PRODUCER_BUILDING, true),
    MERCENARY_POST(400, 10, 10, 0, "mercenary post", null, 0, Resource.WOOD,
            10, false, BuildingGroup.CASTLE, BuildingGroup.PRODUCER_BUILDING, true),
    ENGINEERS_GUILD(400, 5, 10, 100, "engineer's guild", null, 0, Resource.WOOD,
            10, false, BuildingGroup.CASTLE, BuildingGroup.PRODUCER_BUILDING, true),
    KILLING_PIT(-1, 1, 1, 0, "killing pit", null, 0, Resource.WOOD,
            6, false, BuildingGroup.CASTLE, BuildingGroup.TRAP_BUILDING, true),
    OIL_SMELTER(300, 4, 8, 100, "oil smelter", null, 1, Resource.IRON,
            10, true, BuildingGroup.CASTLE, BuildingGroup.OTHERS, true),
    PITCH_DITCH(-1, 1, 1, 0, "pitch ditch", null, 0, Resource.PITCH,
            0.4, false, BuildingGroup.CASTLE, BuildingGroup.TRAP_BUILDING, true),
    CAGED_WAR_DOGS(-1, 2, 2, 100, "caged war dogs", null, 0, Resource.WOOD,
            10, false, BuildingGroup.CASTLE, BuildingGroup.OTHERS, true),
    SIEGE_TENT(100, 3, 3, 0, "siege tent", null, 1, null,
            0, true, BuildingGroup.CASTLE, BuildingGroup.OTHERS, true),
    MANGONEL(100, 2, 3, 50, "mangonel", new ArrayList<>(Arrays.asList(DefenseType.SQUARE_TOWER, DefenseType.ROUND_TOWER)), 2, null,
            0, true, BuildingGroup.CASTLE, BuildingGroup.OTHERS, true),
    BALLISTAE(100, 2, 3, 50, "ballistae", new ArrayList<>(Arrays.asList(DefenseType.SQUARE_TOWER, DefenseType.ROUND_TOWER)), 2, null,
            0, true, BuildingGroup.CASTLE, BuildingGroup.OTHERS, true),
    STABLE(300, 6, 6, 400, "stable", null, 0, Resource.WOOD,
            20, false, BuildingGroup.CASTLE, BuildingGroup.PRODUCER_BUILDING, true),
    APPLE_ORCHARD(200, 11, 11, 0, "apple orchard", new ArrayList<>(Arrays.asList(MapType.THICK_SCRUB,
            MapType.OASIS_GRASS)), 1, Resource.WOOD, 5, false, BuildingGroup.FARM, BuildingGroup.PRODUCER_BUILDING, true),
    DIARY_FARMER(200, 10, 10, 0, "diary farmer", new ArrayList<>(Arrays.asList(MapType.THICK_SCRUB,
            MapType.OASIS_GRASS)), 1, Resource.WOOD, 10, false, BuildingGroup.FARM, BuildingGroup.PRODUCER_BUILDING, true),
    HOPS_FARMER(200, 9, 9, 0, "hops farmer", new ArrayList<>(Arrays.asList(MapType.THICK_SCRUB,
            MapType.OASIS_GRASS)), 1, Resource.WOOD, 15, false, BuildingGroup.FARM, BuildingGroup.PRODUCER_BUILDING, true),
    WHEAT_FARMER(200, 9, 9, 0, "wheat farmer", new ArrayList<>(Arrays.asList(MapType.THICK_SCRUB,
            MapType.OASIS_GRASS)), 1, Resource.WOOD, 15, false, BuildingGroup.FARM, BuildingGroup.PRODUCER_BUILDING, true),
    HUNTER_POST(200, 11, 11, 0, "hunter post", null, 1, Resource.WOOD,
            5, false, BuildingGroup.FARM, BuildingGroup.PRODUCER_BUILDING, true),
    BAKERY(300, 4, 4, 0, "bakery", null, 1, Resource.WOOD,
            10, false, BuildingGroup.FOOD_PROCESSING, BuildingGroup.PRODUCER_BUILDING, true),
    BREWER(300, 4, 4, 0, "brewer", null, 1, Resource.WOOD,
            10, false, BuildingGroup.FOOD_PROCESSING, BuildingGroup.PRODUCER_BUILDING, true),
    GRANARY(250, 4, 4, 0, "granary", null, 0, null,
            0, false, BuildingGroup.FOOD_PROCESSING, BuildingGroup.STORAGE_BUILDING, true),
    INN(400, 6, 6, 100, "inn", null, 1, Resource.WOOD,
            20, false, BuildingGroup.FOOD_PROCESSING, BuildingGroup.PRODUCER_BUILDING, true),
    MILL(200, 3, 3, 0, "mill", null, 3, Resource.WOOD,
            20, false, BuildingGroup.FOOD_PROCESSING, BuildingGroup.PRODUCER_BUILDING, true),
    IRON_MINE(300, 4, 4, 0, "iron mine", new ArrayList<>(List.of(MapType.IRON)), 2, Resource.WOOD,
            20, false, BuildingGroup.INDUSTRY, BuildingGroup.PRODUCER_BUILDING, true),
    MARKET(200, 5, 5, 0, "market", null, 1, Resource.WOOD,
            5, false, BuildingGroup.INDUSTRY, BuildingGroup.SHOP_BUILDING, true),
    OX_TETHER(100, 2, 2, 0, "ox tether", null, 1, Resource.WOOD,
            5, false, BuildingGroup.INDUSTRY, BuildingGroup.OTHERS, true),
    PITCH_RIG(200, 4, 4, 0, "pitch rig", new ArrayList<>(List.of(MapType.SMALL_POND)), 1, Resource.WOOD,
            20, false, BuildingGroup.INDUSTRY, BuildingGroup.PRODUCER_BUILDING, true),
    QUARRY(350, 6, 6, 0, "quarry", new ArrayList<>(List.of(MapType.BOULDERS)), 3, Resource.WOOD,
            20, false, BuildingGroup.INDUSTRY, BuildingGroup.PRODUCER_BUILDING, true),
    STOCK_PILE(-1, 5, 5, 0, "stock pile", null, 0, null,
            0, false, BuildingGroup.INDUSTRY, BuildingGroup.STORAGE_BUILDING, true),
    WOODCUTTER(150, 3, 3, 0, "wood cutter", null, 1, Resource.WOOD,
            3, false, BuildingGroup.INDUSTRY, BuildingGroup.PRODUCER_BUILDING, true),
    HOVEL(200, 4, 4, 0, "hovel", null, 0, Resource.WOOD,
            6, false, BuildingGroup.TOWN, BuildingGroup.OTHERS, true),
    CHURCH(600, 9, 9, 500, "church", null, 0, null,
            0, false, BuildingGroup.TOWN, BuildingGroup.PRODUCER_BUILDING, true),
    CATHEDRAL(1200, 13, 13, 1000, "cathedral", null, 0, null,
            0, false, BuildingGroup.TOWN, BuildingGroup.PRODUCER_BUILDING, true),
    ARMOURER(500, 4, 4, 100, "armourer", null, 1, Resource.WOOD,
            20, false, BuildingGroup.WEAPONS, BuildingGroup.PRODUCER_BUILDING, true),
    BLACKSMITH(500, 4, 4, 200, "blacksmith", null, 1, Resource.WOOD,
            20, false, BuildingGroup.WEAPONS, BuildingGroup.PRODUCER_BUILDING, true),
    FLETCHER(500, 4, 4, 100, "fletcher", null, 1, Resource.WOOD,
            20, false, BuildingGroup.WEAPONS, BuildingGroup.PRODUCER_BUILDING, true),
    POLETURNER(500, 4, 4, 100, "poleturner", null, 1, Resource.WOOD,
            10, false, BuildingGroup.WEAPONS, BuildingGroup.PRODUCER_BUILDING, true),
    MAIN_HOUSE(6000, 7, 7, 0, "main house", null, 0, null,
            0, false, BuildingGroup.OTHERS, BuildingGroup.OTHERS, true);

    private final int maxHp;
    private final int width;
    private final int length;
    private final int cost;
    private final String name;
    private final ArrayList<?> mapTypes;
    private final int workers;
    private final Resource costType;
    private final double costAmount;
    private final boolean needEngineer;
    private final BuildingGroup buildingGroup;
    private final BuildingGroup buildingGroup2;
    private final boolean canBePassed;

    BuildingType(int maxHp, int width, int length, int cost, String name, ArrayList<?> mapTypes,
                 int workers, Resource costType, double costAmount, boolean needEngineer, BuildingGroup buildingGroup, BuildingGroup buildingGroup2, boolean canBePassed) {
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
        this.buildingGroup2 = buildingGroup2;
        this.canBePassed = canBePassed;
    }

    public boolean isCanBePassed() {
        return canBePassed;
    }

    public BuildingGroup getBuildingGroup2() {
        return buildingGroup2;
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
