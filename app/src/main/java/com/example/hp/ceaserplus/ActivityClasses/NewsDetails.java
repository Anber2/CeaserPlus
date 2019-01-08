package com.example.hp.ceaserplus.ActivityClasses;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.ceaserplus.Others.NewsDetails_Addapter;
import com.example.hp.ceaserplus.Others.NewsScreen_Adapter;
import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.Others.newsClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP on 4/9/2017.
 */

public class NewsDetails extends Activity {

    TextView titlee, details;
    ImageView thumb_image;
    String title, Thumbnail, description;

    private ProgressBar pBar;




    NewsDetails_Addapter adapter;

    ArrayList<newsClass> newsClassArr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_news_details);

        newsClass newsClassobj= SessionClass.newsClassobj;

        titlee = (TextView) findViewById(R.id.newDetailTitleTXT);
        details = (TextView) findViewById(R.id.newDetalDescTXT);
        thumb_image = (ImageView) findViewById(R.id.imageView);

        titlee.setText(newsClassobj.getTitle());
        details.setText(Html.fromHtml(newsClassobj.getDescription()) );
        Picasso.with(getApplicationContext()).load(newsClassobj.getThumbnail()).into(thumb_image);


    }



        }
