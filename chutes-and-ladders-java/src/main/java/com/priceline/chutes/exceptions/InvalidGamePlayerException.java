package com.priceline.chutes.exceptions;

/**
 * @author lzn
 * @date 2023/02/14 21:15
 * @Description Throw exception if board size is invalid
 */
public class InvalidGamePlayerException extends RuntimeException {

    public InvalidGamePlayerException(String message) {
        super(message);
    }
}
