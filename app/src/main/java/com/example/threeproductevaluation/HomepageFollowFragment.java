package com.example.threeproductevaluation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomepageFollowFragment extends TopFragment implements Runnable, AdapterView.OnItemClickListener{

    private final String TAG = "HomepageFollowFragment";
    private SqlHelper sqlHelper;
    private TextView textView_Top;
    ResultSet resultSet;
    String url = "None";
    String title ="None";

    Message message;
    Handler handler;
    Bundle bundle_message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_homepage_follow, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        textView_Top = getActivity().findViewById(R.id.textView_homepageFollowTop);
        textView_Top.setOnClickListener(new ImageView.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                intent.putExtra("OriginalActivity", "Follow");
                intent.putExtra("url", url);
                startActivity(intent);
            }
        });


        //子线程启用
        Thread thread = new Thread(this);
        thread.start();
        handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case 0:
                        bundle_message = (Bundle) msg.obj;
                        url = bundle_message.getString("url");
                        title = bundle_message.getString("title");
                        textView_Top.setText(title);

                        /*adapter = new ArrayAdapter<String>(MyRateListActivity.this, //若为this则为handler
                                android.R.layout.simple_expandable_list_item_1,
                                updateRateList);
                        myListView.setAdapter(adapter);*/
                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    //子线程
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sqlHelper = new SqlHelper();
        //数据库获取数据
        String sql = "select * from Article where Article_ID = 1 and (Article_Type = 1 or Article_Type = 0)";
        resultSet = sqlHelper.onQuery(sql);
        if(resultSet == null){
            Log.i(TAG, "resultSet == null");
            return;
        }else{
            try {
                if (resultSet.next()) {
                    url = resultSet.getString("Article_Url");
                    Log.i(TAG, "url="+url);
                    title = resultSet.getString("Article_Title");
                    Log.i(TAG, "title="+title);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                url = "ID=1 failed";
                Log.i(TAG, "fail to search where Article_ID=1");
            }

            if(null == url || "".equals(url) || "ID=1 failed".equals(url)){
                Log.i(TAG, "url获取失败");
            }

            //获取message对象返回主线程;设置标号what：=0
            message = handler.obtainMessage(0);
            //存储数据
            bundle_message = new Bundle();
            bundle_message.putString("url", url);
            bundle_message.putString("title", title);
            message.obj = bundle_message;
            //发送数据
            handler.sendMessage(message);
            Log.i(TAG, "rate has been update in run(son thread)");
        }

    }
}
