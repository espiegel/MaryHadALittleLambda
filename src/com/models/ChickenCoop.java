package com.models;

import javafx.scene.image.Image;
import com.Location;

import java.util.stream.Collectors;

public class ChickenCoop extends MapObject {
    // Image by LovelyBlue: http://l0velyblue.deviantart.com/art/chickencoop-350736803
    static final Image CHICKEN_COOP = SpriteView.loadImage("../images/chicken-coop.png");
    public ChickenCoop(Location loc) {
        super(CHICKEN_COOP, loc);
    }
    @Override
    public void visit(Shepherd s) {
        // single map:
//            s.getAnimals().setAll(s.getAnimals()
//                .stream()
//                .map(sv -> new Eggs(sv.getFollowing())
//            ).collect(Collectors.toList()));
        // or a double map:
        s.getAnimals().setAll(s.getAnimals()
                        .stream().parallel()
                        .map(SpriteView::getFollowing)
                        .map(Eggs::new)
                        .collect(Collectors.toList())
        );
    }
}
