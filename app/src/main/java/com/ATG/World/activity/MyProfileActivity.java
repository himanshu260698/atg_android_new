package com.ATG.World.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.fragments.ConnectionsListFragment;
import com.ATG.World.fragments.FollowersListFragment;
import com.ATG.World.fragments.FollowingUsersListFragment;
import com.ATG.World.models.UserData;
import com.ATG.World.models.UserProfile;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.civProfilePic)
    CircleImageView profilePicture;
    @BindView(R.id.textViewUserFullName)
    TextView userFullName;
    @BindView(R.id.textViewUserHeadline)
    TextView userHeadline;
    @BindView(R.id.textViewUserProfession)
    TextView userProfession;
    @BindView(R.id.textViewUserEducation)
    TextView userEducation;
    @BindView(R.id.textViewUserLocation)
    TextView userLocation;
    @BindView(R.id.textViewUserBio)
    TextView userBio;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPagerProfile)
    ViewPager mViewPager;
    @BindView(R.id.buttonEditProfile)
    ImageView editProfile;

    private UserData userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        ButterKnife.bind(this);

        String name = UserPreferenceManager.getUserFirstName(this) + " " + UserPreferenceManager.getUserLastName(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(name);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        userFullName.setText(name);

        String profilePictureUrl = getString(R.string.WS_PROFILE_IMAGE_PATH) + UserPreferenceManager.getUserId(this) + "/thumb/" +
                UserPreferenceManager.getUserImage(this);

        GlideApp.with(MyProfileActivity.this)
                .load(profilePictureUrl)
                .error(R.drawable.ic_avtar_male)
                .into(profilePicture);

        setupViewPager(mViewPager);

        mTabLayout.setupWithViewPager(mViewPager);

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<UserProfile> call = atgService.getAccountSettings(Integer.parseInt(UserPreferenceManager.getUserId(this)));
        call.enqueue(fetchAccountSettings);

        editProfile.setOnClickListener(view -> {
            try {
                Intent intent = new Intent(MyProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("editType", "EditIntro");
                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new ConnectionsListFragment(), getString(R.string.connection_label));
        viewPagerAdapter.addFragment(new FollowersListFragment(), getString(R.string.followers_label));
        viewPagerAdapter.addFragment(new FollowingUsersListFragment(), getString(R.string.following_label));

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

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitle.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitle.get(position);
        }
    }

    private void setupProfileDetails(UserData userDetails) {
        // Setup User's Profile Tagline
        if (!TextUtils.isEmpty(userDetails.getTagline())) {
            userHeadline.setText(userDetails.getTagline());
        } else {
            userHeadline.setVisibility(View.GONE);
        }

        // Setup User's Location Details
        if (!TextUtils.isEmpty(userDetails.getLocation())) {
            userLocation.setText(userDetails.getLocation());
        } else {
            userLocation.setVisibility(View.GONE);
        }
        // Setup User's Profile Summary Details
        if (!TextUtils.isEmpty(userDetails.getLocation())) {
            userBio.setText(userDetails.getAboutMe());
        } else {
            userBio.setVisibility(View.GONE);
        }
        // Setup User's Profession Details
        if (!TextUtils.isEmpty(userDetails.getLocation())) {
            userProfession.setText(userDetails.getProfession());
        } else {
            userProfession.setVisibility(View.GONE);
        }
        // Setup User's Education
        userEducation.setVisibility(View.GONE);
    }

    public Callback<UserProfile> fetchAccountSettings = new Callback<UserProfile>() {
        @Override
        public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if (responseCode == 504) {
                }
                return;
            }

            UserProfile userProfileResponse = response.body();
            if (userProfileResponse != null) {
                userProfile = response.body().getArrUData();
                if (userProfile != null) {
                    setupProfileDetails(userProfile);
                }
            }
        }

        @Override
        public void onFailure(Call<UserProfile> call, Throwable t) {

        }
    };

}
