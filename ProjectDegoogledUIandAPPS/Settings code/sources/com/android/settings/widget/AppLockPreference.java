package com.android.settings.widget;

import android.app.AppLockManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceViewHolder;
import com.havoc.config.center.C1715R;

public class AppLockPreference extends CheckBoxPreference {
    /* access modifiers changed from: private */
    public final AppLockManager mAppLockManager;
    private View mNotifFrame;
    /* access modifiers changed from: private */
    public ImageView mNotificationImage;
    /* access modifiers changed from: private */
    public final String mPackageName;

    public AppLockPreference(Context context, AppLockManager appLockManager, String str) {
        super(context, (AttributeSet) null);
        this.mAppLockManager = appLockManager;
        this.mPackageName = str;
        setLayoutResource(C1715R.layout.preference_applock);
        setWidgetLayoutResource(C1715R.layout.preference_widget_applock);
        setSummaryOn((int) C1715R.string.applock_locked);
        setSummaryOff((int) C1715R.string.applock_unlocked);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.mNotificationImage = (ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.notification_img);
        this.mNotifFrame = preferenceViewHolder.findViewById(C1715R.C1718id.notification_frame);
        this.mNotifFrame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean appNotificationHide = AppLockPreference.this.mAppLockManager.getAppNotificationHide(AppLockPreference.this.mPackageName);
                AppLockPreference.this.mAppLockManager.setAppNotificationHide(AppLockPreference.this.mPackageName, !appNotificationHide);
                AppLockPreference.this.mNotificationImage.setImageResource(appNotificationHide ? C1715R.C1717drawable.ic_audio_notifications : C1715R.C1717drawable.ic_audio_notifications_off_24dp);
                view.setTooltipText(AppLockPreference.this.getContext().getString(appNotificationHide ? C1715R.string.applock_show_notif : C1715R.string.applock_hide_notif));
                view.performLongClick();
            }
        });
        boolean appNotificationHide = this.mAppLockManager.getAppNotificationHide(this.mPackageName);
        this.mNotifFrame.setTooltipText(getContext().getString(appNotificationHide ? C1715R.string.applock_hide_notif : C1715R.string.applock_show_notif));
        this.mNotificationImage.setImageResource(appNotificationHide ? C1715R.C1717drawable.ic_audio_notifications_off_24dp : C1715R.C1717drawable.ic_audio_notifications);
        this.mNotifFrame.setVisibility(isChecked() ? 0 : 8);
    }

    public void startHintAnimation() {
        View view = this.mNotifFrame;
        view.postOnAnimationDelayed(getSinglePressFor(view), 200);
    }

    private Runnable getSinglePressFor(View view) {
        return new Runnable(view) {
            private final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                AppLockPreference.this.lambda$getSinglePressFor$0$AppLockPreference(this.f$1);
            }
        };
    }

    public /* synthetic */ void lambda$getSinglePressFor$0$AppLockPreference(View view) {
        view.setPressed(true);
        view.postOnAnimationDelayed(getSingleUnpressFor(view), 200);
    }

    private Runnable getSingleUnpressFor(View view) {
        return new Runnable(view) {
            private final /* synthetic */ View f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                this.f$0.setPressed(false);
            }
        };
    }
}
