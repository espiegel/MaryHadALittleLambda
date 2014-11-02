package com;

/**
 * Created by sleeprate on 02/11/14.
 */
public enum LionCounter {
    STEP_ONE(1), STEP_TWO(2), STEP_ZERO(0);
    private final int counter;

    LionCounter(int cn){ counter = cn; }
    public int getCounter() { return counter;}

}
