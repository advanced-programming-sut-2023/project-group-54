package model.Buildings;

public enum StorageType {
    GRANARY(BuildingType.GRANARY, 250.0), // انبار غذا
    STOCK_PILE(BuildingType.STOCK_PILE, 200.0), //انبار
    ARMOURY(BuildingType.ARMOURY, 50.0); //اسلحه خانه
    private final double capacity;
    private BuildingType buildingType;

    StorageType(BuildingType buildingType, double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
