package com.models;

import com.Direction;
import com.Location;
import com.Main;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by sleeprate on 15/12/14.
 */
public class Lion extends NumberedSpriteView{

    static Image LION = loadImage("../images/extra/lion.png");
    double scale = 1;
    public Lion(Location loc) {
        super(LION, loc);
        number.set(0);
    }
    public void chase(ObservableList<SpriteView> mary_lambs,ObservableList<SpriteView> john_lambs, int shep_walk){
        if(john_lambs.isEmpty() && mary_lambs.isEmpty()) return;
        SpriteView tmp_lmb = null;
        //Location tmp_lmb.location.get() = new Location(location.get().getX(),location.get().getY());
        int min = Integer.MAX_VALUE;
        if(!mary_lambs.isEmpty())
        {
            for(SpriteView s: mary_lambs){
                if (location.get().distance(s.location.get()) < min) {
                    tmp_lmb = s;
                    min =location.get().distance(tmp_lmb.location.get());
                }
        }
        }
        if(!john_lambs.isEmpty())
        {
            for(SpriteView s: john_lambs) {
                if(location.get().distance(s.location.get()) < min) {
                    tmp_lmb = s;
                    min = location.get().distance(tmp_lmb.location.get());
                }
            }
        }
        if (tmp_lmb!=null) {
            Direction dir = location.get().directionTo(tmp_lmb.location.get());
            if(min == 0) {
                number.set(getNumber() + 1);
                scale *= 1.05;
                LION = loadImage("../images/extra/lion.png",scale);
                imageView.setImage(LION);
                spriteWidth = (int) (LION.getWidth() / 3);
                spriteHeight = (int) (LION.getHeight() / 4);
                imageView.setViewport(new Rectangle2D(frame.get() * spriteWidth, direction.get().getOffset() * spriteHeight, spriteWidth, spriteHeight));
                mary_lambs.remove(tmp_lmb);
                john_lambs.remove(tmp_lmb);
            }
        if (shep_walk % 2 == 0 && min!=0)moveTo(location.getValue().offset(dir.getXOffset(), dir.getYOffset()));
        }
    }

}
