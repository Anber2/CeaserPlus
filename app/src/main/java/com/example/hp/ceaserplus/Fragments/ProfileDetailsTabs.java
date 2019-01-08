package com.example.hp.ceaserplus.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.R;

/**
 * Created by HP on 4/6/2017.
 */

public class ProfileDetailsTabs extends Fragment {
    View view;
    private FragmentTabHost tabHost;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile_details, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        tabHost.setup(getActivity(), getActivity().getSupportFragmentManager(), android.R.id.tabcontent);

        tabHost.addTab(
                tabHost.newTabSpec("appointment").setIndicator("Appointment", null),
                AppointmentFragment.class, null);
        tabHost.getTabWidget().getChildAt(0).setBackgroundColor(Color.parseColor("#7B8347"));
        TextView tv = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        tv.setTextColor(Color.parseColor("#ffffff"));
        tv.setTextSize(12);

        tabHost.addTab(
                tabHost.newTabSpec("diet").setIndicator("Diet", null),
                DietFragment.class, null);
        tabHost.getTabWidget().getChildAt(1).setBackgroundColor(Color.parseColor("#84664E"));
        TextView tv2 = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        tv2.setTextColor(Color.parseColor("#ffffff"));
        tabHost.addTab(
                tabHost.newTabSpec("package").setIndicator("Package", null),
                PackageFragment.class, null);
        tabHost.getTabWidget().getChildAt(2).setBackgroundColor(Color.parseColor("#8E5355"));
        TextView tv3 = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
        tv3.setTextColor(Color.parseColor("#ffffff"));

        String tabPostion = SessionClass.tabPostion;
        tabHost.setCurrentTab(Integer.parseInt(tabPostion));


    }
}
