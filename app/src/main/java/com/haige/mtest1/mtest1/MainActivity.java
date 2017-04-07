package com.haige.mtest1.mtest1;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haige.mtest1.mtest1.pullto.PulltoRefreshActivity;
import com.haige.mtest1.mtest1.retrofit.RetrofitActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG = "MainActivity的Log信息Error：";
    @BindView(R.id.net)
    TextView net;
    @BindView(R.id.pullto)
    TextView pullto;
    @BindView(R.id.no2)
    TextView no2;
    @BindView(R.id.no21)
    TextView no21;
    @BindView(R.id.no22)
    TextView no22;
    @BindView(R.id.no23)
    TextView no23;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        if (net == null) {
            Toast.makeText(this, "没有绑定成功。", Toast.LENGTH_SHORT).show();
        }
        net.setOnClickListener(this);
        pullto.setOnClickListener(this);
        no2.setOnClickListener(this);
        no21.setOnClickListener(this);
        no22.setOnClickListener(this);
        no23.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.net:
                netConnect();
                break;
            case R.id.pullto:
                Intent intent = new Intent(this, Main2Activity.class);
                startActivity(intent);
                break;
            case R.id.no2:
                retrofit();
                break;
            case R.id.no21:
                Intent intent1 = new Intent(this, PulltoRefreshActivity.class);
                startActivity(intent1);
                break;
            case R.id.no22:
                break;
            case R.id.no23:
                break;
        }
    }

    /**
     * 测试retrofit
     */
    private void retrofit() {
        Intent intent = new Intent(this, RetrofitActivity.class);
        startActivity(intent);
    }

    /**
     * 网络链接状态测试
     */
    private void netConnect() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetworkInfo.isConnected();
        if (isConnected) {
            int type = activeNetworkInfo.getType();
            switch (type) {
                case ConnectivityManager.TYPE_WIFI:
                    Toast.makeText(this, TAG + "网络的类型wifi", Toast.LENGTH_SHORT).show();
                    break;
                case ConnectivityManager.TYPE_MOBILE:
                    Toast.makeText(this, TAG + "网络的类型是手机网络", Toast.LENGTH_SHORT).show();
                    break;
                case ConnectivityManager.TYPE_MOBILE_DUN:
                    Toast.makeText(this, TAG + "网络的类型是手机网络", Toast.LENGTH_SHORT).show();
                    break;
            }
            Toast.makeText(this, TAG + "网络链接了", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, TAG + "网络没有链接。", Toast.LENGTH_SHORT).show();
        }

    }

}
