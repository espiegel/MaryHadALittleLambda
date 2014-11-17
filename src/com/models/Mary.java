package com.models;

import com.Location;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Mary extends Shepherd {
    // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    static final Image MARY = loadImage("../images/mary.png");
    public Mary(Location loc) {
        super(MARY, loc);
    }

    public boolean isValidKey(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
            case DOWN:
            case RIGHT:
            case LEFT:
                return true;
        }
        return false;
    }

    public Location getNextLocation(KeyCode keyCode) {
        int x = getLocation().getX();
        int y = getLocation().getY();
        switch (keyCode) {
            case UP:
                return new Location(x, y - 1);
            case DOWN:
                return new Location(x, y + 1);
            case RIGHT:
                return new Location(x + 1, y);
            case LEFT:
                return new Location(x - 1, y);
        }
        return null; // Not going to happen
    }
}
