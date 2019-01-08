package com.example.hp.ceaserplus.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.ceaserplus.ActivityClasses.Popup_Class;
import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StreamCorruptedException;

public class EditProfileFragment extends Fragment {

    Button saveBTN, resetBTN;
    public static EditText genderCB;
    EditText firstNameTXT, lastNameTXT, ageTXT, mobileNoTXT, emailTXT, passwordTXT, confirmPassTXT, oldPAssTXT;
    SQLHelper dbHelper;
    String urlEdit = "";
    ProgressDialog progressBar;
    String res = "";
    JSONObject data;
    JSONObject dataObj;
    Cursor userIDCursor;
    String urlGet = "";


    public EditProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        try {
            dbHelper = new SQLHelper(getActivity());
            dbHelper.open();

            firstNameTXT = (EditText) view.findViewById(R.id.editFirstNameTXT);
            lastNameTXT = (EditText) view.findViewById(R.id.editLastNAmeTXT);
            emailTXT = (EditText) view.findViewById(R.id.editEmailTXT);
            //confirmEmaTXT = (EditText) view.findViewById(R.id.editConfirmEmailTXT);
            genderCB = (EditText) view.findViewById(R.id.editGenderTXT);
            ageTXT = (EditText) view.findViewById(R.id.editAgeCB);
            mobileNoTXT = (EditText) view.findViewById(R.id.editMobileTXT);
            passwordTXT = (EditText) view.findViewById(R.id.editPassswordTXT);
            oldPAssTXT = (EditText) view.findViewById(R.id.oldPassswordTXT);
            confirmPassTXT = (EditText) view.findViewById(R.id.editConfirmaPassTXT);
            resetBTN = (Button) view.findViewById(R.id.resetEditProfBTN);
            saveBTN = (Button) view.findViewById(R.id.saveEditProfBTN);


            genderCB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SessionClass.popupFlag = "gender_edit";
                    startActivity(new Intent(EditProfileFragment.this.getActivity(), Popup_Class.class));
                }
            });

            resetBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firstNameTXT.setText("");
                    lastNameTXT.setText("");
                    emailTXT.setText("");
                    genderCB.setText("");
                    SessionClass.gender = "";
                    ageTXT.setText("");
                    mobileNoTXT.setText("");
                    passwordTXT.setText("");
                    confirmPassTXT.setText("");
                    oldPAssTXT.setText("");


                }
            });

            saveBTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkRule()) {
                        progressBar = ProgressDialog.show(EditProfileFragment.this.getActivity(), "", "Please Wait ...", true, false);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                String urlPostData = "?CrmUserId=" + SessionClass.userID.trim() + "" +
                                        "&FirstName=" + firstNameTXT.getText().toString().trim() + "" +
                                        "&LastName=" + lastNameTXT.getText().toString().trim() + "" +
                                        "&Gender=" + genderCB.getText().toString().trim() + "" +
                                        "&BirthDate=" + ageTXT.getText().toString().trim() + "" +
                                        "&MobileNo=" + mobileNoTXT.getText().toString().trim() + "" +
                                        "&Password=" + confirmPassTXT.getText().toString().trim() + "" +
                                        "&SecurityKey=WEBNAVIMSERVICE";
                                urlEdit = urlAdressesClass.editProfileURL + urlPostData;
                                JSONObject data = null;
                                try {
                                    data = syncJsonClass.getJSONDataObject(urlEdit);
                                    if (data != null) {
                                        String resss = data.get("ReturnMsg").toString();
                                        if (resss.equalsIgnoreCase("Save Successfully")) {
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(EditProfileFragment.this.getActivity(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                                                    dbHelper.Delete("delete from users");
                                                    String insertQuery = "insert into users values('" + firstNameTXT.getText().toString() + "','" + lastNameTXT.getText().toString() + "'," +
                                                            "'" + emailTXT.getText().toString() + "','" + genderCB.getText().toString() + "','" + ageTXT.getText().toString() + "'," +
                                                            "'" + mobileNoTXT.getText().toString() + "','" + confirmPassTXT.getText().toString().trim() + "','" + SessionClass.userID + "','0')";

                                                    String xx = dbHelper.Insert(insertQuery);
                                                    getActivity().onBackPressed();
                                                }
                                            });
                                        } else {
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(EditProfileFragment.this.getActivity(), "Please Try Later", Toast.LENGTH_SHORT).show();

                                                }
                                            });
                                        }
                                    } else {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(EditProfileFragment.this.getActivity(), "No Connection", Toast.LENGTH_SHORT).show();

                                            }
                                        });

                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                progressBar.dismiss();

                            }
                        }).start();


                    }
                }
            });


            String urlPostData = "?CrmUserId=" + SessionClass.userID.trim() + "" +
                    "&LanguageKey=ar_KW" +
                    "&SecurityKey=WEBNAVIMSERVICE";
            urlGet = urlAdressesClass.getProfileURL + urlPostData;
            progressBar = ProgressDialog.show(EditProfileFragment.this.getActivity(), "", "Please Wait ...", true, false);

            syncData sync = new syncData();
            sync.execute();

        } catch (Exception xx) {
            xx.toString();
        }

        return view;
    }


    class syncData extends AsyncTask<Void, Integer, Void> {
        JSONObject dataObject;

        @Override
        protected Void doInBackground(Void... params) {

            try {

                JSONObject data = syncJsonClass.getJSONDataObject(urlGet);

                if (data != null) {
                    dataObject = data;

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
                if (dataObject != null) {

                    firstNameTXT.setText(dataObject.getString("FirstName"));
                    lastNameTXT.setText(dataObject.getString("LastName"));
                    emailTXT.setText(dataObject.getString("Email"));
                    genderCB.setText(dataObject.getString("Gender"));
                    SessionClass.gender = dataObject.getString("Gender");
                    ageTXT.setText(dataObject.getString("Age"));
                    mobileNoTXT.setText(dataObject.getString("MobileNo"));


                } else {
                    Toast.makeText(EditProfileFragment.this.getActivity(), "No Connection", Toast.LENGTH_SHORT).show();

                }
                progressBar.dismiss();
                super.onPostExecute(aVoid);
            } catch (Exception xx) {
                progressBar.dismiss();
                Toast.makeText(getActivity(), "No Connection", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private boolean checkRule() {
        if (firstNameTXT.getText().toString().trim().equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check the First Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (lastNameTXT.getText().toString().trim().equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check the Last Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (emailTXT.getText().toString().trim().equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check the Emai", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (confirmEmaTXT.getText().toString().trim().equals("")) {
//            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check Confirm Email", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        if (genderCB.getText().toString().trim().equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check the Gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (ageTXT.getText().toString().trim().equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check the age", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mobileNoTXT.getText().toString().trim().equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check the mobile", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (passwordTXT.getText().toString().trim().equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check the password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (confirmPassTXT.getText().toString().trim().equals("")) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please check confirm password", Toast.LENGTH_SHORT).show();
            return false;
        }
//        if (emailTXT.getText().toString().trim().equals(confirmEmaTXT.getText().toString().trim())) {
//            Toast.makeText(EditProfileFragment.this.getActivity(), "Please confirm your email", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        if (!passwordTXT.getText().toString().trim().equals(confirmPassTXT.getText().toString().trim())) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Please confirm your password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!oldPAssTXT.getText().toString().trim().equals(SessionClass.oldPAssword)) {
            Toast.makeText(EditProfileFragment.this.getActivity(), "Your Old Password Not Correct", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }


}
