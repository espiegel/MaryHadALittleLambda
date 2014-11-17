package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3), UPRIGHT(4), UPLEFT(5), DOWNRIGHT(6), DOWNLEFT(7);
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
            case UPLEFT:
            case DOWNLEFT:
                return -1;
            case RIGHT:
            case UPRIGHT:
            case DOWNRIGHT:
                return 1;
            default:
                return 0;
        }
    }
    public int getYOffset() {
        switch (this) {
            case UP:
            case UPLEFT:
            case UPRIGHT:
                return -1;
            case DOWN:
            case DOWNRIGHT:
            case DOWNLEFT:
                return 1;
            default:
                return 0;
        }
    }
}
