package com.models;

import com.Direction;
import com.Main;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import com.Location;
import com.LionCounter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ObjectProperty;

public class Lion extends NumberedSpriteView {
    // Image by Mack: http://www.rpgmakervx.net/index.php?showtopic=15704
    static final Image LION = loadImage("../images/extra/lion.png");
    public ObjectProperty<LionCounter> lionCounter = new SimpleObjectProperty<>();

    private ChangeListener<Direction> directionListener = (ov, o, o2) -> {
        switch (o2) {
            case RIGHT:
                label.setTranslateX(-4 * Main.SCALE);
                label.setTranslateY(2 * Main.SCALE);
                break;
            case LEFT:
                label.setTranslateX(4 * Main.SCALE);
                label.setTranslateY(2 * Main.SCALE);
                break;
            case UP:
                label.setTranslateX(0);
                label.setTranslateY(-2 * Main.SCALE);
                break;
            case DOWN:
                label.setTranslateX(0);
                label.setTranslateY(-9 * Main.SCALE);
                break;
        }
    };

    /*
    private ChangeListener<LionCounter> preySeekerListener = (c1, c2, c3) ->{
        if( c2.getVal() >= 2 ||
                c3.getVal() >= 2){

            moveTo(new Location(
                    this.location.getValue().getX() -1, this.location.getValue().getY() -1) );
        }

    };
    */

    public Lion(SpriteView following) {
        super(LION, following);
        direction.addListener(directionListener);
        directionListener.changed(direction, direction.getValue(), direction.getValue());
    }

    public Lion(Location loc){
        super(LION, loc); // Super constructor
        //lionCounter.setValue(Main.lionCounter); //Bind to the static counter
        //Incharge of numbers?
        direction.addListener(directionListener);
        directionListener.changed(direction, direction.getValue(), direction.getValue());
        //Supposed to move lion:
        /*
        lionCounter.addListener(preySeekerListener);
        preySeekerListener.changed(lionCounter, lionCounter.getValue(),
                lionCounter.getValue());
                */

    }
}
