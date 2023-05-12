package model.Buildings;

public enum StorageType {
    GRANARY(BuildingType.GRANARY, 250), // انبار غذا
    STOCK_PILE(BuildingType.STOCK_PILE, 200), //انبار
    ARMOURY(BuildingType.ARMOURY, 50); //اسلحه خانه
    private final double capacity;
    private BuildingType buildingType;

    StorageType(BuildingType buildingType, double capacity) {
        this.capacity = capacity;
        this.buildingType = buildingType;
    }

    public double getCapacity() {
        return capacity;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
