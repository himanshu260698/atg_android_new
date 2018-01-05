package com.ATG.World.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by username on 4/4/17.
 */

public class MyGroupFragment extends Fragment {
    private View mview;
    private ListView mListview;
    private ArrayList mArraylist;
    private String mStrRowIndex;
    private int Tagindex;
    private String TagID, Groupname;
    private Dialog dialog;
    private String mStrUpdateTag;
    private String mStrGetMyGroup;
    private ArrayList<HashMap<String, String>> mArrayGroupdetails;
    private TextView mTvGotoGroups, mTv_AddEditTag, mTvInviteFrnds, mTvNichegroups;
    private TextView mTvTile;
    private FloatingActionButton mMenu;
    public ImageView optionsButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.my_group_fragment, container, false);
        //setUI();
        //wsGetmygroup();
        View testView = mview.findViewById(R.id.firstEngg);
        optionsButton = (ImageView)testView.findViewById(R.id.popup);
        optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpWindowDialog();
            }
        });
        return mview;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void setUI() {
        mListview = (ListView) mview.findViewById(R.id.lv_mygrouplist);
        mArraylist = new ArrayList();
        mArrayGroupdetails = new ArrayList<>();
    }

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
        //View newsItemView = mListview.getChildAt(Integer.parseInt(mStrRowIndex));
        //ImageView imgdot = (ImageView) newsItemView.findViewById(R.id.popup);

        mTv_AddEditTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
                //TagDialogue();
            }
        });
        mTvGotoGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent groupdetails = new Intent(getActivity(), Groupdetails_Activit.class);
                //groupdetails.putExtra("Group_id", TagID);
                //groupdetails.putExtra("Eventtype", "0");
                //startActivity(groupdetails);
                popup.dismiss();
            }
        });
        mTvNichegroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent subgroup = new Intent(getActivity(), SubgroupActivity.class);
                Bundle data = new Bundle();

                data.putString("Groupid", TagID);
                data.putString("Name", Groupname);
                data.putString("Flag", "Mygroup");
                //subgroup.putExtras(data);
                //startActivity(subgroup);
                popup.dismiss();

            }
        });
        //popup.setAnimationStyle();
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        // popup.showAsDropDown(imgdot);
        // popup.showAsDropDown(imgdot, 100, 10, 4);

        popup.showAsDropDown(optionsButton,100,10,4);
    }
}
