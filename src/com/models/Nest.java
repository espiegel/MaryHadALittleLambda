package com.models;

import javafx.scene.image.Image;
import com.Location;

import java.util.stream.Collectors;

public class Nest extends MapObject {
    // Image derived from Lokilech's Amselnest: http://commons.wikimedia.org/wiki/File:Amselnest_lokilech.jpg
    static final Image NEST = SpriteView.loadImage("images/nest.png");
    public Nest(Location loc) {
        super(NEST, loc);
    }
    @Override
    public void visit(Shepherd s) {
        s.getAnimals().setAll(s.getAnimals()
                        .stream().parallel()
                        .flatMap(Eggs::hatch)
                        .collect(Collectors.toList())
        );
    }
}