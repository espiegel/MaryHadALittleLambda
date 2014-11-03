package com.models;

import com.Location;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Mary extends Shepherd {
    // Image by Terra-chan: http://www.rpgmakervx.net/index.php?showtopic=29404
    static final Image MARY = loadImage("../images/mary.png");
    public Mary(Location loc) {
        super(MARY, loc);
    }

  /*  public static void moove(Mary mary, KeyCode keyCode){

             switch (keyCode) {
                case W:
                case UP:
                    mary.move(Direction.UP);
                    break;
                case A:
                case LEFT:
                    mary.move(Direction.LEFT);
                    break;
                case S:
                case DOWN:
                    mary.move(Direction.DOWN);
                    break;
                case D:
                case RIGHT:
                    mary.move(Direction.RIGHT);
                    break;
                case ESCAPE:
                    Platform.exit();
            }

    }*/
}
