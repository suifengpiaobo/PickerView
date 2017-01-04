package com.zxl.pickerview.widget.wheelview;

import android.view.MotionEvent;

/**
 * Description
 * Created by zxl on 2017/1/4 下午6:21.
 * Email:444288256@qq.com
 */
public class LoopViewGestureListener extends  android.view.GestureDetector.SimpleOnGestureListener{
    final WheelView loopView;

    LoopViewGestureListener(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        loopView.scrollBy(velocityY);
        return true;
    }
}
