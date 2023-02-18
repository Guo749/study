package com.priceline.chutes.game;

import com.priceline.chutes.enums.RollType;
import com.priceline.chutes.enums.RuleType;
import com.priceline.chutes.players.Player;
import com.priceline.chutes.rollings.RollingStrategyFactory;
import com.priceline.chutes.rules.RuleStrategy;
import com.priceline.chutes.rules.RuleStrategyFactory;

import java.util.List;

/**
 * @author lzn
 * @date 2023/02/15 11:13
 * @Description game factory
 */
public class GameFactory {

    private static volatile GameFactory INSTANCE;

    private GameFactory(){

    }

    public static GameFactory getInstance(){
        GameFactory temp = INSTANCE;
        if(null == temp){
            synchronized (GameFactory.class){
                temp = INSTANCE;
                if(null == temp){
                    temp = new GameFactory();
                    INSTANCE = temp;
                }
            }
        }

        return INSTANCE;
    }

    public Game createClassicChuteLadder(List<Player> players) {
        return new ChutesAndLadderGame.Builder()
                .chutes(5)
                .ladders(7)
                .boardWidth(10)
                .boardHeight(10)
                .players(players)
                .rollingStrategy(RollingStrategyFactory.getRollingStrategy(RollType.Dice))
                .build();
    }
}
