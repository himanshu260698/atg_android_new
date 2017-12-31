package com.ATG.World.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by Chetan on 22-12-2017.
 */

public abstract class BaseFragment extends Fragment {

    // Region Member Variables
    protected List<Call> calls;

    // Region Lifecycle Methods
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calls = new ArrayList<>();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        calls.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // Region Inner Class
    private class CancelTask extends AsyncTask<Call, Void, Void>{
        @Override
        protected Void doInBackground(Call... params) {
            Call call = params[0];
            call.cancel();
            return null;
        }
    }
}
