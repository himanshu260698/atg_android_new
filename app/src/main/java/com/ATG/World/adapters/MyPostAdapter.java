package com.ATG.World.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.models.MyPostPojo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Abhimanoj on 28-02-2018.
 */

public class MyPostAdapter extends BaseAdapter<MyPostPojo> {

    private static final String TAG = MyPostAdapter.class.getSimpleName();

    private FooterViewHolder footerViewHolder;

    public MyPostAdapter() {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mypost_singlerow, parent, false);

        final AllViewHolder holder = new AllViewHolder(view);

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
        final AllViewHolder holder = (AllViewHolder) viewHolder;

        final MyPostPojo myPostPojo = getItem(position);
        if (myPostPojo != null) {
            holder.bind(myPostPojo);
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
        add(new MyPostPojo());
    }

    public static class AllViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.imv_openpopup)
        ImageView mImv_openpopup;

        @BindView(R.id.event_type_image)
        ImageView mImv_typeimage;


        @BindView(R.id.ptitle)
        TextView mTvTitle;

        @BindView(R.id.tv_pdesc)
        TextView mTvDescription;

        @BindView(R.id.tv_upvote)
        TextView mTvUpvoteCount;

        @BindView(R.id.tv_downvote)
        TextView mTvDownvoteCount;


        @BindView(R.id.tv_viewcount)
        TextView mTvViewCount;


        @BindView(R.id.layout_date_time_loc)
        LinearLayout mTvVoteBar;


        @BindView(R.id.if_draft_show)
        TextView mTvNotPublish;


        public AllViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(MyPostPojo myPostPojoLocal) {

            mTvTitle.setText(myPostPojoLocal.getFirstName() + " " + myPostPojoLocal.getLastName());
            mTvDescription.setText(myPostPojoLocal.getDescription());
            mTvUpvoteCount.setText(myPostPojoLocal.getUpvoteCount());
            mTvDownvoteCount.setText(myPostPojoLocal.getDownvoteCount());
            mTvViewCount.setText(myPostPojoLocal.getCount());
            if (myPostPojoLocal.getType().equalsIgnoreCase("article")) {
                mImv_typeimage.setImageResource(R.drawable.ic_article);

            } else if (myPostPojoLocal.getType().equalsIgnoreCase("event")) {
                mImv_typeimage.setImageResource(R.drawable.ic_post_event);

            } else if (myPostPojoLocal.getType().equalsIgnoreCase("meetup")) {
                mImv_typeimage.setImageResource(R.drawable.ic_meetup);

            } else if (myPostPojoLocal.getType().equalsIgnoreCase("qrious")) {
                mImv_typeimage.setImageResource(R.drawable.ic_question);
            } else if (myPostPojoLocal.getType().equalsIgnoreCase("job")) {
                mImv_typeimage.setImageResource(R.drawable.ic_post_a_job);
            } else if (myPostPojoLocal.getType().equalsIgnoreCase("education")) {
                mImv_typeimage.setImageResource(R.drawable.ic_education);
            }

            if (myPostPojoLocal.getType().equalsIgnoreCase("0")) {
                mTvVoteBar.setVisibility(View.INVISIBLE);
                mTvNotPublish.setText("Unpublished " + myPostPojoLocal.getType());
                mTvNotPublish.setVisibility(View.VISIBLE);
            } else {
                mTvVoteBar.setVisibility(View.VISIBLE);
                mTvNotPublish.setVisibility(View.INVISIBLE);
            }
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
