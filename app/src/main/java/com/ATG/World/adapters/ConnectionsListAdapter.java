package com.ATG.World.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.models.Connection;
import com.ATG.World.utilities.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Chetan on 28-02-2018.
 */

public class ConnectionsListAdapter extends BaseAdapter<Connection> {

    private FooterViewHolder footerViewHolder;

    public ConnectionsListAdapter() {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.followers_item_card, parent, false);

        final ConnectionsViewHolder holder = new ConnectionsViewHolder(view);

        holder.itemView.setOnClickListener(v -> {
            int adapterPos = holder.getAdapterPosition();
            if (adapterPos != RecyclerView.NO_POSITION) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(adapterPos, holder.itemView);
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
        final ConnectionsViewHolder holder = (ConnectionsViewHolder) viewHolder;

        final Connection connection = getItem(position);
        if (connection != null) {
            holder.bind(connection);
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
        add(new Connection());
    }

    public static class ConnectionsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.circleImageViewFollowerProfilePhoto)
        CircleImageView followerProfilePhoto;
        @BindView(R.id.textViewFollowerName)
        TextView followerName;

        public ConnectionsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Connection connection) {
            setupFollowerProfilePhoto(followerProfilePhoto, connection);
            setupFollowerName(followerName, connection);

            int adapterPos = getAdapterPosition();
        }

        private void setupFollowerName(TextView tv, Connection connection) {
            String name = connection.getToFirstName() + " " + connection.getToLastName();

            if (!TextUtils.isEmpty(name)) {
                tv.setText(name);
            }
        }

        private void setupFollowerProfilePhoto(CircleImageView civ, Connection connection) {
            String followerProfilePhoto = connection.getToProfilePicture();

            GlideApp.with(civ.getContext())
                    .load(followerProfilePhoto)
                    .placeholder(R.drawable.ic_avtar_male)
                    .into(civ);
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