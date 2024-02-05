package com.example.storygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.storygame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.ET.getText().toString();

                if (name.isEmpty()) {
                    binding.ERROR.setText("Введите свое имя");

                } else {
                    binding.ERROR.setText("");
                    Log.e("RESULT", name);
                    Intent sendIntent = new Intent(MainActivity.this, MainActivity2.class);
                    Game game = new Game(name);
                    sendIntent.putExtra("GAME", game);

                    startActivity(sendIntent);
                }
            }
        });

    }
}