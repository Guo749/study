package com.priceline.chutes.exceptions;

/**
 * @author lzn
 * @date 2023/02/14 21:04
 * @Description Throw exception if board size is invalid
 */
public class InvalidBoardSizeException extends RuntimeException {

    public InvalidBoardSizeException(String message) {
        super(message);
    }
}
