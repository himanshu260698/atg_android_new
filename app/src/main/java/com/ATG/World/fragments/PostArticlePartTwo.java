package com.ATG.World.fragments;


import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.models.PostArticleDetails;
import com.ATG.World.models.PostArticleResponse;
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
public class PostArticlePartTwo extends Fragment {
    Button nextButton,backButton;
    EditText title,description;
    private List<String> articleIds;
    ArrayList<String> arrayList;
    private AtgService atgService;
    SendArticleData sendArticleData;
    String titlea;
    ProgressBar progressBar_cyclic_two;
    int id;
    public PostArticlePartTwo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_post_article_part_two, container, false);
        progressBar_cyclic_two=view.findViewById(R.id.progressBar_cyclic_two);
        progressBar_cyclic_two.setVisibility(View.GONE);
        backButton=view.findViewById(R.id.change_back_button_article2);
        title=view.findViewById(R.id.titleEdittext);
        description=view.findViewById(R.id.descriptionEdittext);
        nextButton=view.findViewById(R.id.nextStep);
        articleIds=new ArrayList<>();
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkFor()){
                    if(NetworkUtility.isNetworkAvailable(getActivity())){

                        uploadArticleFirst();
                    }else {
                        Toast.makeText(getActivity(), "Network connection not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPrevious();
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        atgService= AtgClient.getClient().create(AtgService.class);
    }

    private void uploadArticleFirst(){
        progressBar_cyclic_two.setVisibility(View.VISIBLE);
        Call<PostArticleResponse> call;
        for (int i=0;i<arrayList.size();i++){
            call=atgService.postArticleStepOne(UserPreferenceManager.getUserId(getActivity()),Integer.parseInt(arrayList.get(i)),
                    articleIds.size()>0?articleIds.get(i):"",
                    title.getText().toString(),description.getText().toString());
            call.enqueue(new Callback<PostArticleResponse>() {
                @Override
                public void onResponse(Call<PostArticleResponse> call, Response<PostArticleResponse> response) {
                    if(response.code()==200) {
                        progressBar_cyclic_two.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Article successfully posted", Toast.LENGTH_SHORT).show();
                        PostArticleResponse postArticleResponse=response.body();
                        id=postArticleResponse.getArticleId();
                        titlea=postArticleResponse.getArticleData().getTitle();
                      //  Toast.makeText(getActivity(), "Title "+titlea, Toast.LENGTH_SHORT).show();
                     //   Toast.makeText(getActivity(), "ID "+id, Toast.LENGTH_SHORT).show();
                        sendArticleData.transferData(String.valueOf(id),titlea);
                        articleIds.add(String.valueOf(id));

                      //  loadNext();
                      //  Toast.makeText(getActivity(), "data "+articleIds.get(0), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PostArticleResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed to post article", Toast.LENGTH_SHORT).show();
                }
            });
        }


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
        if(articleIds.size()>0) {
            Toast.makeText(getActivity(), "Ids " + articleIds.get(articleIds.size() - 1), Toast.LENGTH_SHORT).show();
        }
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
        sendArticleData=(SendArticleData) getActivity();
    }
    public interface SendArticleData{
        void transferData(String articleID,String title);
    }
}
