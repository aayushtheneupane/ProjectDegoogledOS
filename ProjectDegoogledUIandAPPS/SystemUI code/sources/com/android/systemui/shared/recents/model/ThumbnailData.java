package com.android.systemui.shared.recents.model;

import android.app.ActivityManager;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class ThumbnailData {
    public Rect insets;
    public boolean isRealSnapshot;
    public boolean isTranslucent;
    public int orientation;
    public boolean reducedResolution;
    public float scale;
    public int systemUiVisibility;
    public final Bitmap thumbnail;
    public int windowingMode;

    public ThumbnailData(ActivityManager.TaskSnapshot taskSnapshot) {
        this.thumbnail = Bitmap.wrapHardwareBuffer(taskSnapshot.getSnapshot(), taskSnapshot.getColorSpace());
        this.insets = new Rect(taskSnapshot.getContentInsets());
        this.orientation = taskSnapshot.getOrientation();
        this.reducedResolution = taskSnapshot.isReducedResolution();
        this.scale = taskSnapshot.getScale();
        this.isRealSnapshot = taskSnapshot.isRealSnapshot();
        this.isTranslucent = taskSnapshot.isTranslucent();
        this.windowingMode = taskSnapshot.getWindowingMode();
        this.systemUiVisibility = taskSnapshot.getSystemUiVisibility();
    }
}
