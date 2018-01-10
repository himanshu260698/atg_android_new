package com.ATG.World.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.fragments.HomeFragment;
import com.ATG.World.models.GroupDetails;

public class GroupDetailsActivity extends AppCompatActivity {

    public GroupDetails groupDetails = new GroupDetails();
    public FrameLayout frameLayout;
    public TextView groupTitle;
    public LinearLayout backButton;
    public Toolbar toolbar;
    private static final String TAG_HOME = "Home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        backButton = (LinearLayout) findViewById(R.id.tv_sub_group_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        if(intent.hasExtra("name")){
            groupDetails.setName(intent.getStringExtra("name"));
            groupDetails.setId(Integer.parseInt(intent.getStringExtra("id")));
            groupDetails.setImage(intent.getStringExtra("image"));
            groupDetails.setTagLine(intent.getStringExtra("tag"));
            getSupportActionBar().setTitle(groupDetails.getName());

            groupTitle = (TextView) findViewById(R.id.tv_title);
            groupTitle.setText(groupDetails.getName());

            frameLayout = (FrameLayout) findViewById(R.id.post_content);

            Fragment fragment = new HomeFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                    android.R.anim.fade_out);
            fragmentTransaction.replace(R.id.post_content, fragment, TAG_HOME);
            fragmentTransaction.commitAllowingStateLoss();

        }
    }
}
