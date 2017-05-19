package com.example.dong.weather;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class todayWeather extends Fragment {

               private TextView temperature;
               private TextView weather;
               private TextView wind;
               private TextView dressing_index;
               private TextView dressing_advice;
               private TextView uv_index;
               private TextView comfort_index;
               private TextView wash_index;
               private TextView travel_index;
               private TextView exercise_index;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_today_weather,container,false);
        Bundle bundle1 = getArguments();
        temperature=(TextView) view.findViewById(R.id.tv_ttemp);
        weather=(TextView) view.findViewById(R.id.tv_tweather);
        wind=(TextView) view.findViewById(R.id.tv_wind);
        dressing_index=(TextView) view.findViewById(R.id.tv_dressing_index);
        dressing_advice=(TextView) view.findViewById(R.id.tv_dressing_advice);
        uv_index=(TextView) view.findViewById(R.id.tv_uv_index);
        comfort_index=(TextView) view.findViewById(R.id.comfort_index);
        wash_index=(TextView) view.findViewById(R.id.tv_wash_index);
        travel_index=(TextView) view.findViewById(R.id.tv_travel_index);
        exercise_index=(TextView) view.findViewById(R.id.tv_exercise_index);
        Weather tw = (Weather) bundle1.getSerializable("weather");
        temperature.setText(tw.getResult().getToday().getTemperature());
        weather.setText(tw.getResult().getToday().getWeather());
        wind.setText(tw.getResult().getToday().getWind());
        dressing_index.setText(tw.getResult().getToday().getDressing_index());
        dressing_advice.setText(tw.getResult().getToday().getDressing_advice());
        uv_index.setText(tw.getResult().getToday().getUv_index());
        comfort_index.setText(tw.getResult().getToday().getComfort_index());
        wash_index.setText(tw.getResult().getToday().getWash_index());
        travel_index.setText(tw.getResult().getToday().getTravel_index());
        exercise_index.setText(tw.getResult().getToday().getExercise_index());
        return view;
    }

}
