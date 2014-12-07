package com.models;

import com.Location;
import javafx.scene.image.Image;

/**
 * Created by sleeprate on 07/12/14.
 */
public class John extends Shepherd {
    static final Image John = loadImage("../images/extra/guy1.png");
    public John(Location loc) {
        super(John, loc);
    }

}

