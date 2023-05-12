package model.Buildings;

import model.Game;
import model.Resource;

public class ProducerBuilding extends Building {
    private ProducerType producerType;

    public ProducerBuilding(ProducerType producerType) {
        super(producerType.getBuildingType(), producerType.getBuildingType().getMaxHp(), Game.getCurrentUser().getGovernment());
        this.producerType = producerType;
    }

    public void changeOutput(Resource resource) {

    }
}
