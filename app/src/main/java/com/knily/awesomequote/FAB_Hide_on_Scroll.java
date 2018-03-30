package com.knily.awesomequote;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FAB_Hide_on_Scroll extends FloatingActionButton.Behavior {

    private static final String TAG = FAB_Hide_on_Scroll.class.getSimpleName();

    public FAB_Hide_on_Scroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        Log.d(TAG, "onNestedPreScroll: " + dy);
        if (child.getVisibility() == View.VISIBLE && dy != 0) {
            child.hide();
        }
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout layout, FloatingActionButton child, View target, float velocityX, float velocityY) {
        boolean fling = super.onNestedPreFling(layout, child, target, velocityX, velocityY);
        Log.d(TAG, "onNestedPreFling: " + velocityY);
        if (velocityY != 0 && child.getVisibility() == View.VISIBLE) {
            child.hide();
        }
        return fling;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout layout, FloatingActionButton child, View target) {
        Log.d(TAG, "onStopNestedScroll: " + String.valueOf(child.getVisibility() != View.VISIBLE));
        if (child.getVisibility() != View.VISIBLE) {
            child.show();
        }
        super.onStopNestedScroll(layout, child, target);
    }
}
