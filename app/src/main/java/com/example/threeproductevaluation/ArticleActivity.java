package com.example.threeproductevaluation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView_return, imageView_refresh, imageView_totop;
    private TextView textView_article_title;
    private WebView webView;
    //退出时间
    private long exitTime = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        initWidget();
    }

    private void initWidget() {

        imageView_return = findViewById(R.id.imageView_article_return);
        imageView_refresh = findViewById(R.id.imageView_article_refresh);
        imageView_totop = findViewById(R.id.imageView_article_totop);
        webView = findViewById(R.id.webView_article);
        textView_article_title = findViewById(R.id.textView_article_title);

        WebSettings settings = webView.getSettings();
        settings.setUseWideViewPort(true);//设定支持viewport
        settings.setLoadWithOverviewMode(true);   //自适应屏幕
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setDisplayZoomControls(false);//隐藏缩放控件
        webView.setInitialScale(25);//设置最小缩放等级为25%
        settings.setSupportZoom(true);//设定支持缩放
        /*
        //设置只缩放字体:
        settings.setTextZoom(int);
        或
        settings.setTextSize(TextSize.LARGER);
        Android自带五个可选字体大小的值：SMALLEST(50%),SMALLER(75%),NORMAL(100%),LARGER(150%), LARGEST(200%)。
        */


        Intent intent = getIntent();
        String originalActivity = intent.getStringExtra("OriginalActivity");
        //网址
        String url = null;
        switch (originalActivity){
            case "Follow":
                url = "http://pc.zol.com.cn/705/7053818.html";
                break;
            case "Recommend":
                url = "http://nb.zol.com.cn/716/7165571.html";
                break;
            case "Rankinglist":
                url = "http://mobile.zol.com.cn/715/7151347.html";
                break;
        }
        webView.loadUrl(url);
        //这里设置获取到的网站title
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                textView_article_title.setText(title);
            }
        });
        //在webview里打开新链接
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView_article_return:
                finish();
                break;
            case R.id.imageView_article_refresh:
                webView.reload();
                break;
            case R.id.imageView_article_totop:
                //滚动到顶部
                webView.setScrollY(0);
                break;
        }
    }
    //按退出直接退出,避免网络重定向的干扰
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出网页",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }

        }
    }


}
