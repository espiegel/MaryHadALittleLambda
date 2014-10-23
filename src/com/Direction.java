package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3),UP_LEFT(4),UP_RIGHT(5),DOWN_LEFT(6),DOWN_RIGHT(7);
    private final int offset;
    Direction(int offset) {
        this.offset = offset;
    }
    public int getOffset() {
        return offset;
    }
    public int getXOffset() {
        switch (this) {
            case UP_LEFT:
            case DOWN_LEFT:
            case LEFT:
                return -1;
            case UP_RIGHT:
            case DOWN_RIGHT:
            case RIGHT:
                return 1;
            default:
                return 0;
        }
    }
    public int getYOffset() {
        switch (this) {
            case UP_RIGHT:
            case UP_LEFT:
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
