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
        Guy guy = new Guy(new Location(9, 0));
        Lion lion = new Lion(new Location(9, 5));

        populateCells(root);

        root.getChildren().add(mary);
        root.getChildren().add(guy);
        root.getChildren().add(lion);
        addKeyHandler(scene, mary, guy, lion);

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

        for (int i = 0; i < HORIZONTAL_CELLS; i++) {
            for (int j = 0; j < VERTICAL_CELLS; j++) {
                Rectangle rect = new Rectangle(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                rect.setFill(Color.rgb(0, 0, 0, 0));
                rect.setStrokeType(StrokeType.INSIDE);
                rect.setStroke(Color.BLACK);
                cells.getChildren().add(rect);
            }
        }

        root.getChildren().add(cells);
    }

    private void addKeyHandler(Scene scene, Shepherd mary, Shepherd guy, Lion lion) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            KeyCode keyCode = ke.getCode();
            switch (keyCode) {
                case W:
                    if (guy.getLocation().cell_y > 0) guy.move(Direction.UP);
                    break;
                case UP:
                    if (mary.getLocation().cell_y > 0) mary.move(Direction.UP);
                    break;
                case A:
                    if (guy.getLocation().cell_x > 0) guy.move(Direction.LEFT);
                    break;
                case LEFT:
                    if (mary.getLocation().cell_x > 0) mary.move(Direction.LEFT);
                    break;
                case S:
                    if (guy.getLocation().cell_y < 5) guy.move(Direction.DOWN);
                    break;
                case DOWN:
                    if (mary.getLocation().cell_y < 5) mary.move(Direction.DOWN);
                    break;
                case D:
                    if (guy.getLocation().cell_x < 9) guy.move(Direction.RIGHT);
                    break;
                case RIGHT:
                    if (mary.getLocation().cell_x < 9) mary.move(Direction.RIGHT);
                    break;
                case ESCAPE:
                    Platform.exit();
            }
            if (!mary.getAnimals().isEmpty()) {
                switch (mary.getDirection()) {
                    case UP:
                    case DOWN:
                        lion.move(mary.getLocation().getY() <= lion.getLocation().getY() ? Direction.UP : Direction.DOWN);
                        break;

                    case RIGHT:
                    case LEFT:
                        lion.move(mary.getLocation().getX() <= lion.getLocation().getX() ? Direction.LEFT : Direction.RIGHT);
                        break;
                }
            }

            System.out.println(mary.getLocation());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}