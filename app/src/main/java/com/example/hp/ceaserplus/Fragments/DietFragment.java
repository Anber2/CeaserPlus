package com.example.hp.ceaserplus.Fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.ceaserplus.Others.MyRadioAdapter;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.Others.dietClass;
import com.example.hp.ceaserplus.R;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by HP on 4/6/2017.
 */

public class DietFragment extends Fragment implements AdapterView.OnItemClickListener {


    ListView saladLV, breakfastLV, soupLV, lunchLV, DinnerLV, snackLV;
    LinearLayout saladLO, saladLO2, saladtitleLO, breakfastLO, breakfasttitleLO, breakfastLO2, soupLO, souptitleLO, soupLO2, lunchLO, lunchtitleLO, lunchLO2, DinnerLO, DinnertitleLO, DinnerLO2, snackLO, snacktitleLO, snackLO2;

    ImageView salad_imagearrow, breakfast_imagearrow, soup_imagearrow, lunch_imagearrow, dinner_imagearrow, snack_imagearrow;

    ImageView next, back;

    View view;

    private static ListView dietlist;

    private static MyRadioAdapter dietMealsAdptr;
    private static ArrayList<dietClass> dietClassArr;
    ProgressDialog progressBar;
    String MealId, MealItem, MasterMealItem, MealPeriod, MealDescription, MealCalories;
    private static Cursor searchCursor;
    static Activity currentCOntx;
    Animation animSlideDOwn, animSlideUp;
    JSONArray diet;

    private static SQLHelper dbHelper;

    int counter = 0;

    String[] a = {"a", "b"};

    TextView daysTV;

    dietClass dietClassobj;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.diet_table, container, false);

        dbHelper = new SQLHelper(getActivity());
        dbHelper.open();

        currentCOntx = this.getActivity();


        // return inflater.inflate(R.layout.diet_layout, container, false);
        animSlideDOwn = AnimationUtils.loadAnimation(getActivity(), R.anim.slidedown);
        animSlideUp = AnimationUtils.loadAnimation(getActivity(), R.anim.slideup);

        next = (ImageView) v.findViewById(R.id.diet_table_arr_right);
        back = (ImageView) v.findViewById(R.id.diet_table_arr_left);
        daysTV = (TextView) v.findViewById(R.id.diet_table_days);


        Resources res = getResources();

        final String[] daysList = res.getStringArray(R.array.days_table);
        ((TextView) v.findViewById(R.id.diet_table_days)).setText(daysList[counter]);

        Log.e("counter = = ", " " + counter);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter++;

                if (counter >= daysList.length)
                    counter = 0;

                daysTV.setText(daysList[counter]);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter--;

                if (counter < 0)
                    counter = 7 - Math.abs(counter);

                daysTV.setText(daysList[counter]);
            }
        });

        breakfastLO = (LinearLayout) v.findViewById(R.id.breakfastLO);
        soupLO = (LinearLayout) v.findViewById(R.id.soupLO);
        saladLO = (LinearLayout) v.findViewById(R.id.saladLO);
        lunchLO = (LinearLayout) v.findViewById(R.id.lunchLO);
        DinnerLO = (LinearLayout) v.findViewById(R.id.DinnerLO);
        snackLO = (LinearLayout) v.findViewById(R.id.snackLO);

        breakfasttitleLO = (LinearLayout) v.findViewById(R.id.breakfasttitleLO);
        souptitleLO = (LinearLayout) v.findViewById(R.id.souptitleLO);
        saladtitleLO = (LinearLayout) v.findViewById(R.id.saladtitleLO);
        lunchtitleLO = (LinearLayout) v.findViewById(R.id.lunchtitleLO);
        DinnertitleLO = (LinearLayout) v.findViewById(R.id.dinnertitleLO);
        snacktitleLO = (LinearLayout) v.findViewById(R.id.snacktitleLO);


        breakfastLO2 = (LinearLayout) v.findViewById(R.id.breakfastLO2);
        breakfastLO2.setVisibility(View.GONE);

        soupLO2 = (LinearLayout) v.findViewById(R.id.soupLO2);
        soupLO2.setVisibility(View.GONE);

        saladLO2 = (LinearLayout) v.findViewById(R.id.saladLO2);
        saladLO2.setVisibility(View.GONE);

        lunchLO2 = (LinearLayout) v.findViewById(R.id.lunchLO2);
        lunchLO2.setVisibility(View.GONE);

        DinnerLO2 = (LinearLayout) v.findViewById(R.id.DinnerLO2);
        DinnerLO2.setVisibility(View.GONE);

        snackLO2 = (LinearLayout) v.findViewById(R.id.snackLO2);
        snackLO2.setVisibility(View.GONE);

        breakfastLV = (ListView) v.findViewById(R.id.breakfastLV);
        breakfastLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "No connection could not get Data", Toast.LENGTH_LONG).show();

            }
        });
        soupLV = (ListView) v.findViewById(R.id.soupLV);
        saladLV = (ListView) v.findViewById(R.id.saladLV);
        lunchLV = (ListView) v.findViewById(R.id.lunchLV);
        DinnerLV = (ListView) v.findViewById(R.id.DinnerLV);
        snackLV = (ListView) v.findViewById(R.id.snackLV);

        breakfast_imagearrow = (ImageView) v.findViewById(R.id.breakfast_imagearrow33);
        soup_imagearrow = (ImageView) v.findViewById(R.id.soup_imagearrow);
        salad_imagearrow = (ImageView) v.findViewById(R.id.salad_imagearrow);

        lunch_imagearrow = (ImageView) v.findViewById(R.id.lunch_imagearrow);
        dinner_imagearrow = (ImageView) v.findViewById(R.id.dinner_imagearrow);
        snack_imagearrow = (ImageView) v.findViewById(R.id.snack_imagearrow);


        breakfastLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (breakfastLO2.getVisibility() == View.GONE) {
                    breakfastLO2.setVisibility(View.VISIBLE);
                    breakfastLO2.setAnimation(animSlideDOwn);
                    breakfasttitleLO.setBackgroundColor(Color.parseColor("#86744e"));
                    breakfast_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));

                    ArrayList<dietClass> dietClassArr = new ArrayList<>();

                    dietClassobj = new dietClass("Egg", "150");
                    dietClassArr.add(dietClassobj);
                    dietClassobj = new dietClass("Egg", "150");
                    dietClassArr.add(dietClassobj);
                    dietClassobj = new dietClass("Egg", "150");
                    dietClassArr.add(dietClassobj);

                    dietMealsAdptr = new MyRadioAdapter(getActivity(), dietClassArr);
                    breakfastLV.setAdapter(dietMealsAdptr);


