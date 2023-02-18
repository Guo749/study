package com.priceline.chutes.players;

import com.priceline.chutes.observer.GameObserver;
import com.priceline.chutes.observer.Move;
import com.priceline.chutes.rollings.RollingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * @author lzn
 * @date 2023/02/14 22:25
 * @Description Parent class for all players
 */
public class Player implements GameObserver {
    static Logger logger = LoggerFactory.getLogger(Player.class);
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void updateMove(Move move, Player player) {
        logger.info(this.toString() + ", I saw " + player.getName() + " playing games, now he/she move to " + move.getStep());
    }

    @Override
    public Move generateMove(RollingStrategy strategy) {
        int roll = strategy.roll();
        logger.info(this.toString() + " and I rolled " + roll);
        return new Move(roll);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
