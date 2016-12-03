package com.pritesh.popular_movies;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Details_Activity extends AppCompatActivity {
    Intent intent;
    int position;
    TextView movieName,date,synopsis;
    TextView ratingBar;
    ImageView imageView;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_);
        intent=getIntent();


        position=intent.getIntExtra("POSITION",0);
        movieName=(TextView)findViewById(R.id.movieName);
        date=(TextView)findViewById(R.id.releaseDate);
        synopsis=(TextView)findViewById(R.id.synopsis);
        ratingBar=(TextView) findViewById(R.id.textView3);
        imageView=(ImageView)findViewById(R.id.imageView);
        try {
        movieName.setText(MainActivity.results.getJSONObject(position).getString("original_title"));
        date.setText(MainActivity.results.getJSONObject(position).getString("release_date"));
        synopsis.setText(MainActivity.results.getJSONObject(position).getString("overview"));
        ratingBar.setText(MainActivity.results.getJSONObject(position).getDouble("vote_average")+"/10");
            String img_url=MainActivity.results.getJSONObject(position).getString("poster_path");
            img_url=img_url.substring(1);
            Picasso.with(getApplicationContext()).load(Constants.IMAGE_BASE_URL+img_url+"?"+Constants.API_KEY).fit().into(imageView);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    }

