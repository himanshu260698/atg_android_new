package com.ATG.World.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;

import com.ATG.World.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostEducationActivity extends AppCompatActivity {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String postType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_education);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Post Education");
        }

    }

}
