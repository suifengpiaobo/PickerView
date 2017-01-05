package com.zxl.pickerview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zxl.pickerview.CityPickerView;
import com.zxl.pickerview.OptionsPickerView;
import com.zxl.pickerview.TimePickerView;
import com.zxl.pickerview.model.PickerViewPojo;
import com.zxl.pickerview.utils.FileUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.zxl.pickerview.utils.FileUtils.getJsonData;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button timeChoose,cityChoose,otherChoose;
    private TimePickerView timePickerView;
    private OptionsPickerView<PickerViewPojo> mOptionsPickerView;
    private CityPickerView cityPickerView;

    private List<String> mProvices = new ArrayList<>();
    private List<List<String>> mCity = new ArrayList<>();
    private List<List<List<String>>> mArea = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        mOptionsPickerView = new OptionsPickerView<>(this);
        cityPickerView = new CityPickerView(this);

        timeChoose = (Button) findViewById(R.id.time_choose);
        cityChoose = (Button) findViewById(R.id.city_choose);
        otherChoose = (Button) findViewById(R.id.other_choose);

        timeChoose.setOnClickListener(this);
        cityChoose.setOnClickListener(this);
        otherChoose.setOnClickListener(this);

        initCityData();
    }

    @Override
    public void onClick(View view) {
        if (view == timeChoose){
            showTimePickerView();
        }else if (view == cityChoose){
            showCityPickerView();
        }else if (view == otherChoose){
            showOtherPickerView();
        }
    }

    public void showTimePickerView(){
        timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_SHORT).show();
            }
        });
        timePickerView.show();
    }

    public void initCityData(){
        JSONObject object = FileUtils.getJsonData(MainActivity.this,"city.json");
        try {
            JSONArray jsonArray = object.getJSONArray("citylist");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 获取每个省的Json对象
                String province = jsonP.getString("name");

                List<String> options2Items_01 = new ArrayList<>();
                List<List<String>> options3Items_01 = new ArrayList<>();
                JSONArray jsonCs = jsonP.getJSONArray("city");
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonC = jsonCs.getJSONObject(j);// 获取每个市的Json对象
                    String city = jsonC.getString("name");
                    options2Items_01.add(city);// 添加市数据

                    ArrayList<String> options3Items_01_01 = new ArrayList<>();
                    JSONArray jsonAs = jsonC.getJSONArray("area");
                    for (int k = 0; k < jsonAs.length(); k++) {
                        options3Items_01_01.add(jsonAs.getString(k));// 添加区数据
                    }
                    options3Items_01.add(options3Items_01_01);
                }
                mProvices.add(province);// 添加省数据
                mCity.add(options2Items_01);
                mArea.add(options3Items_01);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void showCityPickerView(){
        cityPickerView.initCityData(mProvices,mCity,mArea,true);

        cityPickerView.setOnCitySelectListener(new CityPickerView.OnCitySelectListener() {
            @Override
            public void onCitySelect(String str) {
                Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
        cityPickerView.show();
    }

    public void showOtherPickerView(){
        final ArrayList<PickerViewPojo> list = new ArrayList<>();
        PickerViewPojo pojo;
        pojo = new PickerViewPojo();
        pojo.setPickerViewText("男");
        list.add(pojo);

        pojo = new PickerViewPojo();
        pojo.setPickerViewText("女");
        list.add(pojo);
        mOptionsPickerView.setPicker(list);

        mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String sex = list.get(option1).getPickerViewText();
                Toast.makeText(MainActivity.this, sex, Toast.LENGTH_SHORT).show();
            }
        });
        mOptionsPickerView.show();
    }
}
