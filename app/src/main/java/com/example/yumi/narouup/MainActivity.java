package com.example.yumi.narouup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.AsyncTask;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(this);
        //textView.setText( getString(R.string.bookmark,10) );
    }


    @Override
    public void onClick(View v) {
        TextView textView = (TextView)findViewById(R.id.text_view);
        textView.setText("WORKING");
        String url = "https://www.google.co.jp/";
        Scraping task = new Scraping();
        task.execute(url); //非同期処理開始
    }

    public class Scraping extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            Document document = null;
            String title = null;
            try {
                document = Jsoup.connect(params[0]).userAgent("Mozilla").get();
                title = document.title();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return title;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView = (TextView)findViewById(R.id.text_view);
            if (result != null){
                textView.setText(result);
            }else{
                textView.setText("FAILURE");
            }
        }
    }

}
