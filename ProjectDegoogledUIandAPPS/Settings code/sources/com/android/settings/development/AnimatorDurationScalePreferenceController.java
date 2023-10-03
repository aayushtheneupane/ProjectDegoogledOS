package com.android.settings.development;

import android.content.Context;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.view.IWindowManager;
import androidx.preference.Preference;
import com.android.settings.AnimationScalePreference;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.development.DeveloperOptionsPreferenceController;
import com.havoc.config.center.C1715R;

public class AnimatorDurationScalePreferenceController extends DeveloperOptionsPreferenceController implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener, PreferenceControllerMixin {
    static final int ANIMATOR_DURATION_SCALE_SELECTOR = 2;
    static final float DEFAULT_VALUE = 1.0f;
    private final String[] mListSummaries;
    private final String[] mListValues;
    private final IWindowManager mWindowManager = IWindowManager.Stub.asInterface(ServiceManager.getService("window"));

    public String getPreferenceKey() {
        return "animator_duration_scale";
    }

    public AnimatorDurationScalePreferenceController(Context context) {
        super(context);
        this.mListValues = context.getResources().getStringArray(C1715R.array.animator_duration_scale_values);
        this.mListSummaries = context.getResources().getStringArray(C1715R.array.animator_duration_scale_entries);
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        writeAnimationScaleOption(obj);
        return true;
    }

    public void updateState(Preference preference) {
        updateAnimationScaleValue();
    }

    /* access modifiers changed from: protected */
    public void onDeveloperOptionsSwitchDisabled() {
        super.onDeveloperOptionsSwitchDisabled();
        writeAnimationScaleOption((Object) null);
    }

    private void writeAnimationScaleOption(Object obj) {
        float f;
        if (obj != null) {
            try {
                f = Float.parseFloat(obj.toString());
            } catch (RemoteException unused) {
                return;
            }
        } else {
            f = DEFAULT_VALUE;
        }
        this.mWindowManager.setAnimationScale(2, f);
        updateAnimationScaleValue();
    }

    private void updateAnimationScaleValue() {
        try {
            float animationScale = this.mWindowManager.getAnimationScale(2);
            AnimationScalePreference animationScalePreference = (AnimationScalePreference) this.mPreference;
            animationScalePreference.setOnPreferenceClickListener(this);
            animationScalePreference.setScale(animationScale);
        } catch (RemoteException unused) {
        }
    }

    public boolean onPreferenceClick(Preference preference) {
        ((AnimationScalePreference) preference).click();
        return false;
    }
}
