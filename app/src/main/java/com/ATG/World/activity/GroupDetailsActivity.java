package com.ATG.World.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.fragments.ArticleFragment;
import com.ATG.World.fragments.EducationFragment;
import com.ATG.World.fragments.EventsFragment;
import com.ATG.World.fragments.GetAllFragment;
import com.ATG.World.fragments.HomeFragment;
import com.ATG.World.fragments.JobsFragment;
import com.ATG.World.fragments.MeetingsFragment;
import com.ATG.World.fragments.QueriesFragment;
import com.ATG.World.models.GroupDetails;
import com.ATG.World.utilities.GlideApp;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class GroupDetailsActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;
    public GroupDetails groupDetails = new GroupDetails();
    public AppBarLayout appBarLayout;
    public CollapsingToolbarLayout collapsingToolbarLayout;
    public ImageView toolbarGroupIcon;
    public FrameLayout frameLayout;
    public TextView groupTitle;
    public LinearLayout backButton;
    public LinearLayout toolbarHeader;
    public Toolbar toolbar;
    private static final String TAG_HOME = "Home";
    public Boolean expandedBar = true;
    public float alphaHeader = (float) 5.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarHeader = (LinearLayout) findViewById(R.id.header);
        toolbarHeader.setAlpha(alphaHeader);

        appBarLayout = (AppBarLayout) findViewById(R.id.actionbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapseToolbar);

        collapsingToolbarLayout.setTitle("");
        collapsingToolbarLayout.setScrimAnimationDuration(200);

        toolbarGroupIcon = (ImageView) findViewById(R.id.group_detail_icon);

        /*backButton = (LinearLayout) findViewById(R.id.tv_sub_group_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });*/
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if(intent.hasExtra("name")){
            groupDetails.setName(intent.getStringExtra("name"));
            groupDetails.setId(Integer.parseInt(intent.getStringExtra("id")));
            groupDetails.setImage(intent.getStringExtra("image"));
            groupDetails.setTagLine(intent.getStringExtra("tag"));

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);

            //groupTitle = (TextView) findViewById(R.id.tv_title);
            //groupTitle.setText(groupDetails.getName());

            ImageView headerImage = (ImageView) findViewById(R.id.image_header);

//            GlideApp.with(this)
//                    .load("http://www.guoguiyan.com/data/out/64/69479271-engineering-wallpapers.jpg")
//                    .into(headerImage);

            appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                    if(Math.abs(verticalOffset) > 100){
                        expandedBar = false;
                        collapsingToolbarLayout.setTitle(groupDetails.getName());
                        invalidateOptionsMenu();
                    }
                    else{
                        expandedBar = true;
                        collapsingToolbarLayout.setTitle("");
                        invalidateOptionsMenu();
                    }
                }
            });

            setupViewPager(viewPager);

            tabLayout.setupWithViewPager(viewPager);

            /*frameLayout = (FrameLayout) findViewById(R.id.post_content);

            Fragment fragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.post_content, fragment, TAG_HOME);
            fragmentTransaction.commitAllowingStateLoss();*/

        }
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new GetAllFragment(), getString(R.string.fragment_get_all));
        viewPagerAdapter.addFragment(new ArticleFragment(), getString(R.string.fragment_article));
        viewPagerAdapter.addFragment(new EducationFragment(), getString(R.string.fragment_education));
        viewPagerAdapter.addFragment(new MeetingsFragment(), getString(R.string.fragment_meetup));
        viewPagerAdapter.addFragment(new EventsFragment(), getString(R.string.fragment_event));
        viewPagerAdapter.addFragment(new QueriesFragment(), getString(R.string.fragment_queries));
        viewPagerAdapter.addFragment(new JobsFragment(), getString(R.string.fragment_jobs));

        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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
