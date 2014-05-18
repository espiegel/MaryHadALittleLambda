package com.models;

import com.Location;
import javafx.scene.image.Image;

/**
 * Created by eidan on 5/18/14.
 */
public class Lion extends MapObject {
    // Image by PinedaVX: http://www.rpgmakervx.net/index.php?showtopic=9422
    static final Image Lion = SpriteView.loadImage("../images/extra/lion.png");
    public Lion(Location loc) {
        super(Lion, loc);
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
