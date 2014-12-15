package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3), UP_RIGHT(4), UP_LEFT(5), DOWN_RIGHT(6), DOWN_LEFT(7);

    private final int offset;
    Direction(int offset) {
        this.offset = offset;
    }
    public int getOffset() {
        return offset;
    }
    public int getXOffset() {
        switch (this) {
            case DOWN_LEFT:
            case UP_LEFT:
            case LEFT:
                return -1;
            case DOWN_RIGHT:
            case UP_RIGHT:
            case RIGHT:
                return 1;
            default:
                return 0;
        }
    }
    public int getYOffset() {
        switch (this) {
            case UP_LEFT:
            case UP_RIGHT:
            case UP:
                return -1;
            case DOWN_LEFT:
            case DOWN_RIGHT:
            case DOWN:
                return 1;
            default:
                return 0;
        }
    }
}
