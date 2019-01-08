package com.example.hp.ceaserplus.Fragments;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.AlbumAdapter;
import com.example.hp.ceaserplus.Others.PhotoGallery;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;
import com.example.hp.ceaserplus.Others.SQLHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP on 4/5/2017.
 */

public class PhotoGalleryFragment extends Fragment {

    private GridView albumGridView;
    private AlbumAdapter albumAdapter;
    private String url = "";
    private ProgressDialog progressBar;
    SQLHelper dbHelper;
    private Fragment fragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        albumGridView = (GridView) view.findViewById(R.id.album_gallery);

        dbHelper = new SQLHelper(getActivity());
        dbHelper.open();

        progressBar = new ProgressDialog(getActivity());

        progressBar.setMessage(" Please Wait...");
        progressBar.setCancelable(false);
        String urlPostData = "?strLang=ar_KW&SecurityKey=WEBNAVIMSERVICE";

        url = urlAdressesClass.getPhotoAlbumsURL+ urlPostData;
        syncData sync = new syncData();
        sync.execute();


        return view;
    }


    private class syncData extends AsyncTask<Void, Integer, ArrayList<PhotoGallery>> {


        @Override
        protected void onPreExecute() {
            progressBar.show();
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute( ArrayList<PhotoGallery> galleryArrayList) {
            try {
                progressBar.dismiss();

                albumAdapter = new AlbumAdapter(getActivity(),galleryArrayList);
                albumGridView.setAdapter(albumAdapter);

                albumGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Toast.makeText(getActivity(),""+albumAdapter.getItem(position),Toast.LENGTH_SHORT).show();

                        fragment = new PhotoGalleryDetailsFragment();
                        SessionClass.albumId = String.valueOf(albumAdapter.getItem(position));
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.addToBackStack(null);
                        transaction.replace(R.id.main_container, fragment).commit();

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
        protected ArrayList<PhotoGallery> doInBackground(Void... params) {
            ArrayList<PhotoGallery> galleryArrayList=new ArrayList<>();
            try {
                JSONObject data = syncJsonClass.getJSONDataObject(url);

                if(data!=null)
                {
                    JSONArray albumsJSONAray = data.getJSONArray("PhotoGalleryList");


                    for (int i = 0; i < albumsJSONAray.length(); i++) {
                        JSONObject photoGalleryJsonObj = albumsJSONAray.getJSONObject(i);
                        String photoGalleryId = photoGalleryJsonObj.getString("Id");
                        String photoGalleryTitle = photoGalleryJsonObj.getString("AlbumTitle");
                        String photoGalleryPhoto = photoGalleryJsonObj.getString("AlbumCoverPhoto");
                        PhotoGallery photoGallery = new PhotoGallery(photoGalleryId, photoGalleryTitle, photoGalleryPhoto);
                        galleryArrayList.add(photoGallery);

                    }
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
