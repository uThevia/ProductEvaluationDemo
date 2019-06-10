package com.example.threeproductevaluation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ParameterComparisonActivity extends AppCompatActivity {

    private ImageView imageViewReturn;
    Button button_paracontrast1, button_paracontrast2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_comparison);

        initWidget();
    }

    private void initWidget() {
        //返回
        imageViewReturn = findViewById(R.id.imageView_paracontrast_return);
        imageViewReturn.setOnClickListener(new ImageView.OnClickListener(){

            @Override
            public void onClick(View v) {
                //待写: 返回
                finish();
            }
        });
        //参数详解
        button_paracontrast1 = findViewById(R.id.button_paracontrast1);
        button_paracontrast1.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
        //生成图表
        button_paracontrast2 = findViewById(R.id.button_paracontrast2);
        button_paracontrast2.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });

    }
}
