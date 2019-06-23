package com.example.threeproductevaluation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MySetActivity extends AppCompatActivity implements Runnable{

    private ImageView imageView_return;
    private EditText editTextName, editTextOldPw, editTextNewPw;
    private Button set;

    private String nameOld = "DefaultName", passwordOld = "123";
    private String str_setName = "", str_setPw = "";


    private final static String TAG = "MySetActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_set);

        imageView_return = findViewById(R.id.imageView_myset_return);
        imageView_return.setOnClickListener(new ImageView.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editTextName = findViewById(R.id.editTextSetName);
        editTextOldPw = findViewById(R.id.editTextOldPw);
        editTextNewPw = findViewById(R.id.editTextNewPw);
        set = findViewById(R.id.buttonSet);

        Intent intent = getIntent();
        nameOld = intent.getStringExtra("name");
        passwordOld = intent.getStringExtra("password");
        editTextName.setText(nameOld);

    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.buttonSet:
                String str_oldPw = editTextOldPw.getText().toString();
                if(!str_oldPw.equals(passwordOld)){
                    Toast.makeText(getApplicationContext(), "密码错误，无法保存设置！",   Toast.LENGTH_SHORT).show();
                    break;
                }
                String str_getName = editTextName.getText().toString();
                String str_getPw = editTextNewPw.getText().toString();
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                if(str_getName.length() > 0){
                    str_setName = str_getName;
                }else{
                    str_setName = nameOld;
                }
                Log.i(TAG, "str_setName = " + str_setName);
                bundle.putString("nameSet", str_setName);
                String str_setPw = null;
                if(str_getPw.length() > 0){
                    str_setPw = str_getPw;
                }else{
                    str_setPw = passwordOld;
                }
                Log.i(TAG, "str_setPw = " + str_setPw);
                bundle.putString("passwordSet", str_setPw);
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();

                //更新数据库
                Thread thread = new Thread(MySetActivity.this);
                thread.start();
                Handler handler =new Handler();

                break;
        }
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        SqlHelper sqlHelper = new SqlHelper();
        String sql = "update user set User_Name = " + str_setName + ", User_Password = "+ str_setPw +" where User_ID = 1;";
        sqlHelper.onUpdate(sql);
        sqlHelper.onFinish();
    }
}
