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
import com.ATG.World.adapters.GetAllAdapter;
import com.ATG.World.models.Dashboard;
import com.ATG.World.models.DashboardResponse;
import com.ATG.World.network.AtgClient;
import com.ATG.World.network.AtgService;
import com.ATG.World.preferences.UserPreferenceManager;
import com.ATG.World.utilities.NetworkLogUtility;
import com.ATG.World.utilities.NetworkUtility;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This fragment will be used to show list of all posts.
 */
public class GetAllFragment extends BaseFragment implements GetAllAdapter.OnItemClickListener, GetAllAdapter.OnReloadClickListener {

    public static final int PAGE_SIZE = 7;
    public static final int GET_ALL_FILTER = 1;
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
    private GetAllAdapter mGetAllAdapter;

    public GetAllFragment() {
    }

    public static GetAllFragment newInstance() {
        return new GetAllFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_get_all, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        mGetAllAdapter = new GetAllAdapter();
        mGetAllAdapter.setOnItemClickListener(this);
        mGetAllAdapter.setOnReloadClickListener(this);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mGetAllAdapter);

        // Pagination
        mRecyclerView.addOnScrollListener(recyclerViewOnScrollListener);

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<DashboardResponse> call = atgService.getDashboardData(0, PAGE_START, 455);
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
        Call<DashboardResponse> fetchAll = atgService.getDashboardData(0, PAGE_START, 455);
        calls.add(fetchAll);
        fetchAll.enqueue(firstFetchCallback);
    }

    private void loadMoreItems() {
        isLoading = true;
        mCurrentPage = mCurrentPage + 1;

        AtgService atgService = AtgClient.getClient().create(AtgService.class);
        Call<DashboardResponse> call = atgService.getDashboardData(GET_ALL_FILTER, mCurrentPage, Integer.parseInt(UserPreferenceManager.getUserId(getActivity())));
        call.enqueue(nextFetchCallback);

    }

    public Callback<DashboardResponse> firstFetchCallback = new Callback<DashboardResponse>() {
        @Override
        public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
            if(mProgressBar!=null)
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
                        mGetAllAdapter.addAll(dashboardList);
                    }
                    if (dashboardList.size() >= PAGE_SIZE) {
                        mGetAllAdapter.addFooter();
                    } else {
                        isLastPage = true;
                    }
                }
            }
        }

        @Override
        public void onFailure(retrofit2.Call call, Throwable t) {
            NetworkLogUtility.logFailure(call, t);

            if (!call.isCanceled()) {
                isLoading = false;
                if(mProgressBar!=null)
                    mProgressBar.setVisibility(View.GONE);

                if (NetworkUtility.isKnownException(t)) {
                    if(errorTextView!=null) {
                        errorTextView.setText("Can't load data.\nCheck your network connection.");
                        errorLinearLayout.setVisibility(View.VISIBLE);
                    }
                }
            }

        }
    };
    public Callback<DashboardResponse> nextFetchCallback = new Callback<DashboardResponse>() {
        @Override
        public void onResponse(Call<DashboardResponse> call, Response<DashboardResponse> response) {
            mGetAllAdapter.removeFooter();
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
                        mGetAllAdapter.addAll(dashboardList);

                    if (dashboardList.size() >= PAGE_SIZE){
                        mGetAllAdapter.addFooter();
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
                    mGetAllAdapter.updateFooter(GetAllAdapter.FooterType.ERROR);
                }
            }

        }
    };

    @Override
    public void onItemClick(int position, View view) {
        // Get Item position
    }

    private void removeListeners() {
        mRecyclerView.removeOnScrollListener(recyclerViewOnScrollListener);
    }

    @Override
    public void onReloadClick() {

    }
}
