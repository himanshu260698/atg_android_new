package com.ATG.World.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.activity.MainActivity;
import com.ATG.World.models.Group;
import com.ATG.World.models.MainGroup;
import com.ATG.World.models.WsJoinLeaveGroupResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.GroupSelectionSingleton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Prince on 24/1/18.
 */

public class ExploreGroupsFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private AtgService retrofit;
    private Toolbar mTopToolbar;
    List<Group> groups;
    private View mainlayout;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.my_group_fragment, container, false);


    mTopToolbar = view.findViewById(R.id.my_toolbar);
    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.setSupportActionBar(mTopToolbar);
    activity.getSupportActionBar().setTitle("Select Your Interest");
    retrofit= AtgClient.getClient().create(AtgService.class);

    progressBar=view.findViewById(R.id.my_group_progress);
    mainlayout=view.findViewById(R.id.main_container);
    viewPager = view.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(6);

    tabLayout =view.findViewById(R.id.tabs);
    networkCallfirst();

    return view;

}

    private void networkCallfirst() {
        Call<MainGroup> call=retrofit.getmainGroup();
        call.enqueue(new Callback<MainGroup>() {
            @Override
            public void onResponse(Call<MainGroup> call, Response<MainGroup> response) {
                progressBar.setVisibility(View.GONE);
                mainlayout.setVisibility(View.VISIBLE);
                MainGroup mainGroup=response.body();
                groups=mainGroup.getGroup();
                setupViewPager();
            }

            @Override
            public void onFailure(Call<MainGroup> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                View view=getView().findViewById(R.id.root_view);
                Snackbar snackbar=Snackbar.make(view,"Network Error",Snackbar.LENGTH_INDEFINITE)
                        .setAction("RELOAD", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                networkCallfirst();
                            }
                        });
                snackbar.show();

            }
        });
    }

    private void networkCallSecond(){
        progressBar.setVisibility(View.VISIBLE);
        GroupSelectionSingleton groupSelectionSingleton=GroupSelectionSingleton.getInstance();
        List<Integer> groups=groupSelectionSingleton.addedGroup();
        String group_ids="";
        for (Integer i:groups)
            group_ids=group_ids+i+",";
        Log.e("CHECK",group_ids);

        Call<WsJoinLeaveGroupResponse> call=retrofit.joinLeaveGroup(0,group_ids, UserPreferenceManager.getUserId(getActivity()));
        call.enqueue(new Callback<WsJoinLeaveGroupResponse>() {
            @Override
            public void onResponse(Call<WsJoinLeaveGroupResponse> calll, Response<WsJoinLeaveGroupResponse> response) {
                progressBar.setVisibility(View.GONE);
                WsJoinLeaveGroupResponse wsJoinLeaveGroupResponse=response.body();
                Log.e("CHECK",wsJoinLeaveGroupResponse.getError_code());
                Log.e("CHECK",call.request().url()+"");

                if(wsJoinLeaveGroupResponse.getError_code().equals("0")){
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().finish();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getActivity(),"Error adding group",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<WsJoinLeaveGroupResponse> call, Throwable t) {

                progressBar.setVisibility(View.GONE);
                View view=getView().findViewById(R.id.root_view);
                Snackbar snackbar=Snackbar.make(view,"Network Error",Snackbar.LENGTH_INDEFINITE)
                        .setAction("RELOAD", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                networkCallSecond();
                            }
                        });
                snackbar.show();

            }
        });
    }

//    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.group_selection_next_button,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_next:
                GroupSelectionSingleton groupSelectionSingleton=GroupSelectionSingleton.getInstance();
                List<Integer> groups=groupSelectionSingleton.addedGroup();
                if(groupSelectionSingleton==null|| groups.size()==0 )
                    Toast.makeText(getActivity(),"Please select Atleast one Group",Toast.LENGTH_SHORT).show();
                else {
                    networkCallSecond();
                    for(Integer i:groups)
                        Log.e("CHECK",i+"");
                }
                break;
            default:
                break;
        }
        return true;
    }

    private void setupViewPager() {
        CollectionPagerAdapter adapter = new CollectionPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

public class CollectionPagerAdapter extends FragmentStatePagerAdapter {
    public CollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new GroupSelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",groups.get(i).getId());
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return groups.get(position).getGroup_name();
    }
}

}
