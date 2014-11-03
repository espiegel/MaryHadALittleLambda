package com.models;

import com.Location;
import javafx.scene.image.Image;

/**
 * Created by sleeprate on 03/11/14.
 */
public class John extends Shepherd {
    // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    static final Image JOHN = loadImage("../images/extra/guy1.png");
    public John(Location loc) {
        super(JOHN, loc);
    }
}
