package com.ATG.World.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.models.PostJobResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostJobThree extends Fragment {
    String transfereedLocation;
    ArrayList<String> jobIds;
    Button skip,back,submit;
    private AtgService atgService;
    EditText cName,cWebsite,role,extLink,email,deadLine,education,minExp,location,tags;
    Spinner spinner;
    private List<String> listProfession;
    private ArrayAdapter<String> adapter;
    private int spinPosition;
    ProgressBar progressBar_cyclic_job_three;
    public PostJobThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_post_job_three, container, false);
        listProfession=new ArrayList<>();
        progressBar_cyclic_job_three=view.findViewById(R.id.progressBar_cyclic_job_three);
        progressBar_cyclic_job_three.setVisibility(View.INVISIBLE);
        skip=view.findViewById(R.id.skip);
        back=view.findViewById(R.id.back);
        submit=view.findViewById(R.id.submitJob);
        cName=view.findViewById(R.id.company_name);
        cWebsite=view.findViewById(R.id.company_website);
        role=view.findViewById(R.id.role);
        extLink=view.findViewById(R.id.external_link);
        email=view.findViewById(R.id.email);
        deadLine=view.findViewById(R.id.dead_line);
        education=view.findViewById(R.id.education);
        minExp=view.findViewById(R.id.min_exp);
        location=view.findViewById(R.id.location);
        tags=view.findViewById(R.id.tags);
        spinner=view.findViewById(R.id.spinner);
        additems();
        location.setText(transfereedLocation);
        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, listProfession);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                spinPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spinner.setSelection(0);
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadHome();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPrevious();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFor()){
                    if(NetworkUtility.isNetworkAvailable(getActivity())){
                        submit();
                    }else {
                        Toast.makeText(getActivity(), "Network not available", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        atgService= AtgClient.getClient().create(AtgService.class);
    }
    public boolean checkFor(){
        if(cName.getText().toString().equals("")){
            cName.requestFocus();
            cName.setError("Company name cannot be empty");
            return false;
        }
        else if(cWebsite.getText().toString().equals("")){
            cWebsite.requestFocus();
            cWebsite.setError("Company website cannot be empty");
            return false;
        }else if(role.getText().toString().equals("")){
            role.requestFocus();
            role.setError("Role cannot be empty");
            return false;
        }else if(extLink.getText().toString().equals("")){
            extLink.requestFocus();
            extLink.setError("External link cannot be empty");
            return false;
        }else if(email.getText().toString().equals("")){
            email.requestFocus();
            email.setError("Email cannot be empty");
            return false;
        }else if(deadLine.getText().toString().equals("")){
            deadLine.requestFocus();
            deadLine.setError("Deadline cannot be empty");
            return false;
        }else if(education.getText().toString().equals("")){
            education.requestFocus();
            education.setError("Education cannot be empty");
            return false;
        }else if(minExp.getText().toString().equals("")){
            minExp.requestFocus();
            minExp.setError("Minimum experience cannot be empty");
            return false;
        }else if(location.getText().toString().equals("")){
            location.requestFocus();
            location.setError("Location cannot be empty");
            return false;
        }else if(tags.getText().toString().equals("")){
            tags.requestFocus();
            tags.setError("Tags cannot be empty");
            return false;
        }else if (spinPosition==0){
            Toast.makeText(getActivity(), "Select employment type", Toast.LENGTH_SHORT).show();
            return false;
        }

        else {
            return true;
        }
    }
    private void loadPrevious(){

        getActivity().getSupportFragmentManager().popBackStack();

    }
    private int spinnerItemSelect(int k) {
        if (k==1) {
            return 1;
        } else if (k==2) {
            return 2;
        } else if (k==3) {
            return 3;
        } else if (k==4) {
            return 4;
        } else return 0;
    }

    private void additems() {
        listProfession.add("Employment Type");
        listProfession.add("FullTime");
        listProfession.add("PartTime");
        listProfession.add("Internship");
        listProfession.add("Fellowship");

    }
    private void submit() {
        Call<PostJobResponse> call;
        for (int i = 0; i < jobIds.size(); i++) {
            call = atgService.postJobStepTwo(UserPreferenceManager.getUserId(getActivity()), Integer.parseInt(jobIds.get(i)),
                    cName.getText().toString(), cWebsite.getText().toString(), role.getText().toString(),
                    extLink.getText().toString(), email.getText().toString(), deadLine.getText().toString()
                    , education.getText().toString(), minExp.getText().toString(), location.getText().toString(),
                    getTags(), spinPosition
            );
            call.enqueue(new Callback<PostJobResponse>() {
                @Override
                public void onResponse(Call<PostJobResponse> call, Response<PostJobResponse> response) {
                    if (response.code() == 200) {
                        progressBar_cyclic_job_three.setVisibility(View.GONE);
                        loadHome();
                        Toast.makeText(getActivity(), "Job updated successfully", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), "Failed to post job", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PostJobResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to post job", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    private String getTags(){
        String requiredTags,returnTag = "",finalTags;
        if(!TextUtils.isEmpty(tags.getText().toString())){
            finalTags=tags.getText().toString();
            requiredTags=finalTags.trim();
            returnTag=requiredTags.replace(" ",",");

        }
        return returnTag;
    }
    private void loadHome(){
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
    public void receiveJobData(String jId, String loc) {
        //articleID=aId;
        jobIds = new ArrayList<>();
        transfereedLocation = loc;
        jobIds.add(jId);
        Log.d("job data received", "receiveJobData: " + jobIds.get(jobIds.size() - 1) + loc);

    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        ((MainActivity) getActivity()).showNButton();

    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        ((MainActivity) getActivity()).showBack();

    }
}
