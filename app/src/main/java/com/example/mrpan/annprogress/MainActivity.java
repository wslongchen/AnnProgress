package com.example.mrpan.annprogress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnProgress annProgress=(AnnProgress)findViewById(R.id.progress);
        annProgress.setCustomText("100%");
        annProgress.setCustomCurrentProgress(80);
    }
}
