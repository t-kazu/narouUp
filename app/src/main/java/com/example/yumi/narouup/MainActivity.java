package com.example.yumi.narouup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.AsyncTask;

import java.io.IOException;

import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
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

        TextView textView = (TextView)findViewById(R.id.text_view);
        textView.setText("WORKING");

        String url = "https://ncode.syosetu.com/novelview/infotop/ncode/n3938ch/";
        Scraping task = new Scraping();
        task.execute(url); //非同期処理開始
    }

    public class Scraping extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            Document document = null;
            String title = null;
            try {
                document = Jsoup.connect(params[0]).get();
                Log.d("test", document.toString());
                if(document.title() == "年齢確認"){
                    return document.title();
                } else {
                    title = document.select("td").get(8).text();
                }
            } catch (IOException e) {
                e.printStackTrace(); //強制終了
            }
            return title;
        }

        @Override
        protected void onPostExecute(String result) {
            TextView textView = (TextView)findViewById(R.id.text_view);
            if (result != null){
                textView.setText( getString(R.string.bookmark,result) );
            }else{
                textView.setText("FAILURE");
            }
        }
    }

}
