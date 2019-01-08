package com.example.hp.ceaserplus.ActivityClasses;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;
import com.example.hp.ceaserplus.Others.SQLHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;

public class registerScreen extends AppCompatActivity {

    Button signupBTN;
    public static EditText genderCB;
    EditText firstNameTXT, lastNameTXT, surNameTXT, ageTXT, mobileNoTXT, emailTXT, confirmEmaTXT, passwordTXT, confirmPassTXT;

    TextView createAccountTV;

    SQLHelper dbHelper;
    String url = "";
    ProgressDialog progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        inistializaScreen();

        Log.e("SessionClass.language"," "+SessionClass.language);

        genderCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionClass.popupFlag = "gender";
                startActivity(new Intent(registerScreen.this, Popup_Class.class));
            }
        });

        signupBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkRule()) {
                    progressBar = ProgressDialog.show(registerScreen.this, "", "Please Wait ...", true, false);

                    String urlPostData = "?" + "FirstName=" + firstNameTXT.getText().toString().trim() + "" +
                            "&LastName=" + lastNameTXT.getText().toString().trim() + "" +
                            "&SurName=" + surNameTXT.getText().toString().trim() + "" +
                            "&Gender=" + genderCB.getText().toString().trim() + "" +
                            "&Age=" + ageTXT.getText().toString().trim() + "" +
                            "&Email=" + confirmEmaTXT.getText().toString().trim() + "" +
                            "&EntryDate=" + SessionClass.CurrentDate(false, true, false).toString() + "" +
                            "&Password=" + confirmPassTXT.getText().toString().trim() + "" +
                            "&MobileNo=" + mobileNoTXT.getText().toString().trim() + "" +
                            "&SecurityKey=WEBNAVIMSERVICE" +
                            "&LanguageKey=ar_KW";
                    url = urlAdressesClass.registerURL + urlPostData;

                    syncData sync = new syncData();
                    sync.execute();
                }


            }
        });
    }

    private boolean checkRule() {


        if (SessionClass.language.equalsIgnoreCase("1")) {

            if (firstNameTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "الرجاء التأكد من الأسم الأول", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (surNameTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "الرجاء التأكد من الأسم الأوسط", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (lastNameTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "الرجاء التأكد من الأسم الأول", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (genderCB.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "الرجاء التأكد من الجنس", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (ageTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "الرجاء التأكد من العمر", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (emailTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "الرجاء التأكد من الإميل", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (confirmEmaTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "يرجى تأكيد الإميل", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (passwordTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "الرجاء ادخال كلمة المرور", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (confirmPassTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "الرجاء تأكيد كلمة المرور", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!emailTXT.getText().toString().trim().equals(confirmEmaTXT.getText().toString().trim())) {
                Toast.makeText(getApplicationContext(), "الرجار تأكيد الإميل", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!passwordTXT.getText().toString().trim().equals(confirmPassTXT.getText().toString().trim())) {
                Toast.makeText(getApplicationContext(), "الرجاء تأكيد كلمة المرور", Toast.LENGTH_SHORT).show();
                return false;
            }


        } else {


            if (firstNameTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Please check the First Name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (surNameTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Please check the Sure Name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (lastNameTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Please check the Last Name", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (genderCB.getText().toString().equals("")) {
                Toast.makeText(getApplicationContext(), "Please check the Gender", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (ageTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Please check the Age", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (emailTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Please check the Email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (confirmEmaTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Please fill confirm Email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (passwordTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Please fill  Password", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (confirmPassTXT.getText().toString().trim().equals("")) {
                Toast.makeText(getApplicationContext(), "Please fill confirm Password", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!emailTXT.getText().toString().trim().equals(confirmEmaTXT.getText().toString().trim())) {
                Toast.makeText(getApplicationContext(), "Please cofirm your email", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!passwordTXT.getText().toString().trim().equals(confirmPassTXT.getText().toString().trim())) {
                Toast.makeText(getApplicationContext(), "Please cofirm your password", Toast.LENGTH_SHORT).show();
                return false;
            }

        }


        return true;
    }

    class syncData extends AsyncTask<Void, Integer, Void> {


        String res = "";
        String crmUser = "";

        @Override
        protected void onPreExecute() {
            // progressBar.show();
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Void result) {
            try {
                progressBar.dismiss();
                if (res.equals("true")) {
                    Toast.makeText(getApplicationContext(), "Registerd successfull ", Toast.LENGTH_SHORT).show();

                    finish();
                } else if (res.equals("registerd")) {
                    Toast.makeText(getApplicationContext(), "You are already registerd ", Toast.LENGTH_SHORT).show();
                }


            } catch (Exception ee) {


            }
            super.onPostExecute(result);
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {
                //JSONArray data= syncJsonClass.getJSONDataArray(url);
                JSONObject data = syncJsonClass.getJSONDataObject(url);
                if (data != null) {
                    Iterator<String> keys = data.keys();

                    while (keys.hasNext())

                    {
                        String key = keys.next().toString();


                        if (key.equals("Status")) {
                            if (data.getString(key).equals("Registered")) {
                                res = "true";
                                crmUser = data.getString("CrmUserId");
                                break;

                            } else if (data.getString(key).equals("You are already registered. Not yet Approved")) {

                                res = "registerd";
                                break;

                            }
                        }
                    }


                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }


    }


    private void inistializaScreen() {
        dbHelper = new SQLHelper(this);
        dbHelper.open();


        createAccountTV = (TextView) findViewById(R.id.reg_txt);
        signupBTN = (Button) findViewById(R.id.regsterBTN);
        firstNameTXT = (EditText) findViewById(R.id.firstNameTXT);
        surNameTXT = (EditText) findViewById(R.id.sureNameTXT);
        lastNameTXT = (EditText) findViewById(R.id.lastNameTXT);
        genderCB = (EditText) findViewById(R.id.genderCbox);
        ageTXT = (EditText) findViewById(R.id.ageCB);
        mobileNoTXT = (EditText) findViewById(R.id.mobileTXT);
        emailTXT = (EditText) findViewById(R.id.emailTXT);
        confirmEmaTXT = (EditText) findViewById(R.id.confirmEmailTXT);
        passwordTXT = (EditText) findViewById(R.id.passswordTXT);
        confirmPassTXT = (EditText) findViewById(R.id.confirmaPassTXT);

        Log.e("SessionClass.language"," "+SessionClass.language);


        if (SessionClass.language.equalsIgnoreCase("1")) {

            signupBTN.setText("تسجيل");
            firstNameTXT.setHint("الأسم الأول");
            surNameTXT.setHint("الأسم الأوسط");
            lastNameTXT.setHint("اسم العائلة");
            genderCB.setHint("الجنس");
            ageTXT.setHint("العمر");
            mobileNoTXT.setHint("رقم الجوال");
            emailTXT.setHint("الأميل");
            confirmEmaTXT.setHint("تأكيد الإميل");
            passwordTXT.setHint("كلمة المرور");
            confirmPassTXT.setHint("تأكيد كلمة المرور");
        }else

           signupBTN.setText("Register");
           firstNameTXT.setHint("First Name");
            surNameTXT.setHint("Sure Name");
            lastNameTXT.setHint("Last Name");
            genderCB.setHint("Gender");
            ageTXT.setHint("Age");
            mobileNoTXT.setHint("Mobile");
            emailTXT.setHint("Email");
            confirmEmaTXT.setHint("Confirm Email");
            passwordTXT.setHint("Password");
            confirmPassTXT.setHint("Confirm Password");

    }
}
