package com.ATG.World.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.ATG.World.R;

/**
 * Created by Chetan on 22-12-2017.
 */

public class PostStageTwoFragment extends Fragment {

    private EditText inputTitle;
    private EditText inputDescription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.post_fragment_stage_two_layout, container, false);

        //for Skip button...
        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.skip_post_fragment, menu);  // Use filter.xml from step 1
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_skip) {


            //Do whatever you want to do


            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
