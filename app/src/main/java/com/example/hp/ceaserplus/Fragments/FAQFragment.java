package com.example.hp.ceaserplus.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.FAQListAdapter;
import com.example.hp.ceaserplus.Others.FAQ_DataClass;
import com.example.hp.ceaserplus.Others.OurClinc_ListAdapter;
import com.example.hp.ceaserplus.Others.ourClincDataClass;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;
import com.example.hp.ceaserplus.Others.SQLHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class FAQFragment extends Fragment  {

   public static ListView list;
     static FAQListAdapter faq_adapter;
    public static FAQ_DataClass faqDataClass;
    public static  ArrayList<FAQ_DataClass> faqList ;
    ProgressDialog progressBar;
    public static SQLHelper dbhelper;
    JSONObject faqDataObj;
    static Activity currentCOntx;
    public FAQFragment()
    {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_faq, container, false);

        dbhelper=new SQLHelper(FAQFragment.this.getContext());
        dbhelper.open();
        currentCOntx=this.getActivity();
        list=(ListView)v.findViewById(R.id.faqLV);



        progressBar = ProgressDialog.show(FAQFragment.this.getContext(),"", "Please Wait ...",true,false);

        getFAQData getDataAsync=new getFAQData();
        getDataAsync.execute();
        list.setAdapter(faq_adapter);



        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
            }
        });


        return v;


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



    }



    private  class getFAQData extends AsyncTask<Void, Integer, Void>
    {


        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                String url= urlAdressesClass.faqURL+"?SecurityKey=WEBNAVIMSERVICE&strLang=en_US";
                JSONArray faqDatajson= syncJsonClass.getJSONDataArray(url);
                if(faqDatajson !=null)
                {

                    faqList = new ArrayList<>();
                    for (int i = 0; i < faqDatajson.length(); i++)
                    {
                         faqDataObj=faqDatajson.getJSONObject(i);
                        String question=faqDataObj.getString("FAQ");
                        String answer=faqDataObj.getString("FAQAnswer");
                        faqDataClass=new FAQ_DataClass(question,answer);
                        faqList.add(faqDataClass);


                    }
                }
                else
                {

                    Cursor faqDataCurs=dbhelper.Select("select faq,faqanswer from faq",null);
                    faqList = new ArrayList<>();
                    if(faqDataCurs.moveToFirst())
                    {
                        do
                        {
                            String question=faqDataCurs.getString(0);
                            String answer=faqDataCurs.getString(1);
                            faqDataClass=new FAQ_DataClass(question,answer);
                            faqList.add(faqDataClass);
                        }
                        while (faqDataCurs.moveToNext());
                        }

                    faq_adapter = new FAQListAdapter(getActivity(), faqList);



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

            try
            {

                faq_adapter = new FAQListAdapter(getActivity(), faqList);
                list.setAdapter(faq_adapter);
                dbhelper.Delete("delete from faq");

                for(int i=0;i<faqList.size();i++)
                {
                    String query="insert into faq values('"+ faqList.get(i).getQuestion().toString().replace("'","")+"','"+faqList.get(i).getAnswer().toString().replace("'","")+"')";
                    String resss= dbhelper.Insert(query);
                }
                SessionClass.BackupDatabase();
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

    public static void search_faq(String searchKey)
    {

        try
        {
            Cursor faqDataCurs=dbhelper.Select("select faq,faqanswer from faq where faq like '%"+searchKey+"%' or faqanswer like '%"+searchKey+"%'",null);
            faqList = new ArrayList<>();
            if(faqDataCurs.moveToFirst())
            {
                do
                {
                    String question=faqDataCurs.getString(0);
                    String answer=faqDataCurs.getString(1);
                    faqDataClass=new FAQ_DataClass(question,answer);
                    faqList.add(faqDataClass);
                }
                while (faqDataCurs.moveToNext());
            }

            faq_adapter = new FAQListAdapter(currentCOntx, faqList);
            list.setAdapter(faq_adapter);


        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

}
