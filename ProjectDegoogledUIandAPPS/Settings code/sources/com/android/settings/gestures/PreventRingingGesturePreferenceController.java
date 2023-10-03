package com.android.settings.gestures;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceScreen;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.widget.RadioButtonPreference;
import com.android.settings.widget.VideoPreference;
import com.android.settingslib.core.AbstractPreferenceController;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.havoc.config.center.C1715R;

public class PreventRingingGesturePreferenceController extends AbstractPreferenceController implements RadioButtonPreference.OnClickListener, LifecycleObserver, OnSaveInstanceState, OnResume, OnPause, OnCreate, PreferenceControllerMixin {
    @VisibleForTesting
    static final String KEY_MUTE = "prevent_ringing_option_mute";
    @VisibleForTesting
    static final String KEY_MUTE_NO_MEDIA = "prevent_ringing_option_mute_no_media";
    @VisibleForTesting
    static final String KEY_VIBRATE = "prevent_ringing_option_vibrate";
    private final String KEY = "gesture_prevent_ringing_category";
    private final String KEY_VIDEO_PAUSED = "key_video_paused";
    private final String PREF_KEY_VIDEO = "gesture_prevent_ringing_video";
    private final Context mContext;
    RadioButtonPreference mCyclePref;
    @VisibleForTesting
    RadioButtonPreference mMuteNoMediaPref;
    @VisibleForTesting
    RadioButtonPreference mMutePref;
    @VisibleForTesting
    PreferenceCategory mPreferenceCategory;
    private SettingObserver mSettingObserver;
    @VisibleForTesting
    RadioButtonPreference mVibratePref;
    private boolean mVideoPaused;
    private VideoPreference mVideoPreference;

    public String getPreferenceKey() {
        return "gesture_prevent_ringing_category";
    }

    public String getVideoPrefKey() {
        return "gesture_prevent_ringing_video";
    }

