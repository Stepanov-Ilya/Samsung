package com.example.storygame;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Modifications implements Serializable {
    public int capital;
    public int health;
    public int reputation;

    public Modifications() {
    }


    public Modifications(int capital, int health, int reputation) {
        this.capital = capital;
        this.health = health;
        this.reputation = reputation;
    }

    private <T extends Comparable<T>> String show(T x) {
        if (x.compareTo((T) Integer.valueOf(0)) > 0) {
            return '+' + String.valueOf(x);
        } else {
            return String.valueOf(x);
        }
    }


    public String chowModifications() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedCapital = (capital < 0) ? decimalFormat.format(capital) : '+' + decimalFormat.format(capital);
        return "Состояние: " + formattedCapital +
                "$\nЗдоровье: " + show(health) +
                "%\nУважение " + show(reputation) + "%";
    }
}
