package com.ATG.World.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ATG.World.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostJobThree extends Fragment {
    String transfereedLocation;
    ArrayList<String> jobIds;
    public PostJobThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_job_three, container, false);
    }
    public void receiveJobData(String jId,String loc){
        //articleID=aId;
        jobIds=new ArrayList<>();
        transfereedLocation=loc;
        jobIds.add(jId);
        Log.d("job data received", "receiveJobData: "+jobIds.get(jobIds.size()-1)+loc);

    }

}
