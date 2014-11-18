package com;

import com.models.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.util.Iterator;

public class Main extends Application {
    int i=0;
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
        John john = new John(new Location(9, 0));
        Lion lion = new Lion(new Location(6,5));
        populateCells(root);
        root.getChildren().add(mary);
        root.getChildren().add(john);
        root.getChildren().add(lion);

        addKeyHandler(scene, mary, john,lion);
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

    private void addKeyHandler(Scene scene, Shepherd mary,Shepherd john,Lion lion) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, ke -> {
            KeyCode keyCode = ke.getCode();
            switch (keyCode) {
                case W:
                    if(john.getLocation().getY()!=0)
                        john.move(Direction.UP);
                    break;
                case UP:
                    if(mary.getLocation().getY()!=0)
                        mary.move(Direction.UP);
                    break;
                case A:
                    if(john.getLocation().getX()!=0)
                        john.move(Direction.LEFT);
                    break;
                case LEFT:
                    if(mary.getLocation().getX()!=0)
                        mary.move(Direction.LEFT);
                    break;
                case S:
                    if(john.getLocation().getY()!=VERTICAL_CELLS-1)
                        john.move(Direction.DOWN);
                    break;
                case DOWN:
                    if(mary.getLocation().getY()!=VERTICAL_CELLS-1)
                        mary.move(Direction.DOWN);
                    break;
                case D:
                    if(john.getLocation().getX()!=HORIZONTAL_CELLS-1)
                        john.move(Direction.RIGHT);
                    break;
                case RIGHT:
                    if(mary.getLocation().getX()!=HORIZONTAL_CELLS-1)
                        mary.move(Direction.RIGHT);
                    break;
                case Q:
                    if(john.getLocation().getX()!=0 && john.getLocation().getY()!=0 )
                        john.move(Direction.UPLEFT);
                    break;
                case E:
                    if(john.getLocation().getX()!=HORIZONTAL_CELLS-1 && john.getLocation().getY()!=0 )
                        john.move(Direction.UPRIGHT);
                    break;
                case Z:
                    if(john.getLocation().getX()!=0 && john.getLocation().getY()!=VERTICAL_CELLS-1 )
                        john.move(Direction.DOWNLEFT);
                    break;
                case C:
                    if(john.getLocation().getX()!=HORIZONTAL_CELLS-1 && john.getLocation().getY()!=VERTICAL_CELLS-1 )
                        john.move(Direction.DOWNRIGHT);
                    break;
                case ESCAPE:
                    Platform.exit();
            }
            i++;
            if (i%2==0) {

                SpriteView sheep = findClosestLamb(mary, john, lion);
                Location lamloc =null;
                if (sheep!=null) {
                    lamloc = sheep.getLocation();
                        Location lionLoc = lion.getLocation();
                        int x = lamloc.getX() - lion.getLocation().getX();
                        int y = lamloc.getY() - lion.getLocation().getY();

                        if(x < 0 && lionLoc.getX() != 0)
                            lion.moveTo(new Location(lionLoc.getX() - 1, lionLoc.getY()));
                        else if(x > 0 && lionLoc.getX() != HORIZONTAL_CELLS - 1)
                            lion.moveTo(new Location(lionLoc.getX() + 1, lionLoc.getY()));

                        if(y < 0 && lionLoc.getY() != 0)
                            lion.moveTo(new Location(lionLoc.getX(), lionLoc.getY() - 1));
                        else if(y > 0 && lionLoc.getY() != VERTICAL_CELLS - 1)
                            lion.moveTo(new Location(lionLoc.getX() + 1, lionLoc.getY() + 1));
                    sheep = findLamb(mary,john,lion);
                    if (sheep!=null)
                        visit(mary,lion,sheep);

                }

            }


        });
    }
    private SpriteView findLamb(Shepherd marry,Shepherd john, Lion lion) {
        Location lionLoc = lion.getLocation();
        SpriteView curr = null;
        ObservableList<SpriteView> list = marry.getAnimals();
        ObservableList<SpriteView> listjohn = john.getAnimals();
        list.addAll(listjohn);
        for (i=0;i<list.size();i++)
        {
            if (list.get(i).getLocation().getX() == lionLoc.getX() && list.get(i).getLocation().getY()==lionLoc.getY()){
                curr = list.get(i);
                break;
            }

        }

        return curr;

    }
    public void visit(Shepherd shepherd,Lion lion,SpriteView sheep) {

        lion.setScaleX(lion.getScaleX() + lion.getScaleX()*0.05);
        lion.setScaleY(lion.getScaleY() + lion.getScaleY()*0.05);
        shepherd.getAnimals().remove(sheep);
        lion.number.set(lion.number.getValue().intValue()+1);
        lion.label.textProperty().bind(lion.number.asString());


    }
    private SpriteView findClosestLamb(Shepherd marry,Shepherd john, Lion lion){
        Location lionLoc = lion.getLocation();
        SpriteView curr = null;
        double curdist = 1000;
        int i,x,y;
        ObservableList<SpriteView> list = marry.getAnimals();
        ObservableList<SpriteView> listjohn = john.getAnimals();
        list.addAll(listjohn);
        for (i=0;i<list.size();i++)
        {
            x = list.get(i).getLocation().getX(); //
            y = list.get(i).getLocation().getY();
            double dist = Math.sqrt((y-lionLoc.getY())*(y-lionLoc.getY()) + (x - lionLoc.getX())*(y-lionLoc.getY()));
            if (dist<curdist){
                curdist = dist;
                curr = list.get(i);
            }
        }

        return curr;
    }
    public static void main(String[] args) {
        launch(args);
    }
}