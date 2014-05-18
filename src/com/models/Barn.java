package com.models;

import javafx.scene.image.Image;
import com.Location;

import java.util.stream.Stream;

/**
 * Created by eidan on 5/8/14.
 */
public class Barn extends MapObject {
    // Image by LovelyBlue: http://l0velyblue.deviantart.com/art/barncharset-350737104
    static final Image BARN = SpriteView.loadImage("../images/barn.png");
    public Barn(Location loc) {
        super(BARN, loc);
    }
    @Override
    public void visit(Shepherd s) {
        SpriteView tail = s.getAnimals().isEmpty() ?
                s : s.getAnimals().get(s.getAnimals().size() - 1);

        Stream.iterate(tail, Lamb::new)
                .skip(1).limit(7)
                .forEach(s.getAnimals()::add);
    }
}