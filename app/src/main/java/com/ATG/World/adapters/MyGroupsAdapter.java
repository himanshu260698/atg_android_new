package com.ATG.World.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.models.GroupDetails;
import com.ATG.World.utilities.GlideApp;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by root on 1/5/18.
 */

public class MyGroupsAdapter extends ArrayAdapter {

    Context context;
    List<GroupDetails> groupDetails;
    private TextView mTvGotoGroups, mTv_AddEditTag, mTvInviteFrnds, mTvNichegroups;

    public MyGroupsAdapter(Context context, List<GroupDetails> groupDetails){

        super(context,0,groupDetails);
        this.context = context;
        this.groupDetails = groupDetails;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.my_group_singlerow,parent,false);
        TextView groupName = (TextView) view.findViewById(R.id.tv_grouptitle);
        TextView groupTag = (TextView) view.findViewById(R.id.tv_tagline);
        ImageView groupImage = (ImageView) view.findViewById(R.id.imv_groupprofile);

        //String profile = this.context.getString(R.string.WS_EXPLOREGROUPICON)+groupDetails.get(position).getImage();
        String profile = groupDetails.get(position).getImage();

        GlideApp.with(this.context)
                .load(profile)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_avtar_male)
                .into(groupImage);

        groupName.setText(groupDetails.get(position).getName());
        String tagLine = groupDetails.get(position).getTagLine();
        if(tagLine.length()>0)
            groupTag.setText(tagLine);

        ImageView imageView = (ImageView)view.findViewById(R.id.popup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpWindowDialog(view);
            }
        });

        return view;
    }

    private void popUpWindowDialog(View view) {
        final PopupWindow popup = new PopupWindow(getContext());
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.my_group_popup, null);

        popup.setContentView(layout);
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        mTvGotoGroups = (TextView) layout.findViewById(R.id.tv_gotogroups);
        mTv_AddEditTag = (TextView) layout.findViewById(R.id.tv_addedittag);
        mTvInviteFrnds = (TextView) layout.findViewById(R.id.tv_invitefrnd);
        mTvNichegroups = (TextView) layout.findViewById(R.id.tv_nichegroups);

        mTv_AddEditTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        mTvGotoGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        mTvNichegroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });
        //popup.setAnimationStyle(R.style.OverflowMenuStyle);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        // popup.showAsDropDown(imgdot);
        // popup.showAsDropDown(imgdot, 100, 10, 4);

        popup.showAsDropDown(view,100,10,4);
    }

}
