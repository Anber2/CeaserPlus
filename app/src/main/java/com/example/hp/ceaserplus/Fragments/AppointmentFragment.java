package com.example.hp.ceaserplus.Fragments;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.MainActivity;
import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.AppointmentAdapter;
import com.example.hp.ceaserplus.Others.AppointmentsClass;
import com.example.hp.ceaserplus.Others.SQLHelper;
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

public class AppointmentFragment extends Fragment {
    View view;
    private ListView appointmentLisView;
    private Button newAppointmentButton;
    private AppointmentAdapter appointmentAdapter;

    private String url = "";
    private ProgressDialog progressBar;
    SQLHelper dbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.appointment_layout, container, false);
        initView();

        dbHelper = new SQLHelper(getActivity());
        dbHelper.open();
        MainActivity.searchTXT.setVisibility(View.INVISIBLE);

        progressBar = new ProgressDialog(getActivity());

        progressBar.setMessage(" Please Wait...");
        progressBar.setCancelable(false);


        String urlPostData = "?" + "LanguageKey=ar_KW" +
                "&SecurityKey=WEBNAVIMSERVICE" + "&UserId=7733c341-c28a-4353-955f-942f04316cf5";
        url = urlAdressesClass.getAppoitmentURL + urlPostData;
        syncData sync = new syncData();
        sync.execute();


        newAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.main_container, new NewAppointmentFragment()).commit();

            }
        });


        return view;
    }

    private void initView() {
        appointmentLisView = (ListView) view.findViewById(R.id.appointment_lst);
        newAppointmentButton = (Button) view.findViewById(R.id.new_app_btn);
    }

    private class syncData extends AsyncTask<Void, Integer, ArrayList<AppointmentsClass>> {


        @Override
        protected void onPreExecute() {
            progressBar.show();
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(ArrayList<AppointmentsClass> appointmentsClassArrayList) {
            try {
                progressBar.dismiss();
                ArrayList<AppointmentsClass> appointmentsClassArrayList1 = new ArrayList<>();
                AppointmentsClass appointmentsClass;
                if (appointmentsClassArrayList == null || appointmentsClassArrayList.isEmpty()) {

                    Toast.makeText(getActivity(), "No connection could not get Data", Toast.LENGTH_LONG).show();
                    String sql = " select Appointment_Id,AppointmentStartDate,AppointmentEndDate,AppointmentDetails,Status,Success from appoitment";
                    Cursor cursor = dbHelper.Select(sql, null);
                    if (cursor.moveToFirst()) {
                        do {
                            String appointmentId = cursor.getString(0);
                            String appointmentStartDate = cursor.getString(1);
                            String appointmentEndDate = cursor.getString(2);
                            String appointmentDetails = cursor.getString(3);
                            String appointmentStatus = cursor.getString(4);
                            boolean appointmentSuccess = Boolean.parseBoolean(cursor.getString(5));
                            appointmentsClass = new AppointmentsClass(appointmentId, appointmentStartDate, appointmentEndDate,
                                    appointmentDetails, appointmentStatus, appointmentSuccess);
                            appointmentsClassArrayList1.add(appointmentsClass);
                        }
                        while (cursor.moveToNext());

                        appointmentAdapter = new AppointmentAdapter(getActivity(), appointmentsClassArrayList1, "offline");
                        appointmentLisView.setAdapter(appointmentAdapter);

                    }

                } else {
                    appointmentAdapter = new AppointmentAdapter(getActivity(), appointmentsClassArrayList, "online");
                    appointmentLisView.setAdapter(appointmentAdapter);

                }

            } catch (Exception ee) {
                String sss = "";
                System.out.print("" + ee);

            }
            super.onPostExecute(appointmentsClassArrayList);
        }


        @Override
        protected ArrayList<AppointmentsClass> doInBackground(Void... params) {
            ArrayList<AppointmentsClass> appointmentsClassArrayList = new ArrayList<>();
            AppointmentsClass appointmentsClass;
            try {
                JSONArray data = syncJsonClass.getJSONDataArray(url);

                if (data == null) {
                    return null;
                }


                for (int i = 0; i < data.length(); i++) {
                    JSONObject appointmentJsonObj = data.getJSONObject(i);
                    String appointmentId = appointmentJsonObj.getString("AppointmentId");
                    String appointmentStartDate = appointmentJsonObj.getString("AppointmentStartDate");
                    String appointmentEndDate = appointmentJsonObj.getString("AppointmentEndDate");
                    String appointmentDetails = appointmentJsonObj.getString("AppointmentDetails");
                    String appointmentStatus = appointmentJsonObj.getString("Status");
                    boolean appointmentSuccess = appointmentJsonObj.getBoolean("Success");
                    appointmentsClass = new AppointmentsClass(appointmentId, appointmentStartDate, appointmentEndDate,
                            appointmentDetails, appointmentStatus, appointmentSuccess);
                    appointmentsClassArrayList.add(appointmentsClass);

                    String qury = "insert into appoitment values('" + appointmentId + "','" + appointmentStartDate + "'" +
                            ",'" + appointmentEndDate + "','" + appointmentDetails + "','" + appointmentStatus + "','" + appointmentSuccess + "')";
                    dbHelper.Insert(qury);

                }

                return appointmentsClassArrayList;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // callService();
            return null;
        }


    }


}
