package com.example.hp.ceaserplus.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.ImagesGalleryActivity;
import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.AlbumDetailsAdapter;
import com.example.hp.ceaserplus.Others.PhotoGalleryDetails;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;
import com.example.hp.ceaserplus.Others.SQLHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP on 4/6/2017.
 */

public class PhotoGalleryDetailsFragment extends Fragment {
    private GridView albumDetailsGridView;
    private AlbumDetailsAdapter albumDetailsAdapter;
    private String url = "";
    private ProgressDialog progressBar;
    SQLHelper dbHelper;
    private ArrayList<PhotoGalleryDetails> photoGalleryDetailses;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_gallery_details, container, false);
        String albumId = SessionClass.albumId;
        albumDetailsGridView = (GridView) view.findViewById(R.id.album_details_gallery);

        dbHelper = new SQLHelper(getActivity());
        dbHelper.open();

        progressBar = new ProgressDialog(getActivity());

        progressBar.setMessage(" Please Wait...");
        progressBar.setCancelable(false);
        String urlPostData = "?LanguageKey=ar_KW&SecurityKey=WEBNAVIMSERVICE&AlbumId=" + albumId + "";

        url = urlAdressesClass.getPhotoGalleryURL + urlPostData;
        syncData sync = new syncData();
        sync.execute();




        return view;
    }


    private class syncData extends AsyncTask<Void, Integer, ArrayList<PhotoGalleryDetails>> {


        @Override
        protected void onPreExecute() {
            progressBar.show();
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(final ArrayList<PhotoGalleryDetails> galleryArrayList) {
            try {
                progressBar.dismiss();

                albumDetailsAdapter = new AlbumDetailsAdapter(getActivity(), galleryArrayList);
                albumDetailsGridView.setAdapter(albumDetailsAdapter);

                albumDetailsGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getActivity(), ImagesGalleryActivity.class);
                        photoGalleryDetailses = galleryArrayList;
                        SessionClass.galleryDetailsArrayList = photoGalleryDetailses;
                        startActivity(intent);

                    }
                });


            }
            catch (Exception xx)
            {
                progressBar.dismiss();
                Toast.makeText(getActivity(),"No Connection",Toast.LENGTH_SHORT).show();

            }
            super.onPostExecute(galleryArrayList);
        }


        @Override
        protected ArrayList<PhotoGalleryDetails> doInBackground(Void... params) {
            ArrayList<PhotoGalleryDetails> galleryArrayList = new ArrayList<>();
            try {
                JSONArray data = syncJsonClass.getJSONDataArray(url);


                for (int i = 0; i < data.length(); i++) {
                    JSONObject photoDetailsObj = data.getJSONObject(i);
                    String photoGalleryDetailsId = photoDetailsObj.getString("Id");
                    String photoGalleryDetailsTitle = photoDetailsObj.getString("Title");
                    String photoGalleryDetailsImage = photoDetailsObj.getString("Image");
                    String photoGalleryDetailsOrder = photoDetailsObj.getString("Order");
                    PhotoGalleryDetails photoGalleryDetails = new PhotoGalleryDetails(photoGalleryDetailsId, photoGalleryDetailsTitle,
                            photoGalleryDetailsImage, photoGalleryDetailsOrder);
                    galleryArrayList.add(photoGalleryDetails);
                }

                return galleryArrayList;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // callService();
            return galleryArrayList;
        }


    }


}
