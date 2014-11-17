package com.models;

import com.Location;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Observer {
    private static ObservableList<Shepherd> shepherds = FXCollections.observableArrayList();
    private static ObservableList<SpriteView> lambs = FXCollections.observableArrayList();
    private static Lion lion;
    private static int steps = 0;

    private static int distance(Location l1,Location l2) {
        return (Math.abs(l1.getX() - l2.getX()) + Math.abs(l1.getY() - l2.getY()));
    }

    public static void setLion(Lion l) {lion = l;}
    public static void addShpherd(Shepherd s) {shepherds.add(s);}
    public static void addLambs(ObservableList<SpriteView> list) {
        lambs.addAll(list);
    }

    public static SpriteView getNearestLamb(Location l) {
        SpriteView nearest = null;
        int min_d = Integer.MAX_VALUE;
        for (SpriteView sv : lambs) {
            Location loc = sv.getLocation();
            int d = distance(l, loc);
            if (d < min_d) {
                min_d = d;
                nearest = sv;
            }
        }
        return nearest;
    }

    private static void eatLamb(Lion lion, SpriteView lamb) {
        Shepherd shepherd = null;
        for (Shepherd s : shepherds) {
            if (s.getAnimals().contains(lamb)) {
                shepherd = s;
                break;
            }
        }
        lion.incNumber();
        lambs.remove(lamb);
        shepherd.getAnimals().remove(lamb);
    }

    private static void moveLionToNearestLamb(Lion lion, SpriteView lamb) {
        int lion_x = lion.getLocation().getX();
        int lion_y = lion.getLocation().getY();
        int lamb_x = lamb.getLocation().getX();
        int lamb_y = lamb.getLocation().getY();
        if (lion_x > lamb_x + 1) {
            lion.moveToLocation(lion_x - 2, lion_y);
        } else if (lion_x < lamb_x - 1) {
            lion.moveToLocation(lion_x + 2, lion_y);
        } else if (lion_y > lamb_y + 1) {
            lion.moveToLocation(lion_x, lion_y - 2);
        } else if (lion_y < lamb_y - 1) {
            lion.moveToLocation(lion_x, lion_y + 2);
        } else if (lion_x > lamb_x) {
            if (lion_y > lamb_y) {
                lion.moveToLocation(lion_x - 1, lion_y - 1);
            } else {
                lion.moveToLocation(lion_x - 1, lion_y + 1);
            }
        } else if (lion_y > lamb_y) {
            lion.moveToLocation(lion_x, lion_y - 1);
        }
    }

    private static void moveLion(Lion lion, SpriteView lamb) {
        int dist = distance(lion.getLocation(), lamb.getLocation());
        switch (dist) {
            case 0:
                eatLamb(lion, lamb);
                return;
            case 1:
            case 2:
                lion.moveTo(lamb.getLocation());
                eatLamb(lion, lamb);
                return;
            default:
                moveLionToNearestLamb(lion, lamb);
                return;
        }

    }

    public static void onMove(Shepherd s) {
        if (++steps == 2) {
            steps = 0;
            if (lambs.size() > 0) {
                moveLion(lion, getNearestLamb(lion.getLocation()));
            }
            if (lambs.size() == 0) {
                lion.moveToOrigin();
            }
        }
    }
}
