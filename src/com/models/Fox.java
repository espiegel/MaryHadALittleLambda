package com.models;

import javafx.scene.image.Image;
import com.Location;

public class Fox extends MapObject {
    // Image by PinedaVX: http://www.rpgmakervx.net/index.php?showtopic=9422
    static final Image FOX = SpriteView.loadImage("../images/fox.png");
    public Fox(Location loc) {
        super(FOX, loc);
        startAnimation();
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
