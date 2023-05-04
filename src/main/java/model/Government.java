package model;

import java.util.HashMap;

public class Government {
    private int population;
    private int popularity;
    private int foodRate;
    private int taxRate;
    private int fearRate;
    private int efficiency;
    private int unemployedWorker;
    private int gold;
    private final HashMap<Resource, Integer> allResources = new HashMap<>();

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

    public HashMap<Resource, Integer> getAllResources() {
        return allResources;
    }

    public int getResourceCount(Resource resource){
        return allResources.get(resource);
    }
}
