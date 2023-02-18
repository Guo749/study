package com.priceline.chutes;

import com.priceline.chutes.game.ChutesAndLadderGame;
import com.priceline.chutes.game.Game;
import com.priceline.chutes.game.GameFactory;
import com.priceline.chutes.observer.GameEvent;
import com.priceline.chutes.observer.Move;
import com.priceline.chutes.players.NormalPlayer;
import com.priceline.chutes.players.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * @author lzn
 * @date 2023/02/15 09:32
 * @Description Manage the game
 */
public class GameController {
    static Logger logger = LoggerFactory.getLogger(GameController.class);
    private final Game game;
    private final GameEvent event;

    public GameController(Game game) {
        this.game = game;
        this.event = new GameEvent();
    }

    /**
     * start the game
     */
    public void playGame() {
        game.initialize();
        logger.info("Now let's start the ChuteAndLadder Game!");
        logger.info("-----------------------------");
        while (!game.isOver()) {
            Player player = game.getPlayerWithTurn();
            //When each player starts a round of game, the need to use props to get the number of steps to move, and then move the corresponding distance
            game.makeMove();
            //get the current players position
            Move currentLocations = game.getPlayerLocations(player);
            event.notifySpecifiedObservers(currentLocations, player);
            logger.info("-----------------------------");
        }
        Player player = game.getWinningPlayerStats();
        logger.info(player.getName() + " has won the Game!!");
    }

    /**
     * Register as an observer of the game
     *
     * @param player the play that need to be registered
     */
    public void registerObserver(Player player) {
        event.addObserver(player);
    }

    public static void main(String[] args) {
        List<Player> players = Arrays.asList(new NormalPlayer("John"), new NormalPlayer("David"), new NormalPlayer("Han"));
        ChutesAndLadderGame game = (ChutesAndLadderGame) GameFactory.getInstance().createClassicChuteLadder(players);
        GameController gameController = new GameController(game);
        players.forEach(gameController::registerObserver);
        gameController.playGame();
    }
}
