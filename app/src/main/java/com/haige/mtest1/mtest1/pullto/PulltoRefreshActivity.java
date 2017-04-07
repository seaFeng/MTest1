package com.haige.mtest1.mtest1.pullto;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.haige.mtest1.mtest1.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PulltoRefreshActivity extends AppCompatActivity {

    @BindView(R.id.listview1)
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pullto_refresh);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        listview.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends BaseAdapter {
        LayoutInflater inflater = LayoutInflater.from(PulltoRefreshActivity.this);

        @Override
        public int getCount() {
            return 25;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.listview_item,null);
                holder.tv = (TextView) convertView.findViewById(R.id.ac_pullto_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText("当前条目" + position);
            final int position1 = position;
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInfo1("当前条目" + position1);
                }
            });
            return convertView;
        }
    }

    /**
     *  显示条目信息。
     */
    private void showInfo1(String message) {
        new AlertDialog.Builder(this)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("测试条目信息")
                .setMessage(message)
                .show();
    }

    public final class ViewHolder{
        public TextView tv;
    }
}
