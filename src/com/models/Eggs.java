package com.models;

import javafx.scene.image.Image;

import java.util.stream.Stream;

public class Eggs extends NumberedSpriteView {
    static final Image EGGS = loadImage("images/eggs.png");
    public Eggs(SpriteView following) {
        super(EGGS, following);
    }
    public static Stream<SpriteView> hatch(SpriteView sv) {
        if (!(sv instanceof Eggs)) {
            return Stream.of(sv);
        }
        return Stream.iterate(sv, Chicken::new).skip(1).limit(3);
    }
}
