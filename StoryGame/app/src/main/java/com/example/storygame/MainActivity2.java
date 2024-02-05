package com.example.storygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.example.storygame.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Game game = (Game) getIntent().getSerializableExtra("GAME");

        assert game != null;
        binding.SceneName.setText(game.story.currentScene.name);
        binding.SceneIntro.setText(game.story.currentScene.description);
        binding.OPTION1.setText(game.story.currentScene.options.option1);
        binding.OPTION2.setText(game.story.currentScene.options.option2);
        binding.OPTION3.setText(game.story.currentScene.options.option3);


        binding.BUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = binding.RADIOGROUP.getCheckedRadioButtonId();
                if (i != -1) {
                    binding.ERROR.setText("");
                    Intent intent = new Intent(MainActivity2.this, ResultActivity.class);
                    intent.putExtra("GAME", game);
                    intent.putExtra("ID", i - binding.OPTION1.getId());
                    startActivity(intent);
                } else {
                    binding.ERROR.setText("Выберите один из вариантов");
                }
            }
        });
    }
}