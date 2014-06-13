package com.models;

import com.Direction;
import javafx.animation.Animation;
import javafx.scene.image.Image;
import com.Location;
import com.Main;

public abstract class MapObject extends SpriteView {

    public MapObject(Image spriteSheet, Location loc) {
        super(spriteSheet, loc);
        Main.map[loc.getX()][loc.getY()] = this;
    }

    public abstract void visit(Shepherd shepherd);

    public void move(Direction direction) {
        if (walking != null && walking.getStatus().equals(Animation.Status.RUNNING))
            return;
        moveTo(location.getValue().offset(direction.getXOffset(), direction.getYOffset()));
    }
}
