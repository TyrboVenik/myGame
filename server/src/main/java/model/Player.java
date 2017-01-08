package model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by venik on 08.01.17.
 */
public class Player {
    private static AtomicInteger atomic = new AtomicInteger(0);

    private int id;
    private String name;

    public Player(String name){
        id = atomic.getAndIncrement();
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
