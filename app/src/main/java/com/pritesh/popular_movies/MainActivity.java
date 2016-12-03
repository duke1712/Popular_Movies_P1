package com.pritesh.popular_movies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    getmovie mov=new getmovie();
    static JSONArray results;
    static ImageAdapter image;
    private Spinner spinner;
    private GridView movies;
    private ArrayList<String> arrayList=new ArrayList<>();
    private String sort="Popular";
    ProgressDialog mProg;
    OkHttpClient client = new OkHttpClient();
    @Override
    protected void onNewIntent(Intent intent) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProg=new ProgressDialog(this,ProgressDialog.STYLE_SPINNER);
        spinner=(Spinner)findViewById(R.id.spinner);
        movies=(GridView)findViewById(R.id.movies);
  //      mov.doInBackground(Constants.BASE_URL+Constants.POPULAR_URL+Constants.API_KEY);

        ArrayAdapter<CharSequence> arrayAdapter=ArrayAdapter.createFromResource(this,R.array.filter, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
//        try {
//  //          update("Popular");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sort=adapterView.getAdapter().getItem(i).toString();
                try {
                    update(sort);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


       image=new ImageAdapter(arrayList,this);
        movies.setAdapter(image);


    }

    private void update(String sort) throws IOException, JSONException {
        String url=Constants.BASE_URL;
        arrayList.clear();
        if(sort.equalsIgnoreCase("Popular"))
        url+=Constants.POPULAR_URL+Constants.API_KEY;
        else
        url+=Constants.RATED_URL+Constants.API_KEY;
        new getmovie().execute(url);
   }

    String doGetRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
    public class getmovie extends AsyncTask<String,Integer,Long>{
        @Override
        public Long doInBackground(String... strings) {
            try {
                String JSON_STRING = doGetRequest(strings[0]);
                results= new JSONObject(JSON_STRING).getJSONArray("results");
                int length = results.length();
                for (int i = 0; i < length; i++) {
                    JSONObject movie = results.getJSONObject(i);
                    String img_url = movie.getString("poster_path");
                    img_url = img_url.substring(1);
                    arrayList.add(Constants.IMAGE_BASE_URL + img_url +"?"+ Constants.API_KEY);

                }


            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

            mProg.show();
        }

        @Override
        protected void onPostExecute(Long aLong) {
            mProg.cancel();
            image.notifyDataSetChanged();
        }
    }

}
