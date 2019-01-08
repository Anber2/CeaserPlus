package com.example.hp.ceaserplus.ActivityClasses;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.example.hp.ceaserplus.Others.PhotoGalleryDetails;
import com.example.hp.ceaserplus.Others.dietClass;
import com.example.hp.ceaserplus.Others.newsClass;
import com.example.hp.ceaserplus.Others.packageClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by HP on 4/3/2017.
 */

public class SessionClass
{


    //Osama Section
    public static String userID="";
    public static String oldPAssword="";
    public  static String gender="";
    public  static String country="";
    public  static String surNAme="";
    public  static String popupFlag="";
    public  static int ourClincListPos;
    public  static String versionNO="1.2";
    public  static String language="";
    public  static String languageKey="ar_KW";
    public  static String memberShip="";



    public static String CurrentDate(Boolean Time,Boolean Date,Boolean DateTime) {

        String FulDate = null;
        final Calendar c = Calendar.getInstance();
        int hour=c.get(Calendar.HOUR);
        int mint=c.get(Calendar.MINUTE);
        int sec=c.get(Calendar.SECOND);

        int todaysDate = (c.get(Calendar.YEAR) * 10000) +((c.get(Calendar.MONTH) + 1) * 100) + (c.get(Calendar.DAY_OF_MONTH));
        String DateString=String.valueOf(todaysDate);
        String Year=DateString.substring(0, 4);
        String Month=DateString.substring(4, 6);
        String Day=DateString.substring(6, 8);

        if(Date==true)
        {
            FulDate=Year+"-"+Month+"-"+Day;
        }
        else if(Time==true)
        {
            FulDate=hour+":"+mint+":"+sec;
        }
        else if(DateTime==true)
        {
            FulDate=Day+"-"+Month+"-"+Year+" "+hour+":"+mint+":"+sec;
        }
        return(String.valueOf(FulDate));

    }

    public static boolean isValidEmaillId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static void BackupDatabase() throws IOException
    {
        boolean success =true;


        File file = null;
        file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"CeaserPlusBackup");
        //file = new File("/sdcard"+File.separator+"CeaserPlusBackup/");

        if(file.exists())
        {
            success =true;
        }
        else
        {
            success = file.mkdir();
        }


        if(success)
        {
            String inFileName = "/data/data/com.example.hp.ceaserplus/databases/CEASER_PLUS_DB.s3db";
            File dbFile = new File(inFileName);
            FileInputStream fis = new FileInputStream(dbFile);

            String outFileName = Environment.getExternalStorageDirectory()+"/CeaserPlusBackup/CEASER_PLUS_DB.s3db";
            //Open the empty db as the output stream
            OutputStream output = new FileOutputStream(outFileName);
            //transfer bytes from the inputfile to the outputfile
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer))>0){
                output.write(buffer, 0, length);
            }

            output.flush();
            output.close();
            fis.close();
        }
    }

    public static String Genarate_ID()
    {
        String Final_ID;
        UUID uniqID=UUID.randomUUID();
        String [] MyID=UUID.randomUUID().toString().split("-");
        Final_ID=MyID[0].toString();
        return Final_ID;

    }

    //Waleed Section

    public static String albumId="";
    public static String ImageId="";
    public static ArrayList<PhotoGalleryDetails> galleryDetailsArrayList =new ArrayList<>();
    public static int appPostion=-1;
    public static String tabPostion="";

    //Saddam Section

    public static ArrayList<newsClass> newsClassArrayList=new ArrayList<>();
    public static newsClass newsClassobj=new newsClass();

    public static ArrayList<dietClass> dietClassArrayList=new ArrayList<>();
    public static dietClass dietClassobj;
    public static packageClass packageClassObj = new packageClass();

}
