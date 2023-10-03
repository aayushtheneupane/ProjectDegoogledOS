package com.android.settings.accessibility;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.VideoView;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.settings.widget.SwitchBar;
import com.havoc.config.center.C1715R;

public class ToggleScreenMagnificationPreferenceFragment extends ToggleFeaturePreferenceFragment implements SwitchBar.OnSwitchChangeListener {
    protected Preference mConfigWarningPreference;
    private Dialog mDialog;
    private boolean mInitialSetting = false;
    private boolean mLaunchFromSuw = false;
    protected VideoPreference mVideoPreference;

    public int getDialogMetricsCategory(int i) {
        return 7;
    }

    public int getMetricsCategory() {
        return 7;
    }

    protected class VideoPreference extends Preference {
        private ViewTreeObserver.OnGlobalLayoutListener mLayoutListener;
        /* access modifiers changed from: private */
        public ImageView mVideoBackgroundView;

        public VideoPreference(Context context) {
            super(context);
        }

        public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
            super.onBindViewHolder(preferenceViewHolder);
            Resources resources = ToggleScreenMagnificationPreferenceFragment.this.getPrefContext().getResources();
            final int dimensionPixelSize = resources.getDimensionPixelSize(C1715R.dimen.screen_magnification_video_background_width);
            final int dimensionPixelSize2 = resources.getDimensionPixelSize(C1715R.dimen.screen_magnification_video_width);
            final int dimensionPixelSize3 = resources.getDimensionPixelSize(C1715R.dimen.screen_magnification_video_height);
            final int dimensionPixelSize4 = resources.getDimensionPixelSize(C1715R.dimen.screen_magnification_video_margin_top);
            preferenceViewHolder.setDividerAllowedAbove(false);
            preferenceViewHolder.setDividerAllowedBelow(false);
            this.mVideoBackgroundView = (ImageView) preferenceViewHolder.findViewById(C1715R.C1718id.video_background);
            final VideoView videoView = (VideoView) preferenceViewHolder.findViewById(C1715R.C1718id.video);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.setLooping(true);
                }
            });
            videoView.setAudioFocusRequest(0);
            Bundle arguments = ToggleScreenMagnificationPreferenceFragment.this.getArguments();
            if (arguments != null && arguments.containsKey("video_resource")) {
                videoView.setVideoURI(Uri.parse(String.format("%s://%s/%s", new Object[]{"android.resource", ToggleScreenMagnificationPreferenceFragment.this.getPrefContext().getPackageName(), Integer.valueOf(arguments.getInt("video_resource"))})));
            }
            videoView.setMediaController((MediaController) null);
            this.mLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    int width = VideoPreference.this.mVideoBackgroundView.getWidth();
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) videoView.getLayoutParams();
                    int i = dimensionPixelSize;
                    layoutParams.width = (dimensionPixelSize2 * width) / i;
                    layoutParams.height = (dimensionPixelSize3 * width) / i;
                    layoutParams.setMargins(0, (dimensionPixelSize4 * width) / i, 0, 0);
                    videoView.setLayoutParams(layoutParams);
                    videoView.invalidate();
                    videoView.start();
                }
            };
            this.mVideoBackgroundView.getViewTreeObserver().addOnGlobalLayoutListener(this.mLayoutListener);
        }

        /* access modifiers changed from: protected */
        public void onPrepareForRemoval() {
            this.mVideoBackgroundView.getViewTreeObserver().removeOnGlobalLayoutListener(this.mLayoutListener);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mVideoPreference = new VideoPreference(getPrefContext());
        this.mVideoPreference.setSelectable(false);
        this.mVideoPreference.setPersistent(false);
        this.mVideoPreference.setLayoutResource(C1715R.layout.magnification_video_preference);
        this.mConfigWarningPreference = new Preference(getPrefContext());
        this.mConfigWarningPreference.setSelectable(false);
        this.mConfigWarningPreference.setPersistent(false);
        this.mConfigWarningPreference.setVisible(false);
        this.mConfigWarningPreference.setIcon((int) C1715R.C1717drawable.ic_warning_24dp);
        PreferenceScreen preferenceScreen = getPreferenceManager().getPreferenceScreen();
        preferenceScreen.setOrderingAsAdded(false);
        this.mVideoPreference.setOrder(0);
        this.mConfigWarningPreference.setOrder(2);
        preferenceScreen.addPreference(this.mVideoPreference);
        preferenceScreen.addPreference(this.mConfigWarningPreference);
    }

    public void onResume() {
        super.onResume();
        VideoView videoView = (VideoView) getView().findViewById(C1715R.C1718id.video);
        if (videoView != null) {
            videoView.start();
        }
        updateConfigurationWarningIfNeeded();
    }

    public Dialog onCreateDialog(int i) {
        if (i == 1) {
            if (isGestureNavigateEnabled()) {
                this.mDialog = AccessibilityGestureNavigationTutorial.showGestureNavigationTutorialDialog(getActivity());
            } else {
                this.mDialog = AccessibilityGestureNavigationTutorial.showAccessibilityButtonTutorialDialog(getActivity());
            }
        }
        return this.mDialog;
    }

    public void onSwitchChanged(Switch switchR, boolean z) {
        onPreferenceToggled(this.mPreferenceKey, z);
    }

    /* access modifiers changed from: protected */
    public void onPreferenceToggled(String str, boolean z) {
        if (z && TextUtils.equals("accessibility_display_magnification_navbar_enabled", str)) {
            showDialog(1);
        }
        MagnificationPreferenceFragment.setChecked(getContentResolver(), str, z);
        updateConfigurationWarningIfNeeded();
    }

    /* access modifiers changed from: protected */
    public void onInstallSwitchBarToggleSwitch() {
        super.onInstallSwitchBarToggleSwitch();
        this.mSwitchBar.setCheckedInternal(MagnificationPreferenceFragment.isChecked(getContentResolver(), this.mPreferenceKey));
        this.mSwitchBar.addOnSwitchChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void onRemoveSwitchBarToggleSwitch() {
        super.onRemoveSwitchBarToggleSwitch();
        this.mSwitchBar.removeOnSwitchChangeListener(this);
    }

    /* access modifiers changed from: protected */
    public void onProcessArguments(Bundle bundle) {
        int i;
        super.onProcessArguments(bundle);
        if (bundle != null) {
            if (bundle.containsKey("video_resource")) {
                this.mVideoPreference.setVisible(true);
                bundle.getInt("video_resource");
            } else {
                this.mVideoPreference.setVisible(false);
            }
            if (bundle.containsKey("from_suw")) {
                this.mLaunchFromSuw = bundle.getBoolean("from_suw");
            }
            if (bundle.containsKey("checked")) {
                this.mInitialSetting = bundle.getBoolean("checked");
            }
            if (bundle.containsKey("title_res") && (i = bundle.getInt("title_res")) > 0) {
                getActivity().setTitle(i);
            }
        }
    }

    private boolean isGestureNavigateEnabled() {
        return getContext().getResources().getInteger(17694869) == 2;
    }

    private void updateConfigurationWarningIfNeeded() {
        CharSequence configurationWarningStringForSecureSettingsKey = MagnificationPreferenceFragment.getConfigurationWarningStringForSecureSettingsKey(this.mPreferenceKey, getPrefContext());
        if (configurationWarningStringForSecureSettingsKey != null) {
            this.mConfigWarningPreference.setSummary(configurationWarningStringForSecureSettingsKey);
        }
        this.mConfigWarningPreference.setVisible(configurationWarningStringForSecureSettingsKey != null);
    }
}
