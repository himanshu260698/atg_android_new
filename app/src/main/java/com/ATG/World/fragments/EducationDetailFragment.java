package com.ATG.World.fragments;

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
import com.ATG.World.models.ArrTag;
import com.ATG.World.models.EducationDetailResponse;
import com.ATG.World.models.FeedDetailResponse;
import com.ATG.World.models.EducationDetail;
import com.ATG.World.models.JobDetailResponse;
import com.ATG.World.models.JobPosted_by;
import com.ATG.World.models.PostDetail;
import com.ATG.World.models.PostedBy;
import com.ATG.World.models.Tags;
import com.ATG.World.models.UpvoteDownvoteResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
//import com.ATG.World.utilities.GlideApp;
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


public class EducationDetailFragment extends Fragment {

    final static String FEED_ID = "";
    private static final String TAG = EducationDetailFragment.class.getSimpleName();
    @BindView(R.id.wv_education_description)
    WebView postDescription;
    @BindView(R.id.education_tags)
    RecyclerView tagsRecycler;
    @BindView(R.id.education_title)
    TextView title;
    @BindView(R.id.tv_likes)
    TextView likes;
    @BindView(R.id.tv_unlikes)
    TextView unlikes;
    @BindView(R.id.tv_followers)
    TextView followers;
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
    private EducationDetailResponse educationDetailResponse;
    private EducationDetail educationDetail;
    private JobPosted_by postedBy;
    private int feedId;
    private TagsAdapter tagsAdapter;
    private int SET_LIKE = 0;
    private int SET_UNLIKE = 1;

    private static boolean isUpvoted = false;
    private static boolean isDownvoted = false;

    private Unbinder unbinder;

    public EducationDetailFragment() {
        // Required empty public constructor
    }

    public static EducationDetailFragment newInstance(Bundle extras) {
        EducationDetailFragment fragment = new EducationDetailFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static EducationDetailFragment newInstance() {
        EducationDetailFragment fragment = new EducationDetailFragment();
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
            Log.e("feed_id_edu_details:",""+feedId);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_education_detail, container, false);
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
        Call<EducationDetailResponse> call = atgService.getEducationDetails("education", feedId, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
        call.enqueue(fetchEducationDetails);
        tagsAdapter = new TagsAdapter();
    }

    public Callback<EducationDetailResponse> fetchEducationDetails = new Callback<EducationDetailResponse>() {
        @Override
        public void onResponse(Call<EducationDetailResponse> call, Response<EducationDetailResponse> response) {
            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if (responseCode == 504) {

                }
                return;
            }

            educationDetailResponse = response.body();
            Log.e("Response",educationDetailResponse.toString());
            if (educationDetailResponse != null) {
                educationDetail = educationDetailResponse.getPostDetail();
                if(educationDetail!=null) {
                    List<Tags> tagsList = educationDetail.getArr_tag();
                    if (tagsList != null) {
                        if (tagsList.size() > 0) {
                            tagsAdapter.addAll(tagsList);

                        }
                    }

                    //tagsRecycler.setAdapter(tagsAdapter);
                    setData();
                }
            }
        }

        @Override
        public void onFailure(Call<EducationDetailResponse> call, Throwable t) {
            Log.e("education_details","failure");
            //setupPostDescription(postDescription, postDetail);
        }
    };



    public void setData() {
        //  progressBar.setVisibility(View.GONE);
        //setupPostCoverImage(mCoverPicture, postDetail);
        setupTitle(title,educationDetail);
        setupPostDescription(postDescription, educationDetail);
        setupLikesCount(likes, educationDetail);
        setupUnLikesCount(unlikes, educationDetail);
        setupFollowersCount(followers,educationDetail);
        setupTags();
    }

    private void setupTitle(TextView title,EducationDetail postDetail)
    {
        title.setText(postDetail.getTitle());
    }
    private void setupFollowersCount(TextView tv_follow,EducationDetail educationDetail)
    {
        tv_follow.setText(""+educationDetail.getFollower_count());
    }


