package com.android.systemui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceControl;
import android.view.WindowManager;

public class ImageUtilities {
    public static Bitmap screenshotSurface(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        defaultDisplay.getRealMetrics(displayMetrics);
        Bitmap screenshot = SurfaceControl.screenshot(new Rect(), Math.round(((float) displayMetrics.widthPixels) * 0.35f), Math.round(((float) displayMetrics.heightPixels) * 0.35f), false, defaultDisplay.getRotation());
        if (screenshot == null) {
            Log.e("ScreenShotHelper", "screenShotBitmap error bitmap is null");
            return null;
        }
        screenshot.prepareToDraw();
        return screenshot.copy(Bitmap.Config.ARGB_8888, true);
    }

    public static Bitmap blurImage(Context context, Bitmap bitmap, int i) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap);
        RenderScript create = RenderScript.create(context);
        ScriptIntrinsicBlur create2 = ScriptIntrinsicBlur.create(create, Element.U8_4(create));
        Allocation createFromBitmap = Allocation.createFromBitmap(create, bitmap);
        Allocation createFromBitmap2 = Allocation.createFromBitmap(create, createBitmap);
        create2.setRadius((i <= 0 || i > 100) ? 7.5f : ((float) i) * 0.25f);
        create2.setInput(createFromBitmap);
        create2.forEach(createFromBitmap2);
        createFromBitmap2.copyTo(createBitmap);
        return createBitmap;
    }
}
