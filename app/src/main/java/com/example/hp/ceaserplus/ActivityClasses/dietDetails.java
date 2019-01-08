package com.example.hp.ceaserplus.ActivityClasses;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.Others.dietClass;
import com.example.hp.ceaserplus.Others.newsClass;
import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

/**
 * Created by HP on 4/11/2017.
 */

public class dietDetails extends Activity {

    TextView diet_title, MealCalories, diet_Period, MealDescription;
    ImageView diet_thumb_image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_diet_details);

        dietClass dietClassobj = SessionClass.dietClassobj;


        diet_title = (TextView) findViewById(R.id.dite_title);
        MealCalories = (TextView) findViewById(R.id.MealCalories);
        diet_Period = (TextView) findViewById(R.id.diet_Period);
        MealDescription = (TextView) findViewById(R.id.MealDescription);

        diet_thumb_image = (ImageView) findViewById(R.id.imageView9);

//        diet_title.setText(dietClassobj.getMasterMealItem());
//        MealCalories.setText(dietClassobj.getMealCalories());
//        diet_Period.setText(dietClassobj.getMealPeriod());
//        MealDescription.setText(dietClassobj.getMealDescription());


        //Picasso.with(getApplicationContext()).load(dietClassobj.getMealItem()).into(diet_thumb_image);


    }
}