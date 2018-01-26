package com.ATG.World.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.ATG.World.R;
import com.ATG.World.fragments.ArticleDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity {

    private static final String TAG = PostDetailActivity.class.getSimpleName();
    private String postType;
    private int feedId;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        postType = intent.getStringExtra("Type");

        Log.d(TAG, "onCreate: " + postType);
        setupFragment();
    }

    private void setupFragment() {
        progressBar.setVisibility(View.GONE);

        if (postType.equals("Article")) {
            ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.post_detail_frame, fragment)
                    .commit();

        } else if (postType == "Education") {

        } else if (postType == "Meetup") {

        } else if (postType == "Event") {

        } else if (postType == "Job") {

        } else if (postType == "Qrious") {

        }
    }
}
