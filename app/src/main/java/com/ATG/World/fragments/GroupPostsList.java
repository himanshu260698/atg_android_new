package com.ATG.World.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.adapters.SingleGroupPostAdapter;
import com.ATG.World.models.ArrPostDatum;
import com.ATG.World.models.GroupPostListResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 1/23/18.
 */

public class GroupPostsList extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    View emptyView;
    @BindView(R.id.error_ll)
    LinearLayout errorLinearLayout;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.btn_reload)
    TextView mReloadButton;
    @BindView(R.id.progressBar_load)
    ProgressBar mProgressBar;

    public String type;
    public String group_id;
    private Unbinder unbinder;
    private LinearLayoutManager mLinearLayoutManager;
    public List<ArrPostDatum> arrPostDatumList;
    public int page_number = 0;

    public GroupPostsList(String type,
                          String group_id){
        this.group_id = group_id;
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_get_all, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Log.d("CLAY|jack1806",UserPreferenceManager.getUserId(getContext())+"::"
                +this.group_id+"::"+this.type+"::"+page_number);
        Call<GroupPostListResponse> postListResponseCall =
                atgService.getGroupPosts(UserPreferenceManager.getUserId(getContext()),
                        this.group_id,this.type,page_number+"");
        Callback<GroupPostListResponse> groupPostListResponseCallback =
                new Callback<GroupPostListResponse>() {
                    @Override
                    public void onResponse(Call<GroupPostListResponse> call, Response<GroupPostListResponse> response) {
                        GroupPostListResponse groupPostListResponse = response.body();
                        try {
                            arrPostDatumList = groupPostListResponse.getArrData().getArrPostData();
                            SingleGroupPostAdapter singleGroupPostAdapter =
                                    new SingleGroupPostAdapter(group_id, type,
                                            getContext(), arrPostDatumList);
                            mRecyclerView.setAdapter(singleGroupPostAdapter);
                            if(mProgressBar!=null)
                                mProgressBar.setVisibility(View.INVISIBLE);
                        }
                        catch (NullPointerException e){

                        }
                    }

                    @Override
                    public void onFailure(Call<GroupPostListResponse> call, Throwable t) {
                        Toast.makeText(getContext(),"Network Error", Toast.LENGTH_SHORT).show();
                    }
                };
        postListResponseCall.enqueue(groupPostListResponseCallback);
//        AtgService atgService = AtgClient.getClient().create(AtgService.class);
//        Call<GroupPostListResponse> postListResponseCall =
//                atgService.getGroupPosts(UserPreferenceManager.getUserId(getContext()),
//                        this.group_id,this.type);
    }
}
