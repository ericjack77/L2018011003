package com.example.student.l2018011003;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv =findViewById(R.id.listview);
    }

    public void click1(View v)
    {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String str_url ="https://www.mobile01.com/rss/news.xml";
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
                    final MyHandler myHandler=new MyHandler();
                    SAXParserFactory spf = SAXParserFactory.newInstance();
                    SAXParser sp = spf.newSAXParser();
                    XMLReader xr = sp.getXMLReader();
                    xr.setContentHandler(myHandler);
                    xr.parse(new InputSource(new StringReader(str)));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter = new ArrayAdapter<String>(MainActivity.this,
                                    android.R.layout.simple_list_item_1,myHandler.title
                                    );
                            lv.setAdapter(adapter);
                        }
                    });

                    br.close();
                    isr.close();
                    inputStream.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}
