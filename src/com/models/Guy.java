package com.models;

import com.Location;
import javafx.scene.image.Image;

/**
 * Created by Eidan on 6/4/2014.
 */
public class Guy extends Shepherd{
    static final Image Guy = loadImage("../images/extra/guy1.png");

    public Guy(Location loc) {
        super(Guy, loc);
    }
}
