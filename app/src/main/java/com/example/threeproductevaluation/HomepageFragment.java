package com.example.threeproductevaluation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class HomepageFragment extends BottomFragment {
    private TabLayout mTabTop;
    private List<String> listTabTopString;
    private List<Fragment> listTabTopFragment;
    private ViewPager mViewPagerContent;
    private ContentPagerAdapter contentPagerAdapter;

    private class ContentPagerAdapter extends FragmentPagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return listTabTopFragment.get(position);
        }

        @Override
        public int getCount() {
            return listTabTopString.size();
        }

        //重写 设置顶部标签的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return listTabTopString.get(position);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_homepage, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化
        mTabTop = (TabLayout) getActivity().findViewById(R.id.tablayout_top);
        mViewPagerContent = (ViewPager) getActivity().findViewById(R.id.viewpager_content_child);
        initContent();
        initTab();
    }

    private void initContent(){
        listTabTopString = new ArrayList<>();
        String[] tabTop_text = getResources().getStringArray(R.array.tabTop_text);
        listTabTopFragment = new ArrayList<>();
        for (int i=0; i<tabTop_text.length;i++) {
            listTabTopString.add(tabTop_text[i]);
            Fragment fragment = FragmentFactory.createBottomFragmentByIndex(i+1);
            listTabTopFragment.add(fragment);
        }
        /*嵌套Fragment:activity -> 父fragment -> 子fragment :
        普通库:
        父fragment创建子fragment(设置其布局所在)时获得FragmentManager用getChildFragmentManager()
        子fragment获取加入父fragmentget的manager用FragmentManager()

        v4 support库:
            activity中, getSupportFragmentManager() 返回的是管理activity中fragments的manager
            fragment中, 若此fragment直接加入activity(即为父fragment), getFragmentManager() 返回的把自己加进Activity的manager
                        若此fragment加入父fragment(即为子fragment), getFragmentManager()返回把自己加入父fragment的manager
        总结: getFragmentManager()是本级别管理者, getChildFragmentManager()是下一级别管理者.
        */
        contentPagerAdapter = new ContentPagerAdapter(getChildFragmentManager());
        mViewPagerContent.setAdapter(contentPagerAdapter);
    }

    private void initTab(){
        ViewCompat.setElevation(mTabTop, 10);
        //建立tab和viewpager的联系
        mTabTop.setupWithViewPager(mViewPagerContent);
    }

}
