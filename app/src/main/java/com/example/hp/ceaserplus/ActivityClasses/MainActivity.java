package com.example.hp.ceaserplus.ActivityClasses;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.ceaserplus.Fragments.AboutUsFragment;
import com.example.hp.ceaserplus.Fragments.ContactUsFragment;
import com.example.hp.ceaserplus.Fragments.DietFragment;
import com.example.hp.ceaserplus.Fragments.FAQFragment;
import com.example.hp.ceaserplus.Fragments.HomeFragment;
import com.example.hp.ceaserplus.Fragments.NewsFragment;
import com.example.hp.ceaserplus.Fragments.OurClincFragment;
import com.example.hp.ceaserplus.Fragments.PhotoGalleryFragment;
import com.example.hp.ceaserplus.Fragments.ProfileFragment;
import com.example.hp.ceaserplus.Fragments.missionVisionFragment;
import com.example.hp.ceaserplus.Others.SQLHelper;
import com.example.hp.ceaserplus.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private BottomNavigationView bottomNavigation;
    private Fragment fragment;

    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    public static EditText searchTXT;
    TextView logOutTxt;
    SQLHelper dbhelper;

    private DrawerLayout drawerLayout;


    @Override
    public void onBackPressed()
    {


        try {

            if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                drawerLayout.closeDrawer(Gravity.RIGHT); //CLOSE Nav Drawer!
            }


            int fragments = getSupportFragmentManager().getBackStackEntryCount();


            if (fragments == 1) {
                new AlertDialog.Builder(this)
                        .setMessage("Are you sure you want to exit?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                MainActivity.this.finish();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            } else

                super.onBackPressed();
        }
        catch (Exception xx)
        {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        logOutTxt = (TextView) findViewById(R.id.log_out_txt);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        dbhelper=new SQLHelper(getApplicationContext());
        dbhelper.open();

        bottomNavigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigation.inflateMenu(R.menu.bottom_menu);
        fragmentManager = getSupportFragmentManager();

        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        searchTXT= (EditText) findViewById(R.id.serchTXT);
        searchTXT.setVisibility(View.INVISIBLE);
        searchTXT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                Fragment currentFragment=fragmentManager.findFragmentById(R.id.main_container);
                if(currentFragment!=null) {
                    String[] currentFragmentName = currentFragment.toString().split("\\{");
                    if (currentFragmentName[0].toString().equalsIgnoreCase("NewsFragment")) {
                        NewsFragment.search_news(searchTXT.getText().toString().trim());

                    } else if (currentFragmentName[0].toString().equalsIgnoreCase("OurClincFragment")) {
                        OurClincFragment.search_Clinics(searchTXT.getText().toString().trim());

                    } else if (currentFragmentName[0].toString().equalsIgnoreCase("FAQFragment")) {
                        FAQFragment.search_faq(searchTXT.getText().toString().trim());

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.action_d_about_us:
                        searchTXT.setVisibility(View.INVISIBLE);
                        fragment = new AboutUsFragment();
                        break;

                    case R.id.action_d_mission:
                        searchTXT.setVisibility(View.INVISIBLE);
                        fragment = new missionVisionFragment();
                        break;
                    // For rest of the options we just show a toast on click

                    case R.id.action_d_news:
                        searchTXT.setVisibility(View.VISIBLE);
                        fragment = new NewsFragment();
                        break;

                    case R.id.action_d_our_clinics:
                        searchTXT.setVisibility(View.VISIBLE);
                        fragment=new OurClincFragment();
                        break;

                    case R.id.action_d_my_diet:
                        searchTXT.setVisibility(View.VISIBLE);
                        fragment = new DietFragment();
                        break;

                    case R.id.action_d_user_profile:
                        searchTXT.setVisibility(View.INVISIBLE);
                        fragment = new ProfileFragment();
                        break;

                    case R.id.action_d_media_gallery:
                        searchTXT.setVisibility(View.INVISIBLE);
                        fragment=new PhotoGalleryFragment();
                        break;

                    case R.id.action_d_faq:
                        searchTXT.setVisibility(View.VISIBLE);
                        fragment = new FAQFragment();
                        break;

                    default:
                        //Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();


                }
                searchTXT.setText("");
                FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.main_container, fragment);
                tx.addToBackStack(null);
                tx.commit();
                return  true;
            }
        });


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle
                (this, drawerLayout, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView)
            {

                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        //searchTXT.setText("");
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.main_container, new HomeFragment());
        tx.addToBackStack(null);
        tx.commit();


        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.action_home:
                        searchTXT.setVisibility(View.INVISIBLE);
                        fragment = new HomeFragment();
                        break;
                    case R.id.action_profile:
                        searchTXT.setVisibility(View.INVISIBLE);
                        fragment = new ProfileFragment();
                        break;
                    case R.id.action_news:
                        searchTXT.setVisibility(View.VISIBLE);
                        fragment = new NewsFragment();
                        break;
                    case R.id.action_contact_us:
                        searchTXT.setVisibility(View.INVISIBLE);
                        fragment = new ContactUsFragment();
                        break;

                }
                searchTXT.setText("");
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_container, fragment).commit();
                transaction.addToBackStack(null);
                return true;


            }
        });

        findViewById(R.id.action_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.RIGHT)) {
                    drawerLayout.closeDrawer(Gravity.RIGHT);

                } else {
                    drawerLayout.openDrawer(Gravity.RIGHT);
                }
            }
        });

        logOutTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Log Out.");
                alertDialog.setMessage("Are You Sure You To Log Out ??");
                //  alertDialog.setIcon(R.drawable.ic_launcher);

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbhelper.Delete("delete from users");
                        Intent i = getBaseContext().getPackageManager()
                                .getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        startActivity(i);
                    }
                });


                alertDialog.show();


            }
        });


    }


}
