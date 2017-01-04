package com.zxl.pickerview.adapter;

/**
 * Description
 * Created by zxl on 2017/1/4 下午6:07.
 * Email:444288256@qq.com
 */
public interface WheelAdapter<T> {
    /**
     *
     * @return the count of wheel items
     */
    int getItemsCount();

    /**
     *
     * @param index the item index
     * @return the wheel item text or null
     */
    T getItem(int index);

    /**
     * Gets maximum item length. It is used to determine the wheel width.
     * If -1 is returned there will be used the default wheel width.
     * @param o
     * @return the maximum item length or -1
     */
    int indexOf(T o);
}
