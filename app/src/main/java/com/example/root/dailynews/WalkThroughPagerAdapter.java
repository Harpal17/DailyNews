package com.example.root.dailynews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ${Harpal} on 4/12/17.
 * {@link WalkThroughPagerAdapter} used to set the list of fragments in view pager
 */

public class WalkThroughPagerAdapter extends FragmentPagerAdapter
{

    private List<Fragment> fragments;

    public WalkThroughPagerAdapter(FragmentManager fm, List<Fragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return this.fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }
}
