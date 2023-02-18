package com.priceline.chutes.players;

/**
 * @author lzn
 * @date 2023/02/14 22:25
 * @Description Real players
 */
public class NormalPlayer extends Player {
    private final String name;

    public NormalPlayer(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hi, I'm a normal player names " + name;
    }
}
