package com.priceline.chutes.rules;

import com.priceline.chutes.enums.RuleType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lzn
 * @date 2023/02/15 18:17
 * @Description Rule Strategy factory
 */
public class RuleStrategyFactory {

    private static final Map<RuleType, RuleStrategy> STRATEGIES = new HashMap<>();

    static {
        STRATEGIES.put(RuleType.CHUTE, new ChuteStrategy());
        STRATEGIES.put(RuleType.LADDER, new LadderStrategy());
    }

    public static RuleStrategy getGameRuleStrategy(RuleType ruleType) {
        if (ruleType == null) {
            throw new IllegalArgumentException("ruleType should not be empty.");
        }
        return STRATEGIES.get(ruleType);
    }
}
