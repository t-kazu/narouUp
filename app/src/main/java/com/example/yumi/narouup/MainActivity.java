package com.example.yumi.narouup;

import java.io.IOException;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //動的に表示
        TextView textView = findViewById(R.id.text_view);
        textView.setText( getString(R.string.bookmark,10) );
    }
}
