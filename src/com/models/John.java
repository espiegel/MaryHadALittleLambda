package com.models;

import com.Location;
import javafx.scene.image.Image;

public class John extends Shepherd {
    // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    private static final int TYPE = 1;
    static final Image JOHN = loadImage("../images/guy1.png");
    public John(Location loc) {
        super(JOHN, loc, TYPE);
    }

}
