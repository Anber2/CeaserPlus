package com.example.hp.ceaserplus.Others;

import android.app.Activity;
import android.content.Context;
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
import java.util.HashMap;

/**
 * Created by HP on 4/9/2017.
 */

public class NewsDetails_Addapter extends BaseAdapter {

    private Context context;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater = null;



    ArrayList<newsClass> newsClassArrayList;

    public NewsDetails_Addapter(Context context, ArrayList<newsClass> newsClassArrayList) {
        this.context = context;
        this.newsClassArrayList = newsClassArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return newsClassArrayList.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;

        if (convertView == null) {
            vi = inflater.inflate(R.layout.activity_news_details, null);
        }

        TextView title = (TextView) vi.findViewById(R.id.newDetailTitleTXT);
        TextView details = (TextView) vi.findViewById(R.id.newDetalDescTXT);
        ImageView thumb_image = (ImageView) vi.findViewById(R.id.imageView);


        newsClass newsClassobj = newsClassArrayList.get(position);

        title.setText(newsClassobj.getTitle());
        details.setText(Html.fromHtml(newsClassobj.getDescription()));
        Picasso.with(context).load(newsClassobj.getThumbnail()).into(thumb_image);


        return vi;
    }
}
