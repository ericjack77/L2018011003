package com.example.student.l2018011003;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Student on 2018/1/10.
 */

public class MyHandler extends DefaultHandler {
    boolean isTitle = false ;
    boolean isitem = false ;
    boolean isLink =false ;
    boolean isDescription =false;
    StringBuilder SBLink = new StringBuilder();
    StringBuilder SBDescri = new StringBuilder();

    public ArrayList<Moblie01NewsItem> newsItems = new ArrayList<>();
    Moblie01NewsItem item;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        switch (qName)
        {
            case "title":
                isTitle=true;
                break;
            case "item":
                isitem=true;
                item=new Moblie01NewsItem();
                break;
            case "link":
                isLink=true;
                break;
            case "description":
                isDescription=true;
                SBDescri = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        switch (qName)
        {
            case "title":
                isTitle=false;
                break;
            case "item":
                isitem=false;
                newsItems.add(item);
                break;
            case "link":
                isLink=false;
                Log.d("link",SBLink.toString());
                if(isitem)
                {
                    item.url=SBLink.toString();
                    SBLink=new StringBuilder();
                }
                break;
            case "description":
                isDescription=false;
                if (isitem)
                {
                    String str=SBDescri.toString();

                    Pattern pattern =Pattern.compile("http.*jpg");
                    Matcher m = pattern.matcher(str);
                    String imgurl="";
                    if(m.find())
                    {
                        imgurl=m.group(0);
                    }

                    str=str.replaceAll("<img src.*/>","");
                    item.description=str;
                    item.imgurl=imgurl;
                    Log.d("imgurl", imgurl);
                }
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(isTitle && isitem)
        {
            Log.d("Net",new String(ch,start,length));
            item.title=new String(ch,start,length);
        }
        if (isLink && isitem)
        {
           SBLink.append(new String(ch,start,length));
        }
        if(isDescription && isitem)
        {
           SBDescri.append(new String(ch,start,length));
        }

    }
}
