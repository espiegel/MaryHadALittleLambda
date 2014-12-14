package com.models;

import com.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public abstract class NumberedSpriteView extends SpriteView {
    protected final Label label = new Label();
    public NumberedSpriteView(Image spriteSheet, SpriteView following, int TYPE) {
        super(spriteSheet, following, TYPE);
        label.textProperty().bind(number.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        getChildren().add(label);
    }
}
