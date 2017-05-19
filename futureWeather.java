package com.example.dong.weather;

import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;


public class futureWeather extends Fragment {
    private ListView mylist;
    private Adapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_future_weather, container, false);
        mylist=(ListView)view.findViewById(R.id.mylist);
        Bundle bundle1 = getArguments();
        Weather tw = (Weather) bundle1.getSerializable("weather");
        List<Weather.Result.futures> list=tw.getResult().getFuture();
        adapter=new Adapter(list,this.getActivity());
        mylist.setAdapter(adapter);
        return view;
    }
}
