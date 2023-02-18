package com.priceline.chutes;

import com.priceline.chutes.enums.GameState;
import com.priceline.chutes.enums.RuleType;
import com.priceline.chutes.exceptions.InvalidGamePlayerException;
import com.priceline.chutes.exceptions.InvalidGameStateForActionException;
import com.priceline.chutes.game.ChutesAndLadderGame;
import com.priceline.chutes.game.GameFactory;
import com.priceline.chutes.observer.Move;
import com.priceline.chutes.players.NormalPlayer;
import com.priceline.chutes.players.Player;
import com.priceline.chutes.rules.RuleStrategy;
import com.priceline.chutes.rules.RuleStrategyFactory;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lzn
 * @date 2023/02/15 09:32
 * @Description Test Chutes and Ladder game
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ChutesAndLadderGameTest {
    List<Player> players = Arrays.asList(new NormalPlayer("John"), new NormalPlayer("David"), new NormalPlayer("Han"));
    ChutesAndLadderGame game = (ChutesAndLadderGame) GameFactory.getInstance().createClassicChuteLadder(players);

    @BeforeEach
    @Order(1)
    void initialize() {
        assertEquals(game.getGameState(), GameState.WAITING);
        game.initialize();
        assertEquals(game.getGameState(), GameState.START);
    }

    @Test
    @Order(2)
    void testGameRules() {
        RuleStrategy chuteRules = RuleStrategyFactory.getGameRuleStrategy(RuleType.CHUTE);
        RuleStrategy ladderRules = RuleStrategyFactory.getGameRuleStrategy(RuleType.LADDER);
        Map<Integer, Integer> chuteLocations = chuteRules.generateGameRule(100, 5);
        Map<Integer, Integer> ladderLocations = ladderRules.generateGameRule(100, 7);
        chuteLocations.forEach((key, value) -> {
            if (ladderLocations.containsKey(key) && !ladderLocations.containsKey(value) && value != 0) {
                ladderLocations.put(value, ladderLocations.get(key));
                ladderLocations.remove(key);
            }
        });
        System.out.println("chuteLocations: " + chuteLocations);
        System.out.println("ladderLocations: " + ladderLocations);
        assertEquals(chuteLocations.size(), 5);
        assertFalse(chuteLocations.containsKey(100));
        assertFalse(chuteLocations.containsKey(0));

        assertEquals(ladderLocations.size(), 7);
        assertFalse(ladderLocations.containsKey(0));
        chuteLocations.forEach((key, value) -> assertTrue(key > value));
        ladderLocations.forEach((key, value) -> assertTrue(key < value));
    }

    @Test
    void testForceToStop() {
        assertSame(game.getGameState(), GameState.START);
        game.forceToStop();
        assertSame(game.getGameState(), GameState.END);
    }

    @Test
    void testContainsPlayer() {
        assertFalse(game.containsPlayer(new NormalPlayer("Lucy")));
        assertTrue(game.containsPlayer(players.get(0)));
    }

    @Test
    void testAddPlayer() {
        assertThrows(InvalidGamePlayerException.class, () -> game.addPlayer(players.get(0)));
        Player player = new NormalPlayer("Lucy");
        assertThrows(InvalidGameStateForActionException.class, () -> game.addPlayer(player));
        game.forceToStop();
        assertThrows(InvalidGameStateForActionException.class, () -> game.addPlayer(player));
        game = (ChutesAndLadderGame) GameFactory.getInstance().createClassicChuteLadder(players);
        game.addPlayer(player);
        assertTrue(game.containsPlayer(player));
    }

    @Test
    void testRemovePlayer() {
        assertThrows(InvalidGamePlayerException.class, () -> game.removePlayer(new NormalPlayer("Lucy")));
        assertThrows(InvalidGameStateForActionException.class, () -> game.removePlayer(players.get(0)));
        game.forceToStop();
        assertThrows(InvalidGameStateForActionException.class, () -> game.removePlayer(players.get(0)));
//        game = (ChutesAndLadderGame) GameFactory.getInstance().createClassicChuteLadder(players);
//        Player player = players.get(0);
//        game.removePlayer(player);
//        assertFalse(game.containsPlayer(player));
    }

    @Test
    void testMakeMove() {
        Player player = game.getPlayerWithTurn();
        Move prevLocation = game.getPlayerLocations(player);
        game.makeMove();
        Move currLocation = game.getPlayerLocations(player);
        assertNotSame(game.getPlayerWithTurn(), player);
        assertNotSame(prevLocation, currLocation);
    }

    @Test
    void testGetPlayerLocations() {
        assertThrows(InvalidGamePlayerException.class, () -> game.getPlayerLocations(new NormalPlayer("Lucy")));
        game.forceToStop();
        assertThrows(InvalidGameStateForActionException.class, () -> game.getPlayerLocations(players.get(0)));
//        game = (ChutesAndLadderGame) GameFactory.getInstance().createClassicChuteLadder(players);
//        assertNotNull(game.getPlayerLocations(players.get(0)));
    }

}