package com.example.hp.ceaserplus.ActivityClasses;

import java.util.ArrayList;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.hp.ceaserplus.Fragments.EditProfileFragment;
import com.example.hp.ceaserplus.R;
import com.example.hp.ceaserplus.Others.SQLHelper;

public class Popup_Class extends Activity {

/////////////////////////////////////////
//Osama Ibrahim  1/9/2013
//Popup Messages Class
////////////////////////////////////////
	
	ListView Popup_LV;
	SQLHelper dbhelper;
	//Button backButton;
	String[]genders = {"Male", "Female", "Other"};
	String[] country;

	Cursor groupsCursor,sectoinsCursor,classCursor,rejectRsnCursor,analysCodeCursor;
	ArrayList<String> results;
	   ProgressDialog progressBar;
	   //MessageBox msgBox;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.popup_withoutsrch);
	    
	    dbhelper=new SQLHelper(this);
	    dbhelper.open();


	    Popup_LV=(ListView) findViewById(R.id.popLV);

	    Popup_LV.setOnItemClickListener(new OnItemClickListener() 
	    {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id)
			{

				if(SessionClass.popupFlag.equalsIgnoreCase("gender"))
				{
					SessionClass.gender=genders[position].toString().trim();
					registerScreen.genderCB.setText(SessionClass.gender);
					finish();

				}
				else if(SessionClass.popupFlag.equalsIgnoreCase("gender_edit"))
				{
					SessionClass.gender=genders[position].toString().trim();
					EditProfileFragment.genderCB.setText(SessionClass.gender);
					finish();

				}

				else if(SessionClass.popupFlag.equalsIgnoreCase("country"))
				{
					SessionClass.country=country[position].toString().trim();
					SendFeedback.countryTXT.setText(SessionClass.country);
					finish();

				}

//				 if(SessionClass.popupData.equalsIgnoreCase("groups"))
//				    {
//
//					 if(groupsCursor.moveToPosition(position))
//					 {
//						 SessionClass.groupID_pop=groupsCursor.getString(0);
//						 SessionClass.groupName_pop=groupsCursor.getString(3);
//
//					 }
//
//					 SubItemsListFragment.groupNmaePOP.setText(SessionClass.groupName_pop);
//					 SubItemsListFragment.classNamePOP.setText("");
//					 SessionClass.classID_pop="";
//
//					 finish();
//				    }
				 

				
			
				
			}
		});
	    
	    if(SessionClass.popupFlag.equalsIgnoreCase("gender") || SessionClass.popupFlag.equalsIgnoreCase("gender_edit") )
	    {
	        Fill_Gender();
//	        
////	        try
////	        {
////	        
////	        Cursor manpID=dbhelperFrame.Select("select a.manucipality_id from establishment_location a where a.id='"+SessionClass.est_loc_ID+"'",null );
////	        if(manpID.moveToFirst())
////	        {
////	        	 for	(int k=0;k< manpciultyCurs.getCount();k++)
////          	{
////	        		 if(manpciultyCurs.moveToPosition(k))
////	        		 {
////          		if(manpciultyCurs.getString(0).equalsIgnoreCase(manpID.getString(0)))
////          		{
////          			Popup_LV.setItemChecked(k, true);
////          			break;
////          		}
////	        		 }
////          	}
////	        }
////	    }
////	    catch (Exception e) 
////	    {
////			// TODO: handle exception
////		}
//	       
	    }

		if(SessionClass.popupFlag.equalsIgnoreCase("country"))
		{

            try
            {

                Resources res = getResources();
                country = res.getStringArray(R.array.country_arrays);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listview_checkboxes,country);
                Popup_LV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                Popup_LV.setAdapter(adapter);



            }
            catch (Exception e)
            {
                e.getMessage();
            }

		}

	   
	  
	  	 	    
	    
	}





	private void Fill_Gender()
	{

		try
		{


				ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listview_checkboxes,genders);
				Popup_LV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
				Popup_LV.setAdapter(adapter);



		}
		catch (Exception e)
		{
			e.getMessage();
		}
	}
	

//	private void Fill_Groups()
//	{
//
//           try
//           {
//        	   groupsCursor= dbhelper.Select("SELECT C.ID, C.DIVISION_ID, C.GROUP_CODE, "+
//											 "  C.GROUP_NAME,C.STATUS "+
//											 "  FROM CPI_GROUPS_TAB C "+
//											 "  where C.STATUS='1' and C.DIVISION_ID='"+SessionClass.sectoinID_pop+"'", null);
//              results=new ArrayList<String>();
//              if (groupsCursor != null ) {
//              if  (groupsCursor.moveToFirst())
//              {
//                    do
//                    {
//                    	String arabName = groupsCursor.getString(3).toString();
//
//                        results.add(arabName);
//
//                    }
//                    while (groupsCursor.moveToNext());
//              }
//              ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.listview_checkboxes,results);
//              Popup_LV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
//              Popup_LV.setAdapter(adapter);
//              }
//
//
//        }
//           catch (Exception e)
//           {
//				e.getMessage();
//			}
//      }
//



	

	


}
