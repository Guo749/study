package com.priceline.chutes.turns;

/**
 * @author lzn
 * @date 2023/02/14 23:17
 * @Description Simple implement of Round-robin(cyclic executive), assigned to each player in circular order
 */
public class RoundRobinTurn implements TurnStrategy {

    private int turn;
    private final int size;

    public RoundRobinTurn(int size) {
        this.turn = -1;
        this.size = size;
    }

    @Override
    public int getNext() {
        turn = (turn + 1) % size;
        return turn;
    }
}
