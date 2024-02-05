package com.example.storygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.storygame.databinding.ActivityEndingBinding;
import com.example.storygame.databinding.ActivityMainBinding;
import com.example.storygame.databinding.ActivityResultBinding;

public class EndingActivity extends AppCompatActivity {


    ActivityEndingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEndingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Game game = (Game) getIntent().getSerializableExtra("GAME");

        assert game != null;
        binding.END.setText(game.Ending());


        binding.REPLAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Game newGame = new Game(game.player.name);
                Intent intent = new Intent(EndingActivity.this, MainActivity2.class);
                intent.putExtra("GAME", newGame);
                startActivity(intent);
            }
        });
    }
}