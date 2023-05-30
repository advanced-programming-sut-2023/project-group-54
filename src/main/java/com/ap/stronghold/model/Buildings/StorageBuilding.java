package com.ap.stronghold.model.Buildings;

import com.ap.stronghold.model.Game;
import com.ap.stronghold.model.Government;
import com.ap.stronghold.model.Resource;

import java.util.HashMap;

public class StorageBuilding extends Building{
    private StorageType storageType;
    private HashMap<Resource, Double> storage;

    public StorageType getStorageType() {
        return storageType;
    }

    public HashMap<Resource, Double> getStorage() {
        return storage;
    }

    public StorageBuilding(StorageType storageType, int x1Position, int x2Position, int y1Position, int y2Position, Government government) {
        super(storageType.getBuildingType(), storageType.getBuildingType().getMaxHp(), government, x1Position, x2Position, y1Position, y2Position);
        this.storageType = storageType;
        this.storage = new HashMap<>();
    }

    public void setFoodRate(int foodRate){
        Game.getCurrentUser().getGovernment().setFoodRate(foodRate);
    }

    public double showStoredResource(Resource resource){
        return storage.get(resource);
    }

    public double getCapacity(){
        double capacity = storageType.getCapacity();
        for (Resource resource : storage.keySet()) {
            capacity -= storage.get(resource);
        }
        return capacity;
    }
}
