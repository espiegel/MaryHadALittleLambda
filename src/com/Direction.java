package com;

import com.models.SpriteView;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3), NULL(4);
    private final int offset;

    Direction(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }
    public int getXOffset() {
        switch (this) {
            case LEFT:
                return -1;
            case RIGHT:
                return 1;
            default:
                return 0;
        }
    }
    public int getYOffset() {
        switch (this) {
            case UP:
                return -1;
            case DOWN:
                return 1;
            default:
                return 0;
        }
    }

    public Direction Towards(SpriteView a, SpriteView b) {
        Direction out = NULL;
        switch (b.getDirection()) {
            case UP : case DOWN:
                out =  b.getLocation().getY() <= a.getLocation().getY() ? Direction.UP : Direction.DOWN;
            break;

            case RIGHT: case LEFT:
                out =  b.getLocation().getX() <= a.getLocation().getX() ? Direction.LEFT : Direction.RIGHT;
                break;
        }

        return out;
    }
}
