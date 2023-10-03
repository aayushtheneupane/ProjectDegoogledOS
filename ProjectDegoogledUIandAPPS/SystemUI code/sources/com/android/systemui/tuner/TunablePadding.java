package com.android.systemui.tuner;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.android.systemui.Dependency;
import com.android.systemui.tuner.TunerService;

public class TunablePadding implements TunerService.Tunable {
    private final int mDefaultSize;
    private final float mDensity;
    private final int mFlags;
    private final TunerService mTunerService;
    private final View mView;

    private TunablePadding(String str, int i, int i2, View view, TunerService tunerService) {
        this.mDefaultSize = i;
        this.mFlags = i2;
        this.mView = view;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) view.getContext().getSystemService(WindowManager.class)).getDefaultDisplay().getMetrics(displayMetrics);
        this.mDensity = displayMetrics.density;
        this.mTunerService = tunerService;
        this.mTunerService.addTunable(this, str);
    }

    public void onTuningChanged(String str, String str2) {
        int i = this.mDefaultSize;
        if (str2 != null) {
            try {
                i = (int) (((float) Integer.parseInt(str2)) * this.mDensity);
            } catch (NumberFormatException unused) {
            }
        }
        int i2 = 2;
        int i3 = this.mView.isLayoutRtl() ? 2 : 1;
        if (this.mView.isLayoutRtl()) {
            i2 = 1;
        }
        this.mView.setPadding(getPadding(i, i3), getPadding(i, 4), getPadding(i, i2), getPadding(i, 8));
    }

    private int getPadding(int i, int i2) {
        if ((this.mFlags & i2) != 0) {
            return i;
        }
        return 0;
    }

    public void destroy() {
        this.mTunerService.removeTunable(this);
    }

    public static class TunablePaddingService {
        private final TunerService mTunerService;

        public TunablePaddingService(TunerService tunerService) {
            this.mTunerService = tunerService;
        }

        public TunablePadding add(View view, String str, int i, int i2) {
            if (view != null) {
                return new TunablePadding(str, i, i2, view, this.mTunerService);
            }
            throw new IllegalArgumentException();
        }
    }

    public static TunablePadding addTunablePadding(View view, String str, int i, int i2) {
        return ((TunablePaddingService) Dependency.get(TunablePaddingService.class)).add(view, str, i, i2);
    }
}
