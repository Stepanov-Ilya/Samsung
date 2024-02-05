package com.example.storygame;

import java.io.Serializable;
import java.util.List;

public class Scene implements Serializable {
    public String name;
    public String description;
    public Options options;
    public List<Modifications> modificationsList;
    public List<String> results;

    public Scene(String name, String description, Options options, List<Modifications> modificationsList, List<String> results) {
        this.name = name;
        this.description = description;
        this.options = options;
        this.modificationsList = modificationsList;
        this.results = results;
    }
}