//                    dietClassArr.add(dietClassobj);
//                    dietClassobj = new dietClass("Cheese", "180 Cal");
//                    dietClassArr.add(dietClassobj);
//                    dietClassobj = new dietClass("Zait w zatar", "10 Cal");
//                    dietClassArr.add(dietClassobj);


                    /*ArrayList<String> breakfastMeals = new ArrayList<String>();
                    breakfastMeals.add("Cheese 150 kal");
                    breakfastMeals.add("Cheese2 150 kal");
                    breakfastMeals.add("Cheese3 150 kal");
                    breakfastMeals.add("Cheese 150 kal");
                    breakfastMeals.add("Cheese2 150 kal");
                    breakfastMeals.add("Cheese3 150 kal");

                    breakfastLV.setVisibility(View.VISIBLE);


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.listview_radio_diet, breakfastMeals);
                    breakfastLV.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    breakfastLV.setAdapter(adapter);*/


                } else {
                    breakfastLO2.setVisibility(View.GONE);
                    breakfastLO2.setAnimation(animSlideUp);

                    breakfasttitleLO.setBackgroundColor(Color.parseColor("#CD853F"));
                    breakfast_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }

            }
        });

        breakfastLV.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        soupLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (soupLO2.getVisibility() == View.GONE) {
                    soupLO2.setVisibility(View.VISIBLE);
                    soupLO2.setAnimation(animSlideDOwn);

                    souptitleLO.setBackgroundColor(Color.parseColor("#86744e"));
                    soup_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                } else {
                    soupLO2.setVisibility(View.GONE);
                    soupLO2.setAnimation(animSlideUp);

                    souptitleLO.setBackgroundColor(Color.parseColor("#CD853F"));
                    soup_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }

            }
        });


        saladLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (saladLO2.getVisibility() == View.GONE) {
                    saladLO2.setVisibility(View.VISIBLE);
                    saladLO2.setAnimation(animSlideDOwn);

                    saladtitleLO.setBackgroundColor(Color.parseColor("#86744e"));
                    salad_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                } else {
                    saladLO2.setVisibility(View.GONE);
                    saladLO2.setAnimation(animSlideUp);

                    saladtitleLO.setBackgroundColor(Color.parseColor("#CD853F"));
                    salad_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }

            }
        });

        lunchLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (lunchLO2.getVisibility() == View.GONE) {
                    lunchLO2.setVisibility(View.VISIBLE);
                    lunchLO2.setAnimation(animSlideDOwn);

                    lunchtitleLO.setBackgroundColor(Color.parseColor("#86744e"));
                    lunch_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                } else {
                    lunchLO2.setVisibility(View.GONE);
                    lunchLO2.setAnimation(animSlideUp);

                    lunchtitleLO.setBackgroundColor(Color.parseColor("#CD853F"));
                    lunch_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }

            }
        });

        DinnerLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DinnerLO2.getVisibility() == View.GONE) {
                    DinnerLO2.setVisibility(View.VISIBLE);
                    DinnerLO2.setAnimation(animSlideDOwn);

                    DinnertitleLO.setBackgroundColor(Color.parseColor("#86744e"));
                    dinner_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                } else {
                    DinnerLO2.setVisibility(View.GONE);
                    DinnerLO2.setAnimation(animSlideUp);

                    DinnertitleLO.setBackgroundColor(Color.parseColor("#CD853F"));
                    dinner_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }
            }
        });

        snackLO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (snackLO2.getVisibility() == View.GONE) {
                    snackLO2.setVisibility(View.VISIBLE);
                    snackLO2.setAnimation(animSlideDOwn);

                    snacktitleLO.setBackgroundColor(Color.parseColor("#86744e"));
                    snack_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.up_arrow));
                } else {
                    snackLO2.setVisibility(View.GONE);
                    snackLO2.setAnimation(animSlideUp);

                    snacktitleLO.setBackgroundColor(Color.parseColor("#CD853F"));
                    snack_imagearrow.setImageDrawable(getResources().getDrawable(R.drawable.down_arrow));
                }
            }
        });

        return v;

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        // new GetDiet().execute();

       /* saladLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub

                dietClass dietClass = adapter.getItem(position);
                SessionClass.dietClassobj = dietClass;
                Intent intent = new Intent(getActivity(), dietDetails.class);
                startActivity(intent);


            }
        });*/


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }

   /* private class GetDiet extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... params) {


            try {

                String url = urlAdressesClass.myDietURL + "?SecurityKey=WEBNAVIMSERVICE&LanguageKey=en_US&UserId=" + SessionClass.userID + "";
                diet = syncJsonClass.getJSONDataArray(url);

                if (diet == null) {
                    return null;
                }

                dietClass dietClassObj;
                dietClassArr = new ArrayList<>();
                dbHelper.Delete("delete from diet");

                for (int i = 0; i < diet.length(); i++) {

                    JSONObject jsonObject = diet.getJSONObject(i);

                    String MealId = jsonObject.getString("MealId");
                    String MealItem = jsonObject.getString("MealItem");
                    String MasterMealItem = jsonObject.getString("MasterMealItem");
                    String MealPeriod = jsonObject.getString("MealPeriod");
                    String MealDescription = jsonObject.getString("MealDescription");
                    String MealCalories = jsonObject.getString("MealCalories");

                    dietClassObj = new dietClass(MealId, MealItem, MasterMealItem, MealPeriod, MealDescription, MealCalories);
                    dietClassArr.add(dietClassObj);

                    String qury =
                            "insert into diet(meal_id,meal_item, Master_Meal_Item, Meal_Period, Meal_Description, Meal_Calories) values('"
                                    + MealId + "','" + MealItem + "','" + MasterMealItem + "'," +
                                    " '" + MealPeriod + "','" + MealDescription + "','" + MealCalories + "')";

                    String resss = dbHelper.Insert(qury);
                    String cfc = resss;
                    String cfcccc = resss;

                }


            } catch (final JSONException e)

            {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DietFragment.this.getActivity(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            } catch (
                    IOException e)

            {
                e.printStackTrace();
            }


            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            try {
                if (dietClassArr == null || dietClassArr.isEmpty()) {

                    Toast.makeText(getActivity(), "No connection could not get Data", Toast.LENGTH_LONG).show();
                    String sql = " select * from diet";
                    Cursor cursor = dbHelper.Select(sql, null);
                    ArrayList<dietClass> dietClassArrayList1 = new ArrayList<>();
                    dietClass dietClassobj;
                    if (cursor.moveToFirst()) {


                        do {

                            //JSONObject jsonObject = data.getJSONObject(i);
                            MealId = cursor.getString(0);
                            MealItem = cursor.getString(1);
                            MasterMealItem = cursor.getString(2);
                            MealPeriod = cursor.getString(3);
                            MealDescription = cursor.getString(4);
                            MealCalories = cursor.getString(5);

                            dietClassobj = new dietClass(MealId, MealItem, MasterMealItem, MealPeriod, MealDescription, MealCalories);

                            dietClassArrayList1.add(dietClassobj);
                        } while (cursor.moveToNext());


                    }

                    adapter = new DietList_Adapter(getActivity(), dietClassArrayList1);
                    dietlist.setAdapter(adapter);

                } else {

                    adapter = new DietList_Adapter(getActivity(), dietClassArr);
                    dietlist.setAdapter(adapter);

                }

            } catch (Exception ee) {
                String sss = "";
                System.out.print("" + ee);


            }
            progressBar.dismiss();
        }
    }*/


    private void inist() {
    }


}
