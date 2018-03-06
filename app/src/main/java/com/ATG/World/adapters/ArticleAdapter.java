package com.ATG.World.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.ATG.World.R;
import com.ATG.World.models.Dashboard;
import com.ATG.World.utilities.CustomTextView;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chetan on 22-12-2017.
 */

public class ArticleAdapter extends BaseAdapter<Dashboard> {

    private FooterViewHolder footerViewHolder;
    private Context mContext;
    private static boolean isUpvoted = false;
    private static boolean isDownvoted = false;

    public Dashboard dashboard;
    private onUpvoteClickListener onUpvoteClickListener;
    private onDownvoteClickListener onDownvoteClickListener;
    private ArticleAdapter adapter;

    public ArticleAdapter() {
        super();
    }

    public ArticleAdapter(Context context) {
        super();
        mContext = context;
    }

    public ArticleAdapter(Dashboard dashboard) {
        super();
        this.dashboard = dashboard;
    }

    public interface onUpvoteClickListener {
        void onUpvoteClick(CustomTextView upvoted);
    }

    public interface onDownvoteClickListener {
        void onDownvoteClick(CustomTextView upvoted);
    }

    @Override
    public int getItemViewType(int position) {
        return (isLastPosition(position) && isFooterAdded) ? FOOTER : ITEM;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_all_layout, parent, false);

