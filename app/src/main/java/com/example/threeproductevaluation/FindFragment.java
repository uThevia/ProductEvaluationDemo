package com.example.threeproductevaluation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class FindFragment extends BottomFragment {
    private final static String TAG = "FindFragment";

    private Spinner spinnerFindAll;
    private ImageView imageViewFindMore, imageViewFindContent;
    private TextView textViewFindComputer, textViewFindPhone;
    private ImageButton imageButtonFindSearch;
    public static String strSpinner = "全部", strTextView = "电脑";//分别为下拉选项和文本框的选定字符串
    public static String[] spinnerFindAll_text ,
            textViewFind_text ;

    private int [] pictureId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_find, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        spinnerFindAll_text = getResources().getStringArray(R.array.spinnerFindAll_text);
        textViewFind_text = getResources().getStringArray(R.array.textViewFind_text);

        inintViewFind();

    }

    private void inintViewFind() {
        // ## 下拉列表spinnerFindAll
        // 初始化控件
        spinnerFindAll = getActivity().findViewById(R.id.spinnerFindAll);
        // 建立数据源
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapterForSpinnerFindAll=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, spinnerFindAll_text);
        // 绑定 Adapter到控件
        spinnerFindAll.setAdapter(adapterForSpinnerFindAll);
        // 点击事件
        spinnerFindAll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                strSpinner = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(), "你选中的是:"+strSpinner , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                onItemSelected(null ,null ,1, 0);
            }
        });


        // ## Popup menu弹出式菜单：imageViewFindMore
        imageViewFindMore = getActivity().findViewById(R.id.imageViewFindMore);
        imageViewFindMore.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //创建弹出式菜单对象（最低版本11）
                PopupMenu popup = new PopupMenu(getActivity(), v);//第二个参数是绑定的那个view
                //获取菜单填充器
                MenuInflater inflater = popup.getMenuInflater();
                //填充菜单
                inflater.inflate(R.menu.find_more, popup.getMenu());
                //绑定菜单项的点击事件
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.findMore_tablet:
                                strTextView = menuItem.getTitle().toString();
                                Toast.makeText(getActivity(), "你点击了"+strTextView , Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.findMore_cosmetic:
                                strTextView = menuItem.getTitle().toString();
                                Toast.makeText(getActivity(), "你点击了"+strTextView , Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.findMore_furniture:
                                strTextView = menuItem.getTitle().toString();
                                Toast.makeText(getActivity(), "你点击了"+strTextView , Toast.LENGTH_SHORT).show();                                            break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
                //显示(这一行代码不要忘记了)
                popup.show();
            }
        });

        //## TextView
        textViewFindComputer = getActivity().findViewById(R.id.textViewFindComputer);
        textViewFindComputer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                strTextView =((TextView)v).getText().toString();
                Toast.makeText(getActivity(), "你点击了"+strTextView , Toast.LENGTH_SHORT).show();
            }
        });
        textViewFindPhone = getActivity().findViewById(R.id.textViewFindPhone);
        textViewFindPhone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                strTextView =((TextView)v).getText().toString();
                Toast.makeText(getActivity(), "你点击了"+strTextView , Toast.LENGTH_SHORT).show();
            }
        });



        //## imageButtonFindSearch
        pictureId = new int[spinnerFindAll_text.length * textViewFind_text.length];
        for(int i = 0;i< pictureId.length;i++){
            pictureId[i] = 0;
        }


        pictureId[8] = R.mipmap.find_all_tablet;
        pictureId[9] = R.mipmap.find_flagshipstore_tablet;
        pictureId[10] = R.mipmap.find_evaluationvideo_tablet;
        pictureId[11] = R.mipmap.find_comment_tablet;
        pictureId[12] = R.mipmap.find_all_cosmetic;
        pictureId[13] = R.mipmap.find_flagshipstore_cosmetic;
        pictureId[14] = R.mipmap.find_evaluationvideo_cosmetic;
        pictureId[15] = R.mipmap.find_comment_cosmetic;
        pictureId[16] = R.mipmap.find_all_furniture;
        pictureId[17] = R.mipmap.find_flagshipstore_furniture;
        pictureId[18] = R.mipmap.find_evaluationvideo_furniture;
        pictureId[19] = R.mipmap.find_comment_furniture;

        imageViewFindContent = getActivity().findViewById(R.id.imageView_find_content);

        imageButtonFindSearch = getActivity().findViewById(R.id.imageButtonFindSearch);
        imageButtonFindSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int indexSpinner =0 , indexTextview = 0;
                for(indexSpinner = 0; indexSpinner < spinnerFindAll_text.length; indexSpinner++){
                    if(strSpinner.equals(spinnerFindAll_text[indexSpinner])){
                        break;
                    }
                }
                for(indexTextview = 0; indexTextview < textViewFind_text.length ;indexTextview++){
                    if(strTextView.equals(textViewFind_text[indexTextview])){
                        break;
                    }
                }
                int index_pictureId = indexSpinner +  indexTextview *4;
                if(pictureId[index_pictureId] != 0){
                    imageViewFindContent.setImageResource(pictureId[index_pictureId]);
                }
            }
        });
    }


}
