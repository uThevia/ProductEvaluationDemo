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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class HomepageFollowFragment extends TopFragment implements Runnable, AdapterView.OnItemClickListener{

    private static final String TAG = "HomepageFollowFragment";
    public static final int MESSAGE_WHAT_HOMEPAGE_FOLLOW = 0;

    private ListView listview_Article;

    private ArrayList<HashMap<String, String>> articleList;

    private Message message;
    private Handler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_homepage_follow, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listview_Article = getActivity().findViewById(R.id.listview_Article);

        //子线程启用:从数据库获取信息
        Thread thread = new Thread(this);
        thread.start();
        handler =new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case MESSAGE_WHAT_HOMEPAGE_FOLLOW:
                        articleList = (ArrayList<HashMap<String, String>>) msg.obj;
                        ActicleAdapter acticleAdapter= new ActicleAdapter(getActivity(), R.layout.item_article, articleList);
                        listview_Article.setAdapter(acticleAdapter);
                        break;
                }
                super.handleMessage(msg);
            }
        };
        //列表为空显示空提示的TextView
        listview_Article.setEmptyView(getActivity().findViewById(R.id.textView_articleNone));
        //列表点击事件
        listview_Article.setOnItemClickListener(HomepageFollowFragment.this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HashMap<String, String> map = (HashMap<String, String>) listview_Article.getItemAtPosition(position);
        String articleUrl = map.get("ArticleUrl");

        //跳转到ArticleActivity
        Intent intent = new Intent(getActivity(), ArticleActivity.class);
        intent.putExtra("OriginalActivity", "Follow");
        intent.putExtra("strUrl", articleUrl);
        startActivity(intent);
        Log.i(TAG, "onItemClick: intent:ArticleActivity");
    }

    //子线程
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = null;
        ArticleMySQLManager articleMySQLManager = new ArticleMySQLManager();
        for(ArticleItem articleItem: articleMySQLManager.search()){
            map = new HashMap<String, String>();    //一定在循环内初始化map，否则map之后更新会导致之前加入list的map也会更新（它们是同一个对象）
            map.put("ArticleId", String.valueOf(articleItem.getId()));
            map.put("ArticleUrl", articleItem.getUrl());
            map.put("ArticleTitle", articleItem.getTitle());
            map.put("ArticleDetail", articleItem.getDetail());
            list.add(map);
        }
        message = handler.obtainMessage(MESSAGE_WHAT_HOMEPAGE_FOLLOW);
        message.obj = list;
        handler.sendMessage(message);
        Log.i(TAG, "article info has been update in run(son thread)");

        /*sqlHelper = new SqlHelper();
        //数据库获取数据
        String sql = "select * from Article where Article_ID = 1 and (Article_Type = 1 or Article_Type = 0);";
        resultSet = sqlHelper.onQuery(sql);
        if(resultSet == null){
            Log.i(TAG, "resultSet == null");
            return;
        }else{
            try {
                if (resultSet.next()) {
                    strUrl = resultSet.getString("Article_Url");
                    Log.i(TAG, "strUrl="+ strUrl);
                    strTitle = resultSet.getString("Article_Title");
                    Log.i(TAG, "strTitle="+ strTitle);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                strUrl = "Article_ID=1 failed";
                Log.i(TAG, "fail to search where Article_ID=1");
            }

            if(null == strUrl || "".equals(strUrl) || "Article_ID=1".equals(strUrl)){
                Log.i(TAG, "url获取失败");
                strUrl = "";
            }

            //获取message对象返回主线程;设置标号what：=0
            message = handler.obtainMessage(0);
            //存储数据
            bundle_message = new Bundle();
            bundle_message.putString("strUrl", strUrl);
            bundle_message.putString("strTitle", strTitle);
            message.obj = bundle_message;
            //发送数据
            handler.sendMessage(message);
            Log.i(TAG, "Article info has been update in run(son thread)");
        }
        sqlHelper.onFinish();*/

    }
}
