package com.models;

import com.Location;
import com.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public abstract class NumberedSpriteView extends SpriteView {
    protected final Label label = new Label();
    public NumberedSpriteView(Image spriteSheet, Location loc) {
        super(spriteSheet, loc);
        label.textProperty().bind(number.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        getChildren().add(label);
    }
    public NumberedSpriteView(Image spriteSheet, SpriteView following) {
        super(spriteSheet, following);
        label.textProperty().bind(number.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        getChildren().add(label);
    }
}
