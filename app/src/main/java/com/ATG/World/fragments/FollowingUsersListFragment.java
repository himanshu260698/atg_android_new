package com.ATG.World.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import com.ATG.World.R;
import com.ATG.World.adapters.FollowingUsersListAdapter;
import com.ATG.World.models.FollowingUsersList;
import com.ATG.World.models.FollowingUsersResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkLogUtility;
import com.ATG.World.utilities.NetworkUtility;

import java.util.List;

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
public class FollowingUsersListFragment extends BaseFragment implements FollowingUsersListAdapter.OnReloadClickListener, FollowingUsersListAdapter.OnItemClickListener {
    
    private static final String TAG = FollowingUsersListFragment.class.getSimpleName();
    public static final int PAGE_SIZE = 10;
    public static final int NO_RECORD_FOUND = 3;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.error_ll)
    LinearLayout errorLinearLayout;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.btn_reload)
    TextView mReloadButton;
    @BindView(R.id.progressBar_load)
    ProgressBar mProgressBar;

    private LinearLayoutManager mLinearLayoutManager;
    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int mCurrentPage = PAGE_START;
    private Unbinder unbinder;
    private FollowingUsersListAdapter followingUsersListAdapter;
    private AtgService atgService;

    public FollowingUsersListFragment() {
        // Required empty public constructor
    }
    
    public static FollowingUsersListFragment newInstance() {
        return new FollowingUsersListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_following_users_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        followingUsersListAdapter = new FollowingUsersListAdapter();
        followingUsersListAdapter.setOnItemClickListener(this);
        followingUsersListAdapter.setOnReloadClickListener(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(followingUsersListAdapter);

        // Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        atgService = AtgClient.getClient().create(AtgService.class);
        Call<FollowingUsersResponse> call = atgService.getFollowingUsers(Integer.parseInt(UserPreferenceManager.getUserId(getActivity())), PAGE_START, 10);
        calls.add(call);
        call.enqueue(firstFetchCallback);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        removeListeners();
        mCurrentPage = 1;
        unbinder.unbind();
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

    @OnClick(R.id.btn_reload)
    public void onReloadButtonClicked() {
        errorLinearLayout.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);

        Call<FollowingUsersResponse> fetchAll = atgService.getFollowingUsers(Integer.parseInt(UserPreferenceManager.getUserId(getActivity())), PAGE_START, 10);
        calls.add(fetchAll);
        fetchAll.enqueue(firstFetchCallback);
    }

    private void loadMoreItems() {
        isLoading = true;
        mCurrentPage = mCurrentPage + 1;

        Call<FollowingUsersResponse> call = atgService.getFollowingUsers(Integer.parseInt(UserPreferenceManager.getUserId(getActivity())), mCurrentPage, 10);
        call.enqueue(nextFetchCallback);

    }

    public Callback<FollowingUsersResponse> firstFetchCallback = new Callback<FollowingUsersResponse>() {
        @Override
        public void onResponse(Call<FollowingUsersResponse> call, Response<FollowingUsersResponse> response) {
            mProgressBar.setVisibility(View.GONE);
            isLoading = false;

            if (!response.isSuccessful()) {
                int responseCode = response.code();
                if (responseCode == 504) { // 504 Unsatisfiable Request (only-if-cached)
                    errorTextView.setText("Can't load data.\nCheck your network connection.");
                    errorLinearLayout.setVisibility(View.VISIBLE);
                }
                return;
            }

            FollowingUsersResponse FollowingUsersResponse = response.body();
            int errorMessage = Integer.parseInt(String.valueOf(FollowingUsersResponse.getErr()));

            if (FollowingUsersResponse != null) {

                List<FollowingUsersList> followingUsersList = FollowingUsersResponse.getFollowings();

                if (followingUsersList != null) {

                    if (followingUsersList.size() > 0) {
                        followingUsersListAdapter.addAll(followingUsersList);
                    }

                    if (followingUsersList.size() >= PAGE_SIZE) {
                        followingUsersListAdapter.addFooter();
                    } else {
                        isLastPage = true;
                    }
                }
            } else if (errorMessage == NO_RECORD_FOUND) {
                emptyView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()) {
                isLoading = false;
                mProgressBar.setVisibility(View.GONE);

                if (NetworkUtility.isKnownException(t)) {
                    errorTextView.setText("Can't load data.\nCheck your network connection.");
                    errorLinearLayout.setVisibility(View.VISIBLE);
                }
            }

        }
    };
    public Callback<FollowingUsersResponse> nextFetchCallback = new Callback<FollowingUsersResponse>() {
        @Override
        public void onResponse(Call<FollowingUsersResponse> call, Response<FollowingUsersResponse> response) {
            followingUsersListAdapter.removeFooter();
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

            FollowingUsersResponse FollowingUsersResponse = response.body();
            if (FollowingUsersResponse != null) {
                List<FollowingUsersList> followingUsersList = FollowingUsersResponse.getFollowings();
                if (followingUsersList != null) {
                    if (followingUsersList.size() > 0)
                        followingUsersListAdapter.addAll(followingUsersList);

                    if (followingUsersList.size() >= PAGE_SIZE) {
                        followingUsersListAdapter.addFooter();
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
                    followingUsersListAdapter.updateFooter(FollowingUsersListAdapter.FooterType.ERROR);
                }
            }

        }
    };

    @Override
    public void onItemClick(int position, View view) {
    }

    private void removeListeners() {
        mRecyclerView.removeOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void onReloadClick() {

    }

}
