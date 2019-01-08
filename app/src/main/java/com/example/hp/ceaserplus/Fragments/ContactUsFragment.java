package com.example.hp.ceaserplus.Fragments;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.ActivityClasses.Login;
import com.example.hp.ceaserplus.ActivityClasses.MainActivity;
import com.example.hp.ceaserplus.ActivityClasses.SendFeedback;
import com.example.hp.ceaserplus.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by HP on 3/28/2017.
 */

public class ContactUsFragment extends Fragment implements View.OnClickListener, OnMapReadyCallback {

    TextView fb;

    private GoogleMap mMap;

    SupportMapFragment mapFragment;

    Button facebook, twitter, instgram, youtube;


    public ContactUsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        fb = (TextView) v.findViewById(R.id.sendyoufeedback);

        try {
            fb.setOnClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        facebook = (Button) v.findViewById(R.id.facebook);
        twitter = (Button) v.findViewById(R.id.twitter);
        instgram = (Button) v.findViewById(R.id.instgram);
        youtube = (Button) v.findViewById(R.id.youtube);


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.facebook.com/Vimkw_-227730574366542/"));
                getActivity().startActivity(i);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://twitter.com/vimkw_"));
                getActivity().startActivity(i);
            }
        });

        instgram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.instagram.com/vimkw_/"));
                getActivity().startActivity(i);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://www.youtube.com/"));
                getActivity().startActivity(i);
            }
        });
        return v;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);




    }


    @Override
    public void onClick(View v) {

        Log.e("onClick  ", "  text");

        Intent i = new Intent(ContactUsFragment.this.getActivity(), SendFeedback.class);
        startActivity(i);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        Log.e("onMapReady  ", "  text");


        mMap = googleMap;

        // For showing a move to my location button
        googleMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // For dropping a marker at a point on the Map
        LatLng vimkw = new LatLng(29.362703, 47.965167);
        googleMap.addMarker(new MarkerOptions().position(vimkw).icon(BitmapDescriptorFactory.fromResource(R.drawable.location2x)).title("vimkw").snippet("Phone:1800888 Email:info@vimkw.com"));

        // For zooming automatically to the location of the marker
        CameraPosition cameraPosition = new CameraPosition.Builder().target(vimkw).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


    }
}
