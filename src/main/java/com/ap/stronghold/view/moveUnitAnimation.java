package com.ap.stronghold.view;

import com.ap.stronghold.model.Map;
import javafx.animation.Transition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class moveUnitAnimation extends Transition {
    Pane gamePane;
    ArrayList<Map> path;
    int x;
    int y;
    int index;
    ImageView unit;
    public moveUnitAnimation(Pane pane, ArrayList<Map> path, int x1, int y1, ImageView unit) {
        gamePane = pane;
        this.path = path;
        this.unit = unit;
        this.setCycleDuration(Duration.millis(1000));
        this.setCycleCount(path.size());
        index = 0;
        x = x1;
        y = y1;
    }

    @Override
    protected void interpolate(double v) {
        int toX = path.get(index).getX();
        int toY = path.get(index).getY();
        if(Math.abs(toX - x) == 1){
            
        }
    }
}
