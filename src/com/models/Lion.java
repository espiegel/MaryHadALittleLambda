package com.models;

import com.Direction;
import com.Main;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

public class Lion extends NumberedSpriteView {
    // Image by Mack: http://www.rpgmakervx.net/index.php?showtopic=15704
    static final Image LAMB = loadImage("../images/lion.png");
    static private boolean stepCounter = true;
    private ChangeListener<Direction> directionListener = (ov, o, o2) -> {
        if (o2 != null) {
            if(stepCounter == true)
            {
                stepCounter = false;
            }
            else
            {
                stepCounter = true;
            }
        }
        if(stepCounter)
        {
            //label.setTranslateX();
        }
    };
    public Lion(SpriteView following) {
        super(LAMB, following);
        direction.addListener(directionListener);
        directionListener.changed(direction, direction.getValue(), direction.getValue());
    }
}
