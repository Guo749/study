package com.priceline.chutes.rollings;

import com.priceline.chutes.enums.RollType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lzn
 * @date 2023/02/15 09:50
 * @Description Rolling strategy factory
 */
public class RollingStrategyFactory {

    private final static Map<RollType, RollingStrategy> STRATEGIES = new HashMap<>();

    static {
        STRATEGIES.put(RollType.Dice, new DiceStrategy());
        STRATEGIES.put(RollType.Coin, new CoinStrategy());
    }

    public static RollingStrategy getRollingStrategy(RollType rollType){
        if (rollType == null) {
            throw new IllegalArgumentException("rollType should not be empty.");
        }
        return STRATEGIES.get(rollType);
    }
}