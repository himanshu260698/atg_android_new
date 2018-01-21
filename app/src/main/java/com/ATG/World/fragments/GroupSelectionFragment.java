package com.ATG.World.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.adapters.GetAllAdapter;
import com.ATG.World.models.DashboardResponse;
import com.ATG.World.models.NicheGroupResponse;
import com.ATG.World.models.Subgroup;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.utilities.GlideApp;
import com.ATG.World.utilities.GroupSelectionSingleton;
import com.ATG.World.utilities.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupSelectionFragment extends Fragment {

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


    private LinearLayoutManager mLinearLayoutManager;
    private boolean isLoading = false;
    private Unbinder unbinder;
    NicheGroupAdapter nicheGroupAdapter;
    private int id;
    List<Subgroup> mList =new ArrayList<>();
    GroupSelectionSingleton addedGroups;


    public GroupSelectionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            id = getArguments().getInt("id");
        }
        addedGroups=GroupSelectionSingleton.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_group_selection, container, false);
        unbinder = ButterKnife.bind(this, root);
        nicheGroupAdapter=new NicheGroupAdapter();
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setAdapter(nicheGroupAdapter);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        AtgService retrofit = AtgClient.getClient().create(AtgService.class);
        Call<NicheGroupResponse> call=retrofit.getNicheGroup(id);
        call.enqueue(callback);


    }

    public Callback<NicheGroupResponse> callback=new Callback<NicheGroupResponse>() {
        @Override
        public void onResponse(Call<NicheGroupResponse> call, Response<NicheGroupResponse> response) {
            if(mProgressBar!=null)
                mProgressBar.setVisibility(View.GONE);
            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if (responseCode == 504) { // 504 Unsatisfiable Request (only-if-cached)
                    errorTextView.setText("Can't load data.\nCheck your network connection.");
                    errorLinearLayout.setVisibility(View.VISIBLE);
                }
                return;
            }

            List<Subgroup> subgroup=response.body().getSubgroup();
            mList=subgroup;
            nicheGroupAdapter.notifyDataSetChanged();

        }

        @Override
        public void onFailure(Call<NicheGroupResponse> call, Throwable t) {


            if (!call.isCanceled()) {
                isLoading = false;
                if(mProgressBar!=null)
                    mProgressBar.setVisibility(View.GONE);

                if (NetworkUtility.isKnownException(t)) {
                    if(errorTextView!=null) {
                        errorTextView.setText("Can't load data.\nCheck your network connection.");
                        errorLinearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_reload)
    public void onReloadButtonClicked() {
        errorLinearLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        AtgService retrofit = AtgClient.getClient().create(AtgService.class);
        Call<NicheGroupResponse> call=retrofit.getNicheGroup(id);
        call.enqueue(callback);
    }

    private class NicheGroupHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView textView;
        Button button;


        public NicheGroupHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_niche_group_selection, parent, false));
            circleImageView = itemView.findViewById(R.id.image_group);
            textView = itemView.findViewById(R.id.group_name);
            button = itemView.findViewById(R.id.group_select);

        }

        public void bind(Subgroup subgroup) {
            textView.setText(subgroup.getGroup_name());
            if(subgroup.getFollowing())
                button.setSelected(true);
            else
                button.setSelected(false);
            Log.e("CHECK",subgroup.getIcon_img());
            GlideApp.with(getContext())
                    .load(subgroup.getIcon_img())
                    .override(80,80)
                    .into(circleImageView);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(button.isSelected()){
                        addedGroups.removeGroup(subgroup.getId());
                        Log.e("CHECK",subgroup.getId()+""+"-");
                        subgroup.setFollowing(false);
                        button.setSelected(false);}
                    else{
                        addedGroups.addGroup(subgroup.getId());
                        Log.e("CHECK",subgroup.getId()+""+"+");
                        subgroup.setFollowing(true);
                        button.setSelected(true);}
                        nicheGroupAdapter.notifyDataSetChanged();


                }
            });



        }
    }

    private class NicheGroupAdapter extends RecyclerView.Adapter<NicheGroupHolder>{
        public NicheGroupAdapter(){

        }

        @Override
        public NicheGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            return new NicheGroupHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(NicheGroupHolder holder, int position) {
                holder.bind(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

}
