package com.example.threeproductevaluation;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {

    //顶部标签栏
    private TabLayout mTabBottom;
    //顶部标签的名称
    private List<String> listTabBottomString;
    //顶部标签的碎片
    private List<Fragment> listTabButtomFragment;
    //内容部分
    private ViewPager mViewPagerContent;
    //内容部分的适配器
    private MyFragmentPagerAdapter contentPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        //初始化
        mTabBottom = (TabLayout) findViewById(R.id.tablayout_bottom);
        mViewPagerContent = (ViewPager) findViewById(R.id.viewpager_content);
        initContent();
        initTab();
    }

    private void initContent(){
        listTabBottomString = new ArrayList<>();
        String[] tabBottom_text = getResources().getStringArray(R.array.tabBottom_text);
        listTabButtomFragment = new ArrayList<>();
        for (int i=0; i<tabBottom_text.length;i++) {
            listTabBottomString.add(tabBottom_text[i]);
            Fragment fragment = FragmentFactory.createTopFragmentByIndex(i+1);
            listTabButtomFragment.add(fragment);
        }

        contentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPagerContent.setAdapter(contentPagerAdapter);
    }

    private void initTab(){
        ViewCompat.setElevation(mTabBottom, 10);
        //建立tab和viewpager的联系
        mTabBottom.setupWithViewPager(mViewPagerContent);
    }



    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listTabButtomFragment.get(position);
        }

        @Override
        public int getCount() {
            return listTabBottomString.size();
        }

        //重写 设置顶部标签的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return listTabBottomString.get(position);
        }
    }

}
