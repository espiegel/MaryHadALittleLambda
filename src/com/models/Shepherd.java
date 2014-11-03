package com.models;

import com.Direction;
import com.Location;
import com.Main;
import javafx.animation.Animation;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;

public class Shepherd extends SpriteView {
    private ObservableList<SpriteView> animals;

    public ObservableList<SpriteView> getAnimals() {
        return animals;
    }

    public Shepherd(Image spriteSheet, Location loc) {
        super(spriteSheet, loc);
        animals = FXCollections.observableArrayList();
        animals.addListener((ListChangeListener<Node>) c -> {
            ObservableList<Node> children = ((Group) getParent()).getChildren();
            while(c.next()) {
                if(c.wasAdded() || c.wasRemoved() || c.wasReplaced()) {
                    children.removeAll(c.getRemoved());
                    children.addAll(c.getAddedSubList());
                    SpriteView prev = this;
                    int number = 0;
                    for(SpriteView a : animals) {
                        a.following = prev;
                        a.number.set(++number);
                        prev.follower = a;
                        prev = a;
                    }
                }
            }
        });
        arrivalHandler = e -> {
            MapObject object = Main.map[location.get().getX()][location.get().getY()];
            if(object != null) {
                object.visit(this);
            }
        };
    }

    public void move(Direction direction) {

        if(walking != null && walking.getStatus().equals(Animation.Status.RUNNING))
            return;

       if(illegalMove(location.get().getX(), location.get().getY(), direction))
           return;

 //       count_moves ++;


        moveTo(location.getValue().offset(direction.getXOffset(), direction.getYOffset()));
        animals.stream().reduce(
                location.get(), (loc, sprt) -> {
                    sprt.moveTo(loc);
                    return sprt.location.get();
                }, (loc1, loc2) -> loc1);

     /*   if(count_moves == 2){
            lion.moveTo(closeLambDirection(lion.getLocation()));
            if(isLamb(lion.getLocation)){
                //lion eat Lamb
                lion.getNumber()
            }
            count_moves = 0;
        }*/
    }

 //   private Location closeLambDirection(Location loc){
//         return null;
  //  }

    private boolean illegalMove(int x, int y, Direction dir){

        if(x == 0 && dir.getXOffset() == -1)
            return true;
        if(x == Main.HORIZONTAL_CELLS-1 && dir.getXOffset() == 1)
            return true;
        if(y == 0 && dir.getYOffset() == -1)
            return true;
        if(y == Main.VERTICAL_CELLS-1 && dir.getYOffset() == 1)
            return true;
        return false;
    }
}
