package com.example.calendattest1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Presentation;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.naishadhparmar.zcustomcalendar.CustomCalendar;
import org.naishadhparmar.zcustomcalendar.OnDateSelectedListener;
import org.naishadhparmar.zcustomcalendar.Property;

import java.security.ProtectionDomain;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    CustomCalendar customCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //변수선언
        customCalendar = findViewById(R.id.custom_calendar);
        //해시맵 초기화
        HashMap<Object, Property> descHashMap = new HashMap<>();
        //디폴트 값
        Property defaultProperty = new Property();
        //디폴트 소스
        defaultProperty.layoutResource = R.layout.default_view;
        //초기화와 변수선언
        defaultProperty.dateTextViewResource = R.id.text_view;
        //객체와 값 삽입
        descHashMap.put("default", defaultProperty);

        //최근의 날짜의 경우
        Property currentProperty = new Property();
        currentProperty.layoutResource = R.layout.current_view;
        currentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("current",currentProperty);
        //오늘날짜의 경우
        Property presentProperty = new Property();
        presentProperty.layoutResource = R.layout.present_view;
        presentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("present",presentProperty);
        // for absent
        Property absentProperty = new Property();
        absentProperty.layoutResource = R.layout.absent_view;
        absentProperty.dateTextViewResource = R.id.text_view;
        descHashMap.put("absent",absentProperty);
        //
        customCalendar.setMapDescToProp(descHashMap);

        HashMap<Integer,Object> dateHashMap = new HashMap<>();

        Calendar calendar = Calendar.getInstance();

        dateHashMap.put(calendar.get(Calendar.DAY_OF_MONTH), "current");
        dateHashMap.put(1,"present");
        dateHashMap.put(2,"absent");
        dateHashMap.put(3,"present");
        dateHashMap.put(4,"absent");
        dateHashMap.put(20,"present");
        dateHashMap.put(30,"absent");

        customCalendar.setDate(calendar,dateHashMap);

        customCalendar.setOnDateSelectedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(View view, Calendar selectedDate, Object desc) {
                // 선택했을때 날짜 토스트 띄우기
                String sDate = selectedDate.get(Calendar.DAY_OF_MONTH)
                        + "/" + (selectedDate.get(Calendar.MONTH )+1)
                        + "/" + selectedDate.get(Calendar.YEAR);

                Toast.makeText(getApplicationContext()
                        ,sDate,Toast.LENGTH_SHORT).show();
            }
        });

    }
}