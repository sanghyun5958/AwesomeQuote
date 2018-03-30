package com.knily.awesomequote;

import android.support.v7.widget.RecyclerView;

@SuppressWarnings("unused")
public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

    private static final String TAG = EndlessRecyclerViewScrollListener.class.getSimpleName();

    /**
     * The minimum amount of items to have below your current scroll position
     * before loading more
     */
    private static final int VISIBLE_THRESHOLD = 2;

    /**
     * The total number of items in the data set after the last load
     */
    private int mPreviousTotal = 0;

    private int currentPage = 1;

    /**
     * True if we are still waiting for the last set of data to load.
     */
    private boolean mLoading = true;

    public EndlessRecyclerViewScrollListener() {
    }

    private int getFirstVisiblePosition(int[] spans) {
        int min = Integer.MAX_VALUE;
        for (int span : spans) {
            if (span < min) {
                min = span;
            }
        }
        return min;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        super.onScrolled(view, dx, dy);
    /*    StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) view.getLayoutManager();
        GridLayoutManager gridLayoutManager = (GridLayoutManager) view.getLayoutManager();

        int visibleItemCount = view.getChildCount();
        int totalItemCount = gridLayoutManager.getItemCount();
     //   int[] firstVisibleItems = gridLayoutManager.findFirstVisibleItemPosition();
       // int totalItemCount = staggeredGridLayoutManager.getItemCount();
      //  int[] firstVisibleItems = staggeredGridLayoutManager.findFirstVisibleItemPositions(null);
        int firstVisibleItems = getFirstVisiblePosition(firstVisibleItems);

        if (totalItemCount != 0 && dy != 0) {
            if (mLoading) {
                if (totalItemCount > mPreviousTotal + 1) {
                    mLoading = false;
                    mPreviousTotal = totalItemCount;
                }
            }

            if (!mLoading && ((totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD))) {
                currentPage++;
                onLoadMore(currentPage);
                mLoading = true;
            }
        }*/
    }

    public void reset() {
        currentPage = 1;
        mPreviousTotal = 0;
        mLoading = true;
    }

    protected abstract void onLoadMore(int page);
}