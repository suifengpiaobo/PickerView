package com.zxl.pickerview.model;

/**
 * Description
 * Created by zxl on 2017/1/4 下午6:06.
 * Email:444288256@qq.com
 */
public class PickerViewPojo {
    String pickerViewText;//显示的文字
    int id; //当前id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPickerViewText(String pickerViewText) {
        this.pickerViewText = pickerViewText;
    }

    public String getPickerViewText(){
        return pickerViewText;
    }
}
