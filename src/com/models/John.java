package com.models;

import com.Location;
import javafx.scene.image.Image;

public class John extends Shepherd {
    static final Image JOHN = loadImage("../images/extra/guy1.png");
    public John(Location loc) {
        super(JOHN, loc);
    }
}
