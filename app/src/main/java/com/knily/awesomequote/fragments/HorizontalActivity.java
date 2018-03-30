package com.knily.awesomequote.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.knily.awesomequote.DetailActivity;
import com.knily.awesomequote.MainActivity;
import com.knily.awesomequote.PostRetrieve;
import com.knily.awesomequote.R;
import com.knily.awesomequote.TopYapsServiceGen;
import com.knily.awesomequote.adapters.HorizontalAdapter;

import com.knily.awesomequote.adapters.JSONDataAdapter;
import com.knily.awesomequote.helper.Constants;
import com.knily.awesomequote.models.PostJSONData;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by takusemba on 2017/08/03.
 */

public class HorizontalActivity extends Fragment {

    String[] titles = {
            "Android",
            "Beta",
            "Cupcake",
            "Donut",
            "Eclair",
            "Froyo",
            "Gingerbread",
            "Honeycomb",
            "Ice Cream Sandwich",
            "Jelly Bean",
            "KitKat",
            "Lollipop",
            "Marshmallow",
            "Nougat",
            "Android O",
    };

    private  HorizontalAdapter firstAdapter;
    private  HorizontalAdapter secondAdapter;
    private  Button more_1,more_2;
    private ViewPager viewPager;


    List<PostJSONData> arrayList = new ArrayList<>();

    public static HorizontalActivity getInstance(String category, int position) {

        HorizontalActivity fragmentHome = new HorizontalActivity();
        return fragmentHome;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.activity_horizontal, container, false);


        more_1 = (Button)view.findViewById(R.id.more_button_1);
        viewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
        more_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("tt","onClicked");

                viewPager.setCurrentItem(1);



            }
        });

        more_2 = (Button)view.findViewById(R.id.more_button_2);

        more_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Log.d("tt","onClicked");

                viewPager.setCurrentItem(2);



            }
        });

     firstAdapter = new HorizontalAdapter(new ArrayList<PostJSONData>(), new JSONDataAdapter.ItemClickListener() {
            @Override
            public void onItemClick(PostJSONData data) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(Constants.REFERENCE.KNILY_DATA, data);
                Log.d("DATA", data + "");
                startActivity(intent);
            }
        });
        MultiSnapRecyclerView firstRecyclerView = (MultiSnapRecyclerView)view.findViewById(R.id.first_recycler_view);
        LinearLayoutManager firstManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false);
        firstRecyclerView.setLayoutManager(firstManager);
        firstRecyclerView.setAdapter(firstAdapter);

        secondAdapter = new HorizontalAdapter(new ArrayList<PostJSONData>(), new JSONDataAdapter.ItemClickListener() {
            @Override
            public void onItemClick(PostJSONData data) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(Constants.REFERENCE.KNILY_DATA, data);
                Log.d("DATA", data + "");
                startActivity(intent);
            }
        });
        MultiSnapRecyclerView secondRecyclerView =(MultiSnapRecyclerView) view.findViewById(R.id.second_recycler_view);
        LinearLayoutManager secondManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false);
        secondRecyclerView.setLayoutManager(secondManager);
        secondRecyclerView.setAdapter(secondAdapter);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        /*if (Utils.getInstance().checkIfHasNetwork())
        {*/
        makeRequest(PostRetrieve.OFFSET, PostRetrieve.PER_PAGE_INITIAL, true, false, false, 1);
        makeRequest(PostRetrieve.OFFSET, PostRetrieve.PER_PAGE_INITIAL, true, false, false, 2);
        // only showProgressDialog




    }

    // Request from JSON
    public void makeRequest(int pageNumber, int perPageCount, final boolean showProgressDialog, final boolean clearData, final boolean loadMore, final int num) {

        //  if (showProgressDialog) showSwipeRefresh();
        try {
            PostRetrieve client = TopYapsServiceGen.provideTopyapsService();
            Call<List<PostJSONData>> call;
            if (num == 1) {

                call = client.getPostListCategory("80",pageNumber, perPageCount);

                call.enqueue(new Callback<List<PostJSONData>>() { //비동기식
                    @Override
                    public void onResponse(Call<List<PostJSONData>> call, Response<List<PostJSONData>> response) {

                        List<PostJSONData> postJSONData = response.body();
                        arrayList=response.body();
                        try {


                            firstAdapter.addAllData(postJSONData);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //adapter.printData(TAG);
                    }

                    @Override
                    public void onFailure(Call<List<PostJSONData>> call, Throwable t) {

                        Snackbar.make(getView(), t.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });


            }
            else {

                call = client.getPostListCategory("81",pageNumber, perPageCount);

                call.enqueue(new Callback<List<PostJSONData>>() { //비동기식
                    @Override
                    public void onResponse(Call<List<PostJSONData>> call, Response<List<PostJSONData>> response) {

                        List<PostJSONData> postJSONData = response.body();
                        arrayList=response.body();
                        try {


                            secondAdapter.addAllData(postJSONData);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        //adapter.printData(TAG);
                    }

                    @Override
                    public void onFailure(Call<List<PostJSONData>> call, Throwable t) {

                        Snackbar.make(getView(), t.getMessage(), Snackbar.LENGTH_LONG).show();
                    }
                });

            }


        } catch (Exception e) {
            e.printStackTrace();
            Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }

}
