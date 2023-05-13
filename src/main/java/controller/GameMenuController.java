package controller;

import model.*;
import model.Buildings.Building;
import model.Buildings.BuildingType;
import model.Buildings.StorageBuilding;
import view.enums.messages.GameMenuMessage;

import java.util.HashMap;

public class GameMenuController {
    public static int getPopularityFromFood(){
        int foodPop = Game.getCurrentUser().getGovernment().getFoodRate() * 4;
        int addPopForDif = (hasApple() ? 1 : 0) + (hasMeet() ? 1 : 0) + (hasBread() ? 1 : 0) + (hasCheese() ? 1 : 0);
        return foodPop + addPopForDif;
    }
    private static boolean hasApple(){
        for (Building building : Building.getBuildings()) {
            if(building.getBuildingType().equals(BuildingType.GRANARY)){
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    if(resource.equals(Resource.APPLE)){
                        return true;
                    }
                }

            }
        }
        return false;
    }
    private static boolean hasMeet(){
        for (Building building : Building.getBuildings()) {
            if(building.getBuildingType().equals(BuildingType.GRANARY)){
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    if(resource.equals(Resource.MEAT)){
                        return true;
                    }
                }

            }
        }
        return false;
    }
    private static boolean hasCheese(){
        for (Building building : Building.getBuildings()) {
            if(building.getBuildingType().equals(BuildingType.GRANARY)){
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    if(resource.equals(Resource.CHEESE)){
                        return true;
                    }
                }

            }
        }
        return false;
    }
    private static boolean hasBread(){
        for (Building building : Building.getBuildings()) {
            if(building.getBuildingType().equals(BuildingType.GRANARY)){
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    if(resource.equals(Resource.BREAD)){
                        return true;
                    }
                }

            }
        }
        return false;
    }
    public static int getPopularityFromTax(){
        int taxPop = 0;
        int gameTaxRate = Game.getCurrentUser().getGovernment().getTaxRate();
        if(gameTaxRate >= -3 && gameTaxRate <= 0)
            taxPop = (gameTaxRate * -2) + 1;
        else if(gameTaxRate >= 1 && gameTaxRate <= 4)
            taxPop = gameTaxRate * -2;
        else if(gameTaxRate >= 5 && gameTaxRate <= 8)
            taxPop = (gameTaxRate * -3) + (8 - gameTaxRate);
        return taxPop;
    }
    public static int getPopularityFromFear(){
        return Game.getCurrentUser().getGovernment().getFearRate();
    }
    public static int getPopularityFromReligion(){
        int religionBuildingCount = 0;
        for (Building building : Building.getBuildings()) {
            if(building.getBuildingType().equals(BuildingType.CHURCH) || building.getBuildingType().equals(BuildingType.CATHEDRAL)){
                religionBuildingCount++;
            }
        }
        return Math.min(religionBuildingCount, 4);
    }
    public static int getPopularity() {
        return getPopularityFromFood() +
                getPopularityFromTax() +
                getPopularityFromFear() +
                getPopularityFromReligion();
    }

    public static double getAppleCount(){
        return Game.getCurrentUser().getGovernment().getResourceCount(Resource.APPLE);
    }
    public static double getMeetCount(){
        return Game.getCurrentUser().getGovernment().getResourceCount(Resource.MEAT);
    }
    public static double getCheeseCount(){
        return Game.getCurrentUser().getGovernment().getResourceCount(Resource.CHEESE);
    }
    public static double getBreadCount(){
        return Game.getCurrentUser().getGovernment().getResourceCount(Resource.BREAD);
    }

    public static int foodRateShow() {
        return Game.getCurrentUser().getGovernment().getFoodRate();
    }

    public static int taxRateShow() {
        return Game.getCurrentUser().getGovernment().getTaxRate();
    }

    public static GameMenuMessage fearRateSet(int rateNumber) {
        Game.getCurrentUser().getGovernment().setFearRate(rateNumber);
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage dropBuilding(int xCoordinate, int yCoordinate, Building building) {
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage selectBuilding(int xCoordinate, int yCoordinate) {
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage selectUnit(int xCoordinate, int yCoordinate) {
        return GameMenuMessage.SUCCESS;
    }

    public static GameMenuMessage nextTurn() {
        return GameMenuMessage.SUCCESS;
    }
}
