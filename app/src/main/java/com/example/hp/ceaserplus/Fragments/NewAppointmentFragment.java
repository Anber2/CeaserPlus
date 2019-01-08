package com.example.hp.ceaserplus.Fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.AlbumAdapter;
import com.example.hp.ceaserplus.Others.Membership;
import com.example.hp.ceaserplus.Others.MembershipAdapter;
import com.example.hp.ceaserplus.Others.MembershipBenefit;
import com.example.hp.ceaserplus.Others.MembershipBenefitAdapter;
import com.example.hp.ceaserplus.Others.PhotoGallery;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by HP on 4/10/2017.
 */

public class NewAppointmentFragment extends Fragment {
    View view;
    private String url = "";
    private String url2 = "";
    private String url3 = "";
    private ProgressDialog progressBar;
    SQLHelper dbHelper;
    TextView startDateAppointmentText, endDateAppointmentText;
    EditText subjectEditText;
    Spinner membershipSpinner, membershipBenefitSpinner;
    Button submitButton;
    MembershipBenefitAdapter membershipBenefitAdapter;
    MembershipAdapter membershipAdapter;
    Calendar myCalendar;
    String membershidId;
    String membershipBenefitId;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_appointment_layout, container, false);
        initView();
        dbHelper = new SQLHelper(getActivity());
        dbHelper.open();
        progressBar = new ProgressDialog(getActivity());
        progressBar.setMessage(" Waiting ...");
        progressBar.setCancelable(false);

        String urlPostData = "?strLang=ar_KW&SecurityKey=WEBNAVIMSERVICE";

        url = urlAdressesClass.getMembershipTemplateURL+ urlPostData;

        String urlPostData2 = "?strLang=ar_KW&SecurityKey=WEBNAVIMSERVICE&CrmUserId="+SessionClass.userID+"";

        url2 = urlAdressesClass.getMembershipURL+ urlPostData2;

        syncData2 sync2 = new syncData2();
        sync2.execute();
        syncData sync = new syncData();
        sync.execute();

        myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        final DatePickerDialog.OnDateSetListener date2 = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel2();
            }

        };

        startDateAppointmentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDateAppointmentText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        membershipSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Membership membership = membershipAdapter.getItem(position);
                membershidId = membership.getMembershipId();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        membershipBenefitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                MembershipBenefit membershipBenefit = membershipBenefitAdapter.getItem(position);
                membershipBenefitId = membershipBenefit.getMembershipBenefitId();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkRule()) {

                    String urlPostData = "?" + "SecurityKey=WEBNAVIMSERVICE" + "&LanguageKey=ar_KW" +
                            "&UserId=7733c341-c28a-4353-955f-942f04316cf5" +
                            "&Membership=" + membershidId +
                            "&MembershipBenefit=" + membershipBenefitId +
                            "&Title=" + subjectEditText.getText().toString().trim() +
                            "&ApmntStartDate=" + startDateAppointmentText.getText().toString().trim() +
                            "&ApmntEndDate=" + endDateAppointmentText.getText().toString().trim() +
                            "&SubmittedDate=" + getDate();
                    url3 = "http://vimservices.mawaqaademo.com/api/Details/NewAppointment" + urlPostData;


                    sendData sendData = new sendData();
                    sendData.execute();


                } else {
                    Toast.makeText(getActivity(), "Calll NOO", Toast.LENGTH_SHORT).show();

                }
            }
        });


        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate() {

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time


        // String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        return formattedDate;
    }

    private boolean checkRule() {
        if (subjectEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getActivity(), "Please check the Subject", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (membershipBenefitId.equals(null) || membershipBenefitId.equals("")) {
            Toast.makeText(getActivity(), "Please check the membership Benefit", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (membershidId.equals(null) || membershidId.equals("")) {
            Toast.makeText(getActivity(), "Please check the membership ", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (startDateAppointmentText.equals(null) || startDateAppointmentText.equals("")) {
            Toast.makeText(getActivity(), "Please check Appointment Start Date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (endDateAppointmentText.equals(null) || endDateAppointmentText.equals("")) {
            Toast.makeText(getActivity(), "Please check the Appointment End Date ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {

        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDateAppointmentText.setText(sdf.format(myCalendar.getTime()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel2() {

        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endDateAppointmentText.setText(sdf.format(myCalendar.getTime()));
    }


    private void initView() {
        subjectEditText = (EditText) view.findViewById(R.id.appointment_subject_edt);
        startDateAppointmentText = (TextView) view.findViewById(R.id.start_date_appointment_txt);
        endDateAppointmentText = (TextView) view.findViewById(R.id.end_date_appointment_txt);
        membershipSpinner = (Spinner) view.findViewById(R.id.membership_spinner);
        membershipBenefitSpinner = (Spinner) view.findViewById(R.id.membership_benefit_spinner);
        submitButton = (Button) view.findViewById(R.id.new_appointment_submit);

    }


    private class syncData extends AsyncTask<Void, Integer, ArrayList<MembershipBenefit>> {


        @Override
        protected void onPreExecute() {
            //progressBar.show();
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(ArrayList<MembershipBenefit> membershipBenefitArrayList) {
            try {
                progressBar.dismiss();
                membershipBenefitAdapter = new MembershipBenefitAdapter(getActivity(), membershipBenefitArrayList);
                membershipBenefitSpinner.setAdapter(membershipBenefitAdapter);


            } catch (Exception ee) {


            }
            super.onPostExecute(membershipBenefitArrayList);
        }


        @Override
        protected ArrayList<MembershipBenefit> doInBackground(Void... params) {
            ArrayList<MembershipBenefit> membershipBenefitArrayList = new ArrayList<>();
            try {
                JSONArray data = syncJsonClass.getJSONDataArray(url);
                for (int i = 0; i < data.length(); i++) {
                    JSONObject membershipBenefitJsonObj = data.getJSONObject(i);
                    String membershipBenefitName = membershipBenefitJsonObj.getString("MembershipTempltName");
                    String membershipBenefitId = membershipBenefitJsonObj.getString("MembershipTempltId");
                    MembershipBenefit membershipBenefit = new MembershipBenefit(membershipBenefitName, membershipBenefitId);
                    membershipBenefitArrayList.add(membershipBenefit);

                }
                return membershipBenefitArrayList;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // callService();
            return membershipBenefitArrayList;
        }


    }


    private class syncData2 extends AsyncTask<Void, Integer, ArrayList<Membership>> {


        @Override
        protected void onPreExecute() {
            progressBar.show();
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(ArrayList<Membership> membershipArrayList) {
            try {
//progressBar.dismiss();
                membershipAdapter = new MembershipAdapter(getActivity(), membershipArrayList);
                membershipSpinner.setAdapter(membershipAdapter);
            } catch (Exception ee) {
            }
            super.onPostExecute(membershipArrayList);
        }


        @Override
        protected ArrayList<Membership> doInBackground(Void... params) {
            ArrayList<Membership> membershipArrayList = new ArrayList<>();
            try {
                JSONArray data = syncJsonClass.getJSONDataArray(url2);

                for (int i = 0; i < data.length(); i++) {
                    JSONObject membershipBenefitJsonObj = data.getJSONObject(i);
                    String membershipName = membershipBenefitJsonObj.getString("MembershipName");
                    String membershipId = membershipBenefitJsonObj.getString("MembershipId");
                    Membership membership = new Membership(membershipName, membershipId);
                    membershipArrayList.add(membership);

                }
                return membershipArrayList;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // callService();
            return membershipArrayList;
        }


    }


    private class sendData extends AsyncTask<Void, Integer, String> {


        @Override
        protected void onPreExecute() {
            progressBar.show();
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(String result) {
            try {
                progressBar.dismiss();
                if (result.equalsIgnoreCase("success")) {
                    Toast.makeText(getActivity(), "Added successfully ", Toast.LENGTH_SHORT).show();
                    FragmentManager fm = getActivity()
                            .getSupportFragmentManager();
                    fm.popBackStack();
                } else {
                    Toast.makeText(getActivity(), "Error Try again later  ", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception ee) {
            }
            super.onPostExecute(result);
        }


        @Override
        protected String doInBackground(Void... params) {
            JSONObject data = null;
            try {
                data = syncJsonClass.getJSONDataObject(url3);
                boolean success = data.getBoolean("Success");
                if (success) {
                    return "success";
                } else {
                    return "false";
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return "";

        }


    }

}
