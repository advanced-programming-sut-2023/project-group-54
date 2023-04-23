package model.Buildings;

import model.Resources;

public class CharacterProducerBuilding {
    enum CharacterType{
        POPULARITY,
        PERSON,
        MONK;
    }

    enum CharacterProducer{
        ;
    }
    private Resources input;
    private CharacterType output;
    private StorageBuilding storage;
    private CharacterProducer characterProducer;
}
