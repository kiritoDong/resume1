package com.example.dong.weather;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends BaseAdapter {
    private LayoutInflater inflater;
    private  List<Weather.Result.futures> list;
    private Context mcontext;

    public Adapter(List lists, Context mcontext) {
        this.mcontext = mcontext;
        this.list = lists;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView =  inflater.from(mcontext).inflate(R.layout.activity_adapter, null);
            holder.temp = (TextView) convertView.findViewById(R.id.ftemp);
            holder.weather = (TextView) convertView.findViewById(R.id.fweather);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.week = (TextView) convertView.findViewById(R.id.week);
            holder.wind = (TextView) convertView.findViewById(R.id.wind);
            holder.temp.setText(list.get(position).getTemperature());
            holder.weather.setText(list.get(position).getWeather());
            holder.wind.setText(list.get(position).getWind());
            holder.week.setText(list.get(position).getWeek());
            holder.date.setText(list.get(position).getDate());
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.temp.setText(list.get(position).getTemperature());
        holder.weather.setText(list.get(position).getWeather());
        holder.wind.setText(list.get(position).getWind());
        holder.week.setText(list.get(position).getWeek());
        holder.date.setText(list.get(position).getDate());
        return convertView;
    }
    public final class ViewHolder {
        private TextView date;
        private TextView temp;
        private TextView weather;
        private TextView week;
        private TextView wind;
    }
}