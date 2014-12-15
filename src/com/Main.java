package com;

import com.models.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

public class Main extends Application {

    public static final int SCALE = 4;
    public static final int SPRITE_SIZE = 32;
    public static final int CELL_SIZE = SPRITE_SIZE * SCALE;
    public static final int HORIZONTAL_CELLS = 10;
    public static final int VERTICAL_CELLS = 6;
    public static final int BOARD_WIDTH = HORIZONTAL_CELLS * CELL_SIZE;
    public static final int BOARD_HEIGHT = VERTICAL_CELLS * CELL_SIZE;
    public static MapObject[][] map = new MapObject[HORIZONTAL_CELLS][VERTICAL_CELLS];
    public static int shep_walk = 0;
    public static boolean step = false;

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
        John john = new John(new Location(9, 0));
        Lion lion = new Lion(new Location(9, 5));

        populateCells(root);
        root.getChildren().add(mary);
        root.getChildren().add(john);
        root.getChildren().add(lion);
        addKeyHandler(scene, mary , john , lion);

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

    private void addKeyHandler(Scene scene, Shepherd mary, Shepherd john, Lion lion) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            KeyCode keyCode = ke.getCode();
            switch (keyCode) {
                case Q:
                    if(john.move(Direction.UP_LEFT)) { step = true ; shep_walk++;}
                    break;
                case W:
                    if(john.move(Direction.UP)) { step = true ; shep_walk++;}
                    break;
                case UP:
                    if(mary.move(Direction.UP)) { step = true ; shep_walk++;}
                    break;
                case E:
                    if(john.move(Direction.UP_RIGHT)) { step = true ; shep_walk++;}
                    break;
                case A:
                    if(john.move(Direction.LEFT)) { step = true ; shep_walk++;}
                    break;
                case LEFT:
                    if(mary.move(Direction.LEFT)) { step = true ; shep_walk++;}
                    break;
                case C:
                    if(john.move(Direction.DOWN_RIGHT)) { step = true ; shep_walk++;}
                    break;
                case S:
                    if(john.move(Direction.DOWN)) { step = true ; shep_walk++;}
                    break;
                case DOWN:
                    if(mary.move(Direction.DOWN)) { step = true ; shep_walk++;}
                    break;
                case Z:
                    if(john.move(Direction.DOWN_LEFT)) { step = true ; shep_walk++;}
                    break;
                case D:
                    if(john.move(Direction.RIGHT)) { step = true ; shep_walk++;}
                    break;
                case RIGHT:
                    if(mary.move(Direction.RIGHT)) { step = true ; shep_walk++;}
                    break;
                case ESCAPE:
                    Platform.exit();
            }
            if (step){
                lion.chase(mary.getAnimals(),john.getAnimals(),shep_walk);
                step = false;
            }

        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}