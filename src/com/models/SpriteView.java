package com.models;

import com.Direction;
import com.Location;
import com.Main;
import com.sun.glass.ui.Application;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SpriteView extends StackPane {
    protected final ImageView imageView;
    protected Color color;
    protected EventHandler<ActionEvent> arrivalHandler;
    protected double colorOffset;
    protected ObjectProperty<Direction> direction = new SimpleObjectProperty<>();
    protected ObjectProperty<Location> location = new SimpleObjectProperty<>();
    protected IntegerProperty frame = new SimpleIntegerProperty(1);
    protected int spriteWidth;
    protected int spriteHeight;
    protected Timeline walking;
    protected SpriteView follower;
    protected SpriteView following;
    protected IntegerProperty number = new SimpleIntegerProperty();

    public SpriteView(Image spriteSheet, SpriteView following) {
        this(spriteSheet, following.getLocation().offset(-following.getDirection().getXOffset(), -following.getDirection().getYOffset()));
        number.set(following.number.get() + 1);
        this.following = following;
        setDirection(following.getDirection());
        following.follower = this;
        setMouseTransparent(true);
    }

    public SpriteView(Image spriteSheet, Location loc) {
        imageView = new ImageView(spriteSheet);
        this.location.set(loc);
        setTranslateX(loc.getX() * Main.CELL_SIZE);
        setTranslateY(loc.getY() * Main.CELL_SIZE);
        ChangeListener<Object> updateImage = (ov, o, o2) -> imageView.setViewport(new Rectangle2D(frame.get() * spriteWidth, direction.get().getOffset() * spriteHeight, spriteWidth, spriteHeight));
        direction.addListener(updateImage);
        frame.addListener(updateImage);
        spriteWidth = (int) (spriteSheet.getWidth() / 3);
        spriteHeight = (int) (spriteSheet.getHeight() / 4);
        direction.set(Direction.RIGHT);
        getChildren().add(imageView);
    }
    public static Image loadImage(String url) {
        return new Image(SpriteView.class.getResource(url).toString(), Main.SPRITE_SIZE * 3 * Main.SCALE, Main.SPRITE_SIZE * 4 * Main.SCALE, true, false);
    }

    public void startAnimation() {
        Timeline timeline = new Timeline(Animation.INDEFINITE,
            new KeyFrame(Duration.seconds(.25), new KeyValue(frame, 0)),
            new KeyFrame(Duration.seconds(.5), new KeyValue(frame, 1)),
            new KeyFrame(Duration.seconds(.75), new KeyValue(frame, 2)),
            new KeyFrame(Duration.seconds(1), new KeyValue(frame, 1))
        );
        timeline.onFinishedProperty().setValue(e -> timeline.play());
        timeline.play();
    }

    public void moveTo(Location loc) {
        walking = new Timeline(Animation.INDEFINITE,
            new KeyFrame(Duration.seconds(.01), new KeyValue(direction, location.getValue().directionTo(loc))),
            new KeyFrame(Duration.seconds(1), new KeyValue(translateXProperty(), loc.getX() * Main.CELL_SIZE)),
            new KeyFrame(Duration.seconds(1), new KeyValue(translateYProperty(), loc.getY() * Main.CELL_SIZE)),
            new KeyFrame(Duration.seconds(.25), new KeyValue(frame, 0)),
            new KeyFrame(Duration.seconds(.5), new KeyValue(frame, 1)),
            new KeyFrame(Duration.seconds(.75), new KeyValue(frame, 2)),
            new KeyFrame(Duration.seconds(1), new KeyValue(frame, 1))
        );
        walking.setOnFinished(e -> {
            location.setValue(loc);
            if (arrivalHandler != null) {
                arrivalHandler.handle(e);
            }
        });
        Application.invokeLater(walking::play);
    }

    public void setDirection(Direction direction) {
        this.direction.setValue(direction);
    }

    public int getNumber() {
        return number.get();
    }

    public SpriteView getFollowing() {
        return following;
    }

    public Location getLocation() {
        return location.get();
    }

    public Direction getDirection() {
        return direction.get();
    }

    public void setColor(Color color) {
        this.color = color;
        if (color == null) {
            imageView.setEffect(null);
        } else {
            imageView.setEffect(new ColorAdjust(color.getHue() / 180 - colorOffset, 0.3, 0, 0));
        }
    }
    public Color getColor() {
        return color;
    }
}
