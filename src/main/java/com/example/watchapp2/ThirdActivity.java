package com.example.watchapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class ThirdActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TextView quote = findViewById(R.id.textViewQ);

        Gson gson = new Gson();

        Random generator = new Random();
        try {
            InputStreamReader reader = new InputStreamReader(getAssets().open("QuotePack/0"+(generator.nextInt(2)+1)+".json"));
            JsonObject data = gson.fromJson(reader,JsonObject.class);
            reader.close();

            quote.setText(data.get("quote").getAsString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}