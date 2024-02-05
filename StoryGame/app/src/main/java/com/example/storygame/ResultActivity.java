package com.example.storygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.storygame.databinding.ActivityResultBinding;

import java.io.Serializable;

public class ResultActivity extends AppCompatActivity implements Serializable {


    ActivityResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Game game = (Game) getIntent().getSerializableExtra("GAME");
        int i = getIntent().getIntExtra("ID", 0);

        assert game != null;
        Deсision.makeDecision(game.story.currentScene, game.player, i);
        binding.RESULT.setText(game.story.currentScene.results.get(i));
        binding.AMOUNT.setText(game.story.currentScene.modificationsList.get(i).chowModifications());
        binding.STATS.setText("Параметры\n" + game.player.showStats());


        binding.CONTINUE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (game.story.checkForEnding()) {
                    Intent intent = new Intent(ResultActivity.this, EndingActivity.class);
                    intent.putExtra("GAME", game);
                    startActivity(intent);
                } else {
                    game.story.nextScene();
                    Intent intent = new Intent(ResultActivity.this, MainActivity2.class);
                    intent.putExtra("GAME", game);
                    startActivity(intent);
                }
            }
        });
    }
}