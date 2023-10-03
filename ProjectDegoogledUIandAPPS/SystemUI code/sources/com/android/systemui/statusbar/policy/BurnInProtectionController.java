package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.android.systemui.C1773R$bool;
import com.android.systemui.C1775R$dimen;
import com.android.systemui.C1778R$integer;
import com.android.systemui.statusbar.phone.NavigationBarView;
import com.android.systemui.statusbar.phone.PhoneStatusBarView;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.BurnInProtectionController;
import java.util.Timer;
import java.util.TimerTask;

public class BurnInProtectionController {
    private Context mContext;
    private int mHorizontalDirection = 1;
    private int mHorizontalMaxShift;
    private int mHorizontalShift = 0;
    private PhoneStatusBarView mPhoneStatusBarView;
    private long mShiftInterval;
    private StatusBar mStatusBar;
    private boolean mSwiftEnabled;
    private Timer mTimer;
    private int mVerticalDirection = 1;
    private int mVerticalMaxShift;
    private int mVerticalShift = 0;

    public BurnInProtectionController(Context context, StatusBar statusBar, PhoneStatusBarView phoneStatusBarView) {
        this.mContext = context;
        this.mStatusBar = statusBar;
        this.mPhoneStatusBarView = phoneStatusBarView;
        this.mSwiftEnabled = this.mContext.getResources().getBoolean(C1773R$bool.config_statusBarBurnInProtection);
        this.mHorizontalMaxShift = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.horizontal_max_swift);
        this.mVerticalMaxShift = this.mContext.getResources().getDimensionPixelSize(C1775R$dimen.vertical_max_swift) - 1;
        this.mShiftInterval = (long) this.mContext.getResources().getInteger(C1778R$integer.config_shift_interval);
    }

    public void startSwiftTimer() {
        if (this.mSwiftEnabled) {
            if (this.mTimer == null) {
                this.mTimer = new Timer();
            }
            this.mTimer.schedule(new TimerTask() {
                public void run() {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            BurnInProtectionController.C15121.this.lambda$run$0$BurnInProtectionController$1();
                        }
                    });
                }

                public /* synthetic */ void lambda$run$0$BurnInProtectionController$1() {
                    BurnInProtectionController.this.swiftItems();
                }
            }, 0, this.mShiftInterval * 1000);
        }
    }

    public void stopSwiftTimer() {
        Timer timer;
        if (this.mSwiftEnabled && (timer = this.mTimer) != null) {
            timer.cancel();
            this.mTimer.purge();
            this.mTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public void swiftItems() {
        this.mHorizontalShift += this.mHorizontalDirection;
        int i = this.mHorizontalShift;
        int i2 = this.mHorizontalMaxShift;
        if (i >= i2 || i <= (-i2)) {
            this.mHorizontalDirection *= -1;
        }
        this.mVerticalShift += this.mVerticalDirection;
        int i3 = this.mVerticalShift;
        int i4 = this.mVerticalMaxShift;
        if (i3 >= i4 || i3 <= (-i4)) {
            this.mVerticalDirection *= -1;
        }
        this.mPhoneStatusBarView.swiftStatusBarItems(this.mHorizontalShift, this.mVerticalShift);
        NavigationBarView navigationBarView = this.mStatusBar.getNavigationBarView();
        if (navigationBarView != null) {
            navigationBarView.swiftNavigationBarItems(this.mHorizontalShift, this.mVerticalShift);
        }
    }
}
