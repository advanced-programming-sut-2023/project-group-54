package com.ap.stronghold.model;

import com.ap.stronghold.model.Buildings.Building;
import com.ap.stronghold.model.Buildings.StorageBuilding;

import java.util.ArrayList;
import java.util.HashMap;

public class Government {
    private final HashMap<Resource, Double> allResources = new HashMap<>();
    private int population;
    private int maxPopulation;
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private int efficiency;
    private int unemployedWorker;
    private int gold;
    private User user;
    private ArrayList<Building> buildings = new ArrayList<>();
    private ArrayList<Trade> receivedTrades = new ArrayList<>();
    private ArrayList<Trade> sentTrades = new ArrayList<>();
    private ArrayList<Trade> newTrades = new ArrayList<>();
    private ArrayList<Trade> AllTrades = new ArrayList<>();

    public Government() {
        this.popularity = 100;
        this.population = 10;
        this.maxPopulation = 10;
        this.unemployedWorker = 10;
        this.foodRate = 0;
        this.taxRate = 0;
        this.fearRate = 0;
        this.efficiency = 100;
        this.gold = 4000;

        for (Resource value : Resource.values()) {
            allResources.put(value, (double) 0);
        }
        allResources.put(Resource.WOOD, (double) 100);
        allResources.put(Resource.STONE, (double) 50);
        allResources.put(Resource.BREAD, (double) 60);
    }


    public ArrayList<Trade> getNewTrades() {
        return newTrades;
    }

    public void setNewTrades(ArrayList<Trade> newTrades) {
        this.newTrades = newTrades;
    }

    public ArrayList<Trade> getReceivedTrades() {
        return receivedTrades;
    }

    public void setReceivedTrades(ArrayList<Trade> receivedTrades) {
        this.receivedTrades = receivedTrades;
    }
    public void addReceivedTrades(Trade receivedTrades) {
        this.receivedTrades.add(receivedTrades);
    }

    public ArrayList<Trade> getSentTrades() {
        return sentTrades;
    }

    public void setSentTrades(ArrayList<Trade> sentTrades) {
        this.sentTrades = sentTrades;
    }

    public ArrayList<Trade> getAllTrades() {
        return AllTrades;
    }

    public void setAllTrades(ArrayList<Trade> allTrades) {
        AllTrades = allTrades;
    }


    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation2(int maxPopulation) {
        this.maxPopulation += maxPopulation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public void setPopulation2(int population) {
        this.population += population;
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

    public void setPopularity2(int popularity) {
        if(this.popularity >= 100)
            this.popularity = 100;
        else
            this.popularity += popularity;
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
        this.unemployedWorker -= unemployedWorker;
    }

    public void addUnemployedWorker(int unemployedWorker) {
        this.unemployedWorker += unemployedWorker;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public void setGold2(int gold) {
        this.gold += gold;
    }

    public void changeResourceAmount(Resource resource, double count) {
        allResources.put(resource, allResources.get(resource) + count);
    }

    public HashMap<Resource, Double> getAllResources() {
        return allResources;
    }

    public double getResourceCount(Resource resource) {
        return allResources.get(resource);
    }

    public boolean hasStorageForItem(Resource item, double amount) {
        int capacity = 0;
        for (Building building : buildings) {
            if (building.getBuildingType().equals(item.getStorageType().getBuildingType())) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                capacity += storageBuilding.getCapacity();
            }
        }
        return capacity >= amount;

    }

    public boolean hasEnoughItem(Resource item, double amount) {
        return allResources.get(item) >= amount;
    }

    public void addToStorage(Resource item, double amount) {
        for (Building building : buildings) {
            if (building.getBuildingType().equals(item.getStorageType().getBuildingType())) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                if (storageBuilding.getCapacity() >= amount) {
                    if (storageBuilding.getStorage().containsKey(item)) {
                        double newAmount = storageBuilding.getStorage().get(item) + amount;
                        storageBuilding.getStorage().put(item, newAmount);
                    } else {
                        storageBuilding.getStorage().put(item, amount);
                    }
                    return;
                } else {
                    if (storageBuilding.getStorage().containsKey(item)) {
                        double newAmount = storageBuilding.getCapacity() + storageBuilding.getStorage().get(item);
                        storageBuilding.getStorage().put(item, newAmount);
                    } else {
                        storageBuilding.getStorage().put(item, storageBuilding.getCapacity());
                    }
                    amount -= storageBuilding.getCapacity();
                }
            }
        }
    }

    public void removeFromStorage(Resource item, double amount) {
        for (Building building : buildings) {
            if (building.getBuildingType().equals(item.getStorageType().getBuildingType())) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                if (storageBuilding.getStorage().containsKey(item)) {
                    if (storageBuilding.getStorage().get(item) >= amount) {
                        storageBuilding.getStorage().put(item, storageBuilding.getStorage().get(item) - amount);
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
