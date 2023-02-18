package com.priceline.chutes.observer;

/**
 * @author lzn
 * @date 2023/02/14 22:02
 * @Description Movements that players use to perform
 */
public class Move {

    private final int step;

    public Move(int step) {
        this.step = step;
    }

    public int getStep() {
        return step;
    }

    @Override
    public String toString() {
        return "Move{" +
                "step=" + step +
                '}';
    }
}
