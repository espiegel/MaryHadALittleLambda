package com.models;

import com.Direction;
import com.Main;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

public class Lamb extends NumberedSpriteView {
    // Image by Mack: http://www.rpgmakervx.net/index.php?showtopic=15704
    static final Image LAMB = loadImage("../images/lamb.png");
    static final int TYPE = 7;
    private ChangeListener<Direction> directionListener = (ov, o, o2) -> {
        switch (o2) {
            case RIGHT:
                label.setTranslateX(-4 * Main.SCALE);
                label.setTranslateY(2 * Main.SCALE);
                break;
            case LEFT:
                label.setTranslateX(4 * Main.SCALE);
                label.setTranslateY(2 * Main.SCALE);
                break;
            case UP:
                label.setTranslateX(0);
                label.setTranslateY(-2 * Main.SCALE);
                break;
            case DOWN:
                label.setTranslateX(0);
                label.setTranslateY(-9 * Main.SCALE);
                break;
        }
    };
    public Lamb(SpriteView following) {
        super(LAMB, following, TYPE);
        direction.addListener(directionListener);
        directionListener.changed(direction, direction.getValue(), direction.getValue());
    }
}
