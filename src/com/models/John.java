package com.models;

import com.Location;
import javafx.scene.image.Image;

/**
 * Created by udi on 6/5/14.
 */
public class John extends Shepherd {

    static final Image JOHN = loadImage("../images/extra/guy1.png");
    public John(Location loc) {
        super(JOHN, loc);

    }


}
