package com.android.settings.notification;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import androidx.preference.PreferenceScreen;
import com.android.internal.widget.LockPatternUtils;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.List;

public class ChannelNotificationSettings extends NotificationSettingsBase {
    /* access modifiers changed from: protected */
    public String getLogTag() {
        return "ChannelSettings";
    }

    public int getMetricsCategory() {
        return 265;
    }

    /* access modifiers changed from: protected */
    public int getPreferenceScreenResId() {
        return C1715R.xml.channel_notification_settings;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        Bundle arguments = getArguments();
        if (preferenceScreen != null && arguments != null && !arguments.getBoolean("fromSettings", false)) {
            preferenceScreen.setInitialExpandedChildrenCount(Integer.MAX_VALUE);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mUid < 0 || TextUtils.isEmpty(this.mPkg) || this.mPkgInfo == null || this.mChannel == null) {
            Log.w("ChannelSettings", "Missing package or uid or packageinfo or channel");
            finish();
            return;
        }
        for (NotificationPreferenceController next : this.mControllers) {
            next.onResume(this.mAppRow, this.mChannel, this.mChannelGroup, this.mSuspendedAppsAdmin);
            next.displayPreference(getPreferenceScreen());
        }
        updatePreferenceStates();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        for (NotificationPreferenceController next : this.mControllers) {
            if (next instanceof PreferenceManager.OnActivityResultListener) {
                ((PreferenceManager.OnActivityResultListener) next).onActivityResult(i, i2, intent);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mNm.forcePulseLedLight(-1, -1, -1);
    }

    public void onStop() {
        super.onStop();
        this.mNm.forcePulseLedLight(-1, -1, -1);
    }

    public void onPause() {
        super.onPause();
        this.mNm.forcePulseLedLight(-1, -1, -1);
    }

    /* access modifiers changed from: protected */
    public List<AbstractPreferenceController> createPreferenceControllers(Context context) {
        this.mControllers = new ArrayList();
        this.mControllers.add(new HeaderPreferenceController(context, this));
        this.mControllers.add(new BlockPreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new ImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new MinImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new HighImportancePreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new AllowSoundPreferenceController(context, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new SoundPreferenceController(context, this, this.mImportanceListener, this.mBackend));
        this.mControllers.add(new VibrationPreferenceController(context, this.mBackend));
        this.mControllers.add(new AppLinkPreferenceController(context));
        this.mControllers.add(new DescriptionPreferenceController(context));
        this.mControllers.add(new VisibilityPreferenceController(context, new LockPatternUtils(context), this.mBackend));
        this.mControllers.add(new LightsPreferenceController(context, this.mBackend));
        this.mControllers.add(new CustomLightsPreferenceController(context, this.mBackend));
        this.mControllers.add(new CustomLightOnTimePreferenceController(context, this.mBackend));
        this.mControllers.add(new CustomLightOffTimePreferenceController(context, this.mBackend));
        this.mControllers.add(new BadgePreferenceController(context, this.mBackend));
        this.mControllers.add(new DndPreferenceController(context, this.mBackend));
        this.mControllers.add(new NotificationsOffPreferenceController(context));
        this.mControllers.add(new BubblePreferenceController(context, getChildFragmentManager(), this.mBackend, false));
        return new ArrayList(this.mControllers);
    }
}