    public PreventRingingGesturePreferenceController(Context context, Lifecycle lifecycle) {
        super(context);
        this.mContext = context;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mPreferenceCategory = (PreferenceCategory) preferenceScreen.findPreference(getPreferenceKey());
            this.mVibratePref = makeRadioPreference(KEY_VIBRATE, C1715R.string.prevent_ringing_option_vibrate);
            this.mMutePref = makeRadioPreference(KEY_MUTE, C1715R.string.prevent_ringing_option_mute);
            this.mMuteNoMediaPref = makeRadioPreference(KEY_MUTE_NO_MEDIA, C1715R.string.prevent_ringing_option_mute_no_media);
            this.mCyclePref = makeRadioPreference("prevent_ringing_option_cycle", C1715R.string.prevent_ringing_option_cycle);
            PreferenceCategory preferenceCategory = this.mPreferenceCategory;
            if (preferenceCategory != null) {
                this.mSettingObserver = new SettingObserver(preferenceCategory);
            }
            this.mVideoPreference = (VideoPreference) preferenceScreen.findPreference(getVideoPrefKey());
        }
    }

    public boolean isAvailable() {
        return this.mContext.getResources().getBoolean(17891608);
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("key_video_paused", this.mVideoPaused);
    }

    public void onRadioButtonClicked(RadioButtonPreference radioButtonPreference) {
        int keyToSetting = keyToSetting(radioButtonPreference.getKey());
        if (keyToSetting != Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1)) {
            Settings.Secure.putInt(this.mContext.getContentResolver(), "volume_hush_gesture", keyToSetting);
        }
    }

    public void updateState(Preference preference) {
        int i = Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1);
        boolean z = i == 1;
        boolean z2 = i == 2;
        boolean z3 = i == 3;
        boolean z4 = i == 4;
        RadioButtonPreference radioButtonPreference = this.mVibratePref;
        if (!(radioButtonPreference == null || radioButtonPreference.isChecked() == z)) {
            this.mVibratePref.setChecked(z);
        }
        RadioButtonPreference radioButtonPreference2 = this.mMutePref;
        if (!(radioButtonPreference2 == null || radioButtonPreference2.isChecked() == z2)) {
            this.mMutePref.setChecked(z2);
        }
        RadioButtonPreference radioButtonPreference3 = this.mMuteNoMediaPref;
        if (!(radioButtonPreference3 == null || radioButtonPreference3.isChecked() == z3)) {
            this.mMuteNoMediaPref.setChecked(z3);
        }
        RadioButtonPreference radioButtonPreference4 = this.mCyclePref;
        if (!(radioButtonPreference4 == null || radioButtonPreference4.isChecked() == z4)) {
            this.mCyclePref.setChecked(z4);
        }
        if (i == 0) {
            this.mVibratePref.setEnabled(false);
            this.mMutePref.setEnabled(false);
            this.mMuteNoMediaPref.setEnabled(false);
            RadioButtonPreference radioButtonPreference5 = this.mCyclePref;
            if (radioButtonPreference5 != null) {
                radioButtonPreference5.setEnabled(false);
                return;
            }
            return;
        }
        this.mVibratePref.setEnabled(true);
        this.mMutePref.setEnabled(true);
        this.mMuteNoMediaPref.setEnabled(true);
        RadioButtonPreference radioButtonPreference6 = this.mCyclePref;
        if (radioButtonPreference6 != null) {
            radioButtonPreference6.setEnabled(true);
        }
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mVideoPaused = bundle.getBoolean("key_video_paused", false);
        }
    }

    public void onResume() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver());
            this.mSettingObserver.onChange(false, (Uri) null);
        }
        VideoPreference videoPreference = this.mVideoPreference;
        if (videoPreference != null) {
            videoPreference.onViewVisible(this.mVideoPaused);
        }
    }

    public void onPause() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.unregister(this.mContext.getContentResolver());
        }
        VideoPreference videoPreference = this.mVideoPreference;
        if (videoPreference != null) {
            this.mVideoPaused = videoPreference.isVideoPaused();
            this.mVideoPreference.onViewInvisible();
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int keyToSetting(java.lang.String r5) {
        /*
            r4 = this;
            int r4 = r5.hashCode()
            r0 = 0
            r1 = 3
            r2 = 2
            r3 = 1
            switch(r4) {
                case -762400752: goto L_0x002a;
                case -571390858: goto L_0x0020;
                case 945532335: goto L_0x0016;
                case 996174361: goto L_0x000c;
                default: goto L_0x000b;
            }
        L_0x000b:
            goto L_0x0034
        L_0x000c:
            java.lang.String r4 = "prevent_ringing_option_vibrate"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0034
            r4 = r3
            goto L_0x0035
        L_0x0016:
            java.lang.String r4 = "prevent_ringing_option_mute"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0034
            r4 = r0
            goto L_0x0035
        L_0x0020:
            java.lang.String r4 = "prevent_ringing_option_mute_no_media"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0034
            r4 = r2
            goto L_0x0035
        L_0x002a:
            java.lang.String r4 = "prevent_ringing_option_cycle"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0034
            r4 = r1
            goto L_0x0035
        L_0x0034:
            r4 = -1
        L_0x0035:
            if (r4 == 0) goto L_0x0042
            if (r4 == r3) goto L_0x0041
            if (r4 == r2) goto L_0x0040
            if (r4 == r1) goto L_0x003e
            return r0
        L_0x003e:
            r4 = 4
            return r4
        L_0x0040:
            return r1
        L_0x0041:
            return r3
        L_0x0042:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.settings.gestures.PreventRingingGesturePreferenceController.keyToSetting(java.lang.String):int");
    }

    private RadioButtonPreference makeRadioPreference(String str, int i) {
        RadioButtonPreference radioButtonPreference = new RadioButtonPreference(this.mPreferenceCategory.getContext());
        radioButtonPreference.setKey(str);
        radioButtonPreference.setTitle(i);
        radioButtonPreference.setOnClickListener(this);
        this.mPreferenceCategory.addPreference(radioButtonPreference);
        return radioButtonPreference;
    }

    private class SettingObserver extends ContentObserver {
        private final Uri VOLUME_HUSH_GESTURE = Settings.Secure.getUriFor("volume_hush_gesture");
        private final Preference mPreference;

        public SettingObserver(Preference preference) {
            super(new Handler());
            this.mPreference = preference;
        }

        public void register(ContentResolver contentResolver) {
            contentResolver.registerContentObserver(this.VOLUME_HUSH_GESTURE, false, this);
        }

        public void unregister(ContentResolver contentResolver) {
            contentResolver.unregisterContentObserver(this);
        }

        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (uri == null || this.VOLUME_HUSH_GESTURE.equals(uri)) {
                PreventRingingGesturePreferenceController.this.updateState(this.mPreference);
            }
        }
    }
}
