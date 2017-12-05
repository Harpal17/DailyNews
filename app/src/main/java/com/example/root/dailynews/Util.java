package com.example.root.dailynews;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;



/**
 * {@link Util} class having some static methods which are used multiple place in whole project
 */

public class Util
{

    // convert pixels to dip
    public static float pxToDp(Context context, float pxValue)
    {
        float dpValue = (float) (pxValue / context.getResources().getDisplayMetrics().density);
        return dpValue;
    }

    // convert pixels to sip
    public static float pxToSp(Context context, float pxValue)
    {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue / scaledDensity;
    }

    //convers dip to sip
    public static float dp2px(Resources resources, float dp)
    {
        final float scale = resources.getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    // converts sip to dip
    public static float sp2px(Resources resources, float sp)
    {
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    // get width of device screen
    public static float getScreenWidth(Context context)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        return width;
    }

    // get height of device screen
    public static float getScreenHeight(Context context)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float height = displayMetrics.heightPixels;
        return height;
    }

    // get height of device screen
    public static void hideSoftKeyboard(View view)
    {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }

    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight)
    {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable)
    {

        if (drawable instanceof BitmapDrawable)
        {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}
