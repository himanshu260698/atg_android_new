package com.ATG.World.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GlideApp;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    View headerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NEW post activity here
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerLayout=navigationView.getHeaderView(0);
        UpdateNavProfile();
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
}
