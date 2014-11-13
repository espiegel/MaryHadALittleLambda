package com.models;

import com.Direction;
import com.Location;
import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import static java.lang.Thread.sleep;

public class Lion extends NumberedSpriteView {
    // Image by Mack: http://www.rpgmakervx.net/index.php?showtopic=15704
    static final Image LION = loadImage("../images/extra/lion.png");
    private boolean shouldMove = false;
    private boolean anotherMove = true;
    private Shepherd mary;
    private Shepherd john;

    public Lion(Location location, Shepherd mary, Shepherd john) {
        super(LION, location);
        this.mary = mary;
        this.john = john;
        number.setValue(0);

        mary.addLocationListener(new ChangeListener<Location>() {
            @Override
            public void changed(ObservableValue<? extends Location> observable, Location oldValue, Location newValue) {
                if (shouldMove)
                    prowl();
                shouldMove=!shouldMove;
            }
        });
        john.addLocationListener(new ChangeListener<Location>() {
            @Override
            public void changed(ObservableValue<? extends Location> observable, Location oldValue, Location newValue) {
                //exactly the same, should probably make the class not anonymous, but not today.
                if (shouldMove)
                    prowl();
                shouldMove=!shouldMove;
            }
        });

        this.addLocationListener((observable, oldValue, newValue) -> {
            if (anotherMove) {
                prowl();
            }
            anotherMove=!anotherMove;
            eatIfOnSheep();
        });

    }
    private void prowl() {
        Location closestSheepLoc = findClosestSheep();
        if (closestSheepLoc==null)
            return;
//        System.out.println(getLocation().getX()+"::"+getLocation().getY());
        Direction direction = getLocation().directionTo(closestSheepLoc);
        while(walking != null && walking.getStatus().equals(Animation.Status.RUNNING));
        moveTo(location.getValue().offset(direction.getXOffset(), direction.getYOffset()));
    }



    private Location findClosestSheep() {
        Location current = this.getLocation();
        int minDist = Integer.MAX_VALUE;
        Location closestSheepLocation = null;
        ObservableList<SpriteView> joinedSheepList = FXCollections.observableArrayList();
        joinedSheepList.addAll(mary.getAnimals());
        joinedSheepList.addAll(john.getAnimals());
        if (joinedSheepList.isEmpty())
            return null;
        for (SpriteView sheep : joinedSheepList) {
            Location sheepLoc = sheep.getLocation();
            int dist = Math.abs(current.getX()-sheepLoc.getX()) + Math.abs(current.getY()-sheepLoc.getY());
            if (dist<minDist) {
                minDist = dist;
                closestSheepLocation = sheepLoc;
            }
        }
        return closestSheepLocation;
    }

    private void eatIfOnSheep() {
        Location current = getLocation();
//        System.out.println("-----");
//        System.out.println(current);
//        System.out.println("-----");
        ObservableList<SpriteView> marysList = mary.getAnimals();
        for (int i=0; i<marysList.size();i++) {
            SpriteView sheep = mary.getAnimals().get(i);
//            System.out.println(sheep.getLocation());
            if (sheep.getLocation().equals(current)) {
                anotherMove=false;
                number.setValue(getNumber()+1);
                marysList.remove(i);
                increaseSizeByFactor(1.05);
            }
        }



    }


}
