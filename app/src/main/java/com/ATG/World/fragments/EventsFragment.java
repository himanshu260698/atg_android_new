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
import com.ATG.World.activity.PostDetailActivity;
import com.ATG.World.adapters.ArticleAdapter;
import com.ATG.World.adapters.EventsAdapter;
import com.ATG.World.models.Dashboard;
import com.ATG.World.models.DashboardResponse;
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
public class EventsFragment extends BaseFragment implements EventsAdapter.OnItemClickListener, EventsAdapter.OnReloadClickListener {

    public static final int PAGE_SIZE = 7;
    private static final String TAG = ArticleFragment.class.getSimpleName();
    public static final int GET_EVENTS_FILTER = 4;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
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

    private LinearLayoutManager mLinearLayoutManager;
    private static final int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int mCurrentPage = PAGE_START;
    private Unbinder unbinder;
    private EventsAdapter eventsAdapter;

    public EventsFragment(){}

    public static EventsFragment newInstance(){
        return new EventsFragment();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        eventsAdapter = new EventsAdapter();
        eventsAdapter.setOnItemClickListener(this);
        eventsAdapter.setOnReloadClickListener(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(eventsAdapter);

        // Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<DashboardResponse> call = atgService.getDashboardData(GET_EVENTS_FILTER, PAGE_START, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
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
        Call<DashboardResponse> fetchAll = atgService.getDashboardData(GET_EVENTS_FILTER, PAGE_START, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
        calls.add(fetchAll);
        fetchAll.enqueue(firstFetchCallback);
    }

    private void loadMoreItems() {
        isLoading = true;
        mCurrentPage = mCurrentPage + 1;

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<DashboardResponse> call = atgService.getDashboardData(GET_EVENTS_FILTER, mCurrentPage, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
        call.enqueue(nextFetchCallback);

    }

    public Callback<DashboardResponse> firstFetchCallback = new Callback<DashboardResponse>() {
        @Override
        public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
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

            DashboardResponse dashboardResponse = response.body();
            if (dashboardResponse != null) {
                List<Dashboard> dashboardList = dashboardResponse.getDashboard();
                if (dashboardList != null) {
                    if (dashboardList.size() > 0) {
                        eventsAdapter.addAll(dashboardList);
                    }
                    if (dashboardList.size() >= PAGE_SIZE) {
                        eventsAdapter.addFooter();
                    } else {
                        isLastPage = true;
                    }
                }
            }
        }

        @Override
        public void onFailure(Call call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);
                Log.e("events","falure");
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
    public Callback<DashboardResponse> nextFetchCallback = new Callback<DashboardResponse>() {
        @Override
        public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
            eventsAdapter.removeFooter();
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

            DashboardResponse dashboardResponse = response.body();
            if (dashboardResponse != null) {
                List<Dashboard> dashboardList = dashboardResponse.getDashboard();
                if (dashboardList != null){
                    if (dashboardList.size() > 0 )
                        eventsAdapter.addAll(dashboardList);

                    if (dashboardList.size() >= PAGE_SIZE){
                        eventsAdapter.addFooter();
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
                    eventsAdapter.updateFooter(EventsAdapter.FooterType.ERROR);
                }
            }

        }
    };

    @Override
    public void onItemClick(int position, View view) {
        Log.d(TAG, "onItemClick: " + position);
        // Get Item position
        Intent intent = new Intent(getActivity(), PostDetailActivity.class);
        Bundle bundle = new Bundle();
        Log.e("event_id_clicked",""+eventsAdapter.getItem(position).getId());
        bundle.putString("Type", eventsAdapter.getItem(position).getType());
        bundle.putInt("FeedId", eventsAdapter.getItem(position).getId());
        intent.putExtras(bundle);
        startActivity(intent);    }

    private void removeListeners() {
        mRecyclerView.removeOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void onReloadClick() {

    }
}
