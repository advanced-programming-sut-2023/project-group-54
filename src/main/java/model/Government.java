package model;

import model.Buildings.Building;
import model.Buildings.StorageBuilding;

import java.util.ArrayList;
import java.util.HashMap;

public class Government {
    private final HashMap<Resource, Integer> allResources = new HashMap<>();
    private int population;
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private int efficiency;
    private int unemployedWorker;
    private int gold;
    private ArrayList<Building> buildings = new ArrayList<>();

    public Government() {
        this.popularity = 100;
        this.population = 10;
        this.unemployedWorker = 10;
        this.foodRate = 0;
        this.taxRate = 0;
        this.fearRate = 0;
        this.efficiency = 100;
        this.gold = 4000;

        for (Resource value : Resource.values()) {
            allResources.put(value, 0);
        }
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public int getUnemployedWorker() {
        return unemployedWorker;
    }

    public void setUnemployedWorker(int unemployedWorker) {
        this.unemployedWorker = unemployedWorker;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void changeResourceAmount(Resource Resource, int count) {
        allResources.put(Resource, allResources.get(Resource) + count);
    }

    public HashMap<Resource, Integer> getAllResources() {
        return allResources;
    }

    public int getResourceCount(Resource resource) {
        return allResources.get(resource);
    }

    public boolean hasStorageForItem(Resource item, int amount) {
        int capacity = 0;
        for (Building building : buildings) {
            if (building.getBuildingType().equals(item.getStorageType().getBuildingType())) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                capacity += storageBuilding.getCapacity();
            }
        }
        return capacity >= amount;

    }

    public boolean hasEnoughItem(Resource item, int amount) {
        return allResources.get(item) >= amount;
    }

    public void addToStorage(Resource item, int amount) {
        for (Building building : buildings) {
            if (building.getBuildingType().equals(item.getStorageType().getBuildingType())) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                if (storageBuilding.getCapacity() >= amount) {
                    if (storageBuilding.getStorage().containsKey(item)) {
                        int newAmount = storageBuilding.getStorage().get(item) + amount;
                        storageBuilding.getStorage().put(item, newAmount);
                    } else {
                        storageBuilding.getStorage().put(item, amount);
                    }
                    return;
                } else {
                    if (storageBuilding.getStorage().containsKey(item)) {
                        int newAmount = storageBuilding.getCapacity() + storageBuilding.getStorage().get(item);
                        storageBuilding.getStorage().put(item, newAmount);
                    } else {
                        storageBuilding.getStorage().put(item, storageBuilding.getCapacity());
                    }
                    amount -= storageBuilding.getCapacity();
                }
            }
        }
    }

    public void removeFromStorage(Resource item, int amount) {
        for (Building building : buildings) {
            if (building.getBuildingType().equals(item.getStorageType().getBuildingType())) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                if (storageBuilding.getStorage().containsKey(item)) {
                    if (storageBuilding.getStorage().get(item) >= amount) {
                        storageBuilding.getStorage().remove(item);
                        return;
                    } else {
                        amount -= storageBuilding.getStorage().get(item);
                        storageBuilding.getStorage().remove(item);
                    }
                } else continue;
            }
        }
    }
}
