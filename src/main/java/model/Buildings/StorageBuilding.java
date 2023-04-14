package model.Buildings;

import model.Resources;

import java.util.HashMap;

public class StorageBuilding {
    enum StorageType{
        ;
    }
    private int capacity;
    private StorageType storageType;
    private HashMap<Resources, Integer> storage;
}
