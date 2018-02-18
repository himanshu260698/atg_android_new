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
import com.ATG.World.models.FeedDetailResponse;
import com.ATG.World.models.PostDetail;
import com.ATG.World.models.PostedBy;
import com.ATG.World.models.Tags;
import com.ATG.World.models.UpvoteDownvoteResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GlideApp;
//import com.ATG.World.utilities.GlideApp;

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


public class EventDetailFragment extends Fragment {

    final static String FEED_ID = "";
    private static final String TAG = EventDetailFragment.class.getSimpleName();
    @BindView(R.id.iv_cover_post_details)
    ImageView mCoverPicture;
    @BindView(R.id.tv_event_title)
    TextView mEventTitle;
    @BindView(R.id.iv_user_picture)
    ImageView mUserPicture;
    @BindView(R.id.tv_user_name)
    TextView mUserName;
    @BindView(R.id.rv_post_tags)
    RecyclerView tagsRecycler;
    @BindView(R.id.e_profession)
    TextView mProfession;
    @BindView(R.id.con_phone)
    TextView mPhone;
    @BindView(R.id.email)
    TextView mEmail;
    @BindView(R.id.website)
    TextView mWebsite;
    @BindView(R.id.venue)
    TextView mVenue;
    @BindView(R.id.city)
    TextView mCity;
    @BindView(R.id.start_date)
    TextView mStartDate;
    @BindView(R.id.start_time)
    TextView mStartTime;
    @BindView(R.id.end_date)
    TextView mEndDate;
    @BindView(R.id.end_time)
    TextView mEndTime;
    @BindView(R.id.wv_post_description)
    WebView postDescription;
    int setTime=0;
   /*@BindView(R.id.iv_user_comment)
           @Nullable
    ImageView mCommenterPicture;
    @BindView(R.id.et_comment)
    EditText mComment;
    @BindView(R.id.iv_send_comment)
    ImageView mCommentButton;*/

    @BindView(R.id.tv_likes)
    TextView mPostLikes;
    @BindView(R.id.tv_unlikes)
    TextView mPostUnLikes;
    //@BindView(R.id.tv_comments)
    // TextView mPostComments;
    @BindView(R.id.tv_share)
    TextView mPostShare;
    /*@BindView(R.id.progress_bar)
    ProgressBar progressBar;*/
    private FeedDetailResponse feedDetail;
    private PostDetail postDetail;
    private PostedBy postedBy;
    private int feedId;
    private TagsAdapter tagsAdapter;
    private int SET_LIKE = 0;
    private int SET_UNLIKE = 1;

    private static boolean isUpvoted = false;
    private static boolean isDownvoted = false;

    private Unbinder unbinder;

    public EventDetailFragment() {
        // Required empty public constructor
    }

    public static EventDetailFragment newInstance(Bundle extras) {
        EventDetailFragment fragment = new EventDetailFragment();
        fragment.setArguments(extras);
        return fragment;
    }

    public static EventDetailFragment newInstance() {
        EventDetailFragment fragment = new EventDetailFragment();
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
            Log.e("feed_id_event_details:",""+feedId);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_event_detail, container, false);
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
        Call<FeedDetailResponse> call = atgService.getFeedDetails("event", feedId, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
        call.enqueue(fetchPostDetails);
        tagsAdapter = new TagsAdapter();



    }


