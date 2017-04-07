package com.haige.mtest1.mtest1.retrofit;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;


import com.haige.mtest1.mtest1.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
        //requestDate2();
        // post 数据请求
        requestPost();
    }

    /**
     *  测试post数据请求。
     */
    private void requestPost() {
        MediaType type = MediaType.parse("text/plain; charset=utf-8");
        MediaType typeJson = MediaType.parse("application/json; charset=utf-8");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key","e5d427da62e36f2b5751bb23de1b66eb");
            jsonObject.put("v","1.0");
            jsonObject.put("month","11");
            jsonObject.put("day","10");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(type,"v=1.0&month=1&day=10&key=e5d427da62e36f2b5751bb23de1b66eb");
        RequestBody bodyJson = RequestBody.create(typeJson,jsonObject.toString());

            FormBody formBody = new FormBody.Builder()
                    .add("key","e5d427da62e36f2b5751bb23de1b66eb")
                    .add("v","1.0")
                    .add("month","11")
                    .add("day","10")
                    .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.juheapi.com/japi/")
                .build();
        PostService service = retrofit.create(PostService.class);

        Call<ResponseBody> call = service.getInfo(formBody);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if ( response.isSuccessful()) {
                        String result = response.body().string();
                        text.setText(result);
                        Log.e(TAG,result);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
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

    /**
     *  post 请求
     */
    public interface PostService{
        @POST("toh")
        Call<ResponseBody> getInfo(@Body RequestBody body);
    }
}
