package com.example.root.dailynews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Harpal} on 2/12/17.
 */

public class WalkThroughActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private Context mContext;
    private ViewPager viewPager;
    private TextView skipButton;
    private LinearLayout dotsLayout;
    private FloatingActionButton nextButton;
    private TextView[] dots;
    private WalkThroughPagerAdapter walkThroughPagerAdapter;
    private List<Fragment> fragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walkthrough_activity);
        mContext = this;
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        skipButton = (TextView) findViewById(R.id.tv_skip);
        dotsLayout = (LinearLayout) findViewById(R.id.ll_dots_layout);
        nextButton = (FloatingActionButton) findViewById(R.id.ib_next);

        skipButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        fragments = new ArrayList<>();

        skipButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        fragments = new ArrayList<>();

        fragments.add(SlideFragment.newInstance(getString(R.string.trekking), 0));
        fragments.add(SlideFragment.newInstance(getString(R.string.adventure), 1));
        fragments.add(SlideFragment.newInstance(getString(R.string.rafting), 2));
        fragments.add(SlideFragment.newInstance(getString(R.string.camping), 3));
        fragments.add(SlideFragment.newInstance(getString(R.string.bonfire), 4));

        // add dots in bottom of the screen
        addBottomDots(0);

        walkThroughPagerAdapter = new WalkThroughPagerAdapter(getSupportFragmentManager(), fragments);

        viewPager.setAdapter(walkThroughPagerAdapter);
        viewPager.addOnPageChangeListener(this);
        viewPager.setPageTransformer(false, new PageTransformer(mContext));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_skip:
                //launch main screen
                launcNextScreen();
                break;
            case R.id.ib_next:
                int current = getItemCount(+1);
                if (current < fragments.size()) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launcNextScreen();
                }
                break;
        }
    }

    // getItemCount returns count of view attached in viewpager
    private int getItemCount(int i) {
        return viewPager.getCurrentItem() + i;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        addBottomDots(position);
        if (position == fragments.size() - 1) {
            nextButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_done));
        } else {
            nextButton.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.next));
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // addBottomDots method add dots in linear layout
    private void addBottomDots(int currentPage) {
        dots = new TextView[fragments.size()];

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(ContextCompat.getColor(mContext, R.color.color_dot_light));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(Color.WHITE);
    }

    public void launcNextScreen() {
        Intent intent = new Intent(mContext, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
