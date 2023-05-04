package model;

import model.Buildings.StorageType;

public enum Resource {
    STONE("stone",StorageType.STOCK_PILE, 14, 7), //سنگ
    WOOD("wood",StorageType.STOCK_PILE, 4, 1), //چوب
    IRON("iron",StorageType.STOCK_PILE, 25, 23), //آهن
    WHEAT("wheat",StorageType.STOCK_PILE, 23, 8), //گندم
    FLOUR("flour",StorageType.STOCK_PILE, 32, 10), //آرد
    HOPS("hops",StorageType.STOCK_PILE, 15, 8), //چو
    ALE("ale",StorageType.STOCK_PILE, 20, 10), //آبجو
    PITCH("pitch",StorageType.STOCK_PILE, 20, 10), //قیر
    MEAT("meat",StorageType.GRANARY, 8, 4), //گوشت
    APPLE("apple",StorageType.GRANARY, 8, 4), //سیب
    CHEESE("cheese",StorageType.GRANARY, 8, 4), //پنیر
    BREAD("bread",StorageType.GRANARY, 8, 4), //نون
    BOW("bow",StorageType.ARMOURY, 31, 15),
    CROSSBOW("crossbow",StorageType.ARMOURY, 58, 30),
    SPEAR("spear",StorageType.ARMOURY, 20, 10),
    PIKE("pike",StorageType.ARMOURY, 36, 18),
    MACE("mace",StorageType.ARMOURY, 58, 30),
    SWORDS("swords",StorageType.ARMOURY, 58, 30),
    METAL_ARMOR("metal",StorageType.ARMOURY, 58, 30),
    LEATHER_ARMOR("leather",StorageType.ARMOURY, 25, 12),
    HORSE("horse",null, 0, 0),
    COW("cow",null, 0, 0);

    private StorageType storageType;
    private int buyPrice;
    private int sellPrice;
    private String name;

    private Resource(String name,StorageType storageType, int buyPrice, int sellPrice){
        this.storageType = storageType;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.name=name;
    }
    public int getBuyPrice(){
        return buyPrice;
    }
    public int getSellPrice(){
        return sellPrice;
    }
    public String getName(){
        return name;
    }
    public static Resource getResourceByName(String name){
        for (Resource Resource : Resource.values())
            if(Resource.name.equals(name)) return Resource;
        return null;
    }

    public StorageType getStorageType() {
        return storageType;
    }
}
