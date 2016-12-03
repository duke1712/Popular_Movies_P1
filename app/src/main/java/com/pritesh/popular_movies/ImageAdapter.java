package com.pritesh.popular_movies;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by prittesh on 23/11/16.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    ArrayList<String> arrayList;
   // int a[]={R.drawable.cc,R.drawable.cc};

    public ImageAdapter(ArrayList<String> arrayList,Context c) {
        mContext=c;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if(view==null)
        {
            imageView=new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600));
            ViewGroup.MarginLayoutParams marginLayoutParams=new ViewGroup.MarginLayoutParams(imageView.getLayoutParams());
         //   marginLayoutParams.setMargins(10,10,10,10);
            imageView.setLayoutParams(marginLayoutParams);
        }
        else {
            imageView = (ImageView) view;
        }
       // ArrayList<Uri> uri=arrayList.;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detaill_intent=new Intent(mContext,Details_Activity.class);
                detaill_intent.putExtra("POSITION",i);
                mContext.startActivity(detaill_intent);

            }
        });
        Picasso.with(mContext).load(arrayList.get(i)).fit().into(imageView);
   //     imageView.setImageResource(ar);
        return imageView;
    }

}
