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
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    public static final int SCALE = 4;
    public static final int SPRITE_SIZE = 32;
    public static final int CELL_SIZE = SPRITE_SIZE * SCALE;
    public static final int HORIZONTAL_CELLS = 10;
    public static final int VERTICAL_CELLS = 6;
    public static final int BOARD_WIDTH = HORIZONTAL_CELLS * CELL_SIZE;
    public static final int BOARD_HEIGHT = VERTICAL_CELLS * CELL_SIZE;
    public static MapObject[][] map = new MapObject[HORIZONTAL_CELLS][VERTICAL_CELLS];
    private int cnt;
    public static boolean[][] map_sheep = new boolean[HORIZONTAL_CELLS*3][VERTICAL_CELLS*3];
    public static SpriteView[][] map_sheep_sprite_view = new SpriteView[HORIZONTAL_CELLS*3][VERTICAL_CELLS*3];
    private Lion lion;


    @Override
    public void start(Stage primaryStage) throws Exception {
        cnt = 0;
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

        John john = new John(new Location(HORIZONTAL_CELLS - 1 , 0));
        lion = new Lion();

        populateCells(root);
        root.getChildren().add(mary);
        addKeyHandler(scene, mary);

        root.getChildren().add(john);
        addKeyHandler(scene, john);

        root.getChildren().add(lion);
        //addKeyHandler(scene, lion);


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

    private void addKeyHandler(Scene scene, Shepherd mary) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            KeyCode keyCode = ke.getCode();
            switch (keyCode) {
                case W:
                    if ( mary.getType() == 1 ) {
                        if ( mary.getLocation().getY() >= 1 ) {
                            initBoard();
                            mary.move(Direction.UP);
                            cnt++;
                        }
                    }
                    break;
                case UP:
                    if ( mary.getType() == 0 ) {
                        if(mary.getLocation().getY() >= 1) {
                            initBoard();
                            mary.move(Direction.UP);
                            cnt++;
                        }
                    }
                    break;
                case A:
                    if ( mary.getType() == 1 ) {
                        if(mary.getLocation().getX() >= 1) {
                            initBoard();
                            mary.move(Direction.LEFT);
                            cnt++;
                        }
                    }
                    break;
                case LEFT:
                    if ( mary.getType() == 0 ) {
                        if(mary.getLocation().getX() >= 1) {
                            initBoard();
                            mary.move(Direction.LEFT);
                            cnt++;
                        }
                    }
                    break;
                case S:
                    if ( mary.getType() == 1 ) {
                        if(mary.getLocation().getY() <= VERTICAL_CELLS - 2) {
                            initBoard();
                            mary.move(Direction.DOWN);
                            cnt++;
                        }
                    }
                    break;
                case DOWN:
                    if ( mary.getType() == 0 ) {
                        if(mary.getLocation().getY() <= VERTICAL_CELLS - 2) {
                            initBoard();
                            mary.move(Direction.DOWN);
                            cnt++;
                        }
                    }
                    break;
                case D:
                    if ( mary.getType() == 1 ) {
                        if(mary.getLocation().getX() <= HORIZONTAL_CELLS - 2) {
                            initBoard();
                            mary.move(Direction.RIGHT);
                            cnt++;
                        }
                    }
                    break;
                case RIGHT:
                    if ( mary.getType() == Common.MARY ) {
                        if ( mary.getLocation().getX() <= HORIZONTAL_CELLS - 2 ) {
                            initBoard();
                            mary.move(Direction.RIGHT);
                            cnt++;
                        }
                    }
                    break;
                // Diagonal movements
                case Q:
                    if ( mary.getType() == 1 ) {
                        if ( mary.getLocation().getY() >= 1 && mary.getLocation().getX() >= 1) {
                            initBoard();
                            mary.move(Direction.DIAG_LEFT_UP);
                            cnt++;
                        }
                    }
                    break;
                case E:
                    if ( mary.getType() == 1 ) {
                        if ( mary.getLocation().getY() >= 1 && mary.getLocation().getX() <= HORIZONTAL_CELLS - 2 ) {
                            initBoard();
                            mary.move(Direction.DIAG_RIGHT_UP);
                            cnt++;
                        }
                    }
                    break;
                case Z:
                    if ( mary.getType() == 1 ) {
                        if ( mary.getLocation().getY() <= VERTICAL_CELLS - 2 && mary.getLocation().getX() >= 1 ) {
                            initBoard();
                            mary.move(Direction.DIAG_LEFT_DOWN);
                            cnt++;
                        }
                    }
                    break;
                case C:
                    if ( mary.getType() == 1 ) {
                        if ( mary.getLocation().getY() <= VERTICAL_CELLS - 2 && mary.getLocation().getX() <= HORIZONTAL_CELLS - 2 ) {
                            initBoard();
                            mary.move(Direction.DIAG_RIGHT_DOWN);
                            cnt++;
                        }
                    }
                    break;
                case ESCAPE:
                    Platform.exit();
                    break;
            }
            if (cnt >= 2) {
                cnt = 0;
                Point pos = find_nearest_sheep();
                if(pos.getX() != -HORIZONTAL_CELLS*5) {
                    int lionX = lion.getLocation().getX();
                    int lionY = lion.getLocation().getY();
                    int dx, dy;
                    dx = dy = 0;
                    if(lionX < pos.x) {
                        dx = 1;
                    } else if(lionX > pos.x) {
                        dx = -1;
                    }
                    if(lionY < pos.y) {
                        dy = 1;
                    } else if(lionY > pos.y) {
                        dy = -1;
                    }
                    if (!((pos.x == lionX + dx) && (pos.y == lionY + dy))) {
                        if(dx == 0) dy = dy * 2;
                        else if(dy == 0) dx = dx * 2;
                    }
                    lion.moveTo(new Location(lionX + dx, lionY + dy));
                    int xres, yres;
                    xres = lionX + dx + HORIZONTAL_CELLS;
                    yres = lionY + dy + VERTICAL_CELLS;
                    if (map_sheep[xres][yres]) {
                        lion.updateNumber();
                        //lion.updateSize();
                        //SpriteView forward = map_sheep_sprite_view[xres][yres].getFollowing();
                        //SpriteView backward = map_sheep_sprite_view[xres][yres].getFollower();
                        //forward.setFollower(backward);
                        //map_sheep_sprite_view[xres][yres].remove();
                    }
                }
            }

        });
    }
    private void initBoard() {
        for (int x = 0; x < Main.HORIZONTAL_CELLS*3; x++) {
            for (int y = 0; y < Main.VERTICAL_CELLS*3; y++) {
                map_sheep[x][y] = false;
                map_sheep_sprite_view[x][y] = null;
            }
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

    private Point find_nearest_sheep() {
        Point res = new Point(-HORIZONTAL_CELLS*5, -HORIZONTAL_CELLS*5);
        double min = HORIZONTAL_CELLS*2;
            for (int y = VERTICAL_CELLS; y < VERTICAL_CELLS*2; y++) {
                for (int x = HORIZONTAL_CELLS; x < HORIZONTAL_CELLS*2; x++) {
                //String st = "";
                //if (map_sheep[x][y]) st = "1"; else st = "0";
                //System.out.print(st);
                if (map_sheep[x][y]) {
                    double dst = Point.distance(x-HORIZONTAL_CELLS, y-VERTICAL_CELLS, lion.getLocation().getX(), lion.getLocation().getY());
                    if (dst < min) {
                        min = dst;
                        res.setLocation(x-HORIZONTAL_CELLS, y-VERTICAL_CELLS);
                    }
                }
            }
            //System.out.println();
        }
        //System.out.println(res.toString());
        return res;
        //return new Point(1, 1);

    }
}