package model;

import java.util.ArrayList;
import java.util.Arrays;

public enum MapType {
    EARTH(true,false,false,false),
    EARTH_AND_STONE(true,false,false,false),
    BOULDERS(true,false,false,false),
    ROCK_N(false,false,true,false),
    ROCK_S(false,false,true,false),
    ROCK_E(false,false,true,false),
    ROCK_W(false,false,true,false),
    IRON(true,false,false,false),
    OASIS_GRASS(true,false,false,false),
    SCRUB(true,false,false,false),
    THICK_SCRUB(true,false,false,false),
    OIL(true,false,false,false),
    PLAIN(true,false,false,false),
    SHALLOW_WATER(true,false,false,false),
    RIVER(true,false,false,false),
    SMALL_POND(true,false,false,false),
    BIG_POUND(true,false,false,false),
    BEACH(true,false,false,false),
    SEA(true,false,false,false),
    DATE_PALM(false,true,false,false),
    COCONUT_PALM(false,true,false,false),
    OLIVE_TREE(false,true,false,false),
    CHERRY_PALM(false,true,false,false),
    DESERT_SHRUB(false,true,false,false),
    DEFAULT(false,false,false,false);

    private boolean inTextureCommand;
    private boolean isTree;
    private boolean isRock;
    private boolean canBePassed;
    private final ArrayList<MapType> allMapTypes = new ArrayList<>(Arrays.asList(MapType.values()));

    MapType(boolean inTextureCommand, boolean isTree, boolean isRock, boolean canBePassed) {
        this.inTextureCommand = inTextureCommand;
        this.isTree = isTree;
        this.isRock = isRock;
        this.canBePassed = canBePassed;
    }

    public boolean isInTextureCommand() {
        return inTextureCommand;
    }

    public boolean isTree() {
        return isTree;
    }

    public boolean isRock() {
        return isRock;
    }

    public boolean isCanBePassed() {
        return canBePassed;
    }

    public ArrayList<MapType> getAllMapTypes() {
        return allMapTypes;
    }
}