        final ArticleViewHolder holder = new ArticleViewHolder(view, mContext);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = holder.getAdapterPosition();
                if (adapterPos != RecyclerView.NO_POSITION) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(adapterPos, holder.itemView);
                    }
                }
            }
        });

        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_footer, parent, false);

        final FooterViewHolder holder = new FooterViewHolder(view);
        holder.reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onReloadClickListener != null) {
                    onReloadClickListener.onReloadClick();
                }
            }
        });
        return holder;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ArticleViewHolder holder = (ArticleViewHolder) viewHolder;

        final Dashboard article = getItem(position);
        if (article != null) {
            holder.bind(article);
        }
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        FooterViewHolder holder = (FooterViewHolder) viewHolder;
        footerViewHolder = holder;
    }

    @Override
    protected void displayLoadMoreFooter() {
        if (footerViewHolder != null) {
            footerViewHolder.errorRelativeLayout.setVisibility(View.GONE);
            footerViewHolder.loadingFrameLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void displayErrorFooter() {
        if (footerViewHolder != null) {
            footerViewHolder.loadingFrameLayout.setVisibility(View.GONE);
            footerViewHolder.errorRelativeLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void addFooter() {
        isFooterAdded = true;
        add(new Dashboard());
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

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        /*@BindView(R.id.iv_dashboard_user_img)
        ImageView postUserProfilePicture;*/
        @BindView(R.id.tv_dashboard_user_name)
        CustomTextView postUserName;
        /*@BindView(R.id.tv_dashboard_post_time)
        CustomTextView postTime;*/
        @BindView(R.id.iv_get_all)
        ImageView postImage;
        @BindView(R.id.tv_post_type)
        CustomTextView postType;
        @BindView(R.id.tv_title_get_all)
        CustomTextView postTitle;
        /*@BindView(R.id.tv_likes)
        CustomTextView postLikes;
        @BindView(R.id.tv_unlikes)
        CustomTextView postUnlikes;
        @BindView(R.id.tv_comments)
        CustomTextView postComments;
        @BindView(R.id.tv_share)
        CustomTextView postShare;*/
        private int SET_LIKE = 0;
        private int SET_UNLIKE = 1;

        Context context;
        int feedId;

        public ArticleViewHolder(View itemView, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.context = context;
        }


        private void bind(Dashboard article) {
            //setupUserImage(postUserProfilePicture, article);
            setupUserName(postUserName, article);
            //setupPostTime(postTime, article);
            setupPostType(postType, article);
            setupPostImage(postImage, article);
            setupPostTitle(postTitle, article);
            /*setupLikesCount(postLikes, article);
            setupUnLikesCount(postUnlikes, article);
            setupPostComments(postComments, article);
            setupPostShares(postShare, article);*/
            int adapterPos = getAdapterPosition();
            feedId = article.getId();
        }


        /*public void onLikesImageViewClicked() {
            if (isUpvoted) {
                Toast.makeText(context, "Already Upvoted", Toast.LENGTH_SHORT).show();

            } else {
                AtgService atgService = AtgClient.getClient().create(AtgService.class);
                Call<UpvoteDownvoteResponse> call = atgService.setUpvoteDownvote(SET_LIKE, "article", feedId, Integer.parseInt(UserPreferenceManager.getUserId(context)));
                call.enqueue(setPostLike);
            }
        }


        public void onUnLikesImageViewClicked() {

            if (isDownvoted) {
                Toast.makeText(context, "Already Downvoted", Toast.LENGTH_SHORT).show();
            } else {
                AtgService atgService = AtgClient.getClient().create(AtgService.class);
                Call<UpvoteDownvoteResponse> call = atgService.setUpvoteDownvote(SET_UNLIKE, "article", feedId, Integer.parseInt(UserPreferenceManager.getUserId(context)));
                call.enqueue(setPostUnLike);
            }
        }

        public Callback<UpvoteDownvoteResponse> setPostLike = new Callback<UpvoteDownvoteResponse>() {
            @Override
            public void onResponse(Call<UpvoteDownvoteResponse> call, Response<UpvoteDownvoteResponse> response) {
                if (!response.isSuccessful()) {

                }

                UpvoteDownvoteResponse upvoteDownvoteResponse = response.body();
                if (upvoteDownvoteResponse != null) {
                    int status = upvoteDownvoteResponse.getUpvoteCount();
                    if (status == 1) {
                        setIsUpvoted(true);
                        Toast.makeText(context, "Liked Successfully", Toast.LENGTH_LONG).show();
                        postLikes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_blue_24dp, 0, 0, 0);

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
                        setIsDownvoted(true);
                    }
                }
            }

            @Override
            public void onFailure(Call<UpvoteDownvoteResponse> call, Throwable t) {

            }
        };*/

        private void setupUserName(CustomTextView postUserName, Dashboard dashboard) {
            if (!TextUtils.isEmpty(dashboard.getFirstName())) {
                postUserName.setText(dashboard.getFirstName() + " " + dashboard.getLastName());
            }
        }

        /*private void setupUserImage(ImageView postUserProfilePicture, Dashboard dashboard) {
            String profileImageUrl = dashboard.getProfilePicture();
            if (!TextUtils.isEmpty(profileImageUrl)) {
                Glide.with(postUserProfilePicture.getContext())
                        .load(R.string.profile_image_path + profileImageUrl)
                        .into(postUserProfilePicture);
            } else {

            }
        }*/

        /*private void setupPostTime(CustomTextView postTime, Dashboard dashboard) {
            String time = dashboard.getStartTime();
            if (!TextUtils.isEmpty(time)) {
                postTime.setText(time);
            }
        }*/

        private void setupPostType(CustomTextView postType, Dashboard dashboard) {
            String type = dashboard.getType();
            if (!TextUtils.isEmpty(type)) {
                postType.setText(type);
            }
        }

        private void setupPostImage(ImageView postImage, Dashboard dashboard) {
            String imagetitle = dashboard.getImage();
            if (!TextUtils.isEmpty(imagetitle)) {
                Glide.with(postImage.getContext())
                        .load("https://www.atg.world/" + imagetitle)
                        .into(postImage);
            }
        }

        /**
         * This function is used to get title of the article from data and set it in a text view
         *
         * @param postTitle Title of article
         * @param dashboard used to access response of api
         */
        private void setupPostTitle(CustomTextView postTitle, Dashboard dashboard) {
            String title = dashboard.getTitle();
            if (!TextUtils.isEmpty(title)) {
                postTitle.setText(title);
            }
        }

        /*private void setupLikesCount(CustomTextView like, Dashboard detail) {
            int likesCount = detail.getUpvoteCount();
            if (likesCount == 0) {
                like.setText("");
            } else {
                like.setText("" + likesCount);
            }
            if (detail.getUserUpvoteCount() == 1) {
                setIsUpvoted(true);
                like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_blue_24dp, 0, 0, 0);
            }
        }

        private void setupUnLikesCount(CustomTextView unlike, Dashboard detail) {
            int unlikesCount = detail.getDownvoteCount();
            if (unlikesCount == 0) {
                unlike.setText("");
            } else {
                unlike.setText("" + unlikesCount);
            }
            if (detail.getUserDownvoteCount() == 1) {
                setIsDownvoted(true);
            }
        }*/

       /* private void setupPostLikes(CustomTextView tvLike, Dashboard dashboard) {
            int like = dashboard.getUpvoteCount();
            if (like == 0) {
                tvLike.setText("");
            } else {
                int userLike = dashboard.getUserUpvoteCount();
                if (userLike == 1) {
                    tvLike.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_blue_24dp, 0, 0, 0);
                    tvLike.setText("" + like);
                }
                tvLike.setText("" + like);
            }
        }

        private void setupPostUnlikes(CustomTextView postUnlikes, Dashboard dashboard) {
            int unlike = dashboard.getDownvoteCount();
            if (unlike > 0) {
                int userDislikeStatus = dashboard.getUserDownvoteCount();
                postUnlikes.setText("" + unlike);
            } else {
                postLikes.setText("");
            }
        }*/

        private void setupPostComments(CustomTextView postComments, Dashboard dashboard) {
        }

        private void setupPostShares(CustomTextView postShare, Dashboard dashboard) {
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loading_fl)
        FrameLayout loadingFrameLayout;
        @BindView(R.id.error_rl)
        RelativeLayout errorRelativeLayout;
        @BindView(R.id.loading_layout)
        ProgressBar progressBar;
        @BindView(R.id.reload_btn)
        Button reloadButton;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
