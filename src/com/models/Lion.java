package com.models;


import com.Direction;
import com.Location;
import com.Main;
import javafx.animation.Animation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

/**
 * Created by sleeprate on 16/11/14.
 */
public class Lion extends Shepherd{
// Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    public int shipEatCount=0;
    static final Image LION = loadImage("../images/extra/lion.png");
    protected final Label label = new Label();
    public Lion(Location loc) {
        super(LION, loc);
        label.textProperty().bind(number.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        getChildren().add(label);
    }

    public void move(Direction direction){
        if(walking != null && walking.getStatus().equals(Animation.Status.RUNNING))
            return;

        number.setValue(shipEatCount);
        label.textProperty().bind(number.asString());



        if(!isOnBoard(direction))
            return;


        moveTo(location.getValue().offset(direction.getXOffset(), direction.getYOffset()));
        getAnimals().stream().reduce(
                location.get(), (loc, sprt) -> {
                    sprt.moveTo(loc);
                    return sprt.location.get();
                }, (loc1, loc2) -> loc1);


    }
}


