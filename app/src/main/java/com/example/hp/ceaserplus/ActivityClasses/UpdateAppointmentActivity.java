package com.example.hp.ceaserplus.ActivityClasses;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.ceaserplus.Others.AppointmentAdapter;
import com.example.hp.ceaserplus.Others.AppointmentsClass;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by HP on 4/11/2017.
 */

public class UpdateAppointmentActivity extends Activity {

    TextView startDateText;
    TextView endDtaeText;
    Calendar myCalendar;
    Button submitAppointmentButton;
    Context context;
    AppointmentsClass appointmentsClass;
    private ProgressDialog progressBar;


    String url="";
    String oldStartDate,oldEndDate,newStartDate,newEndDate;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_appointment_dialog_layout);
        int pos = SessionClass.appPostion;

        context=getApplicationContext();
        startDateText = (TextView) findViewById(R.id.app_start_date_txt);
        endDtaeText = (TextView) findViewById(R.id.app_end_date_txt);
        submitAppointmentButton= (Button) findViewById(R.id.submit_app_btn);
        progressBar = new ProgressDialog(this);
        progressBar.setMessage(" Waiting GPS...");
        progressBar.setCancelable(false);

        appointmentsClass= AppointmentAdapter.appointmentsClassList.get(pos);
        startDateText.setText(appointmentsClass.getAppointmentStartDate());
        endDtaeText.setText(appointmentsClass.getAppointmentEndDate());
        oldStartDate=appointmentsClass.getAppointmentStartDate();
        oldEndDate=appointmentsClass.getAppointmentEndDate();
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

        startDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateAppointmentActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        endDtaeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(UpdateAppointmentActivity.this, date2, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submitAppointmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkRule()){


                    String urlPostData = "?" + "SecurityKey=WEBNAVIMSERVICE" + "&LanguageKey=ar_KW" +
                            "&UserId=7733c341-c28a-4353-955f-942f04316cf5" +
                            "&AppointmentId=" + appointmentsClass.getAppointmentId() +
                            "&ApmntCurrentStartDate=" + appointmentsClass.getAppointmentStartDate() +
                            "&pmntCurrentEndDate=" + appointmentsClass.getAppointmentEndDate() +
                            "&ApmntChangeStartDate=" + startDateText.getText().toString().trim() +
                            "&ApmntChangeEndDate=" + endDtaeText.getText().toString().trim() +
                            "&SubmittedDate=" + getDate();
                    url = urlAdressesClass.updatAppoitmentURL+ urlPostData;


                    sendData sendData = new sendData();
                    sendData.execute();
                }

            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getDate() {

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = df.format(c.getTime());
        // formattedDate have current date/time



        // String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        return formattedDate;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel() {

        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        startDateText.setText(sdf.format(myCalendar.getTime()));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateLabel2() {

        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        endDtaeText.setText(sdf.format(myCalendar.getTime()));
    }


    private boolean checkRule() {

//        if (oldStartDate.equals(null) || oldStartDate.equals("")) {
//            Toast.makeText(context, "Please check the membership Benefit", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (oldEndDate.equals(null) || oldEndDate.equals("")) {
//            Toast.makeText(context, "Please check the membership ", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        if ( startDateText.getText().toString().trim().equals("")) {
            Toast.makeText(context, "Please check Appointment Start Date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ( endDtaeText.getText().toString().trim().equals("")) {
            Toast.makeText(context, "Please check the Appointment End Date ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
                if(result.equalsIgnoreCase("Success")){
                    Toast.makeText(getApplicationContext(),"Update Successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Error ",Toast.LENGTH_SHORT).show();
                }

            } catch (Exception ee) {


            }
            super.onPostExecute(result);
        }


        @Override
        protected String doInBackground(Void... params) {

            try {
                JSONObject data = syncJsonClass.getJSONDataObject(url);
                boolean success=data.getBoolean("Success");


                if(success){
                    return "success";
                }else {
                    return "false";
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // callService();
            return "";
        }


    }
}
