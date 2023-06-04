package com.ap.stronghold.controller;

import com.ap.stronghold.model.*;
import com.ap.stronghold.model.Buildings.*;
import com.ap.stronghold.model.units.*;
import com.ap.stronghold.view.enums.messages.GameMenuMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMenuController {
    public static int getPopularityFromFood() {
        int foodPop = Game.getCurrentUser().getGovernment().getFoodRate() * 4;
        int addPopForDif = (hasApple() ? 1 : 0) + (hasMeet() ? 1 : 0) + (hasBread() ? 1 : 0) + (hasCheese() ? 1 : 0) - 1;
        if (!hasApple() && !hasMeet() && !hasBread() && !hasCheese()) {
            return -8;
        }
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
        return Game.getCurrentUser().getGovernment().getPopularity();
    }

    public static int getPopularity2() {
        return getPopularityFromTax() + getPopularityFromFear() + getPopularityFromReligion() + getPopularityFromFood();
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
        Game.getCurrentUser().getGovernment().setEfficiency(100 - rateNumber * 10);
        return GameMenuMessage.SUCCESS;
    }

    private static void killingPit(User user, Building building) {
        for (int i = building.getX1Position(); i < building.getX2Position(); i++) {
            for (int j = building.getY1Position(); j < building.getY2Position(); j++) {
                ArrayList<Unit> unitsTobeRemoved = new ArrayList<>();
                for (Unit unit : Game.getGameMap()[i][j].getUnit()) {
                    if (!unit.getGovernment().equals(user.getGovernment())) unitsTobeRemoved.add(unit);
                }
                for (Unit unit : unitsTobeRemoved) {
                    Game.getGameMap()[i][j].getUnit().remove(unit);
                }
            }
        }
    }

    public static void procedureBuildings(User user, Building building) {
        if (((ProducerBuilding) building).getProducerType().isAddPopularity()) user.getGovernment().setPopularity2(1);
        for (java.util.Map.Entry<HashMap<Resource, Double>, HashMap<Resource, Double>> input : ((ProducerBuilding) building).getProducerType().getPuts().entrySet()) {
            boolean sign = false;
            for (Resource resource : input.getValue().keySet()) {
                if (resource.equals(((ProducerBuilding) building).getCurrentOutput())) {
                    sign = true;
                }
            }
            if (!sign)
                continue;
            if (input.getKey() != null) {
                for (java.util.Map.Entry<Resource, Double> input2 : input.getKey().entrySet()) {
                    if (user.getGovernment().getAllResources().get(input2.getKey()) < input2.getValue())
                        return;
                    if (input.getValue() == null) {
                        user.getGovernment().changeResourceAmount(input2.getKey(), -input2.getValue());
                        user.getGovernment().removeFromStorage(input2.getKey(), -input2.getValue());
                    } else {
                        for (java.util.Map.Entry<Resource, Double> input3 : input.getValue().entrySet()) {
                            if (!(user.getGovernment().hasStorageForItem(input3.getKey(), input3.getValue()))) return;
                            user.getGovernment().changeResourceAmount(input2.getKey(), -input2.getValue());
                            user.getGovernment().removeFromStorage(input2.getKey(), -input2.getValue());
                            user.getGovernment().changeResourceAmount(input3.getKey(), input3.getValue());
                            user.getGovernment().addToStorage(input3.getKey(), input3.getValue());
                        }
                    }
                }
            } else {
                for (java.util.Map.Entry<Resource, Double> input3 : input.getValue().entrySet()) {
                    if (!(user.getGovernment().hasStorageForItem(input3.getKey(), input3.getValue()))) return;
                    user.getGovernment().changeResourceAmount(input3.getKey(), input3.getValue());
                    user.getGovernment().addToStorage(input3.getKey(), input3.getValue());
                }
            }
        }
    }

    public static void mangonellBallistae(Building building) {
        for (int i = setLowest(building.getX1Position() - 10); i < setHighest(building.getX2Position() + 10); i++) {
            for (int j = setLowest(building.getY1Position() - 10); j < setHighest(building.getY2Position() + 10); j++) {
                for (Unit unit : Game.getGameMap()[i][j].getUnit()) {
                    if (!unit.getGovernment().equals(building.getOwner()))
                        unit.setHp2(-600);
                }
            }
        }
    }

    public static void nextTurnForBuildings(User user) {
        ArrayList<Building> buildings = Game.getCurrentUser().getGovernment().getBuildings();
        for (Building building : buildings) {
            if (building.getBuildingType().equals(BuildingType.SMALL_STONE_GATE) || building.getBuildingType().equals(BuildingType.HOVEL)) {
                user.getGovernment().setPopulation2(8);
                Game.getCurrentUser().getGovernment().addUnemployedWorker(8);
            } else if (building.getBuildingType().equals(BuildingType.LARGE_STONE_GATE))
                user.getGovernment().setPopulation2(10);
            else if (building.getBuildingType().equals(BuildingType.KILLING_PIT)) killingPit(user, building);
            else if (building instanceof ProducerBuilding
                    && (((ProducerBuilding) building).getProducerType().getPuts() != null
                    || ((ProducerBuilding) building).getProducerType().isAddPopularity()))
                procedureBuildings(user, building);
            else if (building.getBuildingType().equals(BuildingType.MANGONEL) || building.getBuildingType().equals(BuildingType.BALLISTAE))
                mangonellBallistae(building);
        }
    }

    public static int setHighest(int whereYouWant) {
        if (whereYouWant > Game.getX()) return Game.getX();
        else return whereYouWant;
    }

    public static int setLowest(int whereYouWant) {
        if (whereYouWant < 0) return 0;
        return whereYouWant;
    }

    public static void setPlacesToFightInDifferentModes(Unit unit, boolean aggressiveOrDefence) {
        int range = (5 * unit.getUnitType().getSpeed());
        int fireRange = unit.getUnitType().getRange();
        int whereToGoXHigh = unit.getxPosition() + range - fireRange + 1;
        int whereToGoXLow = unit.getxPosition() - range + fireRange - 1;
        int whereToGoYHigh = unit.getyPosition() + range - fireRange + 1;
        int whereToGoYLow = unit.getyPosition() - range + fireRange - 1;
        if (whereToGoXHigh >= Game.getX()) whereToGoXHigh = Game.getX();
        if (whereToGoXLow < 0) whereToGoXLow = 0;
        if (whereToGoYHigh >= Game.getY()) whereToGoYHigh = Game.getY();
        if (whereToGoYLow < 0) whereToGoYLow = 0;
        if(!(unit instanceof Engineer)){
            if(!aggressiveOrDefence){
                for (int i = whereToGoXLow; i < whereToGoXHigh; i++) {
                    for (int j = whereToGoYLow; j < whereToGoYHigh; j++) {
                        for (Unit unit1 : Game.getGameMap()[i][j].getUnit()) {
                            if (!unit1.getGovernment().equals(unit.getGovernment())) {
                                if (unit.getUnitType().getRange() == 1) {
                                    unit.setxMoveTarget(unit1.getxPosition());
                                    unit.setyMoveTarget(unit1.getyPosition());
                                    unit.setXTarget(unit1.getxPosition());
                                    unit.setYTarget(unit1.getyPosition());
                                    moveTroop(unit);
                                    return;
                                }
                            }
                        }
                    }
                }
            }else{
                ArrayList<Map> path = null;
                for (Unit unit1 : Unit.getUnits()) {
                    ArrayList<Map> newPath;
                    if(!unit1.getOwner().equals(unit.getOwner())){
                        newPath = AStarShortestPath.findPath(Game.getGameMap(), unit.getxPosition(), unit.getyPosition(), unit1.getxPosition(), unit1.getyPosition());
                        if(path == null || path.size() < newPath.size()){
                            path = newPath;
                        }
                    }
                }
                if(path != null){
                    moveTo(path, unit);
                }
            }
        }
    }

    public static void moveTroop(ArrayList<Unit> units) {
        ArrayList<Map> path = AStarShortestPath.findPath(
                Game.getGameMap(),
                units.get(0).getxPosition(),
                units.get(0).getyPosition(),
                units.get(0).getxMoveTarget(),
                units.get(0).getyMoveTarget());

        path.remove(path.get(0));

        for (Unit unit : units) {
            if ((unit.getxPosition() != unit.getxMoveTarget() || unit.getyPosition() != unit.getyMoveTarget())) {
                moveTo(path, unit);
            } else if (unit.isPatrol()) {
                path = null;
                if (unit.getxPosition() == unit.getPatrolXTarget()) {
                    path = AStarShortestPath.findPath(Game.getGameMap(), unit.getxPosition(), unit.getyPosition(), unit.getPatrolXFrom(), unit.getPatrolYFrom());
                } else if (unit.getxPosition() == unit.getPatrolXFrom()) {
                    path = AStarShortestPath.findPath(Game.getGameMap(), unit.getxPosition(), unit.getyPosition(), unit.getPatrolXTarget(), unit.getPatrolYTarget());
                }
                path.remove(path.get(0));
                moveTo(path, unit);
            }
        }
    }

    public static void moveTroop(Unit unit) {
        if ((unit.getxPosition() != unit.getxMoveTarget() || unit.getyPosition() != unit.getyMoveTarget())) {
            ArrayList<Map> path = AStarShortestPath.findPath(
                    Game.getGameMap(),
                    unit.getxPosition(),
                    unit.getyPosition(),
                    unit.getxMoveTarget(),
                    unit.getyMoveTarget());
            path.remove(path.get(0));
            moveTo(path, unit);
        } else if (unit.isPatrol() && unit.getxTarget() == -1 && unit.getyTarget() == -1) {
            ArrayList<Map> path = null;
            if (unit.getxPosition() == unit.getPatrolXTarget()) {
                path = AStarShortestPath.findPath(Game.getGameMap(), unit.getxPosition(), unit.getyPosition(), unit.getPatrolXFrom(), unit.getPatrolYFrom());
            } else if (unit.getxPosition() == unit.getPatrolXFrom()) {
                path = AStarShortestPath.findPath(Game.getGameMap(), unit.getxPosition(), unit.getyPosition(), unit.getPatrolXTarget(), unit.getPatrolYTarget());
            }
            path.remove(path.get(0));
            moveTo(path, unit);
        }
    }

    private static void moveTo(ArrayList<Map> path, Unit unit) {
        Game.getGameMapXY(unit.getxPosition(), unit.getyPosition()).getUnit().remove(unit);
        if ((path.size()) > unit.getUnitType().getSpeed() * 5) {
            unit.setxPosition(path.get((unit.getUnitType().getSpeed() * 5) - 1).getX());
            unit.setyPosition(path.get((unit.getUnitType().getSpeed() * 5) - 1).getY());
        } else {
            unit.setxPosition(path.get(path.size() - 1).getX());
            unit.setyPosition(path.get(path.size() - 1).getY());
        }
        Game.getGameMapXY(unit.getxPosition(), unit.getyPosition()).getUnit().add(unit);
    }

    public static int setFireRangeOnTowers(Unit unit) {
        int fireRange = unit.getUnitType().getRange();
        if (Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getBuilding() != null) {
            switch (Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getBuilding().getBuildingType()) {
                case DEFENCE_TOWER, PERIMETER_TOWER, ROUND_TOWER, SQUARE_TOWER, LOOK_OUT_TOWER, SMALL_STONE_GATE, LARGE_STONE_GATE:
                    if (fireRange != 1)
                        fireRange += ((DefenseBuilding) Game.getGameMap()[unit.getxPosition()][unit.getyPosition()]
                                .getBuilding()).getDefenseType().getIncreaseAttackRange();
                    break;
                default:
                    break;
            }
        }
        return fireRange;
    }

    public static void archersAttackRadius(Unit unit) {
        int fireRange = setFireRangeOnTowers(unit);
        for (int i = setLowest(unit.getxPosition() - fireRange); i < setHighest(unit.getxPosition() + fireRange); i++) {
            for (int j = setLowest(unit.getyPosition() - fireRange); j < setHighest(unit.getyPosition() + fireRange); j++) {
                for (Unit unit1 : Game.getGameMap()[i][j].getUnit()) {
                    if (i == unit.getxPosition() && j == unit.getyPosition()) continue;
                    if (!unit1.getGovernment().equals(unit.getGovernment()))
                        unit1.setHp2(-unit.getUnitType().getDamage());
                    if (Game.getGameMap()[i][j].getBuilding() != null
                            && Game.getGameMap()[i][j].getBuilding().getBuildingType().equals(BuildingType.PITCH_RIG))
                        ((TrapBuilding) Game.getGameMap()[i][j].getBuilding()).firePitchDitch();
                }
            }
        }
    }

    public static void archersAttackSpecialHouse(Unit unit) {
        int fireRange = setFireRangeOnTowers(unit);
        if (unit.getxTarget() < unit.getxPosition() + fireRange && unit.getyTarget() < unit.getyPosition() + fireRange) {
            for (Unit unit1 : Game.getGameMap()[unit.getxTarget()][unit.getyTarget()].getUnit()) {
                if (!unit1.getGovernment().equals(unit.getGovernment()))
                    unit1.setHp2(-unit.getUnitType().getDamage());
            }
            if (Game.getGameMap()[unit.getxTarget()][unit.getyTarget()].getBuilding() != null) {
                Game.getGameMap()[unit.getxTarget()][unit.getyTarget()].getBuilding().setHp2(unit.getUnitType().getDamage());
                if (Game.getGameMap()[unit.getxTarget()][unit.getyTarget()].getBuilding().getBuildingType().equals(BuildingType.PITCH_RIG))
                    ((TrapBuilding) Game.getGameMap()[unit.getxTarget()][unit.getyTarget()].getBuilding()).firePitchDitch();
            }
        }
    }

    public static void setPlaceForAttackPatrolUnit(Unit unit) {
//        outer:
//        for (int i = unit.getPatrolXFrom(); i < unit.getPatrolXTarget(); i++) {
//            for (int j = unit.getPatrolYFrom(); j < unit.getPatrolYTarget(); j++) {
//                for (Unit unit1 : Game.getGameMap()[i][j].getUnit()) {
//                    if (!unit1.getGovernment().equals(unit.getGovernment())) {
//                        if (unit.getUnitType().getRange() != 1) {
//                            unit.setXMoveTarget(unit1.getxPosition());
//                            unit.setYMoveTarget(unit1.getyPosition());
//                            moveTroop(unit);
//                            unit.setXTarget(i);
//                            unit.setYTarget(j);
//                            archersAttackSpecialHouse(unit);
//                        }
//                        unit.setXTarget(i);
//                        unit.setYTarget(j);
//                        return;
//                    }
//                }
//            }
//        }
    }

    public static void fightsInEachHouse(Unit unit) {
        int damageSet;
        if (unit.getUnitType().getRange() != 1) damageSet = unit.getUnitType().getDamage() / 2;
        else damageSet = unit.getUnitType().getDamage();
        for (Unit unit1 : Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getUnit()) {
            if (!unit1.getGovernment().equals(unit.getGovernment())) {
                unit1.setHp2(-damageSet);
            }
        }
    }

    public static void unitsAttack() {
        for (Unit unit : Unit.getUnits()) {
            if (unit instanceof Engineer) {
                ((Engineer) unit).setHasOil(false);
                killUnit((Engineer) unit, ((Engineer) unit).getPour());
                engineerAttack((Engineer) unit);
            }else{
                if (unit.getUnitType().getRange() != 1 && !unit.isPatrol()) {
                    if (unit.getxTarget() == -1) {
                        if (unit.getState().equals(State.DEFENSIVE)) setPlacesToFightInDifferentModes(unit, false);
                        else if (unit.getState().equals(State.AGGRESSIVE)) setPlacesToFightInDifferentModes(unit, true);
                        archersAttackRadius(unit);
                    } else if (unit.getxTarget() != -1)
                        archersAttackSpecialHouse(unit);
                }
                fightsInEachHouse(unit);
                fightBuildings();
            }
        }
        removeDeadUnits();
        removeBuildings();
    }

    private static boolean checkEngineerAttack(Direction direction, Engineer unit, boolean isAggressive){
        int count = 0;
        switch (unit.getPour()){
            case UP:
                if(unit.getxPosition() - 1 >= 0){
                    for (Unit unit1 : Game.getGameMapXY(unit.getxPosition() - 1, unit.getyPosition()).getUnit()) {
                        if(!unit1.getOwner().equals(unit.getOwner())){
                            count++;
                        }
                    }
                }
                break;
            case DOWN:
                if(unit.getxPosition() + 1 <= Game.getX()){
                    for (Unit unit1 : Game.getGameMapXY(unit.getxPosition() + 1, unit.getyPosition()).getUnit()) {
                        if(!unit1.getOwner().equals(unit.getOwner())){
                            count++;
                        }
                    }
                }
                break;
            case RIGHT:
                if(unit.getyPosition() + 1 <= Game.getX()){
                    for (Unit unit1 : Game.getGameMapXY(unit.getxPosition(), unit.getyPosition() + 1).getUnit()) {
                        if(!unit1.getOwner().equals(unit.getOwner())){
                            count++;
                        }
                    }
                }
                break;
            case LEFT:
                if(unit.getyPosition() - 1 >= 0){
                    for (Unit unit1 : Game.getGameMapXY(unit.getxPosition(), unit.getyPosition() - 1).getUnit()) {
                        if(!unit1.getOwner().equals(unit.getOwner())){
                            count++;
                        }
                    }
                }
                break;
        }
        return isAggressive && count >= 1 || !isAggressive && count >= 3;
    }

    private static void engineerAttack(Engineer unit) {
        if(unit.getState().equals(State.DEFENSIVE)){
            if(unit.getHasOil() && unit.getPour() != null){
                if(checkEngineerAttack(unit.getPour(), unit, false)) {
                    killUnit(unit, unit.getPour());
                }
            }
        }else if(unit.getState().equals(State.AGGRESSIVE)){
            if(unit.getHasOil() && unit.getPour() != null){
                if(checkEngineerAttack(unit.getPour(), unit, true)){
                    killUnit(unit, unit.getPour());
                }
            }
        }
    }

    private static void killUnit(Engineer unit, Direction pour) {
        switch (pour) {
            case UP:
                if(unit.getxPosition() - 1 >= 0){
                    for (Unit unit1 : Game.getGameMap()[unit.getxPosition() - 1][unit.getyPosition()].getUnit()) {
                        Unit.getUnits().remove(unit1);
                        Game.getGameMap()[unit.getxPosition() - 1][unit.getyPosition()].getUnit().remove(unit1);
                    }
                }
            case DOWN:
                if(unit.getxPosition() + 1 <= Game.getX()){
                    for (Unit unit1 : Game.getGameMap()[unit.getxPosition() + 1][unit.getyPosition()].getUnit()) {
                        Unit.getUnits().remove(unit1);
                        Game.getGameMap()[unit.getxPosition() + 1][unit.getyPosition()].getUnit().remove(unit1);
                    }
                }
            case RIGHT:
                if(unit.getyPosition() + 1 <= Game.getY()){
                    for (Unit unit1 : Game.getGameMap()[unit.getxPosition() + 1][unit.getyPosition()].getUnit()) {
                        Unit.getUnits().remove(unit1);
                        Game.getGameMap()[unit.getxPosition() + 1][unit.getyPosition()].getUnit().remove(unit1);
                    }
                }
            case LEFT:
                if(unit.getyPosition() - 1 >= 0){
                    for (Unit unit1 : Game.getGameMap()[unit.getxPosition() - 1][unit.getyPosition()].getUnit()) {
                        Unit.getUnits().remove(unit1);
                        Game.getGameMap()[unit.getxPosition() - 1][unit.getyPosition()].getUnit().remove(unit1);
                    }
                }
        }
    }

    public static void fightBuildings() {
        boolean enemyInThatHouse = false;
        for (Unit unit : Unit.getUnits()) {
            for (Unit unit1 : Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getUnit()) {
                if (!unit1.getGovernment().equals(unit.getGovernment())) {
                    enemyInThatHouse = true;
                    break;
                }
            }
            if (enemyInThatHouse) continue;
            if (unit.getUnitType().getRange() == 1) {
                if (Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getBuilding() != null) {
                    if (!Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getBuilding().getOwner().equals(unit.getGovernment()))
                        Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getBuilding().setHp2(-unit.getUnitType().getDamage());
                    else
                        Game.getGameMap()[unit.getxPosition()][unit.getyPosition()].getBuilding().setHp2(-unit.getUnitType().getDamage() / 2);
                }
            }
        }
    }

    public static void removeBuildings() {
        ArrayList<Building> buildingsToBeRemoved = new ArrayList<>();
        for (Building building : Building.getBuildings()) {
            if (building.getHp() <= 0 && building.getBuildingType().getMaxHp() != -1) buildingsToBeRemoved.add(building);
        }
        for (Building building : buildingsToBeRemoved) {
            if (building instanceof StorageBuilding) {
                StorageBuilding storageBuilding = (StorageBuilding) building;
                for (Resource resource : storageBuilding.getStorage().keySet()) {
                    storageBuilding.getOwner().getAllResources().put(resource,
                            storageBuilding.getOwner().getAllResources().get(resource)
                                    - storageBuilding.getStorage().get(resource));
                }
            }
            int x1 = building.getX1Position();
            int y1 = building.getY1Position();
            int x2 = building.getX2Position();
            int y2 = building.getY2Position();
            for (int i = x1; i < x2; i++) {
                for (int j = y1; j < y2; j++) {
                    Game.getGameMap()[i][j].setBuilding(null);
                }
            }

            Building.getBuildings().remove(building);
            building.getOwner().getBuildings().remove(building);
        }
    }

    public static void moveUnits() {
        ArrayList<Unit> units = new ArrayList<>();
        Unit oldUnit = null;
        for (Unit unit : Unit.getUnits()) {
            if (oldUnit == null || (oldUnit.getxMoveTarget() == unit.getxMoveTarget() && oldUnit.getyMoveTarget() == unit.getyMoveTarget()))
                units.add(unit);
            else {
                moveTroop(units);
                units.clear();
            }
            oldUnit = unit;
        }
        if (!units.isEmpty())
            moveTroop(units);
    }

    public static void removeDeadUnits() {
        ArrayList<Unit> unitsToBeRemoved = new ArrayList<>();

        for (Unit unit : Unit.getUnits()) {
            if (unit.getHp() <= 0) unitsToBeRemoved.add(unit);
        }
        for (Unit unit : unitsToBeRemoved) {
            Game.getGameMapXY(unit.getxPosition(), unit.getyPosition()).getUnit().remove(unit);
            Unit.getUnits().remove(unit);
        }
    }

    public static void checkGameConditions() {
        ArrayList<Building> mainHousesToBeRemoved = new ArrayList<>();
        ArrayList<Building> buildingsToBeRemoved = new ArrayList<>();
        ArrayList<Unit> unitsToBeRemoved = new ArrayList<>();
        for (Building mainHouse : Game.getMainHouses()) {
            if (mainHouse.getHp() <= 0) {
                int scoreToAdd = 0;
                mainHousesToBeRemoved.add(mainHouse);
                buildingsToBeRemoved.add(mainHouse);
                for (Building building : Building.getBuildings()) {
                    if (building.getOwner().equals(mainHouse.getOwner())) {
                        scoreToAdd += (int) (building.getHp() * 1.0) / 100;
                        buildingsToBeRemoved.add(building);
                    }
                }
                for (Unit unit : Unit.getUnits()) {
                    if (unit.getOwner().equals(mainHouse.getOwner())) {
                        scoreToAdd += 1;
                        unitsToBeRemoved.add(unit);
                    }
                }
                scoreToAdd += (int) (mainHouse.getOwner().getGold() * 1.0) / 10;
                doThingsForUserRemoved(mainHouse.getOwner().getUser(), scoreToAdd);
                Game.getUsers().remove(mainHouse.getOwner().getUser());
            }
        }
        for (Building building : mainHousesToBeRemoved)
            Game.getMainHouses().remove(building);
        for (Building building : buildingsToBeRemoved)
            Building.getBuildings().remove(building);
        for (Unit unit : unitsToBeRemoved)
            Unit.getUnits().remove(unit);

    }

    public static void doThingsForUserRemoved(User user, int scoreToAdd) {
        for (User user1 : Game.getUsers()) {
            user1.getGovernment().setGold2((int) (scoreToAdd * 1.0) / 2);
        }
        user.setHighScore(scoreToAdd);
        Game.setUserRemoved();
        Game.addUserRemoved(user);
    }

    public static boolean checkIfGameIsOver() {
        if (Game.getUsers().isEmpty()) return true;
        if (Game.getTurns() == 0) return true;
        return false;
    }

    public static void doGameInEachTurn() {
        moveUnits();
        for (User user : Game.getUsers()) {
            user.getGovernment().setGold2(user.getGovernment().getTaxRate() * user.getGovernment().getPopulation());
            user.getGovernment().setPopularity2(getPopularity2());
            if ((getAppleCount() + getMeetCount() + getBreadCount() + getCheeseCount()) <
                    ((double) (user.getGovernment().getFoodRate() + 2) / 2) * user.getGovernment().getPopulation()) {
                user.getGovernment().getAllResources().put(Resource.APPLE, (double) 0);
                user.getGovernment().getAllResources().put(Resource.MEAT, (double) 0);
                user.getGovernment().getAllResources().put(Resource.CHEESE, (double) 0);
                user.getGovernment().getAllResources().put(Resource.BREAD, (double) 0);
            } else {
                double a = ((double) (user.getGovernment().getFoodRate() + 2) / 2) * user.getGovernment().getPopulation();
                if (user.getGovernment().getAllResources().get(Resource.APPLE) < a) {
                    a -= user.getGovernment().getAllResources().get(Resource.APPLE);
                    user.getGovernment().getAllResources().put(Resource.APPLE, (double) 0);
                    user.getGovernment().removeFromStorage(Resource.APPLE,
                            user.getGovernment().getAllResources().get(Resource.APPLE));
                    if (user.getGovernment().getAllResources().get(Resource.MEAT) < a) {
                        a -= user.getGovernment().getAllResources().get(Resource.MEAT);
                        user.getGovernment().getAllResources().put(Resource.MEAT, (double) 0);
                        user.getGovernment().removeFromStorage(Resource.MEAT,
                                user.getGovernment().getAllResources().get(Resource.MEAT));
                        if (user.getGovernment().getAllResources().get(Resource.CHEESE) < a) {
                            a -= user.getGovernment().getAllResources().get(Resource.CHEESE);
                            user.getGovernment().removeFromStorage(Resource.CHEESE,
                                    user.getGovernment().getAllResources().get(Resource.CHEESE));
                            user.getGovernment().getAllResources().put(Resource.CHEESE, (double) 0);
                            if (user.getGovernment().getAllResources().get(Resource.BREAD) < a) {
                                a -= user.getGovernment().getAllResources().get(Resource.BREAD);
                                user.getGovernment().getAllResources().put(Resource.BREAD, (double) 0);
                                user.getGovernment().removeFromStorage(Resource.BREAD,
                                        user.getGovernment().getAllResources().get(Resource.BREAD));
                            } else {
                                user.getGovernment().getAllResources().put(Resource.BREAD, user.getGovernment().getAllResources().get(Resource.BREAD) - a);
                                user.getGovernment().removeFromStorage(Resource.BREAD, a);
                                a = 0;
                            }
                        } else {
                            user.getGovernment().getAllResources().put(Resource.CHEESE, user.getGovernment().getAllResources().get(Resource.CHEESE) - a);
                            user.getGovernment().removeFromStorage(Resource.CHEESE, a);
                            a = 0;
                        }
                    } else {
                        user.getGovernment().getAllResources().put(Resource.MEAT, user.getGovernment().getAllResources().get(Resource.MEAT) - a);
                        user.getGovernment().removeFromStorage(Resource.MEAT, a);
                        a = 0;
                    }
                } else {
                    user.getGovernment().getAllResources().put(Resource.APPLE, user.getGovernment().getAllResources().get(Resource.APPLE) - a);
                    user.getGovernment().removeFromStorage(Resource.APPLE, a);
                    a = 0;
                }
            }
            nextTurnForBuildings(user);
        }
        unitsAttack();
        checkGameConditions();
    }

    public static void setNextUser() {
        User nextUser = null;
        for (User user : Game.getUsers()) {
            if (user.equals(Game.getCurrentUser())) {
                nextUser = Game.getUsers().get((Game.getUsers().indexOf(user) + 1) % Game.getUsers().size());
                break;
            }
        }
        Game.setCurrentUser(nextUser);
    }

    public static GameMenuMessage dropUnit(int x, int y, String t, int c) {
        UnitType unitType = null;
        for (UnitType value : UnitType.values()) {
            if (value.getType().equals(t)) {
                unitType = value;
                break;
            }
        }
        if (unitType != null) {
            for (int i = 0; i < c; i++) {
                if (unitType.equals(UnitType.ENGINEER)) {
                    new Engineer(x, y);
                } else {
                    new Unit(unitType, x, y);
                }
            }
        }
        return GameMenuMessage.SUCCESS;
    }
}
