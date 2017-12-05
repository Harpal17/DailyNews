package com.example.root.dailynews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * {@link PageTransformer} class implements ViewPager.PageTransformer
 * used to apply animation on views at the time of view pager scrolling
 */

public class PageTransformer implements ViewPager.PageTransformer
{

    Context context;
    int pageWidth;
    float pageWidthTimesPosition, absPosition;
    View header, subHeader, mobileImage, innerIcon, mobileBacground, mobileLayout;

    public PageTransformer(Context context)
    {
        this.context = context;
    }

    @Override
    public void transformPage(View page, final float position)
    {
        pageWidth = page.getWidth();
        pageWidthTimesPosition = pageWidth * position;
        absPosition = Math.abs(position);

        header = page.findViewById(R.id.tv_heading);
        header.setTranslationX(-pageWidthTimesPosition * 4);
        header.setAlpha(1.0f - absPosition);

        subHeader = page.findViewById(R.id.tv_sub_heading);
        subHeader.setTranslationX(pageWidthTimesPosition);
        subHeader.setAlpha(1.0f - absPosition);

        mobileImage = page.findViewById(R.id.iv_walkthrough);
        if (mobileImage != null)
        {
            mobileImage.setTranslationX(-pageWidthTimesPosition);
        }

        mobileLayout = page.findViewById(R.id.rl_mobile);
        mobileLayout.setTranslationX(-pageWidthTimesPosition);

        mobileBacground = page.findViewById(R.id.view_mobile_background);

        innerIcon = page.findViewById(R.id.iv_walkthrough_icon);
        if (innerIcon != null)
        {
            innerIcon.setTranslationX(-pageWidthTimesPosition * 4);
        }
    }

}