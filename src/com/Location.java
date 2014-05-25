package com;

public class Location {
    int cell_x;
    int cell_y;

    public Location(int cell_x, int cell_y) {
        this.cell_x = cell_x;
        this.cell_y = cell_y;
    }
    public int getX() {
        return cell_x;
    }
    public int getY() {
        return cell_y;
    }
    public Location offset(int x, int y) {
        return new Location(cell_x + x, cell_y + y);
    }
    public Direction directionTo(Location loc) {
        if (Math.abs(loc.cell_x - cell_x) > Math.abs(loc.cell_y - cell_y)) {
            return (loc.cell_x > cell_x) ? Direction.RIGHT : Direction.LEFT;
        } else {
            return (loc.cell_y > cell_y) ? Direction.DOWN : Direction.UP;
        }
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if(cell_x != location.cell_x) return false;
        if(cell_y != location.cell_y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cell_x;
        result = 31 * result + cell_y;
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "cell_x=" + cell_x +
                ", cell_y=" + cell_y +
                '}';
    }
}