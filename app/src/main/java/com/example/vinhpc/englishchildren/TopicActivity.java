package com.example.vinhpc.englishchildren;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopicActivity extends AppCompatActivity {

    Button butAnimal, butFruit, butFurniture;
    Intent intentanimal, intentfruit, intentfurni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        butAnimal = (Button)findViewById(R.id.button15);
        butFruit = (Button)findViewById(R.id.button16);
        butFurniture = (Button)findViewById(R.id.button17);

        intentanimal = new Intent(this, NewGameActivity.class);
        intentfruit = new Intent(this, Frui_NewGameActivity.class);
        intentfurni = new Intent( this, Furn_NewGameActivity.class);
        butFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentfurni);
            }
        });
        butAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentanimal);
            }
        });

        butFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentfruit);
            }
        });
    }
}
