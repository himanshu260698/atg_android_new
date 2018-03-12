package com.ATG.World.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.adapters.TagsAdapter;
import com.ATG.World.models.FeedDetailResponse;
import com.ATG.World.models.JobDetail;
import com.ATG.World.models.JobDetailResponse;
import com.ATG.World.models.JobPosted_by;
import com.ATG.World.models.PostDetail;
import com.ATG.World.models.PostedBy;
import com.ATG.World.models.Tags;
import com.ATG.World.models.UpvoteDownvoteResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GlideApp;
//import com.ATG.World.utilities.GlideApp;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JobDetailFragment extends Fragment {

    final static String FEED_ID = "";
    private static final String TAG = JobDetailFragment.class.getSimpleName();
    @BindView(R.id.wv_job_description)
    WebView postDescription;
    @BindView(R.id.job_tags)
    RecyclerView tagsRecycler;
    @BindView(R.id.job_web)
    TextView website;
    @BindView(R.id.job_title)
    TextView title;
    @BindView(R.id.job_company)
    TextView company;
    @BindView(R.id.job_apply_by)
    TextView applyBy;
    @BindView(R.id.job_ext_apply)
    TextView extApply;
    @BindView(R.id.job_location)
    TextView location;
    @BindView(R.id.job_qua)
    TextView qualification;
    @BindView(R.id.job_type)
    TextView type;
    @BindView(R.id.skills)
    TextView skills;
    int setTime=0;
   /*@BindView(R.id.iv_user_comment)
           @Nullable
    ImageView mCommenterPicture;
    @BindView(R.id.et_comment)
    EditText mComment;
    @BindView(R.id.iv_send_comment)
    ImageView mCommentButton;*/


    //@BindView(R.id.tv_comments)
    // TextView mPostComments;
    /*@BindView(R.id.progress_bar)
    ProgressBar progressBar;*/
    private JobDetailResponse jobDetailResponse;
    private JobDetail jobDetail;
    private JobPosted_by postedBy;
    private int feedId;
    private TagsAdapter tagsAdapter;
    private int SET_LIKE = 0;
    private int SET_UNLIKE = 1;

    private static boolean isUpvoted = false;
    private static boolean isDownvoted = false;

    private Unbinder unbinder;

    public JobDetailFragment() {
        // Required empty public constructor
    }

    public static JobDetailFragment newInstance(Bundle extras) {
        JobDetailFragment fragment = new JobDetailFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static JobDetailFragment newInstance() {
        JobDetailFragment fragment = new JobDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        if (getArguments() != null) {
            feedId = (int) getArguments().get("FeedId");
            Log.e("feed_id_job_details:",""+feedId);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_job_detail, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated: ");
        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<JobDetailResponse> call = atgService.getJobDetails("job", feedId, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
        call.enqueue(fetchJobDetails);
        tagsAdapter = new TagsAdapter();
    }

    public Callback<JobDetailResponse> fetchJobDetails = new Callback<JobDetailResponse>() {
        @Override
        public void onResponse(Call<JobDetailResponse> call, Response<JobDetailResponse> response) {
            Log.d(TAG, "onResponse: ");
            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if (responseCode == 504) {

                }
                return;
            }

            jobDetail = response.body();
            if (jobDetail != null) {
                jobDetail = response.body().getJobDetail();
                List<Tags> tagsList = jobDetail.getArr_tag();
                if (tagsList != null) {
                    if (tagsList.size() > 0) {
                        tagsAdapter.addAll(tagsList);

                    }
                }
                //tagsRecycler.setAdapter(tagsAdapter);
                setData();
            }
        }

        @Override
        public void onFailure(Call<JobDetailResponse> call, Throwable t) {
            Log.e("job_details","failure");
            //setupPostDescription(postDescription, postDetail);
        }
    };



    public void setData() {
        //  progressBar.setVisibility(View.GONE);
        //setupPostCoverImage(mCoverPicture, postDetail);
        setupTitle(title,jobDetail);
        setupPostDescription(postDescription, jobDetail);
        setupWebsite(website,jobDetail);
        setupLocation(location,jobDetail);
        setupApplyBy(applyBy,jobDetail);
        setupExtApply(extApply,jobDetail);
        setupCompany(company,jobDetail);
        setupQualification(qualification,jobDetail);
        setupType(type,jobDetail);
        setupSkills(skills,jobDetail);
        setupTags();
    }
    private void setupCompany(TextView company,JobDetail jobDetail)
    {
        company.setText(jobDetail.getCmp_name());
    }
    private void setupTitle(TextView title,JobDetail postDetail)
    {
        title.setText(postDetail.getTitle());
    }
    private void setupSkills(TextView skills,JobDetail jobDetail)
    {
        LinearLayout skill_lay;
        if(jobDetail.getSkill().length()!=0)
        {
            skill_lay=(LinearLayout)(getActivity().findViewById(R.id.skills_layout));
            skills.setText(jobDetail.getExternal_link_to_apply());
            skill_lay.setVisibility(View.VISIBLE);
        }
    }
    private void setupExtApply(TextView extApply,JobDetail jobDetail)
    {
        LinearLayout ext_apply_lay;
        if(jobDetail.getExternal_link_to_apply().length()!=0)
        {
            ext_apply_lay=(LinearLayout)(getActivity().findViewById(R.id.job_ext_link_layout));
            extApply.setText(jobDetail.getExternal_link_to_apply());
            ext_apply_lay.setVisibility(View.VISIBLE);
        }
    }
    private void setupType(TextView type,JobDetail jobDetail)
    {
        LinearLayout type_lay;
        if(jobDetail.getEmployment_type().length()!=0)
        {
            type_lay=(LinearLayout)(getActivity().findViewById(R.id.job_type_layout));
            type.setText(jobDetail.getExternal_link_to_apply());
            type_lay.setVisibility(View.VISIBLE);
        }
    }
    private void setupQualification(TextView qualification,JobDetail jobDetail)
    {
        LinearLayout qua_lay;
        if(jobDetail.getPreferred_qualification().length()!=0)
        {
            qua_lay=(LinearLayout)(getActivity().findViewById(R.id.job_qua_layout));
            qualification.setText(jobDetail.getPreferred_qualification());
            qua_lay.setVisibility(View.VISIBLE);
        }
    }
    private void setupLocation(TextView location,JobDetail jobdetail)
    {
        location.setText(jobdetail.getLocation());
    }
    private void setupApplyBy(TextView applyBy,JobDetail jobDetail)
    {
        LinearLayout apply_lay;
        if(jobDetail.getWebsite().length()!=0)
        {
            apply_lay=(LinearLayout)(getActivity().findViewById(R.id.job_apply_layout));
            applyBy.setText(jobDetail.getApplication_deadline());
            apply_lay.setVisibility(View.VISIBLE);
        }
    }
    private void setupWebsite(TextView website, JobDetail postDetail) {
        LinearLayout web_lay;
        if(postDetail.getWebsite().length()!=0)
        {
            web_lay=(LinearLayout)(getActivity().findViewById(R.id.job_web_layout));
            website.setText(postDetail.getWebsite());
            web_lay.setVisibility(View.VISIBLE);
        }
    }



    private void setupPostDescription(WebView description, JobDetail detail) {
        String desc = detail.getDescriptionURL();
        if (!TextUtils.isEmpty(desc)) {
            Log.e("\ndescriptionUrl",""+desc);
            WebSettings webSettings = description.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            description.setWebChromeClient(new WebChromeClient());
            description.setWebViewClient(new WebViewClient());
            postDescription.setVisibility(View.VISIBLE);
            description.loadUrl(desc);
            description.setHorizontalScrollBarEnabled(false);
        }
        else
        {
            Log.e("\ndescription","failure");
        }
    }

    private void setupLikesCount(TextView like, PostDetail detail) {
        int likesCount = detail.getTotal_upvote();
        if (likesCount == 0) {
            like.setText("");
        } else {
            like.setText("" + likesCount);
        }
        /*if (detail.getUserUpvoteCount() == 1){
            like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_blue_24dp, 0, 0, 0);
        }*/
    }

    private void setupUnLikesCount(TextView unlike, PostDetail detail) {
        int unlikesCount = detail.getTotal_downvote();
        if (unlikesCount == 0) {
            unlike.setText("");
        } else {
            unlike.setText("" + unlikesCount);
        }
    }

    private void setupTags() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        tagsRecycler.setLayoutManager(linearLayoutManager);
        tagsRecycler.setItemAnimator(new DefaultItemAnimator());
        tagsRecycler.setAdapter(tagsAdapter);
    }



    public static boolean isUpvoted() {
        return isUpvoted;
    }

    public static void setIsUpvoted(boolean isUp) {
        isUpvoted = isUp;
    }

    public static boolean isDownvoted() {
        return isDownvoted;
    }

    public static void setIsDownvoted(boolean isDown) {
        isDownvoted = isDown;
    }

    /*
    @OnClick(R.id.tv_comments)
    public void onCommentsImageViewClicked(final View view){
        Intent intent = new Intent(getActivity(), CommentsActivity.class);

        Bundle bundle = new Bundle();
        intent.putExtras(bundle);

        startActivity(intent);
    }*/

    @OnClick(R.id.tv_share)
    public void onShareImageViewClicked(final View view){
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        share.putExtra(Intent.EXTRA_SUBJECT,jobDetail.getTitle());
        share.putExtra(Intent.EXTRA_TEXT,jobDetail.getLink());
        startActivity(Intent.createChooser(share,"Share link!"));
    }
}
