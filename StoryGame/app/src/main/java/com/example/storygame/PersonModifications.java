package com.example.storygame;

import java.io.Serializable;

public class PersonModifications extends Modifications implements Serializable {

    public PersonModifications() {
        capital = 1_000_000;
        health = 50;
        reputation = 50;
    }


    public void addAmount(Modifications modifications) {
        int capital = modifications.capital;
        int health = modifications.health;
        int reputation = modifications.reputation;
        this.capital += capital;
        this.health = (this.health + health > 0) ? Math.min(this.health + health, 100) : 0;
        this.reputation = (this.reputation + reputation > 0) ? Math.min(this.reputation + reputation, 100) : 0;
    }

}
