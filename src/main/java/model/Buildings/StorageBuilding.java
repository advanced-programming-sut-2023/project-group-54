package model.Buildings;

import model.Game;
import model.Resource;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageBuilding extends Building{
    private StorageType storageType;
    private HashMap<Resource, Integer> storage;
    private int storageFilled;

    public StorageBuilding(StorageType storageType, HashMap<Resource, Integer> storage, User owner) {
        super(storageType.getBuildingType(), storageType.getBuildingType().getMaxHp(),owner);
        this.storageType = storageType;
        this.storage = storage;
    }

    public void setFoodRate(int foodRate){
        Game.getCurrentUser().getGovernment().setFoodRate(foodRate);
    }

    public int showStoredResource(Resource resource){
        return storage.get(resource);
    }
}
