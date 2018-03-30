package com.knily.awesomequote.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knily.awesomequote.DetailActivity;
import com.knily.awesomequote.EndlessRecyclerViewScrollListener;
import com.knily.awesomequote.MainActivity;
import com.knily.awesomequote.PostRetrieve;
import com.knily.awesomequote.R;
import com.knily.awesomequote.TopYapsServiceGen;
import com.knily.awesomequote.adapters.JSONDataAdapter;
import com.knily.awesomequote.helper.Constants;
import com.knily.awesomequote.models.PostJSONData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentHome  extends Fragment implements SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener /*implements JSONDataAdapter.HomeItemClickListener*/ {
    private SwipeRefreshLayout refreshLayout;
    private static final String[] TYLanguage = {"35459", "2579", "31188","17313"};
    private RecyclerView recyclerView;
    private JSONDataAdapter adapter;
    private ProgressDialog progressDialog;
    private EndlessRecyclerViewScrollListener scrollListener;
    private String category,selectedLanguage;
    private int position;
    private int Fragmentnum;
    List<PostJSONData> arrayList = new ArrayList<>();

    HashMap<String, String> hm = new HashMap<String, String>();

    private String initialLanguageData="35459 2579 17313 31188";

    private static final String TAG = MainActivity.class.getSimpleName();

    public static FragmentHome getInstance(int position) {

        Bundle arguments = new Bundle();
       arguments.putInt("pos", position);
        FragmentHome fragmentHome = new FragmentHome();
        fragmentHome.setArguments(arguments);
        return fragmentHome;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            position = getArguments().getInt("pos");

        }




    }



    public FragmentHome(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_home);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        setHasOptionsMenu(true);///////////For Search////////////////
        /*internet=(TextView)view.findViewById(R.id.internet);*/

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setItemPrefetchEnabled(true); //스크롤을 부드럽게, 렌더링 퍼포먼스 향상


        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setItemPrefetchEnabled(true);
            /* {
            @Override
            public int getSpanSize(int position) {
                switch (adapter.getItemViewType(position)) {
                    case JSONDataAdapter.VIEW_FIRST:
                        return 2;
                    case JSONDataAdapter.VIEW_REST:
                        return 1;
                    default:
                        return 0;
                }
            }
        });*/
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10),true));
        // recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new JSONDataAdapter(new ArrayList<PostJSONData>(), new JSONDataAdapter.ItemClickListener() {
            @Override
            public void onItemClick(PostJSONData data) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra(Constants.REFERENCE.KNILY_DATA, data);
                Log.d("DATA",data+"");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener() {
            @Override
            public void onLoadMore(int page) {
                Log.d(TAG, "onLoadMore:  called");
                adapter.addProgressDialog();
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyItemInserted(adapter.getItemCount());
                    }
                });
                Log.d("Testing1","onLoadMore, makeRequest");
                makeRequest(page + 1, PostRetrieve.PER_PAGE_AFTER, false, false, true);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

        /*recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                JSONDataAdapter jsonDataAdapter=new JSONDataAdapter();
                if(gridLayoutManager.findLastCompletelyVisibleItemPosition()==jsonDataAdapter.getItemCount()-1){
                    makeRequest();
                }
            }
        });*/
        refreshLayout.setOnRefreshListener(this);
        /*refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                makeRequest();
            }
        });*/
        return view;
    }
    //OnClick to go detailActivity with data.
    /*@Override
    public void onClickHome(int position) {
        PostJSONData selectedData=adapter.getSelectedData(position);
        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(Constants.REFERENCE.KNILY_DATA, selectedData);
        startActivity(intent);
    }*/

    @Override
    public void onRefresh() {
        scrollListener.reset();
        Log.d("Testing1","onRefresh, makeRequest");
        makeRequest(PostRetrieve.OFFSET, PostRetrieve.PER_PAGE_INITIAL, false, true, false);
    }

    @Override
    public void onResume() {
        super.onResume();



        /*if (Utils.getInstance().checkIfHasNetwork())
        {*/
        if (adapter.getItemCount() == 0) {
            Log.d("Testing1","onResume, makeRequest");
            makeRequest(PostRetrieve.OFFSET, PostRetrieve.PER_PAGE_INITIAL, true, false, false);
        } /*else if(!Utils.getInstance().checkIfHasNetwork() && adapter.getItemCount()!=0)
        {
            makeRequest(PostRetrieve.OFFSET, PostRetrieve.PER_PAGE_INITIAL, false, false, false);
        }
        else
        {
            internet.setText("No Internet Connection");
            //makeRequestFromDB();
        }*/
    }

    //Request from Database
    private void makeRequestFromDB() {

    }

    private void setSwipeRefreshingFalse() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
    }

    private void showSwipeRefresh() {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
            }
        });
    }

    // Request from JSON
    public void makeRequest(int pageNumber, int perPageCount, final boolean showProgressDialog, final boolean clearData, final boolean loadMore) {

        Log.d("FragmentHome22","catagory ="+position);
        if (showProgressDialog){
            showSwipeRefresh();
        }
        try {
            PostRetrieve client = TopYapsServiceGen.provideTopyapsService();
            Call<List<PostJSONData>> call;

            if(position == 1) {
                call = client.getPostListCategory("80", pageNumber, perPageCount);

            }else if(position == 2){

                call = client.getPostListCategory("81", pageNumber, perPageCount);
            }else{

                call = client.getPostList(pageNumber, perPageCount);
            }

            call.enqueue(new Callback<List<PostJSONData>>() {
                @Override
                public void onResponse(Call<List<PostJSONData>> call, Response<List<PostJSONData>> response) {
                    if (clearData) {
                        adapter.flash();
                        Log.d("Testing1","clearData");

                    }
                    setSwipeRefreshingFalse();
                    if (loadMore) {
                        Log.d("Testing1","loadMore");
                        adapter.removeProgressDialog();
                    }
                    List<PostJSONData> postJSONData = response.body();
                    arrayList=response.body();
                    try {
                        Log.d("Testing1","addAllData");
                        adapter.addAllData(postJSONData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //adapter.printData(TAG);
                }

                @Override
                public void onFailure(Call<List<PostJSONData>> call, Throwable t) {
                    if (loadMore) {
                        Log.d("Testing1","onFailure, loadMore ");
                        adapter.removeProgressDialog();
                    }
                    Log.d("Testing1","onFailuree ");
                    setSwipeRefreshingFalse();
                    Log.e(TAG, t.toString());
                    hidePD();
                    Snackbar.make(getView(), t.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Snackbar.make(getView(), e.getMessage(), Snackbar.LENGTH_LONG).show();
        }
    }
    // for defining the size of the image
    /*private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;
        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount=spanCount;
            this.spacing=spacing;
            this.includeEdge=includeEdge;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state){
            int position=parent.getChildAdapterPosition(view);
            int column=position%spanCount;
            if(includeEdge)
            {
                outRect.left=spacing-column*spacing/spanCount;
                outRect.right=(column+0)*spacing/spanCount;
                if(position<spanCount){
                    outRect.top=spacing;
                }
                outRect.bottom=spacing;
            }
            else
            {
                outRect.left=column*spacing/spanCount;
                outRect.right=spacing-(column+0)*spacing/spanCount;
                if(position>=spanCount){
                    outRect.top=spacing;
                }
            }


        }
    }*/

    ////////////////For Search////////////////////////////////////////
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {

        super.onCreateOptionsMenu(menu, menuInflater);
    }

    // conversion dp to pixel
    private static int dpToPx(Context context, int dp) {
        Resources resources = context.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics()));
    }

    public void showPD() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.isIndeterminate();
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(true);
            progressDialog.show();
        }
    }

    public void hidePD() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /////////////////////////////////for searching/////////////////////
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        List<PostJSONData> newList = new ArrayList<>();
        for (PostJSONData total : arrayList) {
            String name = total.getTitle().getRendered().toLowerCase();
            if (name.contains(newText)) {
                newList.add(total);
            }
        }
        adapter.setFilter(newList);
        return  true;
    }
}