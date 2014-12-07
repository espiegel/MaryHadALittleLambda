package com;

import com.models.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Main extends Application {

    public static final int SCALE = 4;
    public static final int SPRITE_SIZE = 32;
    public static final int CELL_SIZE = SPRITE_SIZE * SCALE;
    public static final int HORIZONTAL_CELLS = 10;
    public static final int VERTICAL_CELLS = 6;
    public static final int BOARD_WIDTH = HORIZONTAL_CELLS * CELL_SIZE;
    public static final int BOARD_HEIGHT = VERTICAL_CELLS * CELL_SIZE;
    public static MapObject[][] map = new MapObject[HORIZONTAL_CELLS][VERTICAL_CELLS];
    public static int counter =0;


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
        root.getChildren().add(fox);*/

        Mary mary = new Mary(new Location(0, 3));
        populateCells(root);
        root.getChildren().add(mary);
        addKeyHandler(scene, mary);

        John john = new John(new Location(HORIZONTAL_CELLS-1, 0));
        root.getChildren().add(john);
        addKeyHandler2(scene, john);

        Lion lion = new Lion(new Location(HORIZONTAL_CELLS-1, VERTICAL_CELLS-1));
        root.getChildren().add(lion);
      //  addKeyHandler3(scene,lion,john,mary);



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

    private void addKeyHandler2(Scene scene, Shepherd john) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            KeyCode keyCode = ke.getCode();
            switch (keyCode) {
                case W:
                    if (john.getLocation().getY()!=0) {
                        john.move(Direction.UP);
                        counter++;
                    }
                    break;
                case A:

                    if (john.getLocation().getX()!=0) {
                        john.move(Direction.LEFT);
                        counter++;
                    }
                    break;
                case S:

                    if (john.getLocation().getY()!=VERTICAL_CELLS-1){
                        john.move(Direction.DOWN);
                        counter++;
                    }

                    break;
                case D:

                    if (john.getLocation().getX()!=HORIZONTAL_CELLS-1){
                        john.move(Direction.RIGHT);
                        counter++;
                    }
                    break;
                case Q:

                    if (john.getLocation().getX()!=0 &&
                            john.getLocation().getY()!=0){
                        john.move(Direction.Q);
                        counter++;
                    }

                    break;
                case E:

                    if (john.getLocation().getX()!=HORIZONTAL_CELLS-1 &&
                            john.getLocation().getY()!=0){
                        john.move(Direction.E);
                        counter++;

                    }
                    break;

                case Z:
                    if (john.getLocation().getX()!=0 &&
                            john.getLocation().getY()!=VERTICAL_CELLS-1 ){
                        john.move(Direction.Z);
                        counter++;
                    }
                    break;
                case C:

                    if (john.getLocation().getX()!=HORIZONTAL_CELLS-1 &&
                            john.getLocation().getY()!=VERTICAL_CELLS-1){
                        john.move(Direction.C);
                        counter++;
                    }
                    break;

                case ESCAPE:
                    Platform.exit();
            }
        });
    }

    private void addKeyHandler3(Scene scene, Shepherd lion,Shepherd john,Shepherd mary) {

        //int final direct = directionVertical;

                scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
                    if(john.getFollower() == null && mary.getFollower() == null) {
                        return;
                    }

                    Location location = lion.getLocation();
                    int LionX = location.getX();
                    int LionY = location.getY();
                    int FoodDistance=0;
                    //jest john for now
                    SpriteView sp = john.getFollower();
                    int directionHorizon=0;
                    int directionVertical = 0;

                    while(sp != null){
                        if ((Math.abs(sp.getLocation().getY()-LionY)+
                                Math.abs(sp.getLocation().getX() -LionX))<FoodDistance){
                            directionHorizon =sp.getLocation().getX() -LionX;
                            directionVertical = sp.getLocation().getY() - LionY;
                        }
                        FoodDistance = Math.min(Math.abs(sp.getLocation().getY()-LionY)+
                                Math.abs(sp.getLocation().getX() -LionX),FoodDistance);
                        sp =sp.getFollower();
                    }
                    Direction horiz;
                    Direction vertic;
                    if (directionHorizon>0) {
                        horiz = Direction.LEFT;
                    }
                        else {
                        horiz =Direction.RIGHT;
                    }
                    if (directionVertical<0) {
                        vertic = Direction.UP;
                    }
                        else {

                        vertic = Direction.DOWN;

                    }


                    if(counter >= 2) {

                        KeyCode keyCode = ke.getCode();

                        switch(keyCode) {
                            case UP:
                                lion.move(horiz);
                                lion.move(vertic);
                                break;
                            case DOWN:
                                lion.move(horiz);
                                lion.move(vertic);
                                break;
                            case LEFT:
                                lion.move(horiz);
                                lion.move(vertic);
                                break;
                            case RIGHT:
                                lion.move(horiz);
                                lion.move(vertic);
                                break;

                            case ESCAPE:
                                Platform.exit();
                        }
                    }

                });
    }

    private void addKeyHandler(Scene scene, Shepherd mary) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            KeyCode keyCode = ke.getCode();
            switch (keyCode) {

                case UP:
                    if (mary.getLocation().getY()!=0) {
                        mary.move(Direction.UP);
                        counter++;
                    }
                    break;

                case LEFT:
                    if (mary.getLocation().getX()!=0) {
                        mary.move(Direction.LEFT);
                        counter++;
                    }
                    break;

                case DOWN:
                    if (mary.getLocation().getY()!=VERTICAL_CELLS-1){
                        mary.move(Direction.DOWN);
                        counter++;
                    }

                    break;

                case RIGHT:
                    if (mary.getLocation().getX()!=HORIZONTAL_CELLS-1){
                        mary.move(Direction.RIGHT);
                        counter++;
                    }

                    break;
                case ESCAPE:
                    Platform.exit();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}