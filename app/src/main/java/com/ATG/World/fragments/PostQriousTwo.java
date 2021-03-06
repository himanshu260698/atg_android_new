package com.ATG.World.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.models.PostArticleResponse;
import com.ATG.World.models.PostQriousResponse;
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
public class PostQriousTwo extends Fragment {
    EditText title,description;
    Button nextButtonq,backButtonq;
    ArrayList<String> arrayListqrious;
    private List<String> qriousIds;
    private AtgService atgService;
    String titlea;
    SendQriousData sendQriousData;
    int id;
    ProgressBar progressBar_cyclic_qrious_two;
    public PostQriousTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_post_qrious_two, container, false);
        progressBar_cyclic_qrious_two=view.findViewById(R.id.progressBar_cyclic_two_qrious);
        progressBar_cyclic_qrious_two.setVisibility(View.GONE);
        backButtonq=view.findViewById(R.id.change_back_button_qrious2);
        title=view.findViewById(R.id.titleEdittextQrious);
        description=view.findViewById(R.id.descriptionEdittextQrious);
        nextButtonq=view.findViewById(R.id.nextStepQrious);
        qriousIds=new ArrayList<>();
        nextButtonq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFor()){
                    if(NetworkUtility.isNetworkAvailable(getActivity())){
                        uploadQriousFirst();
                    }else {
                        Toast.makeText(getActivity(), "Network connection not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        backButtonq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPreviousQrious();
            }
        });
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        atgService= AtgClient.getClient().create(AtgService.class);
    }

    public void uploadQriousFirst(){
        progressBar_cyclic_qrious_two.setVisibility(View.VISIBLE);
        Call<PostQriousResponse> call;
        for (int i=0;i<arrayListqrious.size();i++){
            call=atgService.postQriousStepOne(UserPreferenceManager.getUserId(getActivity()),Integer.parseInt(arrayListqrious.get(i)),
                    qriousIds.size()>0?qriousIds.get(i):"",
                    title.getText().toString(),description.getText().toString());
            call.enqueue(new Callback<PostQriousResponse>() {
                @Override
                public void onResponse(Call<PostQriousResponse> call, Response<PostQriousResponse> response) {
                    if(response.code()==200) {
                        progressBar_cyclic_qrious_two.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Qrious successfully posted", Toast.LENGTH_SHORT).show();
                        PostQriousResponse postQriousResponse=response.body();
                        id=postQriousResponse.getQriousId();
                        titlea=postQriousResponse.getQriousData().getTitle();
                        //  Toast.makeText(getActivity(), "Title "+titlea, Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(getActivity(), "ID "+id, Toast.LENGTH_SHORT).show();
                        sendQriousData.transferQriousData(String.valueOf(id),titlea);
                        qriousIds.add(String.valueOf(id));

                        //  loadNext();
                        //  Toast.makeText(getActivity(), "data "+articleIds.get(0), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PostQriousResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to post qriuos", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public void receivedQriousGroupData(List<String> qdata){
        arrayListqrious=new ArrayList<>();
        for (int i=0;i<qdata.size();i++)
        {
            Log.d("Received Data Qrious", "receivedQriousGroupData: "+qdata.get(i));

        }
        arrayListqrious.addAll(qdata);

    }
    private void loadPreviousQrious(){
        getActivity().getSupportFragmentManager().popBackStack();
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
        if(qriousIds.size()>0) {
            Toast.makeText(getActivity(), "Ids " + qriousIds.get(qriousIds.size() - 1), Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkFor(){
        if(title.getText().toString().equals("")){
            title.requestFocus();
            title.setError("Qrious title cannot be empty");
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sendQriousData=(PostQriousTwo.SendQriousData) getActivity();
    }
    public interface SendQriousData{
        void transferQriousData(String articleID,String title);
    }
}
