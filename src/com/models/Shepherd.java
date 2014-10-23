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
    protected AvatarType Type;
    public ObservableList<SpriteView> getAnimals() {
        return animals;
    }
    public Shepherd(Image spriteSheet, Location loc,AvatarType type) {
        super(spriteSheet, loc);
        Type = type;
        animals = FXCollections.observableArrayList();
        animals.addListener((ListChangeListener) c -> {
            ObservableList<Node> children = ((Group) getParent()).getChildren();
            while (c.next()) {
                if (c.wasAdded() || c.wasRemoved() || c.wasReplaced()) {
                    children.removeAll(c.getRemoved());
                    children.addAll(c.getAddedSubList());
                    SpriteView prev = this;
                    int number = 0;
                    for (SpriteView a : animals) {
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
            if (object != null) {
                object.visit(this);
            }
        };
    }
    public AvatarType getMyType(){
        return Type;
    }
    public void move(Direction direction) {
        if (walking != null && walking.getStatus().equals(Animation.Status.RUNNING))
            return;
        if(!legalStep(direction)){
            //do nothing
            return;
        }
        moveTo(location.getValue().offset(direction.getXOffset(), direction.getYOffset()));
        animals.stream().reduce(
                location.get(),                (loc, sprt) -> {
                    sprt.moveTo(loc);
                    return sprt.location.get();
                }, (loc1, loc2) -> loc1);
    }

    private boolean legalStep(Direction dir){
        if(location.getValue().getX()+dir.getXOffset()>=Main.HORIZONTAL_CELLS){
            return false;
        }
        else if(location.getValue().getY()+dir.getYOffset()>=Main.VERTICAL_CELLS){
            return false;
        }
        else if(location.getValue().getX()+dir.getXOffset()<0) {
            return false;
        }
        else if(location.getValue().getY()+dir.getYOffset()<0){
            return false;
        }

        return true;
    }
}
