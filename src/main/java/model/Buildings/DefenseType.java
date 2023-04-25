package model.Buildings;

public enum DefenseType {
    WALL_STAIRS(BuildingType.WALL_STAIRS, 0, false, false, false, false),
    LOW_WALL(BuildingType.LOW_WALL, 0, false, false, false, false),
    STONE_WALL(BuildingType.STONE_WALL, 0, false, false, false, false),
    CRENELATED_WALL(BuildingType.CRENELATED_WALL, 0, false, false, false, false),
    SMALL_STONE_GATE(BuildingType.SMALL_STONE_GATE, 5, false, false, true, true),
    LARGE_STONE_GATE(BuildingType.LARGE_STONE_GATE, 5, false, false, true, true),
    DRAW_BRIDGE(BuildingType.DRAW_BRIDGE, 0, false, true, false, false),
    LOOK_OUT_TOWER(BuildingType.LOOK_OUT_TOWER, 20, false, false, true, false),
    PERIMETER_TOWER(BuildingType.PERIMETER_TOWER, 10, false, false, true, false),
    DEFENCE_TOWER(BuildingType.DEFENCE_TOWER, 12, false, false, true, false),
    SQUARE_TOWER(BuildingType.SQUARE_TOWER, 15, true, false, true, false),
    ROUND_TOWER(BuildingType.ROUND_TOWER, 15, true, false, true, false);
    private BuildingType buildingType;
    private int increaseAttackRange;
    private boolean canPutSiege;
    private boolean canOpenOrClose;
    private boolean canClimb;
    private boolean canClimbWithoutStairs;

    DefenseType(BuildingType buildingType, int increaseAttackRange, boolean canPutSiege, boolean canOpenOrClose, boolean canClimb, boolean canClimbWithoutStairs) {
        this.buildingType = buildingType;
        this.increaseAttackRange = increaseAttackRange;
        this.canPutSiege = canPutSiege;
        this.canOpenOrClose = canOpenOrClose;
        this.canClimb = canClimb;
        this.canClimbWithoutStairs = canClimbWithoutStairs;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public int getIncreaseAttackRange() {
        return increaseAttackRange;
    }

    public boolean isCanPutSiege() {
        return canPutSiege;
    }

    public boolean isCanOpenOrClose() {
        return canOpenOrClose;
    }
}