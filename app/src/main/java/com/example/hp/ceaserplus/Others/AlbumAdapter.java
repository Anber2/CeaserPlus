package com.example.hp.ceaserplus.Others;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by HP on 4/5/2017.
 */

public class AlbumAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<PhotoGallery> galleryArrayList;


    public AlbumAdapter(Context context, ArrayList<PhotoGallery> galleryArrayList) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
    }


    @Override
    public int getCount() {
        return galleryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        PhotoGallery photoGallery = galleryArrayList.get(position);
        return photoGallery.getPhotoGalleryId();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.album_grid_item_layout, null);

        }
        ImageView albumImage = (ImageView) view.findViewById(R.id.album_image);
        TextView albumText = (TextView) view.findViewById(R.id.album_text);

        PhotoGallery photoGallery = galleryArrayList.get(position);

        Picasso.with(this.context).load(photoGallery.getPhotoGalleryPhoto()).into(albumImage);
        albumText.setText(photoGallery.getPhotoGalleryTitle());
        return view;
    }
}
