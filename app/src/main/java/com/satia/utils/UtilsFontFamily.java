package com.satia.utils;

import android.content.Context;
import android.graphics.Typeface;

public class UtilsFontFamily {

    public static Typeface typeFaceForRobotoRegular(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Regular.ttf");
    }

    public static Typeface typeFaceForRobotoMedium(Context context) {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/Roboto-Medium.ttf");
    }
}
