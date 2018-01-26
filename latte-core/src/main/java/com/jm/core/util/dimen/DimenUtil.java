package com.jm.core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.jm.core.app.Latte;


/**
 *
 * Created by ltjs1024 on 2018/1/26.
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
