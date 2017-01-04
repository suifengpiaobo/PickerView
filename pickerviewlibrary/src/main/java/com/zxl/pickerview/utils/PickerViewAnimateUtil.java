package com.zxl.pickerview.utils;

import android.view.Gravity;

import com.zxl.pickerview.R;

/**
 * Description
 * Created by zxl on 2017/1/4 下午6:23.
 * Email:444288256@qq.com
 */
public class PickerViewAnimateUtil {
    private static final int INVALID = -1;

    public static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.slide_in_bottom : R.anim.slide_out_bottom;
        }
        return INVALID;
    }
}
