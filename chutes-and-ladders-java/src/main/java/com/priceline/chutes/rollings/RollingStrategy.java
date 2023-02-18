package com.priceline.chutes.rollings;

/**
 * @author lzn
 * @date 2023/02/15 09:50
 * @Description Props that players use to move
 */
public interface RollingStrategy {

    /**
     * the rolling strategy need to be followed
     * @return rolling result
     */
    int roll();
}
