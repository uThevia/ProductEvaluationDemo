package com.example.threeproductevaluation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;

public class MyFragment extends BottomFragment {
    private final static String TAG = "MyFragment";

    private TextView textViewMyName;
    private ImageView my_set_config;
    private static String strName, strPassword,
            strNameInit = "DefaultName", strPasswordInit = "123";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_my, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        my_set_config = getActivity().findViewById(R.id.my_set_config);
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
        textViewMyName = getActivity().findViewById(R.id.textViewMyName);
        textViewMyName.setText("用户名:"+strName);

        //获取数据: 用户名和密码
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("my_set", Activity.MODE_PRIVATE);
        strName = sharedPreferences.getString("name", strNameInit);
        Log.i(TAG, "initWidget: sp strName="+strName);
        strPassword = sharedPreferences.getString("password", strPasswordInit);
        Log.i(TAG, "initWidget: sp strPassword="+strPassword);
        //方便调试
        strName = strNameInit;
        strPassword = strPasswordInit;
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


}
