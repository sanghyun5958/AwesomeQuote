
package com.knily.awesomequote;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.knily.awesomequote.adapters.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private TextView textview_title;
    private TabLayout tabLayout;
    private ViewPager viewPager=null;
    private Toolbar toolbar;
    private int[] tabIcons = {
            R.drawable.home,
            R.drawable.success,
            R.drawable.love,
            R.drawable.profile

    };



    private boolean doubleBackToExitPressedOnce=false;
    private static final String TAG = MainActivity.class.getSimpleName();

    public static final String LANGUAGE_SELECTOR = "language";

    public static final int LANGUAGE_SELECTOR_REQUEST_CODE = 1;

    private FirebaseAnalytics firebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);


        subscribeToPushService();



        //Firebase Analytics code
        firebaseAnalytics=FirebaseAnalytics.getInstance(this);

        //Toolbar
        toolbar=(Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar); //인자로 받은 툴바를 액티비티의 액션바로 대체하는 역할, 액션바 외에 추가 기능 사용

        //Drawer Layout
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);



        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(1); //페이지 제한


        tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();

        /*//Search Start here
        Intent searchIntent=getIntent();
        if(Intent.ACTION_SEARCH.equals(searchIntent.getAction())){
            String query=searchIntent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this, query,Toast.LENGTH_SHORT).show();
        }*/
    }

    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic("test1");

        Log.d("AndroidBash", "Subscribed");

        String token = FirebaseInstanceId.getInstance().getToken();

    }


    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);

        tabLayout.getTabAt(0).getIcon().setColorFilter(Color.parseColor("#ff33b5e5"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(Color.parseColor("#D7D7E2"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(Color.parseColor("#D7D7E2"), PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(3).getIcon().setColorFilter(Color.parseColor("#D7D7E2"), PorterDuff.Mode.SRC_IN);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#ff33b5e5"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.getIcon().setColorFilter(Color.parseColor("#D7D7E2"), PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    // Setting up vewPager in mainActivity
    private void setupViewPager(ViewPager viewPager) {
        String[] categories = new String[] {"Home", "English", "Korean", "Crop"};
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), categories); //FragmentHome이랑 연결
        /*adapter.addFragment(new FragmentHome(), "HOME");
        adapter.addFragment(new FragmentNews(), "NEWS");
        adapter.addFragment(new FragmentCulture(), "CULTURE");
        adapter.addFragment(new FragmentEntertainment(), "ENTERTAINMENT");
        adapter.addFragment(new FragmentMilitary(), "MILITARY");
        adapter.addFragment(new FragmentWomen(), "WOMEN");
        adapter.addFragment(new FragmentNature(), "NATURE");
        adapter.addFragment(new FragmentFood(), "FOOD");
        adapter.addFragment(new FragmentHistory(), "HISTORY");
        adapter.addFragment(new FragmentVideos(), "VIDEOS");
        adapter.addFragment(new FragmentOpinion(), "OPINION");*/
        viewPager.setAdapter(adapter); //어뎁터 세팅
    }

    //Search Implementation here
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchView searchView= (SearchView)menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager= (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
*/
   /* @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int id = menuItem.getItemId();
        switch(id)
        {
            case R.id.action_search:
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }*/
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawerLayout.closeDrawers();
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home:
                //Setting the current page
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_news:
                //Setting the current page
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_culture:
                //Setting the current page
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_entertainment:
                //Setting the current page
                viewPager.setCurrentItem(3);
                break;
            case R.id.nav_military:
                //Setting the current page
                viewPager.setCurrentItem(4);
                break;
            case R.id.nav_women:
                //Setting the current page
                viewPager.setCurrentItem(5);
                break;
            case R.id.nav_nature:
                //Setting the current page
                viewPager.setCurrentItem(6);
                break;
            case R.id.nav_food:
                //Setting the current page
                viewPager.setCurrentItem(7);
                break;
            case R.id.nav_history:
                //Setting the current page
                viewPager.setCurrentItem(8);
                break;
            case R.id.nav_videos:
                //Setting the current page
                viewPager.setCurrentItem(9);
                break;
            case R.id.nav_opinion:
                //Setting the current page
                viewPager.setCurrentItem(10);
                break;
           
            /*case R.id.lang_app:
                Toast.makeText(this,"This working",Toast.LENGTH_SHORT).show();
                break;*/
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    //when double back press terminate application
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to close the app", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 1000);

    }

    /*@Override
    public void onActivityResult(int requsetCode, int resultCode, Intent data) {
        if (requsetCode == LANGUAGE_SELECTOR_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                ArrayList<String> languages = data.getStringArrayListExtra(Language.LANGUAGES_SELECTED);
                for (String language : languages) {
                    Log.d(TAG, language);
                }
            }
        }
    }*/
}