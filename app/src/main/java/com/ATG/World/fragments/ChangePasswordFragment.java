package com.ATG.World.fragments;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.models.User_details;
import com.ATG.World.models.WsLoginResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkUtility;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {

    @BindView(R.id.newPass) TextInputEditText newPass;
    @BindView(R.id.newPassConfirm) TextInputEditText newPassConfirm;
    @BindView(R.id.progressBar_cyclic)ProgressBar progressBar;
    private String nP,nPConfirm;
    private Unbinder unbinder;
    Button button;

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_change_password, container, false);
        button=rootView.findViewById(R.id.change_back_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSettingsFragment();
            }
        });
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

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



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
    @OnClick(R.id.change_pass_button)
    public void updatePassword(View view){
        nP=newPass.getText().toString();
        nPConfirm=newPassConfirm.getText().toString();
        if(NetworkUtility.isNetworkAvailable(getActivity())) {
            if (validate()) {
                progressBar.setVisibility(View.VISIBLE);
                AtgService atgService = AtgClient.getClient().create(AtgService.class);
                Call<WsLoginResponse> call = atgService.postChangedPassword(UserPreferenceManager.getUserId(getActivity()),nPConfirm);
                call.enqueue(new Callback<WsLoginResponse>() {
                    @Override
                    public void onResponse(Call<WsLoginResponse> call, Response<WsLoginResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        int responseCode = response.code();
                        if (responseCode == 200) {
                            Toast.makeText(getActivity(), "Successfully changed password", Toast.LENGTH_SHORT).show();
                            loadHomeFragment();
                        }
                    }

                    @Override
                    public void onFailure(Call<WsLoginResponse> call, Throwable t) {
                        if (call.isCanceled()) {
                            Toast.makeText(getActivity(), "Failed to update password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            }else {
            Toast.makeText(getActivity(), "Network not available", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean validate(){
        if(!nP.equals(nPConfirm)){
            Toast.makeText(getActivity(), "Passwords don't match", Toast.LENGTH_SHORT).show();
            return false;
        }else if((TextUtils.isEmpty(nP))||(TextUtils.isEmpty(nPConfirm))){
            Toast.makeText(getActivity(), "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }else if((nPConfirm.length()<6)||(nPConfirm.length()>128)){
            Toast.makeText(getActivity(), "Password too short/long", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
    private void loadSettingsFragment(){
        SettingsFragment settingsFragment=new SettingsFragment();
        Fragment fragment = settingsFragment;
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();

    }
    private void loadHomeFragment(){
        HomeFragment homeFragment=new HomeFragment();
        Fragment fragment = homeFragment;
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.main_content, fragment);
        fragmentTransaction.commitAllowingStateLoss();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
