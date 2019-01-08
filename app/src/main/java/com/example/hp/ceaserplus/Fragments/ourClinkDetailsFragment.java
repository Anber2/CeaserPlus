package com.example.hp.ceaserplus.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.Others.ourClincDataClass;
import com.example.hp.ceaserplus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ourClinkDetailsFragment extends Fragment implements OnMapReadyCallback {


    TextView titleTV,contactDetailTV,workHourTV,specialistTV,mobileTV,addressTV;
    ImageView headerIMG;
    private GoogleMap mMap;
    double latitude,longtitude;

    SupportMapFragment mapFragment;


    public ourClinkDetailsFragment() {
        // Required empty public constructor
    }


    public static ourClinkDetailsFragment newInstance(String param1, String param2) {
        ourClinkDetailsFragment fragment = new ourClinkDetailsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_our_clink_details, container, false);

        titleTV= (TextView) view.findViewById(R.id.clincTitleTV);
        contactDetailTV= (TextView) view.findViewById(R.id.clincContactDetailTV);
        workHourTV= (TextView) view.findViewById(R.id.clincWorkHrTV);
        specialistTV= (TextView) view.findViewById(R.id.clincSpecialstTV);
        mobileTV= (TextView) view.findViewById(R.id.clincPhoneTV);
        addressTV= (TextView) view.findViewById(R.id.clincAddressTV);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapVew);
        headerIMG= (ImageView) view.findViewById(R.id.clincHeadeIMG);


        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        titleTV.setText(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).getTitle());
        contactDetailTV.setText(Html.fromHtml(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).getContactDetails()));
        workHourTV.setText(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).getWorkingHour());
        specialistTV.setText(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).getSpecialistName());
        //mobileTV.setText(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).get());
        addressTV.setText(Html.fromHtml(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).getAddress()));
        Picasso.with(getContext()).load(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).getImgLink()).into(headerIMG);

        return view;
    }




    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        try {

            latitude=Double.parseDouble(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).getLatiTitud());
            longtitude=Double.parseDouble(OurClincFragment.ourClincDataList.get(SessionClass.ourClincListPos).getLongTitud());
            mMap = googleMap;

            googleMap.setMyLocationEnabled(true);

            LatLng sydney = new LatLng(latitude, longtitude);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

            // For zooming automatically to the location of the marker
            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        catch ( Exception xx)
        {
            xx.toString();
        }
    }
}
