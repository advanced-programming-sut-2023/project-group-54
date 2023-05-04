package model.Buildings;

import model.Game;
import model.Resource;

import java.util.ArrayList;
import java.util.HashMap;

public class StorageBuilding extends Building{
    private StorageType storageType;
    private HashMap<Resource, Integer> storage;

    public StorageBuilding(StorageType storageType, HashMap<Resource, Integer> storage) {
        super(storageType.getBuildingType(), storageType.getBuildingType().getMaxHp());
        this.storageType = storageType;
        this.storage = storage;
    }

    public void setFoodRate(int foodRate){
        Game.getCurrentUser().getGovernment().setFoodRate(foodRate);
    }

    public int showStoredResource(Resource resource){
        return storage.get(resource);
    }

    public HashMap<Resource, Integer> getStorage() {
        return storage;
    }
}
