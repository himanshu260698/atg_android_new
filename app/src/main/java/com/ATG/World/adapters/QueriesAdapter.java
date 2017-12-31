package com.ATG.World.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.models.Dashboard;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chetan on 22-12-2017.
 */

public class QueriesAdapter extends BaseAdapter<Dashboard> {

    private FooterViewHolder footerViewHolder;

    public QueriesAdapter() {
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

        final ArticleViewHolder holder = new ArticleViewHolder(view);


        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_footer, parent, false);

        final FooterViewHolder holder = new FooterViewHolder(view);
        return holder;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ArticleViewHolder holder = (ArticleViewHolder) viewHolder;

        final Dashboard queries = getItem(position);
        if (queries != null){
            holder.bind(queries);
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

    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        /*@BindView(R.id.iv_dashboard_user_img)
        ImageView postUserProfilePicture;*/
        @BindView(R.id.tv_dashboard_user_name)
        TextView postUserName;
        /*@BindView(R.id.tv_dashboard_post_time)
        TextView postTime;*/
        @BindView(R.id.iv_get_all)
        ImageView postImage;
        @BindView(R.id.tv_post_type)
        TextView postType;
        @BindView(R.id.tv_title_get_all)
        TextView postTitle;
        @BindView(R.id.tv_likes)
        TextView postLikes;
        @BindView(R.id.tv_unlikes)
        TextView postUnlikes;
        @BindView(R.id.tv_comments)
        TextView postComments;
        @BindView(R.id.tv_share)
        TextView postShare;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Dashboard qrious) {
            //setupUserImage(postUserProfilePicture, qrious);
            setupUserName(postUserName, qrious);
            //setupPostTime(postTime, qrious);
            setupPostType(postType, qrious);
            setupPostImage(postImage, qrious);
            setupPostTitle(postTitle, qrious);
            setupPostLikes(postLikes, qrious);
            setupPostUnlikes(postUnlikes, qrious);
            setupPostComments(postComments, qrious);
            setupPostShares(postShare, qrious);
            int adapterPos = getAdapterPosition();
        }

        private void setupUserName(TextView postUserName, Dashboard dashboard) {
            int user_id = dashboard.getId();
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

        /*private void setupPostTime(TextView postTime, Dashboard dashboard) {
            String time = dashboard.getStartTime();
            if (!TextUtils.isEmpty(time)) {
                postTime.setText(time);
            }
        }*/

        private void setupPostType(TextView postType, Dashboard dashboard) {
            String type = dashboard.getType();
            if (!TextUtils.isEmpty(type)) {
                postType.setText(type);
            }
        }

        private void setupPostImage(ImageView postImage, Dashboard dashboard) {
        }

        private void setupPostTitle(TextView postTitle, Dashboard dashboard) {
            String title = dashboard.getTitle();
            if (!TextUtils.isEmpty(title)) {
                postTitle.setText(title);
            }
        }

        private void setupPostLikes(TextView postLikes, Dashboard dashboard) {
            int likes = dashboard.getUpvoteCount();
            postLikes.setText(""+likes);

        }

        private void setupPostUnlikes(TextView postUnlikes, Dashboard dashboard) {
            int unlikes = dashboard.getDownvoteCount();
            postUnlikes.setText(""+unlikes);

        }

        private void setupPostComments(TextView postComments, Dashboard dashboard) {
            postComments.setText("0");
        }

        private void setupPostShares(TextView postShare, Dashboard dashboard) {
            postShare.setText("0");
        }

    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.loading_fl)
        FrameLayout loadingFrameLayout;
        @BindView(R.id.error_rl)
        RelativeLayout errorRelativeLayout;
        @BindView(R.id.reload_btn)
        Button reloadButton;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
