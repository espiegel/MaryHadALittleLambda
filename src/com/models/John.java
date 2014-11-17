package com.models;

import com.Location;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


public class John extends Shepherd {
    static final Image JOHN = loadImage("../images/extra/guy1.png");
    public John(Location loc) {
        super(JOHN, loc);
    }

    public boolean isValidKey(KeyCode keyCode) {
        switch (keyCode) {
            case W:
            case D:
            case S:
            case A:
            case Q:
            case E:
            case Z:
            case C:
                return true;
        }
        return false;
    }

    public Location getNextLocation(KeyCode keyCode) {
        int x = getLocation().getX();
        int y = getLocation().getY();
        switch (keyCode) {
            case W:
                return new Location(x, y - 1);
            case S:
                return new Location(x, y + 1);
            case D:
                return new Location(x + 1, y);
            case A:
                return new Location(x - 1, y);
            case Q:
                return new Location(x - 1, y -1);
            case E:
                return new Location(x + 1, y - 1);
            case C:
                return new Location(x + 1, y + 1);
            case Z:
                return new Location(x - 1, y + 1);
        }
        return null; // not going to happen
    }
}
