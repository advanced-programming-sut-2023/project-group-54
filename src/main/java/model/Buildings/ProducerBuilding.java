package model.Buildings;

import model.Game;
import model.Government;
import model.Resource;

import java.util.HashMap;

public class ProducerBuilding extends Building {
    private ProducerType producerType;
    private Resource currentOutput;

    public ProducerBuilding(ProducerType producerType, int x1Position, int x2Position, int y1Position, int y2Position, Government government) {
        super(producerType.getBuildingType(), producerType.getBuildingType().getMaxHp(), government, x1Position, x2Position, y1Position, y2Position);
        this.producerType = producerType;
        if(!(producerType.getPuts() == null)){
            for (HashMap<Resource, Double> input : producerType.getPuts().keySet()) {
                for (Resource resource : producerType.getPuts().get(input).keySet()) {
                    currentOutput = resource;
                    return;
                }
            }
        }else{
            currentOutput = null;
        }
    }

    public ProducerType getProducerType() {
        return producerType;
    }

    public Resource getCurrentOutput() {
        return currentOutput;
    }

    public boolean changeOutput(Resource resource) {
        boolean canProduceResource = false;
        for (HashMap<Resource, Double> input : producerType.getPuts().keySet()) {
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
