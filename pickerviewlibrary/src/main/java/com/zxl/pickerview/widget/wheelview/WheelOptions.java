package com.zxl.pickerview.widget.wheelview;

import android.view.View;

import com.zxl.pickerview.R;
import com.zxl.pickerview.adapter.ArrayWheelAdapter;
import com.zxl.pickerview.listener.OnItemSelectedListener;

import java.util.ArrayList;

/**
 * Description
 * Created by zxl on 2017/1/4 下午7:01.
 * Email:444288256@qq.com
 */
public class WheelOptions<T> {
    private View view;
    private WheelView wheelView1;
    private WheelView wheelView2;
    private WheelView wheelView3;
    private ArrayList<T> items1,items2,items3;

    private float textSize = 22;
    private boolean linkage = false;
    private OnItemSelectedListener wheelListener_option1;
    private OnItemSelectedListener wheelListener_option2;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public WheelOptions(View view) {
        super();
        this.view = view;
        setView(view);
    }

    public void setPicker(ArrayList<T> options1) {
        items1 = options1;
        setPicker(items1, null, null, false);
    }

    public void setPicker(ArrayList<T> options1,
                          ArrayList<T> options2, boolean linkage) {
        items1 = options1;
        items2 = options2;
        setPicker(items1, items2, null, linkage);
    }

    public void setPicker(ArrayList<T> options1, ArrayList<T> options2
            , ArrayList<T> options3,
                          boolean linkage) {
        this.linkage = linkage;
        items1 = options1;
        items2 = options2;
        items3 = options3;
        int len = ArrayWheelAdapter.DEFAULT_LENGTH;
        if (this.items3 == null)
            len = 8;
        if (this.items2 == null)
            len = 12;
        // 选项1
        wheelView1 = (WheelView) view.findViewById(R.id.options1);
        wheelView1.setAdapter(new ArrayWheelAdapter(items1, len));// 设置显示数据
        wheelView1.setCurrentItem(0);// 初始化时显示的数据
        wheelView1.setCurrentObj(items1.get(0));
        // 选项2
        wheelView2 = (WheelView) view.findViewById(R.id.options2);
        if (items2 != null)
            wheelView2.setAdapter(new ArrayWheelAdapter(items2));// 设置显示数据
        wheelView2.setCurrentItem(wheelView1.getCurrentItem());// 初始化时显示的数据
        wheelView2.setCurrentObj(items2.get(wheelView2.getCurrentItem()));
        // 选项3
        wheelView3 = (WheelView) view.findViewById(R.id.options3);
        if (items3 != null)
            wheelView3.setAdapter(new ArrayWheelAdapter(items3));// 设置显示数据
        wheelView3.setCurrentItem(wheelView3.getCurrentItem());// 初始化时显示的数据
        wheelView3.setCurrentObj(items3.get(wheelView3.getCurrentItem()));
        setTextSize();
        if (this.items2 == null)
            wheelView2.setVisibility(View.GONE);
        if (this.items3 == null)
            wheelView3.setVisibility(View.GONE);

        // 联动监听器
        wheelListener_option1 = new OnItemSelectedListener() {

            @Override
            public void onItemSelected(int index) {
                int opt2Select = 0;
                if (items2 != null) {
                    opt2Select = wheelView2.getCurrentItem();//上一个opt2的选中位置
                    //新opt2的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
                    opt2Select = opt2Select >= items2.size() - 1 ? items2.size() - 1 : opt2Select;

                    wheelView2.setAdapter(new ArrayWheelAdapter(items2));
                    wheelView2.setCurrentItem(opt2Select);
                    wheelView2.setCurrentObj(items2.get(opt2Select));
                }
                if (items3 != null) {
                    wheelListener_option2.onItemSelected(opt2Select);
                }
            }
        };
        wheelListener_option2 = new OnItemSelectedListener() {

            @Override
            public void onItemSelected(int index) {
                if (items3 != null) {
                    int opt1Select = wheelView1.getCurrentItem();
                    opt1Select = opt1Select >= items3.size() - 1 ? items3.size() - 1 : opt1Select;
                    index = index >= items2.size() - 1 ?  items2.size() - 1 : index;
                    int opt3 = wheelView3.getCurrentItem();//上一个opt3的选中位置
                    //新opt3的位置，判断如果旧位置没有超过数据范围，则沿用旧位置，否则选中最后一项
                    opt3 = opt3 >= items3.size() - 1 ? items3.size() - 1 : opt3;

                    wheelView3.setAdapter(new ArrayWheelAdapter(items3));
                    wheelView3.setCurrentItem(opt3);
                    wheelView3.setCurrentObj(items3.get(opt3));
                }
            }
        };

//		// 添加联动监听
        if (items2 != null && linkage)
            wheelView1.setOnItemSelectedListener(wheelListener_option1);
        if (items3 != null && linkage)
            wheelView2.setOnItemSelectedListener(wheelListener_option2);
    }

