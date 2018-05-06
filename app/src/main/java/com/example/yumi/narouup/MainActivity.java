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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button button = findViewById(R.id.button);

        //button.setOnClickListener(this);
        //textView.setText( getString(R.string.bookmark,10) );

        TextView textView = (TextView)findViewById(R.id.text_view);
        String url = "https://techbooster.org/";

        try {
            // HTMLのドキュメントを取得
            Document document = Jsoup.connect(url).get();

            // titleタグを取得
            Elements title = document.getElementsByTag("title");
            // こちらでもtitleを取得できる
            // String title = document.title();

            // bodyタグをIDから取得
            Elements body = document.getElementsByTag("body");
            // こちらでもbodyを取得できる
            //Element body = document.body();

            textView.setText(title.toString() + "\n" + body.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    // @Override
    // public void onClick(View v) {
    //     textView.setText("WORKING");
    //     String url = "http://www.google.co.jp/";
    //     Scraping task = new Scraping();
    //     task.execute(url); //非同期処理開始
    // }

    // public class Scraping extends AsyncTask<String,Void,String>{

    //     private Elements title = null;

    //     @Override
    //     protected String doInBackground(String... params) {
    //         Document document = null;
    //         try {
    //             document = Jsoup.connect(params[0]).get();
    //             title = document.getElementsByTag("title");
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //         return null;
    //     }

    //     @Override
    //     protected void onPostExecute(String result) {
    //         TextView textView = (TextView)findViewById(R.id.text_view);
    //         if (true){
    //             textView.setText(title.toString());
    //         }else{
    //             textView.setText("FAILURE");
    //         }
    //     }
    // }

}
