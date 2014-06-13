package com.models;

import com.Location;
import javafx.scene.image.Image;

/**
 * Created by Eidan on 6/4/2014.
 */
public class Lion extends MapObject {
    static final Image LION = SpriteView.loadImage("../images/extra/lion.png");

    public Lion(Location loc) {
        super(LION, loc);
    }

    @Override
    public void visit(Shepherd shepherd) {
        Double mealSize = shepherd.getAnimals()
                .stream()
                .mapToDouble(SpriteView::getScaleX)
                .reduce(0.0, Double::sum);

        setScaleX(getScaleX() + mealSize * .2);
        setScaleY(getScaleY() + mealSize * .2);
        shepherd.getAnimals().clear();
    }
}
