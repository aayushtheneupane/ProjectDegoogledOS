package com.android.settings.dream;

import android.content.Context;
import androidx.preference.Preference;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.widget.GearPreference;
import com.android.settingslib.dream.DreamBackend;
import java.util.Optional;

public class CurrentDreamPreferenceController extends BasePreferenceController {
    private final DreamBackend mBackend;

    public CurrentDreamPreferenceController(Context context, String str) {
        super(context, str);
        this.mBackend = DreamBackend.getInstance(context);
    }

    public int getAvailabilityStatus() {
        return this.mBackend.getDreamInfos().size() > 0 ? 0 : 2;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        setGearClickListenerForPreference(preference);
    }

    public CharSequence getSummary() {
        return this.mBackend.getActiveDreamName();
    }

    private void setGearClickListenerForPreference(Preference preference) {
        if (preference instanceof GearPreference) {
            GearPreference gearPreference = (GearPreference) preference;
            Optional<DreamBackend.DreamInfo> activeDreamInfo = getActiveDreamInfo();
            if (!activeDreamInfo.isPresent() || activeDreamInfo.get().settingsComponentName == null) {
                gearPreference.setOnGearClickListener((GearPreference.OnGearClickListener) null);
            } else {
                gearPreference.setOnGearClickListener(new GearPreference.OnGearClickListener() {
                    public final void onGearClick(GearPreference gearPreference) {
                        CurrentDreamPreferenceController.this.mo9733x67e35ddd(gearPreference);
                    }
                });
            }
        }
    }

    /* renamed from: lambda$setGearClickListenerForPreference$0$CurrentDreamPreferenceController */
    public /* synthetic */ void mo9733x67e35ddd(GearPreference gearPreference) {
        launchScreenSaverSettings();
    }

    private void launchScreenSaverSettings() {
        Optional<DreamBackend.DreamInfo> activeDreamInfo = getActiveDreamInfo();
        if (activeDreamInfo.isPresent()) {
            this.mBackend.launchSettings(this.mContext, activeDreamInfo.get());
        }
    }

    private Optional<DreamBackend.DreamInfo> getActiveDreamInfo() {
        return this.mBackend.getDreamInfos().stream().filter(C0812xf0449e6e.INSTANCE).findFirst();
    }
}
