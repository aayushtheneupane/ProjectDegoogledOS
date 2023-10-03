package com.android.systemui.recents;

import android.app.ActivityManager;
import android.app.trust.TrustManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import com.android.systemui.C1784R$string;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.shared.recents.IOverviewProxy;
import com.android.systemui.shared.system.ActivityManagerWrapper;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.statusbar.phone.StatusBar;

public class OverviewProxyRecentsImpl implements RecentsImplementation {
    private Context mContext;
    private Handler mHandler;
    private OverviewProxyService mOverviewProxyService;
    private SysUiServiceProvider mSysUiServiceProvider;
    private TrustManager mTrustManager;

    public void onStart(Context context, SysUiServiceProvider sysUiServiceProvider) {
        this.mContext = context;
        this.mSysUiServiceProvider = sysUiServiceProvider;
        this.mHandler = new Handler();
        this.mTrustManager = (TrustManager) context.getSystemService("trust");
        this.mOverviewProxyService = (OverviewProxyService) Dependency.get(OverviewProxyService.class);
    }

    public void showRecentApps(boolean z) {
        IOverviewProxy proxy = this.mOverviewProxyService.getProxy();
        if (proxy != null) {
            try {
                proxy.onOverviewShown(z);
            } catch (RemoteException e) {
                Log.e("OverviewProxyRecentsImpl", "Failed to send overview show event to launcher.", e);
            }
        }
    }

    public void hideRecentApps(boolean z, boolean z2) {
        IOverviewProxy proxy = this.mOverviewProxyService.getProxy();
        if (proxy != null) {
            try {
                proxy.onOverviewHidden(z, z2);
            } catch (RemoteException e) {
                Log.e("OverviewProxyRecentsImpl", "Failed to send overview hide event to launcher.", e);
            }
        }
    }

    public void toggleRecentApps() {
        if (this.mOverviewProxyService.getProxy() != null) {
            $$Lambda$OverviewProxyRecentsImpl$ZzsBj6p_GVl3rLvpPgWKT0NW9E r0 = new Runnable() {
                public final void run() {
                    OverviewProxyRecentsImpl.this.lambda$toggleRecentApps$0$OverviewProxyRecentsImpl();
                }
            };
            StatusBar statusBar = (StatusBar) this.mSysUiServiceProvider.getComponent(StatusBar.class);
            if (statusBar == null || !statusBar.isKeyguardShowing()) {
                r0.run();
            } else {
                statusBar.executeRunnableDismissingKeyguard(new Runnable(r0) {
                    private final /* synthetic */ Runnable f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        OverviewProxyRecentsImpl.this.lambda$toggleRecentApps$1$OverviewProxyRecentsImpl(this.f$1);
                    }
                }, (Runnable) null, true, false, true);
            }
        }
    }

    public /* synthetic */ void lambda$toggleRecentApps$0$OverviewProxyRecentsImpl() {
        try {
            if (this.mOverviewProxyService.getProxy() != null) {
                this.mOverviewProxyService.getProxy().onOverviewToggle();
            }
        } catch (RemoteException e) {
            Log.e("OverviewProxyRecentsImpl", "Cannot send toggle recents through proxy service.", e);
        }
    }

    public /* synthetic */ void lambda$toggleRecentApps$1$OverviewProxyRecentsImpl(Runnable runnable) {
        this.mTrustManager.reportKeyguardShowingChanged();
        this.mHandler.post(runnable);
    }

    public boolean splitPrimaryTask(int i, Rect rect, int i2) {
        Point point = new Point();
        if (rect == null) {
            ((DisplayManager) this.mContext.getSystemService(DisplayManager.class)).getDisplay(0).getRealSize(point);
            rect = new Rect(0, 0, point.x, point.y);
        }
        ActivityManager.RunningTaskInfo runningTask = ActivityManagerWrapper.getInstance().getRunningTask();
        int activityType = runningTask != null ? runningTask.configuration.windowConfiguration.getActivityType() : 0;
        boolean isScreenPinningActive = ActivityManagerWrapper.getInstance().isScreenPinningActive();
        boolean z = activityType == 2 || activityType == 3;
        if (runningTask != null && !z && !isScreenPinningActive) {
            if (!runningTask.supportsSplitScreenMultiWindow) {
                Toast.makeText(this.mContext, C1784R$string.dock_non_resizeble_failed_to_dock_text, 0).show();
            } else if (ActivityManagerWrapper.getInstance().setTaskWindowingModeSplitScreenPrimary(runningTask.id, i, rect)) {
                Divider divider = (Divider) this.mSysUiServiceProvider.getComponent(Divider.class);
                if (divider != null) {
                    divider.onRecentsDrawn();
                }
                return true;
            }
        }
        return false;
    }
}
