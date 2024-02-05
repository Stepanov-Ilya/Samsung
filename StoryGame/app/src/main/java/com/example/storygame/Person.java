package com.example.storygame;

import static java.lang.String.format;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.DecimalFormat;

public class Person implements Serializable {
    public PersonModifications modifications;
    public String name;

    public Person(String name) {
        modifications = new PersonModifications();
        this.name = name;
    }

    public String showStats() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String formattedCapital = decimalFormat.format(modifications.capital);

        return name + " : \nСостояние: " + formattedCapital +
                "$\nЗдоровье: " + modifications.health +
                "%\nУважение: " + modifications.reputation +
                "%";
    }
}
