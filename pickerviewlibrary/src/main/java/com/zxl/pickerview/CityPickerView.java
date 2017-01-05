package com.zxl.pickerview;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Description 城市
 * Created by zxl on 2017/1/5 下午2:49.
 * Email:444288256@qq.com
 */
public class CityPickerView extends OptionsPickerView{
    private Context mContext;
    private List<String> mProvices = new ArrayList<>();
    private List<List<String>> mCity = new ArrayList<>();
    private List<List<List<String>>> mArea = new ArrayList<>();

    public OnCitySelectListener mOnCitySelectListener;

    private boolean linkage;

    public CityPickerView(Context context) {
        super(context);
        mContext = context;
    }

    public void initCityData(List<String> provices,List<List<String>> city,List<List<List<String>>> area,boolean linkage){
        this.mProvices.addAll(provices);
        this.mCity.addAll(city);
        this.mArea.addAll(area);

        showCityOPtion(linkage);
    }

    public void showCityOPtion(boolean linkage){
        setTitle("选择城市");
        setPicker(mProvices, mCity, mArea, linkage);
        setCyclic(false, false, false);
        setSelectOptions(0, 0, 0);
        setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                if(mOnCitySelectListener != null){
                    if(mCity.size() > option1 && mCity.size() > option2){
                        if(mArea.size() > option1 && mArea.size() > option2
                                && mArea.size() > option3){
                            String str = mProvices.get(option1).concat(mCity.get(option1)
                                    .get(option2)).concat(mArea.get(option1).get(option2).get(option3));
                            mOnCitySelectListener.onCitySelect(str);
                        }
                    }
                }
            }
        });
    }

    public interface OnCitySelectListener {
        void onCitySelect(String str);
    }

    public void setOnCitySelectListener(OnCitySelectListener listener) {
        this.mOnCitySelectListener = listener;
    }
}
