package com.example.threeproductevaluation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ActicleAdapter extends ArrayAdapter {

    public ActicleAdapter(Context context, int resource, ArrayList<HashMap<String, String>> list){
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent){
        if(view == null){
            //LayoutInflater从布局文件获得对象；getContext上下文：Context即构造方法输入的context；inflate(布局：R.layout.list_item, 当前布局父类（即列表list）：parent, 是否覆盖根布局：false)
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_article, parent, false);
        }
        Map<String, String > map = (Map<String, String>) getItem(position);
        TextView itemTitle = (TextView) view.findViewById(R.id.itemTitle);
        TextView itemDetail = (TextView) view.findViewById(R.id.itemDetail);

        itemTitle.setText(""+map.get("ArticleTitle"));
        itemDetail.setText("    "+map.get("ArticleDetail"));
        return view;
    }
}
