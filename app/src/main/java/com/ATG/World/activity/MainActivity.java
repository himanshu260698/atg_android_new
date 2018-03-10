package com.ATG.World.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.fragments.ExploreGroupsFragment;
import com.ATG.World.fragments.HomeFragment;
import com.ATG.World.fragments.MyGroupFragment;
import com.ATG.World.fragments.MyPostFragment;
import com.ATG.World.fragments.NotificationFragment;
import com.ATG.World.fragments.PostArticlePartOne;
import com.ATG.World.fragments.PostArticlePartThree;
import com.ATG.World.fragments.PostArticlePartTwo;
import com.ATG.World.fragments.PostEducationPartOne;
import com.ATG.World.fragments.PostFragment;
import com.ATG.World.fragments.PostJobOne;
import com.ATG.World.fragments.PostJobThree;
import com.ATG.World.fragments.PostJobTwo;
import com.ATG.World.fragments.PostQriousOne;
import com.ATG.World.fragments.PostQriousThree;
import com.ATG.World.fragments.PostQriousTwo;
import com.ATG.World.fragments.PostStageTwoFragment;
import com.ATG.World.fragments.SettingsFragment;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GPSTracker;
import com.ATG.World.utilities.GlideApp;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PostArticlePartOne.SendGroupData,
        PostArticlePartTwo.SendArticleData, PostQriousOne.SendQriousGroupData, PostQriousTwo.SendQriousData,
        PostJobOne.SendJobGroupData, PostJobTwo.SendJobData {

    private View headerLayout;
    @BindView(R.id.layoutFabJob)
    LinearLayout layoutFabjob;
    //  @BindView(R.id.layoutFabMeetup)
    // LinearLayout layoutFabMeetup;
    @BindView(R.id.layoutFabArticle)
    LinearLayout layoutFabArticle;
    @BindView(R.id.layoutFabEducation)
    LinearLayout layoutFabEducation;
    //  @BindView(R.id.layoutFabEvent)
    // LinearLayout layoutFabEvent;
    @BindView(R.id.layoutFabQrious)
    LinearLayout layoutFabQrious;

    private boolean fabExpanded = false;
    private int FlagLocation = 1;


    //private FloatingActionButton fab;
    //private Toolbar toolbar;
    //private NavigationView navigationView;
    //private DrawerLayout drawer;
    private FrameLayout frameLayout;
    private FragmentTransaction fragmentTransaction;


    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    // Index to identify current nav menu item
    public static int navItemIndex = 0;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // Tags used to attach fragments
    private static final String TAG_HOME = "Home";
    private static final String TAG_POST = "Post";
    private static final String TAG_NOTIFICATION = "Notification";
    private static final String TAG_MY_POST = "My Post";
    private static final String TAG_MY_GROUPS = "My Groups";
    private static final String TAG_EXPLORE_GROUPS = "Explore Groups";
    private static final String TAG_LOGOUT = "Logout";
    private static final String TAG_SETTINGS = "Settings";
    private static String CURRENT_TAG = TAG_HOME;
    private boolean mToolBarNavigationListenerIsRegistered = false;
    private Handler mHandler;
    private boolean shouldLoadHomeFragmentOnBackPress = true;
    private boolean isInternetAvailable = false;
    ActionBarDrawerToggle toggle;
    GPSTracker gps;
    private Fragment fragment;
    RxPermissions rxPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        setUI();
        rxPermissions = new RxPermissions(this);
        setSupportActionBar(toolbar);

        closeSubMenusFab();

        mHandler = new Handler();

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        headerLayout = navigationView.getHeaderView(0);
        UpdateNavProfile();

        setUpNavigationView();

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            if (intent.hasExtra("index")) {
                navItemIndex = intent.getIntExtra("index", 0);
                CURRENT_TAG = intent.getStringExtra("tag");
            } else {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
            }
            loadHomeFragment();
        }
        if (FlagLocation == 1) {
            ConnectivityManager cm =
                    (ConnectivityManager) getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();

            isInternetAvailable = netInfo != null && netInfo.isConnectedOrConnecting();
            if (FlagLocation == 1) {
                Location();
            }
        }


        headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().show();
        fab.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_content);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setUI() {

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true) {
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }

        });
    }

    private void UpdateNavProfile() {
        CircleImageView navProfileImage = headerLayout.findViewById(R.id.nav_profile_image);
        TextView navProfilename = headerLayout.findViewById(R.id.nav_profile_name);
        TextView navProfileLoc = headerLayout.findViewById(R.id.nav_profile_loc);

        String profle = getString(R.string.WS_PROFILE_IMAGE_PATH) + UserPreferenceManager.getUserId(MainActivity.this) + "/thumb/" +
                UserPreferenceManager.getUserImage(MainActivity.this);
        Log.e("Image", profle);
        GlideApp.with(MainActivity.this)
                .load(profle)
                .override(80, 80)
                .error(R.drawable.ic_avtar_male)
                .into(navProfileImage);


        navProfilename.setText(UserPreferenceManager.getUserFirstName(this) + " " + UserPreferenceManager.getUserLastName(this));
        if (UserPreferenceManager.getUserLocatione(this).equalsIgnoreCase(""))
            navProfileLoc.setVisibility(View.INVISIBLE);
        else
            navProfileLoc.setText(UserPreferenceManager.getUserLocatione(this));

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 2) {

        }
        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        }
        if (shouldLoadHomeFragmentOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                getSupportActionBar().show();
                return;
            }
        }

        super.onBackPressed();
    }

    public void loadHomeFragment() {
        //selectNavMenu();

        setToolbarTitle();

        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();

            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        /*Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.main_content, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        //If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }*/

        Fragment fragment = getHomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();

        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 0:
                // Home
                return new HomeFragment();

            case 1:
                logOut();
                return null;
            case 2:
                // Notification
                return new NotificationFragment();
            case 3:
                // Call My Posts
                return new NotificationFragment();
            case 4:
                // Call My Groups
                return new MyGroupFragment();
            case 5:
                return new ExploreGroupsFragment();
            case 6:
                logOut();
                return null;

            case 7:
                closeSubMenusFab();
                toggleFab();
                return new SettingsFragment();

            default:
                return new HomeFragment();
        }
    }


    public void Location() {

        gps = new GPSTracker(MainActivity.this, isInternetAvailable);
        rxPermissions
                .request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_NETWORK_STATE)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M

                        Log.d("Permission", "Location: Given");
                    } else {

                        gps = new GPSTracker(MainActivity.this, isInternetAvailable);
                    }
                });


    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(CURRENT_TAG);
    }

    private void setNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    public void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                closeSubMenusFab();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        loadHomeFragment();
                        break;

                    case R.id.nav_post:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_HOME;
                       // loadPostFragment();
                        loadHomeFragment();
                        fab.show();
                        openSubMenusFab();
                        break;

                    case R.id.nav_notification:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_NOTIFICATION;
                        loadHomeFragment();
                        break;

                    case R.id.nav_my_posts:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_MY_POST;
                        loadHomeFragment();
                        break;

                    case R.id.nav_my_groups:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_MY_GROUPS;
                        loadHomeFragment();
                        break;

                    case R.id.nav_explore_groups:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_EXPLORE_GROUPS;
                        loadHomeFragment();
                        break;

                    case R.id.nav_logout:
                        navItemIndex = 6;
                        logOut();
                        return true;

                    case R.id.settings:
                        navItemIndex = 7;
                        CURRENT_TAG = TAG_SETTINGS;
                        loadHomeFragment();
                        break;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);


                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }

    public void hideFAB() {
        fab.hide();
    }

    private void logOut() {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setMessage("Are you sure, you want to logout?");
        mBuilder.setCancelable(true);
        mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity.this, SocialLoginActivity.class);
                UserPreferenceManager.logout(MainActivity.this);
                intent.setClass(MainActivity.this, SocialLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        mBuilder.create();
        mBuilder.show();
    }

    private void closeSubMenusFab() {
        layoutFabjob.setVisibility(View.INVISIBLE);
        //   layoutFabMeetup.setVisibility(View.INVISIBLE);
        layoutFabArticle.setVisibility(View.INVISIBLE);
        layoutFabEducation.setVisibility(View.INVISIBLE);
        //   layoutFabEvent.setVisibility(View.INVISIBLE);
        layoutFabQrious.setVisibility(View.INVISIBLE);
        fab.setImageResource(R.drawable.ic_add_black_24px);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab() {
        layoutFabjob.setVisibility(View.VISIBLE);
        //   layoutFabMeetup.setVisibility(View.VISIBLE);
        layoutFabArticle.setVisibility(View.VISIBLE);
        layoutFabEducation.setVisibility(View.VISIBLE);
        //  layoutFabEvent.setVisibility(View.VISIBLE);
        layoutFabQrious.setVisibility(View.VISIBLE);
        fab.setImageResource(R.drawable.ic_clear_black_24px);
        fabExpanded = true;

        layoutFabArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSubMenusFab();
                fab.hide();
                Fragment fragment = new PostArticlePartOne();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_content, fragment);
                fragmentTransaction.commitAllowingStateLoss();
            }
        });


        /* layoutFabQrious.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            closeSubMenusFab();
            fab.hide();
            PostQriousOne postQriousOne=new PostQriousOne();
            Fragment fragment = postQriousOne;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.main_content, fragment);
            fragmentTransaction.commitAllowingStateLoss();
        });
        */
        layoutFabArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSubMenusFab();
                fab.hide();
                PostArticlePartOne postArticlePartOne = new PostArticlePartOne();
                Fragment fragment = postArticlePartOne;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.main_content, fragment);
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
        layoutFabjob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                closeSubMenusFab();
                fab.hide();
                PostJobOne postJobOne = new PostJobOne();
                Fragment fragment = postJobOne;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.main_content, fragment);
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
        layoutFabQrious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeSubMenusFab();
                fab.hide();
                PostQriousOne postQriousOne = new PostQriousOne();
                Fragment fragment = postQriousOne;
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.main_content, fragment);
                fragmentTransaction.commitAllowingStateLoss();
            }
        });
    }

    public void showBack() {
        toggle.setDrawerIndicatorEnabled(false);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toggle.syncState();
    }

    public void showNButton() {
        toggle.setDrawerIndicatorEnabled(true);
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
        toggle.syncState();
    }

    @Override
    public void onClick(View view) {

    }

    public void changeFrame(Fragment fragment, int id) {
        drawer.closeDrawers();
        toolbar.setTitle(id);
        fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.main_content, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void sendData(List<String> list) {
        Log.d("Inside senddata", "sendData: ");
        PostArticlePartTwo postArticlePartTwo = new PostArticlePartTwo();
        Fragment fragment = postArticlePartTwo;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        postArticlePartTwo.receivedGroupData(list);
    }


    @Override
    public void transferData(String articleID, String title) {
        PostArticlePartThree postArticlePartThree = new PostArticlePartThree();
        Fragment fragment = postArticlePartThree;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        postArticlePartThree.receiveArticleData(articleID, title);
    }

    @Override
    public void sendQriousData(List<String> qlist) {
        Log.d("Inside qrious data", "sendQriousData: ");
        PostQriousTwo postQriousTwo = new PostQriousTwo();
        Fragment fragment = postQriousTwo;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        postQriousTwo.receivedQriousGroupData(qlist);
    }

    @Override
    public void transferQriousData(String articleID, String title) {
        PostQriousThree postQriousThree = new PostQriousThree();
        Fragment fragment = postQriousThree;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        postQriousThree.receiveQriousData(articleID, title);
    }

    private void loadPostFragment() {
        fragment = new PostFragment();

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.main_content, fragment, CURRENT_TAG);
            fragmentTransaction.commitAllowingStateLoss();
            getSupportActionBar().setTitle("Post");

            //Close Fab button..
            toggleFab();
            //Closing drawer on item click
            drawer.closeDrawers();
        }
    }

    public void loadPostSecondStageFragment() {
        fragment = new PostStageTwoFragment();

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.main_content, fragment);
            fragmentTransaction.commitAllowingStateLoss();
            getSupportActionBar().setTitle("Post");
        }
    }

    @Override
    public void sendJobData(List<String> jlist) {
        Log.d("Inside job data", "sendJobData: ");
        PostJobTwo postJobTwo = new PostJobTwo();
        Fragment fragment = postJobTwo;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        postJobTwo.receivedJobGroupData(jlist);
    }

    @Override
    public void transferJobData(String jobID, String location) {
        PostJobThree postJobThree = new PostJobThree();
        Fragment fragment = postJobThree;
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        postJobThree.receiveJobData(jobID, location);
    }
}