    private void setupPostDescription(WebView description, EducationDetail detail) {
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

    private void setupTags() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        tagsRecycler.setLayoutManager(linearLayoutManager);
        tagsRecycler.setItemAnimator(new DefaultItemAnimator());
        tagsRecycler.setAdapter(tagsAdapter);
    }
    private void setupLikesCount(TextView like, EducationDetail detail) {
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

    private void setupUnLikesCount(TextView unlike, EducationDetail detail) {
        int unlikesCount = detail.getTotal_downvote();
        if (unlikesCount == 0) {
            unlike.setText("");
        } else {
            unlike.setText("" + unlikesCount);
        }
    }
    public Callback<UpvoteDownvoteResponse> setPostUnLike = new Callback<UpvoteDownvoteResponse>() {
        @Override
        public void onResponse(Call<UpvoteDownvoteResponse> call, Response<UpvoteDownvoteResponse> response) {
            if (!response.isSuccessful()) {

            }

            UpvoteDownvoteResponse upvoteDownvoteResponse = response.body();
            if (upvoteDownvoteResponse != null) {
                int status = upvoteDownvoteResponse.getDownvoteCount();
                if (status == 1) {
                    if (isUpvoted) {
                        unlikes.setText(educationDetail.getTotal_downvote() + 1 + "");
                        if (educationDetail.getTotal_upvote() == 0) {
                            likes.setText("");
                        } else {
                            likes.setText((educationDetail.getTotal_upvote() - 1) + "");
                        }

                        likes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_black_24dp, 0, 0, 0);
                        setIsUpvoted(false);
                    }
                    unlikes.setText(educationDetail.getTotal_downvote() + 1 + "");
                    Toast.makeText(getActivity(), "Downvoted Successfully", Toast.LENGTH_LONG).show();
                    setIsDownvoted(true);
                }
            }
        }

        @Override
        public void onFailure(Call<UpvoteDownvoteResponse> call, Throwable t) {

        }
    };

    public Callback<UpvoteDownvoteResponse> setPostLike = new Callback<UpvoteDownvoteResponse>() {
        @Override
        public void onResponse(Call<UpvoteDownvoteResponse> call, Response<UpvoteDownvoteResponse> response) {
            if (!response.isSuccessful()) {
                Toast.makeText(getActivity(), "", Toast.LENGTH_LONG).show();
            }

            UpvoteDownvoteResponse upvoteDownvoteResponse = response.body();
            if (upvoteDownvoteResponse != null) {
                int status = upvoteDownvoteResponse.getUpvoteCount();
                if (status == 1) {
                    if (isDownvoted) {
                        if (educationDetail.getTotal_downvote() == 0) {
                            unlikes.setText("");
                        } else {
                            unlikes.setText((educationDetail.getTotal_downvote() - 1) + "");
                        }
                        setIsDownvoted(false);
                    }
                    likes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_blue_24dp, 0, 0, 0);
                    likes.setText(educationDetail.getTotal_upvote() + 1 + "");
                    Toast.makeText(getActivity(), "Upvoted Successfully", Toast.LENGTH_LONG).show();
                    setIsUpvoted(true);
                }
            }
        }

        @Override
        public void onFailure(Call<UpvoteDownvoteResponse> call, Throwable t) {
        }
    };

    @OnClick(R.id.tv_likes)
    public void onLikesImageViewClicked() {
        if (isUpvoted) {
            Toast.makeText(getActivity(), "This post is already Upvoted by you.", Toast.LENGTH_LONG).show();
        } else {
            AtgService atgService = AtgClient.getClient().create(AtgService.class);
            Call<UpvoteDownvoteResponse> call = atgService.setUpvoteDownvote(SET_LIKE, "article", feedId, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
            call.enqueue(setPostLike);
        }
    }

    @OnClick(R.id.tv_unlikes)
    public void onUnLikesImageViewClicked(final View view) {

        if (UserPreferenceManager.getUserId(getActivity()).equals(educationDetail.getUser_id_fk())) {
            Toast.makeText(getActivity(), "You cannot downvote your own posts.", Toast.LENGTH_LONG).show();
        } else if (isDownvoted) {
            Toast.makeText(getActivity(), "You already downvoted this post.", Toast.LENGTH_LONG).show();
        } else {
            AtgService atgService = AtgClient.getClient().create(AtgService.class);
            Call<UpvoteDownvoteResponse> call = atgService.setUpvoteDownvote(SET_UNLIKE, "article", feedId, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
            call.enqueue(setPostUnLike);
        }

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
    }

    @OnClick(R.id.tv_share)
    public void onShareImageViewClicked(final View view){
    }*/
}
