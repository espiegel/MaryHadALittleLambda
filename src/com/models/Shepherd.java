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
    public static int stepCounts=0;


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


        if(!isOnBoard(direction))
            return;


        stepCounts = (stepCounts+1)%2;


        if (0==stepCounts){
            System.out.println("asdfsaf");
        }

        moveTo(location.getValue().offset(direction.getXOffset(), direction.getYOffset()));
        animals.stream().reduce(
                location.get(), (loc, sprt) -> {
                    sprt.moveTo(loc);
                    return sprt.location.get();
                }, (loc1, loc2) -> loc1);
    }

    protected boolean isOnBoard(Direction d){
        int xPos = location.getValue().getX();
        int yPos = location.getValue().getY();
        int horizontal_limit = Main.HORIZONTAL_CELLS-1;
        int vertical_limit = Main.VERTICAL_CELLS-1;
        boolean isOnBoard = true;

        if ((xPos==0)&&(d.getXOffset() == Direction.LEFT.getXOffset())){
            isOnBoard=false;
        }
        else if ((yPos==0)&&(d.getYOffset() == Direction.UP.getYOffset())){
            isOnBoard=false;
        }
        else if ((yPos==vertical_limit)&&(d.getYOffset() == Direction.DOWN.getYOffset())){
            isOnBoard=false;
        }
        else if ((xPos==horizontal_limit)&&(d.getXOffset() == Direction.RIGHT.getXOffset())){
            isOnBoard=false;
        }


        return isOnBoard;
    }
}
