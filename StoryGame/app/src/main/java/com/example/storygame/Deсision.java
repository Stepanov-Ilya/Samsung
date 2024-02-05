package com.example.storygame;

import java.io.Serializable;
import java.util.Scanner;

public class Deсision implements Serializable {
    public static void makeDecision(Scene scene, Person player, int choose) {
        Modifications amount = scene.modificationsList.get(choose);
        player.modifications.addAmount(amount);
    }


}
