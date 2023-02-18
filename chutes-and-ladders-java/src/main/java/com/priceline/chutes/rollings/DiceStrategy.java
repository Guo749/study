package com.priceline.chutes.rollings;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * @author lzn
 * @date 2023/02/14 17:16
 * @Description Dice strategy: The dice have 6 sides and each roll is any number between 1 and 6
 */
public class DiceStrategy implements RollingStrategy {
    static Logger logger = LoggerFactory.getLogger(DiceStrategy.class);

    @Override
    public int roll() {
        int result = new Random().nextInt(6) + 1;
        logger.info("Current player get " + result + " by rolling the dice");
        return result;
    }
}
