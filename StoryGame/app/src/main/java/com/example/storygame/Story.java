package com.example.storygame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Story implements Serializable {
    public List<String> end;
    public List<Scene> plot;
    public Scene currentScene;

    public Story(List<Scene> plot, List<String> end) {
        this.end = end;
        this.plot = plot;
        currentScene = plot.get(0);
    }

    public void nextScene(){
        currentScene = plot.get(plot.indexOf(currentScene) + 1);
    }


    public boolean checkForEnding() {
        return plot.size() == plot.indexOf(currentScene) + 1;
    }


}
