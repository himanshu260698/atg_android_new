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
import com.ATG.World.activity.MainActivity;

/**
 * Created by Chetan on 22-12-2017.
 */

public class PostFragment extends Fragment {

    private EditText inputTitle;
    private EditText inputDescription;
    MainActivity mainActivityInstance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.post_fragment_layout, container, false);

        //for publish button...
        setHasOptionsMenu(true);
        Initialization(rootView);

        return rootView;
    }

    private void Initialization(View rootView) {

        mainActivityInstance = (MainActivity) getActivity();


        inputTitle = (EditText) rootView.findViewById(R.id.input_title);
        inputDescription = (EditText) rootView.findViewById(R.id.input_description);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.publish_post_fragment, menu);  // Use filter.xml from step 1
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_publish) {

            if (inputTitle.getText().length() <= 0)
                inputTitle.setError("Enter Title");
            else if (inputDescription.getText().length() <= 0)
                inputDescription.setError("Enter Description");
            else {

                //Do whatever you want to do
                mainActivityInstance.loadPostSecondStageFragment();

            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
