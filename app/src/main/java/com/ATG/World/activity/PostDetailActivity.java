package com.ATG.World.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.ATG.World.R;
import com.ATG.World.fragments.ArticleDetailFragment;
import com.ATG.World.fragments.EducationDetailFragment;
import com.ATG.World.fragments.EventDetailFragment;
import com.ATG.World.fragments.JobDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostDetailActivity extends AppCompatActivity {

    private static final String TAG = PostDetailActivity.class.getSimpleName();
    private String postType;
    private int feedId;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        intent = getIntent();

        postType = intent.getStringExtra("Type");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(postType);
        }

        setupFragment();
    }

    private void setupFragment() {
        progressBar.setVisibility(View.GONE);

        if (postType.equalsIgnoreCase("Article")) {
            ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(getIntent().getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.post_detail_frame, fragment)
                    .commit();

        } else if (postType.equalsIgnoreCase("Education")) {
            EducationDetailFragment fragment = EducationDetailFragment.newInstance(intent.getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.post_detail_frame, fragment)
                    .commit();

        } else if (postType.equalsIgnoreCase("Meetup")) {

        } else if (postType.equalsIgnoreCase("Event")) {
            EventDetailFragment fragment = EventDetailFragment.newInstance(intent.getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.post_detail_frame, fragment)
                    .commit();
        } else if (postType.equalsIgnoreCase("Job")) {
            JobDetailFragment fragment = JobDetailFragment.newInstance(intent.getExtras());
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.post_detail_frame, fragment)
                    .commit();
        } else if (postType.equalsIgnoreCase("Qrious")) {

        }
    }
}