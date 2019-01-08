package com.example.hp.ceaserplus.Others;

/**
 * Created by HP on 3/29/2017.
 */

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.Others.newsClass;
import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

public class NewsScreen_Adapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;
    ArrayList<newsClass> newsClassArrayList;

    public NewsScreen_Adapter(Activity a, ArrayList<newsClass> newsClassArrayList) {
        activity = a;
        this.newsClassArrayList = newsClassArrayList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return newsClassArrayList.size();
    }

    public newsClass getItem(int position) {

        newsClass newsClass=newsClassArrayList.get(position);
        return newsClass;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.news_list_adapter, null);
        }

        TextView title = (TextView) vi.findViewById(R.id.title);
        TextView date = (TextView) vi.findViewById(R.id.news_list_date_test);
        TextView details = (TextView) vi.findViewById(R.id.details_news);
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.imageView_news);


        newsClass newsClassobj = newsClassArrayList.get(position);

        title.setText(newsClassobj.getTitle());
        date.setText(newsClassobj.getDate());
        details.setText(  Html.fromHtml(newsClassobj.getDescription()) );
        Picasso.with(this.activity).load(newsClassobj.getThumbnail()).into(thumb_image);


        return vi;
    }
}
