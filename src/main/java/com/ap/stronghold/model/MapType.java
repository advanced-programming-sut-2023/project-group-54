package com.ap.stronghold.model;

public enum MapType {
    EARTH(true,false,false,true,true),
    EARTH_AND_STONE(true,false,false,true,true),
    BOULDERS(true,false,false,true,true),
    ROCK_N(false,false,true,false,false),
    ROCK_S(false,false,true,false,false),
    ROCK_E(false,false,true,false,false),
    ROCK_W(false,false,true,false,false),
    IRON(true,false,false,true,true),
    OASIS_GRASS(true,false,false,true,true),
    SCRUB(true,false,false,true,true),
    THICK_SCRUB(true,false,false,true,true),
    OIL(true,false,false,true,false),
    PLAIN(true,false,false,true,true),
    SHALLOW_WATER(true,false,false,true,false),
    RIVER(true,false,false,false,false),
    SMALL_POND(true,false,false,false,true),
    BIG_POUND(true,false,false,false,false),
    BEACH(true,false,false,true,true),
    SEA(true,false,false,false,false),
    DATE_PALM(false,true,false,false,false),
    COCONUT_PALM(false,true,false,false,false),
    OLIVE_TREE(false,true,false,false,false),
    CHERRY_PALM(false,true,false,false,false),
    DESERT_SHRUB(false,true,false,false,false),
    DEFAULT(false,false,false,false,false);

    private final boolean inTextureCommand;
    private final boolean isTree;
    private final boolean isRock;
    private final boolean canBePassed;
    private final boolean isNormalThingPlacedOn;

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
}
