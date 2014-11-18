package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3),UPLEFT(4),UPRIGHT(5),DOWNLEFT(6),DOWNRIGHT(7);
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
            case UPLEFT:
                return -1;
            case UPRIGHT:
                return 1;
            case DOWNLEFT:
                return -1;
            case DOWNRIGHT:
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
            case UPLEFT:
                return -1;
            case UPRIGHT:
                return -1;
            case DOWNLEFT:
                return 1;
            case DOWNRIGHT:
                return 1;
            default:
                return 0;
        }
    }
}
