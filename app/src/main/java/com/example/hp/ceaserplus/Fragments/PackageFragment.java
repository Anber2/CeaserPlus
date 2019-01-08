package com.example.hp.ceaserplus.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.MainActivity;
import com.example.hp.ceaserplus.ActivityClasses.PackagePayment;
import com.example.hp.ceaserplus.Others.PackageScreen_Adapter;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.Others.packageClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by HP on 4/6/2017.
 */

public class PackageFragment extends Fragment {


    ListView Packagelist;
    PackageScreen_Adapter Packagelistadapter;
    private static String url = urlAdressesClass.getMyPrescriptionsURL+"?SecurityKey=WEBNAVIMSERVICE&UserId="+SessionClass.userID+"";
    ArrayList<packageClass> packageClassArr;
    ProgressDialog progressBar;
    JSONArray packages;

    SQLHelper dbHelper;

    public PackageFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.package_layout, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Packagelist = (ListView) getActivity().findViewById(R.id.package_list);
        MainActivity.searchTXT.setVisibility(View.INVISIBLE);

        progressBar = ProgressDialog.show(PackageFragment.this.getContext(),"", "Please Wait ...",true,false);
        new GetPackage().execute();


        Packagelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub

                packageClass packageClass = Packagelistadapter.getItem(position);
                SessionClass.packageClassObj = packageClass;
                Intent intent = new Intent(getActivity(), PackagePayment.class);
                startActivity(intent);


            }
        });



    }

    private class GetPackage extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {


            try {

                packages = syncJsonClass.getJSONDataArray(url);

                if (packages != null) {



                    packageClass packageClassObj;
                    packageClassArr = new ArrayList<>();
                    for (int i = 0; i < packages.length(); i++) {

                        JSONObject jsonObject = packages.getJSONObject(i);

                        String Id = jsonObject.getString("Id");
                        String Packages = jsonObject.getString("Packages");
                        String ActiveFrom = jsonObject.getString("ActiveFrom");
                        String ActiveTo = jsonObject.getString("ActiveTo");
                        String MembershipFee = jsonObject.getString("MembershipFee");
                        String PackageMode = jsonObject.getString("PackageMode");

                        packageClassObj = new packageClass(Id, Packages, ActiveFrom, ActiveTo, MembershipFee, PackageMode);
                        packageClassArr.add(packageClassObj);

                    }
                }

            } catch (final JSONException e)

            {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(PackageFragment.this.getActivity(),
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
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        try {
            Packagelistadapter = new PackageScreen_Adapter(getActivity(), packageClassArr);
            Packagelist.setAdapter(Packagelistadapter);
        }
        catch (Exception xx)
        {
            Toast.makeText(getActivity(),"No Connection",Toast.LENGTH_SHORT).show();
        }
            progressBar.dismiss();


        }
    }
}
