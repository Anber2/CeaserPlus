package com.example.hp.ceaserplus.Others;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OurClinc_ListAdapter extends BaseAdapter {

    private ArrayList<ourClincDataClass> data;
    private static LayoutInflater inflater=null;
    private Activity activity;

    public OurClinc_ListAdapter(Activity a, ArrayList<ourClincDataClass> dataList) {

        /********** Take passed values **********/
        activity = a;
        data=dataList;


        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class  viewHolder
    {
       ImageView clincImg;
        TextView titleTXT,workingHourTXT,addrrssTXT,placeTXT;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

            View vi = convertView;
            OurClinc_ListAdapter.viewHolder holder;

            if (convertView == null)
            {


                vi = inflater.inflate(R.layout.activity_our_clinc__list_adapter, null);


                holder = new viewHolder();
                holder.clincImg = (ImageView) vi.findViewById(R.id.ourClincIMG);
                holder.titleTXT = (TextView) vi.findViewById(R.id.outClincTitlTV);
                holder.workingHourTXT = (TextView) vi.findViewById(R.id.ourClincWorkHTV);
                holder.addrrssTXT = (TextView) vi.findViewById(R.id.ourClincAddresTV);
                holder.placeTXT=(TextView) vi.findViewById(R.id.ourClincPlaceTV);

                Picasso.with(activity).load(data.get(position).getImgLink()).into(holder.clincImg);
                holder.titleTXT.setText(data.get(position).getTitle());
                holder.workingHourTXT.setText(data.get(position).getWorkingHour());
                holder.addrrssTXT.setText(Html.fromHtml(data.get(position).getAddress()));
                holder.placeTXT.setText(Html.fromHtml(data.get(position).getPlace()));


                vi.setTag(holder);
            }
            return vi;

    }
}
