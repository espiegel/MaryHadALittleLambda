package com.models;

import com.Direction;
import com.Location;
import com.Main;
import javafx.scene.image.Image;

/**
 * Created by eidan on 5/18/14.
 */
public class Lion extends NumberedSpriteView {
    // Image by PinedaVX: http://www.rpgmakervx.net/index.php?showtopic=9422
    static final Image Lion = SpriteView.loadImage("../images/extra/lion.png");

    private int moveQueue = 0;

    public Lion(Location loc) {
        super(Lion, loc, 0);

        arrivalHandler = event -> {
            for(Shepherd s : Main.getmShepherdList()) {
                for(SpriteView a : s.getAnimals()) {
                    if(a.getLocation().equals(getLocation())) {
                        eatLamb((Lamb)a);
                        break;
                    }
                }
            }

            if(getMoveQueue() > 0) {
                Direction dir = calculateMoveDirection();
                if(dir != null) {
                    move(dir);
                }
                reduceMoveQueue();
            }
        };
    }

    public void move(Direction direction) {
        moveTo(location.getValue().offset(direction.getXOffset(), direction.getYOffset()));
    }

    public void eatLamb(Lamb lamb) {
        System.out.println("Lion ate the lamb!");
        increaseNumber();
        lamb.getShepherd().getAnimals().remove(lamb);

       setScaleX(getScaleX() * 1.05);
       setScaleY(getScaleY() * 1.05);
    }

    public Direction calculateMoveDirection() {
        int lionX, lionY;

        lionX = getLocation().getX();
        lionY = getLocation().getY();

        double distance = Float.MAX_VALUE;
        SpriteView animal = null;

        for(Shepherd s : Main.getmShepherdList()) {
            for(SpriteView a : s.getAnimals()) {
                double tmpDistance = Math.sqrt(Math.pow(a.getLocation().getX() - lionX, 2) +
                        Math.pow(a.getLocation().getY() - lionY, 2));

                if(tmpDistance < distance) {
                    distance = tmpDistance;
                    animal = a;
                }
            }
        }

        if(animal != null) {
            int animalX = animal.getLocation().getX();
            int animalY = animal.getLocation().getY();

            if(animalX < lionX) {
                return Direction.LEFT;
            } else if(animalX > lionX) {
                return Direction.RIGHT;
            } else if(animalY < lionY) {
                return Direction.UP;
            }  else if(animalY > lionY) {
                return Direction.DOWN;
            }
        }

        return null;
    }

    public void moveLion(int num) {
        if(num <= 0) {
            return;
        }

        if(!isMoving()) {
            Direction dir = calculateMoveDirection();
            if(dir != null) {
                move(dir);
                moveQueue += num - 1;
            }
        } else {
            moveQueue += num;
        }
    }

    public int getMoveQueue() {
        return moveQueue;
    }

    public void reduceMoveQueue() {
        if(moveQueue > 0) {
            moveQueue--;
        }
    }
}
