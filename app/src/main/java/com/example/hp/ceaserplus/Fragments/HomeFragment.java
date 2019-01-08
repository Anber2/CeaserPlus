package com.example.hp.ceaserplus.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.hp.ceaserplus.ActivityClasses.Login;
import com.example.hp.ceaserplus.ActivityClasses.MainActivity;
import com.example.hp.ceaserplus.ActivityClasses.NewsDetails;
import com.example.hp.ceaserplus.ActivityClasses.OffersPupup;
import com.example.hp.ceaserplus.ActivityClasses.SessionClass;
import com.example.hp.ceaserplus.ActivityClasses.syncJsonClass;
import com.example.hp.ceaserplus.Others.AppointmentAdapter;
import com.example.hp.ceaserplus.Others.AppointmentsClass;
import com.example.hp.ceaserplus.Others.HomeBannerClass;
import com.example.hp.ceaserplus.Others.NewsScreen_Adapter;
import com.example.hp.ceaserplus.Others.OfferClass;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.Others.newsClass;
import com.example.hp.ceaserplus.Others.urlAdressesClass;
import com.example.hp.ceaserplus.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by HP on 3/28/2017.
 */

public class HomeFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout sliderLayout, sliderLayout2;
    HashMap<String, String> Hash_file_maps, Hash_file_maps2;
    private ViewPager mPager;
    private ProgressDialog progressBar;
    private String url = "";
    private String url2 = "";
    ArrayList<newsClass> newsArrayList2;
    ArrayList<OfferClass> offerClassArrayList2;

    SQLHelper dbHelper;
    TextView titletxt, datetxt, disctxt, offerTitleTxt, offerDescTxt;
    ;
    ImageView newsImage, offerImage1, offerImage2, offerImage3, offerImage4, offerImage5;
    ;
    int currentPos = 0;
    int currentPosOffers = 0;
    CountDownTimer timer;
    CountDownTimer timerOffers;


    ArrayList<HomeBannerClass> bannerClassArrayList;


    ImageButton leftNav, rightNav;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        leftNav = (ImageButton) getView().findViewById(R.id.left_nav);
        rightNav = (ImageButton) getView().findViewById(R.id.right_nav);
        mPager = (ViewPager) getView().findViewById(R.id.viewpager);
        Hash_file_maps = new HashMap<String, String>();
        Hash_file_maps2 = new HashMap<String, String>();
        sliderLayout = (SliderLayout) getView().findViewById(R.id.slider);
        sliderLayout2 = (SliderLayout) getView().findViewById(R.id.slider3);
        titletxt = (TextView) getView().findViewById(R.id.news_title_txt);
        datetxt = (TextView) getView().findViewById(R.id.news_date_txt);
        disctxt = (TextView) getView().findViewById(R.id.news_desc_txt);
        newsImage = (ImageView) getView().findViewById(R.id.news_image);

        offerImage1 = (ImageView) getView().findViewById(R.id.offerImg1);
        offerImage2 = (ImageView) getView().findViewById(R.id.offerImg2);
        offerImage3 = (ImageView) getView().findViewById(R.id.offerImg3);
        offerImage4 = (ImageView) getView().findViewById(R.id.offerImg4);
        offerImage5 = (ImageView) getView().findViewById(R.id.offerImg5);
        offerTitleTxt = (TextView) getView().findViewById(R.id.offer_title_txt);
        offerDescTxt = (TextView) getView().findViewById(R.id.offer_desc_txt);
        MainActivity.searchTXT.setVisibility(View.INVISIBLE);

        dbHelper = new SQLHelper(getActivity());
        dbHelper.open();

        offerImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offerClassArrayList2.size() >= 1) {
                    OffersPupup.offerClassArrayList = offerClassArrayList2;
                    OffersPupup.position = 0;
                    startActivity(new Intent(HomeFragment.this.getActivity(), OffersPupup.class));
                } else {
                    return;
                }

            }
        });
        offerImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offerClassArrayList2.size() >= 2) {
                    OffersPupup.offerClassArrayList = offerClassArrayList2;
                    OffersPupup.position = 1;
                    startActivity(new Intent(HomeFragment.this.getActivity(), OffersPupup.class));
                } else {
                    return;
                }

            }
        });

        offerImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offerClassArrayList2.size() >= 3) {
                    OffersPupup.offerClassArrayList = offerClassArrayList2;
                    OffersPupup.position = 2;
                    startActivity(new Intent(HomeFragment.this.getActivity(), OffersPupup.class));
                } else {
                    return;
                }

            }
        });

        offerImage4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offerClassArrayList2.size() >= 4) {
                    OffersPupup.offerClassArrayList = offerClassArrayList2;
                    OffersPupup.position = 3;
                    startActivity(new Intent(HomeFragment.this.getActivity(), OffersPupup.class));
                } else {
                    return;
                }

            }
        });

        offerImage5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (offerClassArrayList2.size() >= 5) {
                    OffersPupup.offerClassArrayList = offerClassArrayList2;
                    OffersPupup.position = 4;
                    startActivity(new Intent(HomeFragment.this.getActivity(), OffersPupup.class));
                } else {
                    return;
                }

            }
        });

        progressBar = ProgressDialog.show(HomeFragment.this.getActivity(), "", "Please Wait ...", true, false);

        String urlPostData = "?LanguageKey=en_US&SecurityKey=WEBNAVIMSERVICE";

        url = urlAdressesClass.getHomeBannersURL + urlPostData;
        syncHomeBanner syncData = new syncHomeBanner();
        syncData.execute();

        GetNews getNews = new GetNews();
        getNews.execute();

        GetOffers getOffers = new GetOffers();
        getOffers.execute();


        leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = mPager.getCurrentItem();
                if (tab > 0) {
                    tab--;
                    mPager.setCurrentItem(tab);
                } else if (tab == 0) {
                    mPager.setCurrentItem(tab);
                }
            }
        });

        // Images right navigatin
        rightNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tab = mPager.getCurrentItem();
                tab++;
                mPager.setCurrentItem(tab);
            }
        });


    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class GetOffers extends AsyncTask<Void, Void, ArrayList<OfferClass>> {
        OfferClass offerClass;
        ArrayList<OfferClass> offerClassArrayList;

        @Override
        protected ArrayList<OfferClass> doInBackground(Void... params) {


            try {

                String url = urlAdressesClass.getOffersURL + "?SecurityKey=WEBNAVIMSERVICE&LanguageKey=ar_KW";
                JSONArray data = syncJsonClass.getJSONDataArray(url);

                if (data != null) {

                    offerClassArrayList = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {

                        JSONObject jsonObject = data.getJSONObject(i);

                        String offerId = jsonObject.getString("OfferId");
                        String title = jsonObject.getString("Title");
                        String imageUrl = jsonObject.getString("ImageUrl");
                        String date = jsonObject.getString("Date");
                        String shortDesc = jsonObject.getString("ShortDesc");
                        String description = jsonObject.getString("Description");

                        offerClass = new OfferClass(offerId, title, imageUrl, date, shortDesc, description);
                        offerClassArrayList.add(offerClass);
                    }
                }
                return offerClassArrayList;


            } catch (final JSONException e)

            {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HomeFragment.this.getActivity(),
                                "Json parsing error: " + e.getMessage(),
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            } catch ( IOException e)

            {
                e.printStackTrace();
            }


            return null;

        }

        @Override
        protected void onPostExecute(final ArrayList<OfferClass> offerClassArrayList) {
            super.onPostExecute(offerClassArrayList);

            offerClassArrayList2 = offerClassArrayList;
            try {
                if (offerClassArrayList == null || offerClassArrayList.isEmpty()) {
                    Toast.makeText(getActivity(), "No connection could not get Data", Toast.LENGTH_LONG).show();
                    return;
                }

                timerOffers = new CountDownTimer(2000, 1900) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        try {

//                            do
//                            {
                            OfferClass offerClass = offerClassArrayList2.get(currentPosOffers);
                            offerTitleTxt.setText(offerClass.getTitle());
                            if (offerClass.getDescription().isEmpty()) {
                                offerDescTxt.setText("Not Available");
                            } else {
                                offerDescTxt.setText(Html.fromHtml(offerClass.getDescription()));

                            }

                            currentPosOffers++;
                            if (currentPosOffers == 4)
                                currentPosOffers = 0;

//                            }
//                            while (currentPosOffers<4);


                        } catch (Exception ee) {
                            currentPosOffers = 0;
                        }
                    }

                    @Override
                    public void onFinish() {
                        timerOffers.start();
                    }
                }.start();


                OfferClass offerClass0 = offerClassArrayList.get(0);
                if (!(offerClass0.getImageUrl() == null || offerClass0.getImageUrl().isEmpty())) {
                    Picasso.with(getActivity()).load(offerClass0.getImageUrl()).into(offerImage1);

                    offerTitleTxt.setText(offerClass0.getTitle());
                    if (offerClass0.getDescription().isEmpty()) {
                        offerDescTxt.setText("Not Available");
                    } else {
                        offerDescTxt.setText(Html.fromHtml(offerClass.getDescription()));

                    }

                }


                OfferClass offerClass1 = offerClassArrayList.get(1);
                if (!(offerClass1.getImageUrl() == null || offerClass1.getImageUrl().isEmpty())) {
                    Picasso.with(getActivity()).load(offerClass1.getImageUrl()).into(offerImage2);
                }


                OfferClass offerClass2 = offerClassArrayList.get(2);
                if (!(offerClass2.getImageUrl() == null || offerClass2.getImageUrl().isEmpty())) {
                    Picasso.with(getActivity()).load(offerClass2.getImageUrl()).into(offerImage3);
                }


                OfferClass offerClass3 = offerClassArrayList.get(3);
                if (!(offerClass3.getImageUrl() == null || offerClass3.getImageUrl().isEmpty())) {
                    Picasso.with(getActivity()).load(offerClass3.getImageUrl()).into(offerImage4);
                }


                OfferClass offerClass4 = offerClassArrayList.get(4);
                if (!(offerClass4.getImageUrl() == null || offerClass4.getImageUrl().isEmpty())) {
                    Picasso.with(getActivity()).load(offerClass4.getImageUrl()).into(offerImage5);
                }


            } catch (Exception ee) {
                String sss = "";
                System.out.print("" + ee);
                progressBar.dismiss();

            }
            progressBar.dismiss();
        }
    }

    private class syncHomeBanner extends AsyncTask<Void, Integer, ArrayList<HomeBannerClass>> {


        @Override
        protected void onPreExecute() {
            // progressBar.show();
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(ArrayList<HomeBannerClass> bannerClassArrayLis) {

            try {
                HomeBannerClass homeBannerClass;
                for (int i = 0; i < bannerClassArrayLis.size(); i++) {
                    homeBannerClass = bannerClassArrayLis.get(i);
                    Hash_file_maps.put("Image " + i, homeBannerClass.getImgURL());
                }


                for (String name : Hash_file_maps.keySet()) {

                    TextSliderView textSliderView = new TextSliderView(HomeFragment.this.getActivity());
                    textSliderView.description(name)
                            .image(Hash_file_maps.get(name))
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                            .setOnSliderClickListener(HomeFragment.this);
                    textSliderView.bundle(new Bundle());
                    textSliderView.getBundle()
                            .putString("extra", name);
                    sliderLayout.addSlider(textSliderView);
                }

                sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
                sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                sliderLayout.setCustomAnimation(new DescriptionAnimation());
                sliderLayout.setDuration(5000);
                sliderLayout.addOnPageChangeListener(HomeFragment.this);


                super.onPostExecute(bannerClassArrayList);
            } catch (Exception xx) {
            }
            progressBar.dismiss();
        }


        @Override
        protected ArrayList<HomeBannerClass> doInBackground(Void... params) {
            ArrayList<HomeBannerClass> bannerClassArrayList = new ArrayList<>();
            HomeBannerClass homeBannerClass;
            try {
                JSONArray data = syncJsonClass.getJSONDataArray(url);

                if (data != null) {


                    for (int i = 0; i < data.length(); i++) {
                        JSONObject imageUrlObj = data.getJSONObject(i);
                        String imageURL = imageUrlObj.getString("IMageURL");
                        homeBannerClass = new HomeBannerClass(imageURL);
                        bannerClassArrayList.add(homeBannerClass);
                    }
                }

                return bannerClassArrayList;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // callService();
            return null;
        }


    }


    private class GetNews extends AsyncTask<Void, Integer, ArrayList<newsClass>> {
        ArrayList<newsClass> newsArrayList;
        newsClass newsClassObj;

        @Override
        protected ArrayList<newsClass> doInBackground(Void... params) {
            try {

                String url = urlAdressesClass.getNewsURL + "?SecurityKey=WEBNAVIMSERVICE&LanguageKey=en_US";
                JSONArray news = syncJsonClass.getJSONDataArray(url);

                if (news != null) {

                    newsArrayList = new ArrayList<>();

                    dbHelper.Delete("delete from news");
                    for (int i = 0; i < news.length(); i++) {

                        JSONObject jsonObject = news.getJSONObject(i);

                        String title = jsonObject.getString("Title");
                        String date = jsonObject.getString("Date");
                        String teaser = jsonObject.getString("Teaser");
                        String description = jsonObject.getString("Description");
                        String Thumbnail = jsonObject.getString("Thumbnail");
                        String MoreDetailsLink = jsonObject.getString("MoreDetailsLink");

                        newsClassObj = new newsClass(title, date, teaser, description, Thumbnail, MoreDetailsLink, "");
                        newsArrayList.add(newsClassObj);

                        String qury = "insert into news(ID,Title, news_date, news_desc, thumbnail, more_details, teaser) values('" +
                                SessionClass.Genarate_ID() + "','" + title + "','" + date + "','" + description + "','" + Thumbnail +                                    "','"  + MoreDetailsLink + "','" + teaser + "')";

                        dbHelper.Insert(qury);

                    }
                }
                return newsArrayList;

            } catch (final JSONException e)

            {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(HomeFragment.this.getActivity(),
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
        protected void onPostExecute(ArrayList<newsClass> newsArrayList) {
            super.onPostExecute(newsArrayList);


            try {
                //From Database
                if (newsArrayList == null || newsArrayList.isEmpty()) {

                    newsClass newsClassOBJ;

                    ArrayList<newsClass> newsClassArrayList1 = new ArrayList<>();

                    Toast.makeText(getActivity(), "No connection could not get Data", Toast.LENGTH_LONG).show();
                    String sql = " select Title, news_date, news_desc, thumbnail, more_details, teaser from news";
                    Cursor cursor = dbHelper.Select(sql, null);
                    if (cursor.moveToFirst()) {

                        do {

                            //JSONObject jsonObject = data.getJSONObject(i);
                            String title = cursor.getString(0);
                            String date = cursor.getString(1);
                            String description = cursor.getString(2);
                            String Thumbnail = cursor.getString(3);
                            String MoreDetailsLink = cursor.getString(4);
                            String teaser = cursor.getString(5);

                            newsClassOBJ = new newsClass(title, date, teaser, description, Thumbnail, MoreDetailsLink, "");

                            newsClassArrayList1.add(newsClassOBJ);

                        } while (cursor.moveToNext());


                    }

                } else {

                    //From Webservice


                    newsArrayList2 = newsArrayList;
                    newsClass newsClassObj = newsArrayList.get(0);
                    datetxt.setText(newsClassObj.getDate());
                    titletxt.setText(Html.fromHtml(newsClassObj.getTitle()));
                    disctxt.setText(Html.fromHtml(newsClassObj.getDescription()));

                    timer = new CountDownTimer(2000, 2000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            try {
                                int size_ = newsArrayList2.size();

                                if (currentPos < size_) {
                                    newsClass newsClassObj = newsArrayList2.get(currentPos);
                                    datetxt.setText(newsClassObj.getDate());
                                    titletxt.setText(Html.fromHtml(newsClassObj.getTitle()));
                                    disctxt.setText(Html.fromHtml(newsClassObj.getDescription()));
                                    Picasso.with(getActivity()).load(newsClassObj.getThumbnail()).into(newsImage);

                                    currentPos++;
                                } else if (currentPos == size_) {

                                    currentPos = 0;
                                }


                            } catch (Exception ee) {

                            }

                            progressBar.dismiss();
                        }

                        @Override
                        public void onFinish() {
                            timer.start();
                        }
                    }.start();


//                    sliderLayout2.setPresetTransformer(SliderLayout.Transformer.Accordion);
//                    sliderLayout2.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//                    sliderLayout2.setCustomAnimation(new DescriptionAnimation());
//                    sliderLayout2.setDuration(5000);


                    sliderLayout2.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            try {
                                int size_ = newsArrayList2.size();

                                if (currentPos < size_) {
                                    newsClass newsClassObj = newsArrayList2.get(currentPos);
                                    datetxt.setText(newsClassObj.getDate());
                                    titletxt.setText(Html.fromHtml(newsClassObj.getTitle()));
                                    disctxt.setText(Html.fromHtml(newsClassObj.getDescription()));
                                    Picasso.with(getActivity()).load(newsClassObj.getThumbnail()).into(newsImage);

                                    currentPos++;
                                } else if (currentPos == size_) {

                                    currentPos = 0;
                                }


                            } catch (Exception ee) {

                            }

                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }
                    });


                }

            } catch (Exception ee) {
                String sss = "";
                System.out.print("" + ee);

            }

        }


    }


}
