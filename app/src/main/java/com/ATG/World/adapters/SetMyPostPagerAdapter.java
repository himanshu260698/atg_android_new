package com.ATG.World.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.ATG.World.fragments.MyPostFragmentForView;


/**
 * Created by Abhimanoj on 1/03/18.
 */

public class SetMyPostPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public SetMyPostPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;

         /*(Options - 0:ALL , 1:qrious, 2:article, 3:event, 4:meetup, 5:job, 6:education.)
    * */
        Log.d("myPost", "Type = " + (position));


        switch (position) {
            case 0:
                fragment = MyPostFragmentForView.newInstance(String.valueOf(0));
                return fragment;
            case 1:
                fragment = MyPostFragmentForView.newInstance(String.valueOf(1));
                return fragment;
            case 2:
                fragment = MyPostFragmentForView.newInstance(String.valueOf(2));
                return fragment;
            case 3:
                fragment = MyPostFragmentForView.newInstance(String.valueOf(3));
                return fragment;
            case 4:
                fragment = MyPostFragmentForView.newInstance(String.valueOf(4));
                return fragment;
            case 5:
                fragment = MyPostFragmentForView.newInstance(String.valueOf(5));
                return fragment;
            case 6:
                fragment = MyPostFragmentForView.newInstance(String.valueOf(6));
                return fragment;

            default:
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
