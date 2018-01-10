package com.example.student.l2018011003;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View v)
    {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String str_url ="https://acg.gamer.com.tw/billboard.php?t=2&p=Android";
                URL url = null;
                try {
                    url =new URL(str_url);
                    HttpURLConnection conn=(HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(inputStream);
                    BufferedReader br=new BufferedReader(isr);
                    String str1;
                    StringBuilder sb=new StringBuilder();
                    while ((str1 = br.readLine()) != null)
                    {
                        sb.append(str1);
                    }
                    String str=sb.toString();
                    Log.d("NET",str);
                    int index1 = str.indexOf("<div class=\"ACG-mainbox1\"><!--資料開始-->");
                    int index2 = str.indexOf("No.1",index1);
                    int index3 = str.indexOf("RO 仙境傳說：守護永恆的愛",index2);
                    Log.d("Net","index1="+index1+"index2="+index2+"index3="+index3);
                    String data = str.substring(index2+62,index2+76);
                    String data2 = "No,1=" + data;
                    Log.d("Net",data2);
                    br.close();
                    isr.close();
                    inputStream.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
