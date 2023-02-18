package com.priceline.chutes.players;

/**
 * @author lzn
 * @date 2023/02/14 22:40
 * @Description computer players
 */
public class ComputerPlayer extends Player {

    private final String name;

    public ComputerPlayer(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hi, I'm a computer player names " + name;
    }
}
