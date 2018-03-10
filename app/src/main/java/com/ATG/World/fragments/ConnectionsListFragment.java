package com.ATG.World.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ATG.World.R;
import com.ATG.World.adapters.ConnectionsListAdapter;
import com.ATG.World.models.Connection;
import com.ATG.World.models.ConnectionResponse;
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
public class ConnectionsListFragment extends BaseFragment implements ConnectionsListAdapter.OnItemClickListener, ConnectionsListAdapter.OnReloadClickListener {

    private static final String TAG = ConnectionsListFragment.class.getSimpleName();

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
    public static final int PAGE_SIZE = 10;
    public static final int NO_RECORD_FOUND = 3;
    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int mCurrentPage = PAGE_START;
    private Unbinder unbinder;
    private ConnectionsListAdapter connectionsListAdapter;

    public ConnectionsListFragment() {
    }

    public static ConnectionsListFragment newInstance() {
        return new ConnectionsListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_followers_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        connectionsListAdapter = new ConnectionsListAdapter();
        connectionsListAdapter.setOnItemClickListener(this);
        connectionsListAdapter.setOnReloadClickListener(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(connectionsListAdapter);

        // Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<ConnectionResponse> call = atgService.getConnections(Integer.parseInt(UserPreferenceManager.getUserId(getActivity())), PAGE_START, PAGE_SIZE);
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

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<ConnectionResponse> call = atgService.getConnections(Integer.parseInt(UserPreferenceManager.getUserId(getActivity())), PAGE_START, PAGE_SIZE);
        calls.add(call);
        call.enqueue(firstFetchCallback);
    }

    private void loadMoreItems() {
        isLoading = true;
        mCurrentPage = mCurrentPage + 1;

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<ConnectionResponse> call = atgService.getConnections(Integer.parseInt(UserPreferenceManager.getUserId(getActivity())), mCurrentPage, PAGE_SIZE);
        calls.add(call);
        call.enqueue(nextFetchCallback);

    }

    public Callback<ConnectionResponse> firstFetchCallback = new Callback<ConnectionResponse>() {
        @Override
        public void onResponse(Call<ConnectionResponse> call, Response<ConnectionResponse> response) {
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

            ConnectionResponse connectionResponse = response.body();
            String errorMessage = connectionResponse.getErr().toString();
            if (connectionResponse != null) {
                List<Connection> connectionList = connectionResponse.getConnections();
                if (connectionList != null) {
                    if (connectionList.size() > 0) {
                        connectionsListAdapter.addAll(connectionList);
                    }
                    if (connectionList.size() >= PAGE_SIZE) {
                        connectionsListAdapter.addFooter();
                    } else {
                        isLastPage = true;
                    }
                }
            } else if (connectionResponse.getTotalConnections() == 0) {
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
    public Callback<ConnectionResponse> nextFetchCallback = new Callback<ConnectionResponse>() {
        @Override
        public void onResponse(Call<ConnectionResponse> call, Response<ConnectionResponse> response) {
            connectionsListAdapter.removeFooter();
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

            ConnectionResponse connectionResponse = response.body();
            if (connectionResponse != null) {
                List<Connection> connectionList = connectionResponse.getConnections();
                if (connectionList != null) {
                    if (connectionList.size() > 0)
                        connectionsListAdapter.addAll(connectionList);

                    if (connectionList.size() >= PAGE_SIZE) {
                        connectionsListAdapter.addFooter();
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
                    connectionsListAdapter.updateFooter(ConnectionsListAdapter.FooterType.ERROR);
                }
            }

        }
    };

    @Override
    public void onItemClick(int position, View view) {
        // Send to that user's profile
    }

    private void removeListeners() {
        mRecyclerView.removeOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void onReloadClick() {

    }

}
