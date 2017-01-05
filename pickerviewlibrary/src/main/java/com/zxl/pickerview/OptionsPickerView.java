package com.zxl.pickerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zxl.pickerview.widget.BasePickerView;
import com.zxl.pickerview.widget.wheelview.WheelOptions;

import java.util.ArrayList;

/**
 * Description 其它选择
 * Created by zxl on 2017/1/4 下午7:00.
 * Email:444288256@qq.com
 */
public class OptionsPickerView<T> extends BasePickerView implements View.OnClickListener{
    private Context mContext;

    private OnOptionsSelectListener mOptionsSelectListener;
    private WheelOptions<T> mWheelOptions;
    private Button mSubmit, mCancel;
    private TextView mTxtTitle;
    private View mHeadView;

    private ArrayList<T> items1,items2,items3;

    public OptionsPickerView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.pickerview_options, contentContainer);
        mWheelOptions = new WheelOptions<>(findViewById(R.id.optionspicker));
        mHeadView = findViewById(R.id.rlt_head_view);
        mTxtTitle = (TextView) findViewById(R.id.title);
        mSubmit = (Button) findViewById(R.id.submit);
        mCancel = (Button) findViewById(R.id.cancel);
        mSubmit.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    /**
     * 设置一级数据
     */
    public void setPicker(ArrayList<T> options) {
        items1 = options;
        mWheelOptions.setPicker(items1, null, null, false);
    }

    /**
     * 设置二级数据
     */
    public void setPicker(ArrayList<T> options1, ArrayList<T> options2, boolean linkage) {
        items1 = options1;
        items2 = options2;
        mWheelOptions.setPicker(items1, items2, null, linkage);
    }

    /**
     * 设置三级数据
     */
    public void setPicker(ArrayList<T> options1, ArrayList<T> options2
            , ArrayList<T> options3, boolean linkage) {
        items1 = options1;
        items2 = options2;
        items3 = options3;
        mWheelOptions.setPicker(items1, items2, items3, linkage);
    }

    /**
     * 设置选中的item位置
     * @param option1 位置
     */
    public void setSelectOptions(int option1){
        mWheelOptions.setCurrentItems(option1, 0, 0);
    }

    public void setSelectOptions(int option1, int option2){
        mWheelOptions.setCurrentItems(option1, option2, 0);
    }

    public void setSelectOptions(int option1, int option2, int option3){
        mWheelOptions.setCurrentItems(option1, option2, option3);
    }

    /**
     * 设置选项的单位
     * @param label1 单位
     */
    public void setLabels(String label1){
        mWheelOptions.setLabels(label1, null, null);
    }

    public void setLabels(String label1, String label2){
        mWheelOptions.setLabels(label1, label2, null);
    }

    public void setLabels(String label1, String label2, String label3){
        mWheelOptions.setLabels(label1, label2, label3);
    }

    /**
     * 设置是否循环滚动
     */
    public void setCyclic(boolean cyclic){
        mWheelOptions.setCyclic(cyclic);
    }

    public void setCyclic(boolean cyclic1, boolean cyclic2, boolean cyclic3) {
        mWheelOptions.setCyclic(cyclic1, cyclic2, cyclic3);
    }

    /**
     * 设置头部背景颜色
     */
    public void setHeadBackgroundColor(int color){
        mHeadView.setBackgroundColor(color);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title){
        mTxtTitle.setText(title);
    }

    /**
     * 设置标题颜色
     */
    public void setTitleColor(int resId){
        mTxtTitle.setTextColor(resId);
    }

    /**
     * 设置标题大小
     */
    public void setTitleSize(float size){
        mTxtTitle.setTextSize(size);
    }

    /**
     * 设置取消文字
     */
    public void setCancelText(String text){
        mCancel.setText(text);
    }

    /**
     * 设置取消文字颜色
     */
    public void setCancelTextColor(int resId){
        mCancel.setTextColor(resId);
    }

    /**
     * 设置取消文字大小
     */
    public void setCancelTextSize(float size){
        mCancel.setTextSize(size);
    }

    /**
     * 设置确认文字
     */
    public void setSubmitText(String text){
        mSubmit.setText(text);
    }

    /**
     * 设置确认文字颜色
     */
    public void setSubmitTextColor(int resId){
        mSubmit.setTextColor(resId);
    }

    /**
     * 设置确认文字大小
     */
    public void setSubmitTextSize(float size){
        mSubmit.setTextSize(size);
    }

    /**
     * 设置滚动文字大小
     */
    public void setTextSize(float size){
        mWheelOptions.setTextSize(size);
    }

    @Override
    public void onClick(View v){
        int id = v.getId();
        if(id == R.id.btnSubmit){
            if(mOptionsSelectListener != null){
                int[] optionsCurrentItems = mWheelOptions.getCurrentItems();
                mOptionsSelectListener.onOptionsSelect(optionsCurrentItems[0], optionsCurrentItems[1], optionsCurrentItems[2]);
            }
            dismiss();
        }else if(id == R.id.btnCancel){
            dismiss();
        }
    }

    public interface OnOptionsSelectListener {
        void onOptionsSelect(int option1, int option2, int option3);
    }

    public void setOnOptionsSelectListener(
            OnOptionsSelectListener optionsSelectListener) {
        this.mOptionsSelectListener = optionsSelectListener;
    }
}
