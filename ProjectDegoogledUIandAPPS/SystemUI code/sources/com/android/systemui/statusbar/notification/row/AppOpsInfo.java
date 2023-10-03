package com.android.systemui.statusbar.notification.row;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.service.notification.StatusBarNotification;
import android.util.ArraySet;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.internal.logging.MetricsLogger;
import com.android.systemui.C1777R$id;
import com.android.systemui.C1784R$string;
import com.android.systemui.statusbar.notification.row.NotificationGuts;

public class AppOpsInfo extends LinearLayout implements NotificationGuts.GutsContent {
    private String mAppName;
    private ArraySet<Integer> mAppOps;
    private int mAppUid;
    private NotificationGuts mGutsContainer;
    private MetricsLogger mMetricsLogger;
    private View.OnClickListener mOnOk = new View.OnClickListener() {
        public final void onClick(View view) {
            AppOpsInfo.this.lambda$new$0$AppOpsInfo(view);
        }
    };
    private OnSettingsClickListener mOnSettingsClickListener;
    private String mPkg;
    private PackageManager mPm;
    private StatusBarNotification mSbn;

    public interface OnSettingsClickListener {
        void onClick(View view, String str, int i, ArraySet<Integer> arraySet);
    }

    public View getContentView() {
        return this;
    }

    public boolean handleCloseControls(boolean z, boolean z2) {
        return false;
    }

    public boolean shouldBeSaved() {
        return false;
    }

    public boolean willBeRemoved() {
        return false;
    }

    public AppOpsInfo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void bindGuts(PackageManager packageManager, OnSettingsClickListener onSettingsClickListener, StatusBarNotification statusBarNotification, ArraySet<Integer> arraySet) {
        this.mPkg = statusBarNotification.getPackageName();
        this.mSbn = statusBarNotification;
        this.mPm = packageManager;
        this.mAppName = this.mPkg;
        this.mOnSettingsClickListener = onSettingsClickListener;
        this.mAppOps = arraySet;
        bindHeader();
        bindPrompt();
        bindButtons();
        this.mMetricsLogger = new MetricsLogger();
        this.mMetricsLogger.visibility(1345, true);
    }

    private void bindHeader() {
        Drawable drawable;
        try {
            ApplicationInfo applicationInfo = this.mPm.getApplicationInfo(this.mPkg, 795136);
            if (applicationInfo != null) {
                this.mAppUid = this.mSbn.getUid();
                this.mAppName = String.valueOf(this.mPm.getApplicationLabel(applicationInfo));
                drawable = this.mPm.getApplicationIcon(applicationInfo);
            } else {
                drawable = null;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            drawable = this.mPm.getDefaultActivityIcon();
        }
        ((ImageView) findViewById(C1777R$id.pkgicon)).setImageDrawable(drawable);
        ((TextView) findViewById(C1777R$id.pkgname)).setText(this.mAppName);
    }

    private void bindPrompt() {
        ((TextView) findViewById(C1777R$id.prompt)).setText(getPrompt());
    }

    private void bindButtons() {
        findViewById(C1777R$id.settings).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AppOpsInfo.this.lambda$bindButtons$1$AppOpsInfo(view);
            }
        });
        ((TextView) findViewById(C1777R$id.f28ok)).setOnClickListener(this.mOnOk);
    }

    public /* synthetic */ void lambda$bindButtons$1$AppOpsInfo(View view) {
        this.mOnSettingsClickListener.onClick(view, this.mPkg, this.mAppUid, this.mAppOps);
    }

    private String getPrompt() {
        ArraySet<Integer> arraySet = this.mAppOps;
        if (arraySet == null || arraySet.size() == 0) {
            return "";
        }
        if (this.mAppOps.size() == 1) {
            if (this.mAppOps.contains(26)) {
                return this.mContext.getString(C1784R$string.appops_camera);
            }
            if (this.mAppOps.contains(27)) {
                return this.mContext.getString(C1784R$string.appops_microphone);
            }
            return this.mContext.getString(C1784R$string.appops_overlay);
        } else if (this.mAppOps.size() != 2) {
            return this.mContext.getString(C1784R$string.appops_camera_mic_overlay);
        } else {
            if (!this.mAppOps.contains(26)) {
                return this.mContext.getString(C1784R$string.appops_mic_overlay);
            }
            if (this.mAppOps.contains(27)) {
                return this.mContext.getString(C1784R$string.appops_camera_mic);
            }
            return this.mContext.getString(C1784R$string.appops_camera_overlay);
        }
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (this.mGutsContainer != null && accessibilityEvent.getEventType() == 32) {
            if (this.mGutsContainer.isExposed()) {
                accessibilityEvent.getText().add(this.mContext.getString(C1784R$string.notification_channel_controls_opened_accessibility, new Object[]{this.mAppName}));
                return;
            }
            accessibilityEvent.getText().add(this.mContext.getString(C1784R$string.notification_channel_controls_closed_accessibility, new Object[]{this.mAppName}));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: closeControls */
    public void lambda$new$0$AppOpsInfo(View view) {
        this.mMetricsLogger.visibility(1345, false);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        this.mGutsContainer.getLocationOnScreen(iArr);
        view.getLocationOnScreen(iArr2);
        int i = iArr2[0] - iArr[0];
        int i2 = iArr2[1] - iArr[1];
        this.mGutsContainer.closeControls(i + (view.getWidth() / 2), i2 + (view.getHeight() / 2), false, false);
    }

    public void setGutsParent(NotificationGuts notificationGuts) {
        this.mGutsContainer = notificationGuts;
    }

    public int getActualHeight() {
        return getHeight();
    }
}
