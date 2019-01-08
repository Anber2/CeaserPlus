package com.example.hp.ceaserplus.ActivityClasses;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;

import com.example.hp.ceaserplus.Others.ImageAdapter;
import com.example.hp.ceaserplus.Others.PhotoGalleryDetails;
import com.example.hp.ceaserplus.R;

import java.util.ArrayList;

/**
 * Created by HP on 4/6/2017.
 */

public class ImagesGalleryActivity extends Activity {

    private Gallery imageGallery;
    private ImageAdapter imageAdapter;
    ArrayList<PhotoGalleryDetails> galleryDetailsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_gallery_layout);
        galleryDetailsArrayList=SessionClass.galleryDetailsArrayList;
        imageGallery= (Gallery) findViewById(R.id.image_gallery);
        imageAdapter=new ImageAdapter(getApplicationContext(),galleryDetailsArrayList);
        imageGallery.setAdapter(imageAdapter);

    }
}
