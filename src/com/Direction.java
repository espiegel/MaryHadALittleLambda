package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3), DOWN_RIGHT(4), DOWN_LEFT(5), UP_RIGHT(6), UP_LEFT(7);
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
            case DOWN_LEFT:
                return -1;
            case UP_LEFT:
                return -1;
            case RIGHT:
                return 1;
            case UP_RIGHT:
                return 1;
            case DOWN_RIGHT:
                return 1;
            default:
                return 0;
        }
    }
    public int getYOffset() {
        switch (this) {
            case UP:
                return -1;
            case UP_RIGHT:
                return -1;
            case UP_LEFT:
                return -1;
            case DOWN:
                return 1;
            case DOWN_RIGHT:
                return 1;
            case DOWN_LEFT:
                return 1;
            default:
                return 0;
        }
    }
}
