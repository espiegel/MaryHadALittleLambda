package com.models;

import com.Main;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import com.Location;
import javafx.scene.text.Font;

public class Lion extends MapObject {
    // Image by PinedaVX: http://www.rpgmakervx.net/index.php?showtopic=9422
    static final Image LION = SpriteView.loadImage("../images/extra/lion.png");
    public static final Label label = new Label();
    public Lion(Location loc) {
        super(LION, loc);
        label.textProperty().bind(number.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        getChildren().add(label);
        startAnimation();
    }
    @Override
    public void visit(Shepherd shepherd) {
        /*
        Double mealSize = shepherd.getAnimals()
                .stream()
                .mapToDouble(SpriteView::getScaleX)
                .reduce(0.0, Double::sum);

        setScaleX(getScaleX() + mealSize * .2);
        setScaleY(getScaleY() + mealSize * .2);
        shepherd.getAnimals().clear();
        number.set(number.getValue().intValue()+1);
        label.textProperty().bind(number.asString());
        */

    }
}