package com.models;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import com.Location;

public class Rainbow extends MapObject {
    static final Image RAINBOW = SpriteView.loadImage("../images/rainbow.png");
    public Rainbow(Location loc) {
        super(RAINBOW, loc);
        startAnimation();
    }
    @Override
    public void visit(Shepherd s) {
        s.getAnimals().stream()
                .filter(a -> a.getNumber() % 4 == 1)
                .forEach(a -> a.setColor(null));
        s.getAnimals().stream()
                .filter(a -> a.getNumber() % 4 == 2)
                .forEach(a -> a.setColor(Color.YELLOW));
        s.getAnimals().stream()
                .filter(a -> a.getNumber() % 4 == 3)
                .forEach(a -> a.setColor(Color.CYAN));
        s.getAnimals().stream()
                .filter(a -> a.getNumber() % 4 == 0)
                .forEach(a -> a.setColor(Color.GREEN));
    }
}