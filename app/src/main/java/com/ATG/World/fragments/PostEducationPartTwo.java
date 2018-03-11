package com.ATG.World.fragments;


import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.models.PostArticleDetails;
import com.ATG.World.models.PostArticleResponse;
import com.ATG.World.models.PostEducationResponse1;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkUtility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostEducationPartTwo extends Fragment {
    Button nextButton,backButton;
    EditText title,description;
    ArrayList<String> arrayList;
    private AtgService atgService;
    //SendArticleData sendArticleData;
    EditText website;
    FragmentTransaction fragmentTransaction ;

    String educationType="";
    String titlea;
    ProgressBar progressBar_cyclic_two;
    int id;
    String csv;
    public PostEducationPartTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_post_education_part_two, container, false);
        progressBar_cyclic_two=view.findViewById(R.id.progressBar_cyclic_two);
        progressBar_cyclic_two.setVisibility(View.GONE);
        backButton=view.findViewById(R.id.change_back_button_article2);
        title=view.findViewById(R.id.titleEdittext);
        description=view.findViewById(R.id.descriptionEdittext);
        website=view.findViewById(R.id.websiteEdittext);
        nextButton=view.findViewById(R.id.nextStep);
        fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPrevious();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFor()){
                    if(NetworkUtility.isNetworkAvailable(getActivity())){
                        Dialog dialog=new Dialog(getActivity());
                        dialog.setContentView(R.layout.edu_type_menu);
                        dialog.setTitle("");
                        dialog.setCancelable(true);
                        RadioGroup radioGroup=(RadioGroup)dialog.findViewById(R.id.edu_type_rad);
                        Button next=(Button)dialog.findViewById(R.id.call_edu_one);
                        next.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                uploadEducationFirst();
                                dialog.dismiss();
                            }
                        });
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                educationType="";
                                educationType=((RadioButton)dialog.findViewById(checkedId)).getText().toString();
                            }
                        });
                        dialog.show();
                    }else {
                        Toast.makeText(getActivity(), "Network connection not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayList=getArguments().getStringArrayList("groups");
        Log.e("group",arrayList.get(0));
        atgService= AtgClient.getClient().create(AtgService.class);
        StringBuilder csvBuilder = new StringBuilder();
        for(String city : arrayList){

            csvBuilder.append(city);

            csvBuilder.append(",");

        }
        csv = csvBuilder.toString();
        csv = csv.substring(0, csv.length() - 1);
    }

    private void uploadEducationFirst(){
        progressBar_cyclic_two.setVisibility(View.VISIBLE);
        Call<PostEducationResponse1> call;
            call=atgService.postEducationStepOne(UserPreferenceManager.getUserId(getActivity()),csv, "",
                    title.getText().toString(),description.getText().toString(),website.getText().toString(),educationType);
            call.enqueue(new Callback<PostEducationResponse1>() {
                @Override
                public void onResponse(Call<PostEducationResponse1> call, Response<PostEducationResponse1> response) {
                    if(response.code()==200) {
                        progressBar_cyclic_two.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Education successfully posted", Toast.LENGTH_SHORT).show();
                        PostEducationResponse1 postArticleResponse=response.body();
                        id=postArticleResponse.getEducation_id();
                        titlea=postArticleResponse.getEducation_data().getTitle();
                        //  Toast.makeText(getActivity(), "Title "+titlea, Toast.LENGTH_SHORT).show();
                        //   Toast.makeText(getActivity(), "ID "+id, Toast.LENGTH_SHORT).show();
                        //sendArticleData.transferData(String.valueOf(id),titlea);
                        //articleIds.add(String.valueOf(id));
                        PostEducationPartThree fragment=new PostEducationPartThree();
                        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                                android.R.anim.fade_out);
                        fragmentTransaction.replace(R.id.main_content, fragment);
                        fragmentTransaction.addToBackStack("");
                        Bundle bundle=new Bundle();
                        bundle.putInt("eduationId",id);
                        bundle.putString("title",titlea);
                        fragment.setArguments(bundle);
                        fragmentTransaction.commit();
                        //loadHomeFragment();
                    }
                }

                @Override
                public void onFailure(Call<PostEducationResponse1> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to post education", Toast.LENGTH_SHORT).show();
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

    private void loadPrevious(){
        //   PostArticlePartOne postArticlePartOne=new PostArticlePartOne();
        //   Fragment fragment = postArticlePartOne;
        //  FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        //  fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
        //          android.R.anim.fade_out);
        //   fragmentTransaction.replace(R.id.main_content, fragment);
        //   fragmentTransaction.commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().popBackStack();

    }
    /*  private void loadNext(){
          PostArticlePartThree postArticlePartThree=new PostArticlePartThree();
          Fragment fragment = postArticlePartThree;
          FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
          fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                  android.R.anim.fade_out);
          fragmentTransaction.replace(R.id.main_content, fragment);
          fragmentTransaction.commitAllowingStateLoss();

      }
      */
    public void receivedGroupData(List<String> data){
        arrayList=new ArrayList<>();
        for (int i=0;i<data.size();i++)
        {
            Log.d("Received Data ", "receivedGroupData: "+data.get(i));

        }
        arrayList.addAll(data);

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
    public boolean checkFor(){
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
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //sendArticleData=(SendArticleData) getActivity();
    }
   /* public interface SendArticleData{
        void transferData(String articleID,String title);
    }*/
}
