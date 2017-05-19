package com.example.dong.weather;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {
    private EditText et_city;
    private Button btn_submit;
    private TextView sk;
    private TextView today;
    private TextView future_weather;
    private Weather tw=new Weather();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_city = (EditText) findViewById(R.id.et_city);
        btn_submit = (Button) findViewById(R.id.submit);
        sk = (TextView) findViewById(R.id.sk_weather);
        today = (TextView) findViewById(R.id.today_weather);
        future_weather = (TextView) findViewById(R.id.future_weather);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String city = et_city.getText().toString();
                if(!TextUtils.isEmpty(city)){
                    request(city);
                    InputMethodManager imm =(InputMethodManager)getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(et_city.getWindowToken(), 0);
                }
                else{
                    Toast.makeText(MainActivity.this,"城市名为空!错误码203901",Toast.LENGTH_SHORT).show();
                }
            }
        });
        sk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tw.getReason().equals("none")) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("weather", tw);
                    skWeather skweather = new skWeather();
                    skweather.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.parents, skweather).commit();
                }
                else{
                    Toast.makeText(MainActivity.this,"请先输入城市名!",Toast.LENGTH_SHORT).show();
                }
            }
        });
        today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tw.getReason().equals("none")) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("weather", tw);
                    todayWeather todayweather = new todayWeather();
                    todayweather.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.parents, todayweather).commit();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"请先输入城市名!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        future_weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!tw.getReason().equals("none")) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("weather", tw);
                    futureWeather futureweather = new futureWeather();
                    futureweather.setArguments(bundle);
                    getFragmentManager().beginTransaction().replace(R.id.parents, futureweather).commit();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"请先输入城市名!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void request(final String name) {
        String url="http://v.juhe.cn/weather/index";
        OkHttpUtils.get()
                .url(url)
                .addParams("cityname", name)
                .addParams("format","2")
                .addParams("key", "6cab5bc34472ee686d7bc2f4ec2cd96b")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Request request, Exception e)
                    {
                        Toast.makeText(MainActivity.this,"获取失败！请重试",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response)
                    {
                        Gson gson= new Gson();
//                        tw = gson.fromJson(response,todayweather.class);
                      tw = gson.fromJson(response, new TypeToken<Weather>() {}.getType());
//                        todayweather tw = gson.fromJson(response,new TypeToken<todayweather>(){}.getType());
                        Log.d("TAG","_________________"+tw.getError_code().toString()+"________________");
                        if(tw.getError_code().toString().equals("203901"))
                        {
                            Toast.makeText(MainActivity.this,"城市名为空!错误码203901",Toast.LENGTH_SHORT).show();
                        }else if(tw.getError_code().toString().equals("203902"))
                        {
                            Toast.makeText(MainActivity.this,"查询不到该城市!错误码203902",Toast.LENGTH_SHORT).show();
                        }
                        else if(tw.getError_code().toString().equals("203903"))
                        {
                            Toast.makeText(MainActivity.this,"查询出错!错误码203903",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"查询成功!",Toast.LENGTH_SHORT).show();
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("weather",tw);
                            skWeather skweather = new skWeather();
                            skweather.setArguments(bundle);
                            getFragmentManager().beginTransaction().replace(R.id.parents,skweather).commit();
                        }
                    }
                });
    }
}
