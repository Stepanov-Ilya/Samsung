package com.example.storygame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game implements Serializable {
    public Story story;
    public Person player;

    public Game(String name) {
        player = new Person(name);
        List<Scene> plot = Arrays.asList(
                new Scene("Начало", "Пришло время начать работу, выбери на чем сегодня будешь спекулировать",
                        new Options("Bitcoin", "Ethereum", "Dogecoin"),
                        Arrays.asList(
                                new Modifications(-100_000, -5, -10),
                                new Modifications(10_000, 0, 10),
                                new Modifications(1_000_000, 15, 20)
                        ),
                        Arrays.asList("Bitcoin сегодня в плохом настроении",
                                "Ethereum сегодня стабилен, даже получилось немного выйти в плюс",
                                "Илон Маск репостнул твит о запуске оплаты заправок Tesla с помощью Dogecoin")
                ),
                new Scene("Подработка", "Босс предложил ночную подоработку," +
                        " нужно будет смотреть стримы Илона Маска и спекулировать на инфоповодах.",
                        new Options("Конечно, да", "Откажусь, пойду посплю", "Я не знаю английский"),
                        Arrays.asList(
                                new Modifications(500_000, -15, 10),
                                new Modifications(0, 15, -10),
                                new Modifications(0, 15, -20)
                        ),
                        Arrays.asList("Вы подзаработали, но состояние здоровья ухудшилось",
                                "Вы сохранили здоровый сон, но стали чувствовать косые взгляды от Босса",
                                "Вы упали в глазах начальства")
                ),
                new Scene("Облава", "В вашей компании стали появляться истории" +
                        " обыска с задержанием сотрудников, начальство предложило вам переждать в филеале Грузии.",
                        new Options("Согласиться", "Согласиться, но попросить премию", "Остаться работать на прежнем месте"),
                        Arrays.asList(
                                new Modifications(0, 10, 20),
                                new Modifications(500_000, 10, -10),
                                new Modifications(-1_000_000, -30, 30)
                        ),
                        Arrays.asList("Всего через месяц все нормализовалось и вы вернулись обратно",
                                "Начальство нехотя выписала вам премию, и через месяц вы вернулись обратно",
                                "К вам пришли домой, пришлось решить все добровольным пожертвованием")
                )
        );

        List<String> end = Arrays.asList(
                "Вы умерли. Событие, которое произошло с вами накануне было" +
                        " последней каплей вашего организма, орварыалыХидроз взял свое.",
                "Вас уволили со стажировки. Начальство решило, что вам не по пути.",
                "Вас взяли на должность вице-призедента компании, лучше и произойти не могло. Начальство увидело в вас потенциал."
        );
        this.story = new Story(plot, end);
    }



    public String Ending() {
        if (player.modifications.health < 10) {
            return story.end.get(0);
        } else if (player.modifications.capital < 1_000_000 | player.modifications.reputation < 50) {
            return story.end.get(1);
        } else return story.end.get(2);
    }


}
