package com.ATG.World.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.PostDetailActivity;
import com.ATG.World.models.ArrPostDatum;
import com.ATG.World.models.Dashboard;
import com.ATG.World.models.GroupPostListResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Chetan on 22-12-2017.
 */

public class SingleGroupPostAdapter extends RecyclerView.Adapter<SingleGroupPostAdapter.CustomHolder> {

    public List<ArrPostDatum> arrPostDatumList;
    public Context context;
    public String group_id ,type;

    public SingleGroupPostAdapter(String group_id,
                                  String type,
                                  Context context,
                                  List<ArrPostDatum> arrPostDatumList) {
        this.group_id = group_id;
        this.type = type;
        this.context = context;
        this.arrPostDatumList = arrPostDatumList;
    }

    @Override
    public int getItemCount() {
        return arrPostDatumList.size();
    }

    @Override
    public CustomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.get_all_layout, parent, false);
        CustomHolder holder = new CustomHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomHolder holder, int position) {
        if(arrPostDatumList != null)
            holder.bind(arrPostDatumList.get(position),context);
    }

    public static class CustomHolder extends RecyclerView.ViewHolder {

//        @BindView(R.id.iv_dashboard_user_img)
//        ImageView postUserProfilePicture;
//        @BindView(R.id.tv_dashboard_user_name)
//        TextView postUserName;@BindView(R.id.tv_dashboard_post_time)
//        TextView postTime;
//        @BindView(R.id.iv_get_all)
//        ImageView postImage;
//        @BindView(R.id.tv_post_type)
//        TextView postType;
//        @BindView(R.id.tv_title_get_all)
//        TextView postTitle;
//        @BindView(R.id.tv_likes)
//        TextView postLikes;
//        @BindView(R.id.tv_unlikes)
//        TextView postUnlikes;
//        @BindView(R.id.tv_comments)
//        TextView postComments;
//        @BindView(R.id.tv_share)
//        TextView postShare;

        @BindView(R.id.tv_dashboard_user_name)
        TextView postUserName;
        @BindView(R.id.tv_dashboard_post_time)
        TextView postTime;
        @BindView(R.id.iv_get_all)
        ImageView postImage;
        @BindView(R.id.tv_post_type)
        TextView postType;
        @BindView(R.id.tv_title_get_all)
        TextView postTitle;
        View view;

        public CustomHolder (View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }

        private void bind(ArrPostDatum article,Context context) {
            //setupUserImage(postUserProfilePicture, article);
            setupUserName(postUserName, article);
            //setupPostTime(postTime, article);
            setupPostType(postType, article);
            setupPostImage(postImage, article);
            setupPostTitle(postTitle, article);
//            setupPostLikes(postLikes, article);
//            setupPostUnlikes(postUnlikes, article);
//            setupPostComments(postComments, article);
//            setupPostShares(postShare, article);
            int adapterPos = getAdapterPosition();
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("CLAY | jack1806", "onClick: "+adapterPos);
                    Intent intent = new Intent(context, PostDetailActivity.class);
                    intent.putExtra("Type",article.getType());
                    intent.putExtra("FeedId",article.getId());
                    context.startActivity(intent);
                }
            });
        }

        private void setupUserName(TextView postUserName, ArrPostDatum dashboard) {
            String name = "";
            if(dashboard.getFirstName()!=null)
                name+=dashboard.getFirstName()+" ";
            if(dashboard.getLastName()!=null)
                name+=dashboard.getLastName();
            if(name.equals("") &&
                    dashboard.getUserName()!=null)
                name = dashboard.getUserName().toString();
            postUserName.setText(name);
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

        private void setupPostType(TextView postType, ArrPostDatum dashboard) {
            String type = dashboard.getType();
            if (!TextUtils.isEmpty(type)) {
                postType.setText(type);
            }
        }

        private void setupPostImage(ImageView postImage, ArrPostDatum dashboard) {
        }

        private void setupPostTitle(TextView postTitle, ArrPostDatum dashboard) {
            String title = dashboard.getTitle();
            if (!TextUtils.isEmpty(title)) {
                postTitle.setText(title);
            }
        }

        private void setupPostLikes(TextView postLikes, ArrPostDatum dashboard) {
            int likes = dashboard.getUpvoteCount();
            postLikes.setText(""+likes);

        }

        private void setupPostUnlikes(TextView postUnlikes, ArrPostDatum dashboard) {
            int unlikes = dashboard.getDownvoteCount();
            postUnlikes.setText(""+unlikes);

        }

        private void setupPostComments(TextView postComments, ArrPostDatum dashboard) {
            postComments.setText("0");
        }

        private void setupPostShares(TextView postShare, ArrPostDatum dashboard) {
            postShare.setText("0");
        }

    }
}
