package com.priceline.chutes.game;

import com.priceline.chutes.enums.GameState;
import com.priceline.chutes.enums.RuleType;
import com.priceline.chutes.exceptions.InvalidBoardSizeException;
import com.priceline.chutes.exceptions.InvalidGamePlayerException;
import com.priceline.chutes.exceptions.InvalidGameStateForActionException;
import com.priceline.chutes.observer.Move;
import com.priceline.chutes.players.Player;
import com.priceline.chutes.rollings.RollingStrategy;
import com.priceline.chutes.rules.RuleStrategy;
import com.priceline.chutes.rules.RuleStrategyFactory;
import com.priceline.chutes.turns.RoundRobinTurn;
import com.priceline.chutes.turns.TurnStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lzn
 * @date 2023/02/14 17:16
 * @Description Chute and Ladder game
 */
public class ChutesAndLadderGame implements Game {
    static Logger logger = LoggerFactory.getLogger(ChutesAndLadderGame.class);
    private GameState gameState;
    private TurnStrategy turnStrategy;
    private final RollingStrategy rollingStrategy;
    private final int chuteCount, laddersCount, boardWidth, boardHeight;
    private final List<Player> players;
    private int playerWithTurn;
    /**
     * store each player current position
     */
    private Map<Player, Integer> playerLocations;
    /**
     * chutes position <start, end>
     */
    private Map<Integer, Integer> chuteLocations;
    /**
     * ladders position <start, end>
     */
    private Map<Integer, Integer> ladderLocations;

    private ChutesAndLadderGame(Builder builder) {
        this.gameState = GameState.WAITING;
        this.chuteCount = builder.chute;
        this.laddersCount = builder.ladder;
        this.boardWidth = builder.boardWidth;
        this.boardHeight = builder.boardHeight;
        this.players = builder.players;
        this.rollingStrategy = builder.rollingStrategy;
    }

    @Override
    public void initialize() {
        int playerSize = players.size();
        playerLocations = new HashMap<>(playerSize);
        players.forEach(player -> playerLocations.put(player, 0));
        initializeGameRules();
        turnStrategy = new RoundRobinTurn(playerSize);
        playerWithTurn = turnStrategy.getNext();
        gameState = GameState.START;
        logger.info("Game is initialized!");
    }

    @Override
    public boolean containsPlayer(Player player) {
        return players.contains(player);
    }

    @Override
    public void forceToStop() {
        gameState = GameState.END;
    }

    /**
     * initialize game rules
     */
    public void initializeGameRules() {
        RuleStrategy chuteRules = RuleStrategyFactory.getGameRuleStrategy(RuleType.CHUTE);
        RuleStrategy ladderRules = RuleStrategyFactory.getGameRuleStrategy(RuleType.LADDER);
        chuteLocations = chuteRules.generateGameRule(getBoardSize(), chuteCount);
        ladderLocations = ladderRules.generateGameRule(getBoardSize(), laddersCount);
        chuteLocations.forEach((key, value) -> {
            if (ladderLocations.containsKey(key) && !ladderLocations.containsKey(value) && value != 0) {
                ladderLocations.put(value, ladderLocations.get(key));
                ladderLocations.remove(key);
            }
        });
    }

    @Override
    public Player getPlayerWithTurn() {
        return players.get(playerWithTurn);
    }

    /**
     * get total board size
     *
     * @return total board size
     */
    private int getBoardSize() {
        return boardWidth * boardHeight;
    }

    @Override
    public void makeMove() {
        Player player = getPlayerWithTurn();
        Move move = player.generateMove(rollingStrategy);
        int prevStep = playerLocations.getOrDefault(player, 0);
        int currentStep = prevStep + move.getStep();
        //if players move to the start of chute or start of ladder, they will slide to the corresponding position
        if (chuteLocations.containsKey(currentStep)) {
            playerLocations.put(player, chuteLocations.get(currentStep));
            logger.info("I'm a unlucky one names " + player.getName() + " and I'll took a chute from " + currentStep + " to " + chuteLocations.get(currentStep));
        } else if (ladderLocations.containsKey(currentStep)) {
            playerLocations.put(player, ladderLocations.get(currentStep));
            logger.info("I'm a luck person names" + player.getName() + " and I'll took a ladder from " + currentStep + " to " + ladderLocations.get(currentStep));
        } else {
            //if not, move to the current position
            playerLocations.put(player, currentStep);
            logger.info("I'm " + player.getName() + " and I moved from " + prevStep + " to " + currentStep);
        }
        //check if the current player's position has reached the end
        if (playerLocations.get(player) == getBoardSize()) {
            // the current player has won the game!
            gameState = GameState.END;
        } else {
            if (playerLocations.get(player) > getBoardSize()) {
                logger.info("Oh! the number is " + playerLocations.get(player) + " bigger than " + getBoardSize() + ", I can't move and I need to stay in place ");
                playerLocations.put(player, prevStep);
            }
            // make sure that the turn is advanced
            playerWithTurn = turnStrategy.getNext();
        }
    }

    @Override
    public void addPlayer(Player player) {
        if (containsPlayer(player)) {
            throw new InvalidGamePlayerException("this player is already in the game");
        }
        if (gameState.equals(GameState.START)) {
            throw new InvalidGameStateForActionException("can't add player mid game");
        }
        if (isOver()) {
            throw new InvalidGameStateForActionException("game has already ended");
        }
        logger.info("Adding new player, " + player.toString());
        players.add(player);
        playerLocations.put(player, 0);
    }

    @Override
    public void removePlayer(Player player) {
        if (!containsPlayer(player)) {
            throw new InvalidGamePlayerException("this player was not registered for this game");
        }
        if (gameState.equals(GameState.START)) {
            throw new InvalidGameStateForActionException("can't remove player mid game");
        }
        if (isOver()) {
            throw new InvalidGameStateForActionException("game has already ended");
        }
        players.remove(player);
        playerLocations.remove(player);
        logger.info("Player " + player.getName() + " has been removed");
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public Player getWinningPlayerStats() {
        if (isOver()) {
            return getPlayerWithTurn();
        } else {
            throw new InvalidGameStateForActionException("Game in progress: winner hasn't been decided yet");
        }
    }

    @Override
    public Move getPlayerLocations(Player player) {
        if (!containsPlayer(player)) {
            throw new InvalidGamePlayerException("this player was not registered for this game");
        }
        if (isOver()) {
            throw new InvalidGameStateForActionException("can't get locations from the ended game");
        }
        return new Move(playerLocations.get(player));
    }


    public static class Builder {
        private int chute, ladder, boardWidth, boardHeight;
        private RollingStrategy rollingStrategy;
        private List<Player> players;

        public Builder() {
        }

        public Builder chutes(int chute) {
            this.chute = chute;
            return this;
        }

        public Builder ladders(int ladder) {
            this.ladder = ladder;
            return this;
        }

        public Builder boardWidth(int boardWidth) {
            this.boardWidth = boardWidth;
            return this;
        }

        public Builder boardHeight(int boardHeight) {
            this.boardHeight = boardHeight;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder rollingStrategy(RollingStrategy rollingStrategy) {
            this.rollingStrategy = rollingStrategy;
            return this;
        }

        public ChutesAndLadderGame build() {
            int minWidth = 3;
            int minHeight = 3;
            if (boardWidth < minWidth || boardHeight < minHeight) {
                throw new InvalidBoardSizeException("Board size should be at least 3x3");
            }
            return new ChutesAndLadderGame(this);
        }
    }
}
