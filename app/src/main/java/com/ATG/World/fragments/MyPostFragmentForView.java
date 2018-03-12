package com.ATG.World.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ATG.World.R;
import com.ATG.World.adapters.GetAllAdapter;
import com.ATG.World.adapters.MyPostAdapter;
import com.ATG.World.models.MyPostPojo;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkLogUtility;
import com.ATG.World.utilities.NetworkUtility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abhimanoj on 23/2/18.
 */

public class MyPostFragmentForView extends BaseFragment implements MyPostAdapter.OnItemClickListener, MyPostAdapter.OnReloadClickListener {

    public static final int PAGE_SIZE = 2;

    //Collection...
    MyPostPojo myPostPojoInstance;
    private MyPostAdapter myPostAdapterInstance;
    private TextView tvNotFound;

    private String setTypeOfData = "0";

    int pageNumberIs = 1;

    private LinearLayoutManager mLinearLayoutManager;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private Unbinder unbinder;


    @BindView(R.id.mypostlistview)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    View emptyView;
    @BindView(R.id.error_ll)
    LinearLayout errorLinearLayout;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.btn_reload)
    TextView mReloadButton;
    @BindView(R.id.progressBar_load)
    ProgressBar mProgressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.my_post_view_pager_view, container, false);

        tvNotFound = (TextView) mView.findViewById(R.id.tv_notfound);
        unbinder = ButterKnife.bind(this, mView);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        if (getArguments() != null) {
            setTypeOfData = getArguments().getString("setTypeOfData");
        }

        GetMyPostData();


        myPostAdapterInstance = new MyPostAdapter();
        myPostAdapterInstance.setOnItemClickListener(this);
        myPostAdapterInstance.setOnReloadClickListener(this);


        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myPostAdapterInstance);

        //Pagination
        recyclerView.addOnScrollListener(recyclerViewOnScrollListener);

    }

    private void GetMyPostData() {

        mProgressBar.setVisibility(View.VISIBLE);
        Log.d("MyPost", "GetMyPostData " + setTypeOfData);

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call call = atgService.getMyPostData(Integer.parseInt(setTypeOfData), Integer.parseInt(UserPreferenceManager.getUserId(getActivity())), pageNumberIs, 1);
        call.enqueue(getResultFromServer);
    }


    public Callback getResultFromServer = new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            if (mProgressBar != null)
                mProgressBar.setVisibility(View.GONE);
            isLoading = false;

            Log.d("MyPostPojo", "response " + response.message());

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if (responseCode == 504) { // 504 Unsatisfiable Request (only-if-cached)
                    Toast.makeText(getContext(), "Can't load data.\nCheck your network connection.", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            if (response.body() != null) {

                JSONObject data = new JSONObject((Map) response.body());
                JSONArray getContent = null;
                try {
                  /*  Log.d("", "" + data.getString("error_code") +
                            data.getString("msg") +
                            data.getString("arr_data") +
                            data.getString("count"));*/
                    String arrData = (data.getString("arr_data"));
                    JSONObject data2 = new JSONObject(arrData);

                    getContent = data2.getJSONArray("arr_post_data");


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Store Data From Json To Pojo


                ArrayList<MyPostPojo> myPostPojoListLocal;
                myPostPojoListLocal = setDataIntoPojo(getContent);

                if (myPostPojoListLocal != null) {
                    if (myPostPojoListLocal.size() > 0) {
                        myPostAdapterInstance.addAll(myPostPojoListLocal);
                    }
                    if (myPostPojoListLocal.size() >= PAGE_SIZE) {
                        myPostAdapterInstance.addFooter();
                    } else {
                        isLastPage = true;
                    }
                }
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()) {
                isLoading = false;
                if (mProgressBar != null)
                    mProgressBar.setVisibility(View.GONE);

                if (NetworkUtility.isKnownException(t)) {

                    Toast.makeText(getContext(), "Can't load data.\nCheck your network connection.", Toast.LENGTH_SHORT).show();

                }
            }
        }
    };


    public Callback nextFetchCallback = new Callback() {
        @Override
        public void onResponse(Call call, Response response) {
            myPostAdapterInstance.removeFooter();
            isLoading = false;

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                switch (responseCode) {
                    case 504: // 504 Unsatisfiable Request (only-if-cached)
                        break;
                    case 400:
                        isLastPage = true;
                        break;
                }
                return;
            }
            if (response.body() != null) {
                JSONObject data = new JSONObject((Map) response.body());
                JSONArray getContent = null;
                try {
                  /*  Log.d("", "" + data.getString("error_code") +
                            data.getString("msg") +
                            data.getString("arr_data") +
                            data.getString("count"));*/
                    String arrData = (data.getString("arr_data"));
                    JSONObject data2 = new JSONObject(arrData);

                    getContent = data2.getJSONArray("arr_post_data");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayList<MyPostPojo> myPostPojosListData;
                myPostPojosListData = setDataIntoPojo(getContent);

                if (myPostPojosListData != null) {
                    if (myPostPojosListData.size() > 0)
                        myPostAdapterInstance.addAll(myPostPojosListData);

                    if (myPostPojosListData.size() >= PAGE_SIZE) {
                        myPostAdapterInstance.addFooter();
                    } else {
                        isLastPage = true;
                    }
                }

            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()) {
                if (NetworkUtility.isKnownException(t)) {
                    myPostAdapterInstance.updateFooter(GetAllAdapter.FooterType.ERROR);
                }
            }

        }
    };

    public static MyPostFragmentForView newInstance(String param1) {

        MyPostFragmentForView fragment = new MyPostFragmentForView();
        Bundle args = new Bundle();
        args.putString("setTypeOfData", param1);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = mLinearLayoutManager.getChildCount();
            int totalItemCount = mLinearLayoutManager.getItemCount();
            int firstVisibleItemCount = mLinearLayoutManager.findFirstVisibleItemPosition();

            if (!isLoading && !isLastPage) {
                if ((visibleItemCount + firstVisibleItemCount) >= totalItemCount
                        && firstVisibleItemCount >= 0
                        && totalItemCount >= PAGE_SIZE) {
                    loadMoreItems();
                }
            }
        }
    };

    private void loadMoreItems() {
        isLoading = true;
        pageNumberIs++;

        Log.d("myPost", "loadMoreItems = " + setTypeOfData);
        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call call = atgService.getMyPostData(Integer.parseInt(setTypeOfData), Integer.parseInt(UserPreferenceManager.getUserId(getActivity())), pageNumberIs, 1);
        call.enqueue(nextFetchCallback);
    }


    @Override
    public void onItemClick(int position, View view) {

    }

    @Override
    public void onReloadClick() {

    }

    public ArrayList<MyPostPojo> setDataIntoPojo(JSONArray getContent) {

        ArrayList<MyPostPojo> myPostPojosListIs = new ArrayList<>();


        for (int k = 0; k < getContent.length(); k++) {
            try {
                JSONObject json = getContent.getJSONObject(k);
                myPostPojoInstance = new MyPostPojo();
                if (json.has("user_id"))
                    myPostPojoInstance.setUserId(json.getString("user_id"));
                if (json.has("user_name"))

                    myPostPojoInstance.setUserName(json.getString("user_name").toString());
                if (json.has("first_name"))

                    myPostPojoInstance.setFirstName(json.getString("first_name").toString());
                if (json.has("last_name"))

                    myPostPojoInstance.setLastName(json.getString("last_name").toString());
                if (json.has("profile_picture"))

                    myPostPojoInstance.setProfilePicture(json.getString("profile_picture").toString());
                if (json.has("gender"))

                    myPostPojoInstance.setGender(json.getString("gender").toString());
                if (json.has("location"))

                    myPostPojoInstance.setLocation(json.getString("location").toString());
                if (json.has("id"))

                    myPostPojoInstance.setId((json.getString("id").toString()));
                if (json.has("user_id_fk"))

                    myPostPojoInstance.setUserIdFk(json.getString("user_id_fk").toString());
                if (json.has("tags"))

                    myPostPojoInstance.setTags(json.getString("tags").toString());
                if (json.has("title"))

                    myPostPojoInstance.setTitle(json.getString("title").toString());
                if (json.has("image"))

                    myPostPojoInstance.setImage(json.getString("image").toString());
                if (json.has("description"))

                    myPostPojoInstance.setDescription(json.getString("description").toString());
                if (json.has("updated_at"))

                    myPostPojoInstance.setUpdatedAt(json.getString("updated_at").toString());
                if (json.has("created_at"))

                    myPostPojoInstance.setCreatedAt(json.getString("created_at").toString());
                if (json.has("post_status"))

                    myPostPojoInstance.setPostStatus(json.getString("post_status").toString());
                if (json.has("share_link"))

                    myPostPojoInstance.setShareLink(json.getString("share_link").toString());
                if (json.has("count"))

                    myPostPojoInstance.setCount((json.getString("count").toString()));
                if (json.has("upvote_count"))

                    myPostPojoInstance.setUpvoteCount(json.getString("upvote_count").toString());
                if (json.has("follower_status"))

                    myPostPojoInstance.setDownvoteCount(json.getString("downvote_count").toString());
                if (json.has("user_name"))

                    myPostPojoInstance.setFollowerStatus(json.getString("follower_status").toString());
                if (json.has("follower_count"))

                    myPostPojoInstance.setFollowerCount(json.getString("follower_count").toString());
                if (json.has("arr_tag"))

                    myPostPojoInstance.setArrTag(new String[]{json.getString("arr_tag").toString()});
                if (json.has("type"))
                    myPostPojoInstance.setType(json.getString("type").toString());

                Log.d("MyPostPojo", "typeIs " + json.getString("type").toString());
                myPostPojosListIs.add(myPostPojoInstance);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return myPostPojosListIs;
    }

}



