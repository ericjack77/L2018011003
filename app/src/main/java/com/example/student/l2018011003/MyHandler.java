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
    ArrayList<String> title = new ArrayList<>();
    boolean isLink =false ;
    ArrayList<String> links = new ArrayList<>();
    StringBuilder SBLink = new StringBuilder();
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
        }
        if (qName.equals("link"))
        {
            isLink=false;
            Log.d("link",SBLink.toString());
            if(isitem)
            {
                links.add(SBLink.toString());
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
            title.add(new String(ch,start,length));
        }
        if (isLink && isitem)
        {
           SBLink.append(new String(ch,start,length));
        }

    }
}
