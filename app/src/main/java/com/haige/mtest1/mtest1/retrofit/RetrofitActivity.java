package com.haige.mtest1.mtest1.retrofit;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;


import com.haige.mtest1.mtest1.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RetrofitActivity extends AppCompatActivity {
    private final String TAG = "RetrofitActivity的log消息： ";
    private AppCompatTextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        initView();
        // http 测试接口。
        // requestDate1();
        // https 测试接口
        requestDate2();
        // post 数据请求
    }

    /**
     *  https请求网络
     */
    private void requestDate2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com/square/")
                .build();
        Service1 service1 = retrofit.create(Service1.class);
        Call<ResponseBody> call = service1.getInfo();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e(TAG,response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void initView() {
        text = (AppCompatTextView) findViewById(R.id.content);
    }

    /**
     *  http请求网络
     */
    private void requestDate1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.weather.com.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitInterface retrofit1 = retrofit.create(RetrofitInterface.class);
        Call<WeatherBean> call = retrofit1.getWeather("101010100");
        call.enqueue(new Callback<WeatherBean>(){

            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                WeatherBean.WeatherinfoBean weatherinfo = response.body().getWeatherinfo();
                String city = weatherinfo.getCity();
                text.setText(city);
            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {

            }
        });
    }

    /**
     *  retrofit的请求接口
     */
    public interface RetrofitInterface{
        @GET("adat/sk/{cityId}.html")
        Call<WeatherBean> getWeather(@Path("cityId") String cityId);
    }

    /**
     *  https请求服务
     */
    public interface Service1{
        @GET("retrofit/tree/master/retrofit-converters/gson/src/main/java/retrofit2/converter/gson")
        Call<ResponseBody> getInfo();
    }
}
