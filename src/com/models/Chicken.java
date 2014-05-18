package com.models;

import com.Direction;
import com.Main;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

public class Chicken extends NumberedSpriteView {
    // Image by LovelyBlue: http://l0velyblue.deviantart.com/art/Chicken-203764427
    static final Image CHICKEN = loadImage("images/chicken.png");
    private ChangeListener<Direction> directionListener = (ov, o, o2) -> {
        switch (o2) {
            case RIGHT:
                label.setTranslateX(0);
                label.setTranslateY(4 * Main.SCALE);
                break;
            case LEFT:
                label.setTranslateX(0);
                label.setTranslateY(4 * Main.SCALE);
                break;
            case UP:
                label.setTranslateX(0);
                label.setTranslateY(2 * Main.SCALE);
                break;
            case DOWN:
                label.setTranslateX(0);
                label.setTranslateY(9 * Main.SCALE);
                break;
        }
    };
    public Chicken(SpriteView following) {
        super(CHICKEN, following);
        colorOffset = 1;
        direction.addListener(directionListener);
        directionListener.changed(direction, direction.getValue(), direction.getValue());
    }
}
