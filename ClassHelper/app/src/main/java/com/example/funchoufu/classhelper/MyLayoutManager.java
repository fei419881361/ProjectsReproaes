package com.example.funchoufu.classhelper;

import android.app.Activity;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Funchou Fu on 2016/10/15.
 */
public class MyLayoutManager extends RecyclerView.LayoutManager {
    Activity mactivity;
    private int verticalScrollOffset = 0;
    private int totalHeight = 0;
    private SparseArray<Rect> allItemFrames = new SparseArray<>();

    public MyLayoutManager(Activity activity) {
        mactivity = activity;
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //如果没有item，直接返回
        if (getItemCount() <= 0) return;
        // 跳过preLayout，preLayout主要用于支持动画
        if (state.isPreLayout()) {
            return;
        }
        View rview= recycler.getViewForPosition(0);
        rview.setMinimumWidth(getWidth());
        //在布局之前，将所有的子View先Detach掉，放入到Scrap缓存中
        detachAndScrapAttachedViews(recycler);
        totalHeight = 0;
        for (int i = 0; i < getItemCount()&&i<3; i++) {
            //这里就是从缓存里面取出
            View view = recycler.getViewForPosition(i);
            //将View加入到RecyclerView中
            addView(view);
            measureChildWithMargins(view, 0, 0);

            Rect frame = allItemFrames.get(i);
            if (frame == null) {
                frame = new Rect();
            }
            if(i==0) {
                frame.set(getWidth() * 1 / 6, getHeight() / 12, getWidth() * 5 / 6, getHeight() * 3 / 8);
            }
            else if(i==1)
                frame.set(getWidth()/12,getHeight()*5/8,getWidth()*5/12,getHeight()*7/8);
            else
                frame.set(getWidth()*7/12,getHeight()*5/8,getWidth()*11/12,getHeight()*7/8);

            // 将当前的Item的Rect边界数据保存
            allItemFrames.put(i, frame);
            //将竖直方向偏移量增大height
        }
        //如果所有子View的高度和没有填满RecyclerView的高度，
        // 则将高度设置为RecyclerView的高度
        totalHeight = Math.max(totalHeight, getVerticalSpace());
        // fixScrollOffset();
        recycleAndFillItems(recycler, state);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //先detach掉所有的子View
        detachAndScrapAttachedViews(recycler);

        //实际要滑动的距离
        int travel = dy;

        //如果滑动到最顶部
        if (verticalScrollOffset + dy < 0) {
            travel = -verticalScrollOffset;
        } else if (verticalScrollOffset + dy > totalHeight - getVerticalSpace()) {//如果滑动到最底部
            travel = totalHeight - getVerticalSpace() - verticalScrollOffset;
        }

        //将竖直方向的偏移量+travel
        verticalScrollOffset += travel;

        // 平移容器内的item
        offsetChildrenVertical(-travel);
        recycleAndFillItems(recycler, state);
        return travel;
    }

    private void recycleAndFillItems(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.isPreLayout()) { // 跳过preLayout，preLayout主要用于支持动画
            return;
        }

        // 当前scroll offset状态下的显示区域
        Rect displayFrame = new Rect(0, verticalScrollOffset, getHorizontalSpace(), verticalScrollOffset + getVerticalSpace());

        /**
         * 将滑出屏幕的Items回收到Recycle缓存中
         */
        Rect childFrame = new Rect();
        for (int i = 0; i < getChildCount()&&i<3; i++) {
            View child = getChildAt(i);
            childFrame.left = getDecoratedLeft(child);
            childFrame.top = getDecoratedTop(child);
            childFrame.right = getDecoratedRight(child);
            childFrame.bottom = getDecoratedBottom(child);
            //如果Item没有在显示区域，就说明需要回收
            if (!Rect.intersects(displayFrame, childFrame)) {
                //回收掉滑出屏幕的View
                removeAndRecycleView(child, recycler);

            }
        }

        //重新显示需要出现在屏幕的子View
        for (int i = 0; i < getItemCount()&&i<3; i++) {

            if (Rect.intersects(displayFrame, allItemFrames.get(i))) {

                View scrap = recycler.getViewForPosition(i);
                measureChildWithMargins(scrap, 0, 0);
                addView(scrap);

                Rect frame = allItemFrames.get(i);
//                scrap.measure(View.MeasureSpec.makeMeasureSpec(getWidth()*2/3, View.MeasureSpec.AT_MOST),View.MeasureSpec.makeMeasureSpec(getHeight()*7/24, View.MeasureSpec.AT_MOST));

                //将这个item布局出来
                layoutDecorated(scrap,
                        frame.left,
                        frame.top - verticalScrollOffset,
                        frame.right,
                        frame.bottom - verticalScrollOffset);

            }
        }
    }

    private int getVerticalSpace() {
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }

    private int getHorizontalSpace() {
        return getWidth() - getPaddingRight() - getPaddingLeft();
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.WRAP_CONTENT);
    }
}
