package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3), LEFTUP(4), RIGHTUP(5),LEFTDOWN(6), RIGHTDOWN(7);
    private final int offset;
    Direction(int offset) {
        this.offset = offset;
    }
    public int getOffset() {
        return offset;
    }
    public int getXOffset() {
        switch (this) {
            case LEFTUP:
            case LEFTDOWN:
            case LEFT:
                return -1;
            case RIGHTUP:
            case RIGHTDOWN:
            case RIGHT:
                return 1;

            default:
                return 0;
        }
    }
    public int getYOffset() {
        switch (this) {
            case LEFTUP:
            case RIGHTUP:
            case UP:
                return -1;
            case LEFTDOWN:
            case RIGHTDOWN:
            case DOWN:
                return 1;
            default:
                return 0;
        }
    }
}
