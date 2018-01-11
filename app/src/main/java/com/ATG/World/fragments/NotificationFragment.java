package com.ATG.World.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.adapters.NotificationAdapter;
import com.ATG.World.models.Notification;
import com.ATG.World.models.NotificationRes;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private int user_id;
    private OnFragmentInteractionListener mListener;

    public NotificationFragment() {
        // Required empty public constructor
    }


    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user_id=(getArguments().getInt("user_id"));
            Log.e("user_idddddddd",""+user_id);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v=inflater.inflate(R.layout.fragment_notification, container, false);
        Retrofit retrofit=new AtgClient().getClient();
        AtgService atg=retrofit.create(AtgService.class);
        Log.e("ATG",UserPreferenceManager.getUserId(getContext()));
        Call<NotificationRes> call=atg.getNotificationList(UserPreferenceManager.getUserId(getContext()));
        //give me 2 mins//is this stuff written in the wrong method?though i dont think so//wait let me look closely for 2-3 mins
        call.enqueue(new Callback<NotificationRes>() {
            @Override
            public void onResponse(Call<NotificationRes> call, Response<NotificationRes> response) {
                NotificationRes notificationRes=response.body();
                List<Notification> notifs=notificationRes.getNotification();
                for(int i=0;i<notifs.size();i++)
                    Log.e("HELLO",notifs.get(i).getFeed_id()+"");
                RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new NotificationAdapter(notifs,getActivity()));
                //as u can see retrofit is getting all the response correctly//u kidding ri8?
            }//so nothing wrong with the retrofit part?now no problem,earlier was not working//i think some api problem.coz it shud return a link...maybe they are
                //it is api prob that they are returning null for prof image,i will try for something else.but we are not loading the images yet
            //u saw the output?its right ?i think so,so ..problem is somewhere else,i gotta drop mom to market,will get back later
            //cool..even i gott visit market n munivcipality//.sbuyre enjoy bro,bye,gotta go hmm
            @Override
            public void onFailure(Call<NotificationRes> call, Throwable t) {

            }
        });
        /*call.enqueue(new Callback<List<UserNotification>>() {
            @Override
            public void onResponse(Call<List<UserNotification>> call, Response<List<UserNotification>> response) {
                notifs[0] =response.body();
                RecyclerView recyclerView=(RecyclerView)v.findViewById(R.id.recycler_view);
                recyclerView.setHasFixedSize(false);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(new NotificationAdapter(notifs[0],getActivity()));
            }

            @Override
            public void onFailure(Call<List<UserNotification>> call, Throwable t) {
                Toast.makeText(getActivity(),"There seems to be an error!!",Toast.LENGTH_LONG).show();

            }
        });
        */
        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
           /* throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
