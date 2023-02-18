package com.priceline.chutes.observer;

import com.priceline.chutes.players.Player;
import com.priceline.chutes.rollings.RollingStrategy;

/**
 * @author lzn
 * @date 2023/02/14 21:57
 * @Description Game observer
 */
public interface GameObserver {

    /**
     * notify players to get the step message
     *
     * @param move   number of steps
     * @param player current player
     */
    void updateMove(Move move, Player player);

    /**
     * get the step of
     *
     * @param strategy rolling strategy
     * @return moving steps
     */
    Move generateMove(RollingStrategy strategy);
}
