package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3), DIAG_LEFT_UP(4), DIAG_LEFT_DOWN(5), DIAG_RIGHT_UP(6), DIAG_RIGHT_DOWN(7);
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
            case DIAG_LEFT_UP:
                return -1;
            case DIAG_LEFT_DOWN:
                return -1;
            case DIAG_RIGHT_DOWN:
                return 1;
            case DIAG_RIGHT_UP:
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
            case DIAG_LEFT_UP:
                return -1;
            case DIAG_LEFT_DOWN:
                return 1;
            case DIAG_RIGHT_UP:
                return -1;
            case DIAG_RIGHT_DOWN:
                return 1;
            default:
                return 0;
        }
    }
}
