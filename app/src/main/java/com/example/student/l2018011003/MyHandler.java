package com.example.student.l2018011003;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/10.
 */

public class MyHandler extends DefaultHandler {
    boolean isTitle = false ;
    boolean isitem = false ;
    boolean isLink =false ;
    StringBuilder SBLink = new StringBuilder();
    //public ArrayList<String> titles = new ArrayList<>();
    //public ArrayList<String> links = new ArrayList<>();

    public ArrayList<Moblie01NewsItem> newsItems = new ArrayList<>();
    Moblie01NewsItem item;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if(qName.equals("title"))
        {
            isTitle=true;
        }
        if (qName.equals("item"))
        {
            isitem=true;
            item = new Moblie01NewsItem();
        }
        if(qName.equals("link"))
        {
            isLink=true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equals("title"))
        {
            isTitle=false;
        }
        if (qName.equals("item"))
        {
            isitem=false;
            newsItems.add(item);
        }
        if (qName.equals("link"))
        {
            isLink=false;
            Log.d("link",SBLink.toString());
            if(isitem)
            {
                //links.add(SBLink.toString());
                item.url = SBLink.toString();
                SBLink=new StringBuilder();
            }

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(isTitle && isitem)
        {
            Log.d("Net",new String(ch,start,length));
            //title.add(new String(ch,start,length));
            item.title=new String(ch,start,length);
        }
        if (isLink && isitem)
        {
           SBLink.append(new String(ch,start,length));
        }

    }
}
