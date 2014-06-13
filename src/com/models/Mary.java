package com.models;

import com.Location;
import javafx.scene.image.Image;

public class Mary extends Shepherd {
    // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    static final Image MARY = loadImage("../images/mary.png");

    public Mary(Location loc) {
        super(MARY, loc);
    }
}
