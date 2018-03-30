package com.knily.awesomequote.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;


import com.knily.awesomequote.CuttingActivity;
import com.knily.awesomequote.DetailActivity;
import com.knily.awesomequote.MainActivity;

import com.knily.awesomequote.R;
import com.knily.awesomequote.helper.Constants;


public class FragmentCutting extends Fragment {



    private RelativeLayout kakao_layout, crop_layout;
    private static final String TAG = MainActivity.class.getSimpleName();


    public static FragmentCutting getInstance(String category, int position) {

        FragmentCutting fragmentHome = new FragmentCutting();

        return fragmentHome;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {


        View view = inflater.inflate(R.layout.fragment_cutting, container, false);


        kakao_layout = (RelativeLayout)view.findViewById(R.id.kakao_layout);
        crop_layout = (RelativeLayout)view.findViewById(R.id.crop_layout);

        kakao_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i00 = new Intent(getActivity(), CuttingActivity.class);

                i00.putExtra("position", 1);
                startActivity(i00);
            }
        });

        crop_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i01 = new Intent(getActivity(), CuttingActivity.class);

                i01.putExtra("position", 2);
                startActivity(i01);
            }
        });




        return view;
    }


    @Override
    public void onResume() {
        super.onResume();


    }

    //Request from Database
    private void makeRequestFromDB() {

    }

}