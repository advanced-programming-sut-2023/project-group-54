package controller;

import model.*;
import model.Buildings.*;
import model.units.State;
import model.units.Unit;
import view.enums.messages.GameMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMenuController {
    public static int getPopularityFromFood() {
        int foodPop = Game.getCurrentUser().getGovernment().getFoodRate() * 4;
        int addPopForDif = (hasApple() ? 1 : 0) + (hasMeet() ? 1 : 0) + (hasBread() ? 1 : 0) + (hasCheese() ? 1 : 0);
        return foodPop + addPopForDif;
    }

    private static boolean hasApple() {
        for (Building building : Building.getBuildings()) {
            if (building.getBuildingType().equals(BuildingType.GRANARY)) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    if (resource.equals(Resource.APPLE)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private static boolean hasMeet() {
        for (Building building : Building.getBuildings()) {
            if (building.getBuildingType().equals(BuildingType.GRANARY)) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    if (resource.equals(Resource.MEAT)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private static boolean hasCheese() {
        for (Building building : Building.getBuildings()) {
            if (building.getBuildingType().equals(BuildingType.GRANARY)) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    if (resource.equals(Resource.CHEESE)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private static boolean hasBread() {
        for (Building building : Building.getBuildings()) {
            if (building.getBuildingType().equals(BuildingType.GRANARY)) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    if (resource.equals(Resource.BREAD)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public static int getPopularityFromTax() {
        int taxPop = 0;
        int gameTaxRate = Game.getCurrentUser().getGovernment().getTaxRate();
        if (gameTaxRate >= -3 && gameTaxRate <= 0)
            taxPop = (gameTaxRate * -2) + 1;
        else if (gameTaxRate >= 1 && gameTaxRate <= 4)
            taxPop = gameTaxRate * -2;
        else if (gameTaxRate >= 5 && gameTaxRate <= 8)
            taxPop = (gameTaxRate * -3) + (8 - gameTaxRate);
        return taxPop;
    }

    public static int getPopularityFromFear() {
        return Game.getCurrentUser().getGovernment().getFearRate();
    }

    public static int getPopularityFromReligion() {
        int religionBuildingCount = 0;
        for (Building building : Building.getBuildings()) {
            if (building.getBuildingType().equals(BuildingType.CHURCH) || building.getBuildingType().equals(BuildingType.CATHEDRAL)) {
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

    public static double getAppleCount() {
        return Game.getCurrentUser().getGovernment().getResourceCount(Resource.APPLE);
    }

    public static double getMeetCount() {
        return Game.getCurrentUser().getGovernment().getResourceCount(Resource.MEAT);
    }

    public static double getCheeseCount() {
        return Game.getCurrentUser().getGovernment().getResourceCount(Resource.CHEESE);
    }

    public static double getBreadCount() {
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

    private static void killingPit(User user, Building building) {
        for (int i = building.getX1Position(); i < building.getX2Position(); i++) {
            for (int j = building.getY1Position(); j < building.getY2Position(); j++) {
                ArrayList<Unit> unitsTobeRemoved = new ArrayList<>();
                for (Unit unit : Game.getGameMap()[i][j].getUnit()) {
                    if (unit.getGovernment().equals(user.getGovernment())) unitsTobeRemoved.add(unit);
                }
                for (Unit unit : unitsTobeRemoved) {
                    Game.getGameMap()[i][j].getUnit().remove(unit);
                }
            }
        }
    }

    public static void procedureBuildings(User user, Building building) {
        if (((ProducerBuilding) building).getProducerType().isAddPopularity()) user.getGovernment().setPopularity2(1);
        for (HashMap.Entry<HashMap<Resource, Double>, HashMap<Resource, Double>> input : ((ProducerBuilding) building).getProducerType().getPuts().entrySet()) {
            if (input.getKey() != null) {
                for (Map.Entry<Resource, Double> input2 : input.getKey().entrySet()) {
                    if (user.getGovernment().getAllResources().get(input2.getKey()) < input2.getValue())
                        continue;
                    if (input.getValue() == null) {
                        user.getGovernment().changeResourceAmount(input2.getKey(), -input2.getValue());
                        user.getGovernment().removeFromStorage(input2.getKey(), -input2.getValue());
                    } else {
                        for (Map.Entry<Resource, Double> input3 : input.getValue().entrySet()) {
                            if (!(user.getGovernment().hasStorageForItem(input3.getKey(), input3.getValue()))) continue;
                            user.getGovernment().changeResourceAmount(input2.getKey(), -input2.getValue());
                            user.getGovernment().removeFromStorage(input2.getKey(), -input2.getValue());
                            user.getGovernment().changeResourceAmount(input3.getKey(), input3.getValue());
                            user.getGovernment().addToStorage(input3.getKey(), input3.getValue());
                        }
                    }
                }
            } else {
                for (Map.Entry<Resource, Double> input3 : input.getValue().entrySet()) {
                    if (!(user.getGovernment().hasStorageForItem(input3.getKey(), input3.getValue()))) continue;
                    user.getGovernment().changeResourceAmount(input3.getKey(), input3.getValue());
                    user.getGovernment().addToStorage(input3.getKey(), input3.getValue());
                }
            }
        }
    }

    public static void mangonellBallistae(Building building) {
        for (int i = building.getX1Position(); i < building.getX2Position() + 10; i++) {
            for (int j = building.getY1Position(); j < building.getY2Position() + 10; j++) {
                for (Unit unit : Game.getGameMap()[i][j].getUnit()) {
                    if (!unit.getGovernment().equals(building.getOwner()))
                        unit.setHp(-600);
                }
            }
        }
    }

    public static void nextTurnForBuildings(User user) {
        ArrayList<Building> buildings = new ArrayList<>();
        for (Building building : Building.getBuildings()) {
            if (building.getOwner().equals(user.getGovernment())) buildings.add(building);
        }
        for (Building building : buildings) {
            if (building.getBuildingType().equals(BuildingType.SMALL_STONE_GATE) || building.getBuildingType().equals(BuildingType.HOVEL))
                user.getGovernment().setPopulation2(8);
            else if (building.getBuildingType().equals(BuildingType.LARGE_STONE_GATE))
                user.getGovernment().setPopulation2(10);
            else if (building.getBuildingType().equals(BuildingType.KILLING_PIT)) killingPit(user, building);
            else if (building.getBuildingType().getBuildingGroup2().equals(BuildingGroup.PRODUCER_BUILDING) &&
                    ((ProducerBuilding) building).getProducerType().getPuts() == null)
                procedureBuildings(user, building);
            else if (building.getBuildingType().equals(BuildingType.CHURCH) || building.getBuildingType().equals(BuildingType.CATHEDRAL))
                user.getGovernment().setPopularity2(2);
            else if (building.getBuildingType().equals(BuildingType.MANGONEL) || building.getBuildingType().equals(BuildingType.BALLISTAE))
                mangonellBallistae(building);
        }
    }

    public static void setPlacesToFightInDifferentModes(Unit unit,boolean aggressiveOrDefence) {
        int range = (5 * unit.getUnitType().getSpeed());
        if (!aggressiveOrDefence) range /= 2;
        int fireRange = unit.getUnitType().getRange();
        if (fireRange == 1) fireRange = 0;
        for (int i = unit.getxPosition(); i <= unit.getxPosition() + range - fireRange; i++) {
            for (int j = unit.getyPosition(); j <= unit.getyPosition() + range - fireRange; j++) {
                for (Unit unit1 : Game.getGameMap()[i][j].getUnit()) {
                    if (!unit1.getGovernment().equals(unit.getGovernment())) {
                        moveTroop(unit,i,j);
                        break;
                    }
                }
            }
        }
    }

    public static void moveTroop(Unit unit,int x,int y) {
        //needed to be completed to move unit
    }

    public static void archersAttackRadios(Unit unit) {
        int fireRange = unit.getUnitType().getRange();
        switch (Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getBuilding().getBuildingType()) {
            case DEFENCE_TOWER, PERIMETER_TOWER, ROUND_TOWER, SQUARE_TOWER, LOOK_OUT_TOWER:
                if (fireRange != 1) fireRange *= 2;
                break;
            default:
                break;
        }
        for (int i = unit.getxPosition(); i < unit.getxPosition()+fireRange; i++) {
            for (int j = unit.getyPosition(); j < unit.getyPosition()+fireRange; j++) {
                for (Unit unit1 : Game.getGameMap()[i][j].getUnit()) {
                    if (!unit1.getGovernment().equals(unit.getGovernment()))
                        unit1.setHp2(-unit.getUnitType().getDamage());
                    if (Game.getGameMap()[i][j].getBuilding().getBuildingType().equals(BuildingType.PITCH_RIG))
                        ((TrapBuilding)Game.getGameMap()[i][j].getBuilding()).firePitchDitch();
                }
            }
        }
    }

    public static void archersAttackSpecialHouse(Unit unit) {
        for (Unit unit1 : Game.getGameMap()[unit.getxTarget()][unit.getyTarget()].getUnit()) {
            if (!unit1.getGovernment().equals(unit.getGovernment()))
                unit1.setHp2(-unit.getUnitType().getDamage());
        }
        if (Game.getGameMap()[unit.getxTarget()][unit.getyTarget()].getBuilding().getBuildingType().equals(BuildingType.PITCH_RIG))
            ((TrapBuilding)Game.getGameMap()[unit.getxTarget()][unit.getyTarget()].getBuilding()).firePitchDitch();
    }

    public static void setPlaceForAttackPatrolUnit(Unit unit) {
        outer : for (int i = unit.getPatrolXFrom(); i < unit.getPatrolXTarget(); i++) {
            for (int j = unit.getPatrolYFrom(); j < unit.getPatrolYTarget(); j++) {
                for (Unit unit1 : Game.getGameMap()[i][j].getUnit()) {
                    if (!unit1.getGovernment().equals(unit.getGovernment())) {
                        if (unit.getUnitType().getRange() != 1) {
                            if (unit1.getyPosition() > unit.getyPosition() + unit.getUnitType().getRange()) {
                                moveTroop(unit,unit.getxPosition(),unit.getyPosition() +
                                        unit1.getyPosition() - unit.getUnitType().getRange());
                            } if (unit1.getyPosition() > unit.getyPosition() + unit.getUnitType().getRange()) {
                                moveTroop(unit,unit.getxPosition() +
                                        unit1.getxPosition() - unit.getUnitType().getRange(),unit.getyPosition());
                            }
                            unit.setXTarget(i);
                            unit.setYTarget(j);
                            archersAttackSpecialHouse(unit);
                            return;
                        }
                        moveTroop(unit,i,j);
                        return;
                    }
                }
            }
        }
    }

    public static void fightsInEachHouse(Unit unit) {
        int damageSet;
        if (unit.getUnitType().getRange()!= 1) damageSet = unit.getUnitType().getDamage()/2;
        else damageSet = unit.getUnitType().getDamage();
        for (Unit unit1 : Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getUnit()) {
            if (!unit1.getGovernment().equals(unit.getGovernment())) {
                unit1.setHp2(-damageSet);
            }
        }
    }

    public static void UnitsAttack() {
        for (Unit unit : Unit.getUnits()) {
            if (unit.getUnitType().getRange() != -1 && !unit.getPatrol()) {
                if (unit.getState().equals(State.DEFENSIVE)) setPlacesToFightInDifferentModes(unit,false);
                else if (unit.getState().equals(State.AGGRESSIVE)) setPlacesToFightInDifferentModes(unit,true);
                if ( unit.getxTarget() == -1)
                    archersAttackRadios(unit);
                else if (unit.getxTarget() != -1)
                    archersAttackRadios(unit);
            } else if (unit.getPatrol()) setPlaceForAttackPatrolUnit(unit);
        }
    }

    public static GameMenuMessage nextTurn() {
        return GameMenuMessage.SUCCESS;
    }
}
