package com.ap.stronghold.model.Buildings;

public enum StorageType {
    GRANARY(250), // انبار غذا
    STOCK_PILE(200), //انبار
    ARMOURY(50); //اسلحه خانه
    private final double capacity;

    StorageType(double capacity) {
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }

    public BuildingType getBuildingType() {
        for (BuildingType value : BuildingType.values()) {
            if(value.name().equals(this.name())){
                return value;
            }
        }
        return null;
    }
}
