package com.models;

import com.Location;
import com.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

/**
 * Created by sleeprate on 07/12/14.
 */
public class Lion extends Shepherd {
    Label label = new Label();

    static final Image Lion = loadImage("../images/extra/lion.png");
    public Lion(Location loc) {
        super(Lion, loc);
        label.textProperty().bind(number.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        getChildren().add(label);
    }
}



