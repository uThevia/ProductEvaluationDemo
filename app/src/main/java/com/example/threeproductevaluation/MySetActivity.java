package com.example.threeproductevaluation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MySetActivity extends AppCompatActivity  {

    ImageView imageView_return;
    EditText editTextName, editTextOldPw, editTextNewPw;
    Button set;
    String nameOld = "DefaultName", passwordOld = "12345678";

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
                String str_name = editTextName.getText().toString();
                String str_newPw = editTextNewPw.getText().toString();
                Intent intent=new Intent();
                Bundle bundle = new Bundle();
                if(str_name.length() > 0){
                    bundle.putString("nameSet", str_name);
                    Log.i(TAG, "nameSet = " + str_name);
                }
                if(str_newPw.length() > 0){
                    bundle.putString("passwordSet", str_newPw);
                    Log.i(TAG, "str_newPw = " + str_newPw);
                }else{
                    bundle.putString("passwordSet", passwordOld);
                }
                intent.putExtras(bundle);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
    }
}
