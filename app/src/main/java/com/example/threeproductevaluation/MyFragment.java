package com.example.threeproductevaluation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

import static android.app.Activity.RESULT_OK;

public class MyFragment extends BottomFragment implements Runnable{
    private final static String TAG = "MyFragment";
    public static final int MESSAGE_WHAT_MY = 1;

    private TextView textViewMyName;
    private ImageView my_set_config;
    private static String strName, strPassword,
            strNameInit = "DefaultName", strPasswordInit = "123";

    private Message message;
    private Handler handler;
    private Bundle bundle_message;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_my, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //获取数据: 用户名和密码
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_set", Activity.MODE_PRIVATE);
        strName = sharedPreferences.getString("name", strNameInit);
        Log.i(TAG, "initWidget: sp strName="+strName);
        strPassword = sharedPreferences.getString("password", strPasswordInit);
        Log.i(TAG, "initWidget: sp strPassword="+strPassword);
        //方便调试
        strName = strNameInit;
        strPassword = strPasswordInit;

        textViewMyName = getActivity().findViewById(R.id.textViewMyName);
        my_set_config = getActivity().findViewById(R.id.my_set_config);

        //子线程启用
        Thread thread = new Thread(this);
        thread.start();
        handler =new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what) {
                    case MESSAGE_WHAT_MY:
                        bundle_message = (Bundle) msg.obj;
                        strName = bundle_message.getString("strName");
                        strPassword = bundle_message.getString("strPassword");
                        textViewMyName.setText("用户名:"+strName);
                        break;
                }
                super.handleMessage(msg);
            }
        };


        textViewMyName.setText("用户名:"+strName);
        my_set_config.setOnClickListener(new ImageView.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MySetActivity.class);
                intent.putExtra("name", strName);
                intent.putExtra("password", strPassword);

                Log.i(TAG, "my_set_config: to MySetActivity");
                //requestCode==1
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    strName = data.getStringExtra("nameSet");
                    strPassword = data.getStringExtra("passwordSet");
                    Log.i(TAG, "onActivityResult:strName="+strName);
                    Log.i(TAG, "onActivityResult:strPassword="+strPassword);
                    textViewMyName.setText("用户名:"+strName);
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_set", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name", strName);
                    editor.putString("password", strPassword);
                    editor.commit();
                    Log.i(TAG, "data has been saved");
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SqlHelper sqlHelper = new SqlHelper();

        String sql = "select * from user where User_ID = 1";
        ResultSet resultSet = sqlHelper.onQuery(sql);
        if(resultSet == null){
            Log.i(TAG, "resultSet == null");
            return;
        }else{
            try {
                if (resultSet.next()) {
                    strName = resultSet.getString("User_Name");
                    Log.i(TAG, "strName="+ strName);
                    strPassword = resultSet.getString("User_Password");
                    Log.i(TAG, "strPassword="+ strPassword);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                strName = "User_ID=1 failed";
                strPassword = strPasswordInit;
                Log.i(TAG, "fail to search where Article_ID=1");
            }

            if(null == strName || "".equals(strName) || "User_ID=1 failed".equals(strName)){
                Log.i(TAG, "User获取失败");
                strName = strNameInit;
            }

            //获取message对象返回主线程;设置标号what：=0
            message = handler.obtainMessage(MESSAGE_WHAT_MY);
            //存储数据
            bundle_message = new Bundle();
            bundle_message.putString("strName", strName);
            bundle_message.putString("strPassword", strPassword);
            message.obj = bundle_message;
            //发送数据
            handler.sendMessage(message);
            Log.i(TAG, "User info has been update in run(son thread)");
        }
        sqlHelper.onFinish();
    }
}
