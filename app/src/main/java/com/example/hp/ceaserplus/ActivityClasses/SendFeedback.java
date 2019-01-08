package com.example.hp.ceaserplus.ActivityClasses;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by HP on 4/4/2017.
 */


public class SendFeedback extends Activity{

    EditText firstNameTXT,lastNameTXT,codeTXT,mobileTXT,addres1TXT,address2TXT,emailTXT,commentTXT;
    public static EditText countryTXT;
    Button submitBTN;
    String url="";
    ProgressDialog progressBar;
    String res="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sendfeedback);


        try {
            initialScreen();
        } catch (Exception e) {
            e.printStackTrace();
        }

        submitBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
             if(checkRule())
             {
                 progressBar = ProgressDialog.show(SendFeedback.this,"", "Please Wait ...",true,false);

                String addresses=addres1TXT.getText().toString().trim()+""+address2TXT.getText().toString().trim();
                 String mobileNo=codeTXT.getText().toString().trim()+mobileTXT.getText().toString().trim();

                 String urlPostData="?"+"FirstName="+firstNameTXT.getText().toString().trim()+"" +
                         "&LastName="+lastNameTXT.getText().toString().trim()+"" +
                         "&Email="+emailTXT.getText().toString().trim()+"" +
                         "&Telephone="+mobileNo+"" +
                         "&Address="+addresses+"" +
                         "&Email="+emailTXT.getText().toString().trim()+"" +
                         "&Comment="+SessionClass.CurrentDate(false,true,false).toString()+"" +
                         "&SecurityKey=WEBNAVIMSERVICE" +
                         "&LanguageKey=ar_KW";
                 url= urlAdressesClass.sendFeedURL+urlPostData;

                 syncData sync = new syncData();
                 sync.execute();
             }
            }
        });

        countryTXT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                SessionClass.popupFlag="country";
                startActivity(new Intent(SendFeedback.this,Popup_Class.class));
            }
        });


    }



    private  void  initialScreen()
    {


        firstNameTXT= (EditText) findViewById(R.id.feedFirstNamTXT);
        lastNameTXT= (EditText) findViewById(R.id.feedLastNameTXT);
        countryTXT= (EditText) findViewById(R.id.feedCountrySpin);
        codeTXT= (EditText) findViewById(R.id.feedCodeNumTXT);
        mobileTXT= (EditText) findViewById(R.id.feedMobileTXT);
        addres1TXT= (EditText) findViewById(R.id.feedAddressTXT);
        address2TXT= (EditText) findViewById(R.id.feedAddress2TXT);
        emailTXT= (EditText) findViewById(R.id.feedEmailTXT);
        commentTXT= (EditText) findViewById(R.id.feedCommentTXT);
        submitBTN= (Button) findViewById(R.id.feedSubbmitBTN);

    }

    class syncData extends AsyncTask<Void, Integer, Void>
    {
       String res="";
        @Override
        protected Void doInBackground(Void... params)
        {

            try {

                String data= syncJsonClass.getJSONDataAsString(url);
                if(data !=null)
                {
                    res=data.trim();
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            try {

               if(res.equalsIgnoreCase("Success"))
               {
                   Toast.makeText(getApplicationContext(),"Submit Successfull, Thank you",Toast.LENGTH_SHORT).show();
                   finish();
               }
               else
               {
                   Toast.makeText(getApplicationContext(),"Submit error , please try later",Toast.LENGTH_SHORT).show();
               }
                progressBar.dismiss();
                super.onPostExecute(aVoid);
            }
            catch (Exception xx)
            {
                progressBar.dismiss();
                Toast.makeText(getApplicationContext(),"Some error , please try later",Toast.LENGTH_SHORT).show();

            }
        }
    }
    private  boolean checkRule()
    {
        if(firstNameTXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the First Name",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(lastNameTXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the Last Name",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(countryTXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the Country",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(codeTXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the Code",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(mobileTXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the Mobile",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(addres1TXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the Address",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(address2TXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the Address",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(emailTXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the Email",Toast.LENGTH_SHORT).show();
            return false;
        }
        if(commentTXT.getText().toString().trim().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please check the Comment",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
