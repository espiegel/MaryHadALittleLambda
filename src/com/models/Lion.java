package com.models;

import com.Direction;
import com.Location;
import com.Main;
import javafx.animation.Animation;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Lion extends NumberedSpriteView {
    // Image by Mack: http://www.rpgmakervx.net/index.php?showtopic=15704
    static final Image LION = loadImage("../images/extra/lion.png");
    private ChangeListener<Direction> directionListener = (ov, o, o2) -> {
        switch (o2) {
            case RIGHT:
                label.setTranslateX(-4 * Main.SCALE);
                label.setTranslateY(2 * Main.SCALE);
                break;
            case LEFT:
                label.setTranslateX(4 * Main.SCALE);
                label.setTranslateY(2 * Main.SCALE);
                break;
            case UP:
                label.setTranslateX(0);
                label.setTranslateY(-2 * Main.SCALE);
                break;
            case DOWN:
                label.setTranslateX(0);
                label.setTranslateY(-9 * Main.SCALE);
                break;
        }
    };
    public Lion(Location loc) {

        super(LION, new SpriteView(LION,loc));
        this.number.set(0);
        direction.addListener(directionListener);
        directionListener.changed(direction, direction.getValue(), direction.getValue());
    }
    public void smellShips(ArrayList<SpriteView> lambs){
        //TODO move to the closest sheep
        int x =0;
        int y =0;
        if(lambs.isEmpty()){
            return;
        }
        SpriteView closestLamb = lambs.get(0);

        for(SpriteView lamb:lambs){

            if(calcDistance(closestLamb)>calcDistance(lamb)){
                closestLamb = lamb;
            }
        }

        for(int i=0 ; i<2;++i){
            if(closestLamb.getLocation().getX()>this.getLocation().getX()){
                x++;
            }
            else if(closestLamb.getLocation().getX()<this.getLocation().getX()){
                x--;
            }

            else if(closestLamb.getLocation().getY()>this.getLocation().getY()){
                y++;
            }

            else if(closestLamb.getLocation().getY()<this.getLocation().getY()){
               y--;
            }
            getCloseToShip(x,y);
            removeLamb(closestLamb,y,x);



            }







    }

    private void removeLamb(SpriteView closestLamb,int offsetY,int offsetX){
        if(closestLamb.getLocation().getY()==this.getLocation().getY()+offsetY&&
                closestLamb.getLocation().getX()==this.getLocation().getX()+offsetX) {
            Main.root.getChildren().remove(closestLamb);
            this.number.set(this.number.get() + 1);
            Main.map[closestLamb.getLocation().getY()][closestLamb.getLocation().getX()] = null;
            SpriteView s = closestLamb.getFollowing();
            s.getChildren().remove(closestLamb);
            this.setScaleY(getScaleY()*1.05);
            this.setScaleX(getScaleX()*1.05);
            return;
        }
    }

    /*public void move(Direction direction) {
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
    }*/

    private double calcDistance(SpriteView s){
        double dis = Math.sqrt(Math.pow(s.getLocation().getX() - this.getLocation().getX(),2) +
                Math.pow(s.getLocation().getY() - this.getLocation().getY(), 2));
        return dis;

    }

    private void getCloseToShip(int x, int y){
        this.moveTo(new Location(this.getLocation().getX()+x,this.getLocation().getY()+y));
    }
}
