package model.Buildings;

import model.Game;
import model.Resource;

import java.util.ArrayList;
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

    public StorageBuilding(StorageType storageType) {
        super(storageType.getBuildingType(), storageType.getBuildingType().getMaxHp(), Game.getCurrentUser().getGovernment());
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
