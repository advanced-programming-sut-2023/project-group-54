package model.Buildings;

public enum StorageType {
    GRANARY(BuildingType.GRANARY, 250), // انبار غذا
    STOCK_PILE(BuildingType.STOCK_PILE, 200), //انبار
    ARMOURY(BuildingType.ARMOURY, 50); //اسلحه خانه
    private int capacity;
    private BuildingType buildingType;

    StorageType(BuildingType buildingType, int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }
}
