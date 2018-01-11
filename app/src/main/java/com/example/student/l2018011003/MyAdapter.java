package com.example.student.l2018011003;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Student on 2018/1/11.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    ArrayList<Moblie01NewsItem> mylist;
    public MyAdapter(Context context,ArrayList<Moblie01NewsItem> mylist)
    {
        this.context=context;
        this.mylist=mylist;
    }
    @Override
    public int getCount() {
        return mylist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null)
        {
            LayoutInflater inflater =LayoutInflater.from(context);
            view = inflater.inflate(R.layout.mylayout,null);
            viewHolder = new ViewHolder();
            viewHolder.tv1 = view.findViewById(R.id.textView);
            viewHolder.tv2 = view.findViewById(R.id.textView2);
            viewHolder.img = view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        }
        else
        {
            viewHolder =(ViewHolder) view.getTag();
        }
        viewHolder.tv1.setText(mylist.get(i).title);
        viewHolder.tv2.setText(mylist.get(i).description);
        Picasso.with(context).load(mylist.get(i).imgurl).into(viewHolder.img);
        return view;
    }

    static class ViewHolder
    {
        TextView tv1;
        TextView tv2;
        ImageView img;
    }
}
