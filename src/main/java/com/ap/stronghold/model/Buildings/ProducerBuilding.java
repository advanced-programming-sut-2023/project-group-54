package com.ap.stronghold.model.Buildings;

import com.ap.stronghold.model.Government;
import com.ap.stronghold.model.Resource;

import java.util.HashMap;

public class ProducerBuilding extends Building {
    private ProducerType producerType;
    private Resource currentOutput;

    public ProducerBuilding(ProducerType producerType, int x1Position, int x2Position, int y1Position, int y2Position, Government government) {
        super(producerType.getBuildingType(), producerType.getBuildingType().getMaxHp(), government, x1Position, x2Position, y1Position, y2Position);
        this.producerType = producerType;
        if (!(producerType.getPuts() == null)) {
            for (HashMap<Resource, Double> input : producerType.getPuts().keySet()) {
                for (Resource resource : producerType.getPuts().get(input).keySet()) {
                    currentOutput = resource;
                    return;
                }
            }
        } else {
            currentOutput = null;
        }
    }

    public ProducerType getProducerType() {
        return producerType;
    }

    public Resource getCurrentOutput() {
        return currentOutput;
    }

    public Double getProductionRate() {
//        HashMap<Resource, Double> inputs = null;
        HashMap<Resource, Double> outputs = null;
        double rateDown = 0;
        if (producerType.getPuts() != null) {
            for (HashMap<Resource, Double> resourceDoubleHashMap : producerType.getPuts().keySet()) {
                for (Resource resource : producerType.getPuts().get(resourceDoubleHashMap).keySet()) {
                    if (resource.equals(currentOutput)) {
//                    inputs = resourceDoubleHashMap;
                        outputs = producerType.getPuts().get(resourceDoubleHashMap);
                    }
                }
            }
        }
//        double rateUp = 0;
//        if(inputs != null){
//            for (Resource resource : inputs.keySet()) {
//                rateUp += inputs.get(resource);
//            }
//        }
        if (outputs != null) {
            for (Resource resource : outputs.keySet()) {
                rateDown += outputs.get(resource);
            }
        }
        return rateDown;
    }

    public boolean changeOutput(Resource resource) {
        boolean canProduceResource = false;
        for (HashMap<Resource, Double> input : producerType.getPuts().keySet()) {
            if (producerType.getPuts().get(input).get(resource) != null) {
                canProduceResource = true;
            }
        }
        if (canProduceResource)
            currentOutput = resource;
        else return false;
        return true;
    }
}
