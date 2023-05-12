package model;

import java.util.ArrayList;
import java.util.Arrays;

public enum MapType {
    EARTH(true,false,false,false,true),
    EARTH_AND_STONE(true,false,false,false,true),
    BOULDERS(true,false,false,false,true),
    ROCK_N(false,false,true,false,false),
    ROCK_S(false,false,true,false,false),
    ROCK_E(false,false,true,false,false),
    ROCK_W(false,false,true,false,false),
    IRON(true,false,false,false,true),
    OASIS_GRASS(true,false,false,false,true),
    SCRUB(true,false,false,false,true),
    THICK_SCRUB(true,false,false,false,true),
    OIL(true,false,false,false,false),
    PLAIN(true,false,false,false,true),
    SHALLOW_WATER(true,false,false,false,false),
    RIVER(true,false,false,false,false),
    SMALL_POND(true,false,false,false,true),
    BIG_POUND(true,false,false,false,false),
    BEACH(true,false,false,false,true),
    SEA(true,false,false,false,false),
    DATE_PALM(false,true,false,false,false),
    COCONUT_PALM(false,true,false,false,false),
    OLIVE_TREE(false,true,false,false,false),
    CHERRY_PALM(false,true,false,false,false),
    DESERT_SHRUB(false,true,false,false,false),
    DEFAULT(false,false,false,false,false);

    private boolean inTextureCommand;
    private boolean isTree;
    private boolean isRock;
    private boolean canBePassed;
    private boolean isNormalThingPlacedOn;
    private final ArrayList<MapType> allMapTypes = new ArrayList<>(Arrays.asList(MapType.values()));

    MapType(boolean inTextureCommand, boolean isTree, boolean isRock, boolean canBePassed,boolean isNormalThingPlacedOn) {
        this.inTextureCommand = inTextureCommand;
        this.isTree = isTree;
        this.isRock = isRock;
        this.canBePassed = canBePassed;
        this.isNormalThingPlacedOn = isNormalThingPlacedOn;
    }

    public boolean isNormalThingPlacedOn() {
        return isNormalThingPlacedOn;
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
