package com.priceline.chutes.exceptions;

/**
 * @author lzn
 * @date 2023/02/14 21:03
 * @Description throw exception if game state inconsistent with expectations
 */
public class InvalidGameStateForActionException extends RuntimeException{

    public InvalidGameStateForActionException(String message) {
        super(message);
    }
}
