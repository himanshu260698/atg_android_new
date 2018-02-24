package com.ATG.World.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.GroupDetailsActivity;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.activity.SocialLoginActivity;
import com.ATG.World.activity.SubGroupActivity;
import com.ATG.World.models.AddEditDialogueResponse;
import com.ATG.World.models.GroupDetails;
import com.ATG.World.models.JoinLeaveGroupResponse;
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
 * Created by root on 1/5/18.
 */

public class MyGroupsAdapter extends ArrayAdapter {

    Context context;
    List<GroupDetails> groupDetails;
    List<String> parentId;
    String LEAVE_GROUP_STATUS_CODE = "1";
    private static final String TAG_MY_GROUPS = "My Groups";
    private static final int NAV_INDEX_MY_GROUPS = 4;
    private TextView mTvGotoGroups, mTv_AddEditTag, mTvInviteFrnds, mTvNichegroups,mTvLeaveGroup;
    private View secondView, thirdView, fourthView;

    public MyGroupsAdapter(Context context, List<GroupDetails> groupDetails,List<String> parentId){

        super(context,0,groupDetails);
        this.context = context;
        this.groupDetails = groupDetails;
        this.parentId = parentId;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.my_group_singlerow,parent,false);
        TextView groupName = (TextView) view.findViewById(R.id.tv_grouptitle);
        TextView groupTag = (TextView) view.findViewById(R.id.tv_tagline);
        ImageView groupImage = (ImageView) view.findViewById(R.id.imv_groupprofile);

        //String profile = this.context.getString(R.string.WS_EXPLOREGROUPICON)+groupDetails.get(position).getImage();
        String profile = groupDetails.get(position).getImage();
        final String groupId = groupDetails.get(position).getId().toString();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Toast.makeText(context, "Imagine you are viewing "+
                        groupDetails.get(position).getName()+" group.", Toast.LENGTH_SHORT).show();
                        */
                Intent intent = new Intent(getContext(), GroupDetailsActivity.class);
                intent.putExtra("name",groupDetails.get(position).getName());
                intent.putExtra("id",groupDetails.get(position).getId().toString());
                intent.putExtra("image",groupDetails.get(position).getImage());
                intent.putExtra("tag",groupDetails.get(position).getTagLine());
                context.startActivity(intent);
            }
        });

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
                popUpWindowDialog(view,position);
            }
        });

        return view;
    }

    private void popUpWindowDialog(View view, final int position) {
        final PopupWindow popup = new PopupWindow(getContext());
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.my_group_popup, null);

        popup.setContentView(layout);
        popup.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);

        mTv_AddEditTag = (TextView) layout.findViewById(R.id.tv_addedittag);
        mTvInviteFrnds = (TextView) layout.findViewById(R.id.tv_invitefrnd);
        mTvNichegroups = (TextView) layout.findViewById(R.id.tv_nichegroups);
        mTvLeaveGroup = (TextView) layout.findViewById(R.id.tv_leavegroup);

        secondView = layout.findViewById(R.id.second_hr);
        thirdView = layout.findViewById(R.id.third_hr);
        fourthView = layout.findViewById(R.id.fourth_hr);

        mTv_AddEditTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
                TagDialogue(groupDetails.get(position));
            }
        });
        mTvNichegroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
                Intent intent = new Intent(getContext(), SubGroupActivity.class);
                intent.putExtra("group",groupDetails.get(position).getId().toString());
                intent.putExtra("name",groupDetails.get(position).getName().toString());
                intent.putExtra("tag",groupDetails.get(position).getTagLine().toString());
                getContext().startActivity(intent);
                ((MainActivity)getContext()).finish();
            }
        });
        mTvLeaveGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                mBuilder.setMessage("Are you sure, you want to leave the group?");
                mBuilder.setCancelable(true);
                mBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AtgService atgService = AtgClient.getClient().create(AtgService.class);
                        Call<JoinLeaveGroupResponse> groupResponseCall =
                                atgService.joinLeaveGroup(LEAVE_GROUP_STATUS_CODE
                                        ,UserPreferenceManager.getUserId(getContext())
                                        ,groupDetails.get(position).getId().toString());
                        groupResponseCall.enqueue(joinLeaveGroupResponseCall);
                    }
                });
                mBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        popup.dismiss();
                    }
                });
                mBuilder.create();
                mBuilder.show();
            }
        });

        if(parentId.contains(groupDetails.get(position).getId().toString())){
            mTvNichegroups.setVisibility(View.VISIBLE);
            thirdView.setVisibility(View.VISIBLE);
        }

        //popup.setAnimationStyle(R.style.OverflowMenuStyle);
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        // popup.showAsDropDown(imgdot);
        // popup.showAsDropDown(imgdot, 100, 10, 4);

        popup.showAsDropDown(view,100,10,4);
    }

    public Callback<JoinLeaveGroupResponse> joinLeaveGroupResponseCall = new Callback<JoinLeaveGroupResponse>() {
        @Override
        public void onResponse(Call<JoinLeaveGroupResponse> call, Response<JoinLeaveGroupResponse> response) {
            Log.w("onResponse: ", response.body().getMsg());
            Intent intent = new Intent(getContext(),MainActivity.class);
            intent.putExtra("index",NAV_INDEX_MY_GROUPS);
            intent.putExtra("tag",TAG_MY_GROUPS);
            getContext().startActivity(intent);
            ((MainActivity)getContext()).finish();
        }

        @Override
        public void onFailure(Call<JoinLeaveGroupResponse> call, Throwable t) {
            Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
        }
    };

    private void TagDialogue(final GroupDetails groupDetails) {
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        View view = mLayoutInflater.inflate(R.layout.add_edit_dialogue, null);

        dialog.getWindow().setLayout(300, 300);
        dialog.setContentView(view);
        final EditText mEdtEntertag = (EditText) dialog.findViewById(R.id.edt_tag);
        //mEdtForgotPass = (CustomEditText) dialog.findViewById(R.id.edt_forgot_email_id);
        TextView mBtnsave = (TextView) dialog.findViewById(R.id.btn_save);
        TextView mTvRemove = (TextView) dialog.findViewById(R.id.tv_remove_tag_line);
        mTvRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        mBtnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tagdata = mEdtEntertag.getText().toString();
                if (mEdtEntertag.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(context, "Please enter tag.", Toast.LENGTH_SHORT).show();
                } else {
                    AtgService atgService = AtgClient.getClient().create(AtgService.class);
                    Call<AddEditDialogueResponse> dialogueResponseCall
                            = atgService.getTagDialogueResponse(UserPreferenceManager
                                    .getUserId(context),
                                    groupDetails.getId().toString(),Tagdata);
                    Callback<AddEditDialogueResponse> addEditDialogueResponseCallback =
                            new Callback<AddEditDialogueResponse>() {
                                @Override
                                public void onResponse(Call<AddEditDialogueResponse> call, Response<AddEditDialogueResponse> response) {
                                    AddEditDialogueResponse result = response.body();
                                    if(result.getErrorCode().equalsIgnoreCase("0")){
                                        Intent intent = new Intent(getContext(),MainActivity.class);
                                        intent.putExtra("tag",TAG_MY_GROUPS);
                                        intent.putExtra("index",NAV_INDEX_MY_GROUPS);
                                        getContext().startActivity(intent);
                                        ((MainActivity)getContext()).finish();
                                    }
                                    else
                                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<AddEditDialogueResponse> call, Throwable t) {
                                    Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                                }
                            };
                    dialogueResponseCall.enqueue(addEditDialogueResponseCallback);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
