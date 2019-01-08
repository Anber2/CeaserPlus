package com.example.hp.ceaserplus.ActivityClasses;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;
import com.example.hp.ceaserplus.Others.SQLHelper;

import org.json.JSONObject;

/**
 * Created by HP on 3/29/2017.
 */

public class Login extends AppCompatActivity {

    private String username, password;

    private EditText emailEditText;
    private EditText passEditText;
    Button loginBTN;
    TextView regesterTV;
    ProgressDialog progressBar;
    private CheckBox saveLoginCheckBox;
    Animation animShke;


    SQLHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        inistializaScreen();

        String sql = " SELECT remember,crmuserid,password from users ";
        Cursor cursor = dbhelper.Select(sql, null);
        if (cursor.moveToFirst()) {

            SessionClass.userID = cursor.getString(1).toString().trim();
            SessionClass.oldPAssword = cursor.getString(2).toString().trim();
            if (cursor.getString(0).toString().trim().equalsIgnoreCase("1")) {
                startActivity(new Intent(Login.this, MainActivity.class));
                finish();
            }

        }

        regesterTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, registerScreen.class));
            }
        });

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!checkRule())
                    return;
                progressBar = ProgressDialog.show(Login.this, "", "Please Wait ...", true, false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String url = "";


                            String urlPostData = "?" + "UserName=" + emailEditText.getText().toString().trim() + "" +
                                    "&Password=" + passEditText.getText().toString().trim() + "" +
                                    "&SecurityKey=WEBNAVIMSERVICE" +
                                    "&LanguageKey=ar_KW";
                            url = urlAdressesClass.LoginURL + urlPostData;
                            JSONObject data = syncJsonClass.getJSONDataObject(url);
                            if (data != null) {
                                if (data.getString("IsSuccess").equals("false")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(getApplicationContext(), "Wrong email or password ", Toast.LENGTH_SHORT).show();
                                            progressBar.dismiss();
                                        }
                                    });
                                } else {
                                    String crmID = data.getString("CRMUserId");
                                    SessionClass.userID = crmID;
                                    String FirstName = data.getString("FirstName");
                                    String LastName = data.getString("LastName");
                                    String Gender = data.getString("Gender");
                                    String Age = data.getString("Age");
                                    String MobileNo = data.getString("MobileNo");
                                    String remember = "";
                                    if (saveLoginCheckBox.isChecked()) {
                                        remember = "1";
                                    }

                                    dbhelper.Delete("delete from users");
                                    String insertQuery = "insert into users values('" + FirstName + "','" + LastName + "'," +
                                            "'" + emailEditText.getText().toString() + "','" + Gender + "','" + Age + "'," +
                                            "'" + MobileNo + "','" + passEditText.getText().toString().trim() + "','" + crmID + "','" + remember + "')";

                                    dbhelper.Insert(insertQuery);
                                    //SessionClass.BackupDatabase();
                                    startActivity(new Intent(Login.this, MainActivity.class));
                                    finish();

                                }

                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "No Connection try later", Toast.LENGTH_SHORT).show();
                                        progressBar.dismiss();
                                    }
                                });
                            }
                            progressBar.dismiss();
                        } catch (Exception xxx) {
                            progressBar.dismiss();
                            xxx.toString();
                        }
                    }
                }).start();


            }
        });


    }


    private void inistializaScreen() {
        dbhelper = new SQLHelper(this);
        dbhelper.open();

        emailEditText = (EditText) findViewById(R.id.username_edit);
        passEditText = (EditText) findViewById(R.id.password_edit);
        saveLoginCheckBox = (CheckBox) findViewById(R.id.checkBox);
        regesterTV = (TextView) findViewById(R.id.createAccountTV);
        loginBTN = (Button) findViewById(R.id.loginBTN);
        animShke = AnimationUtils.loadAnimation(this, R.anim.shake);
        TextView pass_forgot = (TextView) findViewById(R.id.pass_forgot);



        if (SessionClass.language.equalsIgnoreCase("1")) {
            emailEditText.setHint("إميل");
            passEditText.setHint("كلمة المرور");
            saveLoginCheckBox.setHint("تذكرني");
            regesterTV.setHint("أنشئ حساب");
            loginBTN.setHint("تسجيل الدخول");
            pass_forgot.setHint("نسيت كلمة المرور");

        } else {
            emailEditText.setHint("Email");
            passEditText.setHint("Password");
            saveLoginCheckBox.setHint("Remember Me");
            regesterTV.setHint("Creat an Account");
            loginBTN.setHint("Login");
            pass_forgot.setText("Forgot your password");
        }


    }

    private boolean checkRule() {
        if (emailEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the Email", Toast.LENGTH_SHORT).show();
            emailEditText.startAnimation(animShke);
            return false;
        }

        if (passEditText.getText().toString().trim().equals("")) {
            Toast.makeText(getApplicationContext(), "Please check the Password", Toast.LENGTH_SHORT).show();
            passEditText.startAnimation(animShke);
            return false;
        }
        return true;
    }


}
