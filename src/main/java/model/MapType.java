package model;

import java.util.ArrayList;
import java.util.Arrays;

public enum MapType {
    EARTH(true,false,false,false,Direction.F),
    EARTH_AND_STONE(true,false,false,false,Direction.F),
    BOULDERS(true,false,false,false,Direction.F),
    ROCK(false,false,true,false,Direction.R),
    IRON(true,false,false,false,Direction.F),
    OASIS_GRASS(true,false,false,false,Direction.F),
    SCRUB(true,false,false,false,Direction.F),
    THICK_SCRUB(true,false,false,false,Direction.F),
    OIL(true,false,false,false,Direction.F),
    PLAIN(true,false,false,false,Direction.F),
    SHALLOW_WATER(true,false,false,false,Direction.F),
    RIVER(true,false,false,false,Direction.F),
    SMALL_POND(true,false,false,false,Direction.F),
    BIG_POUND(true,false,false,false,Direction.F),
    BEACH(true,false,false,false,Direction.F),
    SEA(true,false,false,false,Direction.F),
    DATE_PALM(false,true,false,false,Direction.F),
    COCONUT_PALM(false,true,false,false,Direction.F),
    OLIVE_TREE(false,true,false,false,Direction.F),
    CHERRY_PALM(false,true,false,false,Direction.F),
    DESERT_SHRUB(false,true,false,false,Direction.F),
    DEFAULT(false,false,false,false,Direction.F);

    private boolean inTextureCommand;
    private boolean isTree;
    private boolean isRock;
    private boolean canBePassed;
    private Direction direction;
    private final ArrayList<MapType> allMapTypes = new ArrayList<>(Arrays.asList(MapType.values()));

    MapType(boolean inTextureCommand, boolean isTree, boolean isRock, boolean canBePassed, Direction direction) {
        this.inTextureCommand = inTextureCommand;
        this.isTree = isTree;
        this.isRock = isRock;
        this.canBePassed = canBePassed;
        this.direction = direction;
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
