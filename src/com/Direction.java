package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3), TOPLEFT(4), TOPRIGHT(5), BOTTOMLEFT(6), BOTTOMRIGHT(7);
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
            case TOPLEFT:
            case BOTTOMLEFT:
                return -1;
            case RIGHT:
            case BOTTOMRIGHT:
            case TOPRIGHT:
                return 1;

            default:
                return 0;
        }
    }
    public int getYOffset() {
        switch (this) {
            case UP:
            case TOPLEFT:
            case TOPRIGHT:
                return -1;
            case DOWN:
            case BOTTOMRIGHT:
            case BOTTOMLEFT:
                return 1;
            default:
                return 0;
        }
    }
}
