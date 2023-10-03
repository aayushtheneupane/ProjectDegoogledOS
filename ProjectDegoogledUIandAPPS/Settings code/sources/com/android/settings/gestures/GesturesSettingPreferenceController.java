package com.android.settings.gestures;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.display.AmbientDisplayConfiguration;
import android.provider.Settings;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GesturesSettingPreferenceController extends BasePreferenceController {
    private static final String FAKE_PREF_KEY = "fake_key_only_for_get_available";
    private static final String KEY_GESTURES_SETTINGS = "gesture_settings";
    private final AssistGestureFeatureProvider mFeatureProvider;
    private List<AbstractPreferenceController> mGestureControllers;

    public GesturesSettingPreferenceController(Context context) {
        super(context, KEY_GESTURES_SETTINGS);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getAssistGestureFeatureProvider();
    }

    public int getAvailabilityStatus() {
        boolean z;
        if (this.mGestureControllers == null) {
            this.mGestureControllers = buildAllPreferenceControllers(this.mContext);
        }
        Iterator<AbstractPreferenceController> it = this.mGestureControllers.iterator();
        loop0:
        while (true) {
            z = false;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                AbstractPreferenceController next = it.next();
                if (z || next.isAvailable()) {
                    z = true;
                }
            }
        }
        if (z) {
            return 0;
        }
        return 3;
    }

    private static List<AbstractPreferenceController> buildAllPreferenceControllers(Context context) {
        AmbientDisplayConfiguration ambientDisplayConfiguration = new AmbientDisplayConfiguration(context);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new AssistGestureSettingsPreferenceController(context, FAKE_PREF_KEY).setAssistOnly(false));
        arrayList.add(new SwipeToNotificationPreferenceController(context, FAKE_PREF_KEY));
        arrayList.add(new DoubleTwistPreferenceController(context, FAKE_PREF_KEY));
        arrayList.add(new DoubleTapPowerPreferenceController(context, FAKE_PREF_KEY));
        arrayList.add(new PickupGesturePreferenceController(context, FAKE_PREF_KEY).setConfig(ambientDisplayConfiguration));
        arrayList.add(new DoubleTapScreenPreferenceController(context, FAKE_PREF_KEY).setConfig(ambientDisplayConfiguration));
        arrayList.add(new PreventRingingParentPreferenceController(context, FAKE_PREF_KEY));
        return arrayList;
    }

    public CharSequence getSummary() {
        if (!this.mFeatureProvider.isSensorAvailable(this.mContext)) {
            return "";
        }
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean z = true;
        boolean z2 = Settings.Secure.getInt(contentResolver, "assist_gesture_enabled", 1) != 0;
        if (Settings.Secure.getInt(contentResolver, "assist_gesture_silence_alerts_enabled", 1) == 0) {
            z = false;
        }
        if (this.mFeatureProvider.isSupported(this.mContext) && z2) {
            return this.mContext.getText(C1715R.string.language_input_gesture_summary_on_with_assist);
        }
        if (z) {
            return this.mContext.getText(C1715R.string.language_input_gesture_summary_on_non_assist);
        }
        return this.mContext.getText(C1715R.string.language_input_gesture_summary_off);
    }
}
