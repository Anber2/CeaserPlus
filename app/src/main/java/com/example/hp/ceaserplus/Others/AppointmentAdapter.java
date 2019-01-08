package com.example.hp.ceaserplus.Others;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.UpdateAppointmentActivity;
import com.example.hp.ceaserplus.Fragments.ProfileDetailsTabs;
import com.example.hp.ceaserplus.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by HP on 4/9/2017.
 */

public class AppointmentAdapter extends BaseAdapter {
    private Context context;
    public static ArrayList<AppointmentsClass> appointmentsClassList;
    Dialog dialog;
    TextView startDateText;
    TextView endDtaeText;
    String status;


//    public AppointmentAdapter(Context context, ArrayList<AppointmentsClass> appointmentsClassList) {
//        this.context = context;
//        this.appointmentsClassList = appointmentsClassList;
//
//
//    }

    public AppointmentAdapter(Context context, ArrayList<AppointmentsClass> appointmentsClassList, String status) {
        this.context = context;
        this.appointmentsClassList = appointmentsClassList;
        this.status = status;


    }

    @Override
    public int getCount() {
        return appointmentsClassList.size();
    }

    @Override
    public Integer getItem(int position) {

        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.appointment_grid_item_layout, null);
            holder = new ViewHolder();
            holder.idTextView = (TextView) view.findViewById(R.id.id_val);
            holder.dateTextView = (TextView) view.findViewById(R.id.date_val);
            holder.detailsTextView = (TextView) view.findViewById(R.id.details_val);
            holder.updateAppointmentBtn = (Button) view.findViewById(R.id.update_app_btn);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        AppointmentsClass appointmentsClass = appointmentsClassList.get(position);

        // for show only sucsess appointment
//        if(appointmentsClass.isAppointmentSuccess()){
//
//        }else{
//
//        }

        holder.idTextView.setText(":  " + appointmentsClass.getAppointmentId());
        holder.dateTextView.setText(":  " + appointmentsClass.getAppointmentStartDate());
        holder.detailsTextView.setText(":  " + appointmentsClass.getAppointmentDetails());
        holder.updateAppointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(status.equals("offline")){
                    Toast.makeText(context,"No connection Could not Get Data",Toast.LENGTH_LONG).show();

                }else{
                    Intent intent = new Intent(context, UpdateAppointmentActivity.class);
                    SessionClass.appPostion = getItem(position);
                    context.startActivity(intent);
                }

            }
        });

        return view;
    }


    static class ViewHolder {
        private TextView idTextView;
        private TextView dateTextView;
        private TextView detailsTextView;
        private Button updateAppointmentBtn;

    }


}
