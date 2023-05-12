package model.Buildings;

import model.Game;
import model.Resource;
import model.User;

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

    public double getStorageFilled() {
        return storageFilled;
    }

    private double storageFilled;

    public StorageBuilding(StorageType storageType, HashMap<Resource, Double> storage, User owner) {
        super(storageType.getBuildingType(), storageType.getBuildingType().getMaxHp(),owner);
        this.storageType = storageType;
        this.storage = storage;
    }

    public void setFoodRate(int foodRate){
        Game.getCurrentUser().getGovernment().setFoodRate(foodRate);
    }

    public double showStoredResource(Resource resource){
        return storage.get(resource);
    }
}
