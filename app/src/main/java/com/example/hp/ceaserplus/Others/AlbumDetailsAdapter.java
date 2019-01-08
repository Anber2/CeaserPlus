package com.example.hp.ceaserplus.Others;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by HP on 4/6/2017.
 */

public class AlbumDetailsAdapter extends BaseAdapter {
    private Context context;
    ArrayList<PhotoGalleryDetails> galleryArrayList;


    public AlbumDetailsAdapter(Context context, ArrayList<PhotoGalleryDetails> galleryArrayList) {
        this.context = context;
        this.galleryArrayList = galleryArrayList;
    }



    @Override
    public int getCount() {
        return galleryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        PhotoGalleryDetails photoGalleryDetails = galleryArrayList.get(position);
        return photoGalleryDetails;
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
            view = inflater.inflate(R.layout.album_details_grid_item_layout, null);

        }
        ImageView albumDetailsImage = (ImageView) view.findViewById(R.id.album_details_image);
        TextView albumDetailsText = (TextView) view.findViewById(R.id.album_details_text);

        PhotoGalleryDetails photoGalleryDetails=galleryArrayList.get(position);
        Picasso.with(this.context).load(photoGalleryDetails.getPhotoGalleryDetailsImage()).into(albumDetailsImage);

        albumDetailsText.setText(photoGalleryDetails.getPhotoGalleryDetailsTitle());
        return view;
    }
}

