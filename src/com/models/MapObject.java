package com.models;

import javafx.scene.image.Image;
import com.Location;
import com.Main;

public abstract class MapObject extends SpriteView {

    public MapObject(Image spriteSheet, Location loc) {
        super(spriteSheet, loc, -1);
        Main.map[loc.getX()][loc.getY()] = this;
    }

    public abstract void visit(Shepherd shepherd);
}