    public Callback<FeedDetailResponse> fetchPostDetails = new Callback<FeedDetailResponse>() {
        @Override
        public void onResponse(Call<FeedDetailResponse> call, Response<FeedDetailResponse> response) {
            Log.d(TAG, "onResponse: ");
            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if (responseCode == 504) {

                }
                return;
            }

            feedDetail = response.body();
            if (feedDetail != null) {
                postDetail = response.body().getPostDetail();
                List<Tags> tagsList = postDetail.getArr_tag();
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
        public void onFailure(Call<FeedDetailResponse> call, Throwable t) {

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
                        if (postDetail.getTotal_downvote() == 0) {
                            mPostUnLikes.setText("");
                        } else {
                            mPostUnLikes.setText((postDetail.getTotal_downvote() - 1) + "");
                        }
                        setIsDownvoted(false);
                    }
                    mPostLikes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_blue_24dp, 0, 0, 0);
                    mPostLikes.setText(postDetail.getTotal_upvote() + 1 + "");
                    Toast.makeText(getActivity(), "Upvoted Successfully", Toast.LENGTH_LONG).show();
                    setIsUpvoted(true);
                }
            }
        }

        @Override
        public void onFailure(Call<UpvoteDownvoteResponse> call, Throwable t) {
        }
    };

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
                        mPostUnLikes.setText(postDetail.getTotal_downvote() + 1 + "");
                        if (postDetail.getTotal_upvote() == 0) {
                            mPostLikes.setText("");
                        } else {
                            mPostLikes.setText((postDetail.getTotal_upvote() - 1) + "");
                        }

                        mPostLikes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_black_24dp, 0, 0, 0);
                        setIsUpvoted(false);
                    }
                    mPostUnLikes.setText(postDetail.getTotal_downvote() + 1 + "");
                    Toast.makeText(getActivity(), "Downvoted Successfully", Toast.LENGTH_LONG).show();
                    setIsDownvoted(true);
                }
            }
        }

        @Override
        public void onFailure(Call<UpvoteDownvoteResponse> call, Throwable t) {

        }
    };

    public void setData() {
        //  progressBar.setVisibility(View.GONE);
        //setupPostCoverImage(mCoverPicture, postDetail);
        setupPostUserImage(mUserPicture, postDetail);
        setupPostUserName(mUserName, postDetail);
        setupEventTitle(mEventTitle, postDetail);
        setupPostDescription(postDescription, postDetail);
        setupLikesCount(mPostLikes, postDetail);
        setupUnLikesCount(mPostUnLikes, postDetail);
        setupContact(mPhone, postDetail);
        setupEmail(mEmail, postDetail);
        setupWebsite(mWebsite, postDetail);
        setupProfession(mProfession, postDetail);
        setupVenue(mVenue, postDetail);
        setupCity(mCity, postDetail);
        setupStartTime(mStartTime, postDetail);
        setupEndDate(mEndDate, postDetail);
        setupEndTime(mEndTime, postDetail);
        setupStartDate(mStartDate, postDetail);
        setupTags();
    }
    private void setupEventTitle(TextView title,PostDetail postDetail)
    {
        title.setText(postDetail.getTitle());
    }
    private void setupContact(TextView contact, PostDetail postDetail) {
        LinearLayout con_lay;
        if(postDetail.getContact_number().length()!=0)
            contact.setText(postDetail.getContact_number());
        else
        {
            con_lay=(LinearLayout)(getActivity().findViewById(R.id.contact_layout));
            con_lay.setVisibility(View.GONE);
        }

    }
    private void setupEmail(TextView email, PostDetail postDetail) {
        LinearLayout em_lay;
        if(postDetail.getEmail_address().length()!=0)
            email.setText(postDetail.getEmail_address());
        else
        {
            em_lay=(LinearLayout)(getActivity().findViewById(R.id.em_lay));
            em_lay.setVisibility(View.GONE);        }
    }
    private void setupWebsite(TextView website, PostDetail postDetail) {
        LinearLayout web_lay;
        if(postDetail.getWebsite().length()!=0)
            website.setText(postDetail.getWebsite());
        else
        {
            web_lay=(LinearLayout)(getActivity().findViewById(R.id.web_lay));
            web_lay.setVisibility(View.GONE);
        }
    }
    private void setupProfession(TextView profession, PostDetail postDetail) {
        profession.setText(postDetail.getProfession());
    }
    private void setupVenue(TextView venue, PostDetail postDetail) {
        venue.setText(postDetail.getVenue());
    }
    private void setupCity(TextView city, PostDetail postDetail) {
        LinearLayout cit_lay;
        if(postDetail.getUser_city()!=null)
            city.setText(postDetail.getUser_city());
        else
        {
            cit_lay=(LinearLayout)(getActivity().findViewById(R.id.cit_lay));
            cit_lay.setVisibility(View.GONE);
        }
    }
    private void setupStartTime(TextView startTime, PostDetail postDetail) {
        startTime.setText(postDetail.getStart_time());
    }
    private void setupStartDate(TextView startDate, PostDetail postDetail) {
        startDate.setText(postDetail.getStart_date());
    }
    private void setupEndDate(TextView endDate, PostDetail postDetail) {
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd /*HH:mm:ss*/");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date strDate = null,enddate=null;
        try {
            strDate = sdf.parse(postDetail.getStart_date()/*+" "+postDetail.getStart_time()*/);
            enddate=sdf.parse(postDetail.getEnd_date()/*+" "+postDetail.getEnd_time()*/);
            if (enddate.compareTo(strDate)==1) {
                endDate.setText(postDetail.getEnd_date());
                setTime=100;
            }
            else
                endDate.setText("N/A");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void setupEndTime(TextView endTime, PostDetail postDetail) {
        if(setTime==100)
            endTime.setText(postDetail.getEnd_time());
        else
            endTime.setText("N/A");
    }
    private void setupPostCoverImage(ImageView cover, PostDetail detail) {
        String url = getResources().getString(R.string.article_details_image_path) + detail.getProfile_image();
        if (!TextUtils.isEmpty(detail.getProfile_image())) {
            mUserPicture.setVisibility(View.VISIBLE);
            GlideApp.with(cover.getContext())
                    .load(url)
                    .into(cover);
        }
    }

    private void setupPostUserImage(ImageView image, PostDetail detail) {
        mUserPicture.setVisibility(View.VISIBLE);
        String url = getResources().getString(R.string.user_image_path) + "/" + detail.getUser_id_fk() + "/thumb/" + detail.getProfile_picture();
        GlideApp.with(image.getContext())
                .load(url)
                .placeholder(R.drawable.ic_avtar_male)
                .into(image);

    }



    private void setupPostUserName(TextView username, PostDetail detail) {
        String user = detail.getFirst_name();
        if (!TextUtils.isEmpty(user)) {
            username.setText(user + " " + detail.getLast_name());
        }

    }


    private void setupPostDescription(WebView description, PostDetail detail) {
        String desc = detail.getDescriptionURL();
        if (!TextUtils.isEmpty(desc)) {
            WebSettings webSettings = description.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setLoadWithOverviewMode(true);
            description.setWebChromeClient(new WebChromeClient());
            description.setWebViewClient(new WebViewClient());
            postDescription.setVisibility(View.VISIBLE);
            description.loadUrl(desc);
            description.setHorizontalScrollBarEnabled(false);

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

    @OnClick(R.id.tv_likes)
    public void onLikesImageViewClicked() {
        if (isUpvoted) {
            Toast.makeText(getActivity(), "This post is already Upvoted by you.", Toast.LENGTH_LONG).show();
        } else {
            AtgService atgService = AtgClient.getClient().create(AtgService.class);
            Call<UpvoteDownvoteResponse> call = atgService.setUpvoteDownvote(SET_LIKE, "event", feedId, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
            call.enqueue(setPostLike);
        }
    }

    @OnClick(R.id.tv_unlikes)
    public void onUnLikesImageViewClicked(final View view) {

        if (UserPreferenceManager.getUserId(getActivity()).equals(postDetail.getUser_id_fk())) {
            Toast.makeText(getActivity(), "You cannot downvote your own posts.", Toast.LENGTH_LONG).show();
        } else if (isDownvoted) {
            Toast.makeText(getActivity(), "You already downvoted this post.", Toast.LENGTH_LONG).show();
        } else {
            AtgService atgService = AtgClient.getClient().create(AtgService.class);
            Call<UpvoteDownvoteResponse> call = atgService.setUpvoteDownvote(SET_UNLIKE, "event", feedId, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
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
