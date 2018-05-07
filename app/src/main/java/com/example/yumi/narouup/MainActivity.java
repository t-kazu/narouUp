package com.example.yumi.narouup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.os.AsyncTask;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView)findViewById(R.id.text_view);
        textView.setText("WORKING");
        startTask();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    public void onClick(View view) {
        TextView textView = (TextView)findViewById(R.id.text_view);
        textView.setText("WORKING");
        startTask();
    }

    public void startTask() {
        String ncode = "n9806eq";
        String url = "https://api.syosetu.com/novel18api/api/?libtype=2&out=json&of=f&ncode=" + ncode;
        Scraping task = new Scraping();
        task.execute(url); //非同期処理開始
    }

    public class Scraping extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            String title = null;
            HttpURLConnection con = null;
            try {
                URL url = new URL(params[0]);
                con = (HttpURLConnection) url.openConnection();
                con.connect();

                final int status = con.getResponseCode();
                if  (status == HttpURLConnection.HTTP_OK) {
                    InputStream in = con.getInputStream();
                    InputStreamReader objReader = new InputStreamReader(in);
                    BufferedReader objBuf = new BufferedReader(objReader);
                    StringBuffer strBuilder = new StringBuffer();
                    String sLine;
                    while((sLine = objBuf.readLine()) != null){
                        Log.d("test",sLine);
                        strBuilder.append(sLine);
                    }
                    String str_json = strBuilder.toString();
                    in.close();

                    JSONArray json_array = new JSONArray(str_json);
                    if (json_array.length() == 2) {
                        JSONObject json_obj = json_array.getJSONObject(1); //"fav_novel_cnt"
                        str_json = json_obj.getString("fav_novel_cnt");
                    }
                    return str_json;
                }
            } catch (IOException e) {
                e.printStackTrace(); //強制終了
            } catch (JSONException e) {
		return "Error";
            } finally {
                if(con != null){
                    con.disconnect();
                }
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
