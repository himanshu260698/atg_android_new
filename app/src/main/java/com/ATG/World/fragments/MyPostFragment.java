package com.ATG.World.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ATG.World.R;
import com.ATG.World.adapters.SetMyPostPagerAdapter;

/**
 * Created by Abhimanoj on 23/2/18.
 */

public class MyPostFragment extends Fragment {

    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.mypost_fragment, container, false);

        viewPager = (ViewPager) mView.findViewById(R.id.view);


        TabLayout tabLayout = (TabLayout) mView.findViewById(R.id.my_post_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("QRIOUS"));
        tabLayout.addTab(tabLayout.newTab().setText("ARTICLES"));
        tabLayout.addTab(tabLayout.newTab().setText("EVENTS"));
        tabLayout.addTab(tabLayout.newTab().setText("MEETUPS"));
        tabLayout.addTab(tabLayout.newTab().setText("JOBS"));
        tabLayout.addTab(tabLayout.newTab().setText("EDUCATION"));

        SetMyPostPagerAdapter adapter = new SetMyPostPagerAdapter(getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            public void onPageSelected(int position) {

                tabLayout.setScrollPosition(position, 0f, true);

            }
        });

        return mView;
    }

}



