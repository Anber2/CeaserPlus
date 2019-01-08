package com.example.hp.ceaserplus.Fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hp.ceaserplus.ActivityClasses.MainActivity;
import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.R;

/**
 * Created by HP on 3/28/2017.
 */

public class ProfileFragment extends Fragment {
    private View view;
    private TextView profileNameTxt, profileAddressTxt,genderTXT,ageTXT,phoneTXT,emailTXT;
    private Button appointmentBtn, dietBtn, packageBtn;
    Button editProfileBTN;
    private ImageView profileImage;
    SQLHelper dbhelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        dbhelper=new SQLHelper(getActivity());
        dbhelper.open();

        initView();
        appointmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.searchTXT.setVisibility(View.INVISIBLE);
                SessionClass.tabPostion="0";
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.main_container, new    ProfileDetailsTabs()).commit();

            }
        });
        // appointmentBtn.setOnClickListener(itemClickListener);

        dietBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.searchTXT.setVisibility(View.VISIBLE);
                SessionClass.tabPostion="1";
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.main_container, new ProfileDetailsTabs()).commit();
            }
        });
        // dietBtn.setOnClickListener(itemClickListener);
        packageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.searchTXT.setVisibility(View.INVISIBLE);
                SessionClass.tabPostion="2";
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.main_container, new ProfileDetailsTabs()).commit();
            }
        });


        editProfileBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Fragment fragment;
                fragment = new EditProfileFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.main_container, fragment).commit();
            }
        });

        return view;
    }


    private void initView()
    {
        try {
            profileNameTxt = (TextView) view.findViewById(R.id.profile_name_txt);
            profileAddressTxt = (TextView) view.findViewById(R.id.profile_address_txt);
            genderTXT = (TextView) view.findViewById(R.id.genderTXT);
            ageTXT = (TextView) view.findViewById(R.id.ageTXT);
            phoneTXT = (TextView) view.findViewById(R.id.phoneTXT);
            emailTXT = (TextView) view.findViewById(R.id.emailTXT);


            appointmentBtn = (Button) view.findViewById(R.id.appointment_btn);
            dietBtn = (Button) view.findViewById(R.id.diet_btn);
            packageBtn = (Button) view.findViewById(R.id.package_btn);
            profileImage = (ImageView) view.findViewById(R.id.profile_img);
            editProfileBTN = (Button) view.findViewById(R.id.editProfileBTN);

            String sql="select first_name,last_name,email,gender,age,mobile,password,crmuserid from users";
            Cursor userData=dbhelper.Select(sql,null);
            if(userData.moveToFirst())
            {
                profileNameTxt.setText(userData.getString(0).toString().trim()+" "+userData.getString(1).toString().trim());
                genderTXT.setText(userData.getString(3).toString().trim());
                ageTXT.setText(userData.getString(4).toString().trim());
                phoneTXT.setText(userData.getString(5).toString().trim());
                emailTXT.setText(userData.getString(2).toString().trim());
//              profileNameTxt.setText(userData.getString(0).toString().trim());
            }
        }
        catch (Exception xx)
        {
            xx.toString();
        }

    }


    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        public void onClick(View v) {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.main_container, new ProfileDetailsTabs()).commit();

        }
    };


}
