package com.example.dong.weather;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class skWeather extends Fragment {
    private TextView tv_temp;
    private TextView tv_wind_direction;
    private TextView tv_wind_strength;
    private TextView tv_humidity;
    private TextView tv_time;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sk_weather,container,false);
        Bundle bundle1 = getArguments();
        tv_temp = (TextView) view.findViewById(R.id.tv_temp);
        tv_wind_direction = (TextView)view. findViewById(R.id.tv_wind_direction);
        tv_wind_strength = (TextView) view.findViewById(R.id.tv_wind_strength);
        tv_humidity = (TextView) view.findViewById(R.id.tv_humidity);
        tv_time = (TextView) view.findViewById(R.id.tv_time);
        Weather tw = (Weather) bundle1.getSerializable("weather");
        tv_temp.setText(tw.getResult().getSk().getTemp()+"℃");
        tv_wind_direction.setText(tw.getResult().getSk().getWind_direction());
        tv_wind_strength.setText(tw.getResult().getSk().getWind_strength());
        tv_humidity.setText(tw.getResult().getSk().getHumidity());
        tv_time.setText(tw.getResult().getSk().getTime());
        return view;
    }
}
