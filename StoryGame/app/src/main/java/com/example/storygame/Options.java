package com.example.storygame;

import java.io.Serializable;

public class Options implements Serializable {
    public String option1;
    public String option2;
    public String option3;

    public Options(String option1, String option2, String option3) {
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
    }
}
