package com.example.hp.ceaserplus.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.FAQListAdapter;
import com.example.hp.ceaserplus.Others.FAQ_DataClass;
import com.example.hp.ceaserplus.Others.OurClinc_ListAdapter;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.Others.ourClincDataClass;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public  class OurClincFragment extends Fragment {

    public static  ListView list;
   public static ArrayList<ourClincDataClass> ourClincDataList;
    public static ourClincDataClass ourClincDataClass_;
    public static OurClinc_ListAdapter ourClinc_listAdapter_;
    ProgressDialog progressBar;
    public static SQLHelper dbhelper;
    private static Cursor searchCursor;
    static Activity currentCOntx;


    public OurClincFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        dbhelper=new SQLHelper(getActivity());
        dbhelper.open();

        currentCOntx=this.getActivity();
        return inflater.inflate(R.layout.fragment_our_clinc, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        list=(ListView)getActivity().findViewById(R.id.ourClincLV);
       progressBar = ProgressDialog.show(OurClincFragment.this.getContext(),"", "Please Wait ...",true,false);

        getOurClincData getData=new getOurClincData();
        getData.execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                SessionClass.ourClincListPos=position;
                Fragment fragment;
                fragment = new ourClinkDetailsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.main_container, fragment).commit();


            }
        });

        super.onActivityCreated(savedInstanceState);
    }

    private  class getOurClincData extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                String url= urlAdressesClass.getClinicsURL+"?SecurityKey=WEBNAVIMSERVICE&LanguageKey=en_US";
                JSONArray clincDatajson= syncJsonClass.getJSONDataArray(url);
                if(clincDatajson!=null)
                {
                    ourClincDataList = new ArrayList<>();
                    for (int i = 0; i < clincDatajson.length(); i++)
                    {
                        JSONObject faqDataObj=clincDatajson.getJSONObject(i);
                        String title=faqDataObj.getString("Name");
                        String img=faqDataObj.getString("Image");
                        String workHours=faqDataObj.getString("WorkHours");
                        String address=faqDataObj.getString("Address");
                        String contactDetails=faqDataObj.getString("ContactDetails");
                        String specialist=faqDataObj.getString("Specialist");
                        String latitude=faqDataObj.getString("Latitude");
                        String longitude=faqDataObj.getString("Longitude");
                        String place=faqDataObj.getString("ContactDetails");

                        ourClincDataClass_=new ourClincDataClass(img,title,workHours,address,specialist,contactDetails,latitude,longitude,place);
                        ourClincDataList.add(ourClincDataClass_);

                    }
                }
                else
                {
                    Cursor clinicDataCurs=dbhelper.Select("select name,image,work_hour,specialist,address,contactDetails,Latitude,Longitude from clinics",null);
                    ourClincDataList = new ArrayList<>();
                    if(clinicDataCurs.moveToFirst())
                    {
                        do
                        {
                            String title=clinicDataCurs.getString(0);
                            String img=clinicDataCurs.getString(1);
                            String workHours=clinicDataCurs.getString(2);
                            String address=clinicDataCurs.getString(4);
                            String contactDetails=clinicDataCurs.getString(5);
                            String specialist=clinicDataCurs.getString(3);
                            String latitude=clinicDataCurs.getString(6);
                            String longitude=clinicDataCurs.getString(7);
                            String place=clinicDataCurs.getString(5);

                            ourClincDataClass_=new ourClincDataClass(img,title,workHours,address,specialist,contactDetails,latitude,longitude,place);
                            ourClincDataList.add(ourClincDataClass_);
                        }
                        while (clinicDataCurs.moveToNext());
                    }

                    ourClinc_listAdapter_ = new OurClinc_ListAdapter(getActivity(), ourClincDataList);
                }
            }
            catch (Exception xx)
            {
                xx.toString();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            try {



                ourClinc_listAdapter_ = new OurClinc_ListAdapter(getActivity(), ourClincDataList);
                list.setAdapter(ourClinc_listAdapter_);

                dbhelper.Delete("delete from clinics");
                for(int i=0;i<ourClincDataList.size();i++)
                {
                    String query="insert into clinics values('"+ ourClincDataList.get(i).getTitle().toString().replace("'","")+"'," +
                            " '"+ourClincDataList.get(i).getImgLink().toString().replace("'","")+"'," +
                            "'"+ourClincDataList.get(i).getWorkingHour().toString().replace("'","")+"'," +
                            "'"+ourClincDataList.get(i).getSpecialistName().toString().replace("'","")+"'," +
                            "'"+ourClincDataList.get(i).getAddress().toString().replace("'","")+"'," +
                            "'"+ourClincDataList.get(i).getContactDetails().toString().replace("'","")+"'," +
                            "'"+ourClincDataList.get(i).getLatiTitud().toString().replace("'","")+"'," +
                            "'"+ourClincDataList.get(i).getLongTitud().toString().replace("'","")+"')";
                    String resss= dbhelper.Insert(query);

                }
                progressBar.dismiss();
                super.onPostExecute(aVoid);
            }
            catch (Exception xx)
            {
                progressBar.dismiss();
                Toast.makeText(getActivity(),"No Connection",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public static void search_Clinics(String searchKey)
    {

        try
        {
            Cursor clinicDataCurs=dbhelper.Select("select name,image,work_hour,specialist,address,contactDetails,Latitude,Longitude from clinics where name like '%"+searchKey+"%'",null);
            ourClincDataList = new ArrayList<>();
            ourClincDataList.clear();
            if(clinicDataCurs.moveToFirst())
            {
                do
                {
                    String title=clinicDataCurs.getString(0);
                    String img=clinicDataCurs.getString(1);
                    String workHours=clinicDataCurs.getString(2);
                    String address=clinicDataCurs.getString(4);
                    String contactDetails=clinicDataCurs.getString(5);
                    String specialist=clinicDataCurs.getString(3);
                    String latitude=clinicDataCurs.getString(6);
                    String longitude=clinicDataCurs.getString(7);
                    String place=clinicDataCurs.getString(5);

                    ourClincDataClass_=new ourClincDataClass(img,title,workHours,address,specialist,contactDetails,latitude,longitude,place);
                    ourClincDataList.add(ourClincDataClass_);
                }
                while (clinicDataCurs.moveToNext());
            }
            ourClinc_listAdapter_ = new OurClinc_ListAdapter(currentCOntx, ourClincDataList);
            list.setAdapter(ourClinc_listAdapter_);


        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

}
