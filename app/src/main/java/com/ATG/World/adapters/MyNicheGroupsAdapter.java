package com.ATG.World.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.interfaces.OnItemClickListener;
import com.ATG.World.models.JoinLeaveGroupResponse;
import com.ATG.World.models.SubGroupDetails;
import com.ATG.World.R;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GlideApp;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 1/8/18.
 */

public class MyNicheGroupsAdapter extends ArrayAdapter{

    List<SubGroupDetails> subGroupDetails;
    Context context;
    ImageView imageView;
    String JOIN_GROUP_CODE = "0";
    String LEAVE_GROUP_CODE = "1";
    OnItemClickListener onItemClickListener;

    public MyNicheGroupsAdapter(@NonNull Context context, @NonNull List<SubGroupDetails> subGroupDetails) {
        super(context, 0, subGroupDetails);
        this.context = context;
        //this.onItemClickListener = onItemClickListener;
        this.subGroupDetails = subGroupDetails;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_my_niche_groups_single_row,parent,false);

        TextView groupName = (TextView) view.findViewById(R.id.node_value);
        imageView = (ImageView) view.findViewById(R.id.niche_group_img);
        final TextView joinButton = (TextView) view.findViewById(R.id.join_button_single);
        final TextView leaveButton = (TextView) view.findViewById(R.id.leave_button_single);

        SubGroupDetails subGroup = this.subGroupDetails.get(position);
        //String imgURL = context.getString(R.string.WS_EXPLOREGROUPICON)+subGroup.getIconImg();
        String imgURL = subGroup.getIconImg();


        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        final Call<JoinLeaveGroupResponse> leaveGroupResponseCall = atgService.joinLeaveGroup(
                LEAVE_GROUP_CODE, UserPreferenceManager.getUserId(getContext()),
                subGroup.getId().toString()
        );
        final Call<JoinLeaveGroupResponse> joinGroupResponseCall = atgService.joinLeaveGroup(
                JOIN_GROUP_CODE,UserPreferenceManager.getUserId(getContext()),
                subGroup.getId().toString()
        );

        final Callback<JoinLeaveGroupResponse> joinGroupResponseCallback
                = new Callback<JoinLeaveGroupResponse>() {
            @Override
            public void onResponse(Call<JoinLeaveGroupResponse> call, Response<JoinLeaveGroupResponse> response) {
                if(response.body().getErrorCode().equalsIgnoreCase("0")){
                    joinButton.setVisibility(View.INVISIBLE);
                    subGroupDetails.get(position).setIsJoin("1");
                    leaveButton.setVisibility(View.VISIBLE);
                }
                else
                    Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JoinLeaveGroupResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        };

        final Callback<JoinLeaveGroupResponse> leaveGroupResponseCallback
                = new Callback<JoinLeaveGroupResponse>() {
            @Override
            public void onResponse(Call<JoinLeaveGroupResponse> call, Response<JoinLeaveGroupResponse> response) {
                if(response.body().getErrorCode().equalsIgnoreCase("0")) {
                    leaveButton.setVisibility(View.INVISIBLE);
                    subGroupDetails.get(position).setIsJoin("0");
                    joinButton.setVisibility(View.VISIBLE);
                }
                else
                    Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<JoinLeaveGroupResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        };

        if (subGroup.getIsJoin().equalsIgnoreCase("1")) {
            leaveButton.setVisibility(View.VISIBLE);
            joinButton.setVisibility(View.INVISIBLE);
        }
        else{
            leaveButton.setVisibility(View.INVISIBLE);
            joinButton.setVisibility(View.VISIBLE);
        }

        leaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(joinGroupResponseCall.isExecuted())
                    leaveGroupResponseCall.enqueue(leaveGroupResponseCallback);
            }
        });

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if(joinGroupResponseCall.isExecuted())
                    joinGroupResponseCall.enqueue(joinGroupResponseCallback);
            }
        });


        GlideApp.with(this.context)
                .load(imgURL)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_avtar_male)
                .into(imageView);

        groupName.setText(this.subGroupDetails.get(position).getGroupName());
        return view;
    }

}
