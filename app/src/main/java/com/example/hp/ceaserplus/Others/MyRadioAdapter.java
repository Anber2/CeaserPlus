package com.example.hp.ceaserplus.Others;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.hp.ceaserplus.R;

import java.util.ArrayList;

/**
 * Created by HP on 4/25/2017.
 */

public class MyRadioAdapter extends BaseAdapter {
    private Context context;

    private Activity activity;

    private ArrayList<dietClass> mVariations;
    private int mSelectedVariation;

    private static LayoutInflater inflater = null;


    public MyRadioAdapter(Activity a, ArrayList<dietClass> variations) {
        this.activity = a;
        this.mVariations = variations;
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mVariations.size();
    }

    @Override
    public Object getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;


        if (convertView == null) {

            view = inflater.inflate(R.layout.diet_table_list_item, null);
        }

        TextView Calories = (TextView) view.findViewById(R.id.diet_table_kal);
        RadioButton radio = (RadioButton) view.findViewById(R.id.meal_radioButton);


        dietClass dietClassobj = mVariations.get(position);


        radio.setText(dietClassobj.getMealName());
        Calories.setText(dietClassobj.getMealCalories()+" Cal");

        return view;
    }

}