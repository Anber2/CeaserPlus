package com.example.hp.ceaserplus.ActivityClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.Fragments.PackageFragment;
import com.example.hp.ceaserplus.Others.HomeBannerClass;
import com.example.hp.ceaserplus.Others.packageClass;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;

import java.util.ArrayList;

public class PackagePayment extends Activity {

    TextView payment_title, payment_description, exp_date_payment, price_payment;
    Button submitBTN;
    RadioButton knetRB,visaRB,masterRB;
    RadioGroup rbGrup;
    String curentCheckdRB="";
    ProgressDialog progressBar;
     String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_payment);

        packageClass packageClassObj = SessionClass.packageClassObj;


        try {
            payment_title = (TextView) findViewById(R.id.payment_title);
            payment_description = (TextView) findViewById(R.id.payment_description);
            exp_date_payment = (TextView) findViewById(R.id.exp_date_payment);
            price_payment = (TextView) findViewById(R.id.price_payment);

            knetRB= (RadioButton) findViewById(R.id.knetRB);
            visaRB= (RadioButton) findViewById(R.id.visaRB);
            masterRB= (RadioButton) findViewById(R.id.masterCardRB);
            rbGrup= (RadioGroup) findViewById(R.id.radioGropBTN);

            submitBTN= (Button) findViewById(R.id.pay_packg_button);

            payment_title.setText(packageClassObj.getPackages());
            exp_date_payment.setText(packageClassObj.getActiveTo());
            price_payment.setText(packageClassObj.getMembershipFee()+" KWD");
        } catch (Exception e) {
            e.printStackTrace();
        }

        submitBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

       //         progressBar = ProgressDialog.show(getApplicationContext(),"", "Please Wait ...",true,false);

//                String urlPostData="?SecurityKey=WEBNAVIMSERVICE" +
//                        "&LanguageKey=ar_KW" +
//                        "&CrmUserId="+SessionClass.userID.toString().trim()+"" +
//                        "&Id="+SessionClass.Genarate_ID()+"" +
//                        "&Price="+price_payment.getText().toString().trim()+"" +
//                        "&Membership="+SessionClass.CurrentDate(false,true,false).toString()+"" +
//                        "&MembershipName="+confirmPassTXT.getText().toString().trim()+"" +
//                        "&MembershipBenefit=";
//                url= urlAdressesClass.proceedtoPayURL+urlPostData;
//
//                new syncData().execute();


            }
        });

        rbGrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {
                curentCheckdRB=String.valueOf(checkedId);
            }
        });


    }


    private class paymentProcess extends AsyncTask<Void, Integer, ArrayList<String>>
    {
        @Override
        protected ArrayList<String> doInBackground(Void... params)
        {

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<String> PaymentRes)
        {
            try
            {
                progressBar.dismiss();
            }
            catch (Exception xx)
            {}
            super.onPostExecute(PaymentRes);
        }
    }
}
