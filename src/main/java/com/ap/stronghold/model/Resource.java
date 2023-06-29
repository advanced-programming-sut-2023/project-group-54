package com.ap.stronghold.model;

import com.ap.stronghold.model.Buildings.StorageType;

public enum Resource {
    STONE("stone",StorageType.STOCK_PILE, 14, 7,"com/ap/stronghold/Media/stone.png"), //سنگ
    WOOD("wood",StorageType.STOCK_PILE, 4, 1,"com/ap/stronghold/Media/wood.png"), //چوب
    IRON("iron",StorageType.STOCK_PILE, 25, 23,"com/ap/stronghold/Media/iron.png"), //آهن
    WHEAT("wheat",StorageType.STOCK_PILE, 23, 8,"com/ap/stronghold/Media/wheat.png"), //گندم
    FLOUR("flour",StorageType.STOCK_PILE, 32, 10,"com/ap/stronghold/Media/flour.png"), //آرد
    HOPS("hops",StorageType.STOCK_PILE, 15, 8,"com/ap/stronghold/Media/hops.png"), //چو
    ALE("ale",StorageType.STOCK_PILE, 20, 10,"com/ap/stronghold/Media/ale.png"), //آبجو
    PITCH("pitch",StorageType.STOCK_PILE, 20, 10,"com/ap/stronghold/Media/pitch.png"), //قیر
    MEAT("meat",StorageType.GRANARY, 8, 4,"com/ap/stronghold/Media/meat.png"), //گوشت
    APPLE("apple",StorageType.GRANARY, 8, 4,"com/ap/stronghold/Media/apple.png"), //سیب
    CHEESE("cheese",StorageType.GRANARY, 8, 4,"com/ap/stronghold/Media/cheese.png"), //پنیر
    BREAD("bread",StorageType.GRANARY, 8, 4,"com/ap/stronghold/Media/bread.png"), //نون
    BOW("bow",StorageType.ARMOURY, 31, 15,"com/ap/stronghold/Media/bow.png"),
    CROSSBOW("crossbow",StorageType.ARMOURY, 58, 30,"com/ap/stronghold/Media/crossbow.png"),
    SPEAR("spear",StorageType.ARMOURY, 20, 10,"com/ap/stronghold/Media/spear.png"),
    PIKE("pike",StorageType.ARMOURY, 36, 18,"com/ap/stronghold/Media/pike.png"),
    MACE("mace",StorageType.ARMOURY, 58, 30,"com/ap/stronghold/Media/mace.png"),
    SWORDS("swords",StorageType.ARMOURY, 58, 30,"com/ap/stronghold/Media/swords.png"),
    METAL_ARMOR("metal",StorageType.ARMOURY, 58, 30,"com/ap/stronghold/Media/metal.png"),
    LEATHER_ARMOR("leather",StorageType.ARMOURY, 25, 12,"com/ap/stronghold/Media/leather.png"),
    HORSE("horse",null, 0, 0,null),
    COW("cow",null, 0, 0,null);

    private final StorageType storageType;
    private final int buyPrice;
    private final int sellPrice;
    private final String name;
    private final String imagePath;

    Resource(String name,StorageType storageType, int buyPrice, int sellPrice,String imagePath){
        this.storageType = storageType;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.name=name;
        this.imagePath=imagePath;
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
    public String getImagePath(){return imagePath;}
}
