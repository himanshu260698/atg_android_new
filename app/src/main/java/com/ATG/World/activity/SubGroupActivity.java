package com.ATG.World.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.adapters.MyNicheGroupsAdapter;
import com.ATG.World.models.SubGroupDetails;
import com.ATG.World.models.SubGroupResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GlideApp;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubGroupActivity extends AppCompatActivity {

    public ListView listView;
    public LinearLayout linearLayout;
    public TextView tv_description;
    private static final String TAG_MY_GROUPS = "My Groups";
    private static final int NAV_INDEX_MY_GROUPS = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_group);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        LinearLayout back = (LinearLayout) findViewById(R.id.tv_sub_group_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        setSupportActionBar(toolbar);
        listView = (ListView) findViewById(R.id.rlcontainer);
        tv_description = (TextView) findViewById(R.id.tv_description);
        //linearLayout = (LinearLayout) findViewById(R.id.ll_groupcoverphoto);
        Intent intent = getIntent();
        if(intent.hasExtra("group")){
            String groupId = intent.getStringExtra("group");
            String groupName = intent.getStringExtra("name");
            getSupportActionBar().setTitle(groupName+" Niche Groups");
            String desc = "Join " + groupName + " if want to receive updates from all these subgroups. Join a sub-group instead to receive niche updates.";
            tv_description.setText(desc);
            AtgService atgService = AtgClient.getClient().create(AtgService.class);
            Call<SubGroupResponse> subGroupResponseCall =
                    atgService.getSubGroupsData(
                            UserPreferenceManager.getUserId(getBaseContext()),
                            groupId);
            subGroupResponseCall.enqueue(subGroupResponseCallback);
        }
        else
            super.onBackPressed();
    }

    public Callback<SubGroupResponse> subGroupResponseCallback = new Callback<SubGroupResponse>() {
        @Override
        public void onResponse(Call<SubGroupResponse> call, Response<SubGroupResponse> response) {
            List<SubGroupDetails> subGroupDetails = response.body().getArrUserGroups();
            /*String coverUrl = getBaseContext().getString(R.string.WS_GROUP_COVER_IMAGE)
                    + response.body().getSubgroup().get(0).getCoverImg();
            Glide.with(SubGroupActivity.this).asBitmap().load(coverUrl).into(new SimpleTarget<Bitmap>(400,400) {
                @Override
                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    Drawable drawable = new BitmapDrawable(resource);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        linearLayout.setBackground(drawable);
                    }
                }
            });*/

            MyNicheGroupsAdapter myNicheGroupsAdapter
                    = new MyNicheGroupsAdapter(getApplicationContext(),subGroupDetails);
            listView.setAdapter(myNicheGroupsAdapter);
        }

        @Override
        public void onFailure(Call<SubGroupResponse> call, Throwable t) {
            Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("tag",TAG_MY_GROUPS);
        intent.putExtra("index",NAV_INDEX_MY_GROUPS);
        startActivity(intent);
        super.onBackPressed();
    }
}
