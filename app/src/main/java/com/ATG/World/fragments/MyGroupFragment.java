package com.ATG.World.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.adapters.MyGroupsAdapter;
import com.ATG.World.models.GroupDetails;
import com.ATG.World.models.MyGroupResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by username on 4/4/17.
 */

public class MyGroupFragment extends Fragment {
    private View mview;
    private ListView mListview;
    private TextView mTvGotoGroups, mTv_AddEditTag, mTvInviteFrnds, mTvNichegroups;
    private TextView mTvTile;
    public ImageView imageView;
    public ProgressBar progressBar;
    public ViewGroup container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.my_group_fragment, container, false);
        this.container = container;
        //View view = mview.findViewById(R.id.firstEngg);
        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        mListview = (ListView)mview.findViewById(R.id.lv_mygrouplist);
        progressBar = (ProgressBar)mview.findViewById(R.id.my_group_progress);

        Call<MyGroupResponse> myGroupResponseCall = atgService.getMyGroupsData(UserPreferenceManager.getUserId(getContext()));
        myGroupResponseCall.enqueue(myGroupResponseCallback);

        /*imageView = (ImageView)view.findViewById(R.id.popup);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpWindowDialog();
            }
        });*/
        return mview;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public Callback<MyGroupResponse> myGroupResponseCallback = new Callback<MyGroupResponse>() {
        @Override
        public void onResponse(Call<MyGroupResponse> call, Response<MyGroupResponse> response) {
            if(!response.isSuccessful()){
                Snackbar.make(getView(),"No Internet Connection",3000);
                return;
            }
            if(progressBar!=null) {
                progressBar.setVisibility(View.INVISIBLE);
                MyGroupResponse result = response.body();
                Gson gson = new Gson();
                Log.w("onMyGroupResponse ", gson.toJson(result));
                if (result != null) {
                    if (result.getErrorCode().equalsIgnoreCase("0")) {
                        List<GroupDetails> groupDetailsList = result.getArrMyGroups();
                        List<String> parentId = result.getUserParentGroup();
                        MyGroupsAdapter myGroupsAdapter = new MyGroupsAdapter(getContext(),
                                groupDetailsList,parentId);
                        mListview.setAdapter(myGroupsAdapter);
                    }
                }
            }
        }

        @Override
        public void onFailure(Call<MyGroupResponse> call, Throwable t) {
            Snackbar.make(getView(),"No Internet Connection",3000);
        }
    };


    private void popUpWindowDialog() {
        final PopupWindow popup = new PopupWindow(getActivity());
        View layout = getActivity().getLayoutInflater().inflate(R.layout.my_group_popup, null);

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

        popup.showAsDropDown(imageView,100,10,4);
    }

}