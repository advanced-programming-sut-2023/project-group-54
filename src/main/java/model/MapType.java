package model;

import java.util.ArrayList;
import java.util.Arrays;

public enum MapType {
    EARTH(true,false),
    EARTH_AND_STONE,
    BOULDERS,
    ROCK,
    IRON,
    OASIS_GRASS,
    SCRUB,
    THICK_SCRUB,
    OIL,
    PLAIN,
    SHALLOW_WATER,
    RIVER,
    SMALL_POND,
    BIG_POUND,
    BEACH,
    SEA,
    DATE_PALM,
    COCONUT_PALM,
    OLIVE_TREE,
    CHERRY_PALM,
    DESERT_SHRUB,
    DEFAULT;
    private final ArrayList<MapType> allMapTypes = new ArrayList<>(Arrays.asList(MapType.values()));

    public ArrayList<MapType> getAllMapTypes() {
        return allMapTypes;
    }
}
