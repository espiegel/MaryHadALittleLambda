package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3), DOWN_LEFT(4), UP_LEFT(5), UP_RIGHT(6), DOWN_RIGHT(7);
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
            case UP_LEFT:
            case DOWN_LEFT:
                return -1;
            case RIGHT:
            case UP_RIGHT:
            case DOWN_RIGHT:
                return 1;
            default:
                return 0;
        }
    }
    public int getYOffset() {
        switch (this) {
            case UP:
            case UP_LEFT:
            case UP_RIGHT:
                return -1;
            case DOWN:
            case DOWN_LEFT:
            case DOWN_RIGHT:

                return 1;
            default:
                return 0;
        }
    }
}
