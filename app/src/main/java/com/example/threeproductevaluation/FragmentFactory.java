package com.example.threeproductevaluation;

import android.support.v4.app.Fragment;

//更方便地创建碎片: 根据 迭代int 获得 fragment实例
public class FragmentFactory {
    public static Fragment createTopFragmentByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case 1:
                fragment = new HomepageFragment();
                break;
            case 2:
                fragment = new FindFragment();
                break;
            case 3:
                fragment = new ContrastFragment();
                break;
            case 4:
                fragment = new MyFragment();
                break;

        }
        return fragment;
    }

    public static Fragment createBottomFragmentByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case 1:
                fragment = new HomepageFollowFragment();
                break;
            case 2:
                fragment = new HomepageRecommendFragment();
                break;
            case 3:
                fragment = new HomePageRankinglistFragment();
                break;
        }
        return fragment;
    }
}
