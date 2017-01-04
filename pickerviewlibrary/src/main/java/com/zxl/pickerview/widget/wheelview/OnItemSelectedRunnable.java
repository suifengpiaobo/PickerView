package com.zxl.pickerview.widget.wheelview;

/**
 * Description
 * Created by zxl on 2017/1/4 下午6:18.
 * Email:444288256@qq.com
 */
public class OnItemSelectedRunnable implements Runnable {
    final WheelView loopView;

    OnItemSelectedRunnable(WheelView loopview) {
        loopView = loopview;
    }

    @Override
    public void run() {
        loopView.onItemSelectedListener.onItemSelected(loopView.getCurrentItem());
    }
}
