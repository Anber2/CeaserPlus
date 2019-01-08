package com.example.hp.ceaserplus.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.NewsDetails;
import com.example.hp.ceaserplus.ActivityClasses.SendFeedback;
import com.example.hp.ceaserplus.Others.NewsScreen_Adapter;
import com.example.hp.ceaserplus.Others.OurClinc_ListAdapter;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.Others.newsClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.ourClincDataClass;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP on 3/28/2017.
 */

public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener {
    static ListView list;
    static NewsScreen_Adapter adapter;

    static ArrayList<newsClass> newsClassArr;
    ProgressDialog progressBar;
    JSONArray news;

    static  SQLHelper dbHelper;
    static Activity currentCOntx;


    public NewsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        dbHelper = new SQLHelper(getActivity());
        dbHelper.open();

        currentCOntx=this.getActivity();
        return inflater.inflate(R.layout.fragment_news, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        list = (ListView) getActivity().findViewById(R.id.news_list);

        progressBar = ProgressDialog.show(NewsFragment.this.getActivity(),"", "Please Wait ...",true,false);
        new GetNews().execute();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub

                newsClass newsClass = adapter.getItem(position);
                SessionClass.newsClassobj = newsClass;
                Intent intent = new Intent(getActivity(), NewsDetails.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

    private class GetNews extends AsyncTask<Void, Integer, Void> {
        String res = "";

        @Override
        protected Void doInBackground(Void... params) {


            try {

                String url= urlAdressesClass.getNewsURL+"?SecurityKey=WEBNAVIMSERVICE&LanguageKey=en_US";
                news = syncJsonClass.getJSONDataArray(url);

                if (news == null) {
                    return null;
                }

                newsClass newsClassObj;
                newsClassArr = new ArrayList<>();

                dbHelper.Delete("delete from news");
                for (int i = 0; i < news.length(); i++) {

                    JSONObject jsonObject = news.getJSONObject(i);

                    String title = jsonObject.getString("Title");
                    String date = jsonObject.getString("Date");
                    String teaser = jsonObject.getString("Teaser");
                    String description = jsonObject.getString("Description");
                    String Thumbnail = jsonObject.getString("Thumbnail");
                    String MoreDetailsLink = jsonObject.getString("MoreDetailsLink");

                    newsClassObj = new newsClass(title, date, teaser, description, Thumbnail, MoreDetailsLink, "");
                    newsClassArr.add(newsClassObj);

                    String qury = "insert into news(ID,Title, news_date, news_desc, thumbnail, more_details, teaser) values('" +
                            SessionClass.Genarate_ID() + "','" + title + "','" + date + "','" + description + "','" + Thumbnail + "','"
                            + MoreDetailsLink + "','" + teaser + "')";

                    dbHelper.Insert(qury);

                }

            } catch (final JSONException e)

            {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NewsFragment.this.getActivity(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            } catch (
                    IOException e)

            {
                e.printStackTrace();
            }


            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);


            try {
                if (newsClassArr == null || newsClassArr.isEmpty()) {

                    newsClass newsClassOBJ;
                    ArrayList<newsClass> newsClassArrayList1 =new ArrayList<>();
                    Toast.makeText(getActivity(), "No connection could not get Data", Toast.LENGTH_LONG).show();
                    String sql = " select Title, news_date, news_desc, thumbnail, more_details, teaser from news";
                    Cursor cursor = dbHelper.Select(sql, null);
                    if (cursor.moveToFirst()) {

                        do{

                            //JSONObject jsonObject = data.getJSONObject(i);
                            String title = cursor.getString(0);
                            String date = cursor.getString(1);
                            String description = cursor.getString(2);
                            String Thumbnail = cursor.getString(3);
                            String MoreDetailsLink = cursor.getString(4);
                            String teaser = cursor.getString(5);

                            newsClassOBJ = new newsClass(title, date, teaser, description, Thumbnail, MoreDetailsLink, "");

                            newsClassArrayList1.add(newsClassOBJ);

                        }while (cursor.moveToNext());

                        adapter = new NewsScreen_Adapter(getActivity(), newsClassArrayList1);
                        list.setAdapter(adapter);

                    }

                } else {

                    adapter = new NewsScreen_Adapter(getActivity(), newsClassArr);
                    list.setAdapter(adapter);

                }

            } catch (Exception ee) {
                String sss = "";
                System.out.print("" + ee);

            }
            progressBar.dismiss();
        }





    }

   public static void search_news(String searchKey)
{

    try
    {
        String sql = " select Title, news_date, news_desc, thumbnail, more_details, teaser from news where title like '%"+searchKey+"%' ";
        Cursor cursor = dbHelper.Select(sql, null);
        newsClass newsClassOBJ;

        newsClassArr=new ArrayList<>();
        if (cursor.moveToFirst()) {

            do{

                String title = cursor.getString(0);
                String date = cursor.getString(1);
                String description = cursor.getString(2);
                String Thumbnail = cursor.getString(3);
                String MoreDetailsLink = cursor.getString(4);
                String teaser = cursor.getString(5);

                newsClassOBJ = new newsClass(title, date, teaser, description, Thumbnail, MoreDetailsLink, "");

                newsClassArr.add(newsClassOBJ);

               }
                while (cursor.moveToNext());


        }

        adapter = new NewsScreen_Adapter(currentCOntx, newsClassArr);
        list.setAdapter(adapter);


    }
    catch (Exception e)
    {
        e.getMessage();
    }
}

}
