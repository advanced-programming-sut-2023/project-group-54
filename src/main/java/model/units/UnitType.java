package model.units;

import model.Buildings.ProducerType;
import model.Resource;

import java.util.ArrayList;
import java.util.List;

public enum UnitType {
    ARCHER("archer", ProducerType.BARRACKS, 12, new ArrayList<>(List.of(Resource.BOW)),
            3, 20, 350, 40, false, true),
    CROSSBOWMAN("crossbowman", ProducerType.BARRACKS, 20, new ArrayList<>(List.of(Resource.CROSSBOW,
            Resource.LEATHER_ARMOR)), 2, 15, 460, 60, false, false),
    SPEARMAN("spearman", ProducerType.BARRACKS, 8, new ArrayList<>(List.of(Resource.SPEAR)),
            3, 1, 350, 50, false, true),
    PIKEMAN("pikeman", ProducerType.BARRACKS, 20, new ArrayList<>(List.of(Resource.PIKE,
            Resource.METAL_ARMOR)), 2, 1, 600, 75, false, false),
    MACEMAN("maceman", ProducerType.BARRACKS, 20, new ArrayList<>(List.of(Resource.MACE,
            Resource.LEATHER_ARMOR)), 4, 1, 460, 100, false, true),
    SWORDSMAN("swordsman", ProducerType.BARRACKS, 40, new ArrayList<>(List.of(Resource.SWORDS,
            Resource.METAL_ARMOR)), 1, 1, 800, 150, false, false),
    KNIGHT("knight", ProducerType.BARRACKS, 40, new ArrayList<>(List.of(Resource.SWORDS,
            Resource.METAL_ARMOR, Resource.HORSE)), 5, 1, 900, 140, false, false),
    TUNNELER("tunneler", ProducerType.ENGINEERS_GUILD, 30, new ArrayList<>(),
            4, 1, 150, 50, false, false),
    LADDERMAN("ladderman", ProducerType.ENGINEERS_GUILD, 4, new ArrayList<>(),
            4, 1, 150, 20, false, false),
    ENGINEER("engineer", ProducerType.ENGINEERS_GUILD, 30, new ArrayList<>(),
            3, 1, 150, 20, false, false),
    BLACK_MONK("black_monk", ProducerType.CATHEDRAL, 10, new ArrayList<>(),
            2, 1, 500, 30, false, false),
    ARABIAN_BOW("arabian bow", ProducerType.MERCENARY_POST, 75, new ArrayList<>(),
            3, 20, 400, 50, false, false),
    SLAVE("slave", ProducerType.MERCENARY_POST, 5, new ArrayList<>(),
            4, 1, 150, 30, false, false),
    SLINGER("slinger", ProducerType.MERCENARY_POST, 12, new ArrayList<>(),
            4, 12, 250, 35, false, false),
    ASSASSIN("assassin", ProducerType.MERCENARY_POST, 60, new ArrayList<>(),
            3, 1, 400, 100, true, false),
    HORSE_ARCHER("horse archer", ProducerType.MERCENARY_POST, 80, new ArrayList<>(),
            5, 25, 460, 50, false, false),
    ARABIAN_SWORDSMAN("arabian swordsman", ProducerType.MERCENARY_POST, 80, new ArrayList<>(),
            1, 1, 650, 130, false, false),
    FIRE_THROWERS("fire throwers", ProducerType.MERCENARY_POST, 100, new ArrayList<>(),
            3, 10, 250, 90, false, false);

    private final String type;
    private final ProducerType producerBuilding;
    private final int goldNeeded;
    private final ArrayList<Resource> resourcesNeeded;
    private final int speed;
    private final int range;
    private final int maxHp;
    private final int damage;
    private final Boolean canUp;
    private final Boolean canUpFromLadder;

    private UnitType(String type, ProducerType producerBuilding, int goldNeeded, ArrayList<Resource> resourcesNeeded, int speed, int range, int maxHp, int damage, boolean canUp, boolean canUpFromLadder) {
        this.type = type;
        this.producerBuilding = producerBuilding;
        this.goldNeeded = goldNeeded;
        this.resourcesNeeded = resourcesNeeded;
        this.speed = speed;
        this.range = range;
        this.maxHp = maxHp;
        this.damage = damage;
        this.canUp = canUp;
        this.canUpFromLadder = canUpFromLadder;
    }

    public String getType() {
        return type;
    }

    public ProducerType getProducerBuilding() {
        return producerBuilding;
    }

    public int getGoldNeeded() {
        return goldNeeded;
    }

    public ArrayList<Resource> getResourcesNeeded() {
        return resourcesNeeded;
    }

    public int getSpeed() {
        return speed;
    }

    public int getRange() {
        return range;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getDamage() {
        return damage;
    }
}
