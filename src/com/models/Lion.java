package com.models;

import com.Location;
import com.Main;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;


public class Lion extends SpriteView {
    Label label = new Label();
    Location origin;
    static final Image LION = loadImage("../images/extra/lion.png");
    public Lion(Location loc) {
        super(LION, loc);
        origin = loc;
        label.textProperty().bind(number.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        getChildren().add(label);
    }

    public void moveToOrigin() {
        moveTo(origin);
    }

    public void moveToLocation(int x, int y) {
        moveTo(new Location(x, y));
    }

    public void incNumber() {
        number = new SimpleIntegerProperty(number.getValue() + 1);
        label.textProperty().bind(number.asString());
        this.resizeRelocate(getLocation().getX(), getLocation().getY(), getWidth() * 2, getHeight() * 2);
    }
}
