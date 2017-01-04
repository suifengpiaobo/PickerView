package com.zxl.pickerview.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zxl.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button timeChoose,cityChoose,otherChoose;
    private TimePickerView timePickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);

        timeChoose = (Button) findViewById(R.id.time_choose);
        cityChoose = (Button) findViewById(R.id.city_choose);
        otherChoose = (Button) findViewById(R.id.other_choose);

        timeChoose.setOnClickListener(this);
        cityChoose.setOnClickListener(this);
        otherChoose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == timeChoose){
            showTimePickerView();
        }else if (view == cityChoose){

        }else if (view == otherChoose){

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
}
