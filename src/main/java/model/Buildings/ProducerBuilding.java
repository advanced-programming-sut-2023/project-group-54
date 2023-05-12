package model.Buildings;

import model.Game;
import model.Resource;

import java.util.HashMap;

public class ProducerBuilding extends Building {
    private ProducerType producerType;
    private Resource currentOutput;

    public ProducerBuilding(ProducerType producerType) {
        super(producerType.getBuildingType(), producerType.getBuildingType().getMaxHp(), Game.getCurrentUser().getGovernment());
        this.producerType = producerType;
        for (HashMap<Resource, Integer> input : producerType.getPuts().keySet()) {
            for (Resource resource : producerType.getPuts().get(input).keySet()) {
                currentOutput = resource;
                return;
            }
        }
    }

    public boolean changeOutput(Resource resource) {
        boolean canProduceResource = false;
        for (HashMap<Resource, Integer> input : producerType.getPuts().keySet()) {
            if (producerType.getPuts().get(input).get(resource) != null) {
                canProduceResource = true;
            }
        }
        if(canProduceResource)
            currentOutput = resource;
        else return false;
        return true;
    }
}
