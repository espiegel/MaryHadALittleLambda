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

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static final int SCALE = 4;
    public static final int SPRITE_SIZE = 32;
    public static final int CELL_SIZE = SPRITE_SIZE * SCALE;
    public static final int HORIZONTAL_CELLS = 10;
    public static final int VERTICAL_CELLS = 6;
    public static final int BOARD_WIDTH = HORIZONTAL_CELLS * CELL_SIZE;
    public static final int BOARD_HEIGHT = VERTICAL_CELLS * CELL_SIZE;
    public static MapObject[][] map = new MapObject[HORIZONTAL_CELLS][VERTICAL_CELLS];

    private static int mMoves = 0;
    private static List<Shepherd> mShepherdList = new ArrayList<>();
    private static Lion mLion;

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

        mLion = new Lion(new Location(7, 5));
        root.getChildren().add(mLion);

        Mary mary = new Mary(new Location(0, 3));
        root.getChildren().add(mary);

        John john = new John(new Location(9, 0));
        root.getChildren().add(john);

        mShepherdList.add(mary);
        mShepherdList.add(john);

        addKeyHandler(scene, mary, john);
        populateCells(root);
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

    private void addKeyHandler(Scene scene, Shepherd mary, Shepherd john) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            final int xMary = mary.getLocation().getX();
            final int yMary = mary.getLocation().getY();

            final int xJohn = john.getLocation().getX();
            final int yJohn = john.getLocation().getY();

            KeyCode keyCode = ke.getCode();
            switch (keyCode) {
                case W:
                    if(isLegalMove(xJohn, yJohn-1)) {
                        move(john, Direction.UP);
                    }
                    break;
                case UP:
                    if(isLegalMove(xMary, yMary-1)) {
                        move(mary, Direction.UP);
                    }
                    break;
                case A:
                    if(isLegalMove(xJohn-1, yJohn)) {
                        move(john, Direction.LEFT);
                    }
                    break;
                case LEFT:
                    if(isLegalMove(xMary-1, yMary)) {
                        move(mary, Direction.LEFT);
                    }
                    break;
                case S:
                    if(isLegalMove(xJohn, yJohn+1)) {
                        move(john, Direction.DOWN);
                    }
                    break;
                case DOWN:
                    if(isLegalMove(xMary, yMary+1)) {
                        move(mary, Direction.DOWN);
                    }
                    break;
                case D:
                    if(isLegalMove(xJohn+1, yJohn)) {
                        move(john, Direction.RIGHT);
                    }
                    break;
                case RIGHT:
                    if(isLegalMove(xMary+1, yMary)) {
                        move(mary, Direction.RIGHT);
                    }
                    break;
                case ESCAPE:
                    Platform.exit();
            }
        });
    }

    private void move(Shepherd shepherd, Direction direction) {
        shepherd.move(direction);
        mMoves++;

        if(mMoves % 2 == 0) {
            mLion.moveLion(2);
        }
    }

    private boolean isLegalMove(int x, int y) {
        return x >= 0 && y >= 0 && x < HORIZONTAL_CELLS && y < VERTICAL_CELLS;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static List<Shepherd> getmShepherdList() {
        return mShepherdList;
    }
}