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

    public boolean sameLoc(Location loc){
        if((cell_x==loc.getX())&&(cell_y==loc.getY())){
           return true;
        }
        return false;
    }
    public double distance(Location loc){
        int locCell_x = loc.getX();
        int locCell_y = loc.getY();


        double dis = Math.sqrt(Math.pow((this.cell_x - locCell_x), 2) + Math.pow((this.cell_y - locCell_y), 2));
        return dis;
    }

    @Override
    public String toString() {
        return "Location{" +
                "cell_x=" + cell_x +
                ", cell_y=" + cell_y +
                '}';
    }
}