package com.example.hp.ceaserplus.Others;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.R;

import java.util.ArrayList;

/**
 * Created by HP on 4/6/2017.
 */


public class FAQListAdapter extends BaseAdapter {

    private  ArrayList<FAQ_DataClass>  data;
    private static LayoutInflater inflater=null;
    private Activity activity;




    public FAQListAdapter(Activity a, ArrayList<FAQ_DataClass> dataList) {

        /********** Take passed values **********/
        activity = a;
        data=dataList;


        /***********  Layout inflator to call external xml layout () ***********/
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }
    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder
    {
        TextView questionTV,answerTV;
        //ImageView adsImage;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(convertView==null){


            vi = inflater.inflate(R.layout.faq_custom_list_adptr, null);



            holder = new ViewHolder();
            holder.questionTV = (TextView) vi.findViewById(R.id.questioFAQTXT);
            holder.answerTV=(TextView)vi.findViewById(R.id.answerFAQTXT);

            holder.questionTV.setText(data.get(position).getQuestion());
            holder.answerTV.setText(data.get(position).getAnswer());

            vi.setTag( holder );
        }
        return vi;
    }


}
