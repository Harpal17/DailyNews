package com.example.root.dailynews;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by ${Harpal} on 4/12/17.
 */

public class SlideFragment extends Fragment
{

    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private static final String PAGE = "page";
    private TextView header, subHeader;
    private ImageView mobileImage, innerIcon;
    private View mobileBacground;
    private RelativeLayout mobileLayout, viewLayout;
    // creates single instance of this class.
    public static final SlideFragment newInstance(String message, int page)
    {
        SlideFragment f = new SlideFragment();
        // set data to bundle coming from activity
        Bundle bdl = new Bundle();
        bdl.putString(EXTRA_MESSAGE, message);
        bdl.putInt(PAGE, page);
        f.setArguments(bdl);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.walkthrough_slide_fragment, container, false);

        header = (TextView) view.findViewById(R.id.tv_heading);
        subHeader = (TextView) view.findViewById(R.id.tv_sub_heading);
        mobileImage = (ImageView) view.findViewById(R.id.iv_walkthrough);
        innerIcon = (ImageView) view.findViewById(R.id.iv_walkthrough_icon);
        mobileBacground = (View) view.findViewById(R.id.view_mobile_background);
        mobileLayout = (RelativeLayout) view.findViewById(R.id.rl_mobile);

        // set margins of mobile layout which shows in this fragment
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = (int) Util.pxToDp(getActivity(), getActivity().getResources().getDimension(R.dimen.margin_16));
        layoutParams.setMargins((int) Util.getScreenWidth(getActivity()) / 4, margin, (int) Util.getScreenWidth(getActivity()) / 4, margin);
        mobileLayout.setLayoutParams(layoutParams);

        // get data from bundle coming from activity
        String message = getArguments().getString(EXTRA_MESSAGE);

        // set data according to message types
        if (message.equalsIgnoreCase(getString(R.string.trekking)))
        {
            header.setVisibility(View.INVISIBLE);
            subHeader.setVisibility(View.INVISIBLE);
            innerIcon.setVisibility(View.INVISIBLE);
            mobileBacground.setVisibility(View.INVISIBLE);
            header.setText(getString(R.string.trekking));
            subHeader.setText("Trek and explore Himalya");
            mobileBacground.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_slide1));
            innerIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.ic_trekking));
            firstTimeAnimate();
        } else if (message.equalsIgnoreCase(getString(R.string.adventure)))
        {
            header.setText(getString(R.string.adventure));
            subHeader.setText("Adventures sports activities");
            mobileBacground.setBackgroundColor(ContextCompat.getColor(getActivity(),R.color.color_slide2));
            innerIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_bungee));
        }

        else if (message.equalsIgnoreCase(getString(R.string.camping)))
        {
            header.setText(getString(R.string.camping));
            subHeader.setText("Camping and bonfire");
            mobileBacground.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_slide4));
            innerIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_camping));
        } else if (message.equalsIgnoreCase(getString(R.string.rafting)))
        {
            header.setText(getString(R.string.rafting));
            subHeader.setText("River rafting in the holy Ganga");
            mobileBacground.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_slide5));
            innerIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_rafting));
        }
        else if (message.equalsIgnoreCase(getString(R.string.bonfire)))
        {
            header.setText(getString(R.string.bonfire));
            subHeader.setText("Enjoy the bonfire");
            mobileBacground.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.color_slide4));
            innerIcon.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_bonfire));
        }
        view.setTag(getArguments().getInt(PAGE));

        return view;
    }

    // firstTimeAnimate method used to animate all the views of this fragments on first loading.
    public void firstTimeAnimate()
    {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(header, "translationX", -Util.getScreenWidth(getActivity()), 0f);
        objectAnimator.setStartDelay(300);
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                header.setVisibility(View.VISIBLE);
            }
        });
        objectAnimator.start();
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(subHeader, "translationX", Util.getScreenWidth(getActivity()), 0f);
        objectAnimator1.setStartDelay(300);
        objectAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                subHeader.setVisibility(View.VISIBLE);
            }
        });
        objectAnimator1.start();
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(innerIcon, "translationX", -Util.getScreenWidth(getActivity()), 0f);
        objectAnimator3.setStartDelay(300);
        objectAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                innerIcon.setVisibility(View.VISIBLE);
            }
        });
        objectAnimator3.start();
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(mobileBacground, "translationX", Util.getScreenWidth(getActivity()), 0f);
        objectAnimator4.setStartDelay(300);
        objectAnimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
        {
            @Override
            public void onAnimationUpdate(ValueAnimator animation)
            {
                mobileBacground.setVisibility(View.VISIBLE);
            }
        });
        objectAnimator4.start();
    }
}
