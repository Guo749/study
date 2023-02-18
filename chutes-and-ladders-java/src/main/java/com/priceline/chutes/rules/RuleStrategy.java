package com.priceline.chutes.rules;

import java.util.Map;

/**
 * @author lzn
 * @date 2023/02/15 18:14
 * @Description Game strategy
 */
public interface RuleStrategy {

    /**
     * Generate game rules to make the game more interesting
     * @param boardSize total board size
     * @param count number of current rules
     * @return Hash table with start and end positions
     */
    Map<Integer, Integer> generateGameRule(int boardSize, int count);
}
