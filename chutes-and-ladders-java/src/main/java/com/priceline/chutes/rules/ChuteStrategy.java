package com.priceline.chutes.rules;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author lzn
 * @date 2023/02/15 18:16
 * @Description Chute rules: allow players to slide from the top to the bottom
 */
public class ChuteStrategy implements RuleStrategy {
    private final Random random = new Random(System.currentTimeMillis());

    @Override
    public Map<Integer, Integer> generateGameRule(int boardSize, int count) {
        Map<Integer, Integer> result = new HashMap<>(count);
        for (int i = 0; i < count; ) {
            int start = random.nextInt(boardSize - 1) + 1;
            int end = random.nextInt(start);
            if (!result.containsKey(start)) {
                result.put(start, end);
                i++;
            }
        }
        return result;
    }
}
