package com.knily.awesomequote.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.knily.awesomequote.R;
import com.knily.awesomequote.fragments.FragmentCutting;
import com.knily.awesomequote.fragments.FragmentHome;
import com.knily.awesomequote.fragments.HorizontalActivity;

public class ViewPagerAdapter extends FragmentStatePagerAdapter{//FragmentStatePagerAdapter { //extends는 고정되지않은 페이지수에 적합

    /*private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> stringList=new ArrayList<>();*/
    private final String[] categories;

    public ViewPagerAdapter(FragmentManager manager, String[] categories) {
        super(manager);
        this.categories = categories;
    }

    @Override
    public Fragment getItem(int position) {


        if(position == 0)
        {

            return HorizontalActivity.getInstance(categories[position], position);
        }else if(position ==1 || position ==2)
        {
            return FragmentHome.getInstance(position);
        }else
        {
            return FragmentCutting.getInstance(categories[position], position);
        }

      /*  switch (position) {
            case 0:
                //Setting the current page
                return FragmentZ.getInstance(categories[position], position);
   //         break;
            case 1:
                //Setting the current page
                return FragmentHome.getInstance(categories[position], position);
    //            break;
            case 2:
                //Setting the current page
                return FragmentHome.getInstance(categories[position], position);
   //             break;
            case 3:
                //Setting the current page
                return FragmentHome.getInstance(categories[position], position);
     //           break;



        }*/
    }

    @Override
    public int getCount() {
        return categories.length;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return categories[position];
    }

    /*public void addFragment(Fragment fragment, String string) {
        fragmentList.add(fragment);
        stringList.add(string);
    }*/
}
