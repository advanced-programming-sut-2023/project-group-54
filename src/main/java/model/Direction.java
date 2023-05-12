package model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Direction {
    UP(false),
    DOWN(false),
    RIGHT(false),
    LEFT(false),
    N(true),
    S(true),
    E(true),
    W(true),
    F(false),
    R(true);

    public boolean isForRock() {
        return isForRock;
    }

    public ArrayList<Direction> getAllDirections() {
        return allDirections;
    }
    private boolean isForRock;

    Direction(boolean isForRock) {
        this.isForRock = isForRock;
    }

    private final ArrayList<Direction> allDirections = new ArrayList<>(Arrays.asList(Direction.values()));


}
