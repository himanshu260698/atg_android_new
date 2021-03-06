package com.ATG.World.adapters;

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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chetan on 22-12-2017.
 */

public class MeetingsAdapter extends BaseAdapter<Dashboard> {

    private FooterViewHolder footerViewHolder;

    public MeetingsAdapter() {
        super();
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

        final MeetingsViewHolder holder = new MeetingsViewHolder(view);

        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_footer, parent, false);

        final GetAllAdapter.FooterViewHolder holder = new GetAllAdapter.FooterViewHolder(view);
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
        final MeetingsViewHolder holder = (MeetingsViewHolder) viewHolder;

        final Dashboard dashboard = getItem(position);
        if (dashboard != null) {
            holder.bind(dashboard);
        }
    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {
        MeetingsAdapter.FooterViewHolder holder = (MeetingsAdapter.FooterViewHolder) viewHolder;
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

    public static class MeetingsViewHolder extends RecyclerView.ViewHolder {

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

        public MeetingsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Dashboard article) {
            //setupUserImage(postUserProfilePicture, article);
            setupUserName(postUserName, article);
            //setupPostTime(postTime, article);
            setupPostType(postType, article);
            setupPostImage(postImage, article);
            setupPostTitle(postTitle, article);
            /*setupPostLikes(postLikes, article);
            setupPostUnlikes(postUnlikes, article);
            setupPostComments(postComments, article);
            setupPostShares(postShare, article);*/
            int adapterPos = getAdapterPosition();
        }

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
        }

        private void setupPostTitle(CustomTextView postTitle, Dashboard dashboard) {
            String title = dashboard.getTitle();
            if (!TextUtils.isEmpty(title)) {
                postTitle.setText(title);
            }
        }

        /*private void setupPostLikes(CustomTextView likes, Dashboard dashboard) {
            int like = dashboard.getUpvoteCount();
            if (like > 0) {
                int userLike = dashboard.getUserUpvoteCount();
                likes.setText("" + like);
                if (userLike == 1) {
                    likes.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_blue_24dp, 0, 0, 0);
                }
            } else {
                likes.setText("");
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
        }

        private void setupPostComments(CustomTextView postComments, Dashboard dashboard) {
            postComments.setText("0");
        }

        private void setupPostShares(CustomTextView postShare, Dashboard dashboard) {
            postShare.setText("0");
        }*/

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