    /**
     * 设置选项的单位
     * @param label1 单位
     * @param label2 单位
     * @param label3 单位
     */
    public void setLabels(String label1, String label2, String label3) {
        if (label1 != null)
            wheelView1.setLabel(label1);
        if (label2 != null)
            wheelView2.setLabel(label2);
        if (label3 != null)
            wheelView3.setLabel(label3);
    }

    /**
     * 设置是否循环滚动
     * @param cyclic 是否循环
     */
    public void setCyclic(boolean cyclic) {
        wheelView1.setCyclic(cyclic);
        wheelView2.setCyclic(cyclic);
        wheelView3.setCyclic(cyclic);
    }

    /**
     * 分别设置第一二三级是否循环滚动
     * @param cyclic1,cyclic2,cyclic3 是否循环
     */
    public void setCyclic(boolean cyclic1,boolean cyclic2,boolean cyclic3) {
        wheelView1.setCyclic(cyclic1);
        wheelView2.setCyclic(cyclic2);
        wheelView3.setCyclic(cyclic3);
    }
    /**
     * 设置第二级是否循环滚动
     * @param cyclic 是否循环
     */
    public void setOption2Cyclic(boolean cyclic) {
        wheelView2.setCyclic(cyclic);
    }

    /**
     * 设置第三级是否循环滚动
     * @param cyclic 是否循环
     */
    public void setOption3Cyclic(boolean cyclic) {
        wheelView3.setCyclic(cyclic);
    }

    /**
     * 返回当前选中的结果对应的位置数组 因为支持三级联动效果，分三个级别索引，0，1，2
     * @return 索引数组
     */
    public int[] getCurrentItems() {
        int[] currentItems = new int[3];
        currentItems[0] = wheelView1.getCurrentItem();
        currentItems[1] = wheelView2.getCurrentItem();
        currentItems[2] = wheelView3.getCurrentItem();
        return currentItems;
    }

    public void setCurrentItems(int option1, int option2, int option3) {
        if(linkage){
            itemSelected(option1, option2, option3);
        }
        wheelView1.setCurrentItem(option1);
        wheelView2.setCurrentItem(option2);
        wheelView3.setCurrentItem(option3);
    }

    private void itemSelected(int opt1Select, int opt2Select, int opt3Select) {
        if (items2 != null) {
            wheelView2.setAdapter(new ArrayWheelAdapter(items2));
            wheelView2.setCurrentItem(opt2Select);
        }
        if (items3 != null) {
            wheelView3.setAdapter(new ArrayWheelAdapter(items3));
            wheelView3.setCurrentItem(opt3Select);
        }
    }

    /**
     * 设置滚动文字大小
     */
    public void setTextSize(float size){
        this.textSize = size;
        setTextSize();
    }

    private void setTextSize(){
        if(wheelView1 != null)
            wheelView1.setTextSize(textSize);
        if(wheelView2 != null)
            wheelView2.setTextSize(textSize);
        if(wheelView3 != null)
            wheelView3.setTextSize(textSize);
    }
}
