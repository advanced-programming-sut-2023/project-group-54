package model.Buildings;

public enum TrapType {
    KILLING_PIT(BuildingType.KILLING_PIT),
    PITCH_DITCH(BuildingType.PITCH_DITCH);
    private BuildingType buildingType;

    TrapType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
