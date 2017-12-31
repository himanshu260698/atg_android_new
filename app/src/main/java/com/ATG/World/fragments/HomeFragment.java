package com.ATG.World.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ATG.World.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Chetan on 22-12-2017.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.tl_home)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager_home)
    ViewPager mViewPager;

    private Unbinder unbinder;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new GetAllFragment(), getString(R.string.fragment_get_all));
        viewPagerAdapter.addFragment(new ArticleFragment(), getString(R.string.fragment_article));
        viewPagerAdapter.addFragment(new EducationFragment(), getString(R.string.fragment_education));
        viewPagerAdapter.addFragment(new MeetingsFragment(), getString(R.string.fragment_meetup));
        viewPagerAdapter.addFragment(new EventsFragment(), getString(R.string.fragment_event));
        viewPagerAdapter.addFragment(new QueriesFragment(), getString(R.string.fragment_queries));
        viewPagerAdapter.addFragment(new JobsFragment(), getString(R.string.fragment_jobs));

        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitle.get(position);
        }
    }
}
