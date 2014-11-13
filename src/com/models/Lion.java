package com.models;

import com.Location;
import com.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public class Lion extends Shepherd {
    // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    static final Image LION = loadImage("../images/extra/lion.png");
    protected final Label label = new Label();
    boolean isAwake; // Is lion awake?

    public Lion(Location loc) {
        super(LION, loc);
        updateNumber();
        isAwake = false;

    }

    private void updateNumber() {
        label.textProperty().bind(number.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        getChildren().add(label);
    }

    public boolean getIsAwake() {
        return isAwake;
    }

    public void wakeLion() {
        this.countMoves=0;
        isAwake = true;
    }

    public void unAwakeLion() {
        isAwake = false;
    }

    public void eatLamb() {
        findNearestLamb();
        // TODO : removeLambFromMap();
        updateLionState();

        // TODO : if last lamb was eaten, invoke unAwakeLion();

    }
    public void findNearestLamb() {
        MapObject object = Main.map[location.get().getX()][location.get().getY()];
        if(object != null) {
            object.visit(this);
        }

    };

    public void updateLionState() {
        // update number
        number.set(number.get() + 1);
        updateNumber();

        // TODO:: increase lion image size



    }
}
