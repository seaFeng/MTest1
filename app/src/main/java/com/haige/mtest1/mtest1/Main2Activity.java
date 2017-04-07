package com.haige.mtest1.mtest1;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private ViewPager viewpager;
    private List<ImageView> advPics;
    private ImageView whiteCricel;
    private ArrayList<ImageView> listCircle = new ArrayList<>();
    private TextView textView;
    //private ImageView moveCricle;
    private final String TAG = "Main2Activity的log信息：";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initDate();
        initView();
    }


    private void initDate() {
        //存放三张图片
        advPics = new ArrayList<>();
        ImageView i1 = new ImageView(this);
        i1.setImageResource(R.mipmap.ic_launcher);
        advPics.add(i1);
        ImageView i2 = new ImageView(this);
        i2.setImageResource(R.mipmap.ic_launcher);
        advPics.add(i2);
        ImageView i3 = new ImageView(this);
        i3.setImageResource(R.mipmap.ic_launcher);
        advPics.add(i3);

        /*// 小圆点。
        circleList = (LinearLayout) findViewById(R.id.circleList);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20,20);
        params.setMargins(10,0,10,0);
        circle1 = new ImageView(this);
        circle1.setLayoutParams(params);
        circle1.setImageResource(R.drawable.bg_circle1);
        circleList.addView(circle1);
        listCircle.add(circle1);

        circle2 = new ImageView(this);
        circle2.setLayoutParams(params);
        circle2.setImageResource(R.drawable.bg_circle1);
        circleList.addView(circle2);
        listCircle.add(circle2);

        circle3 = new ImageView(this);
        circle3.setLayoutParams(params);
        circle3.setImageResource(R.drawable.bg_circle1);
        circleList.addView(circle3);
        listCircle.add(circle3);*/
    }

    private void initView() {
        whiteCricel = (ImageView) findViewById(R.id.white_circel);
        //moveCricle = (ImageView) findViewById(R.id.move_circle);
       textView = (TextView) findViewById(R.id.text111111111);

        viewpager = (ViewPager) findViewById(R.id.viewpage);
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return advPics.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Log.e(TAG,"当前viewpager:" + position);
                container.addView(advPics.get(position),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                return advPics.get(position);
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(advPics.get(position));
            }
        });
        // 设置viewpager的滚动监听。
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.e(TAG,"平移的距离 = " + positionOffset);
                //Log.e(TAG,20*positionOffset + "");
                //moveCricle.scrollBy((int) (20*positionOffset),0);
                textView.scrollBy((int) (-20*positionOffset),0);

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
