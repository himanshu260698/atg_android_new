package com.ATG.World.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
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
import com.ATG.World.models.DashboardResponse;
import com.ATG.World.models.GroupDetails;
import com.ATG.World.models.MyGroupResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkLogUtility;
import com.ATG.World.utilities.NetworkUtility;
import com.google.gson.Gson;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostQriousOne extends Fragment {
    private TagFlowLayout tagFlowLayoutqrious;
    List<GroupDetails> groupDetailsList;
    Button backButtonqrious,nextButtonqrious,reload;
    LinearLayout errorlinearLayout;
    private boolean isLoading = false;
    TextView errorText;
    List<String> listqrious;
    ProgressBar progressBar_cyclic_qrious_one;
    SendQriousGroupData sendQriousGroupData;
    public PostQriousOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_post_qrious_one, container, false);
        tagFlowLayoutqrious=view.findViewById(R.id.tag_flow_layout_qrious);
        tagFlowLayoutqrious.setMaxSelectCount(5);
        progressBar_cyclic_qrious_one=view.findViewById(R.id.progressBar_cyclic_one_qrious);
        listqrious=new ArrayList<>();
        AtgService atgService= AtgClient.getClient().create(AtgService.class);
        if(NetworkUtility.isNetworkAvailable(getActivity())) {
            progressBar_cyclic_qrious_one.setVisibility(View.VISIBLE);
            Call<MyGroupResponse> call = atgService.getMyGroupsData(UserPreferenceManager.getUserId(getContext()));
            call.enqueue(myGroupResponseCallback);
        }else {
            isLoading = false;

            Toast.makeText(getActivity(), "Network not available", Toast.LENGTH_SHORT).show();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backButtonqrious=view.findViewById(R.id.change_back_button_qrious);
        nextButtonqrious=view.findViewById(R.id.nextButtonQrious);
        backButtonqrious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHomeFragment();
            }
        });

        nextButtonqrious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtility.isNetworkAvailable(getActivity())) {
                    if (listqrious.size() > 0) {
                        Log.d("data", "onResponse: " + listqrious.get(0));
                        Log.d("Size", "onResponse: " + listqrious.size());
                        sendQriousGroupData.sendQriousData(listqrious);
                    } else {
                        Toast.makeText(getActivity(), "Select group(s) to proceed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void loadHomeFragment(){
        HomeFragment homeFragment=new HomeFragment();
        Fragment fragment = homeFragment;
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        ((MainActivity)getActivity()).setUpNavigationView();
        ((MainActivity)getActivity()).loadHomeFragment();

    }
    public Callback<MyGroupResponse> myGroupResponseCallback=new Callback<MyGroupResponse>() {
        @Override
        public void onResponse(Call<MyGroupResponse> call, Response<MyGroupResponse> response) {

            if(!response.isSuccessful()){
                Toast.makeText(getContext(), "Failed to load data", Toast.LENGTH_SHORT).show();
               
            }else {
                progressBar_cyclic_qrious_one.setVisibility(View.GONE);
                MyGroupResponse myGroupResponse=response.body();
                Gson gson = new Gson();
                Log.w("onMyGroupResponse ", gson.toJson(myGroupResponse));
                try {
                    groupDetailsList = myGroupResponse.getArrMyGroups();
                    Log.d("onMyGroupResponse", "onResponse: " + groupDetailsList.get(0).getName());
                    String[] array = new String[groupDetailsList.size()];
                    for (int i = 0; i < groupDetailsList.size(); i++) {
                        array[i] = groupDetailsList.get(i).getName();
                    }

                    if (array.length > 0) {
                        tagFlowLayoutqrious.setAdapter(new TagAdapter<String>(array) {
                            @Override
                            public View getView(FlowLayout parent, int position, String s) {
                                TextView textView = (TextView) getLayoutInflater().inflate(R.layout.tv, tagFlowLayoutqrious, false);
                                textView.setText(s);
                                return textView;
                            }

                            /*@Override
                            public void onSelected(int position, View view) {
                                super.onSelected(position, view);
                                // Toast.makeText(getActivity(), "Selected " + groupDetailsList.get(position).getName() + groupDetailsList.get(position).getId(), Toast.LENGTH_SHORT).show();
                                listqrious.add(groupDetailsList.get(position).getId().toString());
                            }

                            @Override
                            public void unSelected(int position, View view) {
                                super.unSelected(position, view);
                                //   Toast.makeText(getActivity(), "Deselected " + groupDetailsList.get(position).getName() + groupDetailsList.get(position).getId(), Toast.LENGTH_SHORT).show();
                                listqrious.remove(position);
                            }*/
                        });
                    } else {
                        Toast.makeText(getActivity(), "Join group(s) to post qrious", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Join group(s) to post an qrious", Toast.LENGTH_SHORT).show();
                }


            }

        }
        @Override
        public void onFailure(Call<MyGroupResponse> call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()) {
                isLoading = false;
                if(progressBar_cyclic_qrious_one!=null)
                    progressBar_cyclic_qrious_one.setVisibility(View.GONE);

                if (NetworkUtility.isKnownException(t)) {
                    if(errorText!=null) {
                        errorText.setText("Can't load data.\nCheck your network connection.");
                        errorlinearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    };
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((MainActivity) getActivity()).showNButton();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
        ((MainActivity)getActivity()).showBack();

    }
    public interface SendQriousGroupData{
        void sendQriousData(List<String> list);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendQriousGroupData=(PostQriousOne.SendQriousGroupData)getActivity();
    }
}
