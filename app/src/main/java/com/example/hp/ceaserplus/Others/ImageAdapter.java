package com.example.hp.ceaserplus.Others;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 4/6/2017.
 */

public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<PhotoGalleryDetails> galleryDetailsArrayList;

    public ImageAdapter(Context context , ArrayList<PhotoGalleryDetails> galleryDetailsArrayList) {
        this.context = context;
        this.galleryDetailsArrayList=galleryDetailsArrayList;
    }



    @Override
    public int getCount() {
        return galleryDetailsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        PhotoGalleryDetails photoGalleryDetails=galleryDetailsArrayList.get(position);
        return photoGalleryDetails;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        PhotoGalleryDetails photoGalleryDetails=galleryDetailsArrayList.get(position);
        Picasso.with(this.context).load(photoGalleryDetails.getPhotoGalleryDetailsImage()).into(imageView);
       // imageView.setImageResource(Integer.parseInt(imageId));
        imageView.setLayoutParams(new Gallery.LayoutParams(700, 800));

        return imageView;
    }
}
