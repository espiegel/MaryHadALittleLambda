package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3),
    TOP_LEFT(4), TOP_RIGHT(5), BOTTOM_LEFT(6), BOTTOM_RIGHT(7);

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
            case TOP_LEFT:
                return -1;
            case TOP_RIGHT:
                return 1;
            case  BOTTOM_LEFT:
                return -1;
            case BOTTOM_RIGHT:
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
            case TOP_LEFT:
                return -1;
            case TOP_RIGHT:
                return  -1;
            case BOTTOM_RIGHT:
                return 1;
            case BOTTOM_LEFT:
                return 1;
            default:
                return 0;
        }
    }
}
