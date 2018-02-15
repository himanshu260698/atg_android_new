package com.ATG.World.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.models.PostArticleResponse;
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
public class PostJobTwo extends Fragment {
    ArrayList<String> arrayList;
    Button nextButtonJob,backButton;
    private List<String> jobIds;
    EditText title,description;
    private List<String> articleIds;
    private AtgService atgService;
    SendJobData sendJobData;
    String location;
    int id;
    public PostJobTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_post_job_two, container, false);
        jobIds=new ArrayList<>();
        nextButtonJob=view.findViewById(R.id.nextButtonJob);
        nextButtonJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  if(checkFor()){
                    if(NetworkUtility.isNetworkAvailable(getActivity())){

                        uploadJobFirst();
                    }else {
                        Toast.makeText(getActivity(), "Network connection not available", Toast.LENGTH_SHORT).show();
                    }
                }
         //   }
        });
     /*   backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPrevious();
            }
        });
        */

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        atgService= AtgClient.getClient().create(AtgService.class);
    }
  /*  public boolean checkFor(){
        if(title.getText().toString().equals("")){
            title.requestFocus();
            title.setError("Title cannot be empty");
            return false;
        }
        else if(description.getText().toString().equals("")){
            description.requestFocus();
            description.setError("Description cannot be empty");
            return false;
        }
        else {
            return true;
        }
    }
    */
    private void loadPrevious(){

        getActivity().getSupportFragmentManager().popBackStack();

    }

    public void receivedJobGroupData(List<String> data){
        arrayList=new ArrayList<>();
        for (int i=0;i<data.size();i++)
        {
            Log.d("Received Data ", "receivedJobGroupData: "+data.get(i));

        }
        arrayList.addAll(data);

    }
    private void uploadJobFirst(){
     //   progressBar_cyclic_two.setVisibility(View.VISIBLE);
        Call<PostJobResponse> call;
        for (int i=0;i<arrayList.size();i++){
            call=atgService.postJobStepOne(UserPreferenceManager.getUserId(getActivity()),Integer.parseInt(arrayList.get(i)),
                    jobIds.size()>0?jobIds.get(i):"",
                    "New title","New description","Shimla");
            call.enqueue(new Callback<PostJobResponse>() {
                @Override
                public void onResponse(Call<PostJobResponse> call, Response<PostJobResponse> response) {
                    if(response.code()==200) {
                     //   progressBar_cyclic_two.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Job successfully posted", Toast.LENGTH_SHORT).show();
                        PostJobResponse postJobResponse=response.body();
                        id=postJobResponse.getJobId();
                        location=postJobResponse.getJobData().getJobLocation();
                        //  Toast.makeText(getActivity(), "Title "+titlea, Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(getActivity(), "ID "+id, Toast.LENGTH_SHORT).show();
                        sendJobData.transferJobData(String.valueOf(id),location);
                        jobIds.add(String.valueOf(id));

                        //  loadNext();
                        //  Toast.makeText(getActivity(), "data "+articleIds.get(0), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PostJobResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to post article", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendJobData=(SendJobData) getActivity();
    }
    public interface SendJobData{
        void transferJobData(String jobID,String location);
    }
}
