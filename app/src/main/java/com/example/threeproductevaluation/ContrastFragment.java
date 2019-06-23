package com.example.threeproductevaluation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class ContrastFragment extends BottomFragment {

    private TextView textViewEdit,textView1,textView2,textView3,textView4,textView5;
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5;
    private CheckBox [] checkBoxes;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_contrast, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initWidget();

    }

    private void initWidget() {
        textViewEdit = getActivity().findViewById(R.id.textView_contrast_edit);
        checkBox1 = getActivity().findViewById(R.id.checkBox1);
        checkBox2 = getActivity().findViewById(R.id.checkBox2);
        checkBox3 = getActivity().findViewById(R.id.checkBox3);
        checkBox4 = getActivity().findViewById(R.id.checkBox4);
        checkBox5 = getActivity().findViewById(R.id.checkBox5);
        checkBoxes = new CheckBox[5];
        checkBoxes[0] = checkBox1;
        checkBoxes[1] = checkBox2;
        checkBoxes[2] = checkBox3;
        checkBoxes[3] = checkBox4;
        checkBoxes[4] = checkBox5;
        for(int i=0;i<checkBoxes.length;i++){
            checkBoxes[i].setOnClickListener(null);
        }
        button = getActivity().findViewById(R.id.button_contrast);
        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                boolean [] checkBoxCondition = new boolean[checkBoxes.length];
                for(int i=0;i<checkBoxes.length;i++){
                    checkBoxCondition[i] = false;
                    if(checkBoxes[i].isChecked()){
                        checkBoxCondition[i] = true;
                    }
                }
                Intent intent = new Intent(getActivity(), ParameterComparisonActivity.class);
                intent.putExtra("checkBoxCondition", checkBoxCondition);
                startActivity(intent);
            }
        });

        textView1 = getActivity().findViewById(R.id.textView1);
        textView2 = getActivity().findViewById(R.id.textView2);
        textView3 = getActivity().findViewById(R.id.textView3);
        textView4 = getActivity().findViewById(R.id.textView4);
        textView5 = getActivity().findViewById(R.id.textView5);
    }

}
