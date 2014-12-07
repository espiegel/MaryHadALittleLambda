package com;

public enum Direction {
    DOWN(0), LEFT(1), RIGHT(2), UP(3) ,Q(4) ,E(5),Z(6),C(7);
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
            case Q:
                return -1;
            case E:
                return 1;
            case Z:
                return -1;
            case C:
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
            case Q:
                return -1;
            case E:
                return -1;
            case Z:
                return 1;
            case C:
                return 1;
            default:
                return 0;
        }
    }

}
