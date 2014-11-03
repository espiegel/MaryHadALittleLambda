package com.models;

import com.Direction;
import com.Location;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

public class Lion extends NumberedSpriteView {
    // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    static final Image LION = loadImage("../images/extra/lion.png");

    private ChangeListener<Direction> directionListener = (ov, o, o2) -> {

    };

    public Lion(SpriteView following) {
        super(LION, following);
        direction.addListener(directionListener);
        directionListener.changed(direction, direction.getValue(), direction.getValue());
    }
}
