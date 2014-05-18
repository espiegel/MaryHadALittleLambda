package com.models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import com.Location;
import com.Main;

import java.util.function.Predicate;

public class Church extends MapObject {
    // Image by LovelyBlue: http://l0velyblue.deviantart.com/art/Church-350736943
    static final Image CHURCH = SpriteView.loadImage("images/church.png");
    LongProperty mealsServed = new SimpleLongProperty();
    public Church(Location loc) {
        super(CHURCH, loc);
        Label label = new Label();
        label.textProperty().bind(mealsServed.asString());
        label.setFont(Font.font("Impact", 12 * Main.SCALE));
        label.setTranslateX(-8 * Main.SCALE);
        label.setTranslateY(3 * Main.SCALE);
        getChildren().add(label);
    }
    @Override
    public void visit(Shepherd s) {
        Predicate<SpriteView> pure =
                a -> a.getColor() == null;

        mealsServed.set(mealsServed.get() +
                        s.getAnimals().filtered(pure).size()
        );

        s.getAnimals().removeIf(pure);
    }
}
