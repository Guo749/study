package com.priceline.chutes.rollings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author lzn
 * @date 2023/02/15 21:00
 * @Description Coin strategy: The coin have 2 sides and each roll is any number between 1 and 2
 */
public class CoinStrategy implements RollingStrategy{
    static Logger logger = LoggerFactory.getLogger(CoinStrategy.class);
    @Override
    public int roll() {
        int result = new Random().nextInt(2) + 1;
        logger.info("Current player get " + result + " by rolling the coin");
        return result;
    }
}
