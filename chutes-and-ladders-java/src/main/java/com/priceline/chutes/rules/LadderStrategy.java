package com.priceline.chutes.rules;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author lzn
 * @date 2023/02/15 18:16
 * @Description Ladder rules: allow players to climb from the bottom to the top
 */
public class LadderStrategy implements RuleStrategy {
    private final Random random = new Random(System.currentTimeMillis());

    @Override
    public Map<Integer, Integer> generateGameRule(int boardSize, int count) {
        Map<Integer, Integer> result = new HashMap<>(count);
        for (int i = 0; i < count; ) {
            int start = random.nextInt(boardSize - 1) + 1;
            int end = start + random.nextInt(boardSize - start) + 1;
            if (!result.containsKey(start) && start != end) {
                result.put(start, end);
                i++;
            }
        }
        return result;
    }
}
