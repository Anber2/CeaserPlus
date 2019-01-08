package com.example.hp.ceaserplus.ActivityClasses;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.Others.OfferClass;
import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OffersPupup extends Activity {
    TextView offerTitle, offerDate, offerDescription;
    ImageView offerImage;
   public static ArrayList<OfferClass> offerClassArrayList;
    public static int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_offers_pupup);


        try {


            offerTitle = (TextView) findViewById(R.id.offer_title);
            offerDate = (TextView) findViewById(R.id.offer_date);
            offerDescription = (TextView) findViewById(R.id.offer_description);
            offerImage = (ImageView) findViewById(R.id.offer_img);

            offerTitle.setText(offerClassArrayList.get(position).getTitle());
            offerDate.setText(offerClassArrayList.get(position).getDate());
            offerDescription.setText(Html.fromHtml(offerClassArrayList.get(position).getDescription()));
            Picasso.with(getApplicationContext()).load(offerClassArrayList.get(position).getImageUrl()).into(offerImage);
        }
        catch (Exception xx)
        {}



    }

}
