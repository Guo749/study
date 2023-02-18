package com.priceline.chutes.observer;

import com.priceline.chutes.players.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lzn
 * @date 2023/02/14 22:16
 * @Description Responsible for posting notifications to all players in the game
 */
public class GameEvent {

    private final List<GameObserver> observers;

    public GameEvent() {
        this.observers = new ArrayList<>();
    }

    /**
     * add a observer to observer list
     *
     * @param obs observer
     */
    public void addObserver(GameObserver obs) {
        observers.add(obs);
    }

    /**
     * remove a observer from observer list
     *
     * @param obs observer
     */
    public void removeObserver(GameObserver obs) {
        observers.remove(obs);
    }

    /**
     * notify all observers
     *
     * @param move number of steps should be moved
     */
    public void notifyObservers(Move move, GameObserver observer) {
        for (GameObserver go : observers) {
            go.updateMove(move, (Player) observer);
        }
    }

    /**
     * notify observers other than the observer passing the reference
     *
     * @param move     which step should be moved
     * @param observer the current observer
     */
    public void notifySpecifiedObservers(Move move, GameObserver observer) {
        observers.forEach(go -> {
            if (!go.equals(observer)) {
                go.updateMove(move, (Player) observer);
            }
        });
    }
}
