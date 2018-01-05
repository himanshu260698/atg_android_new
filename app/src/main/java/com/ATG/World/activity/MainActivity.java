package com.ATG.World.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ATG.World.Fragments.MyGroupFragment;
import com.ATG.World.R;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GlideApp;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private View headerLayout;
    private LinearLayout layoutFabjob;
    private LinearLayout layoutFabMeetup;
    private LinearLayout layoutFabArticle;
    private LinearLayout layoutFabEducation;
    private LinearLayout layoutFabEvent;
    private LinearLayout layoutFabQrious;
    private boolean fabExpanded = false;
    private FloatingActionButton fab;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private FrameLayout frameLayout;
    private FragmentTransaction fragmentTransaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUI();
        setSupportActionBar(toolbar);

        closeSubMenusFab();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
        headerLayout=navigationView.getHeaderView(0);
        UpdateNavProfile();
    }
    private void setUI(){
        navigationView =  findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer_layout);
        toolbar =  findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        frameLayout = findViewById(R.id.main_container);
        layoutFabjob=findViewById(R.id.layoutFabJob);
        layoutFabMeetup=findViewById(R.id.layoutFabMeetup);
        layoutFabArticle=findViewById(R.id.layoutFabArticle);
        layoutFabEducation=findViewById(R.id.layoutFabEducation);
        layoutFabEvent=findViewById(R.id.layoutFabEvent);
        layoutFabQrious=findViewById(R.id.layoutFabQrious);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded == true){
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }

        });
    }

    private void UpdateNavProfile() {
        CircleImageView navProfileImage=headerLayout.findViewById(R.id.nav_profile_image);
        TextView navProfilename=headerLayout.findViewById(R.id.nav_profile_name);
        TextView navProfileLoc=headerLayout.findViewById(R.id.nav_profile_loc);

        String profle=getString(R.string.WS_PROFILE_IMAGE_PATH) + UserPreferenceManager.getUserId(MainActivity.this) + "/thumb/" +
                UserPreferenceManager.getUserImage(MainActivity.this);
        Log.e("Image",profle);

        GlideApp.with(MainActivity.this)
                .load(profle)
                .override(80, 80)
                .error(R.drawable.ic_avtar_male)
                .into(navProfileImage);

        navProfilename.setText(UserPreferenceManager.getUserFirstName(this)+" "+UserPreferenceManager.getUserLastName(this));
        if(UserPreferenceManager.getUserLocatione(this).equalsIgnoreCase(""))
            navProfileLoc.setVisibility(View.INVISIBLE);
        else
            navProfileLoc.setText(UserPreferenceManager.getUserLocatione(this));


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.home_nav) {
            
        } else if (id == R.id.post_nav) {

        } else if (id == R.id.connect_nav) {

        } else if (id == R.id.notifications_nav) {

        } else if (id == R.id.logout_nav) {
            logOut();
        } else if (id == R.id.my_groups_nav) {
            changeFrame(new MyGroupFragment(),R.string.my_groups);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                finish();
                startActivity(intent);
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

    private void closeSubMenusFab(){
        layoutFabjob.setVisibility(View.INVISIBLE);
        layoutFabMeetup.setVisibility(View.INVISIBLE);
        layoutFabArticle.setVisibility(View.INVISIBLE);
        layoutFabEducation.setVisibility(View.INVISIBLE);
        layoutFabEvent.setVisibility(View.INVISIBLE);
        layoutFabQrious.setVisibility(View.INVISIBLE);
        fab.setImageResource(R.drawable.ic_add_black_24px);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab(){
        layoutFabjob.setVisibility(View.VISIBLE);
        layoutFabMeetup.setVisibility(View.VISIBLE);
        layoutFabArticle.setVisibility(View.VISIBLE);
        layoutFabEducation.setVisibility(View.VISIBLE);
        layoutFabEvent.setVisibility(View.VISIBLE);
        layoutFabQrious.setVisibility(View.VISIBLE);
        fab.setImageResource(R.drawable.ic_clear_black_24px);
        fabExpanded = true;
    }

    @Override
    public void onClick(View view) {


    }

    public void changeFrame(Fragment fragment, int id){
        toolbar.setTitle(id);
        fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.main_container,fragment);
        fragmentTransaction.commit();
    }


}
