package com.priceline.chutes.game;

import com.priceline.chutes.enums.GameState;
import com.priceline.chutes.observer.Move;
import com.priceline.chutes.players.Player;

/**
 * @author lzn
 * @date 2023/02/14 20:55
 * @Description
 */
public interface Game {

    /**
     * initialize the game
     */
    void initialize();

    /**
     * Determine if the game is over
     * @return true if game is end, otherwise return false
     */
    default boolean isOver() {
        return this.getGameState().equals(GameState.END);
    }

    /**
     * Determine if the player is in the game
     * @param player player
     * @return true if player is in the game, otherwise return false
     */
    boolean containsPlayer(Player player);

    /**
     * Forced stop of the game in progress
     */
    void forceToStop();

    /**
     * get the current state of the game
     * @return GameState
     */
    GameState getGameState();

    /**
     * get the current player
     * @return current player
     */
    Player getPlayerWithTurn();

    /**
     * check if the game is ended and return the winner
     * @return the winner
     */
    Player getWinningPlayerStats();

    /**
     * get locations of current player
     * @param player the player to be added
     * @return location of current player
     */
    Move getPlayerLocations(Player player);

    /**
     * Move the corresponding distance according to the number
     */
    void makeMove();

    /**
     * add a player to the current game
     * @param player the player to be added
     */
    void addPlayer(Player player);

    /**
     * remove a player to the current game
     * @param player the player to be removed
     */
    void removePlayer(Player player);
}
