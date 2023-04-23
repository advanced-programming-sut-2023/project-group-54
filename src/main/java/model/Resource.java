package model;

import model.Buildings.StorageType;

public enum Resource {
    STONE(StorageType.STOCK_PILE, 14, 7), //سنگ
    WOOD(StorageType.STOCK_PILE, 4, 1), //چوب
    IRON(StorageType.STOCK_PILE, 25, 23), //آهن
    WHEAT(StorageType.STOCK_PILE, 23, 8), //گندم
    FLOUR(StorageType.STOCK_PILE, 32, 10), //آرد
    HOPS(StorageType.STOCK_PILE, 15, 8), //چو
    ALE(StorageType.STOCK_PILE, 20, 10), //آبجو
    PITCH(StorageType.STOCK_PILE, 20, 10), //قیر
    MEAT(StorageType.GRANARY, 8, 4), //گوشت
    APPLE(StorageType.GRANARY, 8, 4), //سیب
    CHEESE(StorageType.GRANARY, 8, 4), //پنیر
    BREAD(StorageType.GRANARY, 8, 4), //نون
    BOW(StorageType.ARMOURY, 31, 15),
    CROSSBOW(StorageType.ARMOURY, 58, 30),
    SPEAR(StorageType.ARMOURY, 20, 10),
    PIKE(StorageType.ARMOURY, 36, 18),
    MACE(StorageType.ARMOURY, 58, 30),
    SWORDS(StorageType.ARMOURY, 58, 30),
    METAL_ARMOR(StorageType.ARMOURY, 58, 30),
    LEATHER_ARMOR(StorageType.ARMOURY, 25, 12),
    HORSE(null, 0, 0),
    COW(null, 0, 0);

    private StorageType storageType;
    private int buyPrice;
    private int sellPrice;

    private Resource(StorageType storageType, int buyPrice, int sellPrice){
        this.storageType = storageType;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }
}
