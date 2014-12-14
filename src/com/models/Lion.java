package com.models;


import com.Direction;
import com.Main;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

public class Lion extends NumberedSpriteView {
    // Image by Mack: http://www.rpgmakervx.net/index.php?showtopic=15704
    static final Image LAMB = loadImage("../images/lion.png");
    static final int TYPE = 5;
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
    public Lion() {
        super(LAMB, null, TYPE);
        direction.addListener(directionListener);
        directionListener.changed(direction, direction.getValue(), direction.getValue());
    }
}


/*
import com.Location;
import javafx.scene.image.Image;

/**
 * Created by sleeprate on 14/12/14.
 *
public class Lion extends Shepherd {
    // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    private static final int TYPE = 2;
    static final Image JOHN = loadImage("../images/lion.png");
    public Lion(Location loc) {
        super(JOHN, loc, TYPE);
    }
}


*/