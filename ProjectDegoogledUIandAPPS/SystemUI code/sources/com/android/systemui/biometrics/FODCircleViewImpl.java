package com.android.systemui.biometrics;

import android.content.pm.PackageManager;
import android.util.Slog;
import com.android.systemui.SystemUI;
import com.android.systemui.statusbar.CommandQueue;

public class FODCircleViewImpl extends SystemUI implements CommandQueue.Callbacks {
    private FODCircleView mFodCircleView;

    public void start() {
        PackageManager packageManager = this.mContext.getPackageManager();
        if (packageManager.hasSystemFeature("android.hardware.fingerprint") && packageManager.hasSystemFeature("vendor.lineage.biometrics.fingerprint.inscreen")) {
            ((CommandQueue) getComponent(CommandQueue.class)).addCallback((CommandQueue.Callbacks) this);
            try {
                this.mFodCircleView = new FODCircleView(this.mContext);
            } catch (RuntimeException e) {
                Slog.e("FODCircleViewImpl", "Failed to initialize FODCircleView", e);
            }
        }
    }

    public void showInDisplayFingerprintView() {
        FODCircleView fODCircleView = this.mFodCircleView;
        if (fODCircleView != null) {
            fODCircleView.show();
        }
    }

    public void hideInDisplayFingerprintView() {
        FODCircleView fODCircleView = this.mFodCircleView;
        if (fODCircleView != null) {
            fODCircleView.hide();
        }
    }
}
