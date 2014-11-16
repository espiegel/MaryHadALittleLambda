package com;

import com.models.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {

    public static final int SCALE = 4;
    public static final int SPRITE_SIZE = 32;
    public static final int CELL_SIZE = SPRITE_SIZE * SCALE;
    public static final int HORIZONTAL_CELLS = 10;
    public static final int VERTICAL_CELLS = 6;
    public static final int BOARD_WIDTH = HORIZONTAL_CELLS * CELL_SIZE;
    public static final int BOARD_HEIGHT = VERTICAL_CELLS * CELL_SIZE;
    public static MapObject[][] map = new MapObject[HORIZONTAL_CELLS][VERTICAL_CELLS];

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Mary Had a Little Lambda");
        Group root = new Group();
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        populateBackground(root);

        // This shows object generation:
        root.getChildren().add(new Barn(new Location(2, 3)));

        /*// This one shows filtering
        root.getChildren().add(new MapObject.Rainbow(new Location(5, 0)));
        // This one demonstrates the additions to the List API:
        root.getChildren().add(new MapObject.Church(new Location(6, 2)));
        // This demonstrates Map:
        root.getChildren().add(new MapObject.ChickenCoop(new Location(5, 4)));
        // And this one FlatMap:
        root.getChildren().add(new MapObject.Nest(new Location(3, 4)));
        // And finally aggregation:



        Fox fox = new Fox(new Location(9, 4));
        fox.setDirection(Direction.LEFT);
        fox.setScaleX(.5);
        fox.setScaleY(.5);
        root.getChildren().add(fox);
         */

        Lion lion = new Lion(new Location(9,5));
        Mary mary = new Mary(new Location(0, 3));
        John john = new John(new Location(9, 0));
        populateCells(root);
        root.getChildren().add(mary);
        root.getChildren().add(john);
        root.getChildren().add(lion);
        HashMap<String,Shepherd> players = new HashMap();

        players.put("mary", mary);
        players.put("john", john);
        players.put("lion", lion);

        addKeyHandler(scene, players);


        primaryStage.show();
    }

    private void populateBackground(Group root) {
        // Image by Victor Szalvay: http://www.flickr.com/photos/55502991@N00/172603855
        ImageView background = new ImageView(getClass().getResource("images/field.jpg").toString());
        background.setFitHeight(BOARD_HEIGHT);
        root.getChildren().add(background);

    }

    private void populateCells(Group root) {
        // Gratuitous use of lambdas to do nested iteration!
        Group cells = new Group();

        for(int i=0;i<HORIZONTAL_CELLS;i++) {
            for(int j=0;j<VERTICAL_CELLS;j++) {
                Rectangle rect = new Rectangle(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                rect.setFill(Color.rgb(0, 0, 0, 0));
                rect.setStrokeType(StrokeType.INSIDE);
                rect.setStroke(Color.BLACK);
                cells.getChildren().add(rect);
            }
        }

        root.getChildren().add(cells);
    }

    private void addKeyHandler(Scene scene, HashMap<String,Shepherd> players) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            KeyCode keyCode = ke.getCode();

            switch (keyCode) {
                case W:
                    players.get("john").move(Direction.UP);
                    break;
                case UP:
                    players.get("mary").move(Direction.UP);
                    break;
                case A:
                    players.get("john").move(Direction.LEFT);
                    break;
                case LEFT:
                    players.get("mary").move(Direction.LEFT);
                    break;
                case S:
                    players.get("john").move(Direction.DOWN);
                    break;
                case DOWN:
                    players.get("mary").move(Direction.DOWN);
                    break;
                case D:
                    players.get("john").move(Direction.RIGHT);
                    break;
                case RIGHT:
                    players.get("mary").move(Direction.RIGHT);
                    break;
                case Q:
                    players.get("john").move(Direction.LEFTUP);
                    break;
                case E:
                    players.get("john").move(Direction.RIGHTUP);
                    break;
                case C:
                    players.get("john").move(Direction.RIGHTDOWN);
                    break;
                case Z:
                    players.get("john").move(Direction.LEFTDOWN);
                    break;
                case ESCAPE:
                    Platform.exit();
            }


               Direction d = getClosestLamb(players.get("lion").getLocation(),players.get("john").getAnimals(),players.get("mary").getAnimals());
                SpriteView sv = isLionEatLamb(players.get("lion").getLocation(), players.get("john").getAnimals(), players.get("mary").getAnimals());
                if (Shepherd.stepCounts==0&& null!=d){
                   players.get("lion").move(d);
                    if (null!=sv){
                        //TODO
                        players.get("john").getAnimals().remove(sv);
                        players.get("mary").getAnimals().remove(sv);
                    }
               }




        });
    }

    private SpriteView isLionEatLamb(Location lionLoc, ObservableList<SpriteView> johnLamb,ObservableList<SpriteView> maryLamb){
        SpriteView needRemove=null;

        for(SpriteView sv:johnLamb){
            if(lionLoc.sameLoc(sv.getLocation())){
                needRemove = sv;
            }
        }

        for(SpriteView sv:maryLamb){
            if(lionLoc.sameLoc(sv.getLocation())){
                needRemove = sv;
            }
        }
        return needRemove;
    }
    private Direction getClosestLamb(Location lionLoc, ObservableList<SpriteView> johnLamb,ObservableList<SpriteView> maryLamb){
        double minLoc = 100;
        Direction d = null;

        for(SpriteView sv:johnLamb){
            if(minLoc > lionLoc.distance(sv.getLocation())){
                minLoc = lionLoc.distance(sv.getLocation());
                d = lionLoc.directionTo(sv.getLocation());

            }
        }

        for(SpriteView sv:maryLamb){
            if(minLoc > lionLoc.distance(sv.getLocation())){
                minLoc = lionLoc.distance(sv.getLocation());
                d = lionLoc.directionTo(sv.getLocation());

            }
        }

        System.out.println(d);
        return d;
    }



    public static void main(String[] args) {
        launch(args);
    }
}