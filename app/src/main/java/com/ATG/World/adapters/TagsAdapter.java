package com.ATG.World.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.models.Tags;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chetan on 18-01-2018.
 */

public class TagsAdapter extends BaseAdapter<Tags> {

    public TagsAdapter() {
        super();
    }

    @Override
    public int getItemViewType(int position) {
        return ITEM;
    }

    @Override
    protected RecyclerView.ViewHolder createHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder createItemViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tags_layout, parent, false);

        final TagsViewHolder holder = new TagsViewHolder(view);

        return holder;
    }

    @Override
    protected RecyclerView.ViewHolder createFooterViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    protected void bindHeaderViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void bindItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final TagsViewHolder holder = (TagsViewHolder) viewHolder;

        final Tags tags = getItem(position);
        if (tags != null) {
            holder.bind(tags);
        }

    }

    @Override
    protected void bindFooterViewHolder(RecyclerView.ViewHolder viewHolder) {

    }

    @Override
    protected void displayLoadMoreFooter() {

    }

    @Override
    protected void displayErrorFooter() {

    }

    @Override
    public void addFooter() {

    }

    public static class TagsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_tags)
        TextView tvTags;

        public TagsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Tags tags) {
            setupTags(tvTags, tags);
        }

        private void setupTags(TextView tag, Tags tags) {
            String tagName = tags.getTagName();
            if (!TextUtils.isEmpty(tagName)) ;
            {
                try {
                    tag.setText(tagName);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
